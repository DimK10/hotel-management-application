import React, {Fragment, useEffect} from 'react';
import PropTypes from 'prop-types';
import SidebarComp from "../layout/Sidebar";
import HeaderNav from "../layout/HeaderNav";
import HotelCard from "./HotelCard";
import CIcon from "@coreui/icons-react";
import {cilPencil, cilTrash} from "@coreui/icons";
import {Tooltip} from '@coreui/coreui/dist/js/coreui';
import {connect} from "react-redux";
import {getAllHotelsByPage} from "../../actions/hotel";
import HotelTable from "./HotelTable";


const Hotel = ({getAllHotelsByPage, hotels, auth}) => {

  useEffect(() => {

    const {user} = auth;

    getAllHotelsByPage(0, 10, 'id', user?.id);
  }, [getAllHotelsByPage]);


  return (
    <Fragment>
      <SidebarComp/>
      <HeaderNav>
        {hotels.length > 0
          ?
          <HotelTable hotels={hotels}/>
          :
          <h2>No hotels found</h2>
        }
      </HeaderNav>
    </Fragment>
  );
};

Hotel.propTypes = {
  hotels: PropTypes.array.isRequired,
  getAllHotelsByPage: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
  auth: state.auth,
  hotels: state.hotel.hotels
})

export default connect(mapStateToProps, {getAllHotelsByPage})(Hotel);
