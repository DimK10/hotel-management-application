import {createAction} from "@reduxjs/toolkit";

// This file will be used for global actions, that will have impact in all the reducers, regarding the state

/**
 * This method reverts all states, except of the alert one, to their initial state. It will be used in log out functionality
 */
export const revertAll = createAction('global/revertAll');