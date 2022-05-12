import React, { useState } from 'react';
import '../styles/Main.css';
import { Link } from 'react-router-dom';
import Cookies from 'universal-cookie';
import axios from 'axios';

function Login() {
  const [username, setUsername] = useState<string>('');
  const [email, setEmail] = useState<string>('');
  const [password, setPassword] = useState<string>('');
  const [message, setMessage] = useState<string>('');
  const [loginName, setLoginName] = useState<string>('');
  const [loginPassw, setLoginPassw] = useState<string>('');
  const [loginMessage, setLoginMessage] = useState<string>('');

  const cookies = new Cookies();

  var emailRegex =  /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
  var passwRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/;

  function checkIfReg() {
    // if(!passwRegex.test(password)){
    //   setMessage(`User password is not correct, should contain: uppercase letter,  lowercase letter, special case letter, digits, and minimum length of 8`);
    // }
    // if(emailRegex.test(email) && passwRegex.test(password)){
    //   setMessage('');
    // }
  }

  const register = async () => {
    setEmail(email.toLowerCase());
    const respReg = await axios.post('http://localhost:8080/register', {
      username: username,
      password: password
    });

    if(respReg){
      const resp = await axios.post('http://localhost:8080/login', {
        username: username,
        password: password
      })
      var token = resp.data;
      cookies.set('token', token, {path: '/'});
      cookies.set('loggedin', "true", {path: '/'});
    }
  }

  const login = async () => {
    const resp = await axios.post('http://localhost:8080/login', {
      username: loginName,
      password: loginPassw
    })
    var token = resp.data;
    cookies.set('token', token, {path: '/'});
    cookies.set('loggedin', "true", {path: '/'});
  }

  return (
    <div className='site'>
      <div className='maincontent'>
        <div className='defaultContainer'>
            <div className='register'>
              <h1>REGISTRATION</h1>
              <input 
                type='text'
                placeholder='Username...'
                onChange={(e) => {
                  setUsername(e.target.value);
                }}
              />
              <input 
                type='password'
                placeholder='Password...'
                onChange={(e) => {
                  setPassword(e.target.value);
                  checkIfReg();
                }}
              />
              <p style={{color: 'red', fontSize: '125%', fontWeight: 'bold'}}>{message}</p>
              <button className='thingCreate' onClick={async (e) => {
                e.preventDefault();
                await register();
                window.location.replace("http://localhost:3000/profile");
              }}>REGISTER</button>
            </div>
        </div>
        <div className='defaultContainer'>
            <div className='register'>
              <h1>LOGIN</h1>
              <input 
                type='text'
                placeholder='Username...'
                onChange={(e) => {
                  setLoginName(e.target.value);
                }}
              />
              <input 
                type='password'
                placeholder='Password...'
                onChange={(e) => {
                  setLoginPassw(e.target.value);
                }}
              />
              <button className='thingCreate' onClick={async (e) => {
                e.preventDefault();
                await login();
                window.location.replace("http://localhost:3000/profile");
              }}>LOGIN</button>
            </div>
        </div>
      </div>
    </div>
  )
}

export default Login