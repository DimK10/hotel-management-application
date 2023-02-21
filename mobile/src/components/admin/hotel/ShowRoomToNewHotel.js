import React from 'react';
import PropTypes from 'prop-types';
import CIcon from "@coreui/icons-react";
import {cilX} from "@coreui/icons";
import {v4 as uuidv4} from 'uuid';


function ShowRoomToNewHotel({room, onRoomCloseButtonClick}) {

    const {
        id,
        name,
        luxurity,
        price,
        capacity,
        amenities,
        status,
    } = room;

    return (
        <div className="card">
            <div className="card-body">
                <div className="card-title d-flex flex-row justify-content-between">
                    <h4>{name}</h4>
                    <button type="button"
                            className="btn btn-danger"
                            onClick={() => onRoomCloseButtonClick(id)}
                    >
                        <CIcon icon={cilX} style={{color: "white"}}/>
                    </button>
                </div>


                <div className='mb-3'>
                    <div className="row">
                        <div className="col">
                            <label htmlFor="luxurity"
                                   className="col-form-label">Luxurity:</label>
                            <div className="col-sm-10">
                                <p id='luxurity'
                                   className="form-control-plaintext fw-bold">{luxurity}</p>
                            </div>
                        </div>
                        <div className="col">
                            <label htmlFor="price"
                                   className="col-form-label">Price:</label>
                            <div className="col-sm-10">
                                <p id='price'
                                   className="form-control-plaintext fw-bold">{price}</p>
                            </div>
                        </div>
                        <div className="col">
                            <div className="mb-3">
                                <label htmlFor="capacity"
                                       className="col-form-label">Capacity:</label>
                                <div className="col-sm-10">
                                    <p id='capacity'
                                       className="form-control-plaintext fw-bold">{capacity}</p>
                                </div>
                            </div>
                        </div>
                    </div>


                </div>
                <div className="mb-3">

                </div>

                <label htmlFor="amenities"
                       className="col-form-label">Amenities:</label>
                <div className="mb-3">

                    {
                        amenities.map((amenity) => (
                            <span key={uuidv4()}
                                  id="amenities" className="badge rounded-pill text-bg-primary"
                            >{amenity.label}</span>
                        ))
                    }

                </div>
            </div>
        </div>
    );
}

ShowRoomToNewHotel.propTypes = {
    room: PropTypes.object.isRequired,
    onRoomCloseButtonClick: PropTypes.func.isRequired
};

export default ShowRoomToNewHotel;