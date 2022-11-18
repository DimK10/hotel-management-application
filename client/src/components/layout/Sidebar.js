import React, {Fragment} from 'react';
import PropTypes from 'prop-types';
import '@coreui/icons-react';
import CIcon from "@coreui/icons-react";
import {
  cilAccountLogout,
  cilBell, cilBug, cilCalculator,
  cilChartPie,
  cilCursor, cilDescription,
  cilDrop, cilLayers, cilNotes,
  cilPencil,
  cilPuzzle,
  cilSpeedometer, cilStar,
  cilUser
} from "@coreui/icons";
import {cibCoreui, cibCoreuiC} from '@coreui/icons';


Sidebar.propTypes = {};

function Sidebar(props) {
  return (
    <Fragment>
      <div className="sidebar sidebar-dark sidebar-fixed" id="sidebar">
        <div className="sidebar-brand d-none d-md-flex">
          <CIcon className='sidebar-brand-full' icon={cibCoreuiC} />
          <CIcon className='sidebar-brand-narrow' icon={cibCoreuiC} />
        </div>
        <ul className="sidebar-nav" data-coreui="navigation" data-simplebar="">
          <li className="nav-item"><a className="nav-link" href="index.html">
            <CIcon className='nav-icon' icon={cilSpeedometer} />
            Dashboard<span className="badge badge-sm bg-info ms-auto">NEW</span></a></li>
          <li className="nav-title">Theme</li>
          <li className="nav-item"><a className="nav-link" href="colors.html">
            <CIcon className='nav-icon' icon={cilDrop} />
            Colors</a></li>
          <li className="nav-item"><a className="nav-link" href="typography.html">
            <CIcon className='nav-icon' icon={cilPencil} />
            Typography</a></li>
          <li className="nav-title">Components</li>
          <li className="nav-group"><a className="nav-link nav-group-toggle" href="#">
            <CIcon className='nav-icon' icon={cilPuzzle} />
            Base</a>
            <ul className="nav-group-items">
              <li className="nav-item"><a className="nav-link" href="base/accordion.html"><span
                className="nav-icon"></span> Accordion</a></li>
              <li className="nav-item"><a className="nav-link"
                                          href="base/breadcrumb.html"><span
                className="nav-icon"></span> Breadcrumb</a></li>
              <li className="nav-item"><a className="nav-link"
                                          href="base/cards.html"><span
                className="nav-icon"></span> Cards</a></li>
              <li className="nav-item"><a className="nav-link"
                                          href="base/carousel.html"><span
                className="nav-icon"></span> Carousel</a></li>
              <li className="nav-item"><a className="nav-link"
                                          href="base/collapse.html"><span
                className="nav-icon"></span> Collapse</a></li>
              <li className="nav-item"><a className="nav-link"
                                          href="base/list-group.html"><span
                className="nav-icon"></span> List group</a></li>
              <li className="nav-item"><a className="nav-link" href="base/navs-tabs.html"><span
                className="nav-icon"></span> Navs &amp; Tabs</a></li>
              <li className="nav-item"><a className="nav-link"
                                          href="base/pagination.html"><span
                className="nav-icon"></span> Pagination</a></li>
              <li className="nav-item"><a className="nav-link"
                                          href="base/placeholders.html"><span
                className="nav-icon"></span> Placeholders</a></li>
              <li className="nav-item"><a className="nav-link"
                                          href="base/popovers.html"><span
                className="nav-icon"></span> Popovers</a></li>
              <li className="nav-item"><a className="nav-link"
                                          href="base/progress.html"><span
                className="nav-icon"></span> Progress</a></li>
              <li className="nav-item"><a className="nav-link" href="base/scrollspy.html"><span
                className="nav-icon"></span> Scrollspy</a></li>
              <li className="nav-item"><a className="nav-link"
                                          href="base/spinners.html"><span
                className="nav-icon"></span> Spinners</a></li>
              <li className="nav-item"><a className="nav-link"
                                          href="base/tables.html"><span
                className="nav-icon"></span> Tables</a></li>
              <li className="nav-item"><a className="nav-link"
                                          href="base/tooltips.html"><span
                className="nav-icon"></span> Tooltips</a></li>
            </ul>
          </li>
          <li className="nav-group"><a className="nav-link nav-group-toggle" href="#">
            <CIcon className='nav-icon' icon={cilCursor} />
            Buttons</a>
            <ul className="nav-group-items">
              <li className="nav-item"><a className="nav-link"
                                          href="buttons/buttons.html"><span
                className="nav-icon"></span> Buttons</a></li>
              <li className="nav-item"><a className="nav-link"
                                          href="buttons/button-group.html"><span
                className="nav-icon"></span> Buttons Group</a></li>
              <li className="nav-item"><a className="nav-link"
                                          href="buttons/dropdowns.html"><span
                className="nav-icon"></span> Dropdowns</a></li>
            </ul>
          </li>
          <li className="nav-item"><a className="nav-link" href="charts.html">
            <CIcon className='nav-icon' icon={cilChartPie} />
            Charts</a></li>
          <li className="nav-group"><a className="nav-link nav-group-toggle" href="#">
            <CIcon className='nav-icon' icon={cilNotes} />
            Forms</a>
            <ul className="nav-group-items">
              <li className="nav-item"><a className="nav-link"
                                          href="forms/form-control.html"> Form Control</a>
              </li>
              <li className="nav-item"><a className="nav-link"
                                          href="forms/select.html"> Select</a></li>
              <li className="nav-item"><a className="nav-link"
                                          href="forms/checks-radios.html"> Checks and
                radios</a></li>
              <li className="nav-item"><a className="nav-link"
                                          href="forms/range.html"> Range</a></li>
              <li className="nav-item"><a className="nav-link"
                                          href="forms/input-group.html"> Input group</a>
              </li>
              <li className="nav-item"><a className="nav-link"
                                          href="forms/floating-labels.html"> Floating
                labels</a></li>
              <li className="nav-item"><a className="nav-link"
                                          href="forms/layout.html"> Layout</a></li>
              <li className="nav-item"><a className="nav-link"
                                          href="forms/validation.html"> Validation</a>
              </li>
            </ul>
          </li>
          <li className="nav-group"><a className="nav-link nav-group-toggle" href="#">
            <CIcon className='nav-icon' icon={cilStar} />
            Icons</a>
            <ul className="nav-group-items">
              <li className="nav-item"><a className="nav-link"
                                          href="icons/coreui-icons-free.html"> CoreUI
                Icons<span className="badge badge-sm bg-success ms-auto">Free</span></a>
              </li>
              <li className="nav-item"><a className="nav-link"
                                          href="icons/coreui-icons-brand.html"> CoreUI
                Icons - Brand</a></li>
              <li className="nav-item"><a className="nav-link"
                                          href="icons/coreui-icons-flag.html"> CoreUI
                Icons - Flag</a></li>
            </ul>
          </li>
          <li className="nav-group"><a className="nav-link nav-group-toggle" href="#">
            <CIcon className='nav-icon' icon={cilBell} />
            Notifications</a>
            <ul className="nav-group-items">
              <li className="nav-item"><a className="nav-link"
                                          href="notifications/alerts.html"><span
                className="nav-icon"></span> Alerts</a></li>
              <li className="nav-item"><a className="nav-link"
                                          href="notifications/badge.html"><span
                className="nav-icon"></span> Badge</a></li>
              <li className="nav-item"><a className="nav-link"
                                          href="notifications/modals.html"><span
                className="nav-icon"></span> Modals</a></li>
              <li className="nav-item"><a className="nav-link"
                                          href="notifications/toasts.html"><span
                className="nav-icon"></span> Toasts</a></li>
            </ul>
          </li>
          <li className="nav-item"><a className="nav-link" href="widgets.html">
            <CIcon className='nav-icon' icon={cilCalculator} />
            Widgets<span className="badge badge-sm bg-info ms-auto">NEW</span></a></li>
          <li className="nav-divider"></li>
          <li className="nav-title">Extras</li>
          <li className="nav-group"><a className="nav-link nav-group-toggle" href="#">
            <CIcon className='nav-icon' icon={cilStar} />
            Pages</a>
            <ul className="nav-group-items">
              <li className="nav-item"><a className="nav-link" href="login.html"
                                          target="_top">
                <CIcon className='nav-icon' icon={cilAccountLogout} />
                Login</a></li>
              <li className="nav-item"><a className="nav-link" href="register.html"
                                          target="_top">
                <CIcon className='nav-icon' icon={cilAccountLogout} />
                Register</a></li>
              <li className="nav-item"><a className="nav-link" href="404.html"
                                          target="_top">
                <CIcon className='nav-icon' icon={cilBug} />
                Error 404</a></li>
              <li className="nav-item"><a className="nav-link" href="500.html"
                                          target="_top">
                <CIcon className='nav-icon' icon={cilBug} />
                Error 500</a></li>
            </ul>
          </li>
          <li className="nav-item mt-auto"><a className="nav-link"
                                              href="https://coreui.io/docs/templates/installation/"
                                              target="_blank">
            <CIcon className='nav-icon' icon={cilDescription} />
            Docs</a></li>
          <li className="nav-item"><a className="nav-link nav-link-danger"
                                      href="https://coreui.io/pro/" target="_top">
            <CIcon className='nav-icon' icon={cilLayers} />
            Try CoreUI
            <div className="fw-semibold">PRO</div>
          </a></li>
          <li className="nav-item"><a className="nav-link nav-link-danger"
                                      href="https://coreui.io/pro/" target="_top">
            <CIcon className='nav-icon' icon={cilLayers} />
            Try CoreUI
            <div className="fw-semibold">PRO</div>
          </a></li>
          <li className="nav-item"><a className="nav-link nav-link-danger"
                                      href="https://coreui.io/pro/" target="_top">
            <CIcon className='nav-icon' icon={cilLayers} />
            Try CoreUI
            <div className="fw-semibold">PRO</div>
          </a></li>
          <li className="nav-item"><a className="nav-link nav-link-danger"
                                      href="https://coreui.io/pro/" target="_top">
            <CIcon className='nav-icon' icon={cilLayers} />
            Try CoreUI
            <div className="fw-semibold">PRO</div>
          </a></li>
          <li className="nav-item"><a className="nav-link nav-link-danger"
                                      href="https://coreui.io/pro/" target="_top">
            <CIcon className='nav-icon' icon={cilLayers} />
            Try CoreUI
            <div className="fw-semibold">PRO</div>
          </a></li>
          <li className="nav-item"><a className="nav-link nav-link-danger"
                                      href="https://coreui.io/pro/" target="_top">
            <CIcon className='nav-icon' icon={cilLayers} />
            Try CoreUI
            <div className="fw-semibold">PRO</div>
          </a></li>
          <li className="nav-item"><a className="nav-link nav-link-danger"
                                      href="https://coreui.io/pro/" target="_top">
            <CIcon className='nav-icon' icon={cilLayers} />
            Try CoreUI
            <div className="fw-semibold">PRO</div>
          </a></li>
        </ul>
        <button className="sidebar-toggler" type="button"
                data-coreui-toggle="unfoldable"></button>
      </div>
    </Fragment>
);
}

export default Sidebar;