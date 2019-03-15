package com.want.mq.model;

import java.util.Comparator;

public class PositionComparator implements Comparator {

	@Override
	public int compare(Object arg0, Object arg1) {
		Position pos0 = (Position) arg0;
		Position pos1 = (Position) arg1;
		int ret = -1;

		if (pos0 != null && pos1 != null && pos0.getId() != null
				&& pos1.getId() != null) {			
			if (pos0.isTopDirector()) {
				if (!pos1.isTopDirector())
					ret = 1;
			} else {
				if (pos0.isDivisionDirector()) {
					if (!pos1.isDivisionDirector())
						ret = 1;
				} else {
					if (pos0.isDepartmentDirector()) {
						if (!pos1.isDepartmentDirector())
							ret = 1;
					} else {
						if (pos0.getEmployee() != null && pos1.getEmployee() != null) {
							if (pos0.getEmployee().getJobGrade()!=null && pos1.getEmployee().getJobGrade()!=null) {
								int g0 = Integer.parseInt(pos0.getEmployee().getJobGrade().trim());
								int g1 = Integer.parseInt(pos1.getEmployee().getJobGrade().trim());
								
								if (g0 > g1)
									ret = 1;
							} else
								ret = pos0.getId().compareTo(pos1.getId());
						} else
							ret = pos0.getId().compareTo(pos1.getId());
					}
				}
			}
		}
		
		return ret;

	}

}
