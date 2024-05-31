
package com.libreria.libreria.model.Service;

import com.libreria.libreria.model.Entity.Libro;
import com.libreria.libreria.model.Repository.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LibroServicio implements ILibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;

    public LibroServicio() {
    }

    @Override
    public List<Libro> listarLibros() {
        return (List<Libro>) libroRepositorio.findAll();
    }

    @Override
    public void guardarLibro(Libro libro) {
        libroRepositorio.save(libro);
    }

    @Override
    public Libro buscarPorId(int id) {
        return libroRepositorio.findById(id).orElse(null);
    }

    @Override
    public void eliminarlibro(int id) {
        libroRepositorio.deleteById(id);
    }
}
