<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="GB2312"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>This is a servlet</title>
</head>
<body>
	<br>����unauthorizedҳ! <br><br>
	 <%-- <%String data = request.getAttribute("hello").toString();
	response.getWriter().write(data);%> ${hello}  --%>
	<%--<%String path =  request.getContextPath();--%>
	<%--String path1 = path+"/WEB-INF/jsp/login.jsp";%>--%>
	<%-- <a href="<%=path1%>">��¼</a> --%>
	<%-- <a href="${pageContext.request.contextPath }/WEB-INF/jsp/login.jsp">���Ա��ͨѶ��Ϣ</a> --%>
	<%-- <a href="<%=path1%>" target="_blank">���Ա��ͨѶ��Ϣ</a> --%>
<%-- 	<input type="button" name="btnAdd" onclick="window.location.href=<%=path1%>" id="btnAdd" value="���Ա��ͨѶ��Ϣ" />
 --%><%-- 	<jsp:forward page="<%=path1%>">
   <jsp:param name="123" value="123"/> 
</jsp:forward> --%>
<!-- 2:��response.sendRedirect("http://www.sun.com"����"*.jsp"); -->
	<%--<form action="/FirstProject/login" method="post">--%>
	<%----%>
	<%--<input type="submit" value="�ύ"/>--%>
<%--</form>--%>
</body>
</html>