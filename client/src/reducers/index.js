import {combineReducers} from "redux";
import alert from './alert';
import auth from './auth';
import order from "./order";

export default combineReducers({
  alert,
  auth,
  order
});