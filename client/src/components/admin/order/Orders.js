import React, {Fragment, useState} from 'react';
import SidebarComp from "../../layout/Sidebar";
import HeaderNav from "../../layout/HeaderNav";
import CIcon from "@coreui/icons-react";
import {cilPlus} from "@coreui/icons";
import {Tooltip} from '@coreui/coreui/dist/js/coreui';
import moment from "moment";
import {useDispatch, useSelector} from "react-redux";
import {findOrdersByFirstnameLastNameAction} from "../../../actions/order";


const Orders = props => {

    const dispatch = useDispatch();

    const {orders, loading} = useSelector(state => state.order);

    const [formData, setFormData] = useState({
        firstname: '',
        lastname: ''
    });

    const {
        firstname,
        lastname
    } = formData;

    const onChange = (e) =>
        setFormData({...formData, [e.target.name]: e.target.value});

    const onSubmit = (e) => dispatch(findOrdersByFirstnameLastNameAction(formData, 0, 999));


    return (
        <Fragment>
            <SidebarComp/>
            <HeaderNav>
                <div className="row">
                    <div className="col">
                        <h4 className="mb-4 mr-auto">List of orders made</h4>
                    </div>
                    {/*<div className="col-auto">*/}
                    {/*    <div className="row">*/}
                    {/*        <div className="col">*/}
                    {/*            <label htmlFor="rows-select" style={{marginRight: ".5rem"}}>Number of*/}
                    {/*                records:</label>*/}
                    {/*        </div>*/}
                    {/*        <div className="col">*/}
                    {/*            <select*/}
                    {/*                className="form-select" id="rows-select">*/}
                    {/*                <option value="10" defaultValue={true}>10</option>*/}
                    {/*                <option value="20">20</option>*/}
                    {/*                <option value="30">30</option>*/}
                    {/*            </select>*/}
                    {/*        </div>*/}
                    {/*    </div>*/}


                    {/*</div>*/}
                </div>

                <form onSubmit={(e) => onSubmit(e)}>
                    <div className="row">
                        <div className="col-sm-4 col-md-4 col-lg-5">
                            <input
                                type='text'
                                className='form-control'
                                placeholder='Search by Firstname'
                                name='firstname'
                                aria-label='Search by Firstname'
                                aria-describedby='button-addon2'
                            />
                        </div>
                        <div className="col-md-4 col-sm-4 col-lg-5">
                            <input
                                type='text'
                                className='form-control'
                                name='lastname'
                                placeholder='Search by Lastname'
                                aria-label='Search by Lastname'
                                aria-describedby='button-addon2'
                            />

                        </div>
                        <div className="col-sm-4 col-md-4 col-lg-2">
                            <button className='btn btn-primary px-4' style={{float: 'right'}} type='button'>
                                Search
                            </button>
                        </div>

                    </div>
                </form>
                <div className="row">
                    <table className="table table-hover table-responsive">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Check In Date</th>
                            <th scope="col">Check Out Date</th>
                            <th scope="col" className="d-none d-md-table-cell">Status</th>
                            <th scope="col" className="d-none d-md-table-cell">Room</th>
                            <th scope="col" className="d-none d-md-table-cell">Client</th>
                            <th scope="col">More</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th scope="row">1</th>
                            <td>{moment().format('DD/MM/YY')}</td>
                            <td>{moment().format('DD/MM/YY')}</td>
                            <td className="d-none d-md-table-cell">Active</td>
                            <td className="d-none d-md-table-cell">Room#1</td>
                            <td className="d-none d-md-table-cell">John Doe</td>
                            <td className="flex-row">
                                <button type="button" className="btn btn-warning"
                                        style={{color: '#fff', marginRight: '0.3rem'}}
                                        data-coreui-toggle="tooltip" data-coreui-placement="top"
                                        title="Show more details"
                                        onMouseOver={(e) => {
                                            Tooltip.getOrCreateInstance(e.target).show()
                                        }}
                                >
                                    <CIcon className="btn-icon" icon={cilPlus}/>
                                </button>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">1</th>
                            <td>{moment().format('DD/MM/YY')}</td>
                            <td>{moment().format('DD/MM/YY')}</td>
                            <td className="d-none d-md-table-cell">Active</td>
                            <td className="d-none d-md-table-cell">Room#1</td>
                            <td className="d-none d-md-table-cell">John Doe</td>
                            <td className="flex-row">
                                <button type="button" className="btn btn-warning"
                                        style={{color: '#fff', marginRight: '0.3rem'}}
                                        data-coreui-toggle="tooltip" data-coreui-placement="top"
                                        title="Show more details"
                                        onMouseOver={(e) => {
                                            Tooltip.getOrCreateInstance(e.target).show()
                                        }}
                                >
                                    <CIcon className="btn-icon" icon={cilPlus}/>
                                </button>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">1</th>
                            <td>{moment().format('DD/MM/YY')}</td>
                            <td>{moment().format('DD/MM/YY')}</td>
                            <td className="d-none d-md-table-cell">Active</td>
                            <td className="d-none d-md-table-cell">Room#1</td>
                            <td className="d-none d-md-table-cell">John Doe</td>
                            <td className="flex-row">
                                <button type="button" className="btn btn-warning"
                                        style={{color: '#fff', marginRight: '0.3rem'}}
                                        data-coreui-toggle="tooltip" data-coreui-placement="top"
                                        title="Show more details"
                                        onMouseOver={(e) => {
                                            Tooltip.getOrCreateInstance(e.target).show()
                                        }}
                                >
                                    <CIcon className="btn-icon" icon={cilPlus}/>
                                </button>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">1</th>
                            <td>{moment().format('DD/MM/YY')}</td>
                            <td>{moment().format('DD/MM/YY')}</td>
                            <td className="d-none d-md-table-cell">Active</td>
                            <td className="d-none d-md-table-cell">Room#1</td>
                            <td className="d-none d-md-table-cell">John Doe</td>
                            <td className="flex-row">
                                <button type="button" className="btn btn-warning"
                                        style={{color: '#fff', marginRight: '0.3rem'}}
                                        data-coreui-toggle="tooltip" data-coreui-placement="top"
                                        title="Show more details"
                                        onMouseOver={(e) => {
                                            Tooltip.getOrCreateInstance(e.target).show()
                                        }}
                                >
                                    <CIcon className="btn-icon" icon={cilPlus}/>
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    {/*<nav aria-label="Page navigation example">*/}
                    {/*    <ul className="pagination justify-content-center ">*/}
                    {/*        <li className="page-item disabled">*/}
                    {/*            <a className="page-link">&laquo;</a>*/}
                    {/*        </li>*/}
                    {/*        <li className="page-item active"><a className="page-link" href="../../client/order#">1</a>*/}
                    {/*        </li>*/}
                    {/*        <li className="page-item"><a className="page-link" href="../../client/order#">2</a></li>*/}
                    {/*        <li className="page-item"><a className="page-link" href="../../client/order#">3</a></li>*/}
                    {/*        <li className="page-item">*/}
                    {/*            <a className="page-link" href="../../client/order#">&raquo;</a>*/}
                    {/*        </li>*/}
                    {/*    </ul>*/}
                    {/*</nav>*/}
                </div>
            </HeaderNav>
        </Fragment>
    );
};

Orders.propTypes = {};

export default Orders;
