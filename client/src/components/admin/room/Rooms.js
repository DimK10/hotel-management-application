import React, {Fragment, useEffect, useState} from 'react';
import SidebarComp from "../../layout/Sidebar";
import HeaderNav from "../../layout/HeaderNav";
import Alert from "../../layout/Alert";
import {useDispatch, useSelector} from "react-redux";
import {getAllHotelsAction} from "../../../actions/hotel";
import Select from "react-select";
import {fetchAllRoomsFromSelectedHotel, getAllRoomsByPage, getCountOfRoomsAction} from "../../../actions/room";
import RoomTable from "./RoomTable";


const Rooms = props => {

    const [hotelsToSelect, setHotelsToSelect] = useState([]);

    const [hotelSelected, setHotelSelected] = useState(null);

    const dispatch = useDispatch();

    const {hotels} = useSelector(state => state.hotel);

    const {count, rooms, loading} = useSelector(state => state.room);

    const {user} = useSelector(state => state.auth);

    useEffect(() => {
        // Get all hotels
        dispatch(getAllHotelsAction());
    }, []);

    useEffect(() => {
        let hotelsToSelect = [...hotels.map(hotel => ({label: hotel.name, value: hotel.id}))]

        setHotelsToSelect(hotelsToSelect);
    }, [hotels]);

    useEffect(() => {
        console.log(hotelSelected)
        if (hotelSelected !== null && hotelSelected !== 'undefined' && user !== null)
            dispatch(getCountOfRoomsAction(hotelSelected.value, user.id));
    }, [hotelSelected]);

    useEffect(() => {
        if (hotelSelected !== null && hotelSelected !== 'undefined')
            dispatch(getAllRoomsByPage(0, 10, 'id', hotelSelected.value));
    }, [count])


    return (
        <Fragment>
            <SidebarComp/>
            <HeaderNav>
                <Alert/>
                {/* Search Hotel Bar */}
                <Select
                    className="basic-single mb-5"
                    classNamePrefix="select a hotel"
                    options={hotelsToSelect}
                    value={hotelSelected}
                    onChange={setHotelSelected}
                    labelledBy="Select"
                />
                {

                    rooms.length > 0 && hotelSelected !== null && hotelSelected !== 'undefined'
                        ?
                        (
                            <RoomTable hotelSelected={hotelSelected}/>
                        )
                        :
                        (
                            <h4>No rooms found.</h4>
                        )

                }

            </HeaderNav>
        </Fragment>
    );
};

Rooms.propTypes = {};

export default Rooms;
