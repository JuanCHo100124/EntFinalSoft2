import React from 'react';

function LibrosRow({ libro, onEdit, onDelete }) {
  const handleEdit = () => {
    onEdit(libro);
  };

  const handleDelete = () => {
    onDelete(libro.idLibro);
  };

  return (
    <tr>
      <td>{libro.idLibro}</td>
      <td>{libro.titulo}</td>
      <td>{libro.autor}</td>
      <td>{libro.anioPublicacion}</td>
      <td>
        <button onClick={handleEdit}>Editar✏️</button>
        <button onClick={handleDelete}>Eliminar🚮</button>
      </td>
    </tr>
  );
}

export default LibrosRow;
