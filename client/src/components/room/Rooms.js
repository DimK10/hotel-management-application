import React, {Fragment} from 'react';
import PropTypes from 'prop-types';
import SidebarComp from "../layout/Sidebar";
import HeaderNav from "../layout/HeaderNav";

const Rooms = props => {
  return (
    <Fragment>

      <SidebarComp/>
      <HeaderNav />
      rooms
    </Fragment>
  );
};

Rooms.propTypes = {

};

export default Rooms;
