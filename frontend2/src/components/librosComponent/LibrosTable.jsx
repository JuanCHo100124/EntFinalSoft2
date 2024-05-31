import React from 'react';
import PropTypes from 'prop-types';
import LibrosRow from './LibrosRow';

const LibrosTable = (props) => {
  const { libros, onEdit, onDelete } = props;

  if (!Array.isArray(libros)) {
    return <p>Error</p>;
  }

  return (
    <table>
      <thead>
        <tr>
          <th>Código</th>
          <th>Título</th>
          <th>Autor</th>
          <th>Año de Publicación</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        {libros.map((libro) => (
          <LibrosRow key={libro.idLibro} libro={libro} onEdit={onEdit} onDelete={onDelete} />
        ))}
      </tbody>
    </table>
  );
};

LibrosTable.propTypes = {
  libros: PropTypes.arrayOf(PropTypes.shape({
    idLibro: PropTypes.number.isRequired,
    titulo: PropTypes.string.isRequired,
    autor: PropTypes.string.isRequired,
    añoPublicacion: PropTypes.number.isRequired,
  })).isRequired,
  onEdit: PropTypes.func.isRequired,
  onDelete: PropTypes.func.isRequired,
};

export default LibrosTable;
