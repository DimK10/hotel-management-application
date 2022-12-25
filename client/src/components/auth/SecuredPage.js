import React, {Fragment, useEffect} from 'react';
import PropTypes from 'prop-types';
import {useDispatch, useSelector} from "react-redux";
import {loadUser} from "../../actions/auth";
import {Navigate} from "react-router-dom";

const SecuredPage = ({ children }) => {

  const dispatch = useDispatch();

  const auth  = useSelector( state => state.auth);

  useEffect(() => {
    dispatch(loadUser());
  }, []);

  if (auth.jwt === null && auth.isAuthenticated === null) {
    return <Navigate to='/not-found' />
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
