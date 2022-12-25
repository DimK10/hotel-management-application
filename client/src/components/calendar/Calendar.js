import React, {Fragment} from 'react';
import SidebarComp from "../layout/Sidebar";
import HeaderNav from "../layout/HeaderNav";
import Kalend, {CalendarView} from 'kalend' // import component
import 'kalend/dist/styles/index.css'; // import styles


const Calendar = props => {

  // dummy events
  const events = [
    {
      id: 1,
      startAt: '2022-12-01T18:00:00.000Z',
      endAt: '2022-12-05T19:00:00.000Z',
      timezoneStartAt: 'Europe/Athens', // optional
      summary: 'test1',
      color: 'blue',
      calendarID: 'work'
    },
    {
      id: 2,
      startAt: '2022-12-08T18:00:00.000Z',
      endAt: '2022-12-11T19:00:00.000Z',
      timezoneStartAt: 'Europe/Athens', // optional
      summary: 'test2',
      color: 'yellow'
    }
  ]

  return (
    <Fragment>

      <SidebarComp/>
      <HeaderNav>
        <div style={{ backgroundColor: "white", height: "90vh" }}>
          <Kalend
            events={events}
            initialDate={new Date().toISOString()}
            hourHeight={60}
            initialView={CalendarView.MONTH}
            disabledViews={[CalendarView.DAY]}

            timeFormat={'24'}
            weekDayStart={'Monday'}
            calendarIDsHidden={['work']}
            language={'en'}
          />
        </div>
      </HeaderNav>
    </Fragment>
  );
};

Calendar.propTypes = {};

export default Calendar;
