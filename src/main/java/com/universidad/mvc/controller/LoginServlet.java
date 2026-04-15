package com.universidad.mvc.controller;

import java.io.IOException;
import java.util.Map;

import com.universidad.mvc.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final Map<String, String> CREDS = Map.of(
            "admin", "Admin123!",
            "viewer", "View456!"
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/views/login.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String user = req.getParameter("username");
        String pass = req.getParameter("password");

        if (user != null && CREDS.containsKey(user)
                && CREDS.get(user).equals(pass)) {

            HttpSession session = req.getSession(true);

            String rol = "admin".equals(user) ? "ADMIN" : "VIEWER";

            Usuario usuario = new Usuario(
                    user,
                    user + "@universidad.edu",
                    rol
            );

            session.setAttribute("usuarioActual", usuario);
            session.setMaxInactiveInterval(1800);

            resp.sendRedirect(req.getContextPath() + "/productos");

        } else {
            req.setAttribute("errorLogin", "Credenciales incorrectas");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp")
                    .forward(req, resp);
        }
    }
}