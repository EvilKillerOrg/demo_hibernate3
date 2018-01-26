package com.ek.hibernate.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.ek.hibernate.model.pojo.Userinfo;
import com.ek.hibernate.model.service.BookShopService;
import com.ek.hibernate.model.util.DozerUtil;

public class UserInfoAction extends DispatchAction {

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return super.unspecified(mapping, form, request, response);
	}

	
	
	public ActionForward register(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.getInputForward();
	}
	
	
	
	public ActionForward saveUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		BookShopService bss = new BookShopService();
		Userinfo user = DozerUtil.getMapper().map(form, Userinfo.class);  //(source , destination)
		bss.register(user);
		
		request.getSession().setAttribute("userinfo", user);
		
		return mapping.findForward("indexpage");
	}
}