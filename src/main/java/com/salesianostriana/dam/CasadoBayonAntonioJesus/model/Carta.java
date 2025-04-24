package com.salesianostriana.dam.CasadoBayonAntonioJesus.model;

import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Carta {

    @Id
    @GeneratedValue
    private Long idCarta;

    @OneToMany(mappedBy = "carta",fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private List<Producto> listaProducto = new ArrayList<Producto>();


}
