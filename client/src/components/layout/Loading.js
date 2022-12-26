import React from 'react';


function Loading(props) {
  return (
    <div className="container text-center">
      <div className="spinner-border text-center" role="status">
        <span className="visually-hidden">Loading...</span>
      </div>
    </div>
  );
}

Loading.propTypes = {};

export default Loading;