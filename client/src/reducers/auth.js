// import {
//   ACCOUNT_DELETED,
//   AUTH_ERROR,
//   CLEAR_PROFILE,
//   LOGIN_FAIL,
//   LOGIN_SUCCESS,
//   LOGOUT,
//   REGISTER_FAIL,
//   REGISTER_SUCCESS,
//   USER_LOADED,
// } from '../actions/types';
//
// const initialState = {
//   jwt: localStorage.getItem('jwt'),
//   isAuthenticated: null,
//   loading: true,
//   user: null,
// };
//
// export default function (state = initialState, action) {
//   const { type, payload } = action;
//
//   switch (type) {
//     case USER_LOADED:
//       return {
//         ...state,
//         isAuthenticated: true,
//         loading: false,
//         user: payload,
//       };
//     case REGISTER_SUCCESS:
//     case LOGIN_SUCCESS:
//       localStorage.setItem('jwt', payload.jwt);
//       return {
//         ...state,
//         ...payload,
//         isAuthenticated: true,
//         loading: false,
//       };
//     case REGISTER_FAIL:
//     case AUTH_ERROR:
//     case LOGIN_FAIL:
//     case LOGOUT:
//     case ACCOUNT_DELETED:
//       localStorage.removeItem('jwt');
//       return {
//         ...state,
//         jwt: null,
//         isAuthenticated: false,
//         loading: false,
//       };
//     case CLEAR_PROFILE:
//       return {
//         ...state,
//         user: null,
//         jwt: null,
//         isAuthenticated: false,
//         loading: false,
//       }
//     default:
//       return state;
//   }
// }

import {createSlice} from "@reduxjs/toolkit";

const authSlice = createSlice({
  name: 'auth',
  initialState: {
    jwt: localStorage.getItem('jwt'),
    isAuthenticated: null,
    loading: true,
    user: null,
  },
  reducers: {
    userLoaded: (state, action) => {
      const { payload } = action;
      state.isAuthenticated = true;
      state.loading = false;
      state.user = payload;
    },
    registerOrLoginSuccess: (state, action) => {
      const { payload, user } = action;
      localStorage.setItem('jwt', payload.jwt);
      state.jwt = payload.jwt;
      state.isAuthenticated = true;
      state.loading = false;
      state.user = user;
    },
    authErrorOrLogOut: state => {

      localStorage.removeItem('jwt');
      state.jwt = null;
      state.isAuthenticated = false;
      state.loading = false;
    },
    cleaProfile: state => {
      state.user = null;
      state.jwt = null;
      state.isAuthenticated = false;
      state.loading = false;
//         jwt: null,
//         isAuthenticated: false,
//         loading: false,
    }

  }
})

export default authSlice;
