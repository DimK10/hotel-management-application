import axios from 'axios';
import jwt from 'jwt-decode'
import alertSlice, {setAlertAction} from './alert';
// import {
//   AUTH_ERROR,
//   CLEAR_PROFILE,
//   LOGIN_FAIL,
//   LOGIN_SUCCESS,
//   LOGOUT,
//   REGISTER_FAIL,
//   REGISTER_SUCCESS,
//   USER_LOADED,
// } from './types';
import setAuthToken from '../utils/setAuthToken';
import authSlice from "../reducers/auth";

const {
  userLoaded,
  authErrorOrLogOut,
  registerOrLoginSuccess,
  cleaProfile
} = authSlice.actions;

// Load User
export const loadUser = () => async (dispatch) => {
  if (localStorage.jwt) {
    setAuthToken(localStorage.jwt);
  }

  try {
    const res = await axios.get('/api/auth');

    // dispatch({
    //   type: USER_LOADED,
    //   payload: res.data,
    // });
    dispatch(userLoaded(res.data));
  } catch (err) {
    // dispatch({
    //   type: AUTH_ERROR,
    // });
    dispatch(authErrorOrLogOut());
  }
};

// Register user
export const register =
  ({name, email, password}) =>
    async (dispatch) => {
      const config = {
        headers: {
          'Content-Type': 'application/json',
        },
      };

      const body = JSON.stringify({name, email, password});

      try {
        const res = await axios.post('/api/users', body, config);

        // dispatch({
        //   type: REGISTER_SUCCESS,
        //   payload: res.data,
        // });

        dispatch(registerOrLoginSuccess(res.data));

        dispatch(loadUser());
      } catch (err) {
        const errors = err.response.data.errors;

        if (errors) {
          errors.forEach((error) => dispatch(setAlertAction(error.msg, 'danger')));
        }

        // dispatch({
        //   type: REGISTER_FAIL,
        // });
        dispatch(authErrorOrLogOut())
      }
    };

// Login user
export const login = (username, password) => async (dispatch) => {
  const config = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  const body = JSON.stringify({username, password});

  try {
    const res = await axios.post('api/login', body, config);

    const token = res.data.jwt;
    const username = jwt(token).sub;

    // dispatch({
    //   type: LOGIN_SUCCESS,
    //   payload: {...res.data, user: username},
    // });

    dispatch(registerOrLoginSuccess({jwt: token, user: username}));

    dispatch(loadUser());
  } catch (err) {
    const errors = err.response.data.errors;

    if (errors) {
      errors.forEach((error) => dispatch(setAlertAction(error.msg, 'danger')));
    }

    // dispatch({
    //   type: LOGIN_FAIL,
    // });
    dispatch(authErrorOrLogOut())
  }
};

// Logout / Clear Profile
export const logout = () => (dispatch) => {
  dispatch(cleaProfile());
  dispatch(authErrorOrLogOut());
};
