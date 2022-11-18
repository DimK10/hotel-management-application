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

const Dashboard = props => {
  return (
    <Fragment>

      <SidebarComp/>
      <div className="wrapper d-flex flex-column min-vh-100 bg-light">

        <header className="header header-sticky mb-4">
          <div className="container-fluid">
            <button className="header-toggler px-md-0 me-md-3" type="button"
                    onClick={() => {Sidebar.getInstance(document.querySelector('#sidebar')).toggle()}}>
              <CIcon className='icon icon-lg' icon={cilMenu}/>
            </button>
            <a className="header-brand d-md-none" href="#">
              <CIcon  icon={cibCoreui}/>


            </a>
            <ul className="header-nav d-none d-md-flex">
              <li className="nav-item"><a className="nav-link" href="#">Dashboard</a></li>
              <li className="nav-item"><a className="nav-link" href="#">Users</a></li>
              <li className="nav-item"><a className="nav-link" href="#">Settings</a></li>
            </ul>


          </div>
          <div className="header-divider"></div>
          <div className="container-fluid">
            <nav aria-label="breadcrumb">
              <ol className="breadcrumb my-0 ms-2">
                <li className="breadcrumb-item">
                  <span>Home</span>
                </li>
                <li className="breadcrumb-item active"><span>Dashboard</span></li>
              </ol>
            </nav>
          </div>
        </header>
      </div>

    </Fragment>
  );
};

Dashboard.propTypes = {};

export default Dashboard;
