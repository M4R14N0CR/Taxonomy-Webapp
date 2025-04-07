package com.proyecto2.proyecto2.Controladores;


import com.proyecto2.proyecto2.Modelo.institution;
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
public class institucionControlador {

    // Inyección de dependencia de institutionService
    @Autowired
    private servicios service;


    /**
     * Método que muestra la lista de instituciones.
     *
     * @param model - objeto Model para pasar datos a la vista
     * @return String - nombre de la vista a mostrar
     */
    @GetMapping("/institutions")
    public String showinstitutionList(Model model){
        List<institution> listinstitution=service.listAllInstitution();
        model.addAttribute("listinstitution",listinstitution);

        return "institutions";
    }

    /**
     * Método que muestra el formulario para añadir una nueva institucion.
     *
     * @param model - objeto Model para pasar datos a la vista
     * @return String - nombre de la vista a mostrar
     */
    @GetMapping("/institutions/new")
    public String showNewinstitutionForm(Model model){
        model.addAttribute("institution",new institution());
        model.addAttribute("pageTitle","Añadir institución");

        return "institution_form";
    }

    /**
     * Método que guarda una nueva institucion en la base de datos.
     *
     * @param institution - objeto institution con los datos de la institucion a guardar
     * @param ra - objeto RedirectAttributes para pasar mensajes entre solicitudes
     * @return String - cadena de redirección a la lista de instituciones
     */
    @PostMapping("/institutions/save")
    public String saveinstitution(institution institution, RedirectAttributes ra){
        service.saveInstitution(institution);
        ra.addFlashAttribute("message","La institución ha sido guardada.");
        return "redirect:/institutions";
    }

    /**
     * Método que muestra el formulario para editar una institucion existente.
     *
     * @param id - ID de la institucion a editar
     * @param model - objeto Model para pasar datos a la vista
     * @param ra - objeto RedirectAttributes para pasar mensajes entre solicitudes
     * @return String - nombre de la vista a mostrar o cadena de redirección
     */
    @GetMapping("/institutions/edit/{id}")
    public String showEditinstitutionForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            institution institution = service.getInstitution(id);
            model.addAttribute("institution",institution);
            model.addAttribute("pageTitle","Edit institution (ID: "+id+")");
            return "institution_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message","La institución ha sido guardada.");
            return "redirect:/institutions";
        }

    }

    /**
     * Método que maneja la solicitud GET para eliminar una institucion.
     *
     * @param id el id de la institucion a eliminar.
     * @param ra el objeto RedirectAttributes utilizado para agregar mensajes flash.
     * @return el nombre de la vista a la que redirigir después de eliminar la institucion.
     */
    @GetMapping("/institutions/delete/{id}")
    public String deleteinstitution(@PathVariable("id") Integer id,RedirectAttributes ra){
        try {
            service.deleteInstitution(id);
            ra.addFlashAttribute("message","La institución de ID: "+id+" ha sido borrada.");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/institutions";
    }
}
