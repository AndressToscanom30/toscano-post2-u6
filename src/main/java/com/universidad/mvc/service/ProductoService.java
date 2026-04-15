package com.universidad.mvc.service;

import com.universidad.mvc.model.ProductoDAO;
import com.universidad.mvc.model.Producto;

import java.util.List;

public class ProductoService {

    private final ProductoDAO dao = new ProductoDAO();

    public List<Producto> listar() {
        return dao.listar();
    }

    public Producto buscar(int id) {
        return dao.buscar(id);
    }

    public void guardar(Producto p) {
        dao.guardar(p);
    }

    public void actualizar(Producto p) {
        dao.actualizar(p);
    }

    public void eliminar(int id) {
        dao.eliminar(id);
    }
}