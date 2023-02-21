import React, {Fragment, useEffect} from 'react';
import { logoutAction } from "../../actions/auth";
import { resetOrderAction } from '../../actions/order'
import {useDispatch} from "react-redux";
import {Navigate} from "react-router-dom";
import {revertAll} from "../../actions/global";
import {setAlertAction} from "../../actions/alert";
import {ALERT_SUCCESS, LOGGED_OUT_SUCCESS} from "../../actions/types";


function Logout() {

  const dispatch = useDispatch();

  useEffect(() => {
    // dispatch(logoutAction());
    // dispatch(resetOrderAction());
    dispatch(revertAll());
    dispatch(setAlertAction(LOGGED_OUT_SUCCESS, 'success'))
  }, []);

  return (
    <Fragment>
      <Navigate to="/sign-in" />
    </Fragment>
  );
}

export default Logout;