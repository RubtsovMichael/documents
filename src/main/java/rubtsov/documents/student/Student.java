package rubtsov.documents.student;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 17.10.13
 * Time: 17:46
 */
public class Student {
    private Integer age;
    private String name;
    private Integer id;
    private Date birth;

    public void setAge(Integer age) {
        this.age = age;
    }
    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}