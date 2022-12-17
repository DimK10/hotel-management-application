import React, {Fragment, useEffect} from 'react';
import PropTypes from 'prop-types';
import SidebarComp from "../layout/Sidebar";
import HeaderNav from "../layout/HeaderNav";
import {connect} from "react-redux";
import {getAllHotelsByPage, getCountOfHotels} from "../../actions/hotel";
import HotelTable from "./HotelTable";
import Loading from "../layout/Loading";


const Hotel = ({getCountOfHotels, getAllHotelsByPage, hotelState, auth}) => {

  const {loading, user} = auth;
  const {loading: hotelsLoading, count, hotels} = hotelState;

  useEffect(() => {
    if (!loading)
      getCountOfHotels(user?.id);
  },[auth, getCountOfHotels])

  useEffect(() => {
    if (!loading) {
      getAllHotelsByPage(0, 10, 'id', user?.id);
    }
  }, [auth, getAllHotelsByPage]);


  return (
    <Fragment>
      <SidebarComp/>
      <HeaderNav>
        {
          hotelsLoading
            ?
            <Loading key={1}/>
            :
            (
              hotels.length > 0
                ?
                <HotelTable key={1} count={count} hotels={hotels}/>
                :
                <h2>No hotels found</h2>
            )
        }
      </HeaderNav>
    </Fragment>
  );
};

Hotel.propTypes = {
  hotelState: PropTypes.object.isRequired,
  getAllHotelsByPage: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
  auth: state.auth,
  hotelState: state.hotel
})

export default connect(mapStateToProps, {getCountOfHotels, getAllHotelsByPage})(Hotel);
