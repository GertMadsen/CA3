
const URL = require("../package.json").serverURL;

function handleHttpErrors(res) {
    if (!res.ok) {
        var error = { message: res.statusText, status: res.status }; 
        throw error;       
    }
    return res.json();
}

class ApiFacade {

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
        return fetch(URL + "/api/test/", options).then(handleHttpErrors);
    }

    makeFetchOptions = (type, b) => {
        let headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
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