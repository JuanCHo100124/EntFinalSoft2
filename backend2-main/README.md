Juan David Camacho Molina - Backend 2 Software II -
Librería
Este es un proyecto de ejemplo para una aplicación web de una librería desarrollada con Spring Boot. La aplicación permite gestionar el catálogo de libros,
incluyendo operaciones como listar libros, agregar nuevos libros, editar la información de los libros existentes y eliminar libros del catálogo.
Requisitos previos:
JDK 17 o superior
MySQL


-Configura la conexión a la base de datos MySQL en el archivo application.properties.
codigo de MYSQL:
CREATE DATABASE libreria;
USE libreria;
CREATE TABLE libro (
    id_libro INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100),
    autor VARCHAR(100),
    anio_publicacion INT
);

CREATE TABLE Usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    correo VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);