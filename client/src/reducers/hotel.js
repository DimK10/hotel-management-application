import {createSlice} from "@reduxjs/toolkit";
import {revertAll} from "../actions/global";

const initialState = {
  loading: true,
  count: 0,
  statistics: {},
  hotels: [],
  hotel: {},
  error: ''
};

const hotelSlice = createSlice({
  name: 'hotel',
  initialState,
  extraReducers: (builder) => builder.addCase(revertAll, () => initialState),
  reducers: {
    prepareLoading: (state) => {
      state.loading = true;
    },
    getStatistics: (state, action) => {
      const {payload} = action;
      state.statistics = payload;
      state.loading = false;
      state.error = '';
    },
    getAllHotels: (state, action) => {
      const { payload } = action;
      state.loading = false;
      state.hotels = payload;
      state.error = '';
    },
    getCountOfHotels: (state, action) => {
      const { payload } = action;
      state.count = payload;
      state.error = '';
    },
    createNewHotel: (state, action) => {
      const { payload } = action;
      state.loading = false;
      state.count = state.count + 1;
      state.hotels = [...state.hotels, payload];
      state.error = '';
    },
    updateHotel: (state, action) => {
      const { payload } = action;
      state.loading = false;
      state.hotel = payload;
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