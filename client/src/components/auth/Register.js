import React, { Fragment, useState } from 'react';
import { useDispatch } from 'react-redux';
import NavBar from './../layout/NavBar';
import CIcon from '@coreui/icons-react';
import {cilEnvelopeOpen, cilLockLocked, cilUser} from '@coreui/icons';
import Alert from "../layout/Alert";
import {Link, Navigate} from "react-router-dom";
import {useSelector} from "react-redux";
import { register } from '../../actions/auth';
import {v4 as uuidv4} from "uuid";
import {addUserToOrderAction} from "../../actions/order";

const Register = (props) => {

  const { hotel, inProcess } = useSelector(state => state.order.currentOrder);

  const isAuthenticated = useSelector((state) => state.auth.isAuthenticated);

  const role = useSelector((state) => state.auth.user?.role);
  const user = useSelector(state => state.auth.user);

  const { user: userInOrder } = useSelector(state => state.order.currentOrder);

  const dispatch = useDispatch();

  const [formData, setFromData] = useState({
    username: '',
    password: '',
    emailVerify: true,
    firstname: '',
    lastname: '',
    email: '',
    role:'',
  });

  const onChange = (e) =>
    setFromData({ ...formData, [e.target.name]: e.target.value });
  
   const handleSelectChange = (e) => {
     setFromData({...formData, role: e.target.value});
   };


  const onSubmit = async (e) => {
    e.preventDefault();
    await dispatch(register(formData));
  };

  const redirectBasedOnRole = () => {
    if (isAuthenticated && role !== 'undefined' && role === 'CLIENT') {
      if (inProcess === true && userInOrder === null)
        dispatch(addUserToOrderAction(user));
      return <Navigate to='/' />;
    }

    // Redirect if logged in
    if (isAuthenticated && role !== 'undefined' && role === 'ADMIN') {
      return <Navigate to='/dashboard' />;
    }
  };

  redirectBasedOnRole();

  return (
  <Fragment>
    {!isAuthenticated ? (
        <Fragment>
          <NavBar />
          <div className='bg-light min-vh-100 d-flex flex-row align-items-center'>
            <div className='container'>
              <Alert />
              <div className='row justify-content-center'>
                <div className='col-md-6'>
                  <div className='card mb-4 mx-4'>
                    <form onSubmit={(e) => onSubmit(e)}>
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
                            name='username'
                            onChange={(e) => onChange(e)}
                            required
                          />
                        </div>
                        <div className='input-group mb-3'>
                          <span className='input-group-text'>
                            <CIcon icon={cilUser} />
                          </span>
                          <input
                            className='form-control'
                            type='text'
                            placeholder='Firstname'
                            name='firstname'
                            onChange={(e) => onChange(e)}
                            required
                          />
                        </div>
                        <div className='input-group mb-3'>
                          <span className='input-group-text'>
                            <CIcon icon={cilUser} />
                          </span>
                          <input
                            className='form-control'
                            type='text'
                            placeholder='Lastname'
                            name='lastname'
                            onChange={(e) => onChange(e)}
                            required
                          />
                        </div>
                        <div className='input-group mb-3'>
                          <span className='input-group-text'>
                            <CIcon icon={cilEnvelopeOpen} />
                          </span>
                          <input
                            className='form-control'
                            type='email'
                            placeholder='Email'
                            name='email'
                            onChange={(e) => onChange(e)}
                            required
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
                            name='password'
                            onChange={(e) => onChange(e)}
                            required
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
                            className='form-select'
                            id='role-select'
                            onChange={(e) => handleSelectChange(e)}
                          >
                            <option value='' defaultValue={true}>
                              What do you want to do?
                            </option>

                            <option
                              value='CLIENT'
                              onChange={(e) => onchange(e)}
                            >
                              I am a customer and I want to order rooms
                            </option>
                            {
                                inProcess !== true
                                &&
                                <option key={uuidv4()} value='ADMIN'>
                                  I want to manage hotels and their rooms
                                </option>
                            }
                          </select>
                        </div>
                        <button className='btn btn-block btn-success' type='submit'>
                          <span className='text-white'>Create Account</span>
                        </button>
                      </div>
                    </form>
                    <Link className='btn btn-link px-0' to='/sign-in'>
                      Already a user? Log in
                    </Link>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </Fragment>
         ) : (
        redirectBasedOnRole()
      )}
      </Fragment>
  );
};

Register.propTypes = {};

export default Register;
