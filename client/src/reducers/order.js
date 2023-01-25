import {createSlice} from "@reduxjs/toolkit";
import moment from "moment/moment";

const orderSlice = createSlice({
  name: 'order',
  initialState: {
    checkInDate: moment(new Date()).format('DD/MM/YYYY'),
    checkOutDate: moment(new Date()).format('DD/MM/YYYY'),
    loading: true,
    client: null,
    hotel: null,
    room: null,
    price: 0,
    hotelAmenities: [],
    roomAmenities: [],
    canceled: false,
    error: {}
  },
  reducers: {
    newOrderPreCheckout: (state, action) => {
      const { payload }  = action;
      state.checkInDate = payload?.checkInDate;
      state.checkOutDate = payload.checkOutDate;
      state.client = payload?.client;
      state.room = payload?.room;
      state.canceled = payload.canceled;
      state.hotel = payload.hotel;
    },
    addToOrder: (state, action) => {
      const {payload} = action;

      state.room = payload.room;
      state.price = payload.price;
    },
    orderError: (state, action) => {
      const { payload } = action;
      state.error = payload;
      state.loading = false;
    }
  }
})

export default orderSlice;
