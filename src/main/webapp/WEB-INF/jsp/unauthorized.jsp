<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="GB2312"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>This is a servlet</title>
</head>
<body>
	<br>这是unauthorized页! <br><br>
	 <%-- <%String data = request.getAttribute("hello").toString();
	response.getWriter().write(data);%> ${hello}  --%>
	<%--<%String path =  request.getContextPath();--%>
	<%--String path1 = path+"/WEB-INF/jsp/login.jsp";%>--%>
	<%-- <a href="<%=path1%>">登录</a> --%>
	<%-- <a href="${pageContext.request.contextPath }/WEB-INF/jsp/login.jsp">添加员工通讯信息</a> --%>
	<%-- <a href="<%=path1%>" target="_blank">添加员工通讯信息</a> --%>
<%-- 	<input type="button" name="btnAdd" onclick="window.location.href=<%=path1%>" id="btnAdd" value="添加员工通讯信息" />
 --%><%-- 	<jsp:forward page="<%=path1%>">
   <jsp:param name="123" value="123"/> 
</jsp:forward> --%>
<!-- 2:用response.sendRedirect("http://www.sun.com"或者"*.jsp"); -->
	<%--<form action="/FirstProject/login" method="post">--%>
	<%----%>
	<%--<input type="submit" value="提交"/>--%>
<%--</form>--%>
</body>
</html>