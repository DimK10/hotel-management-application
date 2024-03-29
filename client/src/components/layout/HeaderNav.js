import React, {Fragment} from 'react';
import CIcon from "@coreui/icons-react";
import {cilMenu} from "@coreui/icons";
import Breadcrumb from "./Breadcrumb";

import {Sidebar} from '@coreui/coreui/dist/js/coreui.js';
import {Link} from "react-router-dom";
import Alert from "./Alert";

const HeaderNav = props => {

  return (
    <Fragment>
      <div className="wrapper d-flex flex-column min-vh-100 bg-light">

        <header className="header header-sticky mb-4">
          <div className="container-fluid">
            <button className="header-toggler px-md-0 me-md-3" type="button"
                    onClick={() => {
                      Sidebar.getOrCreateInstance(document.querySelector('#sidebar')).toggle()
                    }}>
              <CIcon className='icon icon-lg' icon={cilMenu}/>
            </button>
            <a className="header-brand d-md-none" href="#">
              <h5>H</h5>

            </a>
            <ul className="header-nav d-none d-md-flex">
              <li className="nav-item">
                <Link className="nav-link" to="/logout">Logout</Link>
              </li>
            </ul>


          </div>
          <div className="header-divider"></div>
          <Breadcrumb/>
        </header>
        <div className={`${
          window.location.pathname !== '/calendar' ? 'container' : ''
        }`}>
          {props.children}
        </div>
      </div>


    </Fragment>
  );
};

HeaderNav.propTypes = {};

export default HeaderNav;
