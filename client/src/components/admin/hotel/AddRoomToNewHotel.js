import React, {useState} from 'react';
import PropTypes from 'prop-types';
import {MultiSelect} from "react-multi-select-component";
import CIcon from '@coreui/icons-react';
import {cilX} from '@coreui/icons';


function AddRoomToNewHotel({room, roomAmenitiesToSelect, onRoomSubmit, onRoomCloseButtonClick}) {

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

        newRoom.status = "show";
        newRoom.amenities = [...roomAmenitiesSelected];
        onRoomSubmit(e, newRoom);
    }

    return (
        <div className="card">
            <div className="card-body">
                <div className="card-title d-flex flex-row justify-content-between">
                    <h4>Add a new Room</h4>
                    <button type="button"
                            className="btn btn-danger"
                            onClick={() => onRoomCloseButtonClick(room.id)}
                    >
                        <CIcon icon={cilX} style={{color: "white"}}/>
                    </button>
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
                    <input type="number"
                           min="1"
                           step="1"
                           className="form-control"
                           id="price"
                           aria-describedby="name"
                           placeholder="Price"
                           name="price"
                           onKeyPress={(e) => (e.charCode >= 48 && e.charCode <= 57)}
                           onKeyDown={(e) => {
                               if(e.key==='.')
                                   e.preventDefault();
                           }}  onInput={(e) => {e.target.value = e.target.value.replace(/[^0-9]*/g,'')}}
                           onChange={(e) => {
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
                    <button type="button" className="btn btn-primary" onClick={(e) => onSubmit(e, newRoom)}>Add Room
                    </button>
                </div>
            </div>
        </div>
    );
}

AddRoomToNewHotel.propTypes = {
    room: PropTypes.object.isRequired,
    onRoomSubmit: PropTypes.func.isRequired,
    onRoomCloseButtonClick: PropTypes.func.isRequired,
    roomAmenitiesToSelect: PropTypes.arrayOf(PropTypes.object).isRequired,
};
export default AddRoomToNewHotel;