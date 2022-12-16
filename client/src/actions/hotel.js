import {AUTH_ERROR, GET_ALL_HOTELS, HOTEL_ERROR} from "./types";
import axios from "axios";
import setAuthToken from "../utils/setAuthToken";

export const getAllHotelsByPage = (pageNo, pageSize, sortBy, userId) => async (dispatch) => {
  if (localStorage.jwt) {
    setAuthToken(localStorage.jwt);
  }


  try {
    const res = await axios.get(`/api/hotels/${pageNo}/${pageSize}/${sortBy}/${userId}`);

    dispatch({
      type: GET_ALL_HOTELS,
      payload: res.data
    })
  } catch (err) {
    dispatch({
      type: HOTEL_ERROR,
    });
  }
}