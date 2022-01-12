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
                <button onclick="addTabOutgoing(${outgoing.id})" id="outgoing-button-${outgoing.id}" type="button" className="btn btn-secondary" style="width: 100%">${outgoing.name}</button>
            </li>
            `
            html += liHtml;
            outgoingButton.html(html)
        })
        console.log(html)
    })
}

getAllOutgoing();

//////////////////// Вкладка с информацией по Исходящий документ/Outgoing////////////////////////////////
async function addTabOutgoing(idOutgoing) {
    const url = '/ecm/api/outgoing/' + idOutgoing
    $.ajax({
        url: url,
        dataType: 'json',
        type: "GET",
    }).done((outgoing) => {
        idTab = "nav-table-outgoing"+outgoing.id
        let tabButtonHtml = `
        <li class="nav-item">
             <a class="nav-link" id="nav-user-tab" data-bs-toggle="pill" href="#${idTab}">
                Исходящий документ №${outgoing.id}
                <button onclick="deleteTabById('${idTab}')" type="button" class="btn-close" aria-label="Close"></button>
             </a>
        </li>
        `
        let tabContentHtml = `
        <div class="tab-pane fade" id="${idTab}">
             Информация об Исходящим документе ${outgoing.name} №${outgoing.id}
        </div>
        `
        printTab(idTab, tabButtonHtml, tabContentHtml)
    })
}