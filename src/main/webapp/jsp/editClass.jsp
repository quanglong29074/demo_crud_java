<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.demo_wcd_university.entity.ClassRoom" %>
<head>
    <%@ include file="/include/head.jsp"%>
</head>
<body>
<%@ include file="/include/navbar.jsp"%>

<%-- Lấy đối tượng ClassRoom từ request attribute --%>
<%
    ClassRoom classRoom = (ClassRoom) request.getAttribute("classRoom");
%>

<form action="editClass" method="post">
    <input type="hidden" name="id" value="<%= classRoom.getIdClass() %>">
    <input type="text" name="className" placeholder="Class name" value="<%= classRoom.getClassName() %>">
    <input type="number" name="numberMember" placeholder="Number of members" value="<%= classRoom.getNumberMember() %>">
    <button type="submit">Update Class</button>
</form>

<%@ include file="/include/footer.jsp"%>
</body>
</html>
