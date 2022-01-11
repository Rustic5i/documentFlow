///////////////////Получить список всех Входящий документ/Incoming ///////////////////////////
async function getAllIncoming() {
    const incomingButton = $('#incoming-ul');
    console.log(incomingButton)
    const url = '/ecm/api/incoming'
    $.ajax({
        url: url,
        dataType: 'json',
        type: "GET",
    }).done((response) => {
        console.log(response)
        let html = ''
        response.forEach(incoming => {
            let liHtml = `
             <li id="incoming-li-${incoming.id}" className="list-group-item">
                <button id="incoming-button-${incoming.id}" type="button" className="btn btn-secondary" style="width: 100%">${incoming.name}</button>
            </li>
            `
            html += liHtml;
            incomingButton.html(html)
        })
        console.log(html)
    })
}

getAllIncoming();