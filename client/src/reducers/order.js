import {NEW_ORDER_PRE_CHECKOUT, ORDER_ERROR} from "../actions/types";

const initialState = {
  checkInDate: new Date(),
  checkOutDate: new Date(),
  client: null,
  room: null,
  canceled: false,
  error: {}
}

export default function (state = initialState, action) {
  const { type, payload } = action;

  switch (type) {
    case NEW_ORDER_PRE_CHECKOUT:
      return {
        ...state,
        checkInDate: payload?.checkInDate,
        checkOutDate: payload?.checkOutDate,
        client: payload?.client,
        room: payload?.room,
        canceled: payload.canceled,

      }
    case ORDER_ERROR:
      return {
        ...state,
        error: payload,
      }
    default:
      return state;
  }
}

