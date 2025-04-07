package com.proyecto2.proyecto2.Controladores;


import com.proyecto2.proyecto2.Modelo.filo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

//Clase de controladores para los filos
@Controller
public class filoControlador {

    // Inyección de dependencia de filoservice
    @Autowired
    private servicios service;


    /**
     * Método que muestra la lista de filos.
     *
     * @param model - objeto Model para pasar datos a la vista
     * @return String - nombre de la vista a mostrar
     */
    @GetMapping("/filos")
    public String showfiloList(Model model){
        List<filo> listfilo=service.listAllFilo();
        model.addAttribute("listfilo",listfilo);

        return "filos";
    }

    /**
     * Método que muestra el formulario para añadir nuevos filos.
     *
     * @param model - objeto Model para pasar datos a la vista
     * @return String - nombre de la vista a mostrar
     */
    @GetMapping("/filos/new")
    public String showNewfiloForm(Model model){
        model.addAttribute("filo",new filo());
        model.addAttribute("pageTitle","Añadir filo");

        return "filo_form";
    }

    /**
     * Método que guarda un nuevo filo en la base de datos.
     *
     * @param filo - objeto filo con los datos del filo a guardar
     * @param ra - objeto RedirectAttributes para pasar mensajes entre solicitudes
     * @return String - cadena de redirección a la lista de filos
     */
    @PostMapping("/filos/save")
    public String savefilo(filo filo, RedirectAttributes ra){
        service.saveFilo(filo);
        ra.addFlashAttribute("message","El filo fue guardado");
        return "redirect:/filos";
    }

    /**
     * Método que muestra el formulario para editar un filo existente.
     *
     * @param id - ID del filo a editar
     * @param model - objeto Model para pasar datos a la vista
     * @param ra - objeto RedirectAttributes para pasar mensajes entre solicitudes
     * @return String - nombre de la vista a mostrar o cadena de redirección
     */
    @GetMapping("/filos/edit/{id}")
    public String showEditfiloForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            filo filo = service.getFilo(id);
            model.addAttribute("filo",filo);
            model.addAttribute("pageTitle","Editar filo (ID: "+id+")");
            return "filo_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message","El filo fue guardado");
            return "redirect:/filos";
        }

    }

    /**
     * Método que maneja la solicitud GET para eliminar un filo.
     *
     * @param id el id del filo a eliminar.
     * @param ra el objeto RedirectAttributes utilizado para agregar mensajes flash.
     * @return el nombre de la vista a la que redirigir después de eliminar el filo.
     */
    @GetMapping("/filos/delete/{id}")
    public String deletefilo(@PathVariable("id") Integer id,RedirectAttributes ra){
        try {
            service.deleteFilo(id);
            ra.addFlashAttribute("message","El lfilo de ID: "+id+" fue borrado");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/filos";
    }



}