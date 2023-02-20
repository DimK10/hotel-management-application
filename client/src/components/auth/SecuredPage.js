import React, {Fragment, useEffect} from 'react';
import PropTypes from 'prop-types';
import {useDispatch, useSelector} from "react-redux";
import {loadUser} from "../../actions/auth";
import {Navigate} from "react-router-dom";
import {setAlertAction} from "../../actions/alert";
import {ALERT_ERROR, LOGGED_OUT_LOG_IN_AGAIN} from "../../actions/types";

const SecuredPage = ({ children }) => {

  const dispatch = useDispatch();

  const auth  = useSelector( state => state.auth);

  useEffect(() => {
    dispatch(loadUser());
  }, []);

  if (auth.jwt === null && auth.isAuthenticated === null) {
    return <Navigate to='/not-found' />
  }

  if (auth.error === 'Token expired') {
    dispatch(setAlertAction(LOGGED_OUT_LOG_IN_AGAIN, ALERT_ERROR));
    return <Navigate to='/sign-in' />
  }


  return (
    <Fragment>
      {children}
    </Fragment>
  );
};

SecuredPage.propTypes = {
  children: PropTypes.oneOfType([
    PropTypes.arrayOf(PropTypes.node),
    PropTypes.node
  ]).isRequired
};

export default SecuredPage;
