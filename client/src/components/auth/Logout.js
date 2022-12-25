import React, {Fragment, useEffect} from 'react';
import {logout} from "../../actions/auth";
import {connect} from "react-redux";
import {Navigate} from "react-router-dom";


function Logout({ logout }) {

  useEffect(() => {
    logout();
  }, []);

  return (
    <Fragment>
      <Navigate to="/sign-in" />
    </Fragment>
  );
}

Logout.propTypes = {

};

export default connect(null, { logout })(Logout);