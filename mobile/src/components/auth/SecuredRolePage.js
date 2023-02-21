import React, {Fragment, useEffect} from 'react';
import PropTypes from 'prop-types';
import {useDispatch, useSelector} from "react-redux";
import {loadUser} from "../../actions/auth";
import {Navigate} from "react-router-dom";

const SecuredRolePage = ({children, userRole}) => {

    const { isAuthenticated }  = useSelector( state => state.auth);
    let role = useSelector(state => state.auth.user?.role);


    if (isAuthenticated === true && role !== 'undefined' && role !== userRole)
        return <Navigate to='/not-found' />

    return (
        <Fragment>
            {children}
        </Fragment>
    );
};

SecuredRolePage.propTypes = {
    children: PropTypes.oneOfType([
        PropTypes.arrayOf(PropTypes.node),
        PropTypes.node
    ]).isRequired,
    userRole: PropTypes.string.isRequired
};

export default SecuredRolePage;