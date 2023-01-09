import React, {Fragment, useState} from 'react';
import NavBar from '../layout/NavBar';
import {Link} from 'react-router-dom';
import DateRangePicker from "react-bootstrap-daterangepicker";

const FirstPage = (props) => {

    const [formData, setFormData] = useState({
        checkInDate: new Date(),
        checkOutDate: new Date(new Date().setDate(new Date().getDate() + 7))
    });

    const {
        checkInDate,
        checkOutDate,
        nameOrLocation
    } = formData;

    const handleEvent = (event, picker) => {

        setFormData({...formData,
            checkInDate: picker.startDate.toDate(),
            checkOutDate: picker.endDate.toDate()
        });
    }

    const onSubmit = (e) => {
        e.preventDefault();

        setFormData({...formData,
            [e.target.name]: e.target.value
        });

    }

    return (
        <Fragment>
            <NavBar/>
            <div className='container'>
                <div className='jumbotron' style={{marginTop: '7rem'}}>
                    <h1 className='display-4'>
                        Search The place You want To Stay For Your Next Location.
                    </h1>
                    <p className='lead'>
                        You can do this by simply searching for a place below, or from
                        choosing from one of our suggestions.
                    </p>
                    <hr className='my-5'/>
                </div>

                <div className='mt-5'>
                    <form onSubmit={(e) => onSubmit(e)}>
                        <div className='input-group p-3'>
                            <DateRangePicker
                                initialSettings={{
                                    startDate: checkInDate,
                                    endDate: checkOutDate,
                                }} onApply={handleEvent}
                            >
                                <input type='text' className='form-control'/>
                            </DateRangePicker>
                            <input
                                type='text'
                                className='form-control'
                                placeholder='Search for anywhere'
                                aria-label='Search for anywhere'
                                aria-describedby='button-addon2'
                                name='nameOrLocation'
                                style={{width: '45%'}}
                            />
                            <Link className='btn btn-outline-primary' to='/search'>
                                Search
                            </Link>
                        </div>
                    </form>
                    <div className='mt-5'>
                        <h1 className='display-6 m-3'>Explore By Location</h1>
                        <div className='mt-5 d-flex flex-column flex-sm-row'>
                            <div className='card m-3' style={{width: '18rem'}}>
                                <img
                                    src='https://media.cntraveler.com/photos/5ad0ca78fb3e8334dea6e22e/16:9/w_2560%2Cc_limit/GettyImages-88786323.jpg'
                                    className='card-img-top'
                                    alt='...'
                                />
                                <div className='card-body'>
                                    <h5 className='card-title'>Athens</h5>
                                    <p className='card-text'>
                                        Some quick example text to build on the card title and make
                                        up the bulk of the card's content.
                                    </p>
                                    <a href='#' className='btn btn-primary'>
                                        Go somewhere
                                    </a>
                                </div>
                            </div>

                            <div className='card m-3' style={{width: '18rem'}}>
                                <img
                                    src='https://thessaloniki.travel/wp-content/uploads/2021/09/DJI_0134-min-scaled.jpg'
                                    className='card-img-top'
                                    alt='...'
                                />
                                <div className='card-body'>
                                    <h5 className='card-title'>Thessaloniki</h5>
                                    <p className='card-text'>
                                        Some quick example text to build on the card title and make
                                        up the bulk of the card's content.
                                    </p>
                                    <a href='#' className='btn btn-primary'>
                                        Go somewhere
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </Fragment>
    );
};

FirstPage.propTypes = {};

export default FirstPage;
