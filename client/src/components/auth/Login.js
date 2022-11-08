import React, { Fragment } from 'react';
import PropTypes from 'prop-types';
import NavBar from '../layout/NavBar';
import CIcon from '@coreui/icons-react';
import { cilUser, cilLockLocked } from '@coreui/icons';

const Login = (props) => {
  return (
    <Fragment>
      <Fragment>
        <NavBar />
        <div className='bg-light min-vh-100 d-flex flex-row align-items-center'>
          <div className='container'>
            <div className='row justify-content-center'>
              <div className='card-group d-block d-md-flex row'>
                <div className='card col-md-7 p-4 mb-0'>
                  <div className='card-body'>
                    <h1>Login</h1>
                    <p className='text-medium-emphasis'>
                      Sign In to your account
                    </p>
                    <div className='input-group mb-3'>
                      <span className='input-group-text'>
                        <CIcon icon={cilUser} />
                      </span>
                      <input
                        className='form-control'
                        type='text'
                        placeholder='Username'
                      />
                    </div>
                    <div className='input-group mb-4'>
                      <span className='input-group-text'>
                        <CIcon icon={cilLockLocked} />
                      </span>
                      <input
                        className='form-control'
                        type='password'
                        placeholder='Password'
                      />
                    </div>
                    <div className='row'>
                      <div className='col-6'>
                        <button className='btn btn-primary px-4' type='button'>
                          Login
                        </button>
                      </div>
                      <div className='col-6 text-end'>
                        <button className='btn btn-link px-0' type='button'>
                          Forgot password?
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </Fragment>
    </Fragment>
  );
};

Login.propTypes = {};

export default Login;
