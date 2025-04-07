package com.proyecto2.proyecto2.Controladores;

import com.proyecto2.proyecto2.Modelo.owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

//Clase de controladores de dueños
@Controller
public class duenoControlador {

    // Inyección de dependencia de ownerService
    @Autowired
    private servicios service;

    /**
     * Método que muestra la lista de dueños.
     *
     * @param model - objeto Model para pasar datos a la vista
     * @return String - nombre de la vista a mostrar
     */
    @GetMapping("/owners")
    public String showUserList(Model model){

        List<owner> listOwners = service.listAllOwner();
        model.addAttribute("listOwners",listOwners);

        return "owners";
    }

    /**
     * Método que muestra el formulario para añadir un nuevo dueño.
     *
     * @param model - objeto Model para pasar datos a la vista
     * @return String - nombre de la vista a mostrar
     */
    @GetMapping("/owners/new")
    public String showNewForm(Model model){
        model.addAttribute("owner",new owner());
        model.addAttribute("pageTitle","Agregar dueños");
        return "owner_form";
    }

    /**
     * Método que guarda un nuevo dueño en la base de datos.
     *
     * @param owner - objeto owner con los datos del dueño a guardar
     * @param ra - objeto RedirectAttributes para pasar mensajes entre solicitudes
     * @return String - cadena de redirección a la lista de dueños
     */
    @PostMapping("/owners/save")
    public String saveOwner(owner owner, RedirectAttributes ra){
        Integer ownerId = owner.getId(); // Obtén el id existente del propietario
        if (ownerId != null) { // Si el id existe, actualiza el propietario existente
            owner existingOwner = service.findByIdOwner(ownerId);
            existingOwner.setName(owner.getName());
            existingOwner.setEmail(owner.getEmail());
            existingOwner.setCountry(owner.getCountry());
            existingOwner.setPhone(owner.getPhone());
            service.saveOwner(existingOwner);
        } else { // Si el id no existe, crea un nuevo propietario
            service.saveOwner(owner);
        }
        ra.addFlashAttribute("message","El dueño ha sido registrado exitosamente");
        return "redirect:/owners";
    }

    /**
     * Método que muestra el formulario para editar un dueño existente.
     *
     * @param id - ID del dueño a editar
     * @param model - objeto Model para pasar datos a la vista
     * @param ra - objeto RedirectAttributes para pasar mensajes entre solicitudes
     * @return String - nombre de la vista a mostrar o cadena de redirección
     */
    @GetMapping("/owners/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model,RedirectAttributes ra){
        try {
            owner Owner = service.getOwner(id);
            model.addAttribute("owner", Owner);
            model.addAttribute("pageTitle","Editar dueño (ID: " + id + ")");
            return "owner_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message","El dueño ha sido registrado exitosamente");
            return "redirect:/owners";

        }
    }

    /**
     * Método que maneja la solicitud GET para eliminar un dueño.
     *
     * @param id el id del dueño a eliminar.
     * @param ra el objeto RedirectAttributes utilizado para agregar mensajes flash.
     * @return el nombre de la vista a la que redirigir después de eliminar el dueño.
     */
    @GetMapping("/owners/delete/{id}")
    public String deleteOwner(@PathVariable("id") Integer id,RedirectAttributes ra){

        try {
            service.deleteOwner(id);
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());

        }
        return "redirect:/owners";
    }


}
