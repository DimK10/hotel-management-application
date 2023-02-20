// import {GET_ALL_HOTELS, GET_COUNT_OF_HOTELS, HOTEL_ERROR} from "./types";
import axios from "axios";
import setAuthToken from "../utils/setAuthToken";
import hotelSlice from "../reducers/hotel";
import alertSlice from "../reducers/alert";
import {setAlertAction} from "./alert";
import {
    ALERT_ERROR,
    ALERT_SUCCESS,
    HOTEL_CREATED_SUCCESS, HOTEL_ERROR,
    HOTEL_SUCCESS,
    HOTEL_UPDATED_SUCCESS, ROOM_ERROR,
    SOMETHING_WRONG_HAPPENED
} from "./types";


const {
    getAllHotels,
    getHotelById,
    getCountOfHotels,
    createNewHotel,
    updateHotel,
    hotelError,
} = hotelSlice.actions;

export const getAllHotelsAction = () => async dispatch => {
    if (localStorage.jwt) {
        setAuthToken(localStorage.jwt);
    }

    try {
        const res = await axios.get('/api/hotels');


        dispatch(getAllHotels(res.data))
    } catch (err) {
        dispatch(hotelError(err.response.data.errorMessage));
        dispatch(setAlertAction(HOTEL_ERROR, ALERT_ERROR));
    }
}

export const getAllHotelsByPage = (pageNo, pageSize, sortBy) => async (dispatch) => {

    if (localStorage.jwt) {
        setAuthToken(localStorage.jwt);
    }

    try {
        const res = await axios.get(`/api/hotels/${pageNo}/${pageSize}/${sortBy}`);


        dispatch(getAllHotels(res.data))
    } catch (err) {
        dispatch(hotelError(err.response.data.errorMessage));
        dispatch(setAlertAction(HOTEL_ERROR, ALERT_ERROR));
    }
}

export const getCountOfHotelsAction = (userId) => async (dispatch) => {
    if (localStorage.jwt) {
        setAuthToken(localStorage.jwt);
    }

    try {
        const res = await axios.get(`/api/hotels/quantity/${userId}`);

        dispatch(getCountOfHotels(res.data));
    } catch (err) {
        dispatch(hotelError(err.response.data.errorMessage));
    }
}

export const createNewHotelAction = (formData) => async (dispatch) => {

    if (localStorage.jwt) {
        setAuthToken(localStorage.jwt);
    }

    const config = {
        headers: {
            "Content-Type": "application/json",
        },
    };

    try {
        const res = await axios.post("/api/hotel/create", formData, config)

        dispatch(createNewHotel(res.data))
        dispatch(setAlertAction(HOTEL_CREATED_SUCCESS, ALERT_SUCCESS));
    } catch (err) {
        dispatch(hotelError(err.response.data.errorMessage));
        dispatch(setAlertAction(SOMETHING_WRONG_HAPPENED, ALERT_ERROR));
    }


}

export const updateExistingHotelAction = (formData) => async (dispatch) => {
  if (localStorage.jwt) {
    setAuthToken(localStorage.jwt);
  }

  const config = {
    headers: {
      "Content-Type": "application/json",
    },
  };

  const res = await axios.put("/api/hotel/update", formData, config);

  dispatch(updateHotel(res.data));
  dispatch(setAlertAction( HOTEL_UPDATED_SUCCESS, ALERT_SUCCESS ));
}

export const getHotelByIdAction = (hotelId) => async dispatch => {

    if (localStorage.jwt) {
        setAuthToken(localStorage.jwt);
    }

    try {

        const res = await axios.get(`/api/hotelId/${hotelId}`);

        dispatch(getHotelById(res.data));
    } catch (err) {
        dispatch(hotelError(err.response.data.errorMessage));
        dispatch(setAlertAction(HOTEL_ERROR, ALERT_ERROR));
    }

}