///////////////////Получить список Department по ID Organization ///////////////////////////

async function getAllDepartmentByIdOrganization(idOrganization) {
    const departmentButton = $('#id-department-div' + idOrganization);
    console.log(departmentButton)
    const url = '/ecm/api/organization/' + idOrganization + '/department'
    $.ajax({
        url: url,
        dataType: 'json',
        type: "GET",
    }).done((response) => {
        console.log(response)
        let html = ''
        response.forEach(department => {
            let liHtml = `
           <button onclick="getAllPersonByIdDepartment(${department.id})" class="btn btn-info"
            id="textNameOrg1" data-bs-toggle="collapse"data-bs-target="#person-list${department.id}" style="margin-top: 10px">
                                    ${department.shortName}</button>
                                    <div class="container" id="person-list${department.id}">
                                     
                                    </div>
            `
            html += liHtml;
            departmentButton.html(html)
        })
        console.log(html)
    })
}