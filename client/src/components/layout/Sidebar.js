import React, {Fragment} from 'react';
import '@coreui/icons-react';
import CIcon from "@coreui/icons-react";
import {
  cilCalendar,
  cilHome,
  cilMoney,
  cilPlus,
  cilRoom,
  cilSpeedometer
} from "@coreui/icons";
import 'simplebar/dist/simplebar.css';
import 'simplebar/dist/simplebar'
import {Link} from "react-router-dom";


Sidebar.propTypes = {};

function Sidebar(props) {
  return (
    <Fragment>
      <div className="sidebar sidebar-dark sidebar-fixed" id="sidebar">
        <div className="sidebar-brand d-none d-md-flex mb-5">
          <h1 className='sidebar-brand-full '>H</h1>
          <h5 className='sidebar-brand-narrow'>H</h5>
          {/*<CIcon className='sidebar-brand-full' icon={cibCoreuiC} />*/}
          {/*<CIcon className='sidebar-brand-narrow' icon={cibCoreuiC} />*/}
        </div>
        <ul className="sidebar-nav" data-coreui="navigation" data-simplebar="">
          <li className="nav-item">
            <Link className={`nav-link ${
              window.location.pathname === '/dashboard' ? 'active' : ''
            }`} to='/dashboard'>
              <CIcon className='nav-icon' icon={cilSpeedometer}/>
              Dashboard{/*<span className="badge badge-sm bg-info ms-auto">NEW</span>*/}
            </Link>
          </li>
          <li className="nav-title">Calendar</li>
          <li className="nav-item">
            <Link className={`nav-link ${
              window.location.pathname === '/calendar' ? 'active' : ''
            }`} to='/calendar'>
              <CIcon className='nav-icon' icon={cilCalendar}/>
              calendar page
            </Link>
          </li>
          <li className="nav-title">Hotels</li>
          <li className="nav-item">
            <Link className={`nav-link ${
              window.location.pathname === '/hotels' ? 'active' : ''
            }`} to='/hotels'>
                <CIcon className='nav-icon' icon={cilHome}/>
                List of Hotels
            </Link>
          </li>
          <li className="nav-item">
            <Link className={`nav-link ${
                window.location.pathname === '/hotels/new' ? 'active' : ''
            }`} to='/hotels/new'>
              <CIcon className='nav-icon' icon={cilPlus}/>
              Add New Hotel
            </Link>
          </li>
          <li className="nav-title">Rooms</li>
          <li className="nav-item">
            <Link className={`nav-link ${
              window.location.pathname === '/rooms' ? 'active' : ''
            }`} to='/rooms'>
              <CIcon className='nav-icon' icon={cilRoom}/>
              List of Rooms
            </Link>
          </li>
          <li className="nav-item">
            <Link className={`nav-link ${
                window.location.pathname === '/rooms/new' ? 'active' : ''
            }`} to='/rooms/new'>
              <CIcon className='nav-icon' icon={cilPlus}/>
              Add New Room
            </Link>
          </li>
          <li className="nav-title">Orders</li>
          <li className="nav-item">
            <Link className={`nav-link ${
              window.location.pathname === '/orders' ? 'active' : ''
            }`} to='/orders'>
              <CIcon className='nav-icon' icon={cilMoney}/>
              List of Orders
            </Link>
          </li>
          <li className="nav-divider"></li>


        </ul>
        <button className="sidebar-toggler" type="button"
                data-coreui-toggle="unfoldable"></button>
      </div>
    </Fragment>
  );
}

export default Sidebar;