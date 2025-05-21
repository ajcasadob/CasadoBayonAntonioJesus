package com.salesianostriana.dam.CasadoBayonAntonioJesus.service;

import com.salesianostriana.dam.CasadoBayonAntonioJesus.controller.ProductoController;
import com.salesianostriana.dam.CasadoBayonAntonioJesus.model.Carta;
import com.salesianostriana.dam.CasadoBayonAntonioJesus.model.Producto;
import com.salesianostriana.dam.CasadoBayonAntonioJesus.repository.ProductoRepository;
import com.salesianostriana.dam.CasadoBayonAntonioJesus.tipos.TipoProducto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductoService {


    @Autowired
    private  ProductoRepository productoRepository;

    @Autowired
    private CartaService cartaService;

    //Buscar un producto si no escibimos nada no busca, asi evitamos busquedas vacias.
    public List<Producto> buscarPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return Collections.emptyList();
        }

        return productoRepository.findByNombreContainingIgnoreCase(nombre.trim());
    }


    //Obtener todos los productos
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    //Eliminar un producto
    public void deleteProduct(String nombre) {
        List<Producto> productoEliminar = productoRepository.findByNombreContainingIgnoreCase(nombre);
        if (!productoEliminar.isEmpty()) {
            productoRepository.deleteAll(productoEliminar);
        }
    }

    //Buscar un producto por su id
    public Producto findById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + id));
    }

    //Guardar y editar un produdcto

    public void guardarOActualizarProducto(Producto producto, Long idCarta, Double descuento) {
        Carta carta = cartaService.findCartaById(idCarta);
        producto.addToCarta(carta);


        if (descuento == null || descuento <= 0) {
            producto.setConDescuento(false);


            if (producto.getId() == null) {
                producto.setPrecio(producto.getPrecioOriginal());
            }
        } else {

            double original = producto.getPrecioOriginal() != 0 ? producto.getPrecioOriginal() : producto.getPrecio();
            producto.setPrecioOriginal(original);
            double precioConDescuento = original * (1 - descuento / 100);
            producto.setPrecio(precioConDescuento);
            producto.setConDescuento(true);
        }

        productoRepository.save(producto);
    }



    //Obtener los primeros cuatro productos
    public List<Producto> obtenerPrimerosCuatroProductos() {
        return productoRepository.findAll().stream().limit(4).toList();
    }

    //Eliminar
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    // Recortar la descripción de los productos a 50 caracteres
    public List<Producto> obtenerTodosDescripcionReducida() {
        return productoRepository.findAll().stream()
                .map(p -> {
                    p.setDescripcion(p.getDescripcion().length() > 50 ? p.getDescripcion().substring(0, 50) + "..." : p.getDescripcion());
                    return p;
                })
                .collect(Collectors.toList());
    }

    //Obtener los productos con la popularidad más alta y mostar los 6
    public List<Producto> obtenerMejorValorados() {

        return productoRepository.obtenerMejorValorados().stream()
                .limit(6)
                .toList();

    }


    //Obtener los dos últimos productos
    public List<Producto> obtenerUltimosDosProductos() {
        return productoRepository.findTop2ByOrderByFechaDesc();
    }

    //Quitar descuento
    public void quitarDescuento(Long productoId) {
        Producto producto = findById(productoId);
        producto.setPrecio(0);
        producto.setConDescuento(false);
        productoRepository.save(producto);
    }



    //Aplicar un descuento
    public void aplicarDescuento(Long productoId, double porcentajeDescuento) {
        Producto producto = findById(productoId);

        double precioOriginal = producto.getPrecioOriginal();


        if (precioOriginal > 0) {
            double precioConDescuento = precioOriginal * (1 - porcentajeDescuento / 100);
            producto.setPrecio(precioConDescuento);
            producto.setConDescuento(true);
            productoRepository.save(producto);
        }
    }
    //Obtener los productos con descuento
    public List<Producto> obtenerProductosConDescuento() {
        return productoRepository.findAll().stream()
                .filter(p -> p.isConDescuento() && p.getPrecio() < p.getPrecioOriginal())
                .toList();
    }






    //Obtener los productos ordenados por precio de mayor a menor
    public List<Producto> obtenerProductosOrdenadosPorPrecioDesc() {
        return productoRepository.findByPrecioDesc();
    }

    //Obtener los productos ordenados por precio de menor a mayor
    public List<Producto> obtenerProductosOrdenadosPorPrecioAsc() {
        return productoRepository.findByPrecioAsc();
    }

    //Obtener los productos ordenados por nombre de menor a mayor
    public List<Producto> obtenerProductosOrdenadosPorNombreAsc() {
        return productoRepository.findByNombreAsc();
    }

    //Obtener los productos ordenados por fecha de menor a mayor
    public List<Producto> obtenerProductosOrdenadosPorFechaDesc() {
        return productoRepository.findByFechaDesc();
    }

    //Obtener los productos ordenados por popularidad de menor a mayor
    public List<Producto> obtenerProductosOrdenadosPorPopularidadDesc() {
        return productoRepository.findByPopularidadDesc();
    }

    //Obtener los productos ordenados por tipo
    public Map<TipoProducto, List<Producto>> listarProductosPorTipo() {
        return productoRepository.findAll().stream()
                .collect(Collectors.groupingBy(Producto::getTipoProducto));
    }

    //Obtener los productos nuevos (última semana)
    public List<Producto> obtenerProductosNuevos() {
        LocalDate haceUnaSemana = LocalDate.now().minusDays(7);
        return productoRepository.findAll().stream()
                .filter(p -> p.getFecha() != null && p.getFecha().isAfter(haceUnaSemana))
                .toList();
    }


}


