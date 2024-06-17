<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/06/2024
  Time: 2:39 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/include/head.jsp"%>

</head>
<body>
<h1>Student Page</h1>
<header>
    <%@ include file="/include/navbar.jsp"%>
</header>
<form action="students" method="post">

<div class="mb-3">
    <label for="exampleFormControlInput1" class="form-label">Email address</label>
    <input name="email" type="email" class="form-control" id="exampleFormControlInput1" placeholder="name@example.com">
</div>
<div class="mb-3">
    <label for="exampleFormControlTextarea1" class="form-label">Example textarea</label>
    <textarea name="message" class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
</div>
    <button type="submit">Submit</button>

</form>
<%@ include file="/include/footer.jsp"%>

</body>
</html>
