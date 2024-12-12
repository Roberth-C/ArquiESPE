import axios from 'axios';

const API = axios.create({ baseURL: 'http://localhost:8080/api/' });

export const getUsers = () => API.get('/users');
export const getBooks = () => API.get('/books');
export const getLoans = () => API.get('/loans');
export const addUser = (user) => API.post('/users', user);
export const addBook = (book) => API.post('/books', book);
export const addLoan = (loan) => API.post('/loans', loan);
export const deleteBook = (id) => API.delete(`/books/${id}`);
export const deleteUser = (id) => API.delete(`/users/${id}`);
