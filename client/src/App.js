import { Fragment, useEffect } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { loadUser } from './actions/auth';
import { Provider } from 'react-redux';

import FirstPage from './components/firstpage/FirstPage';

import 'bootstrap/dist/css/bootstrap.min.css';
import '@coreui/coreui/dist/css/coreui.min.css';
import '@coreui/coreui/dist/js/coreui.js';

import setAuthToken from './utils/setAuthToken';
import store from './store';
import Register from './components/auth/Register';
import Login from './components/auth/Login';

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
            <Route path='/' element={<FirstPage />} />
            <Route path='/sign-in' element={<Register />} />
            <Route path='/sign-up' element={<Login />} />
          </Routes>
        </Router>
      </Fragment>
    </Provider>
  );
};

export default App;
