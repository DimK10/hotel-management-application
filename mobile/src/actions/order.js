import orderSlice from "../reducers/order";
import moment from "moment";
import axios from 'axios';
import setAuthToken from '../utils/setAuthToken';
import alertSlice from '../reducers/alert';
import {setAlertAction} from "./alert";
import {ALERT_ERROR, ALERT_SUCCESS, ORDER_ERROR, ORDER_SUCCESS} from "./types";

const {
    newOrderPreCheckout,
    getAllOrders,
    getOrderById,
    addHotelToOrderPreCheckout,
    addToOrder,
    addUserToOrder,
    findOrdersByFistnameLastname,
    startOrdersLoading,
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

        dispatch(setAlertAction(
            ORDER_ERROR,
            ALERT_ERROR
        ))
    }
}

export const getAllOrdersForClientAction = () => async (dispatch) => {

    if (localStorage.jwt) {
        setAuthToken(localStorage.jwt);
    }

    try {

        const res = await axios.get('/api/orders/client');

        await dispatch(getAllOrders(res.data));

    } catch (err) {

        const payload = {
            msg: err,
            status: null,
        };

        dispatch(orderError(payload));
    }
}

export const getAllOrdersForAdminAction = (pageNo, pageSize) => async (dispatch) => {

    if (localStorage.jwt) {
        setAuthToken(localStorage.jwt);
    }

    try {

        const res = await axios.get(`/api/orders/admin?${pageNo}&${pageSize}`);

        await dispatch(getAllOrders(res.data));

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

            dispatch(setAlertAction(
                ORDER_SUCCESS,
                ALERT_SUCCESS
            ));

            dispatch(resetOrderState());

        } catch (err) {
            console.error(err)
            const payload = {
                msg: err.message,
                status: err.response.status
            }
            dispatch(orderError(payload));
            dispatch(setAlertAction(
                ORDER_ERROR,
                ALERT_ERROR
            ))
        }
    }

export const resetOrderAction = () => dispatch => {
    dispatch(resetOrderState());
};

export const findOrdersByFirstnameLastNameAction = (formData, pageNo, pageSize) => async dispatch => {
    dispatch(startOrdersLoading);
    try {

        const {
            firstname,
            lastname
        } = formData;

        let res;

        if (firstname === 'undefined' & lastname !== 'undefined') {
            res = await axios.get(`/api/orders/admin?${lastname}&${pageNo}&${pageSize}`)
        }

        if (firstname !== 'undefined' & lastname === 'undefined') {
            res = await axios.get(`/api/orders/admin?${firstname}&${pageNo}&${pageSize}`)
        }

        if (firstname !== 'undefined' & lastname !== 'undefined') {
            res = await axios.get(`/api/orders/admin?${firstname}&${lastname}&${pageNo}&${pageSize}`)
        }



        dispatch(findOrdersByFistnameLastname(res.data))
    } catch (err) {

        const payload = {
            msg: err,
            status: null
        }
        dispatch(orderError(payload))
    }
}