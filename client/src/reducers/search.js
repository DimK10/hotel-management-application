import {createSlice} from "@reduxjs/toolkit";

const searchSlice = createSlice({
    name: 'search',
    initialState: {
        loading: true,
        checkInDate: new Date(),
        checkOutDate: new Date(new Date().setDate(new Date().getDate() + 7)),
        nameOrLocation: '',
        hotels: [],
        error: {}
    },
    reducers: {
        basicSearch: (state, action) => {
            const { payload } = action;

            state.loading = false;
            state.hotels = payload;
            state.error = '';
        }
    }
})

export default searchSlice;