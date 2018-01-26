package com.ek.hibernate.test3;

import java.util.Set;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ek.hibernate.model.pojo2.ZLesson;
import com.ek.hibernate.model.pojo2.ZTeacher;
import com.ek.hibernate.model.util.HibernateSessionFactory;

/**
 *  排序 
 */

@SuppressWarnings("unchecked")
public class TestHibernate_Sort {
	
	private Session session;
	
	@Before
	public void initializeSession(){
		session = HibernateSessionFactory.getSession();
	}
	
	@After
	public void closeSession(){
		HibernateSessionFactory.closeSession();
	}
	
	
	
	@Test
	public void testSort1(){
		session.createQuery(" form TestUserInfo order by username "); //数据库中排序
	}
	
	
	
	// 这也是数据库中排序
	// 给ZLesson 中的  ZTeacher排序 
	// 通过配置映射文件ZLesson中的ZTeacher SET 配置 order-by="te_name asc/desc" 按老师名称排序
	@Test
	public void testSort2(){
		ZLesson lesson = (ZLesson)session.get(ZLesson.class, 1);
		Set<ZTeacher> teachers = lesson.getzTeachers();
		for (ZTeacher teacher : teachers) {
			System.out.println(teacher.getTeName());
		}
	}
	
	
	
	// 内存中排序(自然排序)
	// 给ZLesson 中的  ZTeacher排序 
	// 通过配置映射文件ZLesson中的ZTeacher SET 配置 sort="unsorted"   unsorted : 不排序  natural : 自然排序
	// 被排序的对象 要实现一个 java.lang.Comparable 接口 和此接口的compareTo方法
	@Test
	public void testSort3(){
		ZLesson lesson = (ZLesson)session.get(ZLesson.class, 1);
		Set<ZTeacher> teachers = lesson.getzTeachers();
		for (ZTeacher teacher : teachers) {
			System.out.println(teacher.getTeName());
		}
	}
	
	
	
	//自定义排序  建一个MyComparator类 实现Comparator接口
	// 通过配置映射文件ZLesson中的ZTeacher SET 配置 sort="com.ek.hibernate.model.util.MyComparator"
	@Test
	public void testSort4(){
		ZLesson lesson = (ZLesson)session.get(ZLesson.class, 1);
		Set<ZTeacher> teachers = lesson.getzTeachers();
		for (ZTeacher teacher : teachers) {
			System.out.println(teacher.getTeName());
		}
	}
}