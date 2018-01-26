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
	<html:form action="/userinfo.do?method=saveUser" method="post">
		 <table border="0">
        <tr>
          <td>会员号:</td>
          <td><html:text property="userid" /></td>
          <td colspan="2"><input type="button" name="button1" value="检查该会员号是否已存在" > </td>
        </tr>
        <tr>
          <td>用户姓名:</td>
          <td><html:text property="username" /></td>
           <td>用户密码:</td>
          <td><html:password property="userpassword" /></td>
        </tr>
        <tr>
           <td>性别:</td>
          <td> <html:select property="sex">
          		<html:option value="0">男</html:option>
          		<html:option value="1">女</html:option>
                </html:select> </td>
           <td>年龄:</td>
          <td><html:text property="age" /></td>
        </tr>
        <tr>
          <td>邮编:</td>
          <td><html:text property="postalcode" /></td>
          <td>学历:</td>
          <td> <html:text property="schoolage" /></td>
        </tr>
               <tr>
          <td>地址:</td>
          <td colspan="3"><html:textarea property="address" rows="3" cols="40"></html:textarea></td>
        </tr>
                <tr>
          <td>个人爱好:</td>
          <td colspan="3"><html:textarea property="personlike" rows="3" cols="40"></html:textarea></td>  
        </tr>
        <tr>
          <td colspan="2" align="center"><html:submit value="确定" /></td>
          <td colspan="2"><input type="button" value="返回" onclick="history.go(-1);"> </td>
        </tr>
      </table>
	</html:form>
</body>
</html>