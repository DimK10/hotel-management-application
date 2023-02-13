import React, {Fragment, useEffect, useState} from 'react';
import SidebarComp from "../../layout/Sidebar";
import HeaderNav from "../../layout/HeaderNav";
import Alert from "../../layout/Alert";
import {useDispatch, useSelector} from "react-redux";
import {getAllHotelsAction} from "../../../actions/hotel";
import Select from "react-select";
import {fetchAllRoomsFromSelectedHotel, getCountOfRoomsAction} from "../../../actions/room";
import RoomTable from "./RoomTable";


const Rooms = props => {

    const [hotelsToSelect, setHotelsToSelect] = useState([]);

    const [hotelSelected, setHotelSelected] = useState(null);

    const dispatch = useDispatch();

    const {hotels} = useSelector(state => state.hotel);

    const {count, rooms} = useSelector(state => state.room);

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
        if (hotelSelected !== null && hotelSelected !== 'undefined' && user !== null)
            dispatch(getCountOfRoomsAction(hotelSelected.value, user.id));
            // dispatch(fetchAllRoomsFromSelectedHotel(hotelSelected.value));
    }, [hotelSelected]);

    useEffect(() => {
        if (hotelSelected !== null && hotelSelected !== 'undefined')
            dispatch(fetchAllRoomsFromSelectedHotel(0, 10, 'id', hotelSelected.value));
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

                    rooms.length > 0
                        ?
                        (
                            <RoomTable />
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
