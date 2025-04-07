package com.proyecto2.proyecto2.Controladores;


import com.proyecto2.proyecto2.Modelo.orden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

//Clase de controladores de instituciones
@Controller
public class ordenControlador {

    // Inyección de dependencia de institutionService
    @Autowired
    private servicios service;


    /**
     * Método que muestra la lista de ordenes.
     *
     * @param model - objeto Model para pasar datos a la vista
     * @return String - nombre de la vista a mostrar
     */
    @GetMapping("/ordens")
    public String showordenList(Model model){
        List<orden> listorden=service.listAllOrden();
        model.addAttribute("listorden",listorden);

        return "ordens";
    }

    /**
     * Método que muestra el formulario para añadir un nuevo orden.
     *
     * @param model - objeto Model para pasar datos a la vista
     * @return String - nombre de la vista a mostrar
     */
    @GetMapping("/ordens/new")
    public String showNewordenForm(Model model){
        model.addAttribute("orden",new orden());
        model.addAttribute("pageTitle","Añadir orden");

        return "orden_form";
    }

    /**
     * Método que guarda un nuevo orden en la base de datos.
     *
     * @param orden - objeto institution con los datos del orden a guardar
     * @param ra - objeto RedirectAttributes para pasar mensajes entre solicitudes
     * @return String - cadena de redirección a la lista de ordenes
     */
    @PostMapping("/ordens/save")
    public String saveorden(orden orden, RedirectAttributes ra){
        service.saveOrden(orden);
        ra.addFlashAttribute("message","El orden fue guardado");
        return "redirect:/ordens";
    }

    /**
     * Método que muestra el formulario para editar un orden existente.
     *
     * @param id - ID del orden a editar
     * @param model - objeto Model para pasar datos a la vista
     * @param ra - objeto RedirectAttributes para pasar mensajes entre solicitudes
     * @return String - nombre de la vista a mostrar o cadena de redirección
     */
    @GetMapping("/ordens/edit/{id}")
    public String showEditordenForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            orden orden = service.getOrden(id);
            model.addAttribute("orden",orden);
            model.addAttribute("pageTitle","Editar orden (ID: "+id+")");
            return "orden_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message","El orden fue guardado");
            return "redirect:/ordens";
        }

    }

    /**
     * Método que maneja la solicitud GET para eliminar un orden.
     *
     * @param id el id del orden a eliminar.
     * @param ra el objeto RedirectAttributes utilizado para agregar mensajes flash.
     * @return el nombre de la vista a la que redirigir después de eliminar el orden.
     */
    @GetMapping("/ordens/delete/{id}")
    public String deleteorden(@PathVariable("id") Integer id,RedirectAttributes ra){
        try {
            service.deleteOrden(id);
            ra.addFlashAttribute("message","El orden de ID "+id+" fue borrado");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/ordens";
    }



}