//////////////////// Добавить Вкладку с информацией по Входящий документ/Incoming ////////////////////////////////
async function addTabIncoming(idIncoming) {
    getIncomingById(idIncoming).then((incoming) => {
        idTab = "nav-table-incoming" + incoming.id
        textModal = 'Вы действительно ходите удалить этот входящий документ?'
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
            <div class="btn-group" role="group" aria-label="Basic example">
                <button onclick="fillModelDeleteObject(${incoming.id},'${idTab}','${textModal}',closeTabIncoming)" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                        Удалить
                 </button>
                <button type="button" class="btn btn-primary">Сохранить</button>
             </div>
             Информация о Входящем документе ${incoming.name} №${incoming.id}
        </div>
        `
        printTab(idTab, tabButtonHtml, tabContentHtml)
    })
}

//Удаление Входящий документ/Incoming по id с последующем закрытием вкладки
async function closeTabIncoming(idIncoming, idTab) {
    deleteIncomingById(idIncoming).then(() => {
        deleteTabById(idTab)
    }).then(closeModalDelete).then(printButtonListIncoming)
}