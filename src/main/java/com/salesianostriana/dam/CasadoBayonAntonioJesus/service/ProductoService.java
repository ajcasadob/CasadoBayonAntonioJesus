package com.salesianostriana.dam.CasadoBayonAntonioJesus.service;

import com.salesianostriana.dam.CasadoBayonAntonioJesus.controller.ProductoController;
import com.salesianostriana.dam.CasadoBayonAntonioJesus.model.Producto;
import com.salesianostriana.dam.CasadoBayonAntonioJesus.repository.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {


    @Autowired
    private ProductoRepository productoRepository;

    //Buscar un producto
    public List<Producto> buscarPorNombre (String nombre){

        return productoRepository.findByNombreContainingIgnoreCase(nombre).stream().toList();
    }

    //GET ALL
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }
    //Eliminar un producto
    public void deleteProduct(String nombre){
    List<Producto> productoEliminar = productoRepository.findByNombreContainingIgnoreCase(nombre);
        if(!productoEliminar.isEmpty()){
            productoRepository.deleteAll(productoEliminar);
        }
    }
    //Buscar un producto por id
    public Producto findById(Long id) throws EntityNotFoundException {
        return productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + id));
    }

    //Guardar un producto
    public void savedProduct (Producto producto){

        productoRepository.save(producto);
    }


    //editar producto
    public void editProduct(Producto producto) {
        Producto productoExistente = findById(producto.getId());
        productoExistente.setNombre(producto.getNombre());
        productoExistente.setPrecio(producto.getPrecio());
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setTipoProducto(producto.getTipoProducto());
        productoExistente.setPopularidad(producto.getPopularidad());
        productoExistente.setUrl(producto.getUrl());
        productoExistente.setFecha(producto.getFecha());
        productoRepository.save(productoExistente);
    }


}
