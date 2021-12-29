import {getParameterByName, setTextNode} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => createInfoAction(event));

    fetchAndDisplayAthlete();
});

function fetchAndDisplayAthlete() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayDiscipline(getParameterByName('discipline'))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/disciplines/' + getParameterByName('discipline'), true);
    xhttp.send();
}

function createInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayAthlete();
        }
    };
    xhttp.open("POST", getBackendUrl() + '/api/disciplines/' + getParameterByName('discipline') +
        '/athletes/', true);

    const request = {
        'name': document.getElementById('name').value,
        'birthYear': parseInt(document.getElementById('birthYear').value),
        'discipline': getParameterByName('discipline')
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}

function displayDiscipline(discipline) {
    setTextNode('discipline', discipline);
}

