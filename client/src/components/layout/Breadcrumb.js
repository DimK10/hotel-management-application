import React, {Fragment, useState} from 'react';
import PropTypes from 'prop-types';
import {Link} from "react-router-dom";


const Breadcrumb = props => {

    const [pathname] = useState(window.location.pathname.slice(1));

    return (
        <Fragment>
            <div className="container-fluid">
                <nav aria-label="breadcrumb">
                    <ol className="breadcrumb my-0 ms-2">
                        <li className="breadcrumb-item">
                            <Link to='/dashboard'>
                                <span>Dashboard</span>
                            </Link>

                        </li>
                        {pathname !== 'dashboard' && pathname.split('/').length > 1 &&
                            <li className="breadcrumb-item">
                                <Link to={`/${pathname.split('/')[0]}`}>
                                    <span>{pathname.split('/')[0]}</span>
                                </Link>
                                <span>{' / ' + pathname.split('/')[1]}</span>
                            </li>
                        }

                        {pathname !== 'dashboard' && pathname.split('/').length === 1 &&
                            <li className="breadcrumb-item">
                                <span>{pathname.split('/')[0]}</span>
                            </li>
                        }
                    </ol>
                </nav>
            </div>
        </Fragment>
    );
};

Breadcrumb.propTypes = {};

export default Breadcrumb;
