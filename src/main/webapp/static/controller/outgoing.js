///////////////////Получить список всех Исходящий документов/Outgoing ///////////////////////////
async function getAllOutgoing() {
    const url = '/ecm/api/outgoing'
    return $.ajax({
        url: url,
        dataType: 'json',
        type: "GET",
    })
}

//////////////////// Получить Исходящий документ/Outgoing по Id ////////////////////////////////
async function getOutgoingById(idOutgoing) {
    const url = '/ecm/api/outgoing/' + idOutgoing
    return $.ajax({
        url: url,
        dataType: 'json',
        type: "GET",
    })
}

/////////////////////Удалить Исходящий документ по id ////////////////
const deleteOutgoingById = function deleteOutgoingById(idOutgoing) {
    const url = '/ecm/api/outgoing/' + idOutgoing
    return $.ajax({
        url: url,
        type: 'DELETE'
    })
}