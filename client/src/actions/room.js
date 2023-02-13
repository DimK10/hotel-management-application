import setAuthToken from "../utils/setAuthToken";
import axios from "axios";
import {setAlertAction} from "./alert";
import {ALERT_ERROR, HOTEL_ERROR, ROOM_ERROR} from "./types";
import roomSlice from "../reducers/room";


const {
    getAllRoomsByHotel,
    roomError,
} = roomSlice.actions;

export const fetchAllRoomsFromSelectedHotel = hotelId => async dispatch => {
    if (localStorage.jwt) {
        setAuthToken(localStorage.jwt);
    }

    try {
        const res = await axios.get(`/api/rooms/${hotelId}`);


        dispatch(getAllRoomsByHotel(res.data))
    } catch (err) {
        dispatch(roomError(err.response.data.errorMessage));
        dispatch(setAlertAction(ROOM_ERROR, ALERT_ERROR));
    }
}