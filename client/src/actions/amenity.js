import setAuthToken from "../utils/setAuthToken";
import amenitySlice from "../reducers/amenity";
import axios from "axios";


const {
    getAllActiveHotelAmenities,
    getAllActiveRoomAmenities,
    amenityError
} = amenitySlice.actions;

export const fetchAllHotelAmenitiesAction = () => async dispatch => {

    if (localStorage.jwt) {
        setAuthToken(localStorage.jwt);
    }

    try {

        const res = await axios.get('/api/hotel/amenities');

        dispatch(getAllActiveHotelAmenities(res.data));

    }catch (err) {
        const payload = {
            msg: err.response.data.errorMessage,
            status: null,
        };

        dispatch(amenityError(err.response.data.errorMessage));
    }
}

export const fetchAllRoomAmenitiesAction = () => async dispatch => {

    if (localStorage.jwt) {
        setAuthToken(localStorage.jwt);
    }

    try {

        const res = await axios.get('/api/room/amenities');

        dispatch(getAllActiveRoomAmenities(res.data));

    }catch (err) {
        const payload = {
            msg: err.response.data.errorMessage,
            status: null,
        };

        dispatch(amenityError(err.response.data.errorMessage));
    }
}