package rubtsov.documents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rubtsov.documents.data.model.*;
import rubtsov.documents.data.repository.*;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 12.09.13
 * Time: 16:40
 */
@Service
public class DataGeneratorImpl implements DataGenerator {

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    DepartmentsRepository departmentsRepository;

    @Autowired
    CorrespondentsRepository correspondentsRepository;

    @Autowired
    PersonsRepository personsRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    EmployeesRepository employeesRepository;

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


//    @Transactional
//    public Department getDepartment(Integer id) {
//        return entityManager.find(Department.class, id);
//    }
//
//    @Transactional
//    public Person getPerson(Integer id) {
//        return entityManager.find(Person.class, id);
//    }

    private void addPosts() {
        mainEngineer = new Post();
        mainEngineer.setDisplayName("гл.инженер");
        mainEngineer.setFullName("главный инженер");
        mainEngineer = postsRepository.saveAndFlush(mainEngineer);

        director = new Post();
        director.setDisplayName("директор");
        director.setFullName("директор");
        director = postsRepository.saveAndFlush(director);

        mainAccountant = new Post();
        mainAccountant.setDisplayName("главбух");
        mainAccountant.setFullName("главный бухгалтер");
        mainAccountant = postsRepository.saveAndFlush(mainAccountant);

        depHead = new Post();
        depHead.setDisplayName("нач.отдела");
        depHead.setFullName("начальник отдела");
        depHead = postsRepository.saveAndFlush(depHead);

        engineer = new Post();
        engineer.setDisplayName("инженер");
        engineer.setFullName("инженер");
        engineer = postsRepository.saveAndFlush(engineer);

        archivarius = new Post();
        archivarius.setDisplayName("архивариус");
        archivarius.setFullName("архивариус");
        archivarius = postsRepository.saveAndFlush(archivarius);
    }

    private  void addDepartments() {
        aho = new Department();
        aho.setShortName("ахо");
        aho.setFullName("административно-хозяйственный отдел");
        aho = departmentsRepository.saveAndFlush(aho);

        admin = new Department();
        admin.setShortName("администрация");
        admin.setFullName("администрация");
        admin = departmentsRepository.saveAndFlush(admin);

        oapr = new Department();
        oapr.setShortName("оапр");
        oapr.setFullName("оапр");
        oapr = departmentsRepository.saveAndFlush(oapr);

        teh = new Department();
        teh.setShortName("техотдел");
        teh.setFullName("технический отдел");
        teh = departmentsRepository.saveAndFlush(teh);
    }

    private  void addEmployee(String firstName, String lastName, Post post, Department department) {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        personsRepository.saveAndFlush(person);

        User user = new User();
        user.setName(person.getDisplayName());
        user.setPerson(person);
        person.getUsers().add(user);
        usersRepository.saveAndFlush(user);

        Employee employee = new Employee();
        employee.setPerson(person);
        employee.setPost(post);
        employee.setDepartment(department);
        employee.setDateBegin(new Date());
        employeesRepository.saveAndFlush(employee);

//        department.getEmployees().add(employee);
//        person.getAssigments().add(employee);
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
        correspondentsRepository.saveAndFlush(correspondent);

        correspondent = new Correspondent();
        correspondent.setDisplayName("минугля");
        correspondent.setFullName("министерство угольной промышленности");
        correspondent.setPrefix("МУП");
        correspondentsRepository.saveAndFlush(correspondent);

        correspondent = new Correspondent();
        correspondent.setDisplayName("облсовет");
        correspondent.setFullName("донецкий областной совет");
        correspondent.setPrefix("ДОС");
        correspondentsRepository.saveAndFlush(correspondent);
    }

}
