////// Модальное окно для создание нового Поручений/Task////////////////////////////////

// Открыть модальное окно создание Поручений/Task
function openModalCreateTask(){
    printModalTaskSelectPersonList()
    const htmlModalCreateTask = document.getElementById('modalCreateTask')
    htmlModalCreateTask.style.display = "block" // Запуск модального окна
}

/////Закрыть модальное окно
function closeModalCreateTask(){
    const viewHtmlDelete = document.getElementById('modalCreateTask')
    viewHtmlDelete.style.display = "none" // закрыть окно при нажатие кнопки крестик и на кнопку "Закрыть"
}

//// Сохранить Поручений/Task ///////////////
function createTask(){
    let task = {
        id: $('#formCreateTaskId').val(),
        name:$('#formCreateTaskName').val(),
        text:$('#formCreateTaskText').val(),
        registrationNumber:$('#formCreateTaskRegistrationNumber').val(),
        dateRegistration:$('#formCreateTaskDateRegistration').val(), ////////////Остановился тут !!!!!!!!!!!!!!!!!!!!!
        author: {
            id: $('#formSelectCreateTaskAuthor').val(),
        },
        dateOfIssue:$('#formCreateTaskDateOfIssue').val(),
        termOfExecution:$('#formCreateTaskTermOfExecution').val(),
        responsibleExecutor: {
            id: $('#formSelectCreateTaskResponsibleExecutor').val(),
        },
        signOfControl:$('#formCreateTaskSignOfControl').val(),
        orderController: {
            id: $('#formSelectCreateTaskOrderController').val(),
        },
    }
    saveTask(task).then(printButtonListTask)
}

///////Выводит список всех Работников/Person в форме в разделе <select> //////
function printModalTaskSelectPersonList(){
    const taskSelectAuthor = $('#formSelectCreateTaskAuthor')
    const taskSelectResponsibleExecutor = $('#formSelectCreateTaskResponsibleExecutor')
    const taskSelectOrderController = $('#formSelectCreateTaskOrderController')
    getAllPerson().then((response)=>{
        let html = ''
        response.forEach(person => {
            let liHtml = `
                <option type="number" value="${person.id}">${person.name} №${person.id}</option>
            `
            html += liHtml;
            taskSelectAuthor.html(html)
            taskSelectResponsibleExecutor.html(html)
            taskSelectOrderController.html(html)
        })
    })
}