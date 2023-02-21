import React, {Fragment, useEffect, useState} from 'react';

import {ArcElement, Chart as ChartJS, Legend, Tooltip} from 'chart.js';
import {Doughnut} from 'react-chartjs-2';
import {useDispatch} from "react-redux";
import PropTypes from "prop-types";


function MainChart({statistics, loading}) {

    ChartJS.register(ArcElement, Tooltip, Legend);

    const dispatch = useDispatch();

    // const {statistics, loading} = useSelector(state => state.hotel);

    const [data, setData] = useState({
        labels: ['Vacant', 'Available'],
        datasets: [],
    })

    // useEffect(() => {
    //     dispatch(getStatisticsAction());
    // }, [])

    useEffect(() => {

        const {
            all,
            vacant
        } = statistics;

        setData({
            ...data, datasets: [
                {
                    label: '# of Rooms',
                    data: [Number(vacant), Number(all) - Number(vacant)],
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
            ]
        })

    }, [statistics])


    return (
        <Fragment>
            {
                loading === false
                &&
                Object.keys(data).length > 0
                &&
                <Doughnut data={data} width={399} height={398}/>
            }
        </Fragment>
    );
}

MainChart.propTypes = {
    statistics: PropTypes.object.isRequired,
    loading: PropTypes.bool.isRequired
};

export default MainChart;