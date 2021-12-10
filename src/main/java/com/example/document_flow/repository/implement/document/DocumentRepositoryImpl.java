package com.example.document_flow.repository.implement.document;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.DocumentExistsException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.repository.absraction.document.DocumentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Репозиторий по хранения всех созданных документов
 *
 * @author Баратов Руслан
 */
public class DocumentRepositoryImpl implements DocumentRepository {

    private static DocumentRepositoryImpl implDocumentRepository;

    private DocumentRepositoryImpl() {
    }

    /**
     * Хранит все созданные документы.
     * Где <code>Long</code> это ключ, хранит в себе уникальный регистрационный номер документа
     * <code>Document</code> хранит созданный документ
     */
    private static final Map<Long, Document> documentMap = new HashMap<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentRepositoryImpl.class.getName());

    private final List<Document> documentList = new ArrayList<>() {
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            StringBuilder author = new StringBuilder();
            forEach((value) -> {
                if (!(value.getAuthor().toString().equals(author.toString()))) {
                    author.delete(0, author.length());
                    author.append(value.getAuthor());
                    sb.append(value.getAuthor()).append(":").append("\n");
                    sb.append(" * ").append(value).append("\n");
                } else {
                    sb.append(" * ").append(value).append("\n");
                }
            });
            return sb.toString();
        }
    };

    /**
     * Метод для регистрации созданных документов
     *
     * @param document документ для регистрации
     * @throws DocumentExistsException если документ с таким регистрационным номером уже был создан ранее
     */
    public void save(Document document) throws DocumentExistsException {
        Long registrationNumber = document.getRegistrationNumber();
        containsRegistrationNumber(registrationNumber);
        documentMap.put(registrationNumber, document);
    }

    /**
     * Метод для регистрации созданных документов
     *
     * @param document список документов для регистрации
     */
    public void saveAll(List<Document> document) {
        for (Document doc : document) {
            Long registrationNumber = doc.getRegistrationNumber();
            try {
                containsRegistrationNumber(registrationNumber);
            } catch (DocumentExistsException e) {
                LOGGER.warn("Exception ", e);
            }
            documentMap.put(registrationNumber, doc);
        }
    }

    /**
     * @return Получить список всех документов
     */
    public List<Document> getAll() {
        documentList.addAll(documentMap.values().stream().toList());
        Collections.sort(documentList);
        return documentList;
    }

    /**
     * Найти объект по id
     *
     * @param id id объекта
     * @return найденный объект
     */
    @Override
    public Optional<Document> findById(long id) {
        return Optional.of((Document) getAll().stream().filter(document -> document.getId() == 0));
    }

    /**
     * Удалить объект по id
     *
     * @param id - id объекта
     * @throws DeleteObjectException когда удаление объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void deleteById(long id) throws DeleteObjectException {
        try {
            documentList.remove(Objects.requireNonNull(findById(id).get()));
        } catch (NullPointerException e) {
            throw new DeleteObjectException("Ошибка при попытка удаление Document с id" + id);
        }
    }

    /**
     * Обновить данные объекта
     *
     * @param object объект с обновленными данными
     * @throws SaveObjectException когда изменение объекта терпит не удачу по какой-либо причине
     */
    @Override
    public void update(Document object) throws SaveObjectException {
        try {
            Document document = Objects.requireNonNull(findById(object.getId()).get());
            documentList.set(documentList.indexOf(document), object);
        } catch (NullPointerException e) {
            throw new SaveObjectException("Ошибка при попытки обновить Document с id " + object.getId());
        }
    }

    /**
     * Выполняет поиск документов по id автора
     *
     * @param id id работника
     * @return перечень документов, созданных автором с указанным id
     */
    public List<Document> getDocumentByIdAuthor(long id) {
        return getAll().stream().filter(document -> document.getAuthor().getId() == id).toList();
    }

    /**
     * Получить документ по регистрационному номеру
     *
     * @param registrationNumber регистрационный номер документа
     * @return найденный документ
     */
    public Document getDocumentByRegNumber(Long registrationNumber) {
        return documentMap.get(registrationNumber);
    }

    /**
     * Выбрасывает исключение DocumentExistsException,
     * если в DocumentRepositoryImpl уже храниться документ с таким регистрационным номером.
     *
     * @param registrationNumber регистрационный номер документа
     * @throws DocumentExistsException если документ с таким регистрационным номером уже был создан ранее
     */
    public void containsRegistrationNumber(Long registrationNumber) throws DocumentExistsException {
        if (documentMap.containsKey(registrationNumber)) {
            throw new DocumentExistsException("Document с регистрационным номер " + registrationNumber + " уже существует ");
        }
    }

    /**
     * @return синголтон обьект
     */
    public static DocumentRepositoryImpl getInstance() {
        if (implDocumentRepository == null) {
            implDocumentRepository = new DocumentRepositoryImpl();
        }
        return implDocumentRepository;
    }
}
