package com.tienda.controller;

import com.tienda.domain.Producto;
import com.tienda.service.CategoriaService;
import com.tienda.service.impl.FirebaseStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.tienda.service.ProductoService;
import java.util.List;
/**
 *
 * @author Me
 */
@Controller
@RequestMapping("/producto")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;
    
    @GetMapping("/listado")
    public String inicio(Model model){
        List<Producto> listadoProductos = productoService.getProductos(false);
        model.addAttribute("productos", listadoProductos);
        model.addAttribute("totalProductos", listadoProductos.size());
        return "/producto/listado";
    }
    
    @GetMapping("/nuevo")
    public String productoNuevo(Producto producto){
        return "/producto/modifica";
    }
    
    @PostMapping("/guardar")
    public String productoGuardar(Producto producto,
            @RequestParam("imagenFile") MultipartFile imagenFile){
        if (!imagenFile.isEmpty()){
           productoService.save(producto);
           producto.setRutaImagen(
           firebaseStorageService.cargaImagen(
                   imagenFile, 
                   "productos", 
                   producto.getIdProducto()));
           
        }
        productoService.save(producto);
        return "redirect:/producto/listado";
    }
    
    @GetMapping("/eliminar/{idProducto}")
    public String productoEliminar(Producto producto){
        productoService.delete(producto);
        return "redirect:/producto/listado";
    }
    
    @GetMapping("/modificar/{idProducto}")
    public String productoModificar(Producto producto, Model model){
        producto = productoService.getProducto(producto);
        model.addAttribute("producto", producto);
        return "/producto/modifica";
    }
}
