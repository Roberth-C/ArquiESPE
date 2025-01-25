// BookPage.js
import React, { useEffect, useState } from 'react';
import {
  getAllBooks,
  getBookByIsbn,
  getBooksByTitle,
  getBooksByAuthor,
  filterBooks,
  addBook,
  updateBook,
  deleteBook,
} from '../services/bookService';
import 'bootstrap/dist/css/bootstrap.min.css';

function BookPage() {
  const [books, setBooks] = useState([]);
  const [selectedBookId, setSelectedBookId] = useState(null);
  const [activeTab, setActiveTab] = useState('listBooks'); // 'listBooks', 'addBook', 'editBook'
  const [formData, setFormData] = useState({
    title: '',
    author: '',
    isbn: '',
    librarianId: '', // Asegurarse de que este campo también esté en el estado inicial
    category: '',
    year: '',
    quantity: 1,
    availableQuantity: 1,
    location: '',
    status: 'available' // Asume un estado predeterminado, ajusta según sea necesario
});

  useEffect(() => {
    fetchBooks();
  }, []);

  const fetchBooks = async () => {
    try {
      const data = await getAllBooks();
      setBooks(data);
    } catch (error) {
      console.error('Error al obtener los libros:', error.message);
    }
  };

  const handleDeleteBook = async (id) => {
    try {
      await deleteBook(id);
      fetchBooks();
    } catch (error) {
      console.error('Error al eliminar el libro:', error.message);
    }
  };

  const handleFormSubmit = async (event) => {
    event.preventDefault();
    if (!formData.title || !formData.author || !formData.isbn || !formData.librarianId) {
        alert('Título, Autor, ISBN y ID del Bibliotecario son obligatorios.');
        return;
    }

    try {
        if (activeTab === 'addBook') {
            await addBook(formData);
            alert("Libro agregado con éxito");
        } else if (activeTab === 'editBook' && selectedBookId) {
            await updateBook(selectedBookId, formData);
        }
        fetchBooks(); // Actualizar la lista de libros
        setFormData({
            title: '',
            author: '',
            isbn: '',
            category: '',
            year: new Date().getFullYear(),
            quantity: 1,
            availableQuantity: 1,
            location: '',
            librarianId: '',
            status: 'available'
        }); // Limpiar el formulario
    } catch (error) {
        console.error('Error al guardar el libro:', error.message);
        alert(error.message); // Mostrar el mensaje de error al usuario
    }
};


  const handleEdit = (book) => {
    setActiveTab('editBook');
    setSelectedBookId(book.id);
    setFormData({
      title: book.title,
      author: book.author,
      isbn: book.isbn,
      category: book.category,
      year: book.year,
      status: book.status,
    });
  };

  return (
    <div className="book-page">
      <header className="header">
        <nav className="nav nav-tabs">
          <button onClick={() => setActiveTab('listBooks')} className={`nav-link ${activeTab === 'listBooks' ? 'active' : ''}`}>
            Listar Libros
          </button>
          <button onClick={() => setActiveTab('addBook')} className={`nav-link ${activeTab === 'addBook' ? 'active' : ''}`}>
            Agregar Libro
          </button>
          <button onClick={() => { localStorage.clear(); window.location.reload(); }} className="btn btn-danger ms-auto">
            Cerrar Sesión
          </button>
        </nav>
      </header>

      <main className="mt-4">
        {activeTab === 'listBooks' && (
          <div>
            <h1>Gestión de Libros</h1>
            <table className="table table-striped">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Título</th>
                  <th>Autor</th>
                  <th>ISBN</th>
                  <th>Categoría</th>
                  <th>Año</th>
                  <th>Estado</th>
                  <th>Acciones</th>
                </tr>
              </thead>
              <tbody>
                {books.map((book) => (
                  <tr key={book.id}>
                    <td>{book.id}</td>
                    <td>{book.title}</td>
                    <td>{book.author}</td>
                    <td>{book.isbn}</td>
                    <td>{book.category}</td>
                    <td>{book.year}</td>
                    <td>{book.status}</td>
                    <td>
                      <button onClick={() => handleEdit(book)} className="btn btn-warning me-2">Editar</button>
                      <button onClick={() => handleDeleteBook(book.id)} className="btn btn-danger">Eliminar</button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}

        {['addBook', 'editBook'].includes(activeTab) && (
          <div>
            <h1>{activeTab === 'addBook' ? 'Agregar Libro' : 'Editar Libro'}</h1>
            <form onSubmit={(e) => handleFormSubmit(e)}>
              <div className="mb-3">
                <label className="form-label">ISBN</label>
                <input
                  type="text"
                  className="form-control"
                  value={formData.isbn}
                  onChange={(e) => setFormData({ ...formData, isbn: e.target.value })}
                  required
                />
              </div>
              <div className="mb-3">
                <label className="form-label">Título</label>
                <input
                  type="text"
                  className="form-control"
                  value={formData.title}
                  onChange={(e) => setFormData({ ...formData, title: e.target.value })}
                  required
                />
              </div>
              
              <div className="mb-3">
                <label className="form-label">Autor</label>
                <input
                  type="text"
                  className="form-control"
                  value={formData.author}
                  onChange={(e) => setFormData({ ...formData, author: e.target.value })}
                  required
                />
              </div>
              <div className="mb-3">
                <label className="form-label">Categoría</label>
                <input
                  type="text"
                  className="form-control"
                  value={formData.category}
                  onChange={(e) => setFormData({ ...formData, category: e.target.value })}
                />
              </div>
              <input
            type="text"
            placeholder="Título del libro"
            value={formData.title}
            onChange={(e) => setFormData({ ...formData, title: e.target.value })}
            required
        />
        <input
            type="text"
            placeholder="Autor del libro"
            value={formData.author}
            onChange={(e) => setFormData({ ...formData, author: e.target.value })}
            required
        />
        <input
            type="text"
            placeholder="ISBN del libro"
            value={formData.isbn}
            onChange={(e) => setFormData({ ...formData, isbn: e.target.value })}
            required
        />
        <input
            type="text"
            placeholder="ID del Bibliotecario"
            value={formData.librarianId}
            onChange={(e) => setFormData({ ...formData, librarianId: e.target.value })}
            required
        />
              <div className="mb-3">
                <label className="form-label">Año de Publicación</label>
                <input
                  type="number"
                  className="form-control"
                  value={formData.year}
                  onChange={(e) => setFormData({ ...formData, year: e.target.value })}
                />
              </div>
              <div className="mb-3">
                <label className="form-label">Cantidad Total en Inventario</label>
                <input
                  type="number"
                  className="form-control"
                  value={formData.quantity}
                  onChange={(e) => setFormData({ ...formData, quantity: e.target.value })}
                  required
                />
              </div>
              <div className="mb-3">
                <label className="form-label">Cantidad Disponible para Reservas</label>
                <input
                  type="number"
                  className="form-control"
                  value={formData.availableQuantity}
                  onChange={(e) => setFormData({ ...formData, availableQuantity: e.target.value })}
                  required
                />
              </div>
              <div className="mb-3">
                <label className="form-label">Ubicación</label>
                <input
                  type="text"
                  className="form-control"
                  value={formData.location}
                  onChange={(e) => setFormData({ ...formData, location: e.target.value })}
                />
              </div>
              <button type="submit" className="btn btn-primary">{activeTab === 'addBook' ? 'Agregar' : 'Guardar Cambios'}</button>
            </form>
          </div>
        )}

      </main>
    </div>
  );
}

export default BookPage;
