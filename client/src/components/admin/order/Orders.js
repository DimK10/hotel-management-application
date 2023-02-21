import React, {Fragment, useEffect, useState} from 'react';
import SidebarComp from "../../layout/Sidebar";
import HeaderNav from "../../layout/HeaderNav";
import {useDispatch, useSelector} from "react-redux";
import {findOrdersByFirstnameLastNameAction, getAllOrdersForAdminAction} from "../../../actions/order";
import OrderTable from "./OrderTable";
import Loading from "../../layout/Loading";
import {v4 as uuidv4} from 'uuid';


const Orders = props => {

    const dispatch = useDispatch();

    const {orders, loading} = useSelector(state => state.order);

    const [formData, setFormData] = useState({
        firstname: '',
        lastname: ''
    });

    const {
        firstname,
        lastname
    } = formData;

    const onChange = (e) =>
        setFormData({...formData, [e.target.name]: e.target.value});

    const onSubmit = (e) => dispatch(findOrdersByFirstnameLastNameAction(formData, 0, 999));

    useEffect(() => {
        dispatch(getAllOrdersForAdminAction(0, 999))
    }, [])

    return (
        <Fragment>
            <SidebarComp/>
            <HeaderNav>
                {
                    loading
                        ?
                        <Loading key={uuidv4()}/>
                        :
                        (
                            orders.length > 0
                                ?
                                (
                                    <Fragment>
                                        <div className="row">
                                            <div className="col">
                                                <h4 className="mb-4 mr-auto">List of orders made</h4>
                                            </div>
                                        </div>

                                        <form onSubmit={(e) => onSubmit(e)}>
                                            <div className="row">
                                                <div className="col-sm-4 col-md-4 col-lg-5">
                                                    <input
                                                        type='text'
                                                        className='form-control'
                                                        placeholder='Search by Firstname'
                                                        name='firstname'
                                                        value={firstname}
                                                        onChange={(e) => onChange(e)}
                                                        aria-label='Search by Firstname'
                                                        aria-describedby='button-addon2'
                                                    />
                                                </div>
                                                <div className="col-md-4 col-sm-4 col-lg-5">
                                                    <input
                                                        type='text'
                                                        className='form-control'
                                                        name='lastname'
                                                        value={lastname}
                                                        onChange={(e) => onChange(e)}
                                                        placeholder='Search by Lastname'
                                                        aria-label='Search by Lastname'
                                                        aria-describedby='button-addon2'
                                                    />

                                                </div>
                                                <div className="col-sm-4 col-md-4 col-lg-2">
                                                    <button className='btn btn-primary px-4' style={{float: 'right'}}
                                                            type='button'>
                                                        Search
                                                    </button>
                                                </div>

                                            </div>
                                        </form>

                                        <OrderTable orders={orders}/>
                                    </Fragment>
                                )
                                :
                                <h2>No orders found</h2>
                        )
                }
            </HeaderNav>
        </Fragment>
    );
};

Orders.propTypes = {};

export default Orders;
