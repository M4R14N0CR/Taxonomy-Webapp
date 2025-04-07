package com.proyecto2.proyecto2.Controladores;


import com.proyecto2.proyecto2.Modelo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;


//Clase de controladores de autores
@Controller
public class autorControlador {
    // Inyección de dependencia de authorService
    @Autowired
    private servicios service;

    /**
     * Método que muestra la lista de autores.
     *
     * @param model - objeto Model para pasar datos a la vista
     * @return String - nombre de la vista a mostrar
     */
    @GetMapping("/authors")
    public String showAuthorList(Model model){
        // Obtener la lista de autores utilizando el servicio
        List<Author> listAuthor = service.listAllAuthor();
        // Pasar la lista de autores al modelo
        model.addAttribute("listAuthor",listAuthor);

        // Devolver el nombre de la vista a mostrar
        return "authors";
    }

    /**
     * Método que muestra el formulario para añadir un nuevo autor.
     *
     * @param model - objeto Model para pasar datos a la vista
     * @return String - nombre de la vista a mostrar
     */
    @GetMapping("/authors/new")
    public String showNewAuthorForm(Model model){
        // Pasar un objeto Author vacío al modelo
        model.addAttribute("author",new Author());
        // Pasar el título de la página al modelo
        model.addAttribute("pageTitle","Añadir autor");

        // Devolver el nombre de la vista a mostrar
        return "author_form";
    }

    /**
     * Método que guarda un nuevo autor en la base de datos.
     *
     * @param author - objeto Author con los datos del autor a guardar
     * @param ra - objeto RedirectAttributes para pasar mensajes entre solicitudes
     * @return String - cadena de redirección a la lista de autores
     */
    @PostMapping("/authors/save")
    public String saveAuthor(Author author, RedirectAttributes ra){
        // Guardar el nuevo autor utilizando el servicio
        service.saveAuthor(author);
        // Agregar un mensaje de confirmación a los atributos de redirección
        ra.addFlashAttribute("message","The author ha sido añadido.");
        // Redirigir a la lista de autores
        return "redirect:/authors";
    }

    /**
     * Método que muestra el formulario para editar un autor existente.
     *
     * @param id - ID del autor a editar
     * @param model - objeto Model para pasar datos a la vista
     * @param ra - objeto RedirectAttributes para pasar mensajes entre solicitudes
     * @return String - nombre de la vista a mostrar o cadena de redirección
     */
    @GetMapping("/authors/edit/{id}")
    public String showEditAuthorForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            // Obtener el autor a editar utilizando el servicio
            Author author = service.getAuthor(id);
            // Pasar el autor al modelo
            model.addAttribute("author",author);
            // Pasar el título de la página al modelo
            model.addAttribute("pageTitle","Editar Autor (ID: "+id+")");
            // Devolver el nombre de la vista a mostrar
            return "author_form";
        } catch (UserNotFoundException e) {
            // Si el autor no existe, agregar un mensaje de error a los atributos de redirección
            ra.addFlashAttribute("message","El autor ha sido guardado.");
            // Redirigir a la lista de autores
            return "redirect:/authors";
        }
    }



    /**
     * Método que maneja la solicitud GET para eliminar un autor.
     *
     * @param id el id del autor a eliminar.
     * @param ra el objeto RedirectAttributes utilizado para agregar mensajes flash.
     * @return el nombre de la vista a la que redirigir después de eliminar el autor.
     */
    @GetMapping("/authors/delete/{id}")
    public String deleteAuthor(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            // Llama al método delete de la clase authorService y elimina el autor con el id proporcionado.
            service.deleteAuthor(id);
            // Agrega un mensaje flash indicando que el autor ha sido eliminado.
            ra.addFlashAttribute("message","El autor de ID: "+id+" ha sido eliminado");
        } catch (UserNotFoundException e) {
            // Si no se puede encontrar el usuario con el id proporcionado, agrega un mensaje flash indicando el error.
            ra.addFlashAttribute("message",e.getMessage());
        }
        // Redirige a la lista de autores después de eliminar al autor.
        return "redirect:/authors";
    }




}