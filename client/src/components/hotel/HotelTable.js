import React, {Fragment, useState} from 'react';
import CIcon from "@coreui/icons-react";
import {cilPencil, cilTrash} from "@coreui/icons";
import {Tooltip} from '@coreui/coreui/dist/js/coreui';
import Pagination from "../layout/Pagination";


function HotelTable({count, hotels}) {

  const [pages, setPages] = useState(1);

  const handleSelectChange = (e) => {
    setPages(count / e.target.value );
  }

  return (
    <Fragment>
      <div className="row">
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
      </div>
      <div className="row">
        <div className="col">
          <h4 className="mb-4 mr-auto">List of your Hotels</h4>
        </div>
        <div className="col-auto">
          <label htmlFor="rows-select" style={{marginRight: ".5rem"}}>Number of
            records:</label>
          <select
            className="custom-select" id="rows-select" onChange={(e) => handleSelectChange(e)}>
            <option value="10" defaultValue={true}>10</option>
            <option value="20">20</option>
            <option value="30">30</option>
          </select>
        </div>
      </div>

      <div className="row">
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
          {

            hotels.map((hotel) => (
              <Fragment>
                <tr>
                  <th scope="row">{hotels.indexOf(hotel) + 1}</th>
                  <td>{hotel.name}</td>
                  <td>{hotel.areaName}</td>
                  <td className="d-none d-md-table-cell">{hotel.rooms.length}</td>
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
                    <button type="button" className="btn btn-danger"
                            style={{color: '#fff'}}
                            data-coreui-toggle="tooltip" data-coreui-placement="top"
                            title="Delete this hotel"
                            onMouseOver={(e) => {
                              Tooltip.getOrCreateInstance(e.target).show()
                            }}>
                      <CIcon className="btn-icon" icon={cilTrash}/>
                    </button>
                  </td>
                </tr>
              </Fragment>
            ))
          }
          </tbody>
        </table>
        <Pagination pages={pages} />
      </div>
    </Fragment>
  );
}

HotelTable.propTypes = {};

export default HotelTable;