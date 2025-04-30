package com.salesianostriana.dam.CasadoBayonAntonioJesus.controller;

import com.salesianostriana.dam.CasadoBayonAntonioJesus.service.CartaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CartaController {
    @Autowired
    private final CartaService cartaService;

    public CartaController (CartaService cartaService){
        this.cartaService= cartaService;
    }
}
