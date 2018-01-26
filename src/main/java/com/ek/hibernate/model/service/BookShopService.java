package com.ek.hibernate.model.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ek.hibernate.model.dao.UserInfoDAO;
import com.ek.hibernate.model.pojo.Userinfo;
import com.ek.hibernate.model.util.UserType;

public class BookShopService {
	
	private UserInfoDAO userdao =new UserInfoDAO();
	
	public Userinfo login(Userinfo user){
		Map<String , Object> searchMap = new HashMap<String , Object>();
		searchMap.put("userid", user.getUserid());
		searchMap.put("userpassword", user.getUserpassword());
		List<Userinfo> userList = userdao.findUserinfo(searchMap);
		if(userList==null||userList.size()==0){
			return null;
		}
		return userList.get(0);
	}
	
	
	public void register(Userinfo user){
		user.setRegisterdate(new Date());
		user.setUsertype(UserType.GENERAL.toString());
		userdao.addUserinfo(user);
	}
	
}
