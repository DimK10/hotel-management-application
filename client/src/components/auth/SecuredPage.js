import React, {Fragment, useEffect} from 'react';
import PropTypes from 'prop-types';
import { connect } from "react-redux";
import store from "../../store";
import {loadUser} from "../../actions/auth";
import {Navigate} from "react-router-dom";

const SecuredPage = ({ loadUser, auth, children }) => {

  useEffect(() => {
    loadUser();
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
  auth: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  auth: state.auth
});

export default connect(mapStateToProps, { loadUser })(SecuredPage);
