import React, {Fragment} from 'react';
import {Link} from 'react-router-dom';
import {useSelector} from "react-redux";

const NavBar = (props) => {

    const {user, isAuthenticated} = useSelector(state => state.auth);

    return (
        <nav className='navbar navbar-expand-lg navbar-dark bg-dark sticky-top'>
            <div className='container-fluid'>
                <Link className='navbar-brand' to='/'>
                    Hotel Management App
                </Link>
                <button
                    className='navbar-toggler'
                    type='button'
                    data-coreui-toggle='collapse'
                    data-coreui-target='#navbarNav'
                    aria-controls='navbarNav'
                    aria-expanded='false'
                    aria-label='Toggle navigation'
                >
                    <span className='navbar-toggler-icon'></span>
                </button>
                <div className='collapse navbar-collapse' id='navbarNav'>
                    <ul className='navbar-nav' style={{marginLeft: 'auto'}}>
                        <li className='nav-item'>
                            <Link
                                className={`nav-link ${
                                    window.location.pathname === '/' ? 'active' : ''
                                }`}
                                to='/'
                            >
                                Home
                            </Link>
                        </li>
                        {
                            /* Not authenticated links */
                            !isAuthenticated
                            &&
                            user === null
                            &&
                            <Fragment>
                                <li className='nav-item'>
                                    <Link
                                        className={`nav-link ${
                                            window.location.pathname === '/sign-in' ? 'active' : ''
                                        }`}
                                        to='/sign-in'
                                    >
                                        Sign In
                                    </Link>
                                </li>
                                <li className='nav-item'>
                                    <Link
                                        className={`nav-link ${
                                            window.location.pathname === '/sign-up' ? 'active' : ''
                                        }`}
                                        to='/sign-up'
                                    >
                                        Sign Up
                                    </Link>
                                </li>
                            </Fragment>
                        }
                        {
                            /* Authenticated links for client */
                            isAuthenticated
                            &&
                            user !== null
                            &&
                            <Fragment>
                                <li className='nav-item'>
                                    <Link
                                        className={`nav-link ${
                                            window.location.pathname === '/my-orders' ? 'active' : ''
                                        }`}
                                        to='/my-orders'
                                    >
                                        My orders
                                    </Link>
                                </li>
                                <li className='nav-item'>
                                    <Link
                                        className={`nav-link ${
                                            window.location.pathname === '/logout' ? 'active' : ''
                                        }`}
                                        to='/logout'
                                    >
                                        Logout
                                    </Link>
                                </li>
                            </Fragment>
                        }

                    </ul>
                </div>
            </div>
        </nav>
    );
};

NavBar.propTypes = {};

export default NavBar;
