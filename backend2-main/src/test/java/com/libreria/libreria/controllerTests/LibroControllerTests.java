
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

import com.libreria.libreria.controlller.LibroController;
import com.libreria.libreria.model.Entity.Libro;
import com.libreria.libreria.model.Service.ILibroServicio;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(LibroController.class)
public class LibroControllerTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ILibroServicio libroServicio;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testListarLibros() throws Exception {
        when(libroServicio.listarLibros()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/libreria/"))
            .andExpect(status().isOk())
            .andExpect(content().json("[]"));
        verify(libroServicio, times(1)).listarLibros();
    }

    @Test
    public void testObtenerLibroPorId() throws Exception {
        Libro libro = new Libro();
        libro.setIdLibro(1);
        when(libroServicio.buscarPorId(1)).thenReturn(libro);
        mockMvc.perform(get("/api/libreria/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idLibro").value(1));
        verify(libroServicio, times(1)).buscarPorId(1);
    }

    @Test
    public void testCrearLibro() throws Exception {
        Libro libro = new Libro();
        libro.setIdLibro(1);
        libro.setTitulo("Nuevo Libro");

        ObjectMapper objectMapper = new ObjectMapper();
        String libroJson = objectMapper.writeValueAsString(libro);

        doNothing().when(libroServicio).guardarLibro(libro);

        mockMvc.perform(post("/api/libreria/post/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(libroJson))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.idLibro").value(1))
            .andExpect(jsonPath("$.titulo").value("Nuevo Libro"));

        verify(libroServicio, times(1)).guardarLibro(libro);
    }

    @Test
    public void testActualizarLibro() throws Exception {
        Libro libro = new Libro();
        libro.setIdLibro(1);
        libro.setTitulo("Libro Actualizado");
        

        ObjectMapper objectMapper = new ObjectMapper();
        String libroJson = objectMapper.writeValueAsString(libro);

        doNothing().when(libroServicio).guardarLibro(libro);

        mockMvc.perform(put("/api/libreria/put/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(libroJson))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idLibro").value(1))
            .andExpect(jsonPath("$.titulo").value("Libro Actualizado"));

        verify(libroServicio, times(1)).guardarLibro(libro);
    }

    @Test
    public void testEliminarLibro() throws Exception {
        doNothing().when(libroServicio).eliminarlibro(1);

        mockMvc.perform(delete("/api/libreria/delete/1"))
            .andExpect(status().isNoContent());

        verify(libroServicio, times(1)).eliminarlibro(1);
    }
}
