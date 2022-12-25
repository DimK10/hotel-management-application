import {createSlice} from "@reduxjs/toolkit";

const hotelSlice = createSlice({
  name: 'hotel',
  initialState: {
    loading: true,
    count: 0,
    hotels: [],
    hotel: {},
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
    createNewHotel: (state, action) => {
      const { payload } = action;
      state.loading = false;
      state.count = state.count + 1;
      state.hotels = [...state.hotels, payload];
    },
    getHotelById: (state, action) => {
      const { payload } = action;
      state.loading = false;
      state.hotel = payload;
    },
    hotelError: (state, action) => {
      const { payload } = action;
      state.error = payload;
      state.loading = false;
    }
  }
})

export default hotelSlice;