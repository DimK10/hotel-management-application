import {createSlice} from "@reduxjs/toolkit";

const alertSlice = createSlice({
  name: "alert",
  initialState: [],
  reducers: {
    setAlert: (state, action )=> {
      const { payload } = action;
      state = [...payload];
    },
    removeAlert: (state, action) => {
      const { payload } = action;
      state = [...state.filter((alert) => alert.id !== payload)];
    }
  }
})

export default alertSlice;