import React, {Fragment, useEffect, useState} from 'react';
import SidebarComp from "../../layout/Sidebar";
import HeaderNav from "../../layout/HeaderNav";
import CIcon from "@coreui/icons-react";
import {cilPencil, cilTrash} from "@coreui/icons";
import {Tooltip} from '@coreui/coreui/dist/js/coreui';
import {MultiSelect} from "react-multi-select-component";
import Alert from "../../layout/Alert";
import {useDispatch, useSelector} from "react-redux";
import {getAllHotelsAction} from "../../../actions/hotel";
import Select from "react-select";
import {fetchAllRoomsFromSelectedHotel} from "../../../actions/room";


const Rooms = props => {

    const [hotelsToSelect, setHotelsToSelect] = useState([]);
    const [hotelSelected, setHotelSelected] = useState(null);

    const dispatch = useDispatch();

    const {hotels} = useSelector(state => state.hotel);

    useEffect(() => {
        // Get all hotels
        dispatch(getAllHotelsAction());
    }, []);

    useEffect(() => {
        console.log(hotels);

        let hotelsToSelect = [...hotels.map(hotel => ({ label: hotel.name, value: hotel.id }))]

        setHotelsToSelect(hotelsToSelect);
    }, [hotels]);

    useEffect(() => {
        if (hotelSelected !== null && hotelSelected !== 'undefined')
            dispatch(fetchAllRoomsFromSelectedHotel(hotelSelected.value));
    }, [hotelSelected]);


    return (
        <Fragment>
            <SidebarComp/>
            <HeaderNav>
                <Alert/>
                {/* Search Hotel Bar */}
                <Select
                    className="basic-single"
                    classNamePrefix="select a hotel"
                    options={hotelsToSelect}
                    value={hotelSelected}
                    onChange={setHotelSelected}
                    labelledBy="Select"
                />
                <div className="row">
                    <div className="col">
                        <h4 className="mb-4 mr-auto">List of your Rooms</h4>
                    </div>
                    <div className="col-auto">
                        <label htmlFor="rows-select" style={{marginRight: ".5rem"}}>Number of
                            records:</label>
                        <select
                            className="custom-select" id="rows-select">
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
                            <th scope="col">Luxurity</th>
                            <th scope="col" className="d-none d-md-table-cell">Hotel</th>
                            <th scope="col">Edit/Delete</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th scope="row">1</th>
                            <td>Room#1</td>
                            <td>3</td>
                            <td className="d-none d-md-table-cell">Hotel#1</td>
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
                        <tr>
                            <th scope="row">2</th>
                            <td>Room#2</td>
                            <td>4</td>
                            <td className="d-none d-md-table-cell">Hotel#1</td>
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
                        <tr>
                            <th scope="row">3</th>
                            <td>Room#3</td>
                            <td>5</td>
                            <td className="d-none d-md-table-cell">Hotel#2</td>
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
                        </tbody>
                    </table>
                    <nav aria-label="Page navigation example">
                        <ul className="pagination justify-content-center ">
                            <li className="page-item disabled">
                                <a className="page-link">&laquo;</a>
                            </li>
                            <li className="page-item active"><a className="page-link" href="#">1</a>
                            </li>
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

Rooms.propTypes = {};

export default Rooms;
