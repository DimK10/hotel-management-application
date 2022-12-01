import React, {Fragment} from 'react';
import PropTypes from 'prop-types';
import moment from "moment/moment";
import CIcon from "@coreui/icons-react";
import {cilDelete, cilPencil, cilTrash} from "@coreui/icons";

const HotelCard = props => {
  return (
    <Fragment>
      <div className="row">
        <div className="col">
          <div className="card mb-3 shadow text-start p-0"
               >
            <div className="row g-0">
              <div className="col-md-5 col-lg-3">
                <img
                  src="https://images.unsplash.com/photo-1606046604972-77cc76aee944?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=735&q=80"
                  className="img-fluid rounded-start h-100" alt="..."/>
              </div>
              <div className="col-md-7 col-lg-9">
                <div className="card-body">
                  <h5 className="card-title">Hotel #1</h5>
                  <h6 className="card-subtitle mb-2">Price set: 50€</h6>
                  <p className="card-text">This is a hotel description about the hotwel
                    Lorem ipsum dolor sit amet, consectetur adipisicing
                    elit. Aspernatur corporis eveniet facilis in laborum molestias
                    praesentium repellendus unde. Aspernatur at beatae deserunt ea,
                    facilis nulla reiciendis repellendus repudiandae soluta
                    voluptatibus.</p>

                  <p className="card-text">
                    <small className="text-muted">Facilities set:</small>
                  </p>
                  <div className="container-fluid">
                      <span
                        className="badge rounded-pill text-bg-primary m-1 btn">Gym</span>
                    <span
                      className="badge rounded-pill text-bg-primary m-1 btn">Spa</span>
                    <span
                      className="badge rounded-pill text-bg-primary m-1 btn">Free Wifi</span>
                    <span
                      className="badge rounded-pill text-bg-primary m-1 btn">Pool</span>
                    <span
                      className="badge rounded-pill text-bg-primary m-1 btn">Mini Bar</span>
                    <span
                      className="badge rounded-pill text-bg-primary m-1 btn">Refrigerator</span>
                    <span
                      className="badge rounded-pill text-bg-primary m-1 btn">TV</span>
                    <span
                      className="badge rounded-pill text-bg-primary m-1 btn">Restaurant</span>
                    <span
                      className="badge rounded-pill text-bg-primary m-1 btn">Parking</span>
                    <span
                      className="badge rounded-pill text-bg-primary m-1 btn">PetsAllowed</span>
                    <span className="badge rounded-pill text-bg-primary m-1 btn">Toilet With Grab Rails</span>
                    <span className="badge rounded-pill text-bg-primary m-1 btn">Bathtub With Grab Rails</span>

                  </div>
                  <div className="container mt-5">
                   <div className="row justify-content-center">
                     <div className="col-auto">
                       <button type="button" className="btn btn-success" style={{ color: '#fff' }}>
                         <CIcon className="btn-icon" icon={cilPencil} />
                         <span className="d-inline-block" style={{ marginLeft: '.3rem' }}>Edit</span>
                       </button>
                     </div>
                     <div className="col-auto">
                       <button type="button" className="btn btn-danger" style={{ color: '#fff' }}>
                         <CIcon className="btn-icon" icon={cilTrash} />
                         <span className="d-inline-block" style={{ marginLeft: '.3rem' }}>Delete</span>
                       </button>
                     </div>
                   </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </Fragment>
  );
};

HotelCard.propTypes = {

};

export default HotelCard;
