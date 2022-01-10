///////////////////Получить список Person по ID Department///////////////////////////

async function getAllPersonByIdDepartment(idDepartment) {
    const personButton = $('#person-list'+idDepartment);
    console.log(personButton)
    const url = '/ecm/api/organization/department/'+idDepartment+'/person'
    $.ajax({
        url: url,
        dataType: 'json',
        type: "GET",
    }).done((response) => {
        console.log(response)
        let html = ''
        response.forEach(person => {
            let liHtml = `
            <div class="gy-5" style="margin-top: 10px" id="person${person.id}">
                                          <button class="cols-2 btn btn-success" >
                                                 ${person.name} ${person.surname}
                                           </button> 
                             </div>
            `
            html += liHtml;
            personButton.html(html)
        })
        console.log(html)
    })
}