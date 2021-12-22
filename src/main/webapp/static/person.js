///////////////////Получить список Person///////////////////////////
async function getAllPerson() {
    const personButton = $('#person-ul');
    console.log(personButton)
    const url = '/ecm/api/person'
    $.ajax({
        url: url,
        dataType: 'json',
        type: "GET",
    }).done((response) => {
        console.log(response)
        let html = ''
        response.forEach(person => {
            let liHtml = `
             <li id="person-li-${person.id}" className="list-group-item">
                <button id="person-button-${person.id}" type="button" className="btn btn-secondary" style="width: 100%">${person.surname} ${person.name} ${person.patronymic}</button>
            </li>
            `
            html += liHtml;
            personButton.html(html)
        })
        console.log(html)
    })
}

getAllPerson();