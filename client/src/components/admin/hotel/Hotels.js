import React, {Fragment, useEffect} from 'react';
import SidebarComp from "../../layout/Sidebar";
import HeaderNav from "../../layout/HeaderNav";
import {useDispatch, useSelector} from "react-redux";
import {getAllHotelsByPage, getCountOfHotelsAction} from "../../../actions/hotel";
import HotelTable from "./HotelTable";
import Loading from "../../layout/Loading";


const Hotel = () => {

  const dispatch = useDispatch();

  const {loading: hotelsLoading, count, hotels} = useSelector(state => state.hotel);

  const {loading, user, auth} = useSelector(state => state.auth);


  useEffect(() => {
    if (!loading) {
      dispatch(getCountOfHotelsAction(user?.id));
      dispatch(getAllHotelsByPage(0, 10, 'id', user?.id));
    }

  }, [loading, auth])

  return (
    <Fragment>
      <SidebarComp/>
      <HeaderNav>
        {
          hotelsLoading
            ?
            <Loading key={'first'}/>
            :
            (
              hotels.length > 0
                ?
                <HotelTable key={'hTable'}/>
                :
                <h2>No hotels found</h2>
            )
        }
      </HeaderNav>
    </Fragment>
  );
};

export default Hotel;