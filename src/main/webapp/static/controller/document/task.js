/////////////////// Получить список всех Поручений/Task ///////////////////////////
function getAllTask() {
    return $.ajax({
        url: '/ecm/api/task',
        dataType: 'json',
        type: "GET",
    })
}

//////////////////// Получить Поручению/Task по Id ///////////////
async function getTaskById(idTask) {
    const url = '/ecm/api/task/' + idTask
    return $.ajax({
        url: url,
        dataType: 'json',
        type: "GET",
    })
}

// /////////////////Удалить Поручение по id /////////////
async function deleteTaskById(idTask) {
    const url = '/ecm/api/task/' + idTask
    return $.ajax({
        url: url,
        type: 'DELETE'
    })
}