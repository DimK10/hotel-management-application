import React, {Fragment, useEffect} from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import {loadUser} from './actions/auth';
import {Provider} from 'react-redux';

import FirstPage from './components/firstpage/FirstPage';
import Register from './components/auth/Register';
import Login from './components/auth/Login';
import AdvancedSearch from './components/client/search/AdvancedSearch';

import setAuthToken from './utils/setAuthToken';
import store from './store';

import 'bootstrap/dist/css/bootstrap.min.css';


import '@coreui/coreui/dist/css/coreui.css';
import '@coreui/coreui/dist/js/coreui.js';

import NotFound from "./components/error/NotFound";
import SecuredPage from "./components/auth/SecuredPage";
import PlaceNewOrder from "./components/client/order/PlaceNewOrder";
import Dashboard from "./components/admin/dashboard/Dashboard";
import Hotels from "./components/admin/hotel/Hotels";
import Rooms from "./components/admin/room/Rooms";
import Orders from "./components/admin/order/Orders";
import Calendar from './components/admin/calendar/Calendar'
import CreateHotel from "./components/admin/hotel/CreateHotel";
import CreateRoom from "./components/admin/room/CreateRoom";
import Logout from "./components/auth/Logout";
import ViewHotel from "./components/admin/hotel/ViewHotel";
import EditHotel from "./components/admin/hotel/EditHotel";
import ClientOrders from "./components/client/order/ClientOrders";
import SecuredRolePage from "./components/auth/SecuredRolePage";
import ShowOrderInfo from './components/client/order/ShowOrderInfo';
import ViewRoom from "./components/admin/room/ViewRoom";
import EditRoom from "./components/admin/room/EditRoom";
import ViewOrder from "./components/admin/order/ViewOrder";

if (localStorage.token) {
    setAuthToken(localStorage.token);
}

const App = () => {

    const Role = {
        CLIENT: 'CLIENT',
        ADMIN: 'ADMIN'
    }

    useEffect(() => {
        store.dispatch(loadUser());
    }, []);

    return (
        <React.StrictMode>
            <Provider store={store}>
                <Fragment>
                    <Router>
                        <Routes>
                            <Route path='/' element={<FirstPage/>}/>
                            <Route path='/sign-in' element={<Login/>}/>
                            <Route path='/sign-up' element={<Register/>}/>
                            <Route path='/logout' element={<Logout/>}/>
                            <Route path='/search' element={<AdvancedSearch/>}/>
                            <Route path='/not-found' element={<NotFound/>}/>
                            <Route path='/order' element={<PlaceNewOrder/>}/>
                            <Route
                                path='/protected'
                                element={
                                    <SecuredPage>
                                        <AdvancedSearch/>
                                    </SecuredPage>
                                }
                            />

                            {/* Client Routes */}
                            <Route
                                path='/my-orders'
                                element={
                                    <SecuredPage>
                                        <SecuredRolePage userRole={Role.CLIENT}>
                                            <ClientOrders/>
                                        </SecuredRolePage>
                                    </SecuredPage>
                                }
                            />

                            <Route
                                path='/my-orders/:orderId'
                                element={
                                    <SecuredPage>
                                        <SecuredRolePage userRole={Role.CLIENT}>
                                            <ShowOrderInfo/>
                                        </SecuredRolePage>
                                    </SecuredPage>
                                }
                            />

                            {/* Admin routes */}
                            {/*  TODO ADD SECURED ROUTE */}
                            <Route
                                path='/dashboard'
                                element={
                                    <SecuredPage>
                                        <SecuredRolePage userRole={Role.ADMIN}>
                                            <Dashboard/>
                                        </SecuredRolePage>
                                    </SecuredPage>
                                }
                            />
                            <Route
                                path='/hotels'
                                element={
                                    <SecuredPage>
                                        <SecuredRolePage userRole={Role.ADMIN}>
                                            <Hotels/>
                                        </SecuredRolePage>
                                    </SecuredPage>
                                }
                            />
                            <Route
                                path='/hotels/new'
                                element={
                                    <SecuredPage>
                                        <SecuredRolePage userRole={Role.ADMIN}>
                                            <CreateHotel/>
                                        </SecuredRolePage>
                                    </SecuredPage>
                                }
                            />
                            <Route
                                path='/hotels/update/:hotelId'
                                element={
                                    <SecuredPage>
                                        <SecuredRolePage userRole={Role.ADMIN}>
                                            <EditHotel/>
                                        </SecuredRolePage>
                                    </SecuredPage>
                                }
                            />
                            <Route
                                exact
                                path='/hotels/:hotelId'
                                element={
                                    <SecuredPage>
                                        <SecuredRolePage userRole={Role.ADMIN}>
                                            <ViewHotel/>
                                        </SecuredRolePage>
                                    </SecuredPage>
                                }
                            />
                            <Route
                                path='/rooms'
                                element={
                                    <SecuredPage>
                                        <SecuredRolePage userRole={Role.ADMIN}>
                                            <Rooms/>
                                        </SecuredRolePage>
                                    </SecuredPage>
                                }
                            />
                            <Route
                                path='/rooms/new'
                                element={
                                    <SecuredPage>
                                        <SecuredRolePage userRole={Role.ADMIN}>
                                            <CreateRoom/>
                                        </SecuredRolePage>
                                    </SecuredPage>
                                }
                            />
                            <Route
                                path='/rooms/update/:roomId'
                                element={
                                    <SecuredPage>
                                        <SecuredRolePage userRole={Role.ADMIN}>
                                            <EditRoom/>
                                        </SecuredRolePage>
                                    </SecuredPage>
                                }
                            />
                            <Route
                                exact
                                path='/rooms/:roomId'
                                element={
                                    <SecuredPage>
                                        <SecuredRolePage userRole={Role.ADMIN}>
                                            <ViewRoom/>
                                        </SecuredRolePage>
                                    </SecuredPage>
                                }
                            />
                            <Route
                                path='/orders'
                                element={
                                    <SecuredPage>
                                        <SecuredRolePage userRole={Role.ADMIN}>
                                            <Orders/>
                                        </SecuredRolePage>
                                    </SecuredPage>
                                }
                            />
                            <Route
                                exact
                                path='/orders/:orderId'
                                element={
                                    <SecuredPage>
                                        <SecuredRolePage userRole={Role.ADMIN}>
                                            <ViewOrder/>
                                        </SecuredRolePage>
                                    </SecuredPage>
                                }
                            />
                            <Route
                                path='/calendar'
                                element={
                                    <SecuredPage>
                                        <SecuredRolePage userRole={Role.ADMIN}>
                                            <Calendar/>
                                        </SecuredRolePage>
                                    </SecuredPage>
                                }
                            />
                        </Routes>
                    </Router>
                </Fragment>
            </Provider>
        </React.StrictMode>
    );
};

export default App;
