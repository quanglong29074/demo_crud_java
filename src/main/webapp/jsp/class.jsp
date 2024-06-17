<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo_wcd_university.entity.ClassRoom" %>
<head>
    <%@ include file="/include/head.jsp"%>
</head>
<body>
<%@ include file="/include/navbar.jsp"%>
<form action="class" method="post">
    <input type="text" name="className" placeholder="Class name">
    <input type="number" name="numberMember" placeholder="Number of members">
    <button type="submit">Add Class</button>
</form>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Class Id</th>
        <th scope="col">Class name</th>
        <th scope="col">Number member</th>
        <th scope="col">Actions</th>
    </tr>
    </thead>
    <tbody>
    <%-- Lấy danh sách ClassRoom từ request attribute --%>
    <% List<ClassRoom> classRooms = (List<ClassRoom>) request.getAttribute("classRooms"); %>
    <%-- Kiểm tra nếu danh sách không rỗng --%>
    <% if (classRooms != null && !classRooms.isEmpty()) { %>
    <%-- Sử dụng vòng lặp để duyệt qua từng ClassRoom và hiển thị thông tin --%>
    <% for (ClassRoom classRoom : classRooms) { %>
    <tr>
        <td><%= classRoom.getIdClass() %></td>
        <td><%= classRoom.getClassName() %></td>
        <td><%= classRoom.getNumberMember() %></td>
        <td>
            <a class="btn btn-primary" href="editClass?id=<%= classRoom.getIdClass() %>">Edit</a>
            <a class="btn btn-danger" href="deleteClass?id=<%= classRoom.getIdClass() %>" onclick="return confirm('Are you sure you want to delete this class?');">Delete</a>


        </td>

    </tr>
    <% } %>
    <% } else { %>
    <%-- Hiển thị thông báo nếu danh sách trống --%>
    <tr>
        <td colspan="4">No data available</td>
    </tr>
    <% } %>
    </tbody>
</table>
<%@ include file="/include/footer.jsp"%>
</body>
</html>
