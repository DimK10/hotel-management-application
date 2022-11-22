import React, {Fragment, useEffect, useRef} from 'react';
import PropTypes from 'prop-types';
import CIcon from "@coreui/icons-react";
import {cilMenu} from "@coreui/icons";
import Breadcrumb from "./Breadcrumb";

import {Sidebar} from '@coreui/coreui/dist/js/coreui.js';

const HeaderNav = props => {

  return (
    <Fragment>
      <div className="wrapper d-flex flex-column min-vh-100 bg-light">

        <header className="header header-sticky mb-4">
          <div className="container-fluid">
            <button className="header-toggler px-md-0 me-md-3" type="button"
                    onClick={() => {Sidebar.getOrCreateInstance(document.querySelector('#sidebar')).toggle()}}>
              <CIcon className='icon icon-lg' icon={cilMenu}/>
            </button>
            <a className="header-brand d-md-none" href="#">
              <h5>H</h5>

            </a>
            <ul className="header-nav d-none d-md-flex">
              <li className="nav-item"><a className="nav-link" href="#">Dashboard</a></li>
              <li className="nav-item"><a className="nav-link" href="#">Users</a></li>
              <li className="nav-item"><a className="nav-link" href="#">Settings</a></li>
            </ul>


          </div>
          <div className="header-divider"></div>
          <Breadcrumb />
        </header>
      </div>

    </Fragment>
  );
};

HeaderNav.propTypes = {

};

export default HeaderNav;
