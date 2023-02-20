import setAuthToken from "../utils/setAuthToken";
import axios from "axios";
import {setAlertAction} from "./alert";
import {
    ALERT_ERROR,
    ALERT_SUCCESS,
    HOTEL_ERROR,
    HOTEL_UPDATED_SUCCESS,
    ROOM_ERROR,
    ROOM_UPDATED_SUCCESS
} from "./types";
import roomSlice from "../reducers/room";


const {
    getAllRoomsByHotel,
    getCountOfRooms,
    getRoomById,
    updateRoom,
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

// export const fetchAllRoomsFromSelectedHotel = (pageNo, pageSize, sortBy, hotelId) => async dispatch => {
//     if (localStorage.jwt) {
//         setAuthToken(localStorage.jwt);
//     }
//
//     try {
//         const res = await axios.get(`/api/rooms/${hotelId}/${pageNo}/${pageSize}/${sortBy}`);
//
//
//         dispatch(getAllRoomsByHotel(res.data))
//     } catch (err) {
//         dispatch(roomError(err.response.data.errorMessage));
//         dispatch(setAlertAction(ROOM_ERROR, ALERT_ERROR));
//     }
// }

export const getAllRoomsByPage = (pageNo, pageSize, sortBy, hotelId) => async dispatch => {

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

export const updateExistingRoomAction = (formData) => async dispatch => {
    if (localStorage.jwt) {
        setAuthToken(localStorage.jwt);
    }

    const config = {
        headers: {
            "Content-Type": "application/json",
        },
    };

    const res = await axios.put("/api/room/update", formData, config);

    dispatch(updateRoom(res.data));
    dispatch(setAlertAction( ROOM_UPDATED_SUCCESS, ALERT_SUCCESS ));
}