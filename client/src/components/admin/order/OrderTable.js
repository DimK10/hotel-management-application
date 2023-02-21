import React, {Fragment, useState} from 'react';
import PropTypes from 'prop-types';
import moment from "moment/moment";
import CIcon from "@coreui/icons-react";
import {cilPlus} from "@coreui/icons";
import {v4 as uuidv4} from "uuid";
import {useNavigate} from "react-router-dom";

const OrderTable = ({ orders }) => {

    const navigate = useNavigate();
    const [pageSize, setPageSize] = useState(10);

    const [pages, setPages] = useState(1);

    const [currentPage, setCurrentPage] = useState(1);

    const onRowClick = (order) => {
        navigate(`/orders/${order.id}`);
    }

    return (
        <>
            <div className="row">
                <table className="table table-hover table-responsive">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Check In Date</th>
                        <th scope="col">Check Out Date</th>
                        <th scope="col" className="d-none d-md-table-cell">Status</th>
                        <th scope="col" className="d-none d-md-table-cell">Room</th>
                        <th scope="col" className="d-none d-md-table-cell">Hotel</th>
                        <th scope="col" className="d-none d-md-table-cell">Client</th>
                        <th scope="col">More</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        orders.map(order => (
                            <Fragment key={uuidv4()}>
                                <tr style={{ cursor: 'pointer' }} onClick={e => onRowClick(order)}>
                                    <th
                                        scope="row">{orders.indexOf(order) + 1}</th>
                                    <td>{moment(order.checkInDate).format('DD/MM/YY')}</td>
                                    <td>{moment(order.checkOutDate).format('DD/MM/YY')}</td>
                                    <td className="d-none d-md-table-cell">{order.canceled === true ? 'Canceled' : 'Active'}</td>
                                    <td className="d-none d-md-table-cell">{order.roomName}</td>
                                    <td className="d-none d-md-table-cell">{order.hotelName}</td>
                                    <td className="d-none d-md-table-cell">{order.clientname}</td>
                                    <td className="flex-row">
                                        <button type="button" className="btn btn-warning"
                                                style={{color: '#fff', marginRight: '0.3rem'}}
                                        >
                                            <CIcon className="btn-icon" icon={cilPlus}/>
                                        </button>
                                    </td>
                                </tr>
                            </Fragment>
                        ))
                    }
                    </tbody>
                </table>
                {/*<nav aria-label="Page navigation example">*/}
                {/*    <ul className="pagination justify-content-center ">*/}
                {/*        <li className="page-item disabled">*/}
                {/*            <a className="page-link">&laquo;</a>*/}
                {/*        </li>*/}
                {/*        <li className="page-item active"><a className="page-link" href="../../client/order#">1</a>*/}
                {/*        </li>*/}
                {/*        <li className="page-item"><a className="page-link" href="../../client/order#">2</a></li>*/}
                {/*        <li className="page-item"><a className="page-link" href="../../client/order#">3</a></li>*/}
                {/*        <li className="page-item">*/}
                {/*            <a className="page-link" href="../../client/order#">&raquo;</a>*/}
                {/*        </li>*/}
                {/*    </ul>*/}
                {/*</nav>*/}
            </div>
        </>
    );
};

OrderTable.propTypes = {
    orders: PropTypes.array.isRequired
};

export default OrderTable;