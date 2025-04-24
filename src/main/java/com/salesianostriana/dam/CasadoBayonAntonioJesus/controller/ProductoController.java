package com.salesianostriana.dam.CasadoBayonAntonioJesus.controller;


import com.salesianostriana.dam.CasadoBayonAntonioJesus.model.Producto;
import com.salesianostriana.dam.CasadoBayonAntonioJesus.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductoController {

    private ProductoService productoService;


    @GetMapping("/formulario")
    public String  verFormulario(Model model){

        Producto producto = new Producto();
        model.addAttribute("productoForm",producto);

        return "index";
    }

    @PostMapping("/agregarProducto")
    public String enviar (@ModelAttribute("productoForm") Producto producto, Model model){

        model.addAttribute("formulario",producto);

        return "view";
    }
}
