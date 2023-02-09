import {combineReducers} from "redux";
import alertSlice from './alert';
import authSlice from './auth';
import hotelSlice from "./hotel";
import orderSlice from "./order";
import searchSlice from "./search";
import amenitySlice from './amenity';
import roomSlice from "./room";


const alert = alertSlice.reducer;
const auth = authSlice.reducer;
const hotel = hotelSlice.reducer;
const order = orderSlice.reducer;
const search = searchSlice.reducer;
const amenity = amenitySlice.reducer;
const room = roomSlice.reducer;

export default combineReducers({
  alert,
  auth,
  hotel,
  order,
  search,
  amenity,
  room
});