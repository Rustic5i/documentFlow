//Хранит список кнопок для открытых вкладок
let tabButtonArray = new Map()
//Хранит список открытых вкладок
let tabContentArray = new Map()

const tabButton = $('#tabButton')
const tabContent = $('#myTabContent')


function printTab(tabButtonId, tabButtonHtml, tabContentHtml) {
    if (!tabButtonArray.has(tabButtonId)) {
        tabButtonArray.set(tabButtonId, tabButtonHtml)
        tabContentArray.set(tabButtonId, tabContentHtml)
        tabButtonForEach()
        tabContentForEach()
    }
}

///Удалить вкладку по id
function deleteTabById(idTab) {
    tabButtonArray.delete(idTab)
    tabContentArray.delete(idTab)
    if (tabButtonArray.size === 0) {
        tabButton.html('')
        tabContent.html('')
    } else {
        tabButtonForEach()
        tabContentForEach()
    }
}

function tabButtonForEach() {
    let concatenationHtmlTabButton = ''
    tabButtonArray.forEach(tabButtonHtml => {
        concatenationHtmlTabButton += tabButtonHtml
        tabButton.html(concatenationHtmlTabButton)
    })
}

function tabContentForEach() {
    let concatenationHtmlTabContent = ''
    tabContentArray.forEach(tabContentHtml => {
        concatenationHtmlTabContent += tabContentHtml
        tabContent.html(concatenationHtmlTabContent)
    })
}