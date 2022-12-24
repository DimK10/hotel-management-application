import React from 'react';
import SidebarComp from "../layout/Sidebar";
import {useParams} from "react-router-dom";
import HeaderNav from "../layout/HeaderNav";

const ShowHotel = () => {

    const { hotelId } = useParams();

    return (
        <>
            <SidebarComp/>
            <HeaderNav>
                <div className="row">
                    <div className="card">
                        <div className="card-body">
                            <div className="card-title">
                                <h4>Hotel {hotelId}</h4>
                            </div>
                            <div className="card-body">

                            </div>
                        </div>
                    </div>
                </div>
            </HeaderNav>
        </>
    );
};

export default ShowHotel;