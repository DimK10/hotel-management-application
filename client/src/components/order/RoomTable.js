import React from 'react';
import PropTypes from 'prop-types';
import CIcon from "@coreui/icons-react";
import {cilPencil, cilTrash} from "@coreui/icons";

const RoomTable = ({room, roomIndex}) => {
    return (
        <>
            <div className="table-responsive">
                <table className="table align-middle">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col">Luxurity</th>
                        <th scope="col">Room Amenities</th>
                        <th scope="col">Price</th>
                        <th scope="col" className="d-none d-md-table-cell">Capacity</th>
                        <th scope="col">Select/Deselect</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th scope="row">{roomIndex}</th>
                        <td>{room.name}</td>
                        <td>{room.luxurity}</td>
                        <td>
                            {
                                room.roomAmenityDTO.map(amenity => (
                                    amenity.roomAmenities.charAt(0) + amenity.roomAmenities.slice(1).toLowerCase() + ' '
                                ))
                            }
                        </td>
                        <td>{room.price} €</td>
                        <td className="d-none d-md-table-cell">{room.capacity}</td>
                        <td>
                            <input className="form-check-input" type="checkbox" value="" id="flexCheckChecked"
                                   defaultChecked={false}/>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

        </>
    );
};

RoomTable.propTypes = {
    room: PropTypes.object.isRequired,
    roomIndex: PropTypes.number.isRequired
};

export default RoomTable;