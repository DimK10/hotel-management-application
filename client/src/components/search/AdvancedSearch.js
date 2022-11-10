import React, {Fragment, useEffect, useState} from 'react';
import PropTypes from 'prop-types';
import NavBar from '../layout/NavBar';
import DateRangePicker from 'react-bootstrap-daterangepicker';

import 'react-calendar/dist/Calendar.css';

import 'bootstrap-daterangepicker/daterangepicker.css';
import SearchItem from './SearchItem';

import cities from '../../json/cities.json';
import moment from "moment";

const AdvancedSearch = (props) => {
  const [formData, setFormData] = useState({
    location: '',
    dateFrom: new Date(),
    dateTo: new Date(new Date().setDate(new Date().getDate() + 7)),
    adultsRange: 1,
    stars: 1,
    parking: false,
    restaurant: false,
    roomService: false,
    gym: false,
    spa: false,
    pool: false,
    freeWifi: false,
    chargingStation: false,
    viewToSeaMountain: false,
    airConditioning: false,
    fireplace: false,
    kitchen: false,
    refrigerator: false,
    miniBar: false,
    washingMachine: false,
    coffeeTeaMachine: false,
    tv: false,
    petsAllowed: false,
    airportTransport: false,
    toiletGrabRails: false,
    bathtubGrabRails: false,
    showerChair: false,
    raisedChair: false,
    wheelchairRamps: false,
    emergencyPhones: false,
    roomsAccessibleElevator: false,
    safeDepositBox: false,
    bathRobe: false,
    hairDryer: false,
    babyHighChair: false
  });

  const [checkUncheck, setCheckUncheck] = useState(true);

  const [citiesArray, setCitiesArray] = useState(cities);
  const [citiesSuggestions, setCitiesSuggestions] = useState([]);

  const {
    location,
    dateFrom,
    dateTo,
    adultsRange,
    stars,
    parking,
    restaurant,
    roomService,
    gym,
    spa,
    pool,
    freeWifi,
    chargingStation,
    viewToSeaMountain,
    airConditioning,
    fireplace,
    kitchen,
    refrigerator,
    miniBar,
    washingMachine,
    coffeeTeaMachine,
    tv,
    petsAllowed,
    airportTransport,
    toiletGrabRails,
    bathtubGrabRails,
    showerChair,
    raisedChair,
    wheelchairRamps,
    emergencyPhones,
    roomsAccessibleElevator,
    safeDepositBox,
    bathRobe,
    hairDryer,
    babyHighChair
  } = formData;

  let disabledDates = [
    new Date(2022, 11, 12),
    new Date(2021, 7, 2),
  ];

  let disabledDate =moment(
    new Date(2022, 10, 12));

  const onChange = (e) =>
    setFormData({...formData, [e.target.name]: e.target.value});

  const onCheckboxChange = (e) =>
    setFormData({...formData, [e.target.name]: e.target.checked});


  const onLocationInputChange = (e) => {
    // citiesSuggestions = citiesArray
    //   .filter((city) => city.city.includes(e.target.value))

    setCitiesSuggestions(
      citiesArray.filter((city) => city.city.includes(e.target.value))
    );
  };

  const isInvalidDateFunc = (date) => {
    console.log(date);

    if(disabledDate.isSame(date)) {
      console.log("-------------------------------inside if-------------------------------")
      return true;
    }
    return false;
  }

  const handleEvent =(event, picker) => {
    console.log(picker.startDate.toDate());
    console.log(picker.endDate.toDate());
  }

  const checkUncheckAll = () => {
    setFormData({
      ...formData,
      parking: checkUncheck,
      restaurant: checkUncheck,
      roomService: checkUncheck,
      gym: checkUncheck,
      spa: checkUncheck,
      pool: checkUncheck,
      freeWifi: checkUncheck,
      chargingStation: checkUncheck,
      viewToSeaMountain: checkUncheck,
      airConditioning: checkUncheck,
      fireplace: checkUncheck,
      kitchen: checkUncheck,
      refrigerator: checkUncheck,
      miniBar: checkUncheck,
      washingMachine: checkUncheck,
      coffeeTeaMachine: checkUncheck,
      tv: checkUncheck,
      petsAllowed: checkUncheck,
      airportTransport: checkUncheck,
      toiletGrabRails: checkUncheck,
      bathtubGrabRails: checkUncheck,
      showerChair: checkUncheck,
      raisedChair: checkUncheck,
      wheelchairRamps: checkUncheck,
      emergencyPhones: checkUncheck,
      roomsAccessibleElevator: checkUncheck,
      safeDepositBox: checkUncheck,
      bathRobe: checkUncheck,
      hairDryer: checkUncheck,
      babyHighChair: checkUncheck,
    });
    setCheckUncheck(!checkUncheck);
  }

  useEffect(() => {
  }, []);

  return (
    <Fragment>
      <NavBar/>
      <div className='container' style={{marginTop: '10rem'}}>
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
                          startDate: dateFrom,
                          endDate: dateTo,
                          isInvalidDate: isInvalidDateFunc
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
                    <h5>Facilities</h5>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="check-uncheck" onChange={() => checkUncheckAll()}/>
                      <label className="form-check-label" htmlFor="check-uncheck">
                        Check/UnCheck all
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="parking" name='parking' checked={parking}
                             onChange={(e) => {
                               onCheckboxChange(e);
                             }}/>
                      <label className="form-check-label" htmlFor="parking">
                        Parking
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="restaurant" name='restaurant' checked={restaurant}
                             onChange={(e) => {
                               onCheckboxChange(e);
                             }}/>
                      <label className="form-check-label" htmlFor="restaurant">
                        Restaurant
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="room-service" name="roomService" checked={roomService}
                             onChange={(e) => {
                               onCheckboxChange(e);
                             }}/>
                      <label className="form-check-label" htmlFor="room-service">
                        Room Service 24h
                      </label>
                    </div>

                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="gym" name="gym" checked={gym} onChange={(e) => {
                        onCheckboxChange(e);
                      }}/>
                      <label className="form-check-label" htmlFor="gym">
                        Gym
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="spa" name="spa" checked={spa} onChange={(e) => {
                        onCheckboxChange(e);
                      }}/>
                      <label className="form-check-label" htmlFor="spa">
                        Spa
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="pool" name="pool" checked={pool} onChange={(e) => {
                        onCheckboxChange(e);
                      }}/>
                      <label className="form-check-label" htmlFor="pool">
                        Pool
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="free-wifi" name="freeWifi" checked={freeWifi}
                             onChange={(e) => {
                               onCheckboxChange(e);
                             }}/>
                      <label className="form-check-label" htmlFor="free-wifi">
                        Free Wifi
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="charging-station" name="chargingStation"
                             checked={chargingStation}
                             onChange={(e) => {
                               onCheckboxChange(e);
                             }}/>
                      <label className="form-check-label" htmlFor="charging-station">
                        Charging station for electric vehicles
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="view" name="viewToSeaMountain"
                             checked={viewToSeaMountain} onChange={(e) => {
                        onCheckboxChange(e);
                      }}/>
                      <label className="form-check-label" htmlFor="view">
                        View to Sea/Mountain
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="air-condition" name="airConditioning"
                             checked={airConditioning}
                             onChange={(e) => {
                               onCheckboxChange(e);
                             }}/>
                      <label className="form-check-label" htmlFor="air-condition">
                        Air conditioning
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="fireplace" name="fireplace" checked={fireplace}
                             onChange={(e) => {
                               onCheckboxChange(e);
                             }}/>
                      <label className="form-check-label" htmlFor="fireplace">
                        Fireplace
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="kitchen" name="kitchen" checked={kitchen}
                             onChange={(e) => {
                               onCheckboxChange(e);
                             }}/>
                      <label className="form-check-label" htmlFor="kitchen">
                        Kitchen
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="refrigerator" name="refrigerator" checked={refrigerator}
                             onChange={(e) => {
                               onCheckboxChange(e);
                             }}/>
                      <label className="form-check-label" htmlFor="refrigerator">
                        Refrigerator
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="mini-bar" name="miniBar" checked={miniBar}
                             onChange={(e) => {
                               onCheckboxChange(e);
                             }}/>
                      <label className="form-check-label" htmlFor="mini-bar">
                        Mini Bar
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="washing-machine" name="washingMachine"
                             checked={washingMachine}
                             onChange={(e) => {
                               onCheckboxChange(e);
                             }}/>
                      <label className="form-check-label" htmlFor="washing-machine">
                        Washing Machine
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="cofee-tea-machine" name="coffeeTeaMachine"
                             checked={coffeeTeaMachine}
                             onChange={(e) => {
                               onCheckboxChange(e);
                             }}/>
                      <label className="form-check-label" htmlFor="cofee-tea-machine">
                        Coffee/Tea Machine
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="tv" name="tv" checked={tv} onChange={(e) => {
                        onCheckboxChange(e);
                      }}/>
                      <label className="form-check-label" htmlFor="tv">
                        TV
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="pets-allowed" name="petsAllowed" checked={petsAllowed}
                             onChange={(e) => {
                               onCheckboxChange(e);
                             }}/>
                      <label className="form-check-label" htmlFor="pets-allowed">
                        Pets Allowed
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="airport-transport" name="airportTransport"
                             checked={airportTransport}
                             onChange={(e) => {
                               onCheckboxChange(e);
                             }}/>
                      <label className="form-check-label" htmlFor="airport-transport">
                        Airport Transport
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="toilet-grab-rails" name="toiletGrabRails"
                             checked={toiletGrabRails}
                             onChange={(e) => {
                               onCheckboxChange(e);
                             }}/>
                      <label className="form-check-label" htmlFor="toilet-grab-rails">
                        Toilet with grab rails
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="bathtub-grab-rails" name="bathtubGrabRails"
                             checked={bathtubGrabRails}
                             onChange={(e) => {
                               onCheckboxChange(e);
                             }}/>
                      <label className="form-check-label" htmlFor="bathtub-grab-rails">
                        Bathtub with grab rails
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="shower-chair" name="showerChair" checked={showerChair}
                             onChange={(e) => {
                               onCheckboxChange(e);
                             }}/>
                      <label className="form-check-label" htmlFor="shower-chair">
                        shower chair
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="raised-toilet" name="raisedChair" checked={raisedChair}
                             onChange={(e) => {
                               onCheckboxChange(e);
                             }}/>
                      <label className="form-check-label" htmlFor="shower-chair">
                        Raised toilet
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="wheelchair-ramps" name="wheelchairRamps"
                             checked={wheelchairRamps}
                             onChange={(e) => {
                               onCheckboxChange(e);
                             }}/>
                      <label className="form-check-label" htmlFor="wheelchair-ramps">
                        Wheelchair ramps
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="emergency-phones" name="emergencyPhones"
                             checked={emergencyPhones}
                             onChange={(e) => {
                               onCheckboxChange(e);
                             }}/>
                      <label className="form-check-label" htmlFor="emergency-phones">
                        Emergency phones in room and bathroom
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="rooms-disabled-guests" name="roomsAccessibleElevator"
                             checked={roomsAccessibleElevator}
                             onChange={(e) => {
                               onCheckboxChange(e);
                             }}/>
                      <label className="form-check-label" htmlFor="rooms-disabled-guests">
                        Rooms for disabled guests accessible by elevator
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="safe-box" name="safeDepositBox" checked={safeDepositBox}
                             onChange={(e) => {
                               onCheckboxChange(e);
                             }}/>
                      <label className="form-check-label" htmlFor="safe-box">
                        Safe deposit box
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="bathrobe" name="bathRobe" checked={bathRobe}
                             onChange={(e) => {
                               onCheckboxChange(e);
                             }}/>
                      <label className="form-check-label" htmlFor="bathrobe">
                        Bathrobe
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="hair-dryer" name="hairDryer" checked={hairDryer}
                             onChange={(e) => {
                               onCheckboxChange(e);
                             }}/>
                      <label className="form-check-label" htmlFor="hair-dryer">
                        Hair dryer
                      </label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input className="form-check-input" type="checkbox" value=""
                             id="baby-high-chair" name="babyHighChair"
                             checked={babyHighChair} onChange={(e) => {
                        onCheckboxChange(e);
                      }}/>
                      <label className="form-check-label" htmlFor="hair-dryer">
                        Baby high chair
                      </label>
                    </div>
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
          <div className='col-9'>
            <SearchItem/>
          </div>
        </div>
      </div>
    </Fragment>
  );
};

AdvancedSearch.propTypes = {};

export default AdvancedSearch;
