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
        List<Producto> productosGaleria = productoService.obtenerTodos();

        model.addAttribute("sobreNosostros", productoService.obtenerPrimerosCuatroProductos());
        model.addAttribute("productosGaleria", productosGaleria);

        model.addAttribute("productos1",productoService.obtenerMejorValorados());
        model.addAttribute("productosNovedades", productoService.obtenerUltimosDosProductos());

        return "web";
    }



    @GetMapping("/menu2")
    public String menu(@RequestParam(value = "orden", required = false, defaultValue = "todos") String orden, Model model) {
        switch (orden) {
            case "precioAsc":
                model.addAttribute("productos", productoService.obtenerProductosOrdenadosPorPrecioAsc());
                break;
            case "precioDesc":
                model.addAttribute("productos", productoService.obtenerProductosOrdenadosPorPrecioDesc());
                break;
            case "nombreAsc":
                model.addAttribute("productos", productoService.obtenerProductosOrdenadosPorNombreAsc());
                break;
            default:
                model.addAttribute("productos", productoService.obtenerTodos());
                break;
        }
        return "menu2";
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

        return "redirect:/";
    }



    @GetMapping("/buscar")
    public String buscarProductos (String nombre, Model model){
        List<Producto> productos = productoService.buscarPorNombre(nombre);
        model.addAttribute("productos",productos);
        model.addAttribute("terminoBusqueda",nombre);
        model.addAttribute("producto", new Producto());
        return "busqueda";
    }

    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("productos",productoService.obtenerTodosDescripcionReducida());
        return "admin";
    }

    @GetMapping("/productos/borrar/{id}")
    public String borrarProducto(@PathVariable Long id) {
        productoService.deleteById(id);
        return "redirect:/admin";
    }
    @GetMapping("/productos/descuento")
    public String mostrarProductosConDescuento(Model model) {
        List<Producto> productosConDescuento = productoService.obtenerProductosConDescuento();
        model.addAttribute("productos", productosConDescuento);
        return "productosConDescuento";
    }



}
