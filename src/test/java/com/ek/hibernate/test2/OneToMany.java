package com.ek.hibernate.test2;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ek.hibernate.model.pojo2.ZLesson;
import com.ek.hibernate.model.pojo2.ZTeacher;
import com.ek.hibernate.model.util.HibernateSessionFactory;
@SuppressWarnings("unchecked")
public class OneToMany {

	/**
	 * 级联需要关注的2个参数
	 * inverse 决定主键有谁来维护
	 * cascade 决定知否级联操作(默认是不级联的)
	 */
	
	private Session session = null;

	@Before
	public void initializeSession(){
		session = HibernateSessionFactory.getSession();
	}
	
	@After
	public void closeSession(){
		HibernateSessionFactory.closeSession();
	}
	
	@Test //通过外键对象级联主键对象 外键对象要配置cascade
	public void testAdd1(){
		ZTeacher teacher = new ZTeacher();
		teacher.setTeName("黑老师");
		teacher.setTeSex("1");
		ZLesson lesson = new ZLesson();
		lesson.setLeName("语文");
		lesson.setHours(1);
		
		teacher.setzLesson(lesson); //通过teacher级联lesson
		
		Transaction tr = null; 
		
		try {
			
			tr = session.beginTransaction();
			session.save(teacher);
			tr.commit();
			
		} catch (HibernateException e) {
			if(tr!=null){
				tr.rollback();
			}
			e.printStackTrace();
		}
	}
	
	
	
	@Test //通过主键对象级联外键对象 主键对象配置文件中 除了要设置 cascade 还要设置inverse="false"否则没有外键值
	public void testAdd2(){
		ZTeacher teacher = new ZTeacher();
		teacher.setTeName("李老师");
		teacher.setTeSex("1");
		ZLesson lesson = new ZLesson();
		lesson.setLeName("数学");
		lesson.setHours(1);
		
//		Set<ZTeacher> teSet = new HashSet<ZTeacher>();
//		teSet.add(teacher);
//		lesson.setzTeachers(teSet); //
		
		lesson.getzTeachers().add(teacher); //通过lesson级联teacher (上三行可以这样写)
		
		Transaction tr = null; 
		
		try {
			
			tr = session.beginTransaction();
			session.save(lesson);
			tr.commit();
			
		} catch (HibernateException e) {
			if(tr!=null){
				tr.rollback();
			}
			e.printStackTrace();
		}
	}
	
	
	
	@Test // like '%李%' 带的课程
	public void testQuery1(){
		Query query = session.createQuery(" from ZLesson l inner join fetch l.zTeachers  t where  t.teName like '%李%' " );
		List<ZLesson> list = query.list();
		
		for (ZLesson lesson : list) {
			System.out.println(lesson.getLeName());
		}
	}
	/**
	 * from Brand as b where b.styles.styleId=?  
	 * Hibernate3.2.2以前,Hibernate会对关联实体自动使用隐式的inner join,上面的HQL语句毫无问题
	 * 
	 * Hibernate3.2.2以后,如果styles是一个单个的关联实体或者是一个普通的属性,hibernate就会自动使用隐式的inner join.
	 * 如果styles是一个集合,将会出现 org.hibernate.QueryException: illegal attempt to dereference collection异常。
	 * 要使用如下形式的HQL语句：
	 * from Brand as b inner join fetch b.styles as s where s.styleId=?  
	 * 
	 * from ZLesson where zTeachers.teName like '%k%'
	 * from ZLesson l inner join fetch l.zTeachers  t where  t.teName like '%t%'
	 */
	
	
	
	@Test // 有两个以上老师带的一门课程
	public void testQuery2(){
		Query query = session.createQuery(" from ZLesson where zTeachers.size >=2 "); //等价于  from ZLesson where size(zTeachers) >=2
		List<ZLesson> list = query.list();
		
		for (ZLesson lesson : list) {
			System.out.println(lesson.getLeName());
		}
	}
	
	
	
	@Test //找出带相同课程的老师 (找出和白老师带的课程 相同的其他老师)
	public void testQuery3(){
		Query query = session.createQuery(" select  l.zTeachers from ZLesson l inner join l.zTeachers  t where  t.teName like '%白%' ");
		List<ZTeacher> list = query.list();//3.2.2以前:  select l.zTeachers from ZLesson l where  l.zTeachers.teName like '%白%' 
 
		for (ZTeacher teacher : list) {
			System.out.println(teacher.getTeName());
		}
	}
	/**
	 * select   l.zTeachers  from ZLesson l inner join fetch l.zTeachers  t where  t.teName like '%白%'
	 * 报错:org.hibernate.QueryException: query specified join fetching, but the owner of the fetched association was not present in the select list
	 * 
	 * 报这个异常是因为查询时返回的对象不包含有该join fetch后面的对象，因此可以将fetch删除就可以啦.
	 * 这里的fetch就是将fetch后面的对象setter到查询返回的对象中。因此当不存在包含时，就会该报错！
	 */
}