import React, {useState} from 'react';
import PropTypes from 'prop-types';
import {MultiSelect} from "react-multi-select-component";



function AddRoomToNewHotel({room, onRoomSubmit, roomAmenitiesToSelect}) {

    const [newRoom, setNewRoom] = useState({...room});

    const [roomAmenitiesSelected, setRoomAmenitiesSelected] = useState([...room.amenities]);

    let {
        name,
        luxurity,
        price,
        capacity,
        amenities
    } = newRoom;

    const onChange = (e) =>
        setNewRoom({...newRoom, [e.target.name]: e.target.value});

    const onSubmit = (e) => {
        e.preventDefault();

        newRoom.amenities = [...roomAmenitiesSelected];
        onRoomSubmit(e, newRoom);
    }

    return (
        <div className="card">
            <div className="card-body">
                <div className="card-title">
                    <h4>Add a new Room</h4>
                </div>

                <div className="mb-3">
                    <label htmlFor="name" className="form-label">Room Name:</label>
                    <input type="text" className="form-control" id="name"
                           aria-describedby="name" placeholder="Room Name"
                           name="name" onChange={(e) => {
                        onChange(e);
                    }} required={true}
                    />
                </div>
                <div className='mb-3 w-25'>
                    <label htmlFor='luxurity' className='form-label'>
                        Luxurity: {luxurity}
                    </label>
                    <input
                        type='range'
                        className='form-range'
                        id='luxurity'
                        min='1'
                        max='5'
                        name='luxurity'
                        onChange={(e) => {
                            onChange(e);
                        }}
                        required={true}
                    />
                </div>
                <div className="mb-3 w-25">
                    <label htmlFor="price" className="form-label">Price:</label>
                    <input type="number" min="0" step=".01" className="form-control" id="price"
                           aria-describedby="name" placeholder="Price" name="price" onChange={(e) => {
                        onChange(e);
                    }} required={true}/>
                </div>

                <div className="mb-3 w-25">
                    <label htmlFor="capacity" className="form-label">Capacity:</label>
                    <input type="number" min="0" className="form-control" id="capacity"
                           aria-describedby="name" placeholder="Capacity" name="capacity" onChange={(e) => {
                        onChange(e);
                    }} required={true}/>
                </div>
                <div className="mb-3">
                    <MultiSelect
                        options={roomAmenitiesToSelect}
                        value={roomAmenitiesSelected}
                        onChange={setRoomAmenitiesSelected}
                        labelledBy="Select Room Amenities"
                    />
                </div>
                <div className="mb-3">
                    <button type="button" className="btn btn-primary" onClick={(e) => onSubmit(e, newRoom)}>Add Room</button>
                </div>
            </div>
        </div>
    );
}

AddRoomToNewHotel.propTypes = {
    room: PropTypes.object.isRequired,
    onRoomSubmit: PropTypes.func.isRequired,
    roomAmenitiesToSelect: PropTypes.arrayOf(PropTypes.object).isRequired,
};
export default AddRoomToNewHotel;