package com.proyecto2.proyecto2.Controladores;

import com.proyecto2.proyecto2.Modelo.*;
import com.proyecto2.proyecto2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
// Esta clase proporciona servicios para manejar las operaciones de autor
public class servicios {
    @Autowired
    private authorRepository authorRepo;
    @Autowired
    private ownerRepository ownerRepo;
    @Autowired
    private institutionRepository institutionRepo;
    @Autowired
    private claseRepository claseRepo;
    @Autowired
    private familiaRepository familiaRepo;
    @Autowired
    private filoRepository filoRepo;
    @Autowired
    private generoRepository generoRepo;
    @Autowired
    private ordenRepository ordenRepo;
    @Autowired
    private reinoRepository reinoRepo;
    @Autowired
    private imageRepository imageRepo;


    /** Este método devuelve una lista de todos los autores en la base de datos
     * @return: una lista de autores
     */
    public List<Author> listAllAuthor(){
        return (List<Author>) authorRepo.findAll();
    }

    /** Este método guarda un autor en la base de datos
     *@param author: el autor a guardar
     */
    public void saveAuthor(Author author) {
        authorRepo.save(author);
    }

    /** Este método encuentra un autor en la base de datos según su ID
     @param id: el ID del autor a buscar
     @return: el autor correspondiente al ID dado
     */
    public Author findByIdAuthor(Integer id) {
        Optional<Author> result = authorRepo.findById(id);
        return result.get();
    }

    /** Este método encuentra un autor en la base de datos según su ID
     @param id: el ID del autor a buscar
     @return: el autor correspondiente al ID dado
     @throws UserNotFoundException : si no se encuentra un autor con el ID dado
     */
    public Author getAuthor(Integer id) throws UserNotFoundException {
        Optional<Author> result = authorRepo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("No se encontro un usuario con esta id: "+id);

    }

    /** Este método elimina un autor de la base de datos según su ID
     @param id: el ID del autor a eliminar
     @throws UserNotFoundException: si no se encuentra un autor con el ID dado
     */
    public void deleteAuthor(Integer id) throws UserNotFoundException {
        Long count = authorRepo.countById(id);
        if(count == null || count == 0){
            throw new UserNotFoundException("No se encontro un usuario con esta id: "+id);
        }
        authorRepo.deleteById(id);
    }


    /**
     * Obtiene una lista con todos los dueños registrados en el sistema.
     * @return Lista con los dueños registrados.
     */
    public List<owner> listAllOwner(){
        return (List<owner>) ownerRepo.findAll();
    }

    /**
     * Guarda la información de un nuevo dueño o actualiza la información de un dueño existente.
     * @param owner Objeto owner a guardar o actualizar.
     */
    public void saveOwner(owner owner) {
        ownerRepo.save(owner);
    }

    /**
     * Busca un dueño en el sistema por su id.
     * @param id Identificador único del dueño a buscar.
     * @return Objeto owner que coincide con el id proporcionado.
     */
    public owner findByIdOwner(Integer id) {
        Optional<owner> result = ownerRepo.findById(id);
        return result.get(); // Se asume que siempre habrá un resultado al buscar por id
    }

    /**
     * Busca un dueño en el sistema por su id.
     * @param id Identificador único del dueño a buscar.
     * @return Objeto owner que coincide con el id proporcionado.
     * @throws UserNotFoundException Si no se encuentra un dueño con el id proporcionado.
     */
    public owner getOwner(Integer id) throws UserNotFoundException {
        Optional<owner> result = ownerRepo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("No se encontro un usuario con esta id: "+id);
    }

    /**
     * Elimina un dueño del sistema.
     * @param id Identificador único del dueño a eliminar.
     * @throws UserNotFoundException Si no se encuentra un dueño con el id proporcionado.
     */
    public void deleteOwner(Integer id) throws UserNotFoundException {
        Long count = ownerRepo.countById(id);
        if(count == null || count == 0){
            throw new UserNotFoundException("No se encontro un usuario con esta id: "+id);
        }
        ownerRepo.deleteById(id);
    }



    /**
     * Obtiene una lista de todas las instituciones.
     *
     * @return Lista de instituciones
     */
    public List<institution> listAllInstitution(){
        return (List<institution>) institutionRepo.findAll();
    }

    /**
     * Guarda una nueva institución o actualiza una existente.
     *
     * @param institution La institución a guardar o actualizar
     */
    public void saveInstitution(institution institution) {
        institutionRepo.save(institution);
    }

    /**
     * Obtiene una institución por su ID.
     *
     * @param id ID de la institución a buscar
     * @return La institución con el ID especificado
     * @throws UserNotFoundException si no se encuentra una institución con el ID especificado
     */
    public institution getInstitution(Integer id) throws UserNotFoundException {
        Optional<institution> result = institutionRepo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("No se encontro una institucion con esta id: "+id);
    }

    /**
     * Elimina una institución por su ID.
     *
     * @param id ID de la institución a eliminar
     * @throws UserNotFoundException si no se encuentra una institución con el ID especificado
     */
    public void deleteInstitution(Integer id) throws UserNotFoundException {
        Long count = institutionRepo.countById(id);
        if(count == null || count == 0){
            throw new UserNotFoundException("No se encontro una institucion con esta id: "+id);
        }
        institutionRepo.deleteById(id);
    }

    /**
     * Método que retorna una lista con todas las instancias de la clase clase guardadas en la base de datos
     * @return List<clase> - Lista de instancias de la clase clase
     */
    public List<clase> listAllClase(){
        return (List<clase>) claseRepo.findAll();
    }

    /**
     * Método que guarda una instancia de la clase clase en la base de datos
     * @param clase - Instancia de la clase clase a guardar
     */
    public void saveClase(clase clase) {
        claseRepo.save(clase);
    }

    /**
     * Método que busca una instancia de la clase clase por su id en la base de datos
     * @param id - Integer - Id de la instancia de la clase clase a buscar
     * @return clase - Instancia de la clase clase encontrada en la base de datos
     * @throws UserNotFoundException - Excepción lanzada cuando no se encuentra una instancia de la clase clase con el id especificado
     */
    public clase getClase(Integer id) throws UserNotFoundException {
        Optional<clase> result = claseRepo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("No se encontro una clase con esta id: "+id);
    }

    /**
     * Método que elimina una instancia de la clase clase de la base de datos por su id
     * @param id - Integer - Id de la instancia de la clase clase a eliminar
     * @throws UserNotFoundException - Excepción lanzada cuando no se encuentra una instancia de la clase clase con el id especificado
     */
    public void deleteClase(Integer id) throws UserNotFoundException {
        Long count = claseRepo.countById(id);
        if(count == null || count == 0){
            throw new UserNotFoundException("No se encontro una clase con esta id: "+id);
        }
        claseRepo.deleteById(id);
    }


    /**
     * Retorna una lista con todas las instancias de la entidad familia
     *
     * @return una lista de objetos de la clase familia
     */
    public List<familia> listAllFamilia(){
        return (List<familia>) familiaRepo.findAll();
    }

    /**
     * Guarda una instancia de la entidad familia en la base de datos
     *
     * @param familia objeto de la clase familia a guardar
     */
    public void saveFamilia(familia familia) {
        familiaRepo.save(familia);
    }

    /**
     * Obtiene una instancia de la entidad familia por su identificador
     *
     * @param id identificador de la instancia de la clase familia a obtener
     * @return objeto de la clase familia con el identificador proporcionado
     * @throws UserNotFoundException si no se encuentra ninguna instancia con el identificador proporcionado
     */
    public familia getFamilia(Integer id) throws UserNotFoundException {
        Optional<familia> result = familiaRepo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("No se encontro una familia con esta id: "+id);
    }

    /**
     * Elimina una instancia de la entidad familia de la base de datos
     *
     * @param id identificador de la instancia de la clase familia a eliminar
     * @throws UserNotFoundException si no se encuentra ninguna instancia con el identificador proporcionado
     */
    public void deleteFamilia(Integer id) throws UserNotFoundException {
        Long count = familiaRepo.countById(id);
        if(count == null || count == 0){
            throw new UserNotFoundException("No se encontro una familia con esta id: "+id);
        }
        familiaRepo.deleteById(id);
    }


    /**
     * Obtiene una lista de todos los filos
     * @return lista de filos
     */
    public List<filo> listAllFilo(){
        return (List<filo>) filoRepo.findAll();
    }

    /**
     * Guarda un filo en la base de datos
     * @param filo filo a guardar
     */
    public void saveFilo(filo filo) {
        filoRepo.save(filo);
    }

    /**
     * Obtiene un filo por su id
     * @param id id del filo a buscar
     * @return filo encontrado
     * @throws UserNotFoundException si no se encuentra el filo con el id especificado
     */
    public filo getFilo(Integer id) throws UserNotFoundException {
        Optional<filo> result = filoRepo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("No se encontro un filo con esta id: "+id);
    }

    /**
     * Elimina un filo por su id
     * @param id id del filo a eliminar
     * @throws UserNotFoundException si no se encuentra el filo con el id especificado
     */
    public void deleteFilo(Integer id) throws UserNotFoundException {
        Long count = filoRepo.countById(id);
        if(count == null || count == 0){
            throw new UserNotFoundException("No se encontro un filo con esta id: "+id);
        }
        filoRepo.deleteById(id);
    }


    /**
     * Método que devuelve una lista con todos los géneros.
     * @return Lista con todos los géneros.
     */
    public List<genero> listAllGenero(){
        return (List<genero>) generoRepo.findAll();
    }

    /**
     * Método que guarda un género.
     * @param genero Objeto genero que se desea guardar.
     */
    public void saveGenero(genero genero) {
        generoRepo.save(genero);
    }

    /**
     * Método que obtiene un género por su id.
     * @param id Id del género que se desea obtener.
     * @return Objeto genero correspondiente al id proporcionado.
     * @throws UserNotFoundException Si no se encuentra un género con el id proporcionado.
     */
    public genero getGenero(Integer id) throws UserNotFoundException {
        Optional<genero> result = generoRepo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("No se encontro un genero con esta id: "+id);

    }

    /**
     * Método que elimina un género por su id.
     * @param id Id del género que se desea eliminar.
     * @throws UserNotFoundException Si no se encuentra un género con el id proporcionado.
     */
    public void deleteGenero(Integer id) throws UserNotFoundException {
        Long count = generoRepo.countById(id);
        if(count == null || count == 0){
            throw new UserNotFoundException("No se encontro un genero con esta id: "+id);
        }
        generoRepo.deleteById(id);
    }


    /**
     * Recupera todas las órdenes almacenadas en la base de datos.
     * @return Lista de objetos orden.
     */
    public List<orden> listAllOrden(){
        return (List<orden>) ordenRepo.findAll();
    }

    /**
     * Guarda una orden en la base de datos.
     * @param orden Objeto orden que se quiere guardar.
     */
    public void saveOrden(orden orden) {
        ordenRepo.save(orden);
    }

    /**
     * Recupera una orden de la base de datos por su identificador.
     * @param id Identificador de la orden.
     * @return Objeto orden con el identificador dado.
     * @throws UserNotFoundException Si no se encuentra una orden con el identificador dado.
     */
    public orden getOrden(Integer id) throws UserNotFoundException {
        Optional<orden> result = ordenRepo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("No se encontro una orden con esta id: "+id);
    }

    /**
     * Elimina una orden de la base de datos por su identificador.
     * @param id Identificador de la orden que se desea eliminar.
     * @throws UserNotFoundException Si no se encuentra una orden con el identificador dado.
     */
    public void deleteOrden(Integer id) throws UserNotFoundException {
        Long count = ordenRepo.countById(id);
        if(count == null || count == 0){
            throw new UserNotFoundException("No se encontro una orden con esta id: "+id);
        }
        ordenRepo.deleteById(id);
    }



    /**
     * Devuelve una lista de todos los objetos de tipo reino almacenados en la base de datos.
     * @return lista de objetos de tipo reino.
     */
    public List<reino> listAllReino(){
        return (List<reino>) reinoRepo.findAll();
    }

    /**
     * Guarda un objeto de tipo reino en la base de datos.
     * @param reino objeto de tipo reino a guardar.
     */
    public void saveReino(reino reino) {
        reinoRepo.save(reino);
    }

    /**
     * Devuelve el objeto de tipo reino correspondiente a la id especificada.
     * @param id identificador del objeto de tipo reino a buscar.
     * @return objeto de tipo reino correspondiente a la id especificada.
     * @throws UserNotFoundException si no se encuentra un objeto de tipo reino con la id especificada.
     */
    public reino getReino(Integer id) throws UserNotFoundException {
        Optional<reino> result = reinoRepo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("No se encontro un usuario con esta id: "+id);
    }

    /**
     * Elimina el objeto de tipo reino correspondiente a la id especificada de la base de datos.
     * @param id identificador del objeto de tipo reino a eliminar.
     * @throws UserNotFoundException si no se encuentra un objeto de tipo reino con la id especificada.
     */
    public void deleteReino(Integer id) throws UserNotFoundException {
        Long count = reinoRepo.countById(id);
        if(count == null || count == 0){
            throw new UserNotFoundException("No se encontro un usuario con esta id: "+id);
        }
        reinoRepo.deleteById(id);
    }



    /**
     * Método que devuelve una lista de todas las imágenes.
     * @return Lista de todas las imágenes.
     */
    public List<image> listAllImage(){
        return (List<image>) imageRepo.findAll();
    }

    /**
     * Método que guarda una imagen en el repositorio.
     * @param image La imagen a guardar.
     */
    public void saveImage(image image) {
        imageRepo.save(image);
    }

    /**
     * Método que devuelve una página de imágenes paginadas.
     * @param pageNumber Número de página a devolver.
     * @param pageSize Tamaño de la página.
     * @return Página de imágenes.
     */
    public Page<image> findPaginatedImage(int pageNumber, int pageSize) {
        if (pageNumber <= 0) {
            pageNumber = 1;
        }
        Pageable pageable = PageRequest.of(pageNumber-1 , pageSize);
        return imageRepo.findAll(pageable);
    }

    /**
     * Método que busca imágenes por clave.
     * @param clave Clave de búsqueda.
     * @return Lista de imágenes que coinciden con la clave de búsqueda.
     */
    public List<image> busquedaImage(String clave){return imageRepo.busqueda(clave);}

    /**
     * Método que devuelve una imagen por su id.
     * @param id Id de la imagen a buscar.
     * @return Imagen encontrada.
     * @throws UserNotFoundException Si no se encuentra una imagen con el id proporcionado.
     */
    public image getImage(Integer id) throws UserNotFoundException {
        Optional<image> result = imageRepo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("No se encontro una imagen con esta id: "+id);
    }

    /**
     * Método que elimina una imagen por su id.
     * @param id Id de la imagen a eliminar.
     * @throws UserNotFoundException Si no se encuentra una imagen con el id proporcionado.
     */
    public void deleteImage(Integer id) throws UserNotFoundException {
        Long count = imageRepo.countById(id);
        if(count == null || count == 0){
            throw new UserNotFoundException("No se encontro una imagen con esta id: "+id);
        }
        imageRepo.deleteById(id);
    }

}




