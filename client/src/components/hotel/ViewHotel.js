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
                                    <h3 className="card-title">Hotel {hotel.name}</h3>
                                    <p className="card-text mb-3">This is the detailed info about your hotel</p>
                                    <div className="mb-3 row">
                                        <div className="col-md-3 col-sm-4">
                                            <img
                                                src="https://images.unsplash.com/photo-1606046604972-77cc76aee944?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=735&q=80"
                                                className="img-fluid rounded-start" alt="..."/>
                                        </div>
                                        <div className="col-md-9 col-sm-8 container text-center" style={{ margin: 'auto 0' }}>

                                            <div className="mb-3 row">
                                                <label htmlFor="stars"
                                                       className="col-sm-5 col-form-label">Stars:</label>
                                                <div className="col-sm-7">
                                                    <p id='stars' className="form-control-plaintext">{hotel.stars}</p>
                                                </div>
                                            </div>
                                            <div className="mb-3 row">
                                                <label htmlFor="location"
                                                       className="col-sm-5 col-form-label">Location:</label>
                                                <div className="col-sm-7">
                                                    <p id='location' className="form-control-plaintext">{hotel.areaName}</p>
                                                </div>
                                            </div>
                                            <div className="mb-3 row">
                                                <label htmlFor="address"
                                                       className="col-sm-5 col-form-label">Address:</label>
                                                <div className="col-sm-7">
                                                    <p id='address' className="form-control-plaintext">{hotel.address}</p>
                                                </div>
                                            </div>

                                            <div className="mb-3 row">
                                                <label htmlFor="rooms"
                                                       className="col-sm-5 col-form-label">Number of rooms:</label>
                                                <div className="col-sm-7">
                                                    <p id='rooms' className="form-control-plaintext">{hotel.rooms.length}</p>
                                                </div>
                                            </div>


                                        </div>

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