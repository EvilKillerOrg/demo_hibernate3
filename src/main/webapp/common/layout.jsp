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
	<table align="center" border="1" width="100%" height="100%">
		<tbody>
		<tr height="20%">
			<td colspan="2">
				<tiles:insert attribute="top" />
			</td>
		</tr>
		<tr height="70%">
			<td width="25%">
				<tiles:insert attribute="left" />
			</td>
			<td width="75%">
				<tiles:insert attribute="main" />
			</td>
		</tr>
		<tr height="10%">
			<td colspan="2">&nbsp;
				<tiles:insert attribute="footer" />
			</td>
		</tr>
		</tbody>
	</table>
</body>
</html>