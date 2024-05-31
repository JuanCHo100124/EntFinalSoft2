package com.libreria.libreria.model.Service;

import com.libreria.libreria.model.Entity.Usuario;
import com.libreria.libreria.model.Repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicio implements IUsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public UsuarioServicio(){
    }
    
    @Override
    public List<Usuario> listarUsuarios() {
        return (List<Usuario>) usuarioRepositorio.findAll();
    }

    @Override
    public Usuario buscarPorId(int id) {
        return usuarioRepositorio.findById(id).orElse(null);
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuarioRepositorio.save(usuario);
    }

    @Override
    public void eliminarUsuario(int id) {
        usuarioRepositorio.deleteById(id);
    }

}
