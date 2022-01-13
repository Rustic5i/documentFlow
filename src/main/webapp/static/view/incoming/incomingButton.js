async function printButtonListIncoming(){
    const incomingButton = $('#incoming-ul');
    getAllIncoming().then((response) => {
        let html = ''
        response.forEach(incoming => {
            let liHtml = `
             <li id="incoming-li-${incoming.id}" className="list-group-item">
                <button onclick="addTabIncoming(${incoming.id})" id="incoming-button-${incoming.id}" type="button" className="btn btn-secondary" style="width: 100%">${incoming.name} №${incoming.id}</button>
            </li>
            `
            html += liHtml;
            incomingButton.html(html)
        })
        console.log(html)
    })
}
printButtonListIncoming()