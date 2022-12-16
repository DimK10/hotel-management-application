import {GET_ALL_HOTELS} from '../actions/types';


const initialState = {
  loading: true,
  hotels: []
}

export default function (state = initialState, action) {
  const { type, payload } = action;

  switch (type) {
    case GET_ALL_HOTELS:
      return {
        ...state,
        hotels: payload
      }
    default:
      return state;
  }
}