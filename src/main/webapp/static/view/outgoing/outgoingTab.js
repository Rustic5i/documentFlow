//////////////////// Добавить Вкладку с информацией по Исходящий документ/Outgoing////////////////////////////////
async function addTabOutgoing(idOutgoing){
    getOutgoingById(idOutgoing).then((outgoing) => {
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
                <button onclick="fillModelDeleteObject(${outgoing.id},'${idTab}','${textModal}',closeTabOutgoing)" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
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

//Удаление  Исходящий документ/Outgoing по id с последующем закрытием вкладки
async function closeTabOutgoing(idOutgoing, idTab){
    deleteOutgoingById(idOutgoing).then(() => {
        deleteTabById(idTab)
    }).then(closeModalDelete).then(printButtonListOutgoing)
}