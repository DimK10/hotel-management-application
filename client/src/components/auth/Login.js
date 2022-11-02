import React, { Fragment } from 'react';
import PropTypes from 'prop-types';
import NavBar from '../layout/NavBar';

const Login = (props) => {
  return (
    <Fragment>
      <Fragment>
        <NavBar />
        <div className='w-100 h-100'>
          <div className='row'>
            <div className='col-8'>
              <img
                src={process.env.PUBLIC_URL + '/images/log-in-logo-3.jpg'}
                alt='login-logo'
                className='img-fluid'
              />
            </div>

            <div className='col-5'></div>
          </div>
        </div>
      </Fragment>
    </Fragment>
  );
};

Login.propTypes = {};

export default Login;
