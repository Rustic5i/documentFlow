const tabButton = $('#tabButton')
const tabContent = $('#myTabContent')

function printTab(tabButtonId, tabButtonHtml, tabContentHtml) {
    /**
     * Проверяем есть ли уже такой элемент в дом-дерева,
     * если нету, то добавляем html элемент к дом-дерева.
     */
    if (!$("#"+tabButtonId).length) {
        tabButton.append(tabButtonHtml)
        tabContent.append(tabContentHtml)
    }
}

///Удалить вкладку по id
function deleteTabById(idTab, idButtonTab) {
    $("#"+idTab).remove()
    $("#"+idButtonTab).remove()
}