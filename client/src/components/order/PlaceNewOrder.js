import React, {Fragment} from 'react';
import PropTypes from 'prop-types';
import NavBar from "../layout/NavBar";
import moment from "moment/moment";
import {connect} from "react-redux";


const PlaceNewOrder = ({ checkInDate, checkOutDate }) => {

  // todo check if order details exist, else show nothing


  return (
    <Fragment>
      <NavBar/>
      <div className="container mt-5">
        <div className="card">
          <div className="card-body">
            <h3 className="card-title">Order Details</h3>
            <p className="card-text mb-3">Here are you order details based on your
              search:</p>
            <div className="mb-3 row">
              <div className="col-md-4">
                <img
                  src="https://images.unsplash.com/photo-1606046604972-77cc76aee944?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=735&q=80"
                  className="img-fluid rounded-start h-100" alt="..."/>
              </div>
              <div className="col-md-8 container">

                <div className="mb-3 row">
                  <label htmlFor="staticText1"
                         className="col-sm-2 col-form-label">Name:</label>
                  <div className="col-sm-10">
                    <p id='staticText1' className="form-control-plaintext fw-bold">Hotel
                      #1</p>
                  </div>
                </div>
                <div className="mb-3 row">
                  <label htmlFor="staticText1"
                         className="col-sm-2 col-form-label">Base Price:</label>
                  <div className="col-sm-10">
                    <p id='staticText1' className="form-control-plaintext">50€ per
                      night</p>
                    <h6
                      className="card-subtitle mb-2">From: {moment(checkInDate).format('DD/MM/YYYY')} To: {moment(checkOutDate).format('DD/MM/YYYY')}</h6>

                  </div>
                </div>
                <hr/>
                <h6>Facilities</h6>
                <div className="mb-3 row">
                  <label htmlFor="staticText1"
                         className="col-sm-2 col-form-label">Gym:</label>
                  <div className="col-sm-10">
                    <p id='staticText1' className="form-control-plaintext">30€ total<small className='text-danger'>*</small></p>
                  </div>
                </div>
                <div className="mb-3 row">
                  <label htmlFor="staticText1"
                         className="col-sm-2 col-form-label">Spa:</label>
                  <div className="col-sm-10">
                    <p id='staticText1' className="form-control-plaintext">100€
                      total<small className='text-danger'>*</small></p>
                  </div>
                </div>
                <hr/>
                <div className="row mb-md-4 mb-lg-5">
                  <h6
                    className="col-sm-2 col-form-label">Total:</h6>
                  <div className="col-sm-10">
                    <p id='staticText1'
                       className="form-control-plaintext fw-bold">180€</p>
                  </div>
                </div>
                <div className="row mb-3">
                  <div className="col">
                    <p>* This amount applies once at order checkout and not for each day</p>
                  </div>
                </div>
                <div className="row">
                  <div className="col-4 col-md-6 col-sm-6 align-self-end">
                    <button type="button" className="btn btn-danger"><span
                      className="cil-contrast btn-icon mr-2 text-light">Proceed to checkout</span>
                    </button>
                  </div>
                </div>

              </div>

            </div>

          </div>
        </div>
      </div>
    </Fragment>
  );
}

PlaceNewOrder.propTypes = {
  checkInDate: PropTypes.instanceOf(Date),
  checkOutDate: PropTypes.instanceOf(Date)
};

const mapStateToProps = ({ checkInDate, checkOutDate }) => ({
  checkInDate,
  checkOutDate,
});


export default connect(mapStateToProps)(PlaceNewOrder);