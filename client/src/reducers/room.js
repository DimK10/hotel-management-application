import {createSlice} from "@reduxjs/toolkit";
import {revertAll} from "../actions/global";

const initialState = {
    loading: true,
    rooms: [],
    room: null,
    count: 0,
    error: ''
};

const roomSlice = createSlice({
    name: 'room',
    initialState,
    extraReducers: (builder) => builder.addCase(revertAll, () => initialState),
    reducers: {
        getCountOfRooms: (state, action) => {
            const { payload } = action;
            state.count = payload;
            state.error = '';
        },
        getAllRoomsByHotel: (state, action) => {
            const { payload } = action;
            state.loading = false;
            state.rooms = payload;
            state.error = '';
        },
        roomError: (state, action) => {
            const { payload } = action;
            state.error = payload;
            state.loading = false;
        }
    }
});

export default roomSlice;