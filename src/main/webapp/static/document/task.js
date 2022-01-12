/////////////////// Получить список всех Поручений/Task ///////////////////////////
async function getAllTask() {
    const taskButton = $('#task-ul');
    const url = '/ecm/api/task'
    $.ajax({
        url: url,
        dataType: 'json',
        type: "GET",
    }).done((response) => {
        console.log(response)
        let html = ''
        response.forEach(task => {
            let liHtml = `
             <li id="task-li-${task.id}" className="list-group-item">
                <button onclick="addTabTask(${task.id})" id="task-button-${task.id}" type="button" className="btn btn-secondary" style="width: 100%">${task.name} №${task.id}</button>
            </li>
            `
            html += liHtml;
            taskButton.html(html)
        })
        console.log(html)
    })
}

getAllTask();

//////////////////// Вкладка с информацией по Поручению/Task////////////////////////////////
async function addTabTask(idTask) {
    const url = '/ecm/api/task/' + idTask
    $.ajax({
        url: url,
        dataType: 'json',
        type: "GET",
    }).done((task) => {
        idTab = "nav-table-task" + task.id
        textModal = 'Вы действительно ходите удалить это поручение?'
        let tabButtonHtml = `
        <li class="nav-item">
             <a class="nav-link" id="nav-user-tab" data-bs-toggle="pill" href="#${idTab}">
                Поручение №${task.id}
                <button onclick="deleteTabById('${idTab}')" type="button" class="btn-close" aria-label="Close"></button>
             </a>
        </li>
        `
        let tabContentHtml = `
        <div class="tab-pane fade" id="${idTab}">
             <div class="btn-group" role="group" aria-label="Basic example">
                <button onclick="fillModelDeleteObject('${textModal}',deleteTaskById(${task.id},'${idTab}'))" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                        Удалить
                 </button>
                <button type="button" class="btn btn-primary">Сохранить</button>
             </div>
             Информация о документе №${task.id}
        </div>
        `
        printTab(idTab, tabButtonHtml, tabContentHtml)
    })
}

//Удалить Поручение по id
function deleteTaskById(idTask, idTab) {
    const url = '/ecm/api/task/' + idTask
    const method = {
        method: 'DELETE',
        headers: {
            "Content-Type": "application/json;charset=utf-8"
        }
    }
    fetch(url, method).then(() => {
        deleteTabById(idTab)
    }).then(closeModalDelete).then(getAllTask)
}

