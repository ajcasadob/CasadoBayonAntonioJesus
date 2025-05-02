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

@Controller
public class ProductoController {

    private final ProductoService productoService;
    private final CartaService  cartaService;

    @Autowired
    public ProductoController(ProductoService productoService, CartaService cartaService){
        this.productoService=productoService;
        this.cartaService=cartaService;
    }
    @GetMapping("/")
    public String verWeb(Model model) {
        model.addAttribute("productoForm", new Producto());
        model.addAttribute("tiposProducto", TipoProducto.values());
        model.addAttribute("cartasDisponibles", cartaService.findAllCartas()); // Si vas a seleccionar Carta
        return "CasadoBayonAntonioJesus";
    }

    @PostMapping("/agregarProducto")
    public String agregarProducto(
            @ModelAttribute("productoForm") Producto producto,
            @RequestParam("idCarta") Long idCarta, // Cambiado a idCarta
            RedirectAttributes redirectAttributes
    ) {


        // Si manejas la relación con Carta
        Carta carta = cartaService.findById(idCarta);
        producto.setCarta(carta);
        productoService.savedProduct(producto);

        productoService.savedProduct(producto);
        redirectAttributes.addFlashAttribute("exito", "¡Producto agregado correctamente!");
        return "redirect:/";
    }





}
