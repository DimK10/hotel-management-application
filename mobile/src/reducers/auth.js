import {createSlice} from "@reduxjs/toolkit";
import {revertAll} from "../actions/global";

const registerSuccess = (state, action) => {
  const { payload } = action;
  state.isAuthenticated = false;
  state.loading = false;
  state.user = payload;
  state.error = '';
}

const registerOrLoginSuccess = (state, action) => {
  const {payload, user} = action;
  localStorage.setItem('jwt', payload.jwt);
  state.jwt = payload.jwt;
  state.isAuthenticated = true;
  state.loading = false;
  state.user = user;
  state.error = '';
}

const resetState = state => {
  localStorage.removeItem('jwt');
  state.jwt = null;
  state.isAuthenticated = false;
  state.loading = false;
  state.user = null;
  state.error = ""
}

const error = (state, action) => {
  const { payload } = action;
  resetState(state);
  state.loading = false;
  state.error = payload;
}

const initialState = {
  jwt: localStorage.getItem('jwt'),
  isAuthenticated: null,
  loading: true,
  user: null,
  error: '',
}

const authSlice = createSlice({
  name: 'auth',
  initialState,
  extraReducers: (builder) => builder.addCase(revertAll, (state) => resetState(state)),
  reducers: {
    userLoaded: (state, action) => {
      const { payload } = action;
      state.isAuthenticated = true;
      state.loading = false;
      state.user = payload;
    },

    registerSuccess: registerOrLoginSuccess,
    loginSuccess: registerOrLoginSuccess,
    /* error reducers or auth reset reducers */
    authError: error,
    logOut: resetState,
    loginFail: error,
    registerFail: error,
    accountDeleted: resetState,
    cleaProfile: resetState,
  },
});

export default authSlice;
