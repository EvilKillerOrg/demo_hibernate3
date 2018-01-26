package com.ek.hibernate.model.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.ek.hibernate.model.pojo.Userinfo;
import com.ek.hibernate.model.util.HibernateSessionFactory;

public class UserInfoDAO {
	
	public void addUserinfo(Userinfo user){
		Session session = HibernateSessionFactory.getSession();
		session.save(user);
	}
	
	public void updateUserinfo(Userinfo user){
		Session session = HibernateSessionFactory.getSession();
		session.merge(user);
	}
	
	public void deleteUserinfo(String uuid){
		Session session = HibernateSessionFactory.getSession();
		Userinfo user = (Userinfo)session.get(Userinfo.class, uuid);
		if(user!=null){
			session.delete(user);
		}
	}
	
	public Userinfo getUserinfoById(String uuid){
		Session session = HibernateSessionFactory.getSession();
		return (Userinfo)session.get(Userinfo.class, uuid);
	}
	
	@SuppressWarnings("unchecked")
	public List<Userinfo> findUserinfo(){
		Session session = HibernateSessionFactory.getSession();
		Criteria criteria = session.createCriteria(Userinfo.class);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Userinfo> findUserinfo(Map<String , Object> searchMap){
		Session session = HibernateSessionFactory.getSession();
		Criteria criteria = session.createCriteria(Userinfo.class);
		criteria.add(Restrictions.allEq(searchMap));
		return criteria.list();
	}
}
