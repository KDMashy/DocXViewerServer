import React, { useEffect, useState } from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import FolderDocs from './pages/FolderDocs';
import Home from './pages/Home';
import Footer from './components/Footer';
import Navbar from './components/Navbar';
import { Login } from '@mui/icons-material';
import Cookies from 'universal-cookie';
import Notfound from './pages/Notfound';
import Profile from './pages/Profile';

const cookies = new Cookies();

  const [logged, setLogged] = useState(false);

  useEffect(() => {
    var isLoggedIn = cookies.get('loggedin');
    if (isLoggedIn === 'true'){
      setLogged(true);
    } else {
      setLogged(false);
    }
  });

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='/folders' element={<FolderDocs />} />
          {
            logged ? [
              <Route path='/login' element={<Notfound />} />,
              <Route path='/profile' element={<Profile />} />,
              <Route path='/folders' element={<FolderDocs />} />] : [
              <Route path='/profile' element={<Notfound />} />,
              <Route path='/login' element={<Login />} />,
              <Route path='/folders' element={<Notfound />} />
          ]}
        </Routes>
        <Footer />
      </Router>
    </div>
  );
}

export default App;
