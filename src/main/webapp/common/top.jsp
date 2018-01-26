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
	<table>
		<tbody>
			<tr>
				<td colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td>
						<logic:present name="userinfo" scope="session">
							欢迎你: ${userinfo.username} 
						<input type="button" value="注销" onclick="location.href='${pageContext.request.contextPath}/logout.do'">
						</logic:present>
						<logic:notPresent name="userinfo" scope="session">
						<html:form action="/login.do"> 
					 		会员号: <html:text property="userid"></html:text>
							密码: <html:password property="userpassword"></html:password>
							<html:submit value="登录"></html:submit>
						</html:form>
						</logic:notPresent>
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>