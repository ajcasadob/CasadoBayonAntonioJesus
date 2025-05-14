package com.salesianostriana.dam.CasadoBayonAntonioJesus.service;

import com.salesianostriana.dam.CasadoBayonAntonioJesus.controller.ProductoController;
import com.salesianostriana.dam.CasadoBayonAntonioJesus.model.Producto;
import com.salesianostriana.dam.CasadoBayonAntonioJesus.repository.ProductoRepository;
import com.salesianostriana.dam.CasadoBayonAntonioJesus.tipos.TipoProducto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.*;
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
    public Producto findById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + id));
    }

    //Guardar un producto
    public void savedProduct (Producto producto){

        productoRepository.save(producto);

    }
    //Obtener los primeros cuatro productos
    public List<Producto> obtenerPrimerosCuatroProductos() {
        return productoRepository.findAll().stream().limit(4).toList();
    }

    //editar producto
    public void editProduct(Producto producto) {
        Producto productoExistente = findById(producto.getId());

        productoRepository.save(productoExistente);
    }

    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }
    // Recortar la descripción de los productos a 50 caracteres
    public List<Producto> obtenerTodosDescripcionReducida() {
        return productoRepository.findAll().stream()
                .map(p -> {
                    p.setDescripcion(p.getDescripcion().length() > 50 ? p.getDescripcion().substring(0, 50) + "..." : p.getDescripcion());
                    return p;
                })
                .collect(Collectors.toList());
    }
    //Obtener los productos con la popularidad mas alta y mostar los 6
    public List<Producto> obtenerMejorValorados(){

        return productoRepository.obtenerMejorValorados().stream()
                .limit(6)
                .toList();

    }




    //Obtener los dos últimos productos
    public List<Producto> obtenerUltimosDosProductos() {
        return productoRepository.findTop2ByOrderByFechaDesc();
    }

   //Obtener los productos con popularidad menor a 5 y hacer un descuento
    public List<Producto> obtenerProductosConDescuento() {
        return productoRepository.findByPopularidadMenor().stream()
                .map(p -> {
                    p.setPrecio(p.getPrecio() * 0.9);
                    productoRepository.save(p);
                    return p;
                })
                .collect(Collectors.toList());
    }


}


