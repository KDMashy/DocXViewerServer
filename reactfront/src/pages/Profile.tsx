import { useEffect, useState, useCallback } from 'react'
import axios from 'axios'

import Cookies from 'universal-cookie';
import '../styles/Main.css';

export interface IUser {
    id?: number,
    username: string,
    password?: string
}

function Profile() {
  const [user, setUser] = useState<string>();

  const cookies = new Cookies();

  const getProfile = async () => {
    var token = cookies.get('token');
    const resp = await axios.get(
      'http://localhost:8080/profile', {
        headers: {"Authorization": `Bearer ${token}`}
      }
    )
    setUser(resp.data);
  }

  const logout = async () => {
    cookies.set('token', '');
    cookies.set('loggedin', 'false');

    setUser('');

    window.location.replace("http://localhost:3000/");
  }

  useEffect(() => {
    getProfile();
  }, [])

  return (
    <div className='site'>
      <div className='maincontent'>
        <div className='defaultContainer'>
          <h1>{user}</h1>
          <button  className='thingCreate' onClick={logout}>LOGOUT</button>
        </div>
      </div>
    </div>
  )
}

export default Profile