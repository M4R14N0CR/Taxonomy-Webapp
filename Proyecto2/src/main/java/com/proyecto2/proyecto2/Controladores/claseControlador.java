package com.proyecto2.proyecto2.Controladores;


import com.proyecto2.proyecto2.Modelo.clase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

// Clase de controladores de clases taxonomicas
@Controller
public class claseControlador {

    // Inyección de dependencia de claseService
    @Autowired
    private servicios service;

    /**
     * Método que muestra la lista de todas las clases.
     *
     * @param model Model utilizado para almacenar el atributo "listclase" y pasar
     *              la información a la vista.
     * @return Nombre de la vista que muestra la lista de todas las clases.
     */
    @GetMapping("/clases")
    public String showclaseList(Model model){
        // Se obtiene la lista de todas las clases
        List<clase> listclase=service.listAllClase();
        // Se agrega la lista al modelo para pasarla a la vista
        model.addAttribute("listclase",listclase);

        return "clases";
    }

    /**
     * Método que muestra el formulario para añadir una nueva clase.
     *
     * @param model Model utilizado para almacenar el atributo "clase" y pasar la
     *              información a la vista.
     * @return Nombre de la vista que muestra el formulario para añadir una nueva
     *         clase.
     */
    @GetMapping("/clases/new")
    public String showNewclaseForm(Model model){
        // Se crea un objeto de la clase clase
        model.addAttribute("clase",new clase());
        // Se agrega el título de la página al modelo para pasarlo a la vista
        model.addAttribute("pageTitle","Añadir clase");

        return "clase_form";
    }

    /**
     * Método que guarda una nueva clase.
     *
     * @param clase Objeto de la clase clase que se va a guardar.
     * @param ra    RedirectAttributes utilizado para pasar el mensaje a la vista
     *              cuando se redirige a la lista de todas las clases.
     * @return Redirecciona a la lista de todas las clases.
     */
    @PostMapping("/clases/save")
    public String saveclase(clase clase, RedirectAttributes ra){
        // Se guarda la clase
        service.saveClase(clase);
        // Se agrega un mensaje a RedirectAttributes para mostrar en la vista
        ra.addFlashAttribute("message","La clase ha sido guardada.");
        return "redirect:/clases";
    }


    /**
     * Método que edita clases
     *
     * @param id id de la clase
     * @param model    Model utilizado para almacenar el atributo "clase" y pasar la
     *                 información a la vista
     * @param ra    RedirectAttributes utilizado para pasar el mensaje a la vista
     *              cuando se redirige a la lista de todas las clases.
     * @return Redirecciona a la lista de todas las clases.
     */
    @GetMapping("/clases/edit/{id}")
    public String showEditclaseForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            clase clase = service.getClase(id);
            model.addAttribute("clase",clase);
            model.addAttribute("pageTitle","Editar clase (ID: "+id+")");
            return "clase_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message","La clase fue guardada");
            return "redirect:/clases";
        }

    }

    /**
     * Método que borra una clase.
     *
     * @param id       id de la clase
     * @param ra    RedirectAttributes utilizado para pasar el mensaje a la vista
     *              cuando se redirige a la lista de todas las clases.
     * @return Redirecciona a la lista de todas las clases.
     */
    @GetMapping("/clases/delete/{id}")
    public String deleteclase(@PathVariable("id") Integer id,RedirectAttributes ra){
        try {
            service.deleteClase(id);
            ra.addFlashAttribute("message","La clase de ID:  "+id+" fue borrada");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/clases";
    }



}