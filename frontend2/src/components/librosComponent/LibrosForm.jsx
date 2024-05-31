
import { useState, useEffect } from 'react';

function LibrosForm({ onSubmit, initialLibro }) {
  const [idLibro, setIdLibro] = useState(null);
  const [titulo, setTitulo] = useState('');
  const [autor, setAutor] = useState('');
  const [anioPublicacion, setAnioPublicacion] = useState('');

  const handleTituloChange = (event) => {
    setTitulo(event.target.value);
  };

  const handleAutorChange = (event) => {
    setAutor(event.target.value);
  };

  const handleAnioPublicacionChange = (event) => {
    setAnioPublicacion(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const libroData = { idLibro, titulo, autor, anioPublicacion };
    onSubmit(libroData);
    setIdLibro(null);
    setTitulo('');
    setAutor('');
    setAnioPublicacion('');
  };

  useEffect(() => {
    if (initialLibro) {
      setIdLibro(initialLibro.idLibro);
      setTitulo(initialLibro.titulo);
      setAutor(initialLibro.autor);
      setAnioPublicacion(initialLibro.anioPublicacion);
    }
  }, [initialLibro]);

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="Título"
        value={titulo}
        onChange={handleTituloChange}
        required
      />
      <input
        type="text"
        placeholder="Autor"
        value={autor}
        onChange={handleAutorChange}
        required
      />
      <input
        type="text"
        placeholder="Año de Publicación"
        value={anioPublicacion}
        onChange={handleAnioPublicacionChange}
        required
      />
      <button type="submit">{initialLibro ? 'Actualizar' : 'Guardar'}</button>
    </form>
  );
}

export default LibrosForm;

