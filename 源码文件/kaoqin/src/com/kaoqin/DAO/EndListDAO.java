package com.kaoqin.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.kaoqin.DTO.EndList;
import com.kaoqin.DTO.WeekLateList;

/**
 * ���ձ������������
 * @author garen
 */
public class EndListDAO {
	
	/**��������weeklist��time������endlist��*/
	public List<EndList> initEndList(List<WeekLateList> wll) {
		List<EndList> endlist = new ArrayList<EndList>();
		
		//�������гٵ���Ϣ
		for (WeekLateList w : wll) {
//			��ȡ��ǰ����
			String sname = w.getSname();
//			���ˣ�ֻ��ȡ��ǰ������list
			List<WeekLateList> namelist = wll.stream().filter(new Predicate<WeekLateList>() {

				@Override
				public boolean test(WeekLateList t) {
					return t.getSname().equals(sname);
				}
			}).collect(Collectors.toList());
//			����alltime�ܴ���
			int alltime = 0;
			int num = 0;
			for (WeekLateList temp : namelist) {
				num++;
				int value = temp.getTime().intValue();
				alltime += value;
			}
//			����endlist����
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
