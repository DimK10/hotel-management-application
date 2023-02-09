import React from 'react';
import PropTypes from 'prop-types';
import moment from "moment/moment";
import {v4 as uuidv4} from "uuid";

const OrderItem = props => {
    return (
        <>
            <div className="row">
                <div className="col">
                    <div className="card mb-3 btn btn-raised shadow text-start p-0"
                         onClick={() => onHotelCardClick(hotel)}>
                        <div className="row g-0">
                            <div className="col-md-3">
                                <img
                                    src="https://thumbs.dreamstime.com/b/no-image-available-icon-flat-vector-no-image-available-icon-flat-vector-illustration-132482953.jpg"
                                    className="img-fluid rounded-start h-100" alt="..."/>
                            </div>
                            <div className="col-md-9">
                                <div className="card-body">
                                    <h5 className="card-title">{hotel.name}</h5>
                                    <h6 className="card-subtitle mb-2">From Price: {hotel.rooms[0].price} € per night</h6>
                                    <h6
                                        className="card-subtitle mb-2">From: {moment(checkInDate).format('DD/MM/YYYY')} To: {moment(checkOutDate).format('DD/MM/YYYY')}</h6>
                                    <p className="card-text">{hotel.description}</p>

                                    <p className="card-text">
                                        <small className="text-muted">Hotel Amenities</small>
                                    </p>
                                    <div className="container-fluid">
                                        {
                                            hotel.hotelAmenityDTO.map(amenity => (
                                                <span
                                                    key={uuidv4()} className="badge rounded-pill text-bg-primary m-1 btn">{amenity.hAmenity}</span>
                                            ))

                                        }
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
};

OrderItem.propTypes = {

};

export default OrderItem;