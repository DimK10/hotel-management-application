import React, {Fragment, useEffect, useState} from 'react';
import SidebarComp from "../../layout/Sidebar";
import HeaderNav from "../../layout/HeaderNav";
import cities from '../../../json/cities.json';
import {useDispatch, useSelector} from "react-redux";
import {createNewHotelAction} from "../../../actions/hotel";
import {MultiSelect} from "react-multi-select-component";
import {fetchAllHotelAmenitiesAction, fetchAllRoomAmenitiesAction} from "../../../actions/amenity";
import {v4 as uuidv4} from 'uuid';
import AddRoomToNewHotel from "./AddRoomToNewHotel";
import CIcon from '@coreui/icons-react';
import {cilPlus} from '@coreui/icons';
import ShowRoomToNewHotel from "./ShowRoomToNewHotel";
import amenity from "../../../reducers/amenity";
import Alert from "../../layout/Alert";
import {useNavigate} from "react-router-dom";

function CreateHotel() {

    const dispatch = useDispatch();

    const navigate = useNavigate();

    const {user} = useSelector(state => state.auth);

    const {hotelAmenities, hotelAmenitiesLoading} = useSelector(state => state.amenity);

    const {roomAmenities, roomAmenitiesLoading} = useSelector(state => state.amenity);

    const [hotelAmenitiesToSelect, setHotelAmenitiesToSelect] = useState([]);

    const [roomAmenitiesToSelect, setRoomAmenitiesToSelect] = useState([]);

    const [hotelAmenitiesSelected, setHotelAmenitiesSelected] = useState([]);

    // TODO ADD ROOMS WITH CREATE HOTEL FORM
    const [formData, setFormData] = useState({
        name: '',
        stars: 1,
        areaName: '',
        disabled: false,
        owner: user?.id,
        rooms: [],
        amenities: []
    })

    const [citiesArray, setCitiesArray] = useState(cities);

    const [citiesSuggestions, setCitiesSuggestions] = useState([]);

    const newRoom = {
        id: uuidv4(),
        name: '',
        hotel: null,
        luxurity: 1,
        price: 0,
        capacity: 0,
        amenities: [],
        status: 'create'
    };

    const [roomsCreated, setRoomsCreated] = useState([]);


    const {
        name,
        stars,
        areaName,
        address,
        disabled,
        rooms,
    } = formData;

    const onChange = (e) =>
        setFormData({...formData, [e.target.name]: e.target.value});

    const onCheckboxChange = (e) =>
        setFormData({...formData, [e.target.name]: e.target.checked});

    const onLocationInputChange = (e) => {
        setCitiesSuggestions(
            citiesArray.filter((city) => city.city.includes(e.target.value))
        );

        onChange(e);
    };

    const onSubmit = async e => {
        e.preventDefault();
        console.log(formData)
        setFormData({...formData, owner: user?.id});
        console.log(formData)

        dispatch(createNewHotelAction(formData));

        // fixme this is not recommended if there is an error happening when saving a hotel
        navigate('/dashboard');

    };

    const onRoomSubmit = async (e, room) => {
        console.log(room)
        e.preventDefault();
        // room.status = 'show';

        await setRoomsCreated([...roomsCreated.map(el => {
            if (el.id === room.id)
                return {...room}
            else
                return el
        })]);
    }

    /* button click method to add new room for room form to show */
    const onAddNewRoomButtonClick = () =>
        setRoomsCreated([...roomsCreated, newRoom]);

    /* button click method to close room form, by deleting the room object */
    const onRoomCloseButtonClick = (roomId) =>
        setRoomsCreated([...roomsCreated.filter(room => room.id !== roomId)])


    useEffect(() => {

        const fetchAllAmenities = async () => {
            await dispatch(fetchAllHotelAmenitiesAction());
            await dispatch(fetchAllRoomAmenitiesAction());
        }

        fetchAllAmenities()
            .catch(err => console.log(err));

        // construct data for react-multi-select
        let data = [];

        hotelAmenities.forEach(el => data.push({label: el.hAmenity, value: el.id}));
        setHotelAmenitiesToSelect([...data]);

    }, [hotelAmenitiesLoading]);

    useEffect(() => {
        let data = [];
        if (roomAmenities.length > 0)
            roomAmenities.forEach(el => data.push({label: el.rAmenity, value: el.id}))
        setRoomAmenitiesToSelect([...data]);
    }, [roomAmenitiesLoading])

    // test
    useEffect(() => {
        console.log(rooms);
    }, [rooms])

    /* update formData when state for  roomsCreated changes*/
    useEffect(() => {
        setFormData({
            ...formData,
            rooms: [...roomsCreated].map(room => ({
                ...room,
                id: null,
                amenities: [...room.amenities.map(el => ({id: el.value, rAmenity: el.label}))]
            }))
        })
    }, [roomsCreated])

    /* update formData when state for hotelAmenitiesSelected changes */
    useEffect(() => {
        setFormData({
            ...formData,
            amenities: [...hotelAmenitiesSelected.map(el => ({id: el.value, hAmenity: el.label}))]
        })
    }, [hotelAmenitiesSelected]);

    return (
        <Fragment>
            <SidebarComp/>
            <HeaderNav>
                <Alert />
                <div className="row">
                    <div className="card">
                        <div className="card-body">
                            <div className="card-title">
                                <h4>Add a new Hotel</h4>
                            </div>
                            <form onSubmit={e => onSubmit(e)}>
                                <div className="mb-3">
                                    <label htmlFor="name" className="form-label">Hotel Name:</label>
                                    <input type="text" className="form-control" id="name" name='name'
                                           aria-describedby="name" placeholder="Hotel Name" onChange={(e) => {
                                        onChange(e);
                                    }} required={true}/>
                                </div>
                                <div className='mb-3 w-25'>
                                    <label htmlFor='stars' className='form-label'>
                                        Stars: {stars}
                                    </label>
                                    <input
                                        type='range'
                                        className='form-range'
                                        id='stars'
                                        min='1'
                                        max='5'
                                        value={stars}
                                        name='stars'
                                        onChange={(e) => {
                                            onChange(e);
                                        }}
                                        required={true}
                                    />
                                </div>
                                <div className='mb-3'>
                                    <label htmlFor='areaName' className='form-label'>
                                        Location
                                    </label>
                                    <input
                                        type='text'
                                        className='form-control'
                                        id='areaName'
                                        placeholder='Location'
                                        name='areaName'
                                        onChange={(e) => onLocationInputChange(e)}
                                        onBlur={() => setCitiesSuggestions([])}
                                        list='citiesOptions'
                                        required={true}
                                    />
                                    <datalist id='citiesOptions'>
                                        {citiesSuggestions.length > 0 &&
                                            citiesSuggestions.map((city) => (
                                                <option key={city.id} value={city.city}/>
                                            ))}
                                    </datalist>
                                </div>

                                <div className="mb-3">
                                    <label htmlFor="address" className="form-label">Address:</label>
                                    <input type="text" className="form-control" id="address" name='address'
                                           aria-describedby="address" placeholder="Address" onChange={(e) => {
                                        onChange(e);
                                    }} required={true}
                                    />
                                </div>
                                <div className="mb-3">
                                    <MultiSelect
                                        options={hotelAmenitiesToSelect}
                                        value={hotelAmenitiesSelected}
                                        onChange={setHotelAmenitiesSelected}
                                        labelledBy="Select"
                                    />
                                </div>
                                {
                                    roomsCreated.length > 0
                                    &&
                                    roomsCreated.map(room => (
                                        room.status === 'show'
                                        &&
                                        <div key={uuidv4()} className="mb-3">
                                            <ShowRoomToNewHotel room={room}
                                                                onRoomCloseButtonClick={onRoomCloseButtonClick}
                                            />
                                        </div>
                                    ))
                                }
                                {
                                    roomsCreated.length > 0
                                    &&
                                    roomsCreated.map(room => (
                                        room.status === 'create'
                                        &&
                                        <div key={uuidv4()} className="mb-3">
                                            <AddRoomToNewHotel room={room} onRoomSubmit={onRoomSubmit}
                                                               roomAmenitiesToSelect={roomAmenitiesToSelect}
                                                               onRoomCloseButtonClick={onRoomCloseButtonClick}
                                            />
                                        </div>
                                    ))
                                }
                                <div className="mb-3">
                                    <button type="button"
                                            className="btn btn-primary"
                                            onClick={onAddNewRoomButtonClick}
                                    ><CIcon icon={cilPlus}/> Add new Room
                                    </button>
                                </div>

                                <button type="submit" className="btn btn-primary">Add</button>
                            </form>
                        </div>
                    </div>
                </div>
            </HeaderNav>
        </Fragment>
    );
}

export default CreateHotel;