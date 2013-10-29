package rubtsov.documents.web.dto;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 29.10.13
 * Time: 14:50
 */
public interface EntityDto<E> {

    void saveToEntity(E entity);

    void loadFromEntity(E entity);

}
