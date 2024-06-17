package com.example.demo_wcd_university.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/students")
public class StudentController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/student.jsp").forward(req,resp);

    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy dữ liệu từ biểu mẫu
        String email = req.getParameter("email");
        String message = req.getParameter("message");




        // In ra dữ liệu bằng System.out.println
        System.out.println("Email: " + email);
        System.out.println("Message: " + message);
        // Chuyển tiếp đến trang JSP để hiển thị
        req.getRequestDispatcher("/index.jsp").forward(req, resp);    }
}
