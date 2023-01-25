import React from 'react';
import PropTypes from 'prop-types';
import NavBar from "../../layout/NavBar";

const ClientOrders = props => {
    return (
        <>
            <NavBar/>
            <div className='container' style={{marginTop: '3rem'}}>
            Client orders page
            </div>
        </>
    );
};

ClientOrders.propTypes = {

};

export default ClientOrders;