package com.example.document_flow.service.implement.staff.xml;

import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.repository.absraction.staff.OrganizationRepository;
import com.example.document_flow.repository.implement.staff.OrganizationRepositoryXmlImpl;
import com.example.document_flow.service.abstraction.staff.OrganizationService;

import java.util.List;
import java.util.Optional;

/**
 * Класс сервис для управления Organization
 *
 * @author Баратов Руслан
 */
public class OrganizationServiceXmlImpl implements OrganizationService {

    private static OrganizationServiceXmlImpl organizationService;

    private final OrganizationRepository repository = OrganizationRepositoryXmlImpl.getInstance();

    private OrganizationServiceXmlImpl() {
    }

    /**
     * @return синголтон обьект
     */
    public static OrganizationServiceXmlImpl getInstance() {
        if (organizationService == null) {
            organizationService = new OrganizationServiceXmlImpl();
        }
        return organizationService;
    }

    /**
     * Сохранить организацию
     *
     * @param object организация
     */
    @Override
    public void save(Organization object) throws SaveObjectException {
        repository.save(object);
    }

    /**
     * Сохранить список организаций
     *
     * @param objects список организаций
     */
    @Override
    public void saveAll(List<Organization> objects) throws SaveObjectException {
        repository.saveAll(objects);
    }

    /**
     * Получить все организации
     *
     * @return список организаций
     */
    @Override
    public List<Organization> getAll() {
        return repository.getAll();
    }

    /**
     * Найти объект класса <code>Organization</code> по id
     *
     * @param id id объекта класса <code>Organization</code>
     * @return найденный объект класса <code>Organization</code>
     */
    @Override
    public Optional<Organization> findById(long id) {
        return repository.findById(id);
    }

    /**
     * Удалить объект по id
     *
     * @param id - id объекта
     * @throws DeleteObjectException когда удаление объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void deleteById(long id) throws DeleteObjectException {
        repository.deleteById(id);
    }

    /**
     * Обновить данные объекта
     *
     * @param object объект с обновленными данными
     * @throws SaveObjectException когда изменение объекта терпит не удачу по какой-либо причине
     */
    @Override
    public void update(Organization object) throws SaveObjectException {
        repository.update(object);
    }

}
