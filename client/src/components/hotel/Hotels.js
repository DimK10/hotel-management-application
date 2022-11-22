import React, {Fragment} from 'react';
import PropTypes from 'prop-types';
import SidebarComp from "../layout/Sidebar";
import HeaderNav from "../layout/HeaderNav";
import HotelCard from "./HotelCard";

const Hotel = props => {
  return (
    <Fragment>

      <SidebarComp/>
      <HeaderNav >
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
        <HotelCard />
        <HotelCard />
        <HotelCard />
        <HotelCard />
        <HotelCard />
        <HotelCard />
      </HeaderNav>
    </Fragment>
  );
};

Hotel.propTypes = {

};

export default Hotel;
