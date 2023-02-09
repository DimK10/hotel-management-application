import React, {Fragment, useEffect, useState} from 'react';
import SidebarComp from "../../layout/Sidebar";
import HeaderNav from "../../layout/HeaderNav";
import CIcon from "@coreui/icons-react";
import {cilPencil, cilTrash} from "@coreui/icons";
import {Tooltip} from '@coreui/coreui/dist/js/coreui';
import {MultiSelect} from "react-multi-select-component";
import Alert from "../../layout/Alert";
import {useDispatch, useSelector} from "react-redux";
import {getAllHotelsAction, getCountOfHotelsAction} from "../../../actions/hotel";
import Select from "react-select";
import {fetchAllRoomsFromSelectedHotel, getCountOfRoomsAction} from "../../../actions/room";
import {v4 as uuidv4} from 'uuid';


const Rooms = props => {

    const [hotelsToSelect, setHotelsToSelect] = useState([]);

    const [hotelSelected, setHotelSelected] = useState(null);

    const dispatch = useDispatch();

    const {hotels} = useSelector(state => state.hotel);

    const {count, rooms} = useSelector(state => state.room);

    const {user} = useSelector(state => state.auth);

    useEffect(() => {
        // Get all hotels
        dispatch(getAllHotelsAction());
    }, []);

    useEffect(() => {
        let hotelsToSelect = [...hotels.map(hotel => ({label: hotel.name, value: hotel.id}))]

        setHotelsToSelect(hotelsToSelect);
    }, [hotels]);

    useEffect(() => {
        if (hotelSelected !== null && hotelSelected !== 'undefined' && user !== null)
            dispatch(getCountOfRoomsAction(hotelSelected.value, user.id));
            // dispatch(fetchAllRoomsFromSelectedHotel(hotelSelected.value));
    }, [hotelSelected]);

    useEffect(() => {
        if (hotelSelected !== null && hotelSelected !== 'undefined')
            dispatch(fetchAllRoomsFromSelectedHotel(0, 10, 'id', hotelSelected.value));
    }, [count])


    return (
        <Fragment>
            <SidebarComp/>
            <HeaderNav>
                <Alert/>
                {/* Search Hotel Bar */}
                <Select
                    className="basic-single mb-5"
                    classNamePrefix="select a hotel"
                    options={hotelsToSelect}
                    value={hotelSelected}
                    onChange={setHotelSelected}
                    labelledBy="Select"
                />
                {

                    rooms.length > 0
                        ?
                        (
                            <Fragment key={uuidv4()}>
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
                                            <th scope="col">Edit</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        {
                                            rooms.map((room, index) => (
                                                <tr key={uuidv4()}>
                                                    <th scope="row">{index + 1}</th>
                                                    <td>{room.name}</td>
                                                    <td>{room.luxurity}</td>
                                                    <td className="d-none d-md-table-cell">{room.hotel.name}</td>
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
                                                    </td>
                                                </tr>
                                            ))
                                        }
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
                            </Fragment>
                        )
                        :
                        (
                            <h4>No rooms found.</h4>
                        )

                }

            </HeaderNav>
        </Fragment>
    );
};

Rooms.propTypes = {};

export default Rooms;
