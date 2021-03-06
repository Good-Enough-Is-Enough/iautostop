import IMG from "./images";
import MSG from "./messages";
const MAIN_API_PATH = "/api/users";
const HERE_MAP_APP_ID = "i8w3y91fhZ5nDPijP7XL";
const HERE_MAP_APP_CODE = "v6YKXNdMEl2eGoTGHyToSQ";
const SET_USER_INFO = MAIN_API_PATH;
const API_AVAILABLE_PASSENGERS = "/available-passengers";
const API_ASSIGNED_DRIVER = "/assigned-driver";
const API_STREET = "/streets";
const API_CONNECT = "/connectedTo";

const getAvailablePassengersUrl = driverId =>
  `${MAIN_API_PATH}/${driverId}${API_AVAILABLE_PASSENGERS}`;

const getRouteStreet = driverId => `${MAIN_API_PATH}/${driverId}${API_STREET}`;

const getAvailableDriver = passengerId =>
  `${MAIN_API_PATH}/${passengerId}${API_ASSIGNED_DRIVER}`;

const connectTo = (passengerId, driverId) =>
  `${MAIN_API_PATH}/${passengerId}/connectTo/${driverId}`;

const getConnectedPassengers = driverId =>
  `${MAIN_API_PATH}${API_CONNECT}/${driverId}`;

const getEndTripUrl = driverId =>
  `${MAIN_API_PATH}/driver/${driverId}/end-trip`;

export {
  IMG,
  MSG,
  HERE_MAP_APP_ID,
  HERE_MAP_APP_CODE,
  SET_USER_INFO,
  getAvailablePassengersUrl,
  getRouteStreet,
  getAvailableDriver,
  connectTo,
  getConnectedPassengers,
  getEndTripUrl
};
