import React, {useEffect, useState} from 'react';
import PropTypes, {object} from 'prop-types';
import CIcon from "@coreui/icons-react";
import {cilPencil, cilTrash} from "@coreui/icons";
import {v4 as uuidv4} from "uuid";

const RoomTable = ({rooms, onRoomSelect}) => {


    const onRadioChange = (room) => {
        onRoomSelect(room);
    }

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
                        <th scope="col">Select</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        rooms.map(room => (
                            <tr  key={uuidv4()} >
                                <th scope="row">{rooms.indexOf(room) + 1}</th>
                                <td>{room.name}</td>
                                <td>{room.luxurity}</td>
                                <td>
                                    {
                                        room.amenities.map(amenity => (
                                            amenity.rAmenity))
                                    }
                                </td>
                                <td>{room.price} €</td>
                                <td className="d-none d-md-table-cell">{room.capacity}</td>
                                <td>
                                    <input className="form-check-input" type="radio" value=""
                                           defaultChecked={rooms.indexOf(room) === 0} name="select-room"
                                           onChange={(e) => onRadioChange(room)} />
                                </td>
                            </tr>
                        ))
                    }
                    </tbody>
                </table>
            </div>

        </>
    );
};

RoomTable.propTypes = {
    rooms: PropTypes.arrayOf(object).isRequired,
    onRoomSelect: PropTypes.func.isRequired,
};

export default RoomTable;