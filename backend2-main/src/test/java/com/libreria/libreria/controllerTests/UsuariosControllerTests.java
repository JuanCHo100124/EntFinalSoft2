package com.libreria.libreria.controllerTests;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.libreria.libreria.controlller.UsuarioController;
import com.libreria.libreria.model.Entity.Usuario;
import com.libreria.libreria.model.Service.IUsuarioServicio;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UsuarioController.class)
public class UsuariosControllerTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private IUsuarioServicio usuarioServicio;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testListarUsuarios() throws Exception {
        when(usuarioServicio.listarUsuarios()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/usuarios/"))
            .andExpect(status().isOk())
            .andExpect(content().json("[]"));
        verify(usuarioServicio, times(1)).listarUsuarios();
    }

    @Test
    public void testObtenerUsuarioPorId() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        when(usuarioServicio.buscarPorId(1)).thenReturn(usuario);
        mockMvc.perform(get("/api/usuarios/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idUsuario").value(1));
        verify(usuarioServicio, times(1)).buscarPorId(1);
    }

    @Test
    public void testCrearUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombre("Nuevo Usuario");

        ObjectMapper objectMapper = new ObjectMapper();
        String usuarioJson = objectMapper.writeValueAsString(usuario);

        doNothing().when(usuarioServicio).guardarUsuario(usuario);

        mockMvc.perform(post("/api/usuarios/post/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(usuarioJson))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.idUsuario").value(1))
            .andExpect(jsonPath("$.nombre").value("Nuevo Usuario"));

        verify(usuarioServicio, times(1)).guardarUsuario(usuario);
    }

    @Test
    public void testActualizarUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombre("Usuario Actualizado");

        ObjectMapper objectMapper = new ObjectMapper();
        String usuarioJson = objectMapper.writeValueAsString(usuario);

        doNothing().when(usuarioServicio).guardarUsuario(usuario);

        mockMvc.perform(put("/api/usuarios/put/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(usuarioJson))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idUsuario").value(1))
            .andExpect(jsonPath("$.nombre").value("Usuario Actualizado"));

        verify(usuarioServicio, times(1)).guardarUsuario(usuario);
    }

    @Test
    public void testEliminarUsuario() throws Exception {
        doNothing().when(usuarioServicio).eliminarUsuario(1);

        mockMvc.perform(delete("/api/usuarios/delete/1"))
            .andExpect(status().isNoContent());

        verify(usuarioServicio, times(1)).eliminarUsuario(1);
    }
}
