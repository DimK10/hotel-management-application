import {GET_NEW_ORDER, NEW_ORDER_PRE_CHECKOUT, ORDER_ERROR} from "./types";
import orderSlice from "../reducers/order";
import moment from "moment";

const {
  newOrderPreCheckout,
  orderError
} = orderSlice.actions;

// Get newly created order
export const getNewOrder = () => async (dispach) => {
  try {
    // dispach({
    //   type: GET_NEW_ORDER
    // });
  } catch (err) {

  }
}

// Create order pre checkout
export const createNewOrderPreCheckout = (checkInDate, checkOutDate) => async (dispatch) => {
  try {

    const payload = {
      checkInDate: moment(checkInDate).format('DD/MM/YYYY'),
      checkOutDate: moment(checkOutDate).format('DD/MM/YYYY')
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