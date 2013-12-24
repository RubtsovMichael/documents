package rubtsov.documents.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import rubtsov.documents.data.model.entity.Correspondent;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 27.09.13
 * Time: 17:40
 */
public interface CorrespondentsRepository extends CrudRepository<Correspondent, Long> {

    @Query("select c from Correspondent c where c.caseFolder.caseId = :caseId")
    List<Correspondent> getByCaseFolderId(@Param("caseId") Long caseId);

}
