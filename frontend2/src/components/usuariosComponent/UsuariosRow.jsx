import React from 'react';

function UsuariosRow({ usuario, onEdit, onDelete }) {
  const handleEdit = () => {
    onEdit(usuario);
  };

  const handleDelete = () => {
    onDelete(usuario.idUsuario);
  };

  return (
    <tr>
      <td>{usuario.idUsuario}</td>
      <td>{usuario.nombre}</td>
      <td>{usuario.correo}</td>
      <td>{usuario.password}</td>
      <td>
        <button onClick={handleEdit}>Editar✏️</button>
        <button onClick={handleDelete}>Eliminar🚮</button>
      </td>
    </tr>
  );
}

export default UsuariosRow;
