package com.proyecto2.proyecto2.Controladores;


import com.proyecto2.proyecto2.Modelo.genero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

//Clase de controladores de los generos
@Controller
public class generoControlador {

    // Inyección de dependencia de generos
    @Autowired
    private servicios service;


    /**
     * Método que muestra la lista de generos.
     *
     * @param model - objeto Model para pasar datos a la vista
     * @return String - nombre de la vista a mostrar
     */
    @GetMapping("/generos")
    public String showgeneroList(Model model){
        List<genero> listgenero=service.listAllGenero();
        model.addAttribute("listgenero",listgenero);

        return "generos";
    }

    /**
     * Método que muestra el formulario para añadir nuevos generos.
     *
     * @param model - objeto Model para pasar datos a la vista
     * @return String - nombre de la vista a mostrar
     */
    @GetMapping("/generos/new")
    public String showNewgeneroForm(Model model){
        model.addAttribute("genero",new genero());
        model.addAttribute("pageTitle","Añadir genero");

        return "genero_form";
    }

    /**
     * Método que guarda un nuevo genero en la base de datos.
     *
     * @param genero - objeto genero con los datos del genero a guardar
     * @param ra - objeto RedirectAttributes para pasar mensajes entre solicitudes
     * @return String - cadena de redirección a la lista de generos
     */
    @PostMapping("/generos/save")
    public String savegenero(genero genero, RedirectAttributes ra){
        service.saveGenero(genero);
        ra.addFlashAttribute("message","El genero fue guardado");
        return "redirect:/generos";
    }

    /**
     * Método que muestra el formulario para editar un genero existente.
     *
     * @param id - ID del genero a editar
     * @param model - objeto Model para pasar datos a la vista
     * @param ra - objeto RedirectAttributes para pasar mensajes entre solicitudes
     * @return String - nombre de la vista a mostrar o cadena de redirección
     */
    @GetMapping("/generos/edit/{id}")
    public String showEditgeneroForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            genero genero = service.getGenero(id);
            model.addAttribute("genero",genero);
            model.addAttribute("pageTitle","Editar genero (ID: "+id+")");
            return "genero_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message","El genero fue guardado");
            return "redirect:/generos";
        }

    }

    /**
     * Método que maneja la solicitud GET para eliminar un filo.
     *
     * @param id el id del genero a eliminar.
     * @param ra el objeto RedirectAttributes utilizado para agregar mensajes flash.
     * @return el nombre de la vista a la que redirigir después de eliminar el genero.
     */
    @GetMapping("/generos/delete/{id}")
    public String deletegenero(@PathVariable("id") Integer id,RedirectAttributes ra){
        try {
            service.deleteGenero(id);
            ra.addFlashAttribute("message","El genero de ID: "+id+" fue borrado");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/generos";
    }



}