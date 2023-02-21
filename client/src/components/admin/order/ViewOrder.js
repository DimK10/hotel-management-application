import React, {useEffect} from 'react';
import SidebarComp from "../../layout/Sidebar";
import HeaderNav from "../../layout/HeaderNav";
import Alert from "../../layout/Alert";
import {useNavigate, useParams} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {loadUser} from "../../../actions/auth";
import {getOrderByIdAction} from "../../../actions/order";
import NotFound from "../../error/NotFound";
import Loading from "../../layout/Loading";
import moment from "moment/moment";

const ViewOrder = props => {

    const {orderId} = useParams();

    const dispatch = useDispatch();

    const {orderToShow, loading} = useSelector(state => state.order);

    const {user} = useSelector(state => state.auth);

    const navigate = useNavigate();

    const {
        id,
        checkInDate,
        checkOutDate,
        status,
        roomName,
        hotelName,
        clientname,
        price,
    } = orderToShow;

    useEffect(() => {
        if (user === null) {
            dispatch(loadUser);
        }

        dispatch(getOrderByIdAction(orderId));
    }, [orderId])


    return (
        <>
            <SidebarComp />
                <HeaderNav>
                <Alert/>
                {
                    orderId !== null
                        ?
                        (
                            user !== null
                                ?
                                <div className="row">
                                    <div className="card">
                                        <div className="card-body">
                                            <h3 className="card-title d-flex justify-content-between">
                                                Order #{id}
                                            </h3>
                                            <div className="mb-3 row">
                                                <div className="col" style={{margin: 'auto 0'}}>
                                                    <div className="mb-3 row">
                                                        <label htmlFor="checkInDate"
                                                               className="col-sm-5 col-form-label">CheckInDate:</label>
                                                        <div className="col-sm-7">
                                                            <p id='checkInDate'
                                                               className="form-control-plaintext">{moment(checkInDate).format('DD/MM/YYYY')}</p>
                                                        </div>
                                                    </div>

                                                </div>
                                                <div className="mb-3 row">
                                                    <label htmlFor="checkOutDate"
                                                           className="col-sm-5 col-form-label">checkOutDate:</label>
                                                    <div className="col-sm-7">
                                                        <p id='checkOutDate'
                                                           className="form-control-plaintext">{moment(checkOutDate).format('DD/MM/YYYY')}</p>
                                                    </div>
                                                </div>
                                                <div className="mb-3 row">
                                                    <label htmlFor="status"
                                                           className="col-sm-5 col-form-label">Status:</label>
                                                    <div className="col-sm-7">
                                                        <p id='checkOutDate'
                                                           className="form-control-plaintext">{status}</p>
                                                    </div>
                                                </div>
                                                <div className="mb-3 row">
                                                    <label htmlFor="clientName"
                                                           className="col-sm-5 col-form-label">Client:</label>
                                                    <div className="col-sm-7">
                                                        <p id='clientName'
                                                           className="form-control-plaintext">{clientname}</p>
                                                    </div>
                                                </div>
                                                <div className="mb-3 row">
                                                    <label htmlFor="price"
                                                           className="col-sm-5 col-form-label">Price:</label>
                                                    <div className="col-sm-7">
                                                        <p id='clientName'
                                                           className="form-control-plaintext">{price}</p>
                                                    </div>
                                                </div>
                                                <div className="mb-3 row">
                                                    <label htmlFor="roomName"
                                                           className="col-sm-5 col-form-label">room name:</label>
                                                    <div className="col-sm-7">
                                                        <p id='roomName'
                                                           className="form-control-plaintext">{roomName}</p>
                                                    </div>
                                                </div>
                                                <div className="mb-3 row">
                                                    <label htmlFor="hotelName"
                                                           className="col-sm-5 col-form-label">hotel name:</label>
                                                    <div className="col-sm-7">
                                                        <p id='hotelName'
                                                           className="form-control-plaintext">{hotelName}</p>
                                                    </div>
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

ViewOrder.propTypes = {};

export default ViewOrder;