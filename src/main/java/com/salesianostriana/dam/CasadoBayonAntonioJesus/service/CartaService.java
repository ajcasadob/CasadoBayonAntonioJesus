package com.salesianostriana.dam.CasadoBayonAntonioJesus.service;

import com.salesianostriana.dam.CasadoBayonAntonioJesus.model.Carta;
import com.salesianostriana.dam.CasadoBayonAntonioJesus.repository.CartaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaService {



    @Autowired
    private CartaRepository cartaRepository;


    public List<Carta> findAllCartas() {
        return cartaRepository.findAll();
    }

    public Carta findById(Long id)  throws  RuntimeException {
        return cartaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carta no encontrada con ID: " + id));
    }
    public Carta findCartaById(Long id) {
        return cartaRepository.findById(id).orElseThrow();
    }
}
