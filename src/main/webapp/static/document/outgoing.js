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
                <button onclick="addTabOutgoing(${outgoing.id})" id="outgoing-button-${outgoing.id}" type="button" className="btn btn-secondary" style="width: 100%">${outgoing.name} №${outgoing.id}</button>
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
        idTab = "nav-table-outgoing" + outgoing.id
        textModal = 'Вы действительно ходите удалить этот Исходящий документ?'
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
          <div class="btn-group" role="group" aria-label="Basic example">
                <button onclick="fillModelDeleteObject(${outgoing.id},'${idTab}','${textModal}',deleteOutgoingById)" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                        Удалить
                 </button>
                <button type="button" class="btn btn-primary">Сохранить</button>
             </div>
             Информация об Исходящим документе ${outgoing.name} №${outgoing.id}
        </div>
        `
        printTab(idTab, tabButtonHtml, tabContentHtml)
    })
}

//Удалить Исходящий документ по id
const deleteOutgoingById = function deleteOutgoingById(idOutgoing, idTab) {
    const url = '/ecm/api/outgoing/' + idOutgoing
    const method = {
        method: 'DELETE',
        headers: {
            "Content-Type": "application/json;charset=utf-8"
        }
    }
    fetch(url, method).then(() => {
        deleteTabById(idTab)
    }).then(closeModalDelete).then(getAllOutgoing)
}