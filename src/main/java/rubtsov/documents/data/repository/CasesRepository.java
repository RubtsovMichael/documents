package rubtsov.documents.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rubtsov.documents.data.model.CaseFolder;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 15.10.13
 * Time: 16:52
 */
public interface CasesRepository extends JpaRepository<CaseFolder, Integer> {
}
