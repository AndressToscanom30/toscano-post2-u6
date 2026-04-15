package com.universidad.mvc.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.universidad.mvc.model.Producto;
import com.universidad.mvc.service.ProductoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {

    private final ProductoService service = new ProductoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (!verificarSesion(req, resp)) return;

        String accion = req.getParameter("accion");

        if (accion == null) {
            listar(req, resp);
        } else {
            switch (accion) {
                case "nuevo":
                    forward(req, resp, "/WEB-INF/views/formulario.jsp");
                    break;
                case "editar":
                    editar(req, resp);
                    break;
                case "eliminar":
                    eliminar(req, resp);
                    break;
                default:
                    listar(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (!verificarSesion(req, resp)) return;

        String accion = req.getParameter("accion");

        if ("actualizar".equals(accion)) {
            actualizar(req, resp);
        } else {
            guardar(req, resp);
        }
    }

    private boolean verificarSesion(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        HttpSession s = req.getSession(false);

        if (s == null || s.getAttribute("usuarioActual") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return false;
        }
        return true;
    }

    private void listar(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("lista", service.listar());
        forward(req, resp, "/WEB-INF/views/lista.jsp");
    }

    private void editar(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("producto", service.buscar(id));

        forward(req, resp, "/WEB-INF/views/formulario.jsp");
    }

    private void eliminar(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        service.eliminar(id);

        resp.sendRedirect(req.getContextPath() + "/productos");
    }

    private void guardar(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String nombre = req.getParameter("nombre");
        String precioStr = req.getParameter("precio");
        String stockStr = req.getParameter("stock");
        String categoria = req.getParameter("categoria");

        Map<String, String> errores = new LinkedHashMap<>();

        if (nombre == null || nombre.trim().isEmpty()) {
            errores.put("nombre", "El nombre es obligatorio");
        }

        double precio = 0;
        try {
            precio = Double.parseDouble(precioStr);
            if (precio < 0) errores.put("precio", "Precio negativo");
        } catch (Exception e) {
            errores.put("precio", "Precio inválido");
        }

        int stock = 0;
        try {
            stock = Integer.parseInt(stockStr);
            if (stock < 0) errores.put("stock", "Stock negativo");
        } catch (Exception e) {
            errores.put("stock", "Stock inválido");
        }

        if (!errores.isEmpty()) {
            req.setAttribute("errores", errores);
            req.setAttribute("nombre", nombre);
            req.setAttribute("precio", precioStr);
            req.setAttribute("stock", stockStr);
            req.setAttribute("categoria", categoria);

            forward(req, resp, "/WEB-INF/views/formulario.jsp");
            return;
        }

        service.guardar(new Producto(0, nombre, categoria, precio, stock));

        resp.sendRedirect(req.getContextPath() + "/productos");
    }

    private void forward(HttpServletRequest req, HttpServletResponse resp, String vista)
            throws ServletException, IOException {

        req.getRequestDispatcher(vista).forward(req, resp);
    }

    private void actualizar(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(req.getParameter("id"));
        String nombre = req.getParameter("nombre");
        String precioStr = req.getParameter("precio");
        String stockStr = req.getParameter("stock");
        String categoria = req.getParameter("categoria");

        Map<String, String> errores = new LinkedHashMap<>();

        if (nombre == null || nombre.trim().isEmpty()) {
            errores.put("nombre", "El nombre es obligatorio");
        }

        double precio = 0;
        try {
            precio = Double.parseDouble(precioStr);
            if (precio < 0) errores.put("precio", "Precio negativo");
        } catch (Exception e) {
            errores.put("precio", "Precio inválido");
        }

        int stock = 0;
        try {
            stock = Integer.parseInt(stockStr);
            if (stock < 0) errores.put("stock", "Stock negativo");
        } catch (Exception e) {
            errores.put("stock", "Stock inválido");
        }

        if (!errores.isEmpty()) {
            req.setAttribute("errores", errores);
            req.setAttribute("producto", new Producto(id, nombre, categoria, precio, stock));

            forward(req, resp, "/WEB-INF/views/formulario.jsp");
            return;
        }

        service.actualizar(new Producto(id, nombre, categoria, precio, stock));

        resp.sendRedirect(req.getContextPath() + "/productos");
    }

}