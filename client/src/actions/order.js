import { NEW_ORDER_PRE_CHECKOUT, ORDER_ERROR } from "./types";

// Get order pre checkout
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
        msg: 'An error occurred',
        status: null,
      },
    });
  }
};