import React, {Fragment, useState} from 'react';
import PropTypes from 'prop-types';
import SidebarComp from "../layout/Sidebar";
import HeaderNav from "../layout/HeaderNav";

import cities from '../../json/cities.json';
import {connect} from "react-redux";

CreateHotel.propTypes = {};

function CreateHotel({auth}) {


    // TODO ADD ROOMS WITH CREATE HOTEL FORM
    const [formData, setFormData] = useState({
        name: '',
        stars: 1,
        areaName: '',
        disabled: false,
        owner: auth.user,
        rooms: []
    })

    const [citiesArray, setCitiesArray] = useState(cities);
    const [citiesSuggestions, setCitiesSuggestions] = useState([]);


    const {
        name,
        stars,
        areaName,
        disabled,
    } = formData;

    const onChange = (e) =>
        setFormData({...formData, [e.target.name]: e.target.value});

    const onCheckboxChange = (e) =>
        setFormData({...formData, [e.target.name]: e.target.checked});

    const onLocationInputChange = (e) => {
        setCitiesSuggestions(
            citiesArray.filter((city) => city.city.includes(e.target.value))
        );

        onChange(e);
    };

    return (
        <Fragment>
            <SidebarComp/>
            <HeaderNav>
                <div className="row">
                    <div className="card">
                        <div className="card-body">
                            <div className="card-title">
                                <h4>Add a new Hotel</h4>
                            </div>
                            <form>
                                <div className="mb-3">
                                    <label htmlFor="name" className="form-label">Hotel Name:</label>
                                    <input type="text" className="form-control" id="name"
                                           aria-describedby="name" placeholder="Hotel Name" onChange={(e) => {
                                        onChange(e);
                                    }} required="true"/>
                                </div>
                                <div className='mb-3 w-25'>
                                    <label htmlFor='stars' className='form-label'>
                                        Stars: {stars}
                                    </label>
                                    <input
                                        type='range'
                                        className='form-range'
                                        id='stars'
                                        min='1'
                                        max='5'
                                        value={stars}
                                        name='stars'
                                        onChange={(e) => {
                                            onChange(e);
                                        }}
                                        required="true"
                                    />
                                </div>
                                <div className='mb-3'>
                                    <label htmlFor='areaName' className='form-label'>
                                        Location
                                    </label>
                                    <input
                                        type='text'
                                        className='form-control'
                                        id='areaName'
                                        placeholder='Location'
                                        name='areaName'
                                        onChange={(e) => onLocationInputChange(e)}
                                        onBlur={() => setCitiesSuggestions([])}
                                        list='citiesOptions'
                                        required="true"
                                    />
                                    <datalist id='citiesOptions'>
                                        {citiesSuggestions.length > 0 &&
                                            citiesSuggestions.map((city) => (
                                                <option key={city.id} value={city.city}/>
                                            ))}
                                    </datalist>
                                </div>

                                <div className="mb-3">
                                    <label htmlFor="address" className="form-label">Address:</label>
                                    <input type="text" className="form-control" id="address"
                                               aria-describedby="address" placeholder="Address" onChange={(e) => {
                                            onChange(e);
                                        }} required="true"
                                    />
                                </div>

                                <div className="form-check mb-5">
                                    <input className="form-check-input" type="checkbox" value={disabled} id="disabled"
                                           name="disabled"
                                           onChange={(e) => {
                                               onCheckboxChange(e);

                                           }}
                                    />
                                    <label className="form-check-label" htmlFor="disabled">
                                        Disabled
                                    </label>
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

const mapStateToProps = state => ({
    auth: state.auth
});

export default connect(mapStateToProps)(CreateHotel);