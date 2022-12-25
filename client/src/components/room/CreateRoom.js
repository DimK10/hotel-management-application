import React, {Fragment, useState} from 'react';
import SidebarComp from "../layout/Sidebar";
import HeaderNav from "../layout/HeaderNav";
import cities from "../../json/cities.json";

CreateRoom.propTypes = {};

function CreateRoom(props) {

    const [formData, setFormData] = useState({
        name: '',
        luxurity: 1,
        hotel: null,
        price: 0,
        capacity: 0,
    })

    const [citiesArray, setCitiesArray] = useState(cities);
    const [citiesSuggestions, setCitiesSuggestions] = useState([]);


    const {
        name,
        luxurity,
        hotel,
        price,
        capacity,
    } = formData;

    const onChange = (e) =>
        setFormData({...formData, [e.target.name]: e.target.value});

    const onCheckboxChange = (e) =>
        setFormData({...formData, [e.target.name]: e.target.checked});


    return (
        <Fragment>
            <SidebarComp/>
            <HeaderNav>
                <div className="row">
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
                                {/*todo add vaslidation if vaslue is null*/}
                                {/*todo add fetch hotel names and id's*/}
                                <select className="form-select" aria-label="Select Hotel" required="true" value="">
                                    <option disabled="true" value="null" selected="true">Which hotel this room belongs
                                        to?
                                    </option>
                                    <option value="hotel#1">Hotel#1</option>
                                    <option value="hotel#2">Hotel#2</option>
                                    <option value="hotel#3">Hotel#3</option>
                                </select>

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

                                <button type="submit" className="btn btn-primary">Add</button>
                            </form>
                        </div>
                    </div>
                </div>
            </HeaderNav>
        </Fragment>
    );
}

export default CreateRoom;