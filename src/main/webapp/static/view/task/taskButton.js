////////////// Вывести список всех Поручений/Task //////////////////////
async function printButtonListTask(){
    const taskButton = $('#task-ul');
    getAllTask().then((response) => {
        let html = ''
        response.forEach(task => {
            let liHtml = `
             <li id="task-li-${task.id}" className="list-group-item">
                <button onclick="addTabTask(${task.id})" id="task-button-${task.id}" type="button" className="btn btn-secondary" style="width: 100%">${task.name} №${task.id}</button>
            </li>
            `
            html += liHtml;
            taskButton.html(html)
        })
    })
}
printButtonListTask()
