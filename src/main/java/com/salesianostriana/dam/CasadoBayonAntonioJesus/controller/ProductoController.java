package com.salesianostriana.dam.CasadoBayonAntonioJesus.controller;


import com.salesianostriana.dam.CasadoBayonAntonioJesus.model.Carta;
import com.salesianostriana.dam.CasadoBayonAntonioJesus.model.Producto;
import com.salesianostriana.dam.CasadoBayonAntonioJesus.service.CartaService;
import com.salesianostriana.dam.CasadoBayonAntonioJesus.service.ProductoService;
import com.salesianostriana.dam.CasadoBayonAntonioJesus.tipos.TipoProducto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
public class ProductoController {

    @Autowired
    private  ProductoService productoService;
    @Autowired
    private CartaService cartaService;


    @GetMapping("/")
    public String inicio(Model model) {
        List<Producto> productosGaleria = productoService.obtenerTodos();


        model.addAttribute("sobreNosostros", productoService.obtenerPrimerosCuatroProductos());
        model.addAttribute("productosGaleria", productosGaleria);

        model.addAttribute("productos1", productoService.obtenerMejorValorados());
        model.addAttribute("productosNovedades", productoService.obtenerUltimosDosProductos());

        return "web";
    }

    @GetMapping("/menu")
    public String menu(Model model) {
        model.addAttribute("productosValorados", productoService.obtenerMejorValorados());

        return "redirect:/";
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
            case "fechaDesc":
                model.addAttribute("productos", productoService.obtenerProductosOrdenadosPorFechaDesc());
                break;
            case "popularidadDesc":
                model.addAttribute("productos", productoService.obtenerProductosOrdenadosPorPopularidadDesc());
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
        model.addAttribute("cartas", cartaService.findAllCartas());


        return "formularioProducto";
    }

    @PostMapping("/productos/nuevo")
    public String guardarProducto(
            @ModelAttribute Producto producto,
            BindingResult result,
            @RequestParam("idCarta") Long idCarta,
            @RequestParam(name = "descuento", required = false) Double descuento,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("cartas", cartaService.findAllCartas());
            return "formularioProducto";
        }

        productoService.guardarOActualizarProducto(producto, idCarta, descuento);
        return "redirect:/productos/nuevo";
    }


    @GetMapping("/productos/editar/{idProducto}")
    public String mostrarFormularioConProductoCargado(Model model, @PathVariable Long idProducto) {
        model.addAttribute("producto", productoService.findById(idProducto));
        model.addAttribute("cartas", cartaService.findAllCartas());
        return "formularioProducto";
    }

    @PostMapping("/productos/editar/{idProducto}")
    public String editarProducto(
            @ModelAttribute Producto producto,
            BindingResult result,
            @RequestParam("idCarta") Long idCarta,
            @RequestParam(name = "descuento", required = false) Double descuento,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("cartas", cartaService.findAllCartas());
            return "formularioProducto";
        }

        productoService.guardarOActualizarProducto(producto, idCarta, descuento);
        return "redirect:/admin";
    }


    @GetMapping("/productos")
    public String listarProductos(Model model) {

        return "redirect:/";
    }


    @GetMapping("/buscar")
    public String buscarProductos(String nombre, Model model) {
        List<Producto> productos = productoService.buscarPorNombre(nombre);
        model.addAttribute("productos", productos);
        model.addAttribute("terminoBusqueda", nombre);
        model.addAttribute("producto", new Producto());
        return "busqueda";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("productos", productoService.obtenerTodosDescripcionReducida());
        return "admin";
    }

    @GetMapping("/productos/borrar/{id}")
    public String borrarProducto(@PathVariable Long id) {
        productoService.deleteById(id);
        return "redirect:/admin";
    }


    @GetMapping("/productos/descuento")
    public String verProductosConDescuento(Model model) {
        model.addAttribute("productos", productoService.obtenerProductosConDescuento());
        return "productosConDescuento";
    }

    @PostMapping("/descuento")
    public String aplicarDescuento(
            @RequestParam Long productoId,
            @RequestParam double porcentajeDescuento) {

        productoService.aplicarDescuento(productoId, porcentajeDescuento);
        return "redirect:/admin";
    }

    @PostMapping("/quitar-descuento")
    public String quitarDescuento(@RequestParam Long productoId) {
        productoService.quitarDescuento(productoId);
        return "redirect:/admin";
    }



    @GetMapping("/menu2/tipo")
    public String menuPorTipo(Model model) {
        Map<TipoProducto, List<Producto>> productosPorTipo = productoService.listarProductosPorTipo();
        model.addAttribute("productosPorTipo", productosPorTipo);
        model.addAttribute("mostrarPorTipo", true);
        return "menu2";
    }


}
