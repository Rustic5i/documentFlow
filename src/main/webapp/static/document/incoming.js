/////////////////Получить список всех Входящий документ/Incoming ///////////////////////////
async function getAllIncoming() {
    const incomingButton = $('#incoming-ul');
    const url = '/ecm/api/incoming'
    $.ajax({
        url: url,
        dataType: 'json',
        type: "GET",
    }).done((response) => {
        let html = ''
        response.forEach(incoming => {
            let liHtml = `
             <li id="incoming-li-${incoming.id}" className="list-group-item">
                <button onclick="addTabIncoming(${incoming.id})" id="incoming-button-${incoming.id}" type="button" className="btn btn-secondary" style="width: 100%">${incoming.name}</button>
            </li>
            `
            html += liHtml;
            incomingButton.html(html)
        })
        console.log(html)
    })
}

getAllIncoming();

//////////////////// Вкладка с информацией по Входящий документ/Incoming////////////////////////////////
async function addTabIncoming(idIncoming) {
    const url = '/ecm/api/incoming/' + idIncoming
    $.ajax({
        url: url,
        dataType: 'json',
        type: "GET",
    }).done((incoming) => {
        idTab = "nav-table-incoming"+incoming.id
        let tabButtonHtml = `
        <li class="nav-item">
             <a class="nav-link" id="nav-user-tab" data-bs-toggle="pill" href="#${idTab}">
                Входящий документ №${incoming.id}
                <button onclick="deleteTabById('${idTab}')" type="button" class="btn-close" aria-label="Close"></button>
             </a>
        </li>
        `
        let tabContentHtml = `
        <div class="tab-pane fade" id="${idTab}">
             Информация о Входящем документе ${incoming.name} №${incoming.id}
        </div>
        `
        printTab(idTab, tabButtonHtml, tabContentHtml)
    })
}