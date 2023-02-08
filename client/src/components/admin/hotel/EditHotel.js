import React, {Fragment, useEffect, useState} from 'react';
import SidebarComp from "../../layout/Sidebar";
import HeaderNav from "../../layout/HeaderNav";
import {useDispatch, useSelector} from "react-redux";
import cities from "../../../json/cities.json";
import {
  getHotelByIdAction,
  updateExistingHotelAction
} from "../../../actions/hotel";
import {loadUser} from "../../../actions/auth";
import {useNavigate, useParams} from "react-router-dom";
import Alert from "../../layout/Alert";

const EdiHotel = () => {

  const dispatch = useDispatch();

  const {hotelId} = useParams();

  const {user} = useSelector(state => state.auth);

  const {hotel} = useSelector(state => state.hotel);

  const navigate = useNavigate();

  useEffect(() => {

    if (user === null) {
      dispatch(loadUser);
    }

    dispatch(getHotelByIdAction(hotelId));
  }, [hotelId])

  // TODO ADD ROOMS WITH CREATE HOTEL FORM
  const [formData, setFormData] = useState({
    id: hotel.id,
    name: hotel.name,
    stars: hotel.stars,
    areaName: hotel.areaName,
    disabled: hotel.disabled,
    owner: user?.id,
    rooms: hotel.rooms,
    address: hotel.address,
  })

  const [citiesArray, setCitiesArray] = useState(cities);
  const [citiesSuggestions, setCitiesSuggestions] = useState([]);


  const {
    name,
    stars,
    areaName,
    address,
    disabled,
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
    setFormData({...formData, owner: user?.id});
    await dispatch(updateExistingHotelAction(formData));
    navigate(`/hotels/${hotelId}`);
  };


  return (
    <Fragment>
      <SidebarComp/>
      <HeaderNav>
        <Alert />
        <div className="row">
          <div className="card">
            <div className="card-body">
              <div className="card-title">
                <h4>Edit This Hotel</h4>
              </div>
              <form onSubmit={e => onSubmit(e)}>
                <div className="mb-3">
                  <label htmlFor="name" className="form-label">Hotel Name:</label>
                  <input type="text" className="form-control" id="name" name='name'
                         value={name}
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
                    value={areaName}
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
                         value={address}
                         aria-describedby="address" placeholder="Address" onChange={(e) => {
                    onChange(e);
                  }} required={true}
                  />
                </div>
                <button type="submit" className="btn btn-success text-white">Update</button>
              </form>
            </div>
          </div>
        </div>
      </HeaderNav>
    </Fragment>
  );
};

export default EdiHotel;
