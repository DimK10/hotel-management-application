import React, {Fragment, useEffect, useState} from 'react';
import CIcon from "@coreui/icons-react";
import {cilPencil, cilTrash} from "@coreui/icons";
import {Tooltip} from '@coreui/coreui/dist/js/coreui';
import Pagination from "../../layout/Pagination";
import {useDispatch, useSelector} from "react-redux";
import {getAllHotelsByPage} from "../../../actions/hotel";
import {useNavigate} from "react-router-dom";
import {v4 as uuidv4} from "uuid";


function HotelTable() {

  const dispatch = useDispatch();

  const navigate = useNavigate();

  const [pageSize, setPageSize] = useState(10);

  const [pages, setPages] = useState(1);

  const [currentPage, setCurrentPage] = useState(1);

  const {user, loading} = useSelector(state => state.auth);

  const {loading: hotelsLoading, count, hotels} = useSelector(state => state.hotel);

  useEffect(() => {
    setPages(Math.floor(count / 10));
  }, [count]);

  useEffect(() => {
    dispatch(getAllHotelsByPage(currentPage - 1, pageSize, 'id', user?.id));
  }, [pages]);


  const handleSelectChange = (e) => {
    setCurrentPage(1);
    setPageSize(e.target.value);
    setPages(Math.floor((count / e.target.value) + (count % e.target.value > 0 ? 1 : 0)));
  }

  const changePage = (e) => {
    e.preventDefault();

    if (!loading) {

      setCurrentPage(parseInt(e.target.textContent));
      let selectedPage = e.target.textContent - 1;
      dispatch(getAllHotelsByPage(selectedPage, pageSize, 'id', user?.id));
    }
  }


  const moveToNextPage = (e) => {
    e.preventDefault();

    if (!loading) {

      setCurrentPage(prevState => prevState + 1);
      dispatch(getAllHotelsByPage(currentPage, pageSize, 'id', user?.id));
    }
  }

  const moveToPreviousPage = (e) => {
    e.preventDefault();

    if (!loading) {

      // This will be used to set the page as zero indexed number (due to how pagination is configured in backend)
      let page = currentPage - 2;
      setCurrentPage(prevState => prevState - 1);
      dispatch( getAllHotelsByPage(page, pageSize, 'id', user?.id));
    }
  }

  const onRowClick = (hotel) => {
    navigate(`/hotels/${hotel.id}`);
  }

  return (
    <Fragment>
      {/* TODO ADD LOGIC FOR SEARCH BAR IF THERE IS TIME */}
      {/* Search Bar */}
      {/*<div className="row">*/}
      {/*  <div className='input-group pb-3 mb-4'>*/}
      {/*    <input*/}
      {/*      type='text'*/}
      {/*      className='form-control'*/}
      {/*      placeholder='Search for a hotel name'*/}
      {/*      aria-label='Search for a hotel name'*/}
      {/*      aria-describedby='button-addon2'*/}
      {/*    />*/}
      {/*    <button className='btn btn-primary px-4' type='button'>*/}
      {/*      Search*/}
      {/*    </button>*/}
      {/*  </div>*/}
      {/*</div>*/}
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
            <th scope="col">Edit</th>
          </tr>
          </thead>
          <tbody>
          {

            hotels.map((hotel) => (
              <Fragment key={uuidv4()}>
                <tr style={{ cursor: 'pointer' }} onClick={e => onRowClick(hotel)}>
                  <th
                    scope="row">{pageSize * (currentPage - 1) + hotels.indexOf(hotel) + 1}</th>
                  <td>{hotel.name}</td>
                  <td>{hotel.areaName}</td>
                  <td className="d-none d-md-table-cell">{hotel.rooms.length}</td>
                  <td className="flex-row">
                    <button type="button" className="btn btn-success"
                            style={{color: '#fff', marginRight: '0.3rem'}}
                            // data-coreui-toggle="tooltip" data-coreui-placement="top"
                            // title="Edit this hotel"
                            // onMouseOver={(e) => {
                            //   Tooltip.getOrCreateInstance(e.target).show()
                            // }}
                    >
                      <CIcon className="btn-icon" icon={cilPencil}/>
                    </button>
                    {/*<button type="button" className="btn btn-danger"*/}
                    {/*        style={{color: '#fff'}}*/}
                    {/*        // data-coreui-toggle="tooltip" data-coreui-placement="top"*/}
                    {/*        // title="Delete this hotel"*/}
                    {/*        // onMouseOver={(e) => {*/}
                    {/*        //   Tooltip.getOrCreateInstance(e.target).show()*/}
                    {/*        // }}*/}
                    {/*>*/}
                    {/*  <CIcon className="btn-icon" icon={cilTrash}/>*/}
                    {/*</button>*/}
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

export default HotelTable;