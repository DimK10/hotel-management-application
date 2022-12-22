import {combineReducers} from "redux";
import alertSlice from './alert';
import authSlice from './auth';
// import order from "./order";
// import hotel from "./hotel";

const alert = alertSlice.reducer;
const auth = authSlice.reducer;

export default combineReducers({
  alert,
  auth,
  // hotel,
  // order
});