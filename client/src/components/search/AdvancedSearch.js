import React, { Fragment, useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import NavBar from '../layout/NavBar';
import DateRangePicker from 'react-bootstrap-daterangepicker';

import 'bootstrap-daterangepicker/daterangepicker.css';
import SearchItem from './SearchItem';

import cities from '../../json/cities.json';

const AdvancedSearch = (props) => {
  const [formData, setFormData] = useState({
    location: '',
    dateFrom: new Date(),
    dateTo: new Date(new Date().setDate(new Date().getDate() + 7)),
    adultsRange: 1,
    stars: 1,
  });

  const [citiesArray, setCitiesArray] = useState(cities);
  const [citiesSuggestions, setCitiesSuggestions] = useState([]);

  const { location, dateFrom, dateTo, adultsRange, stars } = formData;

  const onChange = (e) =>
    setFormData({ ...formData, [e.target.name]: e.target.value });

  const onLocationInputChange = (e) => {
    // citiesSuggestions = citiesArray
    //   .filter((city) => city.city.includes(e.target.value))

    setCitiesSuggestions(
      citiesArray.filter((city) => city.city.includes(e.target.value))
    );
  };

  useEffect(() => {}, []);

  return (
    <Fragment>
      <NavBar />
      <div className='container' style={{ marginTop: '10rem' }}>
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
                        onChange={(e) => onLocationInputChange(e)}
                        list='citiesOptions'
                      />
                      <datalist id='citiesOptions'>
                        {citiesSuggestions.length > 0 &&
                          citiesSuggestions.map((city) => (
                            <option value={city.city} />
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
                        }}
                      >
                        <input type='text' className='form-control' />
                      </DateRangePicker>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div className='row'>
              <div className='card-group'>
                <div className='card'>
                  <div className='card-header'>
                    <h5 className='text-center'>Categories</h5>
                  </div>
                  <div className='card-body'>
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
            <SearchItem />
          </div>
        </div>
      </div>
    </Fragment>
  );
};

AdvancedSearch.propTypes = {};

export default AdvancedSearch;
