package documents.service;

import documents.model.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 12.09.13
 * Time: 16:40
 */
@Repository
public class DataGenerator {

    private EntityManager entityManager;

    private Post director;
    private Post mainEngineer;
    private Post mainAccountant;
    private Post depHead;
    private Post engineer;
    private Post archivarius;

    private Department aho;
    private Department admin;
    private Department oapr;
    private Department teh;

    @Transactional
    public void generate() {
        addPosts();
        addDepartments();
        addCorrespondents();
        addEmployees();
    }

    @Transactional
    public Department getDepartment(Integer id) {
//        Department result;
//        result = entityManager.find(Department.class, id);
//        for (Employee employee : result.getEmployees()) {
//            System.out.println(employee.getDisplayName());
//        }
//        return result;
        return entityManager.find(Department.class, id);
//        return (Department) entityManager.createQuery("select d from Department d join fetch d.employees where d.departmentId = :id").setParameter("id", id).getSingleResult();
    }

    @Transactional
    public Person getPerson(Integer id) {
        return entityManager.find(Person.class, id);
    }

    private void addPosts() {
        mainEngineer = new Post();
        mainEngineer.setDisplayName("гл.инженер");
        mainEngineer.setFullName("главный инженер");
        getEntityManager().persist(mainEngineer);

        director = new Post();
        director.setDisplayName("директор");
        director.setFullName("директор");
        getEntityManager().persist(director);

        mainAccountant = new Post();
        mainAccountant.setDisplayName("главбух");
        mainAccountant.setFullName("главный бухгалтер");
        getEntityManager().persist(mainAccountant);

        depHead = new Post();
        depHead.setDisplayName("нач.отдела");
        depHead.setFullName("начальник отдела");
        getEntityManager().persist(depHead);

        engineer = new Post();
        engineer.setDisplayName("инженер");
        engineer.setFullName("инженер");
        getEntityManager().persist(engineer);

        archivarius = new Post();
        archivarius.setDisplayName("архивариус");
        archivarius.setFullName("архивариус");
        getEntityManager().persist(archivarius);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    private  void addDepartments() {
        aho = new Department();
        aho.setShortName("ахо");
        aho.setFullName("административно-хозяйственный отдел");
        getEntityManager().persist(aho);

        admin = new Department();
        admin.setShortName("администрация");
        admin.setFullName("администрация");
        getEntityManager().persist(admin);

        oapr = new Department();
        oapr.setShortName("оапр");
        oapr.setFullName("оапр");
        getEntityManager().persist(oapr);

        teh = new Department();
        teh.setShortName("техотдел");
        teh.setFullName("технический отдел");
        getEntityManager().persist(teh);
    }

    private  void addEmployee(String firstName, String lastName, Post post, Department department) {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        getEntityManager().persist(person);

        User user = new User();
        user.setName(person.getDisplayName());
        user.setPerson(person);
        person.getUsers().add(user);
        getEntityManager().persist(user);

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setPerson(person);
        department.getEmployees().add(employee);
        person.getAssigments().add(employee);
        employee.setPost(post);
        employee.setDateBegin(new Date());
        getEntityManager().persist(employee);
    }

    private  void addEmployees() {
        addEmployee("Михаил", "Рубцов", director, admin);
        addEmployee("Андрей", "Артеменко", mainEngineer, admin);
        addEmployee("Юлия", "Похилова", mainAccountant, admin);

        addEmployee("Сергей", "Козырь", depHead, teh);
        addEmployee("Максим", "Чаплюк", engineer, oapr);
        addEmployee("Никита", "Борисов", engineer, oapr);

        addEmployee("Евгений", "Куликов", depHead, oapr);
        addEmployee("Максим", "Лехман", engineer, oapr);

        addEmployee("Марина", "Кузнецова", archivarius, aho);
    }

    private  void addCorrespondents() {
        Correspondent correspondent = new Correspondent();
        correspondent.setDisplayName("засядько");
        correspondent.setFullName("шахта им. Засядько");
        correspondent.setPrefix("ШЗ");
        getEntityManager().persist(correspondent);

        correspondent = new Correspondent();
        correspondent.setDisplayName("минугля");
        correspondent.setFullName("министерство угольной промышленности");
        correspondent.setPrefix("МУП");
        getEntityManager().persist(correspondent);

        correspondent = new Correspondent();
        correspondent.setDisplayName("облсовет");
        correspondent.setFullName("донецкий областной совет");
        correspondent.setPrefix("ДОС");
        getEntityManager().persist(correspondent);
    }

}
