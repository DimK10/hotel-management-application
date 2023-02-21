import React, { Fragment } from 'react';
import SidebarComp from '../../layout/Sidebar';
import HeaderNav from '../../layout/HeaderNav';
import CIcon from '@coreui/icons-react';
import { cilPlus } from '@coreui/icons';
import { Tooltip } from '@coreui/coreui/dist/js/coreui';
import moment from 'moment';
import DateRangePicker from 'react-bootstrap-daterangepicker';
import { v4 as uuidv4 } from 'uuid';
import { PropTypes } from 'prop-types';

const OrderTable = ({orders}) => {
  return (
    <Fragment>
      {/* <div className='row'>
          <div className='input-group pb-3 mb-4'>
            <input
              type='text'
              className='form-control'
              placeholder='Search by order number'
              aria-label='Search by order number'
              aria-describedby='button-addon2'
            />
            <button className='btn btn-primary px-4' type='button'>
              Search
            </button>
          </div>
        </div> */}
      <div className='row'>
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
              <tr key={uuidv4()}>
                <th scope='row'>{orders.indexOf(order) + 1}</th>
                <td>{moment(order.checkInDate).format('DD/MM/YY')}</td>
                <td>{moment(order.checkOutDate).format('DD/MM/YY')}</td>
					<td className='d-none d-md-table-cell'>{ order.canceled !== false ? 'Disabled' : 'Active'}</td>
					<td className='d-none d-md-table-cell'>{order.room}</td>
                <td className='flex-row'>
                  <button
                    type='button'
                    className='btn btn-warning'
                    style={{ color: '#fff', marginRight: '0.3rem' }}
                    data-coreui-toggle='tooltip'
                    data-coreui-placement='top'
                    title='Show more details'
                    onMouseOver={(e) => {
                      Tooltip.getOrCreateInstance(e.target).show();
                    }}
                  >
                    <CIcon className='btn-icon' icon={cilPlus} />
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        {/* todo add navigation */}
        {/* <nav aria-label='Page navigation example'>
            <ul className='pagination justify-content-center '>
              <li className='page-item disabled'>
                <a className='page-link'>&laquo;</a>
              </li>
              <li className='page-item active'>
                <a className='page-link' href='../../client/order#'>
                  1
                </a>
              </li>
              <li className='page-item'>
                <a className='page-link' href='../../client/order#'>
                  2
                </a>
              </li>
              <li className='page-item'>
                <a className='page-link' href='../../client/order#'>
                  3
                </a>
              </li>
              <li className='page-item'>
                <a className='page-link' href='../../client/order#'>
                  &raquo;
                </a>
              </li>
            </ul>
          </nav> */}
      </div>
    </Fragment>
  );
};

OrderTable.propTypes = {
	orders: PropTypes.array.isRequired
};

export default OrderTable;
