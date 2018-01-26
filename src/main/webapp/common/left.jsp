<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean"  prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html"  prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic"  prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-nested"  prefix="nested"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles"  prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<table align="center" >
	<tbody>
		<tr height="15%">
			<td>&nbsp;<a href="${pageContext.request.contextPath}/userinfo.do?method=register" >注册</a></td>
		</tr>
		<tr height="15%">
			<td>&nbsp;<a href="">书籍查询</a></td>
		</tr>
		<logic:present name="userinfo" scope="session">
			<logic:match value="ADMIN"  name="userinfo"  property="usertype"  scope="session">
		<tr height="15%">
			<td>&nbsp;<a href="">图书的添加</a></td>
		</tr>
			</logic:match>
		</logic:present>
		<tr height="15%">
			<td>&nbsp;<a href="">购书列表</a></td>
		</tr>
		<logic:present name="userinfo" scope="session">
			<logic:match value="ADMIN"  name="userinfo"  property="usertype"  scope="session">
		<tr height="15%">
			<td>&nbsp;<a href="">用户管理</a></td>
		</tr>
			</logic:match>
		</logic:present>
		<logic:present name="userinfo" scope="session">
			<logic:match value="GENERAL"  name="userinfo"  property="usertype"  scope="session">
		<tr height="15%">
			<td>&nbsp;<a href="">我的购物车</a></td>
		</tr>
			</logic:match>
		</logic:present>
	</tbody>
</table>
</body>
</html>