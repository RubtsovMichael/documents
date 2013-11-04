package rubtsov.documents.web.dto;

import rubtsov.documents.data.model.Person;
import rubtsov.documents.data.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 04.11.13
 * Time: 16:29
 */
public class UserDto implements EntityDto<User> {

    private Long userID;

    private String name;

    private Person person;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public void saveToEntity(User user) {
        user.setName(getName());
    }

    @Override
    public void loadFromEntity(User user) {
        userID = user.getUserID();
        name = user.getName();
//        person = new perso
    }
}
