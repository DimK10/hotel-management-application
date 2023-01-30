import {GET_NEW_ORDER, NEW_ORDER_PRE_CHECKOUT, ORDER_ERROR} from "./types";
import orderSlice from "../reducers/order";
import moment from "moment";
import axios from 'axios';
import setAuthToken from '../utils/setAuthToken';
import alertSlice from '../reducers/alert';

const {
    newOrderPreCheckout,
    getAllOrdersForClient,
    getOrderById,
    addHotelToOrderPreCheckout,
    addToOrder,
    addUserToOrder,
    orderError
} = orderSlice.actions;

const {
    setAlert
} = alertSlice.actions;

// Get newly created order
export const getNewOrder = () => async (dispach) => {
    try {
        // dispach({
        //   type: GET_NEW_ORDER
        // });
    } catch (err) {

    }
}

export const getOrderByIdAction = (orderId) => async (dispatch) => {
     
    if (localStorage.jwt) {
       setAuthToken(localStorage.jwt);
    }

    try {
        
        const res = await axios.get(`/api/orderId/${orderId}`);
    
        dispatch(getOrderById(res.data));

    } catch (err) {

        const payload = {
          msg: err,
          status: null,
        };

        dispatch(orderError(payload));
    }
}

export const getAllOrdersForClientAction = () => async (dispatch) => {
    
    if (localStorage.jwt) {
      setAuthToken(localStorage.jwt);
    }

    try {
        
        const res = await axios.get('/api/orders/client');

        await dispatch(getAllOrdersForClient(res.data));

    } catch (err) {

         const payload = {
           msg: err,
           status: null,
         };
        
        dispatch(orderError(payload));
    }
}

// Create order pre checkout
export const createNewOrderPreCheckout = (checkInDate, checkOutDate) => async (dispatch) => {
    try {

        const payload = {
            checkInDate: moment(checkInDate).format('DD/MM/YYYY'),
            checkOutDate: moment(checkOutDate).format('DD/MM/YYYY'),
            inProcess: true
        };

        dispatch(newOrderPreCheckout(payload))
    } catch (err) {

        const payload = {
            msg: err,
            status: null
        }
        dispatch(orderError(payload))
    }
};
export const addHotelToOrderPreCheckoutAction = (hotel) => async (dispatch) => {
    try {

        const payload = {
            hotel
        };

        dispatch(addHotelToOrderPreCheckout(payload))
    } catch (err) {

        const payload = {
            msg: err,
            status: null
        }
        dispatch(orderError(payload))
    }
};

export const addToOrderAction = (room, price, user, hotelAmenities, roomAmenities) => (dispatch) => {
    const payload = {
        room,
        price,
        user,
        hotelAmenities,
        roomAmenities
    }
    dispatch(addToOrder(payload));
}

export const addUserToOrderAction = (user) => dispach => {
    dispach(addUserToOrder(user));
}