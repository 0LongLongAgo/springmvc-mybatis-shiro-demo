<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="GB2312"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>This is a servlet</title>
</head>
<body>
	<br>����login_successҳ! <br><br>
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

	<shiro:user>
		��ӭ[<shiro:principal/>]��¼��<a href="${pageContext.request.contextPath}/WEB-INF/jsp/login.jsp">�˳�</a>
	</shiro:user>
	<shiro:authenticated>
		�û�[<shiro:principal/>]�������֤ͨ��
	</shiro:authenticated>
	<shiro:notAuthenticated>
		δ�����֤��������ס�ң�
	</shiro:notAuthenticated>

	<shiro:principal type="java.lang.String"/>
	<%--<shiro:principal property="username"/>--%>
	<shiro:hasRole name="admin">
		�û�[<shiro:principal/>]ӵ�н�ɫadmin<br/>
	</shiro:hasRole>
	<shiro:lacksRole name="abc">
		�û�[<shiro:principal/>]û�н�ɫabc<br/>
	</shiro:lacksRole>
	<shiro:hasPermission name="user:create">
		�û�[<shiro:principal/>]ӵ��Ȩ��user:create<br/>
	</shiro:hasPermission>
	<shiro:lacksPermission name="org:create">
		�û�[<shiro:principal/>]û��Ȩ��org:create<br/>
	</shiro:lacksPermission>
</body>
</html>