import React, {Fragment, useEffect, useReducer, useState} from 'react';
import CIcon from "@coreui/icons-react";
import {cilPencil, cilTrash} from "@coreui/icons";
import {Tooltip} from '@coreui/coreui/dist/js/coreui';
import Pagination from "../layout/Pagination";
import PropTypes from "prop-types";
import {connect} from "react-redux";
import {getAllHotelsByPage} from "../../actions/hotel";


function HotelTable({auth, hotelState, getAllHotelsByPage}) {

  const [pageSize, setPageSize] = useState(10);

  const [pages, setPages] = useState(1);

  const [currentPage, setCurrentPage] = useState(1);

  const {user, loading} = auth;

  const {loading: hotelsLoading, count, hotels} = hotelState;

  useEffect(() => {
    setPages(count / 10);
  }, [count]);

  useEffect(() => {
    getAllHotelsByPage(currentPage - 1, pageSize, 'id', user?.id)
  }, [pages]);


  const handleSelectChange = (e) => {
    setCurrentPage(1);
    setPageSize(e.target.value);
    setPages((count / e.target.value) + (count % e.target.value > 0 ? 1 : 0));
  }

  const changePage = (e) => {
    e.preventDefault();

    if (!loading) {

      console.log(e.target.textContent);
      setCurrentPage(parseInt(e.target.textContent));
      let selectedPage = e.target.textContent - 1;

      getAllHotelsByPage(selectedPage, pageSize, 'id', user?.id)
    }
  }


  const moveToNextPage = (e) => {
    e.preventDefault();

    if (!loading) {

      setCurrentPage(currentPage + 1);
      let selectedPage = currentPage - 1;

      getAllHotelsByPage(selectedPage, pageSize, 'id', user?.id)
    }
  }

  const moveToPreviousPage = (e) => {
    e.preventDefault();

    if (!loading) {

      setCurrentPage(currentPage - 1);
      let selectedPage = currentPage - 1;

      getAllHotelsByPage(selectedPage, pageSize, 'id', user?.id)
    }
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
            className="custom-select" id="rows-select"
            onChange={(e) => handleSelectChange(e)}>
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
                  <th
                    scope="row">{pageSize * (currentPage - 1) + hotels.indexOf(hotel) + 1}</th>
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
        <Pagination pages={pages} changePage={changePage} moveToNextPage={moveToNextPage}
                    moveToPreviousPage={moveToPreviousPage} currentPage={currentPage}/>
      </div>
    </Fragment>
  );
}

HotelTable.propTypes = {
  auth: PropTypes.object.isRequired,
  hotelState: PropTypes.object.isRequired,
  getAllHotelsByPage: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
  auth: state.auth,
  hotelState: state.hotel
})

export default connect(mapStateToProps, {getAllHotelsByPage})(HotelTable);