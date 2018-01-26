package com.ek.hibernate.test3;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ek.hibernate.model.pojo1.TestUserInfo;
import com.ek.hibernate.model.util.HibernateSessionFactory;

public class TestHibernate_JTA {
	
	
	/**
	 * Hibernate 配置文件 配置 JTA事务(Weblogic)
	 *  <property name="transaction.factory_class">org.hibernate.transaction.JTATransactionFactory</property>
  	 *	<property name="transaction.manager_lookup_class">org.hibernate.transaction.WeblogicTransactionManagerLookup</property>
  	 *	<property name="jta.UserTransaction">javax/transaction/UserTransaction</property> 
	 */
	/**
	 *  事务隔离级别
	 *  Read uncommitted (读未提交) 就是一个事务可以读取另一个未提交事务的数据。
	 *  Read committed (读提交) 就是一个事务要等另一个事务提交后才能读取数据。
	 *  Repeatable read (重复读) 就是在开始读取数据（事务开启）时，不再允许修改操作
	 *  Serializable (序列化 ) 是最高的事务隔离级别，在该级别下，事务串行化顺序执行，可以避免脏读、不可重复读与幻读。但是这种事务隔离级别效率低下，比较耗数据库性能，一般不使用。
	 */
	/**
	 *  Hibernate 通过锁 来实现事务隔离级别
	 */
	
	
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
	public void testLockMode(){
		TestUserInfo user = (TestUserInfo)session.get(TestUserInfo.class, 1); //悲观锁 //version 是乐观锁
		//<timestamp> 和  <version type="timestamp"> 是等价的 也是用来加乐观锁
		System.out.println(user.getUsername());
		
	}
}