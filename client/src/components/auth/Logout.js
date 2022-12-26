import React, {Fragment, useEffect} from 'react';
import {logout} from "../../actions/auth";
import {useDispatch} from "react-redux";
import {Navigate} from "react-router-dom";


function Logout() {

  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(logout());
  }, []);

  return (
    <Fragment>
      <Navigate to="/sign-in" />
    </Fragment>
  );
}

export default Logout;