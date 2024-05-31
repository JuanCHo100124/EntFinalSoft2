import React from 'react';
import PropTypes from 'prop-types';
import UsuariosRow from './UsuariosRow';

const UsuariosTable = (props) => {
  const { usuarios, onEdit, onDelete } = props;

  if (!Array.isArray(usuarios)) {
    return <p>Error</p>;
  }

  return (
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Nombre</th>
          <th>Correo</th>
          <th>Password</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        {usuarios.map((usuario) => (
          <UsuariosRow key={usuario.idUsuario} usuario={usuario} onEdit={onEdit} onDelete={onDelete} />
        ))}
      </tbody>
    </table>
  );
};

UsuariosTable.propTypes = {
  usuarios: PropTypes.arrayOf(PropTypes.shape({
    idUsuario: PropTypes.number.isRequired,
    nombre: PropTypes.string.isRequired,
    correo: PropTypes.string.isRequired,
    password: PropTypes.string.isRequired,
  })).isRequired,
  onEdit: PropTypes.func.isRequired,
  onDelete: PropTypes.func.isRequired,
};

export default UsuariosTable;
