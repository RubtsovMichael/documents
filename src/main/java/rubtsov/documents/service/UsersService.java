package rubtsov.documents.service;

import rubtsov.documents.data.model.entity.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 04.11.13
 * Time: 16:27
 */
public interface UsersService {

    List<User> getAllUsers();

    User load(Long id);

    User save(User user);

}
