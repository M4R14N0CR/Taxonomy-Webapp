package com.proyecto2.proyecto2.Controladores;


import com.proyecto2.proyecto2.Modelo.familia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

//Clase de controladores de familias
@Controller
public class familiaControlador {

    // Inyección de dependencia de familiaService
    @Autowired
    private servicios service;

    /**
     * Método que muestra la lista de familias.
     *
     * @param model - objeto Model para pasar datos a la vista
     * @return String - nombre de la vista a mostrar
     */
    @GetMapping("/familias")
    public String showfamiliaList(Model model){
        List<familia> listfamilia=service.listAllFamilia();
        model.addAttribute("listfamilia",listfamilia);

        return "familias";
    }

    /**
     * Método que muestra el formulario para añadir nuevas familias.
     *
     * @param model - objeto Model para pasar datos a la vista
     * @return String - nombre de la vista a mostrar
     */
    @GetMapping("/familias/new")
    public String showNewfamiliaForm(Model model){
        model.addAttribute("familia",new familia());
        model.addAttribute("pageTitle","Añadari familia");

        return "familia_form";
    }

    /**
     * Método que guarda una nueva familia en la base de datos.
     *
     * @param familia - objeto familia con los datos de la familia a guardar
     * @param ra - objeto RedirectAttributes para pasar mensajes entre solicitudes
     * @return String - cadena de redirección a la lista de familias
     */
    @PostMapping("/familias/save")
    public String savefamilia(familia familia, RedirectAttributes ra){
        service.saveFamilia(familia);
        ra.addFlashAttribute("message","La familia fue guaradada");
        return "redirect:/familias";
    }

    /**
     * Método que muestra el formulario para editar un filo existente.
     *
     * @param id - ID de la familia a editar
     * @param model - objeto Model para pasar datos a la vista
     * @param ra - objeto RedirectAttributes para pasar mensajes entre solicitudes
     * @return String - nombre de la vista a mostrar o cadena de redirección
     */
    @GetMapping("/familias/edit/{id}")
    public String showEditfamiliaForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            familia familia = service.getFamilia(id);
            model.addAttribute("familia",familia);
            model.addAttribute("pageTitle","Editar familia (ID: "+id+")");
            return "familia_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message","La familia fue guardada.");
            return "redirect:/familias";
        }

    }


    /**
     * Método que maneja la solicitud GET para eliminar un autor.
     *
     * @param id el id de la familia a eliminar.
     * @param ra el objeto RedirectAttributes utilizado para agregar mensajes flash.
     * @return el nombre de la vista a la que redirigir después de eliminar la familia.
     */
    @GetMapping("/familias/delete/{id}")
    public String deletefamilia(@PathVariable("id") Integer id,RedirectAttributes ra){
        try {
            service.deleteFamilia(id);
            ra.addFlashAttribute("message","La familia de ID: "+id+" fue guardada");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/familias";
    }



}