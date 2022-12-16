import {combineReducers} from "redux";
import alert from './alert';
import auth from './auth';
import order from "./order";
import hotel from "./hotel";

export default combineReducers({
  alert,
  auth,
  hotel,
  order
});