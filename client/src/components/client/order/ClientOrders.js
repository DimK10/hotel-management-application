import React from 'react';
import PropTypes from 'prop-types';
import NavBar from '../../layout/NavBar';
import OrderTable from './OrderTable';
import { useDispatch } from 'react-redux';
import { useSelector } from 'react-redux';
import { useEffect } from 'react';
import { getAllOrdersForClientAction } from '../../../actions/order';

const ClientOrders = (props) => {
  const dispatch = useDispatch();

  const { orders } = useSelector((state) => state.order);

  useEffect(() => {
    dispatch(getAllOrdersForClientAction());
  }, []);

  return (
    <>
      <NavBar />
      <div className='container' style={{ marginTop: '3rem' }}>
        <OrderTable orders={orders} />
      </div>
    </>
  );
};

ClientOrders.propTypes = {};

export default ClientOrders;
