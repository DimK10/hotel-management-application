import React from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';

const NavBar = (props) => {
  return (
    <nav class='navbar navbar-expand-lg navbar-dark bg-dark'>
      <div class='container-fluid'>
        <a class='navbar-brand' href='#'>
          Hotel Management App
        </a>
        <button
          class='navbar-toggler'
          type='button'
          data-coreui-toggle='collapse'
          data-coreui-target='#navbarNav'
          aria-controls='navbarNav'
          aria-expanded='false'
          aria-label='Toggle navigation'
        >
          <span class='navbar-toggler-icon'></span>
        </button>
        <div class='collapse navbar-collapse' id='navbarNav'>
          <ul class='navbar-nav' style={{ marginLeft: 'auto' }}>
            <li class='nav-item'>
              <a class='nav-link active' aria-current='page' href='#'>
                Home
              </a>
            </li>
            <li class='nav-item'>
              <Link className='nav-link' to='/sign-in'>
                Sign In
              </Link>
            </li>
            <li class='nav-item'>
             <Link className='nav-link' to='/sign-up'>
                Sign In
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
