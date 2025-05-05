package com.salesianostriana.dam.CasadoBayonAntonioJesus.controller;


import com.salesianostriana.dam.CasadoBayonAntonioJesus.model.Carta;
import com.salesianostriana.dam.CasadoBayonAntonioJesus.model.Producto;
import com.salesianostriana.dam.CasadoBayonAntonioJesus.service.CartaService;
import com.salesianostriana.dam.CasadoBayonAntonioJesus.service.ProductoService;
import com.salesianostriana.dam.CasadoBayonAntonioJesus.tipos.TipoProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductoController {

    @Autowired
    private  ProductoService productoService;






    @GetMapping("/")
    public String inicio(Model model){
        model.addAttribute("productos",productoService.obtenerTodos());

        return "web";
    }


    @GetMapping("/menu")
    public String menu (Model model){
        model.addAttribute("productos",productoService.obtenerTodos());

        return "web";
    }

    @GetMapping("/productos/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("producto", new Producto());

        return "formularioProducto";
    }

    @PostMapping("/productos/nuevo")
    public String guardarProducto( @ModelAttribute Producto producto, BindingResult result, Model model) {

        if(result.hasErrors()){
            model.addAttribute("productos",productoService.obtenerTodos());
            return "formularioProducto";
        }

        productoService.savedProduct(producto);


        return "redirect:/productos";
    }

    @GetMapping("/productos/editar/{idProducto}")
    public String mostrarFormularioConProductoCargado(Model model , @PathVariable Long idProducto) {

        model.addAttribute("producto", productoService.findById(idProducto));

        return "formularioProducto";
    }

    @PostMapping("/productos/editar/{idProducto}")
    public String editarProducto( @ModelAttribute Producto producto, BindingResult result, Model model) {

        if(result.hasErrors()){
            model.addAttribute("productos",productoService.obtenerTodos());
            return "formularioProducto";
        }

        productoService.editProduct(producto);

        return "redirect:/productos";
    }

    @GetMapping("/productos")
    public String listarProductos(Model model){
        model.addAttribute("productos",productoService.obtenerTodos());
        return "web";
    }

    @GetMapping("/buscar")
    public String buscarProductos (String nombre, Model model){
        List<Producto> productos = productoService.buscarPorNombre(nombre);
        model.addAttribute("productos",productos);
        model.addAttribute("terminoBusqueda",nombre);
        model.addAttribute("producto", new Producto());
        return "busqueda";
    }

}
