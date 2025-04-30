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



    public List<Producto> getAll (){

        return productoRepository.findAll().stream().toList();
    }

    public void deleteProduct(Producto p){

        productoRepository.delete(p);
    }

    public Producto findById(int  id){

        return null ;
    }

}
