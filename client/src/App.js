import {Fragment, useEffect} from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import {loadUser} from './actions/auth';
import {Provider} from 'react-redux';

import FirstPage from './components/firstpage/FirstPage';
import Register from './components/auth/Register';
import Login from './components/auth/Login';
import AdvancedSearch from './components/search/AdvancedSearch';

import setAuthToken from './utils/setAuthToken';
import store from './store';

import 'bootstrap/dist/css/bootstrap.min.css';


import '@coreui/coreui/dist/css/coreui.css';
import '@coreui/coreui/dist/js/coreui.js';

// import '@coreui/coreui'

import NotFound from "./components/error/NotFound";
import SecuredPage from "./components/auth/SecuredPage";
import PlaceNewOrder from "./components/order/PlaceNewOrder";
import Dashboard from "./components/dashboard/Dashboard";
import Hotels from "./components/hotel/Hotels";
import Rooms from "./components/room/Rooms";
import Orders from "./components/order/Orders";
import Calendar from './components/calendar/Calendar'
import CreateHotel from "./components/hotel/CreateHotel";
import CreateRoom from "./components/room/CreateRoom";
import Logout from "./components/auth/Logout";

if (localStorage.token) {
  setAuthToken(localStorage.token);
}

const App = () => {
  useEffect(() => {
    store.dispatch(loadUser());
  }, []);

  return (
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
            <Route path='/protected'
                   element={
                     <SecuredPage>
                       <AdvancedSearch/>
                     </SecuredPage>
                   }/>


            {/* Admin routes */}
            {/*  TODO ADD SECURED ROUTE */}
            <Route path='/dashboard' element={
              <SecuredPage>
              <Dashboard />
            </SecuredPage>}/>
            <Route path='/hotels' element={
              <SecuredPage>
                <Hotels/>
              </SecuredPage>
            }/>
            <Route path='/hotels/new' element={
              <SecuredPage>
                <CreateHotel/>
            </SecuredPage>}/>
            <Route path='/rooms' element={
              <SecuredPage>
                <Rooms/>
              </SecuredPage>
            }/>
            <Route path='/rooms/new' element={
              <SecuredPage>
                <CreateRoom/>
              </SecuredPage>
            }/>
            <Route path='/orders' element={
              <SecuredPage>
                <Orders/>
              </SecuredPage>
            }/>
            <Route path='/calendar' element={<SecuredPage>
              <Calendar/>
            </SecuredPage>}/>
          </Routes>
        </Router>
      </Fragment>
    </Provider>
  );
};

export default App;
