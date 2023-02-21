import React, {Fragment, useEffect} from 'react';
import {useNavigate, useParams} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {loadUser} from "../../../actions/auth";
import SidebarComp from "../../layout/Sidebar";
import HeaderNav from "../../layout/HeaderNav";
import Alert from "../../layout/Alert";
import CIcon from "@coreui/icons-react";
import {cilPencil} from "@coreui/icons";
import Loading from "../../layout/Loading";
import NotFound from "../../error/NotFound";
import {getRoomByIdAction} from "../../../actions/room";
import {v4 as uuidv4} from "uuid";

const ViewRoom = props => {


    const {roomId} = useParams();

    const dispatch = useDispatch();

    const {room} = useSelector(state => state.room);

    const {user} = useSelector(state => state.auth);

    const navigate = useNavigate();

    useEffect(() => {

        if (user === null) {
            dispatch(loadUser);
        }

        dispatch(getRoomByIdAction(roomId));
    }, [roomId])

    const onUpdateClick = () => {
        navigate(`/rooms/update/${roomId}`);
    }

    return (
        <>
            <SidebarComp/>
            <HeaderNav>
                <Alert/>
                {
                    roomId !== null
                    &&
                    Object.keys(room).length > 0
                        ?
                        (
                            user !== null
                                ?
                                <div className="row">
                                    <div className="card">
                                        <div className="card-body">
                                            <h3 className="card-title d-flex justify-content-between">
                                                <p style={{margin: 'auto 0'}}>Room {room.name}</p>
                                                <button type="button" className="btn btn-success"
                                                        style={{color: '#fff', marginRight: '0.3rem'}}
                                                    // data-coreui-toggle="tooltip" data-coreui-placement="top"
                                                    // title="Edit this hotel"
                                                    // onMouseOver={(e) => {
                                                    //     Tooltip.getOrCreateInstance(e.target).show()
                                                    // }}
                                                        onClick={onUpdateClick}
                                                >
                                                    <CIcon className="btn-icon" icon={cilPencil}
                                                           style={{marginRight: '.2rem'}}/>
                                                    Edit
                                                </button>

                                            </h3>

                                            <p className="card-text mb-3">This is the detailed info about your
                                                room</p>

                                            <div className="mb-3 row">
                                                <div className="col-md-3 col-sm-4">
                                                    <img
                                                        src="https://images.unsplash.com/photo-1606046604972-77cc76aee944?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=735&q=80"
                                                        className="img-fluid rounded-start" alt="..."/>
                                                </div>
                                                <div className="col-md-9 col-sm-8 container text-center"
                                                     style={{margin: 'auto 0'}}>

                                                    <div className="mb-3 row">
                                                        <label htmlFor="luxurity"
                                                               className="col-sm-5 col-form-label">Luxurity:</label>
                                                        <div className="col-sm-7">
                                                            <p id='luxurity'
                                                               className="form-control-plaintext">{room.luxurity}</p>
                                                        </div>
                                                    </div>
                                                    <div className="mb-3 row">
                                                        <label htmlFor="price"
                                                               className="col-sm-5 col-form-label">Price:</label>
                                                        <div className="col-sm-7">
                                                            <p id='price'
                                                               className="form-control-plaintext">{room.price}</p>
                                                        </div>
                                                    </div>
                                                    <div className="mb-3 row">
                                                        <label htmlFor="capacity"
                                                               className="col-sm-5 col-form-label">Capacity:</label>
                                                        <div className="col-sm-7">
                                                            <p id='capacity'
                                                               className="form-control-plaintext">{room.capacity}</p>
                                                        </div>
                                                    </div>
                                                    {
                                                        room.amenities.length > 0
                                                        &&
                                                        <Fragment>
                                                            <label htmlFor="amenities"
                                                                   className="col-form-label">Amenities:</label>
                                                            <div className="mb-3">

                                                                {
                                                                    room.amenities.map((amenity) => (
                                                                        <span key={uuidv4()}
                                                                              id="amenities"
                                                                              className="badge rounded-pill text-bg-primary"
                                                                        >{amenity.rAmenity}</span>
                                                                    ))
                                                                }

                                                            </div>
                                                        </Fragment>
                                                    }
                                                </div>

                                            </div>

                                        </div>
                                    </div>
                                </div>
                                :
                                <Loading/>
                        )
                        :
                        <NotFound/>
                }

            </HeaderNav>
        </>
    );
};

ViewRoom.propTypes = {};

export default ViewRoom;