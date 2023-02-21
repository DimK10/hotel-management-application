import axios from "axios";
import setAuthToken from "../utils/setAuthToken";
import searchSlice from "../reducers/search";

const {
    prepareLoading,
    search,
    searchError
} = searchSlice.actions;

export const basicSearchAction = ({checkInDate, checkOutDate, nameOrLocation}) => async (dispatch) => {

    if (localStorage.jwt) {
        setAuthToken(localStorage.jwt);
    }

    try {

        const config = {
            headers: {
                "Content-Type": "application/json",
            },
        };

        const body = JSON.stringify({
            checkInDate,
            checkOutDate,
            nameOrLocation
        });

        const res = await axios.post("/api/hotel/basic/search", body, config);

        dispatch(search(res.data));

    } catch (err) {
        dispatch(searchError(err.response.data.errorMessage));
    }
}

export const advancedSearchAction = (formData, pageNo, pageSize) => async (dispatch) => {

    if (localStorage.jwt) {
        setAuthToken(localStorage.jwt);
    }

    try {

        dispatch(prepareLoading);

        const config = {
            headers: {
                "Content-Type": "application/json",
            },
        };

        const res = await axios.post(`/api/hotel/advanced/search/${pageNo}/${pageSize}`, formData, config);

        dispatch(search(res.data));

    } catch (err) {
        dispatch(searchError(err.response.data.errorMessage));
    }
}