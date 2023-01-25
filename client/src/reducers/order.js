import {createSlice} from "@reduxjs/toolkit";
import moment from "moment/moment";

const orderSlice = createSlice({
  name: 'order',
  initialState: {
    currentOrder: {
      checkInDate: moment(new Date()).format('DD/MM/YYYY'),
      checkOutDate: moment(new Date()).format('DD/MM/YYYY'),
      loading: true,
      user: null,
      hotel: null,
      room: null,
      price: 0,
      hotelAmenities: [],
      roomAmenities: [],
      canceled: false,
    },
    orders: [],
    error: {}
  },
  reducers: {
    newOrderPreCheckout: (state, action) => {
      const { payload }  = action;
      
      state.currentOrder.checkInDate = payload.checkInDate;
      state.currentOrder.checkOutDate = payload.checkOutDate;

    },
    addHotelToOrderPreCheckout: (state, action) => {
      const { payload }  = action;

      state.currentOrder.hotel = payload.hotel;
    },
    addToOrder: (state, action) => {
      const {payload} = action;

      state.currentOrder.room = payload.room;
      state.currentOrder.price = payload.price;
      state.currentOrder.user = payload.user;
      state.currentOrder.hotelAmenities = payload.hotelAmenities;
      state.currentOrder.roomAmenities = payload.roomAmenities;
    },
    orderError: (state, action) => {
      const { payload } = action;
      state.currentOrder.error = payload;
      state.currentOrder.loading = false;
    }
  }
})

export default orderSlice;
