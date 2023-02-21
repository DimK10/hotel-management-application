import React, {Fragment} from 'react';
import CIcon from '@coreui/icons-react';
import {cilPlus} from '@coreui/icons';
import {Tooltip} from '@coreui/coreui/dist/js/coreui';
import moment from 'moment';
import {PropTypes} from 'prop-types';
import {useNavigate} from "react-router-dom";

const OrderTable = ({orders}) => {

    const navigate = useNavigate();

    const onRowClick = (order) => {
        navigate(`/my-orders/${order.id}`);
    }

    return (
        <Fragment>

            <div className="container">
                <div className='row mt-5'>
                    <div className='col'>
                        <h4 className='mb-4 mr-auto'>List of orders made</h4>
                    </div>
                </div>

                <div className='row'>
                    <table className='table table-hover table-responsive'>
                        <thead>
                        <tr>
                            <th scope='col'>#</th>
                            <th scope='col'>Check In Date</th>
                            <th scope='col'>Check Out Date</th>
                            <th scope='col' className='d-none d-md-table-cell'>
                                Status
                            </th>
                            <th scope='col' className='d-none d-md-table-cell'>
                                Room
                            </th>
                            <th scope='col'>More</th>
                        </tr>
                        </thead>
                        <tbody>
                        {orders.map((order) => (
                            <tr style={{cursor: 'pointer'}} onClick={e => onRowClick(order)}>

                                <th scope='row'>{orders.indexOf(order) + 1}</th>
                                <td>{moment(order.checkInDate).format('DD/MM/YY')}</td>
                                <td>{moment(order.checkOutDate).format('DD/MM/YY')}</td>
                                <td className='d-none d-md-table-cell'>{order.canceled !== false ? 'Disabled' : 'Active'}</td>
                                <td className='d-none d-md-table-cell'>{order.room}</td>
                                <td className='flex-row'>
                                    <button
                                        type='button'
                                        className='btn btn-warning'
                                        style={{color: '#fff', marginRight: '0.3rem'}}
                                        data-coreui-toggle='tooltip'
                                        data-coreui-placement='top'
                                        title='Show more details'
                                        onMouseOver={(e) => {
                                            Tooltip.getOrCreateInstance(e.target).show();
                                        }}
                                    >
                                        <CIcon className='btn-icon' icon={cilPlus}/>
                                    </button>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </Fragment>
    );
};

OrderTable.propTypes = {
    orders: PropTypes.array.isRequired
};

export default OrderTable;
