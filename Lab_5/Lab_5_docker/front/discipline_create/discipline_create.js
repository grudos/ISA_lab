import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => createInfoAction(event));
});

function createInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getBackendUrl() + '/api/disciplines', true);

    const request = {
        'name': document.getElementById('name').value,
        'typeOfSport': document.getElementById('typeOfSport').value,
        'numberOfFans': parseInt(document.getElementById('numberOfFans').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}
