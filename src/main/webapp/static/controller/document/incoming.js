/////////////////Получить список всех Входящий документ/Incoming ///////////////////////////
async function getAllIncoming() {
    const url = '/ecm/api/incoming'
    return $.ajax({
        url: url,
        dataType: 'json',
        type: "GET",
    })
}

//////////////////// Получить Входящий документ/Incoming по Id ////////////////////////////////
async function getIncomingById (idIncoming) {
    const url = '/ecm/api/incoming/' + idIncoming
   return  $.ajax({
        url: url,
        dataType: 'json',
        type: "GET",
    })
}

// /////////////////Удалить Входящий документ/Incoming по id
async function deleteIncomingById(idIncoming) {
    const url = '/ecm/api/incoming/' + idIncoming
    return $.ajax({
        url: url,
        type: 'DELETE'
    })
}

/////////////////Сохранить Входящий документ/Incoming ///////////
async function saveIncoming(incoming){
    const url = '/ecm/api/incoming'
    return $.ajax({
        url: url,
        type: "POST",
        dataType: 'json',
        headers: {
            "Content-Type": "application/json;charset=utf-8"
        },
        data: JSON.stringify(incoming)
    })
}