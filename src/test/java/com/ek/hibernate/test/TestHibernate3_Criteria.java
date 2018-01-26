package com.ek.hibernate.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ek.hibernate.model.pojo1.TestUserInfo;
import com.ek.hibernate.model.util.HibernateSessionFactory;

// Criteria 标准查询 

@SuppressWarnings("unchecked")
public class TestHibernate3_Criteria {
	
	private Session session = null;
	
	@Before
	public void initializeSession(){
		session = HibernateSessionFactory.getSession();
	}
	
	@After
	public void closeSession(){
		HibernateSessionFactory.closeSession();
	}
	
	
	
	//org.hibernate.criterion.Restrictions实现 (org.hibernate.criterion.Expression;不赞成了 用他的父类 Restrictions 替换)
	@Test
	public void testfindUserInfo1(){
		Criteria cirteria= session.createCriteria(TestUserInfo.class);   //第二个参数 是别名 用来在级联的时候 指向级联的关联关系的
		
		//产生查询条件  
		//cirteria.add(Restrictions.eq("sex", true)); //.eq(属性 , 值) 相当于 = 
		cirteria.add(Restrictions.ilike("username", "%a%")); // .ilike(属性 , 值) 相当于 like
		
		List<TestUserInfo> list = cirteria.list();
		for (TestUserInfo userInfo : list) {
			System.out.println(userInfo.getUsername());
		}
	}
	
	
	
	//org.hibernate.criterion.Property实现
	@Test
	public void testfindUserInfo2(){
		Criteria cirteria= session.createCriteria(TestUserInfo.class);    
		
		//产生查询条件  
		//cirteria.add(Property.forName("username").eq("ek")); //.forName(列名).eq(值)
		cirteria.add(Property.forName("username").like("%a%"));
		
		List<TestUserInfo> list = cirteria.list();
		for (TestUserInfo userInfo : list) {
			System.out.println(userInfo.getUsername());
		}
	}
	
	
	
	//多条件 或 操作
	@Test
	public void testfindUserInfo3(){
		Criteria cirteria= session.createCriteria(TestUserInfo.class);    
		
		//产生查询条件  
		//2个条件 or
		cirteria.add(Restrictions.or(Restrictions.ilike("username","%a%") , Restrictions.ilike("username","%m%"))); 
		//3个条件 or  ... 一直在Restrictions.or() 中嵌套下去
		//cirteria.add(Restrictions.or(Restrictions.ilike("username", "%k%") , Restrictions.or(Restrictions.ilike("username","%a%") , Restrictions.ilike("username","%m%"))));
		
		List<TestUserInfo> list = cirteria.list();
		for (TestUserInfo userInfo : list) {
			System.out.println(userInfo.getUsername());
		}
	}
	
	
	
	//多条件 或 操作
	@Test
	public void testfindUserInfo4(){
		Criteria cirteria= session.createCriteria(TestUserInfo.class);    
		
		//产生查询条件  
		// or关系 这样可以减少嵌套
		cirteria.add(Restrictions.disjunction()
				.add(Restrictions.isNull("sex"))
				.add(Restrictions.eq("sex", false))
				.add(Restrictions.eq("sex", true))); 
		
		List<TestUserInfo> list = cirteria.list();
		for (TestUserInfo userInfo : list) {
			System.out.println(userInfo.getUsername());
		}
	}
	
	
	
	//多条件 或 操作
	@Test
	public void testfindUserInfo5(){
		Criteria cirteria= session.createCriteria(TestUserInfo.class);    
		
		//产生查询条件
		// 直接用sql 这样不符合hibernate 设计的原则 
		// cirteria.add(Restrictions.sqlRestriction("")); //查询所有 , 没有where 参数空就可以
		cirteria.add(Restrictions.sqlRestriction(" {alias}.username like ? ", "%k%", Hibernate.STRING)); //{alias}.是别名 , 这种方式不加{alias}.没有别名
		
		List<TestUserInfo> list = cirteria.list();
		for (TestUserInfo userInfo : list) {
			System.out.println(userInfo.getUsername());
		}
	}
	
	
	
	//order by
	@Test
	public void testfindUserInfo6(){
		Criteria cirteria= session.createCriteria(TestUserInfo.class);    
		
		//产生查询条件
		cirteria.addOrder(Order.desc("username"));
		
		List<TestUserInfo> list = cirteria.list();
		for (TestUserInfo userInfo : list) {
			System.out.println(userInfo.getUsername());
		}
	}
	
	
	
	//投影 (就是取一部分列)
	@Test
	public void testfindUserInfo7(){
		Criteria cirteria= session.createCriteria(TestUserInfo.class);    
		
		//投影 , 取单个列
		cirteria.setProjection(Property.forName("username"));
		
		List<String> list = cirteria.list();
		for (String username : list) {
			System.out.println(username);
		}
	}
	
	
	
	//聚合
	@Test
	public void testfindUserInfo8(){
		Criteria cirteria= session.createCriteria(TestUserInfo.class);    
		
		//聚合 , 记录条数
		cirteria.setProjection(Projections.rowCount());
		
		Integer count = (Integer)cirteria.uniqueResult(); //确定只有一条记录的时候可以用uniqueResult
		
		System.out.println(count);
	}
	
	
	
	//分组
	@Test
	public void testfindUserInfo9(){
		Criteria cirteria= session.createCriteria(TestUserInfo.class);    
		 	
		//分组
		//cirteria.setProjection(Projections.groupProperty("sex"));
		//多条件 分组+统计 用 projectionList
		cirteria.setProjection(Projections.projectionList()
				.add(Projections.groupProperty("sex"))
				.add(Projections.rowCount()) );
		
		List<Object[]> list = cirteria.list(); //确定只有一条记录的时候可以用uniqueResult
		for (Object[] obj : list) {
			System.out.println(obj[0]);
			System.out.println(obj[1]);
		}
	}
	
	
	
	//离线查询  session在没有开启的情况下 先生成SQL
	@Test
	public void testfindUserInfo10(){
		DetachedCriteria dc = DetachedCriteria.forClass(TestUserInfo.class)
				.add(Property.forName("sex").eq(true));
		
		Criteria criteria = dc.getExecutableCriteria(session); //开启session
		
		List<TestUserInfo> list = criteria.list();
		 for (TestUserInfo userInfo : list) {
			System.out.println(userInfo.getUsername());
		}
	}
	
	
	
	//离线查询  session在没有开启的情况下 先生成SQL
	@Test
	public void testfindUserInfo11(){
		DetachedCriteria dc = DetachedCriteria.forClass(TestUserInfo.class)
				.add(Property.forName("sex").eq(true));
		
		Criteria criteria = dc.getExecutableCriteria(session); //开启session
		
		List<TestUserInfo> list = criteria.list();
		 for (TestUserInfo userInfo : list) {
			System.out.println(userInfo.getUsername());
		}
	}
	
	
	
	//找到最大值(age最大的)
	@Test
	public void testfindUserInfo12(){
		DetachedCriteria dc = DetachedCriteria.forClass(TestUserInfo.class)
				.setProjection(Property.forName("age"));
		
		Criteria criteria = session.createCriteria(TestUserInfo.class);
		
		criteria.add(Subqueries.propertyGeAll("age", dc));
		
		List<TestUserInfo> list = criteria.list();
		 for (TestUserInfo userInfo : list) {
			System.out.println(userInfo.getUsername()+" , age:"+userInfo.getAge());
		}
	}
}