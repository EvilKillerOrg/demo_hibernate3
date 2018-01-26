package com.ek.hibernate.test3;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ek.hibernate.model.pojo2.ZAddress;
import com.ek.hibernate.model.pojo2.ZLesson;
import com.ek.hibernate.model.pojo2.ZStudent;
import com.ek.hibernate.model.pojo2.ZTeacher;
import com.ek.hibernate.model.util.HibernateSessionFactory;
@SuppressWarnings({ "unchecked", "rawtypes" })
public class TestHibernate_JoinQuery {

	private Session session;
	
	@Before
	public void initializeSession(){
		session = HibernateSessionFactory.getSession();
	}
	
	@After
	public void closeSession(){
		HibernateSessionFactory.closeSession();
	}
	
	
	//非常确定 只有一条记录返回时 可以用uniqueResult
	@Test
	public void testGetUserInfo4(){
		try {
			Query	query = session.createQuery(" select count(*) from TestUserInfo "); //统计查询
			 
			System.out.println(query.uniqueResult()); 
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 连接查询 , 连接查询要用HQL
	 */
	
	/**
	 * 内连接(自然连接) : 数据有关联才称查询到
	 * SELECT * FROM z_student t , z_address a WHERE t.address_uuid = a.uuid 
	 * SELECT * FROM z_student t  INNER JOIN  z_address a ON t.address_uuid = a.uuid 
	 * 
	 * 外链接 :
	 * SELECT * FROM z_teacher t LEFT OUTER JOIN z_lesson l ON  t.lesson_uuid = l.uuid     左外 左边的表列出所有数据
	 * SELECT * FROM z_teacher t RIGHT OUTER JOIN z_lesson l ON  t.lesson_uuid = l.uuid  右外 右边的表列出所有数据
	 * 
	 * 全连接 :
	 * SELECT * FROM z_teacher t FULL  JOIN z_lesson l ON  t.lesson_uuid = l.uuid 两边数据都列出来
	 * 全连接 MySql , Hibernate3.1 , 3.2 不支持 全连接
	 */
	
	
	
	@Test
	public void testInnerJoin1(){
		Query query = session.createQuery(" from ZStudent s inner join s.zAddress "); //inner join返回的不是ZStudent 是 Object[]
		List<Object[]> list = query.list();
		for (Object[] obj : list) {
			System.out.println(((ZStudent)obj[0]).getStName());
			System.out.println(((ZAddress)obj[1]).getStreet());
		}
	}
	
	

	@Test
	public void testInnerJoin2(){  
	
		Query query = session.createQuery(" select l from ZLesson l inner join fetch l.zTeachers "); //这select l 返回的就是ZLesson
		//Query query = session.createQuery(" select distinct l from ZLesson l inner join l.zTeachers "); //ZLesson有相同的 用distinct 是一种方法

		List<ZLesson> list = query.list();
		Set<ZLesson> lessonSet = new HashSet();  //把query 得到的 list 放在一个set里 再迭代set 也可以去除相同的(set中不能有重复的)
		lessonSet.addAll(list);
		
		for (ZLesson lesson : lessonSet) {
				System.out.print(lesson.getUuid());
				System.out.println(lesson.getLeName());
		}
	}
	
	
	
	@Test
	public void testOuterJoin1(){  
		
		/**
		 * fetch(非懒惰的) 查到zTeachers 立即放到 ZLesson里 懒惰的就意味着zTeachers在用的时候才加载给ZLesson , (看看有无fetch时后台的SQL)
		 */
		Query query = session.createQuery(" select l from ZLesson l left outer  join fetch  l.zTeachers "); //这select l 返回的就是ZLesson

		List<ZLesson> list = query.list();
		Set<ZLesson> lessonSet = new HashSet();  
		lessonSet.addAll(list);
		
		for (ZLesson lesson : lessonSet) {
			System.out.print(lesson.getUuid());
			System.out.println(lesson.getLeName());
			Set<ZTeacher> set = lesson.getzTeachers();
			for (ZTeacher teacher : set) {
				System.out.println(teacher.getTeName());
			}
		}
	}
}