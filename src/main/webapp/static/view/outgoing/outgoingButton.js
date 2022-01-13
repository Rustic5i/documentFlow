async function printButtonListOutgoing(){
    const outgoingButton = $('#outgoing-ul');
    getAllOutgoing().then((response) => {
        let html = ''
        response.forEach(outgoing => {
            let liHtml = `
             <li id="outgoing-li-${outgoing.id}" className="list-group-item">
                <button onclick="addTabOutgoing(${outgoing.id})" id="outgoing-button-${outgoing.id}" type="button" className="btn btn-secondary" style="width: 100%">${outgoing.name} №${outgoing.id}</button>
            </li>
            `
            html += liHtml;
            outgoingButton.html(html)
        })
        console.log(html)
    })
}
printButtonListOutgoing()