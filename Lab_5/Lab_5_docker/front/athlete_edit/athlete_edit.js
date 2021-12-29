import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplayAthlete();
});

function fetchAndDisplayAthlete() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/disciplines/' + getParameterByName('discipline') +
        '/athletes/' + getParameterByName('athlete'), true);
    xhttp.send();
}

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayAthlete();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/disciplines/' + getParameterByName('discipline') +
        '/athletes/' + getParameterByName('athlete'), true);

    const request = {
        'name': document.getElementById('name').value,
        'birthYear': parseInt(document.getElementById('birthYear').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}
