
package com.libreria.libreria.model.Service;

import com.libreria.libreria.model.Entity.Libro;
import java.util.List;

public interface ILibroServicio {
   List<Libro> listarLibros();

   void guardarLibro(Libro libro);

   Libro buscarPorId(int id);

   void eliminarlibro(int id);
}
