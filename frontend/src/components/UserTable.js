import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Table, Button, Form, Modal, Container, Row, Col } from 'react-bootstrap';

const UserTable = () => {
    const [users, setUsers] = useState([]);
    const [editingUser, setEditingUser] = useState(null);
    const [newUser, setNewUser] = useState(false);
    const [showModal, setShowModal] = useState(false);

    useEffect(() => {
        fetchUsers();
    }, []);

    const fetchUsers = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/users');
            setUsers(response.data);
        } catch (error) {
            console.error('Error fetching users:', error);
        }
    };

    const handleEdit = (user) => {
        setEditingUser(user);
        setShowModal(true);
    };

    const handleSave = async () => {
        try {
            await axios.put(`http://localhost:8080/api/users/${editingUser.idInstitucional}`, editingUser);
            setEditingUser(null);
            setShowModal(false);
            fetchUsers();
        } catch (error) {
            console.error('Error updating user:', error);
        }
    };

    const handleSuspend = async (idInstitucional) => {
        try {
            await axios.put(`http://localhost:8080/api/users/${idInstitucional}/suspend`);
            fetchUsers();
        } catch (error) {
            console.error('Error suspending user:', error);
        }
    };

    const handleActivate = async (idInstitucional) => {
        try {
            await axios.put(`http://localhost:8080/api/users/${idInstitucional}/activate`);
            fetchUsers();
        } catch (error) {
            console.error('Error activating user:', error);
        }
    };

    const handleRegister = async () => {
        try {
            await axios.post('http://localhost:8080/api/users', editingUser);
            setNewUser(false);
            setEditingUser(null);
            fetchUsers();
        } catch (error) {
            console.error('Error creating user:', error);
        }
    };

    return (
        <Container className="mt-4">
            <Row>
                <Col>
                    <h2 className="text-center">Gestión de Usuarios</h2>
                    <Button variant="primary" className="mb-3" onClick={() => { setNewUser(true); setShowModal(true); }}>Registrar Usuario</Button>
                    <Table striped bordered hover>
                        <thead>
                            <tr>
                                <th>ID Institucional</th>
                                <th>Nombre</th>
                                <th>Correo</th>
                                <th>Rol</th>
                                <th>Estado</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            {users.map(user => (
                                <tr key={user.idInstitucional}>
                                    <td>{user.idInstitucional}</td>
                                    <td>{user.username}</td>
                                    <td>{user.email}</td>
                                    <td>{user.role}</td>
                                    <td>{user.status ? 'Activo' : 'Suspendido'}</td>
                                    <td>
                                        {user.status ? (
                                            <Button variant="danger" size="sm" onClick={() => handleSuspend(user.idInstitucional)}>Suspender</Button>
                                        ) : (
                                            <Button variant="success" size="sm" onClick={() => handleActivate(user.idInstitucional)}>Activar</Button>
                                        )}
                                        <Button variant="info" size="sm" className="ms-2" onClick={() => handleEdit(user)}>Editar</Button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                </Col>
            </Row>

            <Modal show={showModal} onHide={() => { setShowModal(false); setEditingUser(null); setNewUser(false); }}>
                <Modal.Header closeButton>
                    <Modal.Title>{newUser ? 'Registrar Usuario' : 'Editar Usuario'}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group className="mb-3">
                            <Form.Label>Nombre</Form.Label>
                            <Form.Control
                                type="text"
                                value={editingUser?.username || ''}
                                onChange={(e) => setEditingUser({ ...editingUser, username: e.target.value })}
                            />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Correo</Form.Label>
                            <Form.Control
                                type="email"
                                value={editingUser?.email || ''}
                                onChange={(e) => setEditingUser({ ...editingUser, email: e.target.value })}
                            />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Rol</Form.Label>
                            <Form.Select
                                value={editingUser?.role || ''}
                                onChange={(e) => setEditingUser({ ...editingUser, role: e.target.value })}>
                                <option value="">Seleccione un rol</option>
                                <option value="ADMIN">Admin</option>
                                <option value="STUDENT">Estudiante</option>
                                <option value="TEACHER">Profesor</option>
                            </Form.Select>
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={() => { setShowModal(false); setEditingUser(null); setNewUser(false); }}>Cancelar</Button>
                    <Button variant="primary" onClick={newUser ? handleRegister : handleSave}>Guardar</Button>
                </Modal.Footer>
            </Modal>
        </Container>
    );
};

export default UserTable;
