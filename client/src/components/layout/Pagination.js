import React, {Fragment, useEffect, useState} from 'react';
import PropTypes from 'prop-types';


function Pagination({
                      pages,
                      currentPage,
                      changePage,
                      moveToNextPage,
                      moveToPreviousPage
                    }) {

  const [pagesArr, setPagesArr] = useState([]);

  useEffect(() => {

    let arr = [];

    for (let i = 1; i <= pages; i++) {
      arr.push(i);
    }

    setPagesArr([...arr]);
  }, [pages, currentPage]);


  return (
    <Fragment>
      <nav aria-label="Page navigation">
        <ul className="pagination justify-content-center ">
          <li className={`page-item ${parseInt(currentPage) === 1 ? 'disabled' : ''}`}
              style={{cursor: "pointer"}} onClick={(e) => parseInt(currentPage) !== 1 && moveToPreviousPage(e)}>
            <a className="page-link">&laquo;</a>
          </li>
          {
            pages !== null
            &&
            pages > 0
            &&
            pagesArr.map((page) => <li key={page}
                                       className={`page-item ${page === parseInt(currentPage) ? 'active' : ''}`}>
              <a className="page-link" href="" onClick={(e) => changePage(e)}>{page}</a>
            </li>)
          }
          <li className={`page-item ${parseInt(currentPage) === pages ? 'disabled' : ''}`}
              style={parseInt(currentPage) === pages ? {cursor: "pointer"} : {}}>
            <a className="page-link" href=""
               onClick={(e) => parseInt(currentPage) !== pages && moveToNextPage(e)}>&raquo;</a>
          </li>
        </ul>
      </nav>
    </Fragment>
  );
}

Pagination.propTypes = {
  pages: PropTypes.number.isRequired,
  currentPage: PropTypes.number.isRequired,
  changePage: PropTypes.func.isRequired,
  moveToNextPage: PropTypes.func.isRequired,
  moveToPreviousPage: PropTypes.func.isRequired
};

export default Pagination;