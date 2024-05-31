package com.libreria.libreria.model.Service;

import com.libreria.libreria.model.Entity.Usuario;

import java.util.List;

public interface IUsuarioServicio {
    List<Usuario> listarUsuarios();
    Usuario buscarPorId(int id);
    void guardarUsuario(Usuario usuario);
    void eliminarUsuario(int id);
}
