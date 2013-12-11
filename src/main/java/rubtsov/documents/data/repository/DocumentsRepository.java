package rubtsov.documents.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rubtsov.documents.data.model.entity.Document;

/**
 * Created by mike on 11.12.13.
 */
public interface DocumentsRepository extends JpaRepository<Document, Long> {
}
