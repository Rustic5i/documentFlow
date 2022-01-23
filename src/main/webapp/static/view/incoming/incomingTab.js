//////////////////// Добавить Вкладку с информацией по Входящий документ/Incoming ////////////////////////////////
async function addTabIncoming(idIncoming) {
    const incoming = await getIncomingById(idIncoming)
    await tabIncoming(incoming)
    await printTab(idTab, tabButtonHtml, tabContentHtml)
    await printModalEditIncomingSelectPersonList(incoming.id)
    await fillFormEditIncoming(incoming)
}

///////Выводит список всех Работников/Person в форме в разделе <select> //////
async function printModalEditIncomingSelectPersonList(idIncoming) {
    const outgoingSelectAuthor = $('#formEditSelectIncomingAuthor' + idIncoming)
    const outgoingSelectSource = $('#formEditSelectIncomingSource' + idIncoming)
    const idSelectOptionAuthor = "formSelectOptionIncomingAuthorId"
    const idSelectOptionSource = "formSelectOptionIncomingSourceId"
    const response = await getAllPerson()
    response.forEach((person) => {
        outgoingSelectAuthor.append( `
                <option id="${idSelectOptionAuthor}${person.id}" type="number" value="${person.id}">${person.name} №${person.id}</option>
            `)
        outgoingSelectSource.append( `
                <option id="${idSelectOptionSource}${person.id}" type="number" value="${person.id}">${person.name} №${person.id}</option>
            `)
    })
}

//Удаление Входящий документ/Incoming по id с последующем закрытием вкладки
async function closeTabIncoming(idIncoming, idTab, idButtonTab) {
    deleteIncomingById(idIncoming).then(() => {
        deleteTabById(idTab, idButtonTab)
    }).then(closeModalDelete).then(printButtonListIncoming)
}

///Заполнение формы редактирование Входящий документ/Incoming ////////
async function fillFormEditIncoming(incoming) {
    $('#formEditIncomingId' + incoming.id).val(`${incoming.id}`)
    $('#formEditIncomingName' + incoming.id).val(`${incoming.name}`)
    $('#formEditIncomingText' + incoming.id).val(`${incoming.text}`)
    $('#formEditIncomingRegistrationNumber' + incoming.id).val(`${incoming.registrationNumber}`)
    $('#formEditIncomingDateRegistration' + incoming.id).val(`${incoming.dateRegistration}`)
    document.getElementById('formSelectOptionIncomingAuthorId' + incoming.author.id).setAttribute("selected", "selected");
    document.getElementById('formSelectOptionIncomingSourceId' + incoming.source.id).setAttribute("selected", "selected");
    $('#formEditIncomingAddressee' + incoming.id).val(`${incoming.addressee}`)
    $('#formEditIncomingOutgoingNumber' + incoming.id).val(`${incoming.outgoingNumber}`)
    $('#formEditIncomingOutgoingRegistrationDate' + incoming.id).val(`${incoming.outgoingRegistrationDate}`)
}

//// Редактировать Входящий документ/Incoming ///////////////
async function editIncoming(incomingId) {
    let incoming = {
        id: $('#formEditIncomingId'+incomingId).val(),
        name: $('#formEditIncomingName'+incomingId).val(),
        text: $('#formEditIncomingText'+incomingId).val(),
        registrationNumber: $('#formEditIncomingRegistrationNumber'+incomingId).val(),
        dateRegistration: $('#formEditIncomingDateRegistration'+incomingId).val(),
        author: {
            id: $('#formEditSelectIncomingAuthor'+incomingId).val(),
        },
        source: {
            id: $('#formEditSelectIncomingSource'+incomingId).val(),
        },
        addressee: $('#formEditIncomingAddressee'+incomingId).val(),
        outgoingNumber: $('#formEditIncomingOutgoingNumber'+incomingId).val(),
        outgoingRegistrationDate: $('#formEditIncomingOutgoingRegistrationDate'+incomingId).val(),
    }
    updateIncoming(incoming).then(printButtonListIncoming)
}

/////Панель с формой для документа Incoming ///////////////////////
async function tabIncoming(incoming) {
    idTab = "tabIncoming" + incoming.id
    idButtonTab = "tabButtonIncoming" + incoming.id
    textModal = 'Вы действительно ходите удалить этот входящий документ?'
    tabButtonHtml = `
        <li class="nav-item">
             <a class="nav-link" id="${idButtonTab}" data-bs-toggle="pill" href="#${idTab}">
                Входящий документ №${incoming.id}
                <button onclick="deleteTabById('${idTab}','${idButtonTab}')" type="button" class="btn-close" aria-label="Close"></button>
             </a>
        </li>
        `
    tabContentHtml = `
        <div class="tab-pane fade" id="${idTab}">
          <div class="bg-white w-100 p-3">
             <div class="col-md-5 mx-auto"> 
             <form>
                <div class="text-start">
                    <div>
                        <label for="formEditIncomingId${incoming.id}"><b>ID</b></label>
                        <input type="text" class="form-control" id="formEditIncomingId${incoming.id}" disabled/>
                    </div>
                    <div class="mb-3">
                        <label for="formEditIncomingName${incoming.id}" class="form-label"><b>Имя документа</b></label>
                        <input type="text" class="form-control" id="formEditIncomingName${incoming.id}" required>
                    </div>
                    <div class="mb-3">
                        <label for="formEditIncomingText${incoming.id}" class="form-label"><b>Текст документа</b></label>
                        <input  type="text" class="form-control" id="formEditIncomingText${incoming.id}" required>
                    </div>
                    <div class="mb-3">
                        <label for="formEditIncomingRegistrationNumber${incoming.id}" class="form-label"><b>Регистрационный номер документа</b></label>
                        <input type="number" class="form-control" id="formEditIncomingRegistrationNumber${incoming.id}" required>
                    </div>
                    <div class="mb-3">
                        <label for="formEditIncomingDateRegistration${incoming.id}" class="form-label"><b>Дата регистрации документа</b></label>
                        <input type="date" class="form-control" id="formEditIncomingDateRegistration${incoming.id}" disabled>
                    </div>
                     <div>
                        <div><b>Автор документа</b></div>
                        <select id="formEditSelectIncomingAuthor${incoming.id}" class="form-select" required>
                            <!--                                        <option value="ROLE_ADMIN">ADMIN</option>-->
                        </select>
                    </div>
                    <div>
                        <div><b>Отправитель документа</b></div>
                        <select id="formEditSelectIncomingSource${incoming.id}" class="form-select" required>
                            <!--                                        <option value="ROLE_ADMIN">ADMIN</option>-->
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="formEditIncomingAddressee${incoming.id}" class="form-label"><b>Адресат</b></label>
                        <input type="text" class="form-control" id="formEditIncomingAddressee${incoming.id}" required>
                    </div>
                     <div class="mb-3">
                        <label for="formEditIncomingOutgoingNumber${incoming.id}"><b>Исходящий номер</b></label>
                        <input type="text" class="form-control" id="formEditIncomingOutgoingNumber${incoming.id}" required/>
                    </div>
                     <div class="mb-3">
                        <label for="formEditIncomingOutgoingRegistrationDate${incoming.id}" class="form-label"><b>Исходящая дата регистрации</b></label>
                        <input type="date" class="form-control" id="formEditIncomingOutgoingRegistrationDate${incoming.id}" disabled>
                    </div>
                    <div class="modal-footer">
                        <div>
                            <button onclick="editIncoming(${incoming.id})" type="submit" class="btn btn-success">Cохранить</button>
                        </div>
                        <div>
                            <button onclick="fillModelDeleteObject(${incoming.id},'${idTab}','${idButtonTab}','${textModal}',closeTabIncoming)" type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                Удалить
                            </button>
                        </div>
                    </div>
                </div>
             </form>
          </div>
       </div>
    </div>  `
}