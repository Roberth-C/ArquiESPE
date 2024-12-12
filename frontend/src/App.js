import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Navbar from './components/Navbar';
import UserTable from './components/UserTable';
import BooksPage from './pages/BooksPage';
import LoansPage from './pages/LoansPage';
import LoginPage from './pages/LoginPage';


const App = () => {
    return (
        <Router>
            <Navbar />
            <div className="container mt-4">
                <Routes>
                    <Route path="/login" element={<LoginPage />} />
                    <Route path="/users" element={<UserTable />} />
                    <Route path="/books" element={<BooksPage />} />
                    <Route path="/loans" element={<LoansPage />} />
                </Routes>
            </div>
        </Router>
    );
};

export default App;
