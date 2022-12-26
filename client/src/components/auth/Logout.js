import React, {Fragment, useEffect} from 'react';
import {logoutAction} from "../../actions/auth";
import {useDispatch} from "react-redux";
import {Navigate} from "react-router-dom";


function Logout() {

  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(logoutAction());
  }, []);

  return (
    <Fragment>
      <Navigate to="/sign-in" />
    </Fragment>
  );
}

export default Logout;