import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayDisciplines();

    createDiscipline();
});

function fetchAndDisplayDisciplines() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayDisciplines(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/disciplines', true);
    xhttp.send();
}

function displayDisciplines(disciplines) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    disciplines.disciplines.forEach(discipline => {
        tableBody.appendChild(createTableRow(discipline));
    })
}

function createTableRow(discipline) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(discipline.name));
    tr.appendChild(createLinkCell('edit', '../disciplines_edit/disciplines_edit.html?discipline=' + discipline.name));
    tr.appendChild(createLinkCell('view', '../disciplines_view/disciplines_view.html?discipline=' + discipline.name));
    tr.appendChild(createButtonCell('delete', () => deleteDiscipline(discipline.name)));
    return tr;
}

function deleteDiscipline(discipline) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayDisciplines();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/disciplines/' + discipline, true);
    xhttp.send();
}

function createDiscipline() {
    let create_discipline = document.getElementById('createBody');
    let tr = document.createElement('tr');
    create_discipline.appendChild(tr.appendChild(createLinkCell('create', '../discipline_create/discipline_create.html')));
}