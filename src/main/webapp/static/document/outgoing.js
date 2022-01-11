///////////////////Получить список всех Исходящий документ/Outgoing ///////////////////////////
async function getAllOutgoing() {
    const outgoingButton = $('#outgoing-ul');
    console.log(outgoingButton)
    const url = '/ecm/api/outgoing'
    $.ajax({
        url: url,
        dataType: 'json',
        type: "GET",
    }).done((response) => {
        console.log(response)
        let html = ''
        response.forEach(outgoing => {
            let liHtml = `
             <li id="outgoing-li-${outgoing.id}" className="list-group-item">
                <button id="outgoing-button-${outgoing.id}" type="button" className="btn btn-secondary" style="width: 100%">${outgoing.name}</button>
            </li>
            `
            html += liHtml;
            outgoingButton.html(html)
        })
        console.log(html)
    })
}

getAllOutgoing();