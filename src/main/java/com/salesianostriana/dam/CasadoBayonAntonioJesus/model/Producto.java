package com.salesianostriana.dam.CasadoBayonAntonioJesus.model;

import com.salesianostriana.dam.CasadoBayonAntonioJesus.tipos.TipoProducto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Producto {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private String descripcion;

    private double precio;

    @Enumerated(EnumType.STRING)
    private TipoProducto tipoProducto;

    private int popularidad;
    private String url;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey (name="fk_producto_carta"))
    private Carta carta;




}
