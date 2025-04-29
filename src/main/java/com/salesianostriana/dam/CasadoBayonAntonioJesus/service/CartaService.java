package com.salesianostriana.dam.CasadoBayonAntonioJesus.service;

import com.salesianostriana.dam.CasadoBayonAntonioJesus.repository.CartaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaService {



    @Autowired
    private CartaRepository cartaRepository;

}
