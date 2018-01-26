package com.ek.hibernate.model.util;

import java.util.Comparator;

import com.ek.hibernate.model.pojo2.ZTeacher;

public class MyComparator implements Comparator<ZTeacher> {

	@Override
	public int compare(ZTeacher arg0, ZTeacher arg1) {
		 // 需要什么样的排序规则要在这里写 就不用compareTo了
		return arg0.compareTo(arg1);
		
	}

}