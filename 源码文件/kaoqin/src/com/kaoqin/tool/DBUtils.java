package com.kaoqin.tool;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 自己封装的数据库工具类，包含一些数据库的简单操作方法
 */

public class DBUtils {
	/** 声明驱动加载 */
	private static String driverClass;
	/** 声明数据库连接的URL */
	private static String url;
	/** 声明数据库的登录名 */
	private static String username;
	/** 声明数据库的密码 */
	private static String password;
	/** 设置最大连接数 */
	private static int maxActive;
	/** 设置最小的闲置连接数 */
	private static int minIdle;
	/** 设置初始连接数 */
	private static int initialSize;
	/** 设置等待连接时间 */
	private static long maxWait;
	/** 声明连接池的数据源 */
	private static DruidDataSource dataSource;

	// 1.加载驱动 静态语句块，只会在类加载时执行一次，不会反复执行
	static {
		init();
	}

	// 2.获取数据库连接
	public static Connection getconn() {
		// 确保连接池是单例
		if (dataSource == null || dataSource.isClosed()) {
			// 重新初始化连接池
			init();
		}
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 初始化数据源方法
	public static void init() {
		try {
			Properties prop = new Properties();
			InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
			prop.load(inputStream);
			driverClass = prop.getProperty("driverClass");
			url = prop.getProperty("url");
			username = prop.getProperty("user");
			password = prop.getProperty("password");
			maxActive = Integer.parseInt(prop.getProperty("maxActive"));
			minIdle = Integer.parseInt(prop.getProperty("minIdle"));
			initialSize = Integer.parseInt(prop.getProperty("initialSize"));
			maxWait = Long.parseLong(prop.getProperty("maxWait"));

			// 创建连接池对象
			dataSource = new DruidDataSource();
			dataSource.setDriverClassName(driverClass);
			dataSource.setUrl(url);
			dataSource.setUsername(username);
			dataSource.setPassword(password);

			// 设置最大连接数
			dataSource.setMaxActive(maxActive);
			// 设置最小的闲置连接数
			dataSource.setMinIdle(minIdle);
			// 设置初始连接数
			dataSource.setInitialSize(initialSize);
			// 设置等待连接时间
			dataSource.setMaxWait(maxWait);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭连接池
	 */
	public static synchronized void closePool() {
		if (dataSource != null) {
			dataSource.close();
		}
	}

	// 3.关闭资源
	public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 4.查询并返回结果集
	public static <T> List<T> query(String sql, CallBack<T> callback, Object... objects) {
		List<T> list = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getconn();
			ps = conn.prepareStatement(sql);
			if (Objects.nonNull(objects)) {
				for (int i = 0; i < objects.length; i++) {
					ps.setObject(i + 1, objects[i]);
				}
			}
			// 执行查询
			rs = ps.executeQuery();
			list = callback.call(rs);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, ps, conn);
		}
		return null;
	}

	// 5.更新操作
	public static int executeUpdate(Connection conn, String sql, Object... objects) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < objects.length; i++) {
				ps.setObject(i + 1, objects[i]);
			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(null, ps, null);
		}
		return 0;
	}

	/**
	 * 内部接口，里面包含一个函数(回调函数)
	 * 
	 * @author dell
	 */
	@FunctionalInterface
	public interface CallBack<T> {
		List<T> call(ResultSet rs) throws SQLException;
	}

	/**
	 * 封装通用查询对象操作，对于任何形式的查询，都能返回一个对象
	 * 
	 * @param t   需要返回的数据类型（泛型）
	 * @param sql 目标查询sql语句
	 * @param obj 执行查询需要的参数
	 * @return
	 */
	public static <T> T queryOneObject(Class<T> t, String sql, Object... objects) {
		T obj = null;
		// 获取查询结果
		List<Map<String, Object>> list = getDataPair(sql, objects);
		// 如果集合非空
		if (!list.isEmpty()) {
			Map<String, Object> map = list.get(0);
			obj = paseMapToBean(map, t);
		}
		return obj;
	}

	/**
	 * 封装通用查询对象操作，对于任何形式的查询，都能返回一个对象的集合
	 * 
	 * @param t   需要返回的数据类型（泛型）
	 * @param sql 目标查询sql语句
	 * @param obj 执行查询需要的参数
	 * @return
	 */
	public static <T> List<T> queryList(Class<T> t, String sql, Object... objects) {
		// 声明一个空集合
		List<T> data = new ArrayList<>();
		// 获取查询结果
		List<Map<String, Object>> list = getDataPair(sql, objects);
		// 如果集合为空
		if (list.isEmpty()) {
			return data;
		}
		// 遍历集合
		for (Map<String, Object> map : list) {
			T obj = paseMapToBean(map, t);
			data.add(obj);
		}
		return data;
	}

	/**
	 * 把Map集合解析为一个对象
	 * 
	 * @param map
	 * @param t
	 * @return
	 */
	private static <T> T paseMapToBean(Map<String, Object> map, Class<T> t) {
		T obj = null;
		try {
			// 先创建一个空的实例
			obj = t.newInstance();
			// 获取Map集合的键集（所有列名称，即要返回对象的属性名）
			Set<String> keys = map.keySet();
			for (String cname : keys) {
				// 获取属性对象
				Field f = t.getDeclaredField(cname);
				// 获取set方法名字
				String setMethodName = "set" + cname.substring(0, 1).toUpperCase() + cname.substring(1);
				// 获取set方法对象
				Method setMethod = t.getMethod(setMethodName, f.getType());
				// 执行方法把map集合的值赋给obj对象
				setMethod.invoke(obj, map.get(cname));
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * 把查询返回的结果集存在List<Map<String, Object>>集合里面，每一个Map<String, Object>都是一个对象
	 * @param sql 目标查询sql语句
	 * @param obj 执行查询需要的参数
	 * @return
	 */
	private static List<Map<String, Object>> getDataPair(String sql, Object... objects) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, Object> maps = null;
		// 声明集合存储获取的表数据（列名，列值）
		List<Map<String, Object>> list = new ArrayList<>();
		try {
			conn = getconn();
			ps = conn.prepareStatement(sql);
			if (Objects.nonNull(objects)) {
				for (int i = 0; i < objects.length; i++) {
					ps.setObject(i + 1, objects[i]);
				}
			}
			// 执行查询
			rs = ps.executeQuery();
			// 获取结果集元数据
			ResultSetMetaData rsmd = rs.getMetaData();
			// 获取列总数、列值，将相关数据存储在Map集合
			int count = rsmd.getColumnCount();

			while (rs.next()) {
				// 对结果集每遍历一次就获取一条数据（即一个maps集合）
				maps = new HashMap<>();
				// 获取所有列的列名称、标签名、
				for (int i = 1; i <= count; i++) {
					// 列名称
					String ColumnName = rsmd.getColumnName(i);
					// 标签名
					// String ColumnLabel = rsmd.getColumnLabel(i);
					// 把对象存在maps中
					// maps.put(ColumnName, rs.getObject(ColumnLabel));
					/** 如果对象不为空，添加到Map集合中 */
					if (Objects.nonNull(rs.getObject(i))) {
						maps.put(ColumnName, rs.getObject(i));
					}
				}
				// 把maps存在list集合中
				list.add(maps);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, ps, conn);
		}
		return list;
	}
}
