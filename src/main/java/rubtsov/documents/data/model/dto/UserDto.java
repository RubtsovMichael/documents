package rubtsov.documents.data.model.dto;

import rubtsov.documents.data.model.entity.User;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 04.11.13
 * Time: 16:29
 */
public class UserDto {

    private Long userID;

    private String name;

    private PersonDto person;

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

    public PersonDto getPerson() {
        return person;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }

    public UserDto() {
    }

    public UserDto(User user) {
        userID = user.getUserID();
        name = user.getName();
        person = new PersonDto(user.getPerson());
    }
}
