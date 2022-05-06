import React from 'react';
import '../styles/Main.css';
import { Link } from 'react-router-dom';

function Home() {
  return (
    <div className='site'>
      <div className='maincontent'>
        <div className='defaultContainer'>
          <h1>Welcome to my folder and document viewer site</h1>
          <p>
            Its basically a beginner made file system
          </p>
          <Link to="/folders"> Folders & Docs </Link>
        </div>
      </div>
    </div>
  )
}

export default Home