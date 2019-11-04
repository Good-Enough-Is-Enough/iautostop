import { IMG } from "./";

const MSG = {
  PASSENGER: {
    waiting: {
      type: "waiting",
      image: IMG.PASSENGER_WAITING,
      title: "Szukamy dla Ciebie podwózki...",
      text:
        "Powiadomimy Cię, gdy któryś z dostępnych kierowców zaakceptuje przejazd"
    },
    success: {
      type: "success",
      image: IMG.PASSENGER_SUCCESS,
      title: "Podwózka zaakceptowana!",
      text: "DANE KIEROWCY:",
      button: "Wróć do strony głównej"
    },
    error: {
      type: "error",
      image: IMG.PASSENGER_ERROR,
      title: "Popsuło się :(",
      text: "Sprawdź swoje połączenie sieciowe i spróbuj ponownie",
      icon: "mdi-internet",
      button: "Wróć do strony głównej"
    }
  }
};

export default MSG;
