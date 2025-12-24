package com.example.demo.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.IOException;

public class SimpleStatusServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req,
                      HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/plain");
        resp.getWriter().write("Multi-Location Inventory Balancer is running");
    }
}
