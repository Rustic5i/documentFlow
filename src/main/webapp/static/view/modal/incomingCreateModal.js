////// Модальное окно для создание нового Входящий документ/Incoming////////////////////////////////

// Открыть модальное окно создание Входящий документ/Incoming
function openModalCreateIncoming(){
    printModalIncomingSelectPersonList()
    const htmlModalCreateIncoming = document.getElementById('modalCreateIncoming')
    htmlModalCreateIncoming.style.display = "block" // Запуск модального окна
}

/////Закрыть модальное окно
function closeModalCreateIncoming(){
    const viewHtmlDelete = document.getElementById('modalCreateIncoming')
    viewHtmlDelete.style.display = "none" // закрыть окно при нажатие кнопки крестик и на кнопку "Закрыть"
}

//// Сохранить Входящий документ/Incoming ///////////////
function createIncoming(){
    let incoming = {
        id: $('#formCreateIncomingId').val(),
        name:$('#formCreateIncomingName').val(),
        text:$('#formCreateIncomingText').val(),
        registrationNumber:$('#formCreateIncomingRegistrationNumber').val(),
        dateRegistration:$('#formCreateIncomingDateRegistration').val(), ////////////Остановился тут !!!!!!!!!!!!!!!!!!!!!
        author: {
            id: $('#formSelectCreateIncomingAuthor').val(),
        },
        source: {
            id: $('#formSelectCreateIncomingSource').val(),
        },
        addressee:$('#formCreateIncomingAddressee').val(),
        outgoingNumber:$('#formCreateIncomingOutgoingNumber').val(),
        outgoingRegistrationDate:$('#formCreateIncomingOutgoingRegistrationDate').val(),
    }
    saveIncoming(incoming).then(printButtonListIncoming)
}

///////Выводит список всех Работников/Person в форме в разделе <select> //////
function printModalIncomingSelectPersonList(){
    const outgoingSelectAuthor = $('#formSelectCreateIncomingAuthor');
    const outgoingSelectSource = $('#formSelectCreateIncomingSource')
    getAllPerson().then((response)=>{
        let html = ''
        response.forEach(person => {
            let liHtml = `
                <option type="number" value="${person.id}">${person.name} №${person.id}</option>
            `
            html += liHtml;
            outgoingSelectAuthor.html(html)
            outgoingSelectSource.html(html)
        })
    })
}