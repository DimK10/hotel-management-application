import React, {Fragment} from 'react';
import PropTypes from 'prop-types';
import '@coreui/icons-react';
import CIcon from "@coreui/icons-react";
import {cilDrop, cilPencil, cilSpeedometer, cilUser} from "@coreui/icons";
import { cibCoreui, cibCoreuiC } from '@coreui/icons';

Sidebar.propTypes = {

};

function Sidebar(props) {
  return (
    <Fragment>
      <div className="sidebar sidebar-dark sidebar-fixed" id="sidebar">
        <div className="sidebar-brand d-none d-md-flex">

          <CIcon className='nav-icon' icon={cibCoreui} />

        </div>
        <ul className="sidebar-nav" data-coreui="navigation" data-simplebar="init">
          <div className="simplebar-wrapper" style={{ margin: '0px' }}>
            <div className="simplebar-height-auto-observer-wrapper">
              <div className="simplebar-height-auto-observer"></div>
            </div>
            <div className="simplebar-mask">
              <div className="simplebar-offset" style={{ right: '0px', bottom: '0px'}}>
                <div className="simplebar-content-wrapper" tabIndex="0" role="region"
                     aria-label="scrollable content"
                     style = {{ height: '100%', overflow: 'hidden scroll' }}
                >
                  <div className="simplebar-content" style={{ padding: '0px' }}>
                    <li className="nav-item"><a className="nav-link" href="index.html">
                      <CIcon className='nav-icon' icon={cilSpeedometer} />

                      Dashboard<span className="badge badge-sm bg-info ms-auto">NEW</span></a>
                    </li>
                    <li className="nav-title">Theme</li>
                    <li className="nav-item"><a className="nav-link" href="colors.html">
                      <CIcon className='nav-icon' icon={cilDrop} />
                      Colors</a></li>
                    <li className="nav-item"><a className="nav-link" href="typography.html">
                      <CIcon className='nav-icon' icon={cilPencil} />
                      Typography</a></li>

                  </div>
                </div>
              </div>
            </div>
            <div className="simplebar-placeholder" style={{ width: 'auto', height: '841px' }}></div>
          </div>
          <div className="simplebar-track simplebar-horizontal" style={{ visibility: 'hidden' }}>
            <div className="simplebar-scrollbar" style={{ width: '0px', display: 'none' }}></div>
          </div>
          <div className="simplebar-track simplebar-vertical"  style = {{ visibility: 'visible' }}>
            <div className="simplebar-scrollbar"
                 style={{ height: '809px', transform: 'translate3d(0px, 0px, 0px)', display: 'block' }}
            ></div>
          </div>
        </ul>
        <button className="sidebar-toggler" type="button"
                data-coreui-toggle="unfoldable"></button>
      </div>
    </Fragment>
  );
}

export default Sidebar;