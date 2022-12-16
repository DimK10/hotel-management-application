import React from 'react';
import PropTypes from 'prop-types';



function Loading(props) {
  return (
    <div className="spinner-border" role="status">
      <span className="visually-hidden">Loading...</span>
    </div>
  );
}

Loading.propTypes = {

};

export default Loading;