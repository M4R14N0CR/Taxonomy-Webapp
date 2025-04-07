package com.proyecto2.proyecto2.Controladores;


import com.proyecto2.proyecto2.Modelo.Author;
import com.proyecto2.proyecto2.Modelo.License;
import com.proyecto2.proyecto2.Modelo.image;
import com.proyecto2.proyecto2.Modelo.owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.*;
import java.util.List;

@Controller
public class imagenControlador {

    // Inyección de dependencias
    @Autowired
    private servicios service;


    /**
     * Método que muestra las imagenes.
     *
     * @param model - objeto Model para pasar datos a la vista
     * @param page  - la pagina actual
     * @return String - retorna el index donde se ven las imagenes
     */
    @GetMapping("/")
    public String showImageList(Model model, @RequestParam(required = false, defaultValue = "0") Integer page) {
        int pageSize = 9; // número de elementos por página
        Page<image> imagePage = service.findPaginatedImage(page, pageSize);
        List<image> images = imagePage.getContent();
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", imagePage.getTotalPages());
        model.addAttribute("totalItems", imagePage.getTotalElements());
        model.addAttribute("images", images);
        return "index";
    }

    /**
     * Método que muestra los resultados de las busquedas.
     *
     * @param model - objeto Model para pasar datos a la vista
     * @param page - pagina acutal de las busquedas
     * @param clave - la palabra que busco el usuario
     * @return String - nombre de la vista a mostrar
     */
    @GetMapping("/buscador")
    public String showImageSearch(@RequestParam(required = false, defaultValue = "0") Integer page, @RequestParam("clave") String clave,Model model) {
        int pageSize = 9; // número de elementos por página
        Page<image> imagePage = service.findPaginatedImage(page, pageSize);
        List<image> images = service.busquedaImage(clave);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", imagePage.getTotalPages());
        model.addAttribute("totalItems", imagePage.getTotalElements());
        model.addAttribute("images", images);
        return "index";
    }

    /**
     * Método que muestra una imagen en especifico.
     *
     * @param model - objeto Model para pasar datos a la vista
     * @param id - id de la imagen
     * @return String - nombre de la vista a mostrar
     */
    @GetMapping("/images/{id}")
    public String showimageList2(@PathVariable("id") Integer id, Model model) throws UserNotFoundException {
            image image = service.getImage(id);
            model.addAttribute("image",image);
            return "images";
    }


    /**
     * Método que muestra el formulario para agregar una imagen.
     *
     * @param model - objeto Model para pasar datos a la vista
     * @return String - nombre de la vista a mostrar
     */
    @GetMapping("/new")
    public String showNewimageForm(Model model){
        model.addAttribute("image",new image());
        model.addAttribute("authors", service.listAllAuthor());
        model.addAttribute("owners", service.listAllOwner());

        model.addAttribute("reinos", service.listAllReino());
        model.addAttribute("filos", service.listAllFilo());
        model.addAttribute("clases", service.listAllClase());
        model.addAttribute("ordenes", service.listAllOrden());
        model.addAttribute("familias", service.listAllFamilia());
        model.addAttribute("generos", service.listAllGenero());

        model.addAttribute("licenses", License.values());
        model.addAttribute("pageTitle","Añadir imagen");


        return "image_form";
    }

    /**
     * Método que guarda imagenes.
     *
     * @param image - objeto imagen
     * @param authorId - el id del author de la imagen
     * @param keywords - las palabras clave de la imagen
     * @param license - la licencia de la imagen
     * @param ownerId - el dueño de la imagen
     * @param nameFile - el nombre del archivo
     * @param ra - objeto RedirectAttributes para pasar mensajes entre solicitudes
     * @return String - nombre de la vista a mostrar
     */
    @PostMapping("/save")
    public String saveImage(image image, @RequestParam("author.id") Integer authorId,
                            @RequestParam("keywords") String keywords, @RequestParam("license") License license, @RequestParam("owner.id") Integer ownerId,
                            @RequestParam("nameimage") MultipartFile nameFile, RedirectAttributes ra){


        Author author = service.findByIdAuthor(authorId);
        owner owner = service.findByIdOwner(ownerId);
        Set<String> keywordsSet = new HashSet<>(Arrays.asList(keywords.split("\\s*,\\s*")));
        image.setAuthor(author);
        image.setOwnerId(owner);
        image.setLicense(license);
        String nombre = nameFile.getOriginalFilename();
        image.setPath(nombre);
        service.saveImage(image);
        ra.addFlashAttribute("message","La imagen fue guradada.");
        return "redirect:/";
    }


    /**
     * Método para editar imagenes.
     *
     * @param model - objeto Model para pasar datos a la vista
     * @param id - id de la imagen a editar
     * @param ra - objeto RedirectAttributes para pasar mensajes entre solicitudes
     * @return String - nombre de la vista a mostrar
     */
    @GetMapping("/images/edit/{id}")
    public String showEditimageForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            image image = service.getImage(id);
            model.addAttribute("image",image);
            model.addAttribute("pageTitle","Editar imagen (ID: "+id+")");
            model.addAttribute("authors", service.listAllAuthor());
            model.addAttribute("owners", service.listAllOwner());
            model.addAttribute("reinos", service.listAllReino());
            model.addAttribute("ordenes", service.listAllOrden());
            model.addAttribute("clases", service.listAllClase());
            model.addAttribute("filos", service.listAllFilo());
            model.addAttribute("generos", service.listAllGenero());
            model.addAttribute("familias", service.listAllFamilia());
            model.addAttribute("licenses", License.values());
            return "image_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message","La imagen fue guardada.");
            return "redirect:/images/edit/{id}";
        }

    }


    /**
     * Método para eliminar imagenes.
     *
     * @param id - id de la imagen a eliminar
     * @param ra - objeto RedirectAttributes para pasar mensajes entre solicitudes
     * @return String - nombre de la vista a mostrar
     */
    @GetMapping("images/delete/{id}")
    public String deleteimage(@PathVariable("id") Integer id,RedirectAttributes ra){
        try {
            service.deleteImage(id);
            ra.addFlashAttribute("message","La imagen de ID:  "+id+" fue borrada");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/";
    }

}