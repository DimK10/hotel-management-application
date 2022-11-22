import React, {Fragment} from 'react';
import PropTypes from 'prop-types';
import SidebarComp from "../layout/Sidebar";
import HeaderNav from "../layout/HeaderNav";



const Calendar = props => {
  return (
    <Fragment>

      <SidebarComp/>
      <HeaderNav />
      calendar
    </Fragment>
  );
};

Calendar.propTypes = {

};

export default Calendar;
