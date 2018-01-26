package com.ek.hibernate.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.ek.hibernate.model.pojo.Userinfo;
import com.ek.hibernate.model.service.BookShopService;

public class LoginAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DynaActionForm daf = (DynaActionForm)form;
		BookShopService bss = new BookShopService();
		Userinfo user = new Userinfo();
		user.setUserid(daf.getString("userid"));
		user.setUserpassword(daf.getString("userpassword"));
		
		Userinfo loginUser = bss.login(user);
		if(loginUser!=null){
			request.getSession().setAttribute("userinfo", loginUser);
		}
		
		return mapping.findForward("indexpage");
	}
	
}