package rubtsov.documents.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rubtsov.documents.data.model.Correspondent;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 27.09.13
 * Time: 17:40
 */
public interface CorrespondentsRepository extends JpaRepository<Correspondent, Integer> {
}
