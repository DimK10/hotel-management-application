import React, {Fragment} from 'react';
import PropTypes from 'prop-types';
import SidebarComp from "../layout/Sidebar";
import HeaderNav from "../layout/HeaderNav";


const Orders = props => {
  return (
    <Fragment>

      <SidebarComp/>
      <HeaderNav />
      orders
    </Fragment>
  );
};

Orders.propTypes = {

};

export default Orders;
