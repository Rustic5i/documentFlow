//////////////////// Добавить Вкладку с информацией по Поручению/Task///////////////
async function addTabTask(idTask) {
    getTaskById(idTask).then((task) => {
        idTab = "tabTask" + task.id
        idButtonTab = "tabButtonTask" + task.id
        textModal = 'Вы действительно ходите удалить это поручение?'
        let tabButtonHtml = `
        <li class="nav-item">
             <a class="nav-link" id="${idButtonTab}" data-bs-toggle="pill" href="#${idTab}">
                Поручение №${task.id}
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
                            <label for="formTaskId${task.id}"><b>ID</b></label>
                            <input type="text" class="form-control" id="formTaskId${task.id}" disabled/>
                        </div>
                        <div class="mb-3">
                            <label for="formTaskName${task.id}" class="form-label"><b>Имя документа</b></label>
                            <input type="text" class="form-control" id="formTaskName${task.id}" required>
                        </div>
                        <div class="mb-3">
                            <label for="formTaskText${task.id}" class="form-label"><b>Текст документа</b></label>
                            <input  type="text" class="form-control" id="formTaskText${task.id}" required>
                        </div>
                        <div class="mb-3">
                            <label for="formTaskRegistrationNumber${task.id}" class="form-label"><b>Регистрационный номер документа</b></label>
                            <input type="number" class="form-control" id="formTaskRegistrationNumber${task.id}" required>
                        </div>
                        <div class="mb-3">
                            <label for="formTaskDateRegistration${task.id}" class="form-label"><b>Дата регистрации документа</b></label>
                            <input type="date" class="form-control" id="formTaskDateRegistration${task.id}" disabled>
                        </div>
                        <div class="mb-3">
                            <label for="formTaskAuthor${task.id}"><b>Автор документа</b></label>
                            <input type="text" class="form-control" id="formTaskAuthor${task.id}" disabled/>
                        </div>
                         <div class="mb-3">
                            <label for="formTaskDateOfIssue${task.id}" class="form-label"><b>Дата выдачи поручения</b></label>
                            <input type="date" class="form-control" id="formTaskDateOfIssue${task.id}" disabled>
                        </div>
                        <div class="mb-3">
                            <label for="formTaskTermOfExecution${task.id}" class="form-label"><b>Срок исполнения поручения</b></label>
                            <input type="date" class="form-control" id="formTaskTermOfExecution${task.id}" disabled>
                        </div>
                        <div class="mb-3">
                            <label for="formTaskResponsibleExecutor${task.id}"><b>Ответственный исполнитель</b></label>
                            <input type="text" class="form-control" id="formTaskResponsibleExecutor${task.id}" disabled/>
                        </div>
                        <div class="mb-3">
                            <label for="formTaskSignOfControl${task.id}" class="form-label"><b>Признак контрольности</b></label>
                            <input type="text" class="form-control" id="formTaskSignOfControl${task.id}" required>
                        </div>
                        <div class="mb-3">
                            <label for="formTaskOrderController${task.id}"><b>Контролер поручения</b></label>
                            <input type="text" class="form-control" id="formTaskOrderController${task.id}" disabled/>
                        </div>
                        <div class="modal-footer">
                            <div>
                                <button onclick="updateUser()" type="submit" class="btn btn-success">Cохранить</button>
                            </div>
                            <div class="btn-group" role="group" aria-label="Basic example">
                               <button onclick="fillModelDeleteObject(${task.id},'${idTab}','${idButtonTab}','${textModal}',closeTabTask)" type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                 Удалить
                               </button>
                             </div>
                        </div>
                    </div>
                        </form>
                      </div>
                    </div>
        </div>
        `
        fillFormEditTask(idTask)
        printTab(idTab, tabButtonHtml, tabContentHtml)
    })
}

//Удаление Поручение по id с последующем закрытием вкладки
async function closeTabTask(idTask, idTab, idButtonTab) {
    deleteTaskById(idTask).then(() => {
        deleteTabById(idTab, idButtonTab)
    }).then(closeModalDelete)
        .then(printButtonListTask)
}

///Заполнение формы редактирование Поручения/Task ////////
async function fillFormEditTask(idTask) {
    getTaskById(idTask).then(task => {
        $('#formTaskId' + task.id).val(`${task.id}`)
        $('#formTaskName' + task.id).val(`${task.name}`)
        $('#formTaskText' + task.id).val(`${task.text}`)
        $('#formTaskRegistrationNumber' + task.id).val(`${task.registrationNumber}`)
        $('#formTaskDateRegistration' + task.id).val(`${task.dateRegistration}`)
        $('#formTaskAuthor' + task.id).val(`${task.author.surname} ` + `${task.author.name}`)
        $('#formTaskDateOfIssue' + task.id).val(`${task.dateOfIssue}`)
        $('#formTaskTermOfExecution' + task.id).val(`${task.termOfExecution}`)
        $('#formTaskResponsibleExecutor' + task.id).val(`${task.responsibleExecutor.surname} ` + `${task.responsibleExecutor.name}`)
        $('#formTaskSignOfControl' + task.id).val(`${task.signOfControl}`)
        $('#formTaskOrderController' + task.id).val(`${task.orderController.surname } `+`${task.orderController.name }`)
    })
}