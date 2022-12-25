import {v4 as uuidv4} from 'uuid';
// import {REMOVE_ALERT, SET_ALERT} from './types';
import alertSlice from '../reducers/alert'

const { setAlert, removeAlert } = alertSlice.actions;

// This is the return from mapDispatchToProps in the documentation
export const setAlertAction =
  (msg, alertType, timeout = 5000) =>
  (dispatch) => {
    const id = uuidv4();
    dispatch(setAlert({ msg, alertType, id }));

    setTimeout(() =>dispatch(removeAlert(id)), timeout);
  };
