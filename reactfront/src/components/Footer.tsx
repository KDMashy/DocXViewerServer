import React from "react";
import InstagramIcon from '@mui/icons-material/Instagram';
import TwitterIcon from '@mui/icons-material/Twitter';
import FacebookIcon from '@mui/icons-material/Facebook';
import LinkedInIcon from '@mui/icons-material/LinkedIn';
import "../styles/Footer.css";
import Fox from '../res/fox.png';

function Footer() {
  return (
    <div className="footer">
      <div className="socialMedia">
        <a href="https://www.instagram.com/"><InstagramIcon /> </a>
        <a href="https://twitter.com/"><TwitterIcon /> </a>
        <a href="https://www.facebook.com/"><FacebookIcon /> </a>
        <a href="https://www.linkedin.com/"><LinkedInIcon /> </a>
      </div>
      <img id="Foxy" src={Fox} alt='fox' />
      <p> &copy; 2021 kdmashyprojects.com</p>
    </div>
  );
}

export default Footer;