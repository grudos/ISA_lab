import {getParameterByName, clearElementChildren, createLinkCell, createButtonCell, createTextCell,
        setTextNode} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayDisciplines();
    fetchAndDisplayAthletes();

    createAthlete();
});

function fetchAndDisplayAthletes() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayAthletes(JSON.parse(this.responseText))

        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/disciplines/' + getParameterByName('discipline') + '/athletes', true);
    xhttp.send();
}

function displayAthletes(athletes) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    athletes.athletes.forEach(athlete => {
        tableBody.appendChild(createTableRow(athlete));
    })
}

function createTableRow(athlete) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(athlete.name));
    tr.appendChild(createLinkCell('edit', '../athlete_edit/athlete_edit.html?discipline='
        + getParameterByName('discipline') + '&athlete=' + athlete.id));
    tr.appendChild(createLinkCell('view', '../athlete_view/athlete_view.html?discipline='
        + getParameterByName('discipline') + '&athlete=' + athlete.id));
    tr.appendChild(createButtonCell('delete', () => deleteAthlete(athlete.id)));
    return tr;
}

function deleteAthlete(athlete) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayAthletes();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/disciplines/' + getParameterByName('discipline')
        + '/athletes/' + athlete, true);
    xhttp.send();
}

function fetchAndDisplayDisciplines() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayDiscipline(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/disciplines/' + getParameterByName('discipline'), true);
    xhttp.send();
}

function displayDiscipline(discipline) {
    setTextNode('name', discipline.name);
    setTextNode('typeOfSport', discipline.typeOfSport);
    setTextNode('numberOfFans', discipline.numberOfFans.toString());
}

function createAthlete() {
    let create_athlete = document.getElementById('createBody');
    let tr = document.createElement('tr');
    create_athlete.appendChild(tr.appendChild(createLinkCell('create', '../athlete_create/athlete_create.html?discipline='
        + getParameterByName('discipline'))));
}
