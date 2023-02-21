import React, {useEffect} from 'react';
import NavBar from '../../layout/NavBar';
import OrderTable from './OrderTable';
import {useDispatch, useSelector} from 'react-redux';
import {getAllOrdersForClientAction} from '../../../actions/order';

const ClientOrders = (props) => {
    const dispatch = useDispatch();

    const {orders} = useSelector((state) => state.order);

    useEffect(() => {
        dispatch(getAllOrdersForClientAction());
    }, []);

    return (
        <>
            <NavBar/>
            <OrderTable orders={orders}/>
        </>
    );
};

ClientOrders.propTypes = {};

export default ClientOrders;
