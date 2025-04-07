package com.proyecto2.proyecto2.Controladores;


import com.proyecto2.proyecto2.Modelo.reino;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

//Clase de controladores de reino
@Controller
public class reinoControlador {

    // Inyección de dependencia de reinoService
    @Autowired
    private servicios service;


    /**
     * Método que muestra la lista de reinos.
     *
     * @param model - objeto Model para pasar datos a la vista
     * @return String - nombre de la vista a mostrar
     */
    @GetMapping("/reinos")
    public String showreinoList(Model model){
        List<reino> listreino=service.listAllReino();
        model.addAttribute("listreino",listreino);

        return "reinos";
    }

    /**
     * Método que muestra el formulario para añadir un nuevo reino.
     *
     * @param model - objeto Model para pasar datos a la vista
     * @return String - nombre de la vista a mostrar
     */
    @GetMapping("/reinos/new")
    public String showNewreinoForm(Model model){
        model.addAttribute("reino",new reino());
        model.addAttribute("pageTitle","Añadir reino");

        return "reino_form";
    }

    /**
     * Método que guarda un nuevo reino en la base de datos.
     *
     * @param reino - objeto reino con los datos del reino a guardar
     * @param ra - objeto RedirectAttributes para pasar mensajes entre solicitudes
     * @return String - cadena de redirección a la lista de reinos
     */
    @PostMapping("/reinos/save")
    public String savereino(reino reino, RedirectAttributes ra){
        service.saveReino(reino);
        ra.addFlashAttribute("message","El reino fue guardado");
        return "redirect:/reinos";
    }

    /**
     * Método que muestra el formulario para editar un reino existente.
     *
     * @param id - ID del reino a editar
     * @param model - objeto Model para pasar datos a la vista
     * @param ra - objeto RedirectAttributes para pasar mensajes entre solicitudes
     * @return String - nombre de la vista a mostrar o cadena de redirección
     */
    @GetMapping("/reinos/edit/{id}")
    public String showEditreinoForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            reino reino = service.getReino(id);
            model.addAttribute("reino",reino);
            model.addAttribute("pageTitle","Editar reino (ID: "+id+")");
            return "reino_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message","El reino fue guardado");
            return "redirect:/reinos";
        }

    }

    /**
     * Método que maneja la solicitud GET para eliminar un reino.
     *
     * @param id el id del reino a eliminar.
     * @param ra el objeto RedirectAttributes utilizado para agregar mensajes flash.
     * @return el nombre de la vista a la que redirigir después de eliminar el reino.
     */
    @GetMapping("/reinos/delete/{id}")
    public String deletereino(@PathVariable("id") Integer id,RedirectAttributes ra){
        try {
            service.deleteReino(id);
            ra.addFlashAttribute("message","El reino de ID:  "+id+" fue eliminado");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/reinos";
    }



}