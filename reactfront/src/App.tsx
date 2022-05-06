import React from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import FolderDocs from './pages/FolderDocs';
import Home from './pages/Home';
import Footer from './components/Footer';
import Navbar from './components/Navbar';
import { Login } from '@mui/icons-material';

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='/folders' element={<FolderDocs />} />
          <Route path='/login' element={<Login />} />
        </Routes>
        <Footer />
      </Router>
    </div>
  );
}

export default App;
