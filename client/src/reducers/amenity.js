import {createSlice} from "@reduxjs/toolkit";
import {revertAll} from "../actions/global";

const initialState = {
    hotelAmenitiesLoading: true,
    roomAmenitiesLoading: true,
    hotelAmenities: [],
    roomAmenities: [],
    error: ''
};

const amenitySlice = createSlice({
    name: 'amenity',
    initialState,
    extraReducers: (builder) => builder.addCase(revertAll, () => initialState),
    reducers: {
        getAllActiveHotelAmenities: (state, action) => {
            const {payload} = action;

            state.hotelAmenities = payload;
            state.hotelAmenitiesLoading = false;
            state.error = ''
        },
        getAllActiveRoomAmenities: (state, action) => {
            const {payload} = action;

            state.roomAmenities = payload;
            state.roomAmenitiesLoading = false;
            state.error = ''
        },
        amenityError: (state, action) => {
            const { payload } = action;
            state.error = payload;
            state.hotelAmenitiesLoading = false;
            state.roomAmenitiesLoading = false;
        }
    }
})

export default amenitySlice;
