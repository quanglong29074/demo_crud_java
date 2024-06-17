package com.example.demo_wcd_university.controllers;

import com.example.demo_wcd_university.entity.ClassRoom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "class", value = "/class")
public class ClassController extends HttpServlet {
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    @Override
    public void init() throws ServletException {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

var classRooms = entityManager.createStoredProcedureQuery("GetClassRoom", ClassRoom.class).getResultList();
        // Truy vấn danh sách lớp học

        // Đặt danh sách lớp học vào request attribute
        req.setAttribute("classRooms", classRooms);

        // Chuyển hướng đến trang JSP
        req.getRequestDispatcher("/jsp/class.jsp").forward(req, resp);
    }


    // Trong phương thức doPost của ClassController
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String className = req.getParameter("className");
        int numberMember = Integer.parseInt(req.getParameter("numberMember"));

        // Bắt đầu một giao dịch để gọi stored procedure
        entityManager.getTransaction().begin();

        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("AddClassRoom");
        storedProcedure.registerStoredProcedureParameter("className", String.class, jakarta.persistence.ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("numberMember", Integer.class, jakarta.persistence.ParameterMode.IN);

        storedProcedure.setParameter("className", className);
        storedProcedure.setParameter("numberMember", numberMember);

        storedProcedure.execute();

        entityManager.getTransaction().commit();

        // Sau khi thêm thành công, chuyển hướng người dùng đến trang hiển thị danh sách lớp học
        resp.sendRedirect("class");
    }





    @Override
    public void destroy() {
        if (entityManager != null) {
            entityManager.close();
        }
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

}
