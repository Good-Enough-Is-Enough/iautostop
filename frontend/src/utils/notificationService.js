export const notifyMe = text => {
  if (!("Notification" in window)) {
    alert("This browser does not support system notifications");
  } else if (Notification.permission === "granted") {
    new Notification(text);
  } else if (Notification.permission !== "denied") {
    Notification.requestPermission(function(permission) {
      if (permission === "granted") {
        new Notification(text);
      }
    });
  }
};
