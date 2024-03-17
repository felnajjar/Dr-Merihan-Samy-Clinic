window.onload = (event) => {
  if (window.location.hash === "#loginFailed") {
    let elem = document.getElementById("loginFailed");
    let toast = new bootstrap.Toast(elem);
    toast.show();
  } else if (window.location.hash === "#loginSuccess") {
    let elem = document.getElementById("loginSuccess");
    let toast = new bootstrap.Toast(elem);
    toast.show();
  } else if (window.location.hash === "#slotNotAvailable") {
    let elem = document.getElementById("slotNotAvailable");
    let toast = new bootstrap.Toast(elem);
    toast.show();
  } else if (window.location.hash === "#bookSuccess") {
    let elem = document.getElementById("bookSuccess");
    let toast = new bootstrap.Toast(elem);
    toast.show();
  } else if (window.location.hash === "#updated") {
    let elem = document.getElementById("updated");
    let toast = new bootstrap.Toast(elem);
    toast.show();
  } else if (window.location.hash === "#added") {
    let elem = document.getElementById("added");
    let toast = new bootstrap.Toast(elem);
    toast.show();
  } else if (window.location.hash === "#deleted") {
    let elem = document.getElementById("deleted");
    let toast = new bootstrap.Toast(elem);
    toast.show();
  } else if (window.location.hash === "#invalidDate") {
    let elem = document.getElementById("invalidDate");
    let toast = new bootstrap.Toast(elem);
    toast.show();
  } else if (window.location.hash === "#invalidDuration") {
    let elem = document.getElementById("invalidDuration");
    let toast = new bootstrap.Toast(elem);
    toast.show();
  } else if (window.location.hash === "#emailExists") {
    let elem = document.getElementById("emailExists");
    let toast = new bootstrap.Toast(elem);
    toast.show();
  } else if (window.location.hash === "#passwordLength") {
    let elem = document.getElementById("passwordLength");
    let toast = new bootstrap.Toast(elem);
    toast.show();
  }
};
