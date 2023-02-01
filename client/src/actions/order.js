import orderSlice from "../reducers/order";
import moment from "moment";
import axios from 'axios';
import setAuthToken from '../utils/setAuthToken';
import alertSlice from '../reducers/alert';
import {setAlertAction} from "./alert";

const {
    newOrderPreCheckout,
    getAllOrdersForClient,
    getOrderById,
    addHotelToOrderPreCheckout,
    addToOrder,
    addUserToOrder,
    orderError,
    resetOrderState
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

        setAlertAction(
            'There was a problem with your order. please try again',
            'danger'
        )
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

export const addUserToOrderAction = (user) => dispatch => {
    dispatch(addUserToOrder(user));
}

export const finalizeOrder = (currentOrder) =>
    async dispatch => {

        if (localStorage.jwt) {
            setAuthToken(localStorage.jwt);
        }

        try {

            const config = {
                headers: {
                    "Content-Type": "application/json",
                },
            };

            const body = JSON.stringify({
                checkInDate: moment(moment(currentOrder.checkInDate, 'DD/MM/YYYY').toDate()).format('YYYY-MM-DD'),
                checkOutDate: moment(moment(currentOrder.checkOutDate, 'DD/MM/YYYY').toDate()).format('YYYY-MM-DD'),
                client: currentOrder.user.id,
                room: currentOrder.room.id,
                price: currentOrder.price,
                roomName: currentOrder.room.name,
                hotelName: currentOrder.hotel.name,
                hotelAmenities: currentOrder.hotelAmenities,
                roomAmenities: currentOrder.roomAmenities
            });

            const res = await axios.post("/api/order/create", body, config);

            // do nothing - the order object is not needed

            setAlertAction(
                'You order has been placed Successfully!!!',
                'success'
            )

            dispatch(resetOrderState());

        } catch (err) {
            console.log(err)
            const payload = {
                msg: err.message,
                status: err.response.status
            }
            dispatch(orderError(payload));
            setAlertAction(
                'There was a problem with your order. please try again',
                'danger'
            )
        }
    }

export const resetOrderAction = () => dispatch => {
    dispatch(resetOrderState());
};