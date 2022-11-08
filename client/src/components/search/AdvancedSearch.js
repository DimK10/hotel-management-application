import React, { Fragment, useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import NavBar from '../layout/NavBar';
import DateRangePicker from 'react-bootstrap-daterangepicker';

import 'bootstrap-daterangepicker/daterangepicker.css';

let dateNow = Date.now();

const AdvancedSearch = (props) => {
  const [dateFrom, setDateFrom] = useState(new Date());
  const [dateTo, setDateTo] = useState(
    new Date(new Date().setDate(new Date().getDate() + 7))
  );

  const [adultsRange, setAdultsRange] = useState(1);

  useEffect(() => {
    if (dateTo instanceof Date) {
      console.log(dateTo);
    }
  }, []);

  return (
    <Fragment>
      <NavBar />
      <div className='container' style={{ marginTop: '10rem' }}>
        <div className='row'>
          <div className='col-3'>
            <div className='row justify-content-center'>
              <div className='card-group'>
                <div className='card mb-4'>
                  <div className='card-header'>
                    <h5 className='text-center'>Search Results</h5>
                  </div>
                  <div className='card-body'>
                    <div className='mb-3'>
                      <label
                        for='exampleFormControlInput1'
                        className='form-label'
                      >
                        Location
                      </label>
                      <input
                        type='text'
                        className='form-control'
                        id='exampleFormControlInput1'
                        placeholder='Location'
                      />
                    </div>
                    <div className='mb-3'>
                      <label for='customRange1' className='form-label'>
                        Number of Adults: {adultsRange}
                      </label>
                      <input
                        type='range'
                        className='form-range'
                        id='customRange1'
                        min='1'
                        max='10'
                        value={adultsRange}
                        onChange={(e) => {
                          setAdultsRange(e.target.value);
                        }}
                      />
                    </div>

                    <div className='mb-3'>
                      <label
                        for='exampleFormControlInput1'
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
                      <label
                        for='exampleFormControlInput1'
                        className='form-label'
                      >
                        Location
                      </label>
                      <input
                        type='text'
                        className='form-control'
                        id='exampleFormControlInput1'
                        placeholder='Location'
                      />
                    </div>

                    <div className='mb-3'>
                      <label
                        for='exampleFormControlInput1'
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
          </div>
          <div className='col-9'></div>
        </div>
      </div>
    </Fragment>
  );
};

AdvancedSearch.propTypes = {};

export default AdvancedSearch;
