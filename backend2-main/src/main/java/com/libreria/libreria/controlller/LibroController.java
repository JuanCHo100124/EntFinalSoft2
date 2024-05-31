
package com.libreria.libreria.controlller;

import com.libreria.libreria.model.Entity.Libro;
import com.libreria.libreria.model.Service.ILibroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/libreria")
public class LibroController extends ApiBaseController{

    @Autowired
    private ILibroServicio libroServicio;

    public LibroController() {
    }

    @GetMapping("/")
    public ResponseEntity<List<Libro>> listarLibros() {
        try {
            List<Libro> listadoLibros = libroServicio.listarLibros();
            return ResponseEntity.ok(listadoLibros);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable("id") int id) {
        try {
            Libro libro = libroServicio.buscarPorId(id);
            if (libro != null) {
                return ResponseEntity.ok(libro);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/post/")
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        try {
            libroServicio.guardarLibro(libro);
            return ResponseEntity.status(HttpStatus.CREATED).body(libro);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable("id") int idLibro, @RequestBody Libro libro) {
        try {
            libro.setIdLibro(idLibro);
            libroServicio.guardarLibro(libro);
            return ResponseEntity.ok(libro);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable("id") int idLibro) {
        try {
            libroServicio.eliminarlibro(idLibro);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
