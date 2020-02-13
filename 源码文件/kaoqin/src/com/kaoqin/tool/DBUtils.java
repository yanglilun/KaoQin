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
 * �Լ���װ�����ݿ⹤���࣬����һЩ���ݿ�ļ򵥲�������
 */

public class DBUtils {
	/** ������������ */
	private static String driverClass;
	/** �������ݿ����ӵ�URL */
	private static String url;
	/** �������ݿ�ĵ�¼�� */
	private static String username;
	/** �������ݿ������ */
	private static String password;
	/** ������������� */
	private static int maxActive;
	/** ������С������������ */
	private static int minIdle;
	/** ���ó�ʼ������ */
	private static int initialSize;
	/** ���õȴ�����ʱ�� */
	private static long maxWait;
	/** �������ӳص�����Դ */
	private static DruidDataSource dataSource;

	// 1.�������� ��̬���飬ֻ���������ʱִ��һ�Σ����ᷴ��ִ��
	static {
		init();
	}

	// 2.��ȡ���ݿ�����
	public static Connection getconn() {
		// ȷ�����ӳ��ǵ���
		if (dataSource == null || dataSource.isClosed()) {
			// ���³�ʼ�����ӳ�
			init();
		}
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// ��ʼ������Դ����
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

			// �������ӳض���
			dataSource = new DruidDataSource();
			dataSource.setDriverClassName(driverClass);
			dataSource.setUrl(url);
			dataSource.setUsername(username);
			dataSource.setPassword(password);

			// �������������
			dataSource.setMaxActive(maxActive);
			// ������С������������
			dataSource.setMinIdle(minIdle);
			// ���ó�ʼ������
			dataSource.setInitialSize(initialSize);
			// ���õȴ�����ʱ��
			dataSource.setMaxWait(maxWait);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �ر����ӳ�
	 */
	public static synchronized void closePool() {
		if (dataSource != null) {
			dataSource.close();
		}
	}

	// 3.�ر���Դ
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

	// 4.��ѯ�����ؽ����
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
			// ִ�в�ѯ
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

	// 5.���²���
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
	 * �ڲ��ӿڣ��������һ������(�ص�����)
	 * 
	 * @author dell
	 */
	@FunctionalInterface
	public interface CallBack<T> {
		List<T> call(ResultSet rs) throws SQLException;
	}

	/**
	 * ��װͨ�ò�ѯ��������������κ���ʽ�Ĳ�ѯ�����ܷ���һ������
	 * 
	 * @param t   ��Ҫ���ص��������ͣ����ͣ�
	 * @param sql Ŀ���ѯsql���
	 * @param obj ִ�в�ѯ��Ҫ�Ĳ���
	 * @return
	 */
	public static <T> T queryOneObject(Class<T> t, String sql, Object... objects) {
		T obj = null;
		// ��ȡ��ѯ���
		List<Map<String, Object>> list = getDataPair(sql, objects);
		// ������Ϸǿ�
		if (!list.isEmpty()) {
			Map<String, Object> map = list.get(0);
			obj = paseMapToBean(map, t);
		}
		return obj;
	}

	/**
	 * ��װͨ�ò�ѯ��������������κ���ʽ�Ĳ�ѯ�����ܷ���һ������ļ���
	 * 
	 * @param t   ��Ҫ���ص��������ͣ����ͣ�
	 * @param sql Ŀ���ѯsql���
	 * @param obj ִ�в�ѯ��Ҫ�Ĳ���
	 * @return
	 */
	public static <T> List<T> queryList(Class<T> t, String sql, Object... objects) {
		// ����һ���ռ���
		List<T> data = new ArrayList<>();
		// ��ȡ��ѯ���
		List<Map<String, Object>> list = getDataPair(sql, objects);
		// �������Ϊ��
		if (list.isEmpty()) {
			return data;
		}
		// ��������
		for (Map<String, Object> map : list) {
			T obj = paseMapToBean(map, t);
			data.add(obj);
		}
		return data;
	}

	/**
	 * ��Map���Ͻ���Ϊһ������
	 * 
	 * @param map
	 * @param t
	 * @return
	 */
	private static <T> T paseMapToBean(Map<String, Object> map, Class<T> t) {
		T obj = null;
		try {
			// �ȴ���һ���յ�ʵ��
			obj = t.newInstance();
			// ��ȡMap���ϵļ��������������ƣ���Ҫ���ض������������
			Set<String> keys = map.keySet();
			for (String cname : keys) {
				// ��ȡ���Զ���
				Field f = t.getDeclaredField(cname);
				// ��ȡset��������
				String setMethodName = "set" + cname.substring(0, 1).toUpperCase() + cname.substring(1);
				// ��ȡset��������
				Method setMethod = t.getMethod(setMethodName, f.getType());
				// ִ�з�����map���ϵ�ֵ����obj����
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
	 * �Ѳ�ѯ���صĽ��������List<Map<String, Object>>�������棬ÿһ��Map<String, Object>����һ������
	 * @param sql Ŀ���ѯsql���
	 * @param obj ִ�в�ѯ��Ҫ�Ĳ���
	 * @return
	 */
	private static List<Map<String, Object>> getDataPair(String sql, Object... objects) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, Object> maps = null;
		// �������ϴ洢��ȡ�ı����ݣ���������ֵ��
		List<Map<String, Object>> list = new ArrayList<>();
		try {
			conn = getconn();
			ps = conn.prepareStatement(sql);
			if (Objects.nonNull(objects)) {
				for (int i = 0; i < objects.length; i++) {
					ps.setObject(i + 1, objects[i]);
				}
			}
			// ִ�в�ѯ
			rs = ps.executeQuery();
			// ��ȡ�����Ԫ����
			ResultSetMetaData rsmd = rs.getMetaData();
			// ��ȡ����������ֵ����������ݴ洢��Map����
			int count = rsmd.getColumnCount();

			while (rs.next()) {
				// �Խ����ÿ����һ�ξͻ�ȡһ�����ݣ���һ��maps���ϣ�
				maps = new HashMap<>();
				// ��ȡ�����е������ơ���ǩ����
				for (int i = 1; i <= count; i++) {
					// ������
					String ColumnName = rsmd.getColumnName(i);
					// ��ǩ��
					// String ColumnLabel = rsmd.getColumnLabel(i);
					// �Ѷ������maps��
					// maps.put(ColumnName, rs.getObject(ColumnLabel));
					/** �������Ϊ�գ���ӵ�Map������ */
					if (Objects.nonNull(rs.getObject(i))) {
						maps.put(ColumnName, rs.getObject(i));
					}
				}
				// ��maps����list������
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
