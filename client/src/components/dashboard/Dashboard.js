import React, {Fragment} from 'react';
import PropTypes from 'prop-types';
import SidebarComp from "../layout/Sidebar";

import '../../css/wrapperFix.css'
import HeaderNav from "../layout/HeaderNav";
import MainChart from "./MainChart";
import {Link, useNavigate} from "react-router-dom";
import moment from "moment/moment";

const Dashboard = props => {

  let navigate = useNavigate();

  const onCardClick = (url) => {
    navigate(url);
  }

  return (
    <Fragment>

      <SidebarComp/>
      <HeaderNav>
        <div className="jumbotron">
          <h1 className="display-4">Welcome back username!</h1>
          <hr className="my-4"/>
          <div style={{margin: "5rem auto"}}>
            <div className="row">
              <p className="lead">Summary of Your Rooms:</p>
            </div>
            <div className="row mb-5 m-auto">
              <div className="col-6 d-flex align-content-center justify-content-center">
                <div className="c-chart-wrapper">
                  <MainChart/>
                </div>
              </div>
              <div
                className="col-6 text-center d-flex flex-column justify-content-center">

                <h5>Rooms: 20</h5>
                <h5>Available: 10</h5>
                <h5>Vacant: 10</h5>
                <Link to="/rooms">Jump to Rooms</Link>
              </div>
            </div>
          </div>
          <div className="row mt-5">
            <div className="col">
              {/*TODO make a separate component for the cards below */}
              <div className="card mb-3 btn btn-raised shadow text-start p-0"
                   style={{background: "rgba(54, 162, 235, 1)", color: "white"}}
                   onClick={() => onCardClick('/hotels')}
              >
                <div className="card-body">
                  <h5 className="card-title">Jump To Your Hotels</h5>
                  <p className="card-text">In this section, the administrator will have
                    the ability to add his/her owned hotels</p>
                </div>
              </div>
            </div>
            <div className="col">
              <div className="card mb-3 btn btn-raised shadow text-start p-0"
                   style={{background: "rgba(255, 206, 86, 1)"}}
                   onClick={() => onCardClick('/rooms')}
              >
                <div className="card-body">
                  <h5 className="card-title">Jump To Your Rooms</h5>
                  <p className="card-text">In this section, the administrator will have
                    the ability to add his/her owned rooms</p>
                </div>
              </div>

            </div>
            <div className="col">
              <div className="card mb-3 btn btn-raised shadow text-start p-0"
                   style={{background: "rgba(153, 102, 255, 1)", color: "white"}}
                   onClick={() => onCardClick('/orders')}
              >
                <div className="card-body">
                  <h5 className="card-title">Jump To Orders</h5>
                  <p className="card-text">In this section, the administrator will see
                    info regarding the orders from clients</p>
                </div>
              </div>

            </div>
          </div>
        </div>
      </HeaderNav>
    </Fragment>
  );
};

Dashboard.propTypes = {};

export default Dashboard;
