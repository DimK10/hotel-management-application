import React, { Fragment } from 'react'
import PropTypes from 'prop-types'

const Landing = props => {
    return (
      <Fragment>
        <div>Landing</div>
        <button type='button' className='btn btn-primary'>
          Primary
        </button>
      </Fragment>
    );
}

Landing.propTypes = {}

export default Landing