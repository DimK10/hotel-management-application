import React, {Fragment, useState} from 'react';
import PropTypes from 'prop-types';
import NavBar from "../layout/NavBar";
import moment from "moment/moment";
import {useDispatch, useSelector} from "react-redux";
import {getNewOrder} from "../../actions/order";
import RoomTable from "./RoomTable";


const PlaceNewOrder = () => {

    // todo check if order details exist, else show nothing

    const dispatch = useDispatch();

    const {hotel} = useSelector(state => state.order);

    const {checkInDate, checkOutDate} = useSelector(state => state.order);

    const [roomSelected, setRoomSelected] = useState(null);


    const onRoomSelect = (room) => {
        setRoomSelected(room);
    }

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
                            <div className="col-md-4 text-center">
                                <img
                                    src="https://thumbs.dreamstime.com/b/no-image-available-icon-flat-vector-no-image-available-icon-flat-vector-illustration-132482953.jpg"
                                    className="img-fluid rounded-start h-100" style={{
                                    margin: '0 auto',
                                    maxHeight: '20rem'
                                }} alt="..."/>
                            </div>
                            <div className="col-md-8 container">

                                <div className="mb-3 row">
                                    <label htmlFor="staticText1"
                                           className="col-sm-2 col-form-label">Name:</label>
                                    <div className="col-sm-10">
                                        <p id='staticText1' className="form-control-plaintext fw-bold">{hotel.name}</p>
                                    </div>
                                </div>
                                <div className="mb-3 row">
                                    <h6
                                        className="card-subtitle mb-2">From: {checkInDate} To: {checkOutDate}</h6>
                                </div>
                                <hr/>
                                <h6>Hotel Amenities</h6>
                                {
                                    hotel.hotelAmenityDTO.map(amenity => (
                                        <div className="mb-3 row">
                                            <label htmlFor="staticText1"
                                                   className="col-sm-2 col-form-label">{amenity.hotelAmenities.charAt(0) + amenity.hotelAmenities.slice(1).toLowerCase()}:</label>
                                            <div className="col-sm-10">
                                                <p id='staticText1' className="form-control-plaintext">price
                                                    included<small
                                                        className='text-danger'>*</small></p>
                                            </div>
                                        </div>
                                    ))
                                }
                                <hr/>
                                <div className="row">
                                    <h6>Available Rooms:</h6>
                                    <RoomTable rooms={hotel.rooms} onRoomSelect={onRoomSelect}/>
                                </div>
                                <div className="row mb-md-4 mb-lg-5">
                                    <h6
                                        className="col-sm-2 col-form-label">Total:</h6>
                                    <div className="col-sm-10">
                                        <p id='staticText1'
                                           className="form-control-plaintext fw-bold">{roomSelected !== null ? roomSelected.price : hotel.rooms[0].price}€</p>
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


export default PlaceNewOrder;