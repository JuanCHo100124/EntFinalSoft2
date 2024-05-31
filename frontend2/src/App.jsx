
import { useState, useEffect } from 'react';
import axios from 'axios';
import Registro from './components/Registro';
import Login from './components/Login';

import LibrosTable from './components/librosComponent/LibrosTable';
import LibrosForm from './components/librosComponent/LibrosForm';

import UsuariosTable from './components/usuariosComponent/UsuariosTable';
import UsuariosForm from './components/usuariosComponent/UsuariosForm';

import './App.css';

function App() {
  // Estado y funciones para gestionar libros
  const [libros, setLibros] = useState([]);
  const [editingLibro, setEditingLibro] = useState(null);

  const fetchLibros = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/libreria/');
      
      setLibros(response.data);
    } catch (error) {
      console.log('Error al cargar los libros:', error);
    }
  };
  

  useEffect(() => {
    fetchLibros();
  }, []);

  const getNextLibroId = () => {
    if (libros.length === 0) {
      return 1;
    } else {
      const ids = libros.map(libro => libro.idLibro);
      return Math.max(...ids) + 1;
    }
  };

  const handleCreateOrUpdateLibro = async (libroData) => {
    try {
      if (editingLibro) {
        await axios.put(`http://localhost:8080/api/libreria/put/${editingLibro.idLibro}`, libroData);
      } else {
        // Asignar nueva ID
        const newId = getNextLibroId();
        libroData.idLibro = newId;
        await axios.post('http://localhost:8080/api/libreria/post/', libroData);
      }
      fetchLibros();
      setEditingLibro(null);
    } catch (error) {
      console.log('Error al crear o actualizar libro:', error);
    }
  };

  const handleEditLibro = (libro) => {
    setEditingLibro(libro);
  };

  const handleDeleteLibro = async (idLibro) => {
    try {
      await axios.delete(`http://localhost:8080/api/libreria/delete/${idLibro}`);
      fetchLibros();
    } catch (error) {
      console.log('Error al eliminar libro:', error);
    }
  };

  // Estado y funciones para gestionar usuarios
  const [usuarios, setUsuarios] = useState([]);
  const [editingUsuario, setEditingUsuario] = useState(null);

  const fetchUsuarios = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/usuarios/');
      setUsuarios(response.data);
    } catch (error) {
      console.log('Error al cargar los usuarios:', error);
    }
  };

  useEffect(() => {
    fetchUsuarios();
  }, []);

  const getNextUsuarioId = () => {
    if (usuarios.length === 0) {
      return 1;
    } else {
      const ids = usuarios.map(usuario => usuario.idUsuario);
      return Math.max(...ids) + 1;
    }
  };

  const handleCreateOrUpdateUsuario = async (usuarioData) => {
    try {
      if (editingUsuario) {
        await axios.put(`http://localhost:8080/api/usuarios/put/${editingUsuario.idUsuario}`, usuarioData);
      } else {
        // Asignar nueva ID
        const newId = getNextUsuarioId();
        usuarioData.idUsuario = newId;
        await axios.post('http://localhost:8080/api/usuarios/post/', usuarioData);
      }
      fetchUsuarios();
      setEditingUsuario(null);
    } catch (error) {
      console.log('Error al crear o actualizar usuario:', error);
    }
  };

  const handleEditUsuario = (usuario) => {
    setEditingUsuario(usuario);
  };

  const handleDeleteUsuario = async (idUsuario) => {
    try {
      await axios.delete(`http://localhost:8080/api/usuarios/delete/${idUsuario}`);
      fetchUsuarios();
    } catch (error) {
      console.log('Error al eliminar usuario:', error);
    }
  };

  return (
    
    <div className="App">
      <Login/>
      <Registro/>
      
      <h1>Api Librería</h1>
      <br />
      <h2>Lista de Libros</h2>
      <LibrosTable libros={libros} onEdit={handleEditLibro} onDelete={handleDeleteLibro} />
      <h2>{editingLibro ? 'Editar libro' : 'Ingresar libro'}</h2>
      <LibrosForm onSubmit={handleCreateOrUpdateLibro} initialLibro={editingLibro} />

      <br />
      <h2>Lista de Usuarios</h2>
      <UsuariosTable usuarios={usuarios} onEdit={handleEditUsuario} onDelete={handleDeleteUsuario} />
      <h2>{editingUsuario ? 'Editar Usuario' : 'Ingresar Usuario'}</h2>
      <UsuariosForm onSubmit={handleCreateOrUpdateUsuario} initialUsuario={editingUsuario} />
    </div>
  );
}

export default App;



