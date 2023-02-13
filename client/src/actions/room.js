import setAuthToken from "../utils/setAuthToken";
import axios from "axios";
import {setAlertAction} from "./alert";
import {ALERT_ERROR, HOTEL_ERROR, ROOM_ERROR} from "./types";
import roomSlice from "../reducers/room";


const {
    getAllRoomsByHotel,
    getCountOfRooms,
    getRoomById,
    roomError,
} = roomSlice.actions;

export const getCountOfRoomsAction = (hotelId, userId) => async dispatch => {

    if (localStorage.jwt)
        setAuthToken(localStorage.jwt);

    try {

        const res = await axios.get(`/api/rooms/quantity/${hotelId}/${userId}`);

        dispatch(getCountOfRooms(res.data));

    }catch (err){
        dispatch(roomError(err.response.data.errorMessage));
        dispatch(setAlertAction(ROOM_ERROR, ALERT_ERROR));
    }

}

export const fetchAllRoomsFromSelectedHotel = (pageNo, pageSize, sortBy, hotelId) => async dispatch => {
    if (localStorage.jwt) {
        setAuthToken(localStorage.jwt);
    }

    try {
        const res = await axios.get(`/api/rooms/${hotelId}/${pageNo}/${pageSize}/${sortBy}`);


        dispatch(getAllRoomsByHotel(res.data))
    } catch (err) {
        dispatch(roomError(err.response.data.errorMessage));
        dispatch(setAlertAction(ROOM_ERROR, ALERT_ERROR));
    }
}

export const getAllRoomsByPage = (pageNo, pageSize, sortBy) => async dispatch => {

    if (localStorage.jwt) {
        setAuthToken(localStorage.jwt);
    }

    try {
        const res = await axios.get(`/api/rooms/${pageNo}/${pageSize}/${sortBy}`);


        dispatch(getAllRoomsByHotel(res.data))
    } catch (err) {
        dispatch(roomError(err.response.data.errorMessage));
        dispatch(setAlertAction(ROOM_ERROR, ALERT_ERROR));
    }

}

export const getRoomByIdAction = (roomId) => async  dispatch => {
    if (localStorage.jwt) {
        setAuthToken(localStorage.jwt);
    }

    try {

        const res = await axios.get(`/api/roomId/${roomId}`);

        dispatch(getRoomById(res.data));
    } catch (err) {
        dispatch(roomError(err.response.data.errorMessage));
        dispatch(setAlertAction(ROOM_ERROR, ALERT_ERROR));
    }

}