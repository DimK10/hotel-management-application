import React from 'react';
import PropTypes from 'prop-types';



function AddRoomToNewHotel(props) {
    return (
        <div className="card">
            <div className="card-body">
                <div className="card-title">
                    <h4>Add a new Room</h4>
                </div>
                <form action="">
                    <div className="mb-3">
                        <label htmlFor="name" className="form-label">Room Name:</label>
                        <input type="text" className="form-control" id="name"
                               aria-describedby="name" placeholder="Room Name" onChange={(e) => {
                            onChange(e);
                        }} required="true"/>
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
                            required="true"
                        />
                    </div>
                    <div className="mb-3 w-25">
                        <label htmlFor="price" className="form-label">Price:</label>
                        <input type="number" min="0" step=".01"className="form-control" id="price"
                               aria-describedby="name" placeholder="Price" onChange={(e) => {
                            onChange(e);
                        }} required="true"/>
                    </div>

                    <div className="mb-3 w-25">
                        <label htmlFor="capacity" className="form-label">Capacity:</label>
                        <input type="number" min="0" className="form-control" id="capacity"
                               aria-describedby="name" placeholder="Capacity" onChange={(e) => {
                            onChange(e);
                        }} required="true"/>
                    </div>
                </form>
            </div>
        </div>
    );
}

AddRoomToNewHotel.propTypes = {

};
export default AddRoomToNewHotel;