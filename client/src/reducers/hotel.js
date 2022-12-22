import {createSlice} from "@reduxjs/toolkit";

const hotelSlice = createSlice({
  name: 'hotel',
  initialState: {
    loading: true,
    count: 0,
    hotels: [],
    error: ''
  },
  reducers: {
    getAllHotels: (state, action) => {
      const { payload } = action;
      state.loading = false;
      state.hotels = payload
    },
    getCountOfHotels: (state, action) => {
      const { payload } = action;
      state.count = payload;
    },
    hotelError: (state, action) => {
      const { payload } = action;
      state.error = payload;
      state.loading = false;
    }
  }
})

export default hotelSlice;