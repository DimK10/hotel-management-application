// import {GET_ALL_HOTELS, GET_COUNT_OF_HOTELS, HOTEL_ERROR} from "./types";
import axios from "axios";
import setAuthToken from "../utils/setAuthToken";
import hotelSlice from "../reducers/hotel";
import alertSlice from "../reducers/alert";


const {
    getAllHotels,
    getCountOfHotels,
    createNewHotel,
    hotelError,
} = hotelSlice.actions;

const {
    setAlert
} = alertSlice.actions;

export const getAllHotelsByPage = (pageNo, pageSize, sortBy, userId) => async (dispatch) => {

    if (localStorage.jwt) {
        setAuthToken(localStorage.jwt);
    }

    try {
        const res = await axios.get(`/api/hotels/${pageNo}/${pageSize}/${sortBy}/${userId}`);

        // dispatch({
        //   type: GET_ALL_HOTELS,
        //   payload: res.data
        // })

        dispatch(getAllHotels(res.data))
    } catch (err) {
        // dispatch({
        //   type: HOTEL_ERROR,
        // });
        dispatch(hotelError(err))
    }
}

export const getCountOfHotelsAction = (userId) => async (dispatch) => {
    if (localStorage.jwt) {
        setAuthToken(localStorage.jwt);
    }

    try {
        const res = await axios.get(`/api/hotels/quantity/${userId}`);

        // dispatch({
        //   type: GET_COUNT_OF_HOTELS,
        //   payload: res.data
        // })
        dispatch(getCountOfHotels(res.data));
    } catch (err) {
        // dispatch({
        //   type: HOTEL_ERROR,
        // });
        dispatch(hotelError());
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

    const res = await axios.post("/api/hotel/create", formData, config)

    dispatch(createNewHotel(res.data))
    dispatch(setAlert("Hotel Created", "success"));
}