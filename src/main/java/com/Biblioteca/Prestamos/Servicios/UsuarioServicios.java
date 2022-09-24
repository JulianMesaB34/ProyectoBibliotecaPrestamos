package com.Biblioteca.Prestamos.Servicios;

import com.Biblioteca.Prestamos.Entidades.Usuario;
import com.Biblioteca.Prestamos.Repositorio.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UsuarioServicios {
    UsuarioRepository repositorio;

    public UsuarioServicios(UsuarioRepository repositorio) {
        this.repositorio = repositorio;
    }
    public Usuario agregarUsuario(Usuario user){
        return repositorio.save(user);
    }

    public Usuario buscarCorreo(String email){
        return  repositorio.findByEmail(email);
    }
    public Usuario comprobarUsuario(Map<String,Object> datos){
        Usuario user=buscarCorreo((String)datos.get("email"));
        if(user==null){
            String name= (String) datos.get("nickname");
            String imagen= (String) datos.get("picture");
            String authId= (String) datos.get("sub");
            String correo= (String) datos.get("email");
            Usuario usuario= new Usuario(correo,name,imagen,authId);
            user=agregarUsuario(usuario);
            return user;
        }else{
        return user;
        }
    }
}
