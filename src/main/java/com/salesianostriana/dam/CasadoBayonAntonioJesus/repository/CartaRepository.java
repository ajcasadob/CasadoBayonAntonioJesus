package com.salesianostriana.dam.CasadoBayonAntonioJesus.repository;

import com.salesianostriana.dam.CasadoBayonAntonioJesus.model.Carta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaRepository extends JpaRepository<Carta, Long> {




}
