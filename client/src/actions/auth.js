import axios from 'axios';
import jwt from 'jwt-decode'
import alertSlice, {setAlertAction} from './alert';

import setAuthToken from '../utils/setAuthToken';
import authSlice from "../reducers/auth";

const {
  userLoaded,
  registerSuccess,
  loginSuccess,
  authError,
  logOut,
  loginFail,
  registerFail,
  accountDeleted,
  cleaProfile
} = authSlice.actions;

// Load User
export const loadUser = () => async (dispatch) => {
  if (localStorage.jwt) {
    setAuthToken(localStorage.jwt);
  }

  try {
    const res = await axios.get('/api/auth');

    dispatch(userLoaded(res.data));
  } catch (err) {
    dispatch(authError());
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

        dispatch(registerSuccess(res.data));

        dispatch(loadUser());
      } catch (err) {
        const errors = err.response.data.errors;

        if (errors) {
          errors.forEach((error) => dispatch(setAlertAction(error.msg, 'danger')));
        }

        dispatch(registerFail())
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

    dispatch(loginSuccess({jwt: token, user: username}));

    dispatch(loadUser());
  } catch (err) {
    const errors = err.response.data.errors;

    if (errors) {
      errors.forEach((error) => dispatch(setAlertAction(error.msg, 'danger')));
    }

    dispatch(loginFail())
  }
};

// Logout / Clear Profile
export const logout = () => (dispatch) => {
  dispatch(cleaProfile());
};
