package com.salesianostriana.dam.CasadoBayonAntonioJesus.model;

import com.salesianostriana.dam.CasadoBayonAntonioJesus.tipos.TipoProducto;
import jakarta.persistence.*;
import lombok.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static org.apache.catalina.security.SecurityUtil.remove;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Producto {


    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;

    private String nombre;


    private String descripcion;



    private double precio;
    private double precioOriginal;

    @Enumerated(EnumType.STRING)
    private TipoProducto tipoProducto;

    private int popularidad;
    private String url;

    @Lob
    private String txtAlternativo;




    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(optional = true)
    @JoinColumn(name = "id_carta",referencedColumnName = "idCarta", foreignKey = @ForeignKey (name="fk_producto_carta"))
    private Carta carta;

    //METODOS HELPER

    public void addToCarta(Carta carta) {
        this.carta = carta;
        carta.getListaProducto().add(this);
    }

    public void removeFromCarta(Carta carta) {
        carta.getListaProducto().remove(this);
        this.carta = null;
    }


}
