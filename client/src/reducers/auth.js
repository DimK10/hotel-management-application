import {createSlice} from "@reduxjs/toolkit";

const registerOfLoginSuccess = (state, action) => {
  const {payload, user} = action;
  localStorage.setItem('jwt', payload.jwt);
  state.jwt = payload.jwt;
  state.isAuthenticated = true;
  state.loading = false;
  state.user = user;
}

const resetState = state => {
  localStorage.removeItem('jwt');
  state.jwt = null;
  state.isAuthenticated = false;
  state.loading = false;
  state.user = null;
}

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
      const {payload} = action;
      state.isAuthenticated = true;
      state.loading = false;
      state.user = payload;
    },

    registerSuccess: registerOfLoginSuccess,
    loginSuccess: registerOfLoginSuccess,
    /* error reducers or auth reset reducers */
    authError: resetState,
    logOut: resetState,
    loginFail: resetState,
    registerFail: resetState,
    accountDeleted: resetState,
    cleaProfile: resetState
  }
})

export default authSlice;
