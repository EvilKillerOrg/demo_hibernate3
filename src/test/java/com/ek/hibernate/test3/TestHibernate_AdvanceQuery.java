package com.ek.hibernate.test3;

import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ek.hibernate.model.pojo2.ZLesson;
import com.ek.hibernate.model.pojo2.ZTeacher;
import com.ek.hibernate.model.util.HibernateSessionFactory;

/**
 *高级查询  集合的过滤 
 */
@SuppressWarnings("unchecked")
public class TestHibernate_AdvanceQuery {

	private Session session;
	
	@Before
	public void initializeSession(){
		session = HibernateSessionFactory.getSession();
	}
	
	@After
	public void closeSession(){
		HibernateSessionFactory.closeSession();
	}
	
	
	
	// 集合的过滤 
	@Test
	public void testQuery1(){  
		//要对得到的结果进行过滤,过滤器配在ZLesson的映射文件中
		
		Query query = session.createQuery("  from ZLesson  where id =1 ");
		
		session.enableFilter("filterTeachers").setParameter("myFilterParam", "%白%"); // 启用filter , 传参

		ZLesson lesson = (ZLesson)query.uniqueResult();
		
		Set<ZTeacher> teSet = lesson.getzTeachers();
		
		for (ZTeacher teacher : teSet) {
			System.out.println(teacher.getTeName()); 
		}
	}
	
	
	
	/**
	 * lazy 和fetch配合使用：(只对在使用get load时影响)
	 * 1、当lazy="true" fetch = "select" 的时候 ， 这个时候是使用了延迟策略，开始只查询出一端实体，多端的不会查询，只有当用到的时候才会发出sql语句去查询 ；
	 * 2、当lazy="false" fetch = "select" 的时候 ， 这个时候是使没有用延迟策略，同时查询出一端和多端，同时产生1+n条sql.
	 * 3、当lazy="true"/lazy="false" fetch = "join"的时候，自己认为这个时候延迟已经没有什么用了，因为采用的是外连接查询，同时把一端和多端都查询出来了，延迟没有起作用。 
	 * 迫切的左外连接检索 就是fetch = "join"的时候 没有懒惰的可能性 或者 HQL 写了 left join fetch
	 */
	@Test
	public void testQuery2(){  
		
		Query query = session.createQuery("  from ZLesson  where id =1 ");

		ZLesson lesson = (ZLesson)query.uniqueResult();
		System.out.println(lesson.getLeName()); //映射设置lazy="true" fetch = "select"   1条SQL
																	  //映射设置lazy="false" fetch = "select"  2条SQL
		
	}
}