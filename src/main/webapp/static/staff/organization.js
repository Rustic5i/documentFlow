///////////////////Получить список Всех Организаций ///////////////////////////
async function getAllOrganization() {
    const organizationButton = $('#organization-ul');
    console.log(organizationButton)
    const url = '/ecm/api/organization'
    $.ajax({
        url: url,
        dataType: 'json',
        type: "GET",
    }).done((response) => {
        console.log(response)
        let html = ''
        response.forEach(organization => {
            let liHtml = `
           <button onclick="getAllDepartmentByIdOrganization(${organization.id})" class="btn btn-primary" type="button"data-bs-toggle="collapse" data-bs-target="#department${organization.id}" style="width: 190px">
                                          ${organization.shortName}
                                        </button>
                                        <div class="collapse" id="department${organization.id}"style="width: 190px">
                                            <div id="id-department-div${organization.id}" class="card card-body">Name Department</div>
                                        </div>
            `
            html += liHtml;
            organizationButton.html(html)
        })
        console.log(html)
    })
}

getAllOrganization();