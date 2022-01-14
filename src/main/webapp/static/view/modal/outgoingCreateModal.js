////// Модальное окно для создание нового Исходящего документа/Outgoing////////////////////////////////

// Открыть модальное окно создание  Исходящего документа/Outgoing
function openModalCreateOutgoing(){
    printModalOutgoingSelectPersonList()
    const htmlModalCreateOutgoing = document.getElementById('modalCreateOutgoing')
    htmlModalCreateOutgoing.style.display = "block" // Запуск модального окна
}

/////Закрыть модальное окно
function closeModalCreateOutgoing(){
    const viewHtmlDelete = document.getElementById('modalCreateOutgoing')
    viewHtmlDelete.style.display = "none" // закрыть окно при нажатие кнопки крестик и на кнопку "Закрыть"
}

//// Сохранить Исходящий документ/Outgoing ///////////////
function createOutgoing(){
    let outgoing = {
        id: $('#formCreateOutgoingId').val(),
        name:$('#formCreateOutgoingName').val(),
        text:$('#formCreateOutgoingText').val(),
        registrationNumber:$('#formCreateOutgoingRegistrationNumber').val(),
        dateRegistration:$('#formCreateOutgoingDateRegistration').val(),
        author: {
            id: $('#formSelectCreateOutgoingAuthor').val(),
        },
        addressee:$('#formCreateOutgoingAddressee').val(),
        deliveryMethod:$('#formCreateOutgoingDeliveryMethod').val(),
    }
    saveOutgoing(outgoing).then(printButtonListOutgoing)
}

function printModalOutgoingSelectPersonList(){
    const outgoingSelect = $('#formSelectCreateOutgoingAuthor');
    getAllPerson().then((response)=>{
        let html = ''
        response.forEach(person => {
            let liHtml = `
                <option type="number" value="${person.id}">${person.name} №${person.id}</option>
            `
            html += liHtml;
            outgoingSelect.html(html)
        })
    })
}