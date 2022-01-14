//////////////////// Добавить Вкладку с информацией по Исходящий документ/Outgoing////////////////////////////////
async function addTabOutgoing(idOutgoing) {
    getOutgoingById(idOutgoing).then((outgoing) => {
        idTab = "tabOutgoing" + outgoing.id
        idButtonTab = "tabButtonOutgoing" + outgoing.id
        textModal = 'Вы действительно ходите удалить этот Исходящий документ?'
        let tabButtonHtml = `
        <li class="nav-item">
             <a class="nav-link" id="${idButtonTab}" data-bs-toggle="pill" href="#${idTab}">
                Исходящий документ №${outgoing.id}
                <button onclick="deleteTabById('${idTab}','${idButtonTab}')" type="button" class="btn-close" aria-label="Close"></button>
             </a>
        </li>
        `
        let tabContentHtml = `
        <div class="tab-pane fade" id="${idTab}"> 
           <div class="bg-white w-100 p-3">
             <div class="col-md-5 mx-auto"> 
              <form>
                <div class="text-start">
                    <div>
                        <label for="formOutgoingId${outgoing.id}"><b>ID</b></label>
                        <input type="text" class="form-control" id="formOutgoingId${outgoing.id}" disabled/>
                    </div>
                    <div class="mb-3">
                        <label for="formOutgoingName${outgoing.id}" class="form-label"><b>Имя документа</b></label>
                        <input type="text" class="form-control" id="formOutgoingName${outgoing.id}" required>
                    </div>
                    <div class="mb-3">
                        <label for="formOutgoingText${outgoing.id}" class="form-label"><b>Текст документа</b></label>
                        <input  type="text" class="form-control" id="formOutgoingText${outgoing.id}" required>
                    </div>
                    <div class="mb-3">
                        <label for="formOutgoingRegistrationNumber${outgoing.id}" class="form-label"><b>Регистрационный номер документа</b></label>
                        <input type="number" class="form-control" id="formOutgoingRegistrationNumber${outgoing.id}" required>
                    </div>
                    <div class="mb-3">
                        <label for="formOutgoingDateRegistration${outgoing.id}" class="form-label"><b>Дата регистрации документа</b></label>
                        <input type="date" class="form-control" id="formOutgoingDateRegistration${outgoing.id}" disabled>
                    </div>
                    <div class="mb-3">
                        <label for="formOutgoingAuthor${outgoing.id}"><b>Автор документа</b></label>
                        <input type="text" class="form-control" id="formOutgoingAuthor${outgoing.id}" disabled/>
                    </div>
            
                    <div class="mb-3">
                        <label for="formOutgoingAddressee${outgoing.id}"><b>Адресат</b></label>
                        <input type="text" class="form-control" id="formOutgoingAddressee${outgoing.id}" disabled/>
                    </div>
                    <div class="mb-3">
                        <label for="formOutgoingDeliveryMethod${outgoing.id}" class="form-label"><b>Способ доставки</b></label>
                        <input type="text" class="form-control" id="formOutgoingDeliveryMethod${outgoing.id}" required>
                    </div>
                    <div class="modal-footer">
                        <div>
                            <button onclick="updateUser()" type="submit" class="btn btn-success">Cохранить</button>
                        </div>
                       <div>
                            <button onclick="fillModelDeleteObject(${outgoing.id},'${idTab}','${idButtonTab}','${textModal}',closeTabOutgoing)" 
                              type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal">Удалить
                            </button>
                       </div>
                    </div>
                 </div>
              </form>
           </div>
    </div>
  </div>
        `
        fillFormEditOutgoing(idOutgoing)
        printTab(idTab, tabButtonHtml, tabContentHtml)
    })
}

//Удаление  Исходящий документ/Outgoing по id с последующем закрытием вкладки
async function closeTabOutgoing(idOutgoing, idTab, idButtonTab) {
    deleteOutgoingById(idOutgoing).then(() => {
        deleteTabById(idTab, idButtonTab)
    }).then(closeModalDelete).then(printButtonListOutgoing)
}

///Заполнение формы редактирование Исходящий документ/Outgoing ////////
async function fillFormEditOutgoing(idOutgoing) {
    getOutgoingById(idOutgoing).then(outgoing => {
        $('#formOutgoingId' + outgoing.id).val(`${outgoing.id}`)
        $('#formOutgoingName' + outgoing.id).val(`${outgoing.name}`)
        $('#formOutgoingText' + outgoing.id).val(`${outgoing.text}`)
        $('#formOutgoingRegistrationNumber' + outgoing.id).val(`${outgoing.registrationNumber}`)
        $('#formOutgoingDateRegistration' + outgoing.id).val(`${outgoing.dateRegistration}`)
        $('#formOutgoingAuthor' + outgoing.id).val(`${outgoing.author.surname} ` + `${outgoing.author.name}`)
        $('#formOutgoingAddressee' + outgoing.id).val(`${outgoing.addressee}`)
        $('#formOutgoingDeliveryMethod' + outgoing.id).val(`${outgoing.deliveryMethod}`)
    })
}