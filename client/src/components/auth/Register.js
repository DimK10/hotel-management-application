import React, {Fragment} from 'react';
import NavBar from './../layout/NavBar';
import CIcon from '@coreui/icons-react';
import {cilEnvelopeOpen, cilLockLocked, cilUser} from '@coreui/icons';
import Alert from "../layout/Alert";
import {Link} from "react-router-dom";
import {useSelector} from "react-redux";

const Register = (props) => {

  const{ hotel } = useSelector(state => state.order.currentOrder);

  return (
    <Fragment>
      <NavBar />
      <div className='bg-light min-vh-100 d-flex flex-row align-items-center'>
        <div className='container'>
          <Alert />
          <div className='row justify-content-center'>
            <div className='col-md-6'>
              <div className='card mb-4 mx-4'>
                <div className='card-body p-4'>
                  <h1>Register</h1>
                  <p className='text-medium-emphasis'>Create your account</p>
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
                  <div className='input-group mb-3'>
                    <span className='input-group-text'>
                      <CIcon icon={cilEnvelopeOpen} />
                    </span>
                    <input
                      className='form-control'
                      type='text'
                      placeholder='Email'
                    />
                  </div>
                  <div className='input-group mb-3'>
                    <span className='input-group-text'>
                      <CIcon icon={cilLockLocked} />
                    </span>
                    <input
                      className='form-control'
                      type='password'
                      placeholder='Password'
                    />
                  </div>
                  <div className='input-group mb-3'>
                    <span className='input-group-text'>
                      <CIcon icon={cilLockLocked} />
                    </span>
                    <input
                      className='form-control'
                      type='password'
                      placeholder='Repeat password'
                    />
                  </div>
                  <div className='input-group mb-4'>
                    <select
                      className="form-select" id="role-select">
                      <option value="" defaultValue={hotel === null}>What do you want to do?</option>
                      <option value="customer" defaultValue={hotel !== null}>I am a customer and I want to order rooms</option>
                      <option value="manager">I want to manage hotels and their rooms</option>
                    </select>
                  </div>
                  <button className='btn btn-block btn-success' type='button'>
                    <span className="text-white">Create Account</span>
                  </button>
                </div>
                <Link className="btn btn-link px-0" to="/sign-in">Already a user? Log in</Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </Fragment>
  );
};

Register.propTypes = {};

export default Register;
