package com.salesianostriana.dam.CasadoBayonAntonioJesus.service;

import com.salesianostriana.dam.CasadoBayonAntonioJesus.controller.ProductoController;
import com.salesianostriana.dam.CasadoBayonAntonioJesus.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {


    @Autowired
    private ProductoRepository productoRepository;



}
