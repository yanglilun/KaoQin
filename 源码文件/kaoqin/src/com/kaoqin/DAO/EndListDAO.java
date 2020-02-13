package com.kaoqin.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.kaoqin.DTO.EndList;
import com.kaoqin.DTO.WeekLateList;

/**
 * 最终表（虚表）操作工具
 * @author garen
 */
public class EndListDAO {
	
	/**计算所有weeklist的time，存入endlist中*/
	public List<EndList> initEndList(List<WeekLateList> wll) {
		List<EndList> endlist = new ArrayList<EndList>();
		
		//遍历所有迟到信息
		for (WeekLateList w : wll) {
//			获取当前姓名
			String sname = w.getSname();
//			过滤，只获取当前姓名的list
			List<WeekLateList> namelist = wll.stream().filter(new Predicate<WeekLateList>() {

				@Override
				public boolean test(WeekLateList t) {
					return t.getSname().equals(sname);
				}
			}).collect(Collectors.toList());
//			计算alltime总次数
			int alltime = 0;
			int num = 0;
			for (WeekLateList temp : namelist) {
				num++;
				int value = temp.getTime().intValue();
				alltime += value;
			}
//			创建endlist对象
			EndList end = new EndList();
			end.setSname(sname);
			end.setWeek(w.getWeek());
			end.setTime(w.getTime());
			end.setAlltime(alltime);
			end.setNum(num);
			endlist.add(end);
		}
		return endlist;
	}
}
