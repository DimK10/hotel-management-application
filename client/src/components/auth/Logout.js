import React, {Fragment, useEffect} from 'react';
import { logoutAction } from "../../actions/auth";
import { resetOrderAction } from '../../actions/order'
import {useDispatch} from "react-redux";
import {Navigate} from "react-router-dom";
import {revertAll} from "../../actions/global";


function Logout() {

  const dispatch = useDispatch();

  useEffect(() => {
    // dispatch(logoutAction());
    // dispatch(resetOrderAction());
    dispatch(revertAll());
  }, []);

  return (
    <Fragment>
      <Navigate to="/sign-in" />
    </Fragment>
  );
}

export default Logout;