import {GET_NEW_ORDER, NEW_ORDER_PRE_CHECKOUT, ORDER_ERROR} from "./types";

// Get newly created order
export const getNewOrder = () => async (dispach) => {
  try {
    dispach({
      type: GET_NEW_ORDER
    });
  } catch (err) {

  }
}

// Create order pre checkout
export const createNewOrderPreCheckout = (checkInDate, checkOutDate) => async (dispatch) => {
  try {
    dispatch({
      type: NEW_ORDER_PRE_CHECKOUT,
      payload: { checkInDate, checkOutDate }
    });
  } catch (err) {
    dispatch({
      type: ORDER_ERROR,
      payload: {
        msg: err,
        status: null,
      },
    });
  }
};