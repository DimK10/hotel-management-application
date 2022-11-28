import React, {Fragment} from 'react';
import PropTypes from 'prop-types';
import SidebarComp from "../layout/Sidebar";
import HeaderNav from "../layout/HeaderNav";
import HotelCard from "./HotelCard";
import CIcon from "@coreui/icons-react";
import {cilPencil, cilTrash} from "@coreui/icons";
import {Tooltip} from '@coreui/coreui/dist/js/coreui';


const Hotel = props => {

  return (
    <Fragment>
      <SidebarComp/>
      <HeaderNav>
        <div className="container">
          <div className='input-group pb-3 mb-4'>
            <input
              type='text'
              className='form-control'
              placeholder='Search for a hotel name'
              aria-label='Search for a hotel name'
              aria-describedby='button-addon2'
            />
            <button className='btn btn-primary px-4' type='button'>
              Search
            </button>
          </div>
          <h4 className="mb-4">List of your Hotels</h4>
          <table className="table table-hover table-responsive">
            <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Name</th>
              <th scope="col">Location</th>
              <th scope="col" className="d-none d-md-table-cell">Number of Rooms</th>
              <th scope="col">Edit/Delete</th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <th scope="row">1</th>
              <td>Hotel#1</td>
              <td>Athens</td>
              <td className="d-none d-md-table-cell">4</td>
              <td className="flex-row">
                <button type="button" className="btn btn-success"
                        style={{color: '#fff', marginRight: '0.3rem'}}
                        data-coreui-toggle="tooltip" data-coreui-placement="top"
                        title="Edit this hotel"
                        onMouseOver={(e) => {
                          Tooltip.getOrCreateInstance(e.target).show()
                        }}
                >
                  <CIcon className="btn-icon" icon={cilPencil}/>
                </button>
                <button type="button" className="btn btn-danger" style={{color: '#fff'}}
                        data-coreui-toggle="tooltip" data-coreui-placement="top"
                        title="Delete this hotel"
                        onMouseOver={(e) => {
                          Tooltip.getOrCreateInstance(e.target).show()
                        }}>
                  <CIcon className="btn-icon" icon={cilTrash}/>
                </button>
              </td>
            </tr>
            <tr>
              <th scope="row">2</th>
              <td>Hotel#2</td>
              <td>Thessaloniki</td>
              <td className="d-none d-md-table-cell">6</td>
              <td className="flex-row">
                <button type="button" className="btn btn-success"
                        style={{color: '#fff', marginRight: '0.3rem'}}
                        data-coreui-toggle="tooltip" data-coreui-placement="top"
                        title="Edit this hotel"
                        onMouseOver={(e) => {
                          Tooltip.getOrCreateInstance(e.target).show()
                        }}
                >
                  <CIcon className="btn-icon" icon={cilPencil}/>
                </button>
                <button type="button" className="btn btn-danger" style={{color: '#fff'}}
                        data-coreui-toggle="tooltip" data-coreui-placement="top"
                        title="Delete this hotel"
                        onMouseOver={(e) => {
                          Tooltip.getOrCreateInstance(e.target).show()
                        }}>
                  <CIcon className="btn-icon" icon={cilTrash}/>
                </button>
              </td>
            </tr>
            <tr>
              <th scope="row">3</th>
              <td>Hotel#3</td>
              <td>Larisa</td>
              <td className="d-none d-md-table-cell">8</td>
              <td className="flex-row">
                <button type="button" className="btn btn-success"
                        style={{color: '#fff', marginRight: '0.3rem'}}
                        data-coreui-toggle="tooltip" data-coreui-placement="top"
                        title="Edit this hotel"
                        onMouseOver={(e) => {
                          Tooltip.getOrCreateInstance(e.target).show()
                        }}
                >
                  <CIcon className="btn-icon" icon={cilPencil}/>
                </button>
                <button type="button" className="btn btn-danger" style={{color: '#fff'}}
                        data-coreui-toggle="tooltip" data-coreui-placement="top"
                        title="Delete this hotel"
                        onMouseOver={(e) => {
                          Tooltip.getOrCreateInstance(e.target).show()
                        }}>
                  <CIcon className="btn-icon" icon={cilTrash}/>
                </button>
              </td>
            </tr>
            </tbody>
          </table>
          <nav aria-label="Page navigation example">
            <ul className="pagination justify-content-center ">
              <li className="page-item disabled">
                <a className="page-link">&laquo;</a>
              </li>
              <li className="page-item active"><a className="page-link" href="#">1</a></li>
              <li className="page-item"><a className="page-link" href="#">2</a></li>
              <li className="page-item"><a className="page-link" href="#">3</a></li>
              <li className="page-item">
                <a className="page-link" href="#">&raquo;</a>
              </li>
            </ul>
          </nav>
        </div>
      </HeaderNav>
    </Fragment>
  );
};

Hotel.propTypes = {};

export default Hotel;
