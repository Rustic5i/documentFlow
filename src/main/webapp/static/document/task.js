///////////////////Получить список всех Поручений/Task ///////////////////////////
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
                <button id="task-button-${task.id}" type="button" className="btn btn-secondary" style="width: 100%">${task.name}</button>
            </li>
            `
            html += liHtml;
            taskButton.html(html)
        })
        console.log(html)
    })
}

getAllTask();