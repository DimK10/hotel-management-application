import axios from "axios";
import setAuthToken from "../utils/setAuthToken";
import searchSlice from "../reducers/search";

const {
    basicSearch,
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

        dispatch(basicSearch(res.data));

    } catch (err) {
        dispatch(searchError(err.response.data.errorMessage));
    }
}