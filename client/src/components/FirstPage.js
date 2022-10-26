import React, { Fragment } from 'react'
import PropTypes from 'prop-types'
import NavBar from './layout/NavBar';

const FirstPage = (props) => {
  return (
    <Fragment>
      <NavBar/>
      <div>FirstPage</div>
  
    </Fragment>
  );
};

FirstPage.propTypes = {};

export default FirstPage;