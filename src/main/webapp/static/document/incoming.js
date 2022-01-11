///////////////////Получить список всех Входящий документ/Incoming ///////////////////////////
async function getAllIncoming() {
    const incomingButton = $('#incoming-ul');
    console.log(incomingButton)
    const url = '/ecm/api/incoming'
    $.ajax({
        url: url,
        dataType: 'json',
        type: "GET",
    }).done((response) => {
        console.log(response)
        let html = ''
        response.forEach(incoming => {
            let liHtml = `
             <li id="incoming-li-${incoming.id}" className="list-group-item">
                <button id="incoming-button-${incoming.id}" type="button" className="btn btn-secondary" style="width: 100%">${incoming.name}</button>
            </li>
            `
            html += liHtml;
            incomingButton.html(html)
        })
        console.log(html)
    })
}

getAllIncoming();

//////////////////// Вкладка с информацией по Входящий документ/Incoming////////////////////////////////

//Хранит список кнопок для открытых вкладок
let tabButtonArray = new Map()
//Хранит список открытых вкладок
let tabContentArray = new Map()

const tabButton = $('#tabButton')
const tabContent = $('#myTabContent')

async function addTabIncoming(idIncoming) {
    const url = '/ecm/api/incoming"' + idIncoming
    $.ajax({
        url: url,
        dataType: 'json',
        type: "GET",
    }).done((incoming) => {
        let tabButtonHtml = `
        <li class="nav-item">
             <a class="nav-link" id="nav-user-tab" data-bs-toggle="pill" href="#nav-table${incoming.id}">
                Входящий документ №${incoming.id}
                <button onclick="deleteTabTask(${incoming.id})" type="button" class="btn-close" aria-label="Close"></button>
             </a>
        </li>
        `
        let tabContentHtml = `
        <div class="tab-pane fade" id="nav-table${incoming.id}">
             Информация о документе ${incoming.name} №${incoming.id}
        </div>
        `
        printTab(task.id, tabButtonHtml, tabContentHtml)
    })
}

function printTab(tabButtonId, tabButtonHtml, tabContentHtml) {
    if (!tabButtonArray.has(tabButtonId)) {
        tabButtonArray.set(tabButtonId, tabButtonHtml)
        tabContentArray.set(tabButtonId, tabContentHtml)
        tabButtonForEach()
        tabContentForEach()
    }
}

function deleteTabTask(idTabTask) {
    tabButtonArray.delete(idTabTask)
    if (tabButtonArray.size == 0) {
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