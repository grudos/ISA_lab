import {getParameterByName, setTextNode} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayAthletes();
});

function fetchAndDisplayAthletes() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayAthlete(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/disciplines/' + getParameterByName('discipline') +
        '/athletes/' + getParameterByName('athlete'), true);
    xhttp.send();
}

function displayAthlete(athlete) {
    setTextNode('name', athlete.name);
    setTextNode('birthYear', athlete.birthYear);
    setTextNode('discipline', athlete.discipline);
}
