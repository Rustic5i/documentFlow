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

/////////////////Сохранить Поручение ///////////
async function saveTask(task){
    const url = '/ecm/api/task'
    return $.ajax({
        url: url,
        type: "POST",
        dataType: 'json',
        headers: {
            "Content-Type": "application/json;charset=utf-8"
        },
        data: JSON.stringify(task)
    })
}