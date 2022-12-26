import React, {Fragment} from 'react';

import '../../css/btnRaised.css'
import {useNavigate} from "react-router-dom";
import moment from "moment";
import {createNewOrderPreCheckout} from "../../actions/order";
import {useDispatch} from "react-redux";
import PropTypes from "prop-types";

const SearchItem = ({checkInDate, checkOutDate}) => {

  const dispatch = useDispatch();

  let navigate = useNavigate();

  const onHotelCardClick = (e) => {


    let formData = new FormData();
    formData.append('checkInDate', checkInDate);
    formData.append('checkOutDate', checkOutDate);

    dispatch(createNewOrderPreCheckout(checkInDate, checkOutDate));
    //todo move hotel info to stat
    navigate('/order')
  }

  return (
    <Fragment>
      <div className="row">
        <div className="col">
          <div className="card mb-3 btn btn-raised shadow text-start p-0"
               onClick={() => onHotelCardClick()}>
            <div className="row g-0">
              <div className="col-md-3">
                <img
                  src="https://images.unsplash.com/photo-1606046604972-77cc76aee944?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=735&q=80"
                  className="img-fluid rounded-start h-100" alt="..."/>
              </div>
              <div className="col-md-9">
                <div className="card-body">
                  <h5 className="card-title">Hotel #1</h5>
                  <h6 className="card-subtitle mb-2">Price: 50€</h6>
                  <h6
                    className="card-subtitle mb-2">From: {moment(checkInDate).format('DD/MM/YYYY')} To: {moment(checkOutDate).format('DD/MM/YYYY')}</h6>
                  <p className="card-text">This is a hotel description about the hotwel
                    Lorem ipsum dolor sit amet, consectetur adipisicing
                    elit. Aspernatur corporis eveniet facilis in laborum molestias
                    praesentium repellendus unde. Aspernatur at beatae deserunt ea,
                    facilis nulla reiciendis repellendus repudiandae soluta
                    voluptatibus.</p>

                  <p className="card-text">
                    <small className="text-muted">Facilities</small>
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
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </Fragment>
  );
};

SearchItem.propTypes = {
  checkInDate: PropTypes.instanceOf(Date),
  checkOutDate: PropTypes.instanceOf(Date),
};

export default SearchItem;
