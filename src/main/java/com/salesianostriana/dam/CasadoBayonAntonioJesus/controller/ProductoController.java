package com.salesianostriana.dam.CasadoBayonAntonioJesus.controller;


import com.salesianostriana.dam.CasadoBayonAntonioJesus.model.Carta;
import com.salesianostriana.dam.CasadoBayonAntonioJesus.model.Producto;
import com.salesianostriana.dam.CasadoBayonAntonioJesus.service.CartaService;
import com.salesianostriana.dam.CasadoBayonAntonioJesus.service.ProductoService;
import com.salesianostriana.dam.CasadoBayonAntonioJesus.tipos.TipoProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductoController {

    @Autowired
    private  ProductoService productoService;






    @GetMapping("/")
    public String inicio(){

        return "web";
    }


    @GetMapping("/menu")
    public String menu (){

        return "web";
    }

    @GetMapping("/productos/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        return "web";
    }

    @PostMapping("/productos/nuevo")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.savedProduct(producto);
        return "redirect:/productos";
    }
    @GetMapping("/productos")
    public String listarProductos(Model model){
        List<Producto> productos = productoService.obtenerTodos();
        model.addAttribute("productos",productos);
        return "web";
    }

    @GetMapping("/buscar")
    public String buscarProductos (String nombre, Model model){
        List<Producto> productos = productoService.buscarPorNombre(nombre);
        model.addAttribute("productos",productos);
        model.addAttribute("terminoBusqueda",nombre);
        return "web";
    }


}
