import {createSlice} from "@reduxjs/toolkit";
import moment from "moment/moment";

const searchSlice = createSlice({
    name: 'search',
    initialState: {
        loading: true,
        checkInDate: moment(new Date()).format('DD/MM/YYYY'),
        checkOutDate: moment(new Date(new Date().setDate(new Date().getDate() + 7))).format('DD/MM/YYYY'),
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
        },
        searchError: (state, action) => {
            const { payload } = action;
            state.error = payload;
            state.loading = false;
        }
    }
})

export default searchSlice;