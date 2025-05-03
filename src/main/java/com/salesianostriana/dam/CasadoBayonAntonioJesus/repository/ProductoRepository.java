package com.salesianostriana.dam.CasadoBayonAntonioJesus.repository;

import com.salesianostriana.dam.CasadoBayonAntonioJesus.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository  extends JpaRepository <Producto, Long> {


}
