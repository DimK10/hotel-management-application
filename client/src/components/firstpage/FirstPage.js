import React, {Fragment, useEffect, useState} from 'react';
import NavBar from '../layout/NavBar';
import {Link, useNavigate} from 'react-router-dom';
import DateRangePicker from "react-bootstrap-daterangepicker";
import { useDispatch, useSelector } from 'react-redux';
import {basicSearchAction} from "../../actions/search";
import Alert from "../layout/Alert";
import {
  addUserToOrderAction,
  createNewOrderPreCheckout,
} from '../../actions/order';
import { setAlertAction } from '../../actions/alert';


const FirstPage = (props) => {

    const [formData, setFormData] = useState({
        checkInDate: new Date(),
        checkOutDate: new Date(new Date().setDate(new Date().getDate() + 7))
    });

    const dispatch = useDispatch();

    const navigate = useNavigate();

    const { inProcess, user } = useSelector((state) => state.order.currentOrder);

    const {isAuthenticated, user: authUser} = useSelector(
        (state) => state.auth
    );

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

    const onChange = (e) => {
        setFormData({...formData,
            [e.target.name]: e.target.value
        });
    }

    const onSubmit = async (e) => {
        e.preventDefault();

        await dispatch(createNewOrderPreCheckout(checkInDate, checkOutDate));
        await dispatch(basicSearchAction(formData));
        navigate("/search");
    }

    // scroll to top
    useEffect(() => {
        window.scrollTo(0, 0)
        if (
          isAuthenticated &&
          authUser.role !== 'undefined' &&
          authUser.role === 'CLIENT' &&
          inProcess === true
        ) {
          // TODO NEED TO SEND ORDER TO BACKEND AND CHECK IF EVERYTHING WAS OK - IN THAT DISPATCH, CHECK IF USER WAS LOGGED IN
          // TODO SET IN PROCESS TO FALSE AFTER SUCCESSFULL CREATION OF ORDER
            console.log(authUser);
          dispatch(addUserToOrderAction(authUser));
          dispatch(
            setAlertAction(
              'You order has been placed Successfully!!!',
              'success'
            )
          );
        }
    }, [])

    return (
        <Fragment>
            <NavBar/>
            <div className='container mt-2'>
                <Alert />
                <div className='jumbotron' style={{marginTop: '7rem'}}>
                    <h1 className='display-4'>
                        Search The place You want To Stay For Your Next Trip.
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
                                onChange={((e) =>  onChange(e))}
                            />
                            <button type="submit" className='btn btn-outline-primary'>Search</button>
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
