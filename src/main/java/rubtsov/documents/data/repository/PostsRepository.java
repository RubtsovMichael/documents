package rubtsov.documents.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rubtsov.documents.data.model.Post;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 27.09.13
 * Time: 16:26
 */
public interface PostsRepository extends JpaRepository<Post, Integer> {
}
