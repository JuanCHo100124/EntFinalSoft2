package com.libreria.libreria.model.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table( name = "Libro" )
@Data
public class Libro {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int idLibro;
   private String titulo;
   private String autor;
   private int anioPublicacion;

}
