package com.salesianostriana.dam.CasadoBayonAntonioJesus.service;

import com.salesianostriana.dam.CasadoBayonAntonioJesus.controller.ProductoController;
import com.salesianostriana.dam.CasadoBayonAntonioJesus.model.Producto;
import com.salesianostriana.dam.CasadoBayonAntonioJesus.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {


    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> buscarPorNombre (String nombre){

        return productoRepository.findAll().stream().filter(p -> p.getNombre().equalsIgnoreCase(nombre)).toList();
    }

    public List<Producto> getAll() {
        return productoRepository.findAllWithCarta(); // Usa la consulta con JOIN FETCH
    }

    public void deleteProduct(String nombre){
    List<Producto> productoEliminar = buscarPorNombre(nombre);
        productoRepository.deleteAll(productoEliminar);
    }

    public Producto findById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    public void savedProduct (Producto producto){

        productoRepository.save(producto);
    }



}
