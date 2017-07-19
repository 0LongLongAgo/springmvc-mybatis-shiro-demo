<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

  <form action="/user/before_login" method="get">

    <input type="submit" value="登录"/>
  </form>

  <form action="/user/to_register" method="post">

    <input type="submit" value="注册"/>
  </form>
  <form action="/role/testAuthc" method="post">

    <input type="submit" value="测试authc"/>
  </form>

  <shiro:guest>
    欢迎游客访问，<a href="${pageContext.request.contextPath}/jsp/login.jsp">登录</a>
  </shiro:guest>

    <%=pageContext.getServletContext().getContextPath()%>
    ${pageContext.request.contextPath}+"/jsp/login.jsp"

  <shiro:user>
    欢迎[<shiro:principal/>]登录，<a href="${pageContext.request.contextPath}/login.jsp">退出</a>
  </shiro:user>
  <shiro:authenticated>
    用户[<shiro:principal/>]已身份验证通过
  </shiro:authenticated>
  <shiro:notAuthenticated>
    未身份验证（包括记住我）
  </shiro:notAuthenticated>

  <%--<shiro:principal type="java.lang.String"/>--%>
  <%--<shiro:principal property="username"/>--%>
  <shiro:hasRole name="admin">
    用户[<shiro:principal/>]拥有角色admin<br/>
  </shiro:hasRole>
  <shiro:lacksRole name="abc">
    用户[<shiro:principal/>]没有角色abc<br/>
  </shiro:lacksRole>
  <shiro:hasPermission name="user:create">
    用户[<shiro:principal/>]拥有权限user:create<br/>
  </shiro:hasPermission>
  <shiro:lacksPermission name="org:create">
    用户[<shiro:principal/>]没有权限org:create<br/>
  </shiro:lacksPermission>
  </body>
</html>
