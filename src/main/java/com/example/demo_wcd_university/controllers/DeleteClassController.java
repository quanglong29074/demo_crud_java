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

@WebServlet(name = "deleteClass", value = "/deleteClass")
public class DeleteClassController extends HttpServlet {
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

        entityManager.getTransaction().begin();
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DeleteClassRoom");
        storedProcedure.registerStoredProcedureParameter("classId", Integer.class, jakarta.persistence.ParameterMode.IN);
        storedProcedure.setParameter("classId", classId);

        storedProcedure.execute();

        // Commit giao dịch để lưu các thay đổi vào cơ sở dữ liệu
        entityManager.getTransaction().commit();
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
