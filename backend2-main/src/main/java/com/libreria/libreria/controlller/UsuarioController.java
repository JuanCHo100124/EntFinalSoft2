package com.libreria.libreria.controlller;

import com.libreria.libreria.model.Entity.Usuario;
import com.libreria.libreria.model.Service.IUsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController extends ApiBaseController {

    @Autowired
    private IUsuarioServicio usuarioServicio;

    public UsuarioController(){
    }

    @GetMapping("/")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        try {
            List<Usuario> listadoUsuarios = usuarioServicio.listarUsuarios();
            return ResponseEntity.ok(listadoUsuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable("id") int id) {
        try {
            Usuario usuario = usuarioServicio.buscarPorId(id);
            if (usuario != null) {
                return ResponseEntity.ok(usuario);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/post/")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        try {
            usuarioServicio.guardarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable("id") int idUsuario, @RequestBody Usuario usuario) {
        try {
            usuario.setIdUsuario(idUsuario);
            usuarioServicio.guardarUsuario(usuario);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable("id") int idUsuario) {
        try {
            usuarioServicio.eliminarUsuario(idUsuario);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
