import React, {Fragment} from 'react';
import PropTypes from 'prop-types';



function Pagination(props) {
  return (
    <Fragment>
      <nav aria-label="Page navigation">
        <ul className="pagination justify-content-center ">
          <li className="page-item disabled">
            <a className="page-link">&laquo;</a>
          </li>
          <li className="page-item active"><a className="page-link" href="#">1</a>
          </li>
          <li className="page-item"><a className="page-link" href="#">2</a></li>
          <li className="page-item"><a className="page-link" href="#">3</a></li>
          <li className="page-item">
            <a className="page-link" href="#">&raquo;</a>
          </li>
        </ul>
      </nav>
    </Fragment>
  );
}

Pagination.propTypes = {};

export default Pagination;