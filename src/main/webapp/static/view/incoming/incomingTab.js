//////////////////// Добавить Вкладку с информацией по Входящий документ/Incoming ////////////////////////////////
async function addTabIncoming(idIncoming) {
    getIncomingById(idIncoming).then((incoming) => {
        idTab = "tabIncoming" + incoming.id
        idButtonTab = "tabButtonIncoming"+incoming.id
        textModal = 'Вы действительно ходите удалить этот входящий документ?'
        let tabButtonHtml = `
        <li class="nav-item">
             <a class="nav-link" id="${idButtonTab}" data-bs-toggle="pill" href="#${idTab}">
                Входящий документ №${incoming.id}
                <button onclick="deleteTabById('${idTab}','${idButtonTab}')" type="button" class="btn-close" aria-label="Close"></button>
             </a>
        </li>
        `
        let tabContentHtml = `
        <div class="tab-pane fade" id="${idTab}">
            <div class="btn-group" role="group" aria-label="Basic example">
                <button onclick="fillModelDeleteObject(${incoming.id},'${idTab}','${idButtonTab}','${textModal}',closeTabIncoming)" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                        Удалить
                 </button>
             </div>
             <form>
                            <div class="text-start">
                                <div>
                                    <label for="formIncomingId${incoming.id}"><b>ID</b></label>
                                    <input type="text" class="form-control" id="formIncomingId${incoming.id}" disabled/>
                                </div>
                                <div class="mb-3">
                                    <label for="formIncomingName${incoming.id}" class="form-label"><b>Имя документа</b></label>
                                    <input type="text" class="form-control" id="formIncomingName${incoming.id}" required>
                                </div>
                                <div class="mb-3">
                                    <label for="formIncomingText${incoming.id}" class="form-label"><b>Текст документа</b></label>
                                    <input  type="text" class="form-control" id="formIncomingText${incoming.id}" required>
                                </div>
                                <div class="mb-3">
                                    <label for="formIncomingRegistrationNumber${incoming.id}" class="form-label"><b>Регистрационный номер документа</b></label>
                                    <input type="number" class="form-control" id="formIncomingRegistrationNumber${incoming.id}" required>
                                </div>
                                <div class="mb-3">
                                    <label for="formIncomingDateRegistration${incoming.id}" class="form-label"><b>Дата регистрации документа</b></label>
                                    <input type="date" class="form-control" id="formIncomingDateRegistration${incoming.id}" disabled>
                                </div>
                                <div class="mb-3">
                                    <label for="formIncomingAuthor${incoming.id}"><b>Автор документа</b></label>
                                    <input type="text" class="form-control" id="formIncomingAuthor${incoming.id}" disabled/>
                                </div>
                                  <div class="mb-3">
                                    <label for="formIncomingSource${incoming.id}"><b>Отправитель</b></label>
                                    <input type="text" class="form-control" id="formIncomingSource${incoming.id}" disabled/>
                                </div>
                                <div class="mb-3">
                                    <label for="formIncomingAddressee${incoming.id}" class="form-label"><b>Адресат</b></label>
                                    <input type="text" class="form-control" id="formIncomingAddressee${incoming.id}" required>
                                </div>
                                 <div class="mb-3">
                                    <label for="formIncomingOutgoingNumber${incoming.id}"><b>Исходящий номер</b></label>
                                    <input type="text" class="form-control" id="formIncomingOutgoingNumber${incoming.id}" disabled/>
                                </div>
                                 <div class="mb-3">
                                    <label for="formIncomingOutgoingRegistrationDate${incoming.id}" class="form-label"><b>Исходящая дата регистрации</b></label>
                                    <input type="date" class="form-control" id="formIncomingOutgoingRegistrationDate${incoming.id}" disabled>
                                </div>
                                <div class="modal-footer">
                                    <div>
                                        <button onclick="updateUser()" type="submit" class="btn btn-primary">Cохранить</button>
                                    </div>
                                </div>
                            </div>
                        </form>
        </div>
        `
        fillFormEditIncoming(idIncoming)
        printTab(idTab, tabButtonHtml, tabContentHtml)
    })
}

//Удаление Входящий документ/Incoming по id с последующем закрытием вкладки
async function closeTabIncoming(idIncoming, idTab,idButtonTab) {
    deleteIncomingById(idIncoming).then(() => {
        deleteTabById(idTab,idButtonTab)
    }).then(closeModalDelete).then(printButtonListIncoming)
}

///Заполнение формы редактирование Поручения/Task ////////
async function fillFormEditIncoming(idIncoming) {
    getIncomingById(idIncoming).then(incoming => {
        $('#formIncomingId' + incoming.id).val(`${incoming.id}`)
        $('#formIncomingName' + incoming.id).val(`${incoming.name}`)
        $('#formIncomingText' + incoming.id).val(`${incoming.text}`)
        $('#formIncomingRegistrationNumber' + incoming.id).val(`${incoming.registrationNumber}`)
        $('#formIncomingDateRegistration' + incoming.id).val(`${incoming.dateRegistration}`)
        $('#formIncomingAuthor' + incoming.id).val(`${incoming.author.surname} ` + `${incoming.author.name}`)
        $('#formIncomingSource' + incoming.id).val(`${incoming.source.surname} ` + `${incoming.source.name}`)
        $('#formIncomingAddressee' + incoming.id).val(`${incoming.addressee}`)
        $('#formIncomingOutgoingNumber' + incoming.id).val(`${incoming.outgoingNumber}`)
        $('#formIncomingOutgoingRegistrationDate' + incoming.id).val(`${incoming.outgoingRegistrationDate}`)
    })
}