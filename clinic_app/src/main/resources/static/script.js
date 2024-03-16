window.onload = (event) => {
    if (window.location.hash === '#loginFailed') {
    let elem = document.getElementById('loginFailed')
    let toast = new bootstrap.Toast(elem);
    toast.show();
    }
}