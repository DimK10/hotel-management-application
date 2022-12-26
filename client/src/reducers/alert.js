import {createSlice} from "@reduxjs/toolkit";

const alertSlice = createSlice({
  name: "alert",
  initialState: {
    alerts: []
  },
  reducers: {
    setAlert: (state, action )=> {
      const { payload } = action;


      state.alerts = [payload];
    },
    removeAlert: (state, action) => {
      const { payload } = action;
      state.alerts = [...state.alerts.filter((alert) => alert.id !== payload)];
    }
  }
})

export default alertSlice;