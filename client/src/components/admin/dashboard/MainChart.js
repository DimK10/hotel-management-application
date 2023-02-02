import React, {Fragment} from 'react';

import {ArcElement, Chart as ChartJS, Legend, Tooltip} from 'chart.js';
import {Doughnut} from 'react-chartjs-2';


MainChart.propTypes = {};

function MainChart(props) {

  ChartJS.register(ArcElement, Tooltip, Legend);

  const data = {
    labels: ['Vacant', 'Available'],
    datasets: [
      {
        label: '# of Rooms',
        data: [10, 10],
        backgroundColor: [
          'rgba(255, 99, 132, 0.2)',
          'rgba(54, 162, 235, 0.2)',

        ],
        borderColor: [
          'rgba(255, 99, 132, 1)',
          'rgba(54, 162, 235, 1)',

        ],
        borderWidth: 1,
      },
    ],
  };

  return (
    <Fragment>
      <Doughnut data={data} width={399} height={398}/>
    </Fragment>
  );
}

export default MainChart;