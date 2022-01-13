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