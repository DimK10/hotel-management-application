import React, {Fragment} from 'react';
import PropTypes from 'prop-types';
import NavBar from "../layout/NavBar";
import SidebarComp from "../layout/Sidebar";
import CIcon from "@coreui/icons-react";
import {
  cibCoreui,
  cilBell,
  cilCommentSquare, cilCreditCard,
  cilEnvelopeOpen, cilMenu,
  cilTask,
  cilUser
} from "@coreui/icons";
import '../../css/wrapperFix.css'

import {Sidebar} from '@coreui/coreui/dist/js/coreui.js';
import Breadcrumb from "../layout/Breadcrumb";
import HeaderNav from "../layout/HeaderNav";

const Dashboard = props => {
  return (
    <Fragment>

      <SidebarComp/>
      <HeaderNav />
    </Fragment>
  );
};

Dashboard.propTypes = {};

export default Dashboard;
