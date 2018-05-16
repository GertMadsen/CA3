
// const URL = require("../package.json").serverURL;
const URL = "http://localhost:8084/jwtbackend";


function handleHttpErrors(res) {
    if (!res.ok) {
        throw { message: res.statusText, status: res.status };
    }
    return res.json();
}

class ApiFacade {

    setToken = (token) => {
        localStorage.setItem('jwtToken', token)
    }
    getToken = () => {
        return localStorage.getItem('jwtToken')
    }
    loggedIn = () => {
        const loggedIn = this.getToken() != null;
        return loggedIn;
    }
    logout = () => {
        localStorage.removeItem("jwtToken");
    }

    login = (user, pass) => {
        const options = this.makeFetchOptions("POST", { username: user, password: pass });


        return fetch(URL + "/api/login", options, true)
            .then(handleHttpErrors)
            .then(res => { this.setToken(res.token) })
    }

    fetchCars = (urlExtension) => {
        const options = this.makeFetchOptions("GET");
        return fetch(URL + "/api/cars"+urlExtension, options).then(handleHttpErrors);
    }
    
    fetchSingleCar = (regno) => {
        const options = this.makeFetchOptions("GET");
        return fetch(URL + "/api/cars/"+regno, options).then(handleHttpErrors);
    }

    fetchBooking = (body) => {
        const options = this.makeFetchOptions("POST",body);
        return fetch(URL + "/api/cars/", options).then(handleHttpErrors);
    }


    makeFetchOptions = (type, b) => {
        let headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
        if (this.loggedIn()) {
            headers["x-access-token"] = this.getToken();
        }
        return {
            method: type,
            headers,
            body: JSON.stringify(b)
        }
    }
}
const facade = new ApiFacade();
export default facade;