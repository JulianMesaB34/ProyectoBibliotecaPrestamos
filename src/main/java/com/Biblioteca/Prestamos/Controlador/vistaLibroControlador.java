package com.Biblioteca.Prestamos.Controlador;

import com.Biblioteca.Prestamos.Entidades.Libro;
import com.Biblioteca.Prestamos.Entidades.Usuario;
import com.Biblioteca.Prestamos.Servicios.LibroServicios;
import com.Biblioteca.Prestamos.Servicios.UsuarioServicios;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class vistaLibroControlador {
    private LibroServicios servicio;
    UsuarioServicios userServicio;

    public vistaLibroControlador(LibroServicios servicio, UsuarioServicios userServicio) {
        this.servicio = servicio;
        this.userServicio = userServicio;
    }

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal OidcUser principal){
        if(principal !=null){
            Usuario user=userServicio.comprobarUsuario(principal.getClaims());

            model.addAttribute("user",user);
            //System.out.println(principal.getClaims());
        }
        return "index";
    }

    @GetMapping("/Prueba/{nombre}")
    public String prueba(@PathVariable("nombre") String nombre, Model model){
        model.addAttribute("nombre",nombre);
        return "Libros";
    }

    @GetMapping("/Libros")
    public String prueba( Model model){
        List<Libro> lista=this.servicio.listarLibros();
        model.addAttribute("lista",lista);
        return "Libros";
    }

    @GetMapping("formLibro")
    public String mostrarFormulario(Model model){
        model.addAttribute("libro",new Libro());
        return "registrarLibro";
    }

    @PostMapping("/RegistrarLibro")
    public String agregarLibro(@ModelAttribute("libro") Libro libro, Model model, RedirectAttributes attributes){
        if(servicio.agregarLibro(libro)){
            attributes.addFlashAttribute("mensajeOk","Libro registrado exitosamente");
        }else{
            attributes.addFlashAttribute("error","Error, el libro no se registro");
        }
        return "redirect:/Libros";
    }

    @GetMapping("/editarLibro/{isbn}")
    public String pasarLibro(@PathVariable("isbn")String isbn, Model model){
        model.addAttribute("libro",servicio.buscarLibros1(isbn));
        return "editarLibro";
    }
    @GetMapping("/eliminarLibro/{isbn}")
    public String eliminarLibro(@PathVariable("isbn")String isbn, Model model){
        servicio.eliminarLibro(isbn);
        return "redirect:/Libros";
    }

    @PostMapping("/guardarEditado/{isbn}")
    public String actualizarLibro(@PathVariable("isbn") String isbn, @ModelAttribute("libro") Libro libro, Model model){
        Libro lib=servicio.buscarLibros1(isbn);
        //lib.setIsbn(libro.getIsbn());
        lib.setTitulo(libro.getTitulo());
        lib.setAutor(libro.getAutor());
        lib.setEditorial(libro.getEditorial());
        lib.setNum_pag(libro.getNum_pag());
        servicio.actualizarLibro(lib);
        return "redirect:/Libros";

    }


}
