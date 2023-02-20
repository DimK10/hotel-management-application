import {v4 as uuidv4} from 'uuid';
// import {REMOVE_ALERT, SET_ALERT} from './types';
import alertSlice from '../reducers/alert'

const { setAlert, removeAlert } = alertSlice.actions;

export const setAlertAction =
  (msg, alertType, timeout = 7000) =>
  (dispatch) => {
    const id = uuidv4();

    const payload = {
        msg,
        alertType,
        id
    }

    dispatch(setAlert(payload));

    setTimeout(() =>dispatch(removeAlert(id)), timeout);
  };
