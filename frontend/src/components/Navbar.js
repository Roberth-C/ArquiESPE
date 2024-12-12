import React from "react";
import { Navbar as BootstrapNavbar, Nav } from "react-bootstrap";

const CustomNavbar = () => {
    return (
        <BootstrapNavbar bg="primary" variant="dark" expand="lg">
            <div className="container">
                <BootstrapNavbar.Brand href="/">📚 Biblioteca</BootstrapNavbar.Brand>
                <BootstrapNavbar.Toggle aria-controls="basic-navbar-nav" />
                <BootstrapNavbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Nav.Link href="/users">Usuarios</Nav.Link>
                        <Nav.Link href="/books">Libros</Nav.Link>
                        <Nav.Link href="/loans">Préstamos</Nav.Link>
                    </Nav>
                </BootstrapNavbar.Collapse>
            </div>
        </BootstrapNavbar>
    );
};

export default CustomNavbar;
