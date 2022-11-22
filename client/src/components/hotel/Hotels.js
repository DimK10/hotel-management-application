import React, {Fragment} from 'react';
import PropTypes from 'prop-types';
import SidebarComp from "../layout/Sidebar";
import HeaderNav from "../layout/HeaderNav";

const Hotel = props => {
  return (
    <Fragment>

      <SidebarComp/>
      <HeaderNav />
      hotels
    </Fragment>
  );
};

Hotel.propTypes = {

};

export default Hotel;
