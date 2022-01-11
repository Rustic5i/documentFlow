/////////////////// Получить список всех Поручений/Task ///////////////////////////
async function getAllTask() {
    const taskButton = $('#task-ul');
    console.log(taskButton)
    const url = '/ecm/api/task'
    $.ajax({
        url: url,
        dataType: 'json',
        type: "GET",
    }).done((response) => {
        console.log(response)
        let html = ''
        response.forEach(task => {
            let liHtml = `
             <li id="task-li-${task.id}" className="list-group-item">
                <button onclick="addTabTask(${task.id})" id="task-button-${task.id}" type="button" className="btn btn-secondary" style="width: 100%">${task.name}</button>
            </li>
            `
            html += liHtml;
            taskButton.html(html)
        })
        console.log(html)
    })
}

getAllTask();

//////////////////// Вкладка с информацией по Поручению/Task////////////////////////////////

//Хранит список кнопок для открытых вкладок
let tabButtonArray = new Map()
//Хранит список открытых вкладок
let tabContentArray = new Map()

const tabButton = $('#tabButton')
const tabContent = $('#myTabContent')

async function addTabTask(idTask) {
    const url = '/ecm/api/task/' + idTask
    $.ajax({
        url: url,
        dataType: 'json',
        type: "GET",
    }).done((task) => {
        let tabButtonHtml = `
        <li class="nav-item">
             <a class="nav-link" id="nav-user-tab" data-bs-toggle="pill" href="#nav-table${task.id}">
                Поручение №${task.id}
                <button onclick="deleteTabTask(${task.id})" type="button" class="btn-close" aria-label="Close"></button>
             </a>
        </li>
        `
        let tabContentHtml = `
        <div class="tab-pane fade" id="nav-table${task.id}">
             Информация о документе ${task.name} №${task.id}
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