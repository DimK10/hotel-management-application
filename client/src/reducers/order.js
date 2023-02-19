import {createSlice} from "@reduxjs/toolkit";
import moment from "moment/moment";
import {revertAll} from "../actions/global";

const initialOrder = {
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
    inProcess: false,
};

const initialState = {
    currentOrder: initialOrder,
    orderToShow: null,
    orders: [],
    error: {},
};

const orderSlice = createSlice({
    name: 'order',
    initialState,
    extraReducers: (builder) => builder.addCase(revertAll, () => initialState),
    reducers: {
        newOrderPreCheckout: (state, action) => {
            const {payload} = action;

            state.currentOrder.checkInDate = payload.checkInDate;
            state.currentOrder.checkOutDate = payload.checkOutDate;
            state.currentOrder.inProcess = payload.inProcess;
        },
        getOrderById: (state, action) => {
            const {payload} = action;

            state.orderToShow = payload;
            state.error = '';
        },
        getAllOrdersForClient: (state, action) => {
            const {payload} = action;

            state.orders = [...payload];
            state.error = false;
        },
        addHotelToOrderPreCheckout: (state, action) => {
            const {payload} = action;

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
        addUserToOrder: (state, action) => {
            const {payload} = action;
            state.currentOrder.user = payload;
            state.currentOrder.loading = false;
            state.error = '';
        },
        resetOrderState: (state) => initialState,
        orderError: (state, action) => {
            const {payload} = action;
            state.currentOrder = initialOrder;
            state.error = payload;
            state.loading = false;
        },
    },
});

export default orderSlice;
