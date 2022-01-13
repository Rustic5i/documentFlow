//////////////////// Добавить Вкладку с информацией по Поручению/Task///////////////
async function addTabTask(idTask) {
    getTaskById(idTask).then((task) => {
        idTab = "nav-table-task" + task.id
        textModal = 'Вы действительно ходите удалить это поручение?'
        let tabButtonHtml = `
        <li class="nav-item">
             <a onclick="fillFormEditTask(${task.id})" class="nav-link" id="nav-user-tab" data-bs-toggle="pill" href="#${idTab}">
                Поручение №${task.id}
                <button onclick="deleteTabById('${idTab}')" type="button" class="btn-close" aria-label="Close"></button>
             </a>
        </li>
        `
        let tabContentHtml = `
        <div class="tab-pane fade" id="${idTab}">
             <div class="btn-group" role="group" aria-label="Basic example">
                <button onclick="fillModelDeleteObject(${task.id},'${idTab}','${textModal}',closeTabTask)" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                        Удалить
                 </button>
                <button type="button" class="btn btn-primary">Сохранить</button>
             </div>
             Информация о документе №${task.id}
               <form>
                            <div class="text-start">
                                <div>
                                    <label for="id"><b>ID</b></label>
                                    <input th:name="id" th:type="text" class="form-control" id="id" disabled/>
                                </div>
                                <div class="mb-3">
                                    <label for="UpdateName" class="form-label"><b>Имя документа</b></label>
                                    <input th:name="name" type="text" class="form-control" id="UpdateName" required>
                                </div>
                                <div class="mb-3">
                                    <label for="UpdateText" class="form-label"><b>Текст документа</b></label>
                                    <input th:name="text" type="text" class="form-control" id="UpdateText" required>
                                </div>
                                <div class="mb-3">
                                    <label for="UpdateRegistrationNumber" class="form-label"><b>Регистрационный номер документа</b></label>
                                    <input th:name="registrationNumber" type="number" class="form-control" id="UpdateRegistrationNumber" required>
                                </div>
                                <div class="mb-3">
                                    <label for="DateRegistration" class="form-label"><b>Дата регистрации документа</b></label>
                                    <input th:name="dateRegistration" type="date" class="form-control" id="DateRegistration" disabled>
                                </div>
                                <div class="mb-3">
                                    <label for="Author"><b>Автор документа</b></label>
                                    <input th:name="author" th:type="text" class="form-control" id="Author" disabled/>
                                </div>
                                 <div class="mb-3">
                                    <label for="DateOfIssue" class="form-label"><b>Дата выдачи поручения</b></label>
                                    <input th:name="dateOfIssue" type="date" class="form-control" id="DateOfIssue" disabled>
                                </div>
                                <div class="mb-3">
                                    <label for="TermOfExecution" class="form-label"><b>Срок исполнения поручения</b></label>
                                    <input th:name="termOfExecution" type="date" class="form-control" id="TermOfExecution" disabled>
                                </div>
                                <div class="mb-3">
                                    <label for="ResponsibleExecutor"><b>Ответственный исполнитель</b></label>
                                    <input th:name="responsibleExecutor" th:type="text" class="form-control" id="ResponsibleExecutor" disabled/>
                                </div>
                                <div class="mb-3">
                                    <label for="UpdateSignOfControl" class="form-label"><b>Признак контрольности</b></label>
                                    <input th:name="signOfControl" type="text" class="form-control" id="UpdateSignOfControl" required>
                                </div>
                                <div class="mb-3">
                                    <label for="OrderController"><b>Контролер поручения</b></label>
                                    <input th:name="orderController" th:type="text" class="form-control" id="OrderController" disabled/>
                                </div>
                                <div>
                                    <div><b>Role</b></div>
                                    <select id="UpdateListRoles" class="form-select" name="listRoles[]" multiple aria-label="multiple select example" required>
                                        <option value="ROLE_ADMIN">Воробьев </option>
                                        <option value="ROLE_USER">Петрово </option>
                                    </select>
                                </div>
                                <div class="modal-footer">
                                    <div>
                                        <button onclick="closeModalEdit()" type="button"
                                                class="btn btn-secondary">Close
                                        </button>
                                    </div>
                                    <div>
                                        <button onclick="updateUser()" type="submit" class="btn btn-primary">Edit</button>
                                    </div>
                                </div>
                            </div>
                        </form>
        </div>
        `
        printTab(idTab, tabButtonHtml, tabContentHtml)
    })
}

//Удаление Поручение по id с последующем закрытием вкладки
async function closeTabTask(idTask, idTab) {
    deleteTaskById(idTask).then(() => {
        deleteTabById(idTab)
    }).then(closeModalDelete)
        .then(printButtonListTask)
}

///Заполнение формы редактирование Поручения/Task ////////
async function fillFormEditTask(idTask){
    getTaskById(idTask).then(task => {
        console.log(task)
        $('#id').val(`${task.id}`)
        $('#UpdateName').val(`${task.name}`)
        $('#Text').val(`${task.name}`)
        $('#DateRegistration').val(`${task.dateRegistration}`)
    })
}