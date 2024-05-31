
package com.libreria.libreria.model.Repository;

import com.libreria.libreria.model.Entity.Libro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends CrudRepository<Libro, Integer> {
}
