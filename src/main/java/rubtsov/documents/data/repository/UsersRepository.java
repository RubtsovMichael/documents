package rubtsov.documents.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rubtsov.documents.data.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 27.09.13
 * Time: 17:44
 */
public interface UsersRepository extends JpaRepository<User, Integer> {
}
