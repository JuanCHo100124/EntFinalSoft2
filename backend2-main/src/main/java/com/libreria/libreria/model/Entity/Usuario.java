package com.libreria.libreria.model.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "usuario")
@Data
public class Usuario {
   
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private int idUsuario;
   private String nombre;
   private String correo;
   private String password;
}
