import React, {Fragment} from 'react';
import PropTypes from 'prop-types';
import NavBar from "../layout/NavBar";
import Sidebar from "../layout/Sidebar";

const Dashboard = props => {
  return (
    <Fragment>
      <NavBar />
      <Sidebar />
      Dashboard
    </Fragment>
  );
};

Dashboard.propTypes = {

};

export default Dashboard;
