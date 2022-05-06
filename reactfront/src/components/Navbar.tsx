import React, { useLayoutEffect, useState } from 'react'
import { Link } from 'react-router-dom';
import ReorderIcon from '@mui/icons-material/Reorder';
import Logo from '../res/logo.gif'
import "../styles/Navbar.css";

function Navbar() {
    const [openLinks, setOpenLinks] = useState(false);
 
    function useWindowSize() {
      const [size, setSize] = useState([0, 0]);
      useLayoutEffect(() => {
        function updateSize() {
          setSize([window.innerWidth, window.innerHeight]);
          if (window.innerWidth > 600){
            setOpenLinks(openLinks);
          }
        }
        window.addEventListener('resize', updateSize);
        updateSize();
        return () => window.removeEventListener('resize', updateSize);
      }, []);
      return size;
    }
  
    useWindowSize();
  
    const toggleNavbar = () => {
      setOpenLinks(!openLinks);
    }
  
    return (
      <div className='navbar'>
        <div className='leftside' id={openLinks ? "open" : "close"}>
          <Link to="/"> <img src={Logo} alt='logo' className='logoImg'/> </Link>
          <div className='hiddenLinks'>
            <Link to="/" onClick={toggleNavbar}> Home </Link>
            <Link to="/folders" onClick={toggleNavbar}> Folders & Docs </Link>
          </div>
        </div>
        <div className='rightside'>
          <Link to="/"> Home </Link>
          <Link to="/folders"> Folders & Docs </Link>
          <button onClick={toggleNavbar}>
              <ReorderIcon />
          </button>
        </div>
      </div>
    )
}

export default Navbar