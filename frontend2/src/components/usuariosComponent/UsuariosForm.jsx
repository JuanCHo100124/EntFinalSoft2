import { useState, useEffect } from 'react';

function UsuariosForm({ onSubmit, initialUsuario }) {
  const [idUsuario, setIdUsuario] = useState(null);
  const [nombre, setNombre] = useState('');
  const [correo, setCorreo] = useState('');
  const [password, setPassword] = useState('');

  const handleNombreChange = (event) => {
    setNombre(event.target.value);
  };

  const handleCorreoChange = (event) => {
    setCorreo(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const usuarioData = { idUsuario, nombre, correo, password };
    onSubmit(usuarioData);
    setIdUsuario(null);
    setNombre('');
    setCorreo('');
    setPassword('');
  };

  useEffect(() => {
    if (initialUsuario) {
      setIdUsuario(initialUsuario.idUsuario);
      setNombre(initialUsuario.nombre);
      setCorreo(initialUsuario.correo);
      setPassword(initialUsuario.password);
    }
  }, [initialUsuario]);

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="Nombre"
        value={nombre}
        onChange={handleNombreChange}
        required
      />
      <input
        type="email"
        placeholder="Correo"
        value={correo}
        onChange={handleCorreoChange}
        required
      />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={handlePasswordChange}
        required
      />
      <button type="submit">{initialUsuario ? 'Actualizar' : 'Guardar'}</button>
    </form>
  );
}

export default UsuariosForm;
