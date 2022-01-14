// Управляет модальным окном delete
//Заполнить модальное окно информацией
function fillModelDeleteObject(idTask, idTab,idButtonTab,text,functionDeleteObjectById) {
    $('#TextModal').val(text)
    //////Вешаем событие при нажатия на кнопку "Да" / подтверждения на удаление Поручения
    document.getElementById('buttonYes').onclick = function () {
        functionDeleteObjectById(idTask,idTab,idButtonTab)
    }
}

///Закрыть модальное окно на нажатие кнопки "Да" после удаление объекта
function closeModalDelete(){
    const viewHtmlDelete = document.getElementById('exampleModal')
    viewHtmlDelete.style.display = "none" // закрыть окно при нажатие на кнопки "да" / подтверждения удаления
}