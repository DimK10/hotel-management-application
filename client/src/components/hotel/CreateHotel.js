import React, {Fragment, useState} from 'react';
import PropTypes from 'prop-types';
import SidebarComp from "../layout/Sidebar";
import HeaderNav from "../layout/HeaderNav";

CreateHotel.propTypes = {};

function CreateHotel(props) {


    const [formData, setFormData] = useState({
        name: '',
        stars: 1,
        areaName: '',
        disabled: false
    })

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
                                           aria-describedby="name" onChange={(e) => {
                                        onChange(e);
                                    }}/>
                                </div>
                                <div className='mb-3'>
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
                                    />
                                </div>

                                <div className="mb-3">
                                    <label htmlFor="areaName" className="form-label">Area Name:</label>
                                    <input type="text" className="form-control" id="areaName"
                                           aria-describedby="areaName" onChange={(e) => {
                                        onChange(e);
                                    }}/>
                                </div>

                                <div className="form-check">
                                    <input className="form-check-input" type="checkbox" value={disabled} id="disabled"
                                           name="disabled"
                                           onChange={(e) => {
                                        onCheckboxChange(e);
                                    }}/>
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

export default CreateHotel;