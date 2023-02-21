import React, {Fragment, useEffect, useState} from 'react';
import PropTypes from 'prop-types';
import {useDispatch, useSelector} from "react-redux";
import {useNavigate, useParams} from "react-router-dom";
import {loadUser} from "../../../actions/auth";
import {getHotelByIdAction, updateExistingHotelAction} from "../../../actions/hotel";
import {getRoomByIdAction, updateExistingRoomAction} from "../../../actions/room";
import SidebarComp from "../../layout/Sidebar";
import Alert from "../../layout/Alert";
import {MultiSelect} from "react-multi-select-component";
import {fetchAllRoomAmenitiesAction} from "../../../actions/amenity";
import HeaderNav from "../../layout/HeaderNav";
import Loading from "../../layout/Loading";

const EditRoom = props => {

    const dispatch = useDispatch();

    const {roomId} = useParams();

    const {user} = useSelector(state => state.auth);

    const {room} = useSelector(state => state.room);

    const {roomAmenities} = useSelector(state => state.amenity);

    const navigate = useNavigate();

    const [roomAmenitiesToSelect, setRoomAmenitiesToSelect] = useState([]);

    const [roomAmenitiesSelected, setRoomAmenitiesSelected] = useState([]);

    const [loading, setLoading] = useState(true);


    const [formData, setFormData] = useState({
        id: room.id,
        name: room.name,
        luxurity: room.luxurity,
        price: room.price,
        capacity: room.capacity,
        amenities: room.amenities,
        hotel: room.hotel
    });


    const {
        id,
        name,
        luxurity,
        price,
        capacity,
        amenities,
        hotel
    } = formData;

    useEffect(() => {
        dispatch(fetchAllRoomAmenitiesAction());
    }, [])

    useEffect(() => {

        if (user === null) {
            dispatch(loadUser);
        }

        dispatch(getRoomByIdAction(roomId));

    }, [roomId]);

    useEffect(() => {
        if (room !== null && room !== 'undefined' && Object.keys(room).length > 0) {
            setRoomAmenitiesSelected([...room.amenities.map(roomAmenity => {
                return {label: roomAmenity.rAmenity, value: roomAmenity.id}
            })])

            setFormData({
                id: room.id,
                name: room.name,
                luxurity: room.luxurity,
                price: room.price,
                capacity: room.capacity,
                amenities: room.amenities,
                hotel: room.hotel
            })
        }

    }, [room])

    useEffect(() => {
        if (room !== null && room !== 'undefined' && Object.keys(room).length > 0 && roomAmenitiesToSelect.length > 0)
            setLoading(false);
    }, [room, roomAmenitiesToSelect])

    useEffect(() => {

        let data = roomAmenities.map(roomAmenity => {
            return {label: roomAmenity.rAmenity, value: roomAmenity.id}
        })

        setRoomAmenitiesToSelect(data);
    }, [roomAmenities]);

    // test
    useEffect(() => {
        setFormData({
            ...formData,
            amenities: [...roomAmenitiesSelected.map(el => ({id: el.value, rAmenity: el.label}))]
        })
    }, [roomAmenitiesSelected]);


    const onChange = (e) =>
        setFormData({...formData, [e.target.name]: e.target.value});

    const onCheckboxChange = (e) =>
        setFormData({...formData, [e.target.name]: e.target.checked});


    const onSubmit = async e => {
        e.preventDefault();
        await dispatch(updateExistingRoomAction(formData));
        navigate(`/rooms/${roomId}`);
    };

    return (
        <Fragment>
            <SidebarComp/>
            <HeaderNav>
                <Alert/>
                {
                    loading
                        ?
                        (
                            <Loading/>
                        )
                        : (
                            <div className="row">
                                <div className="card">
                                    <div className="card-body">
                                        <div className="card-title">
                                            <h4>Edit This Room</h4>
                                        </div>
                                        <form onSubmit={e => onSubmit(e)}>
                                            <div className="mb-3">
                                                <label htmlFor="name" className="form-label">Room Name:</label>
                                                <input type="text" className="form-control" id="name" name='name'
                                                       value={name}
                                                       aria-describedby="name" placeholder="Hotel Name" onChange={(e) => {
                                                    onChange(e);
                                                }} required={true}/>
                                            </div>
                                            <div className='mb-3 w-25'>
                                                <label htmlFor='luxurity' className='form-label'>
                                                    Luxurity: {luxurity}
                                                </label>
                                                <input
                                                    type='range'
                                                    className='form-range'
                                                    id='luxurity'
                                                    min='1'
                                                    max='5'
                                                    name='luxurity'
                                                    value={luxurity}
                                                    onChange={(e) => {
                                                        onChange(e);
                                                    }}
                                                    required={true}
                                                />
                                            </div>
                                            <div className="mb-3 w-25">
                                                <label htmlFor="price" className="form-label">Price:</label>
                                                <input type="number" min="0" step=".01" className="form-control" id="price"
                                                       name="price" aria-describedby="name" placeholder="Price"
                                                       value={price}
                                                       onChange={(e) => {
                                                           onChange(e);
                                                       }} required={true}/>
                                            </div>

                                            <div className="mb-3 w-25">
                                                <label htmlFor="capacity" className="form-label">Capacity:</label>
                                                <input type="number" min="0" className="form-control" id="capacity"
                                                       name="capacity" aria-describedby="name" placeholder="Capacity" value={capacity}
                                                       onChange={(e) => {
                                                           onChange(e);
                                                       }} required={true}/>
                                            </div>

                                            <div className="mb-3">
                                                <MultiSelect
                                                    options={roomAmenitiesToSelect}
                                                    value={roomAmenitiesSelected}
                                                    onChange={setRoomAmenitiesSelected}
                                                    labelledBy="Select"
                                                />
                                            </div>


                                            <button type="submit" className="btn btn-success text-white">Update</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        )
                }

            </HeaderNav>
        </Fragment>
    );
};

EditRoom.propTypes = {};

export default EditRoom;