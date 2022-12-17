import {GET_ALL_HOTELS, GET_COUNT_OF_HOTELS} from '../actions/types';


const initialState = {
  loading: true,
  count: 0,
  hotels: []
}

export default function (state = initialState, action) {
  const { type, payload } = action;

  switch (type) {
    case GET_ALL_HOTELS:
      return {
        ...state,
        loading: false,
        hotels: payload
      }
    case GET_COUNT_OF_HOTELS:
      return {
        ...state,
        count: payload,
      }
    default:
      return state;
  }
}