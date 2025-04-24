package com.salesianostriana.dam.CasadoBayonAntonioJesus.repository;

import com.salesianostriana.dam.CasadoBayonAntonioJesus.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository  extends JpaRepository <Producto, Long> {
}
