import React, {Fragment, useEffect, useState} from 'react';
import PropTypes from 'prop-types';


function Pagination({pages}) {

  const [pagesArr, setPagesArr] = useState([]);

  useEffect(() => {

    let arr = [];

    for (let i = 1; i <= pages; i++) {
      arr.push(i);
    }

    setPagesArr(arr);
  }, [pages]);


  return (
    <Fragment>
      <nav aria-label="Page navigation">
        <ul className="pagination justify-content-center ">
          <li className="page-item disabled">
            <a className="page-link">&laquo;</a>
          </li>
          {
            pages !== null
            &&
            pages > 0
            &&
            [pagesArr.map((page) => <li key={page}
                                               className={`page-item  ${page === 1 ? 'active' : ''}`}>
              <a className="page-link" href="#">{page}</a></li>)]
          }
          {/*<li className="page-item active"><a className="page-link" href="#">1</a>*/}
          {/*</li>*/}
          {/*<li className="page-item"><a className="page-link" href="#">2</a></li>*/}
          {/*<li className="page-item"><a className="page-link" href="#">3</a></li>*/}
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