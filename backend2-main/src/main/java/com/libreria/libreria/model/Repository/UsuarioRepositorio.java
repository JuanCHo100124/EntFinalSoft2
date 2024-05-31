package com.libreria.libreria.model.Repository;


import com.libreria.libreria.model.Entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends CrudRepository<Usuario, Integer> {
}
