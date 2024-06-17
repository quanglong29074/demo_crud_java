package com.example.demo_wcd_university.controllers;

import com.example.demo_wcd_university.entity.ClassRoom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.persistence.StoredProcedureQuery;

import java.io.IOException;

@WebServlet(name = "editClass", value = "/editClass")
public class EditClassController extends HttpServlet {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Override
    public void init() throws ServletException {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy id của lớp học từ request
        int classId = Integer.parseInt(req.getParameter("id"));

        // Tìm đối tượng ClassRoom theo id
        ClassRoom classRoom = entityManager.find(ClassRoom.class, classId);

        // Đặt đối tượng ClassRoom vào request attribute
        req.setAttribute("classRoom", classRoom);

        // Chuyển hướng đến trang JSP để chỉnh sửa thông tin lớp học
        req.getRequestDispatcher("/jsp/editClass.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy dữ liệu từ form
        int classId = Integer.parseInt(req.getParameter("id"));
        String className = req.getParameter("className");
        int numberMember = Integer.parseInt(req.getParameter("numberMember"));

        // Bắt đầu một giao dịch để gọi stored procedure
        entityManager.getTransaction().begin();

        // Tạo StoredProcedureQuery cho stored procedure UpdateClassRoom
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("UpdateClassRoom");
        // Đăng ký các tham số của stored procedure
        storedProcedure.registerStoredProcedureParameter("classId", Integer.class, jakarta.persistence.ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("className", String.class, jakarta.persistence.ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("numberMember", Integer.class, jakarta.persistence.ParameterMode.IN);

        // Thiết lập giá trị cho các tham số từ dữ liệu lấy được từ request
        storedProcedure.setParameter("classId", classId);
        storedProcedure.setParameter("className", className);
        storedProcedure.setParameter("numberMember", numberMember);

        // Thực thi stored procedure
        storedProcedure.execute();

        // Commit giao dịch để lưu các thay đổi vào cơ sở dữ liệu
        entityManager.getTransaction().commit();
        entityManager.clear();

        // Chuyển hướng người dùng về trang danh sách lớp học
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
