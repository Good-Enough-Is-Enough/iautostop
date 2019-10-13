const MAIN_API_PATH = "/api/users";
export const HERE_MAP_APP_ID = "i8w3y91fhZ5nDPijP7XL";
export const HERE_MAP_APP_CODE = "v6YKXNdMEl2eGoTGHyToSQ";
export const SET_USER_INFO = MAIN_API_PATH;
const API_AVAILABLE_PASSENGERS = "/available-passengers";
const API_ASSIGNED_DRIVER = "/assigned-driver";
const API_STREET = "/streets";
const API_CONNECT = "/connectedTo";

export const getAvailablePassengersUrl = driverId =>
  `${MAIN_API_PATH}/${driverId}${API_AVAILABLE_PASSENGERS}`;

export const getRouteStreet = driverId =>
  `${MAIN_API_PATH}/${driverId}${API_STREET}`;

export const getAvailableDriver = passengerId =>
  `${MAIN_API_PATH}/${passengerId}${API_ASSIGNED_DRIVER}`;

export const connectTo = (passengerId, driverId) =>
  `${MAIN_API_PATH}/${passengerId}/connectTo/${driverId}`;

export const getConnectedPassengers = driverId =>
  `${MAIN_API_PATH}${API_CONNECT}/${driverId}`;

export const getEndTripUrl = driverId =>
  `${MAIN_API_PATH}/driver/${driverId}/end-trip`;
