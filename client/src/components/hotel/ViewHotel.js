import React, {useEffect} from 'react';
import SidebarComp from "../layout/Sidebar";
import {useParams} from "react-router-dom";
import HeaderNav from "../layout/HeaderNav";
import NotFound from "../error/NotFound";
import {useDispatch, useSelector} from "react-redux";
import {getHotelByIdAction} from "../../actions/hotel";


const ViewHotel = () => {

    const {hotelId} = useParams();

    const dispatch = useDispatch();

    const {hotel} = useSelector(state => state.hotel);

    useEffect(() => {
        dispatch(getHotelByIdAction(hotelId));
    }, [hotelId])

    return (
        <>
            <SidebarComp/>
            <HeaderNav>
                {
                    hotelId !== null
                        ?
                        <div className="row">
                            <div className="card">
                                <div className="card-body">
                                    <div className="card-title">
                                        <h4>Hotel {hotel.id}</h4>
                                    </div>
                                    <div className="card-body">

                                    </div>
                                </div>
                            </div>
                        </div>
                        :
                        <NotFound/>
                }

            </HeaderNav>
        </>
    );
};

export default ViewHotel;