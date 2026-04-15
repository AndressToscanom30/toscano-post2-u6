package com.universidad.mvc.model;

import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private static final List<Producto> lista = new ArrayList<>();
    private static int contador = 1;

    public List<Producto> listar() {
        return lista;
    }

    public Producto buscar(int id) {
        return lista.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void guardar(Producto p) {
        p.setId(contador++);
        lista.add(p);
    }

    public void actualizar(Producto p) {
        Producto existente = buscar(p.getId());
        if (existente != null) {
            existente.setNombre(p.getNombre());
            existente.setCategoria(p.getCategoria());
            existente.setPrecio(p.getPrecio());
            existente.setStock(p.getStock());
        }
    }

    public void eliminar(int id) {
        lista.removeIf(p -> p.getId() == id);
    }
}