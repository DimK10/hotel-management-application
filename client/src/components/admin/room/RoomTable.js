import React, {Fragment, useEffect, useState} from 'react';
import {useDispatch, useSelector} from "react-redux";
import {useNavigate} from "react-router-dom";
import {v4 as uuidv4} from "uuid";
import CIcon from "@coreui/icons-react";
import {cilPencil} from "@coreui/icons";
import Pagination from "../../layout/Pagination";
import {getAllRoomsByPage} from "../../../actions/room";
import {Tooltip} from '@coreui/coreui/dist/js/coreui';
import PropTypes from "prop-types";

const RoomTable = ({ hotelSelected }) => {

    const dispatch = useDispatch();

    const navigate = useNavigate();

    const [pageSize, setPageSize] = useState(10);

    const [pages, setPages] = useState(1);

    const [currentPage, setCurrentPage] = useState(1);

    const {user, loading} = useSelector(state => state.auth);

    const {loading: roomsLoading, count, rooms} = useSelector(state => state.room);

    useEffect(() => {
        setPages(Math.floor(count / 10));
    }, [count]);

    useEffect(() => {
        dispatch(getAllRoomsByPage(currentPage - 1, pageSize, 'id', hotelSelected.value));
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
            dispatch(getAllRoomsByPage(selectedPage, pageSize, 'id', user?.id));
        }
    }


    const moveToNextPage = (e) => {
        e.preventDefault();

        if (!loading) {

            setCurrentPage(prevState => prevState + 1);
            dispatch(getAllRoomsByPage(currentPage, pageSize, 'id', user?.id));
        }
    }

    const moveToPreviousPage = (e) => {
        e.preventDefault();

        if (!loading) {

            // This will be used to set the page as zero indexed number (due to how pagination is configured in backend)
            let page = currentPage - 2;
            setCurrentPage(prevState => prevState - 1);
            dispatch( getAllRoomsByPage(page, pageSize, 'id', user?.id));
        }
    }

    const onRowClick = (room) => {
        navigate(`/rooms/${room.id}`);
    }

    return (
        <Fragment key={uuidv4()}>
            <div className="row">
                <div className="col">
                    <h4 className="mb-4 mr-auto">List of your Rooms</h4>
                </div>
                <div className="col-auto">
                    <label htmlFor="rows-select" style={{marginRight: ".5rem"}}>Number of
                        records:</label>
                    <select
                        className="custom-select" id="rows-select"
                        onChange={(e) => handleSelectChange(e)} value={pageSize}>
                        <option value="10">10</option>
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
                        <th scope="col" className="d-none d-md-table-cell">Capacity</th>
                        <th scope="col" className="d-none d-md-table-cell">Price</th>
                        <th scope="col">Edit</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        rooms.map((room, index) => (
                            <tr key={uuidv4()} style={{ cursor: 'pointer' }} onClick={e => onRowClick(room)}>
                                <th scope="row">{index + 1}</th>
                                <td>{room.name}</td>
                                <td>{room.luxurity}</td>
                                <td className="d-none d-md-table-cell">{room.capacity}</td>
                                <td className="d-none d-md-table-cell">{room.price}</td>
                                <td className="flex-row">
                                    <button type="button" className="btn btn-success"
                                            style={{color: '#fff', marginRight: '0.3rem'}}
                                            // data-coreui-toggle="tooltip" data-coreui-placement="top"
                                            // title="Edit this room"
                                            // onMouseOver={(e) => {
                                            //     Tooltip.getOrCreateInstance(e.target).show()
                                            // }}
                                    >
                                        <CIcon className="btn-icon" icon={cilPencil}/>
                                    </button>
                                </td>
                            </tr>
                        ))
                    }
                    </tbody>
                </table>
                <Pagination pages={pages} changePage={changePage} moveToNextPage={moveToNextPage}
                            moveToPreviousPage={moveToPreviousPage} currentPage={currentPage}/>
            </div>
        </Fragment>
    );
};

RoomTable.propTypes = {
    hotelSelected: PropTypes.object.isRequired
};

export default RoomTable;