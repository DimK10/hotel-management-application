import React, {Fragment, useEffect, useState} from 'react';
import NavBar from '../../layout/NavBar';
import DateRangePicker from 'react-bootstrap-daterangepicker';
import {v4 as uuidv4} from 'uuid';
import 'react-calendar/dist/Calendar.css';

import 'bootstrap-daterangepicker/daterangepicker.css';
import SearchItem from './SearchItem';

import cities from '../../../json/cities.json';
import moment from "moment/moment";
import {useDispatch, useSelector} from "react-redux";
import {fetchAllHotelAmenitiesAction, fetchAllRoomAmenitiesAction} from "../../../actions/amenity";
import Loading from "../../layout/Loading";
import Alert from "../../layout/Alert";
import {useNavigate} from "react-router-dom";

const AdvancedSearch = (props) => {

        const {currentOrder} = useSelector(state => state.order);


        const [hotelResults, setHotelResults] = useState([]);

        const [checkUncheck, setCheckUncheck] = useState(true);

        const [citiesArray, setCitiesArray] = useState(cities);

        const [citiesSuggestions, setCitiesSuggestions] = useState([]);

        const [hotelAmenitiesToSelect, setHotelAmenitiesToSelect] = useState([]);

        const [roomAmenitiesToSelect, setRoomAmenitiesToSelect] = useState([]);

        const [formData, setFormData] = useState({
            location: '',
            checkInDate: moment(currentOrder.checkInDate, 'DD/MM/YYYY').toDate(),
            checkOutDate: moment(currentOrder.checkOutDate, 'DD/MM/YYYY').toDate(),
            priceFrom: 0,
            priceTo: 0,
            adultsRange: 1,
            stars: 1,
            hotelAmenities: [],
            roomAmenities: []
        });

        const {
            location,
            checkInDate,
            checkOutDate,
            adultsRange,
            stars,
        } = formData;

        const onChange = (e) =>
            setFormData({...formData, [e.target.name]: e.target.value});

        const onCheckboxChange = (amenity, type) => {

            amenity.checked = !amenity.checked;

            if (type === "hotelAmenity" && amenity.checked === true)
                setFormData({...formData, hotelAmenities: [...formData.hotelAmenities, amenity]});
            else if (type === "hotelAmenity" && amenity.checked === false)
                setFormData({...formData, hotelAmenities: [...formData.hotelAmenities.filter(el => el.id !== amenity.id)]});

            if (type === "roomAmenity" && amenity.checked === true)
                setFormData({...formData, roomAmenities: [...formData.roomAmenities, amenity]});
            else if (type === "roomAmenity" && amenity.checked === false)
                setFormData({...formData, roomAmenities: [...formData.roomAmenities.filter(el => el.id !== amenity.id)]});
        }


        const onLocationInputChange = (e) => {
            setCitiesSuggestions(
                citiesArray.filter((city) => city.city.includes(e.target.value))
            );
        };

        const handleEvent = (event, picker) => {
            // console.log(picker.startDate.toDate());
            // console.log(picker.endDate.toDate());
        }

        const checkUncheckAll = () => {
            hotelAmenitiesToSelect.map(amenity => onCheckboxChange(amenity, "hotelAmenity"))
            roomAmenitiesToSelect.map(amenity => onCheckboxChange(amenity, "roomAmenity"))

            setHotelAmenitiesToSelect([...hotelAmenitiesToSelect.map(amenity => ({...amenity, checked: checkUncheck}))])
            setRoomAmenitiesToSelect([...roomAmenitiesToSelect.map(amenity => ({...amenity, checked: checkUncheck}))])
            setCheckUncheck(!checkUncheck);
        }

        const {hotels, loading} = useSelector(state => state.search);

        const {hotelAmenities, hotelAmenitiesLoading} = useSelector(state => state.amenity);

        const {roomAmenities, roomAmenitiesLoading} = useSelector(state => state.amenity);

        const dispatch = useDispatch();

        const navigate = useNavigate();

        useEffect(() => {

            dispatch(fetchAllHotelAmenitiesAction());
            dispatch(fetchAllRoomAmenitiesAction());
        }, [])

        useEffect(() => {
                setHotelAmenitiesToSelect([...hotelAmenities.map(amenity => ({...amenity, checked: false}))]);
                setRoomAmenitiesToSelect([...roomAmenities.map(amenity => ({...amenity, checked: false}))])
            }, [hotelAmenities, roomAmenities]
        )


        //test
        useEffect(() => {
            console.log("formData.hotelAmenities:")
            console.log(formData.hotelAmenities)
        }, [formData.hotelAmenities])

        useEffect(() => {
            console.log("formData.roomAmenities:")
            console.log(formData.roomAmenities)
        }, [formData.roomAmenities])

        return (
            loading === true
            ||
            hotelAmenitiesLoading === true
            ||
            roomAmenitiesLoading === true
                ?
                (
                    <Loading/>
                )
                :
                (
                    <Fragment>
                        <NavBar/>
                        <Alert/>
                        <div className='container' style={{marginTop: '3rem'}}>
                            <div className='row'>
                                <div className='col-lg-3 col-md-12 col-sm-12'>
                                    <div className='row justify-content-center'>
                                        <div className='card-group'>
                                            <div className='card mb-4'>
                                                <div className='card-header'>
                                                    <h5 className='text-center'>Search Results</h5>
                                                </div>
                                                <div className='card-body'>
                                                    <div className='mb-3'>
                                                        <label htmlFor='locationInput' className='form-label'>
                                                            Location
                                                        </label>
                                                        <input
                                                            type='text'
                                                            className='form-control'
                                                            id='locationInput'
                                                            placeholder='Location'
                                                            name={location}
                                                            onChange={(e) => onLocationInputChange(e)}
                                                            onBlur={() => setCitiesSuggestions([])}
                                                            list='citiesOptions'
                                                        />
                                                        <datalist id='citiesOptions'>
                                                            {citiesSuggestions.length > 0 &&
                                                                citiesSuggestions.map((city) => (
                                                                    <option key={city.id} value={city.city}/>
                                                                ))}
                                                        </datalist>
                                                    </div>
                                                    <div className='mb-3'>
                                                        <label htmlFor='customRange1' className='form-label'>
                                                            Number of Adults: {adultsRange}
                                                        </label>
                                                        <input
                                                            type='range'
                                                            className='form-range'
                                                            id='customRange1'
                                                            min='1'
                                                            max='10'
                                                            value={adultsRange}
                                                            name='adultsRange'
                                                            onChange={(e) => {
                                                                onChange(e);
                                                            }}
                                                        />
                                                    </div>

                                                    <div className='mb-3'>
                                                        <label
                                                            htmlFor='exampleFormControlInput1'
                                                            className='form-label'
                                                        >
                                                            Check In - Check Out Date
                                                        </label>
                                                        <DateRangePicker
                                                            initialSettings={{
                                                                startDate: checkInDate,
                                                                endDate: checkOutDate,
                                                            }} onApply={handleEvent}
                                                        >
                                                            <input type='text' className='form-control'/>
                                                        </DateRangePicker>

                                                    </div>
                                                    <div className='mb-3'>
                                                        <label htmlFor='starsRange' className='form-label'>
                                                            Stars: {stars}
                                                        </label>
                                                        <input
                                                            type='range'
                                                            className='form-range'
                                                            id='starsRange'
                                                            name='stars'
                                                            min='1'
                                                            max='5'
                                                            value={stars}
                                                            onChange={(e) => {
                                                                onChange(e);
                                                            }}
                                                        />
                                                    </div>
                                                    <hr/>
                                                    <h5>Hotel Amenities</h5>
                                                    <div className="form-check form-check-inline">
                                                        <input className="form-check-input" type="checkbox" value=""
                                                               id="check-uncheck" onChange={() => checkUncheckAll()}/>
                                                        <label className="form-check-label" htmlFor="check-uncheck">
                                                            Check/UnCheck all
                                                        </label>
                                                    </div>
                                                    {
                                                        hotelAmenitiesToSelect.length > 0
                                                        &&
                                                        hotelAmenitiesToSelect.map(amenity => (
                                                            <div className="form-check form-check-inline">
                                                                <input className="form-check-input" type="checkbox" value=""
                                                                       id={amenity.hAmenity} name={amenity.hAmenity}
                                                                       checked={amenity.checked}
                                                                       onChange={(e) => {
                                                                           onCheckboxChange(amenity, "hotelAmenity");
                                                                       }}/>
                                                                <label className="form-check-label"
                                                                       htmlFor={amenity.hAmenity}>
                                                                    {amenity.hAmenity}
                                                                </label>
                                                            </div>
                                                        ))
                                                    }

                                                    <h5>Room Amenities</h5>
                                                    {
                                                        roomAmenitiesToSelect.length > 0
                                                        &&
                                                        roomAmenitiesToSelect.map(amenity => (
                                                            <div className="form-check form-check-inline">
                                                                <input className="form-check-input" type="checkbox" value=""
                                                                       id={amenity.rAmenity} name={amenity.rAmenity}
                                                                       checked={amenity.checked}
                                                                       onChange={(e) => {
                                                                           onCheckboxChange(amenity, "roomAmenity");
                                                                       }}/>
                                                                <label className="form-check-label"
                                                                       htmlFor={amenity.rAmenity}>
                                                                    {amenity.rAmenity}
                                                                </label>
                                                            </div>
                                                        ))
                                                    }


                                                    <hr/>
                                                    <div className='row'>
                                                        <div className='col-6'>
                                                            <button className='btn btn-primary px-4' type='button'>
                                                                Search
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>

                                <div className="col-lg-9 col-md-12 col-sm-12">
                                    <div className='input-group pb-3'>
                                        <input
                                            type='text'
                                            className='form-control'
                                            placeholder='Search for a hotel name'
                                            aria-label='Search for a hotel name'
                                            aria-describedby='button-addon2'
                                        />
                                        <button className='btn btn-primary px-4' type='button'>
                                            Search
                                        </button>
                                    </div>
                                    {
                                        hotels !== null
                                        &&
                                        hotels.length > 0
                                        &&
                                        loading !== true
                                        &&
                                        hotels.map(hotel => (
                                            <SearchItem key={uuidv4()} checkInDate={checkInDate} checkOutDate={checkOutDate}
                                                        hotel={hotel}/>
                                        ))
                                    }

                                </div>
                            </div>
                        </div>
                    </Fragment>
                )
        );
    }
;

AdvancedSearch.propTypes = {};


export default AdvancedSearch;
