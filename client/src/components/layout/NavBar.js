import React from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';

const NavBar = (props) => {
  return (
    <nav className='navbar navbar-expand-lg navbar-dark bg-dark sticky-top'>
      <div className='container-fluid'>
        <a className='navbar-brand' href='#'>
          Hotel Management App
        </a>
        <button
          className='navbar-toggler'
          type='button'
          data-coreui-toggle='collapse'
          data-coreui-target='#navbarNav'
          aria-controls='navbarNav'
          aria-expanded='false'
          aria-label='Toggle navigation'
        >
          <span className='navbar-toggler-icon'></span>
        </button>
        <div className='collapse navbar-collapse' id='navbarNav'>
          <ul className='navbar-nav' style={{ marginLeft: 'auto' }}>
            <li className='nav-item'>
              <a className='nav-link active' aria-current='page' href='#'>
                Home
              </a>
            </li>
            <li className='nav-item'>
              <Link className='nav-link' to='/sign-in'>
                Sign In
              </Link>
            </li>
            <li className='nav-item'>
              <Link className='nav-link' to='/sign-up'>
                Sign Up
              </Link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};

NavBar.propTypes = {};

export default NavBar;
