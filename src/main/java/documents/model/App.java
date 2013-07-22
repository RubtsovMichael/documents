package documents.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by mike on 19.07.13.
 */
public class App {

    private static Department aho;
    private static Department admin;
    private static Department oapr;
    private static Department teh;

    private static Post director;
    private static Post mainEngineer;
    private static Post mainAccountant;
    private static Post depHead;
    private static Post engineer;
    private static Post archivarius;

    private static Session session;

    private static void addPosts() {
        mainEngineer = new Post();
        mainEngineer.setDisplayName("гл.инженер");
        mainEngineer.setFullName("главный инженер");
        session.save(mainEngineer);

        director = new Post();
        director.setDisplayName("директор");
        director.setFullName("директор");
        session.save(director);

        mainAccountant = new Post();
        mainAccountant.setDisplayName("главбух");
        mainAccountant.setFullName("главный бухгалтер");
        session.save(mainAccountant);

        depHead = new Post();
        depHead.setDisplayName("нач.отдела");
        depHead.setFullName("начальник отдела");
        session.save(depHead);

        engineer = new Post();
        engineer.setDisplayName("инженер");
        engineer.setFullName("инженер");
        session.save(engineer);

        archivarius = new Post();
        archivarius.setDisplayName("архивариус");
        archivarius.setFullName("архивариус");
        session.save(archivarius);
    }

    private static void addDepartments() {
        aho = new Department();
        aho.setShortName("ахо");
        aho.setFullName("административно-хозяйственный отдел");
        session.save(aho);

        admin = new Department();
        admin.setShortName("администрация");
        admin.setFullName("администрация");
        session.save(admin);

        oapr = new Department();
        oapr.setShortName("оапр");
        oapr.setFullName("оапр");
        session.save(oapr);

        teh = new Department();
        teh.setShortName("техотдел");
        teh.setFullName("технический отдел");
        session.save(teh);
    }

    private static void addEmployee(String firstName, String lastName, Post post, Department department) {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        session.save(person);

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setPerson(person);
        employee.setPost(post);
        employee.setDateBegin(new Date());
        session.save(employee);
    }

    private static void addEmployees() {
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

    private static void addCorrespondents() {
        Correspondent correspondent = new Correspondent();
        correspondent.setDisplayName("засядько");
        correspondent.setFullName("шахта им. Засядько");
        correspondent.setPrefix("ШЗ");
        session.save(correspondent);

        correspondent = new Correspondent();
        correspondent.setDisplayName("минугля");
        correspondent.setFullName("министерство угольной промышленности");
        correspondent.setPrefix("МУП");
        session.save(correspondent);

        correspondent = new Correspondent();
        correspondent.setDisplayName("облсовет");
        correspondent.setFullName("донецкий областной совет");
        correspondent.setPrefix("ДОС");
        session.save(correspondent);
    }

    public static void main(String argv[]) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("development/testContext.xml");

        session = context.getBean(SessionFactory.class).openSession();

//        session.beginTransaction();
//
//        addPosts();
//        addDepartments();
//        addCorrespondents();
//        addEmployees();
//
//        session.getTransaction().commit();

        Department dep = (Department) session.get(Department.class, new Integer(2));

        System.out.println(dep.getShortName());
        System.out.println(dep.getEmployees().getClass());
        System.out.println(dep.getEmployees().size());

        for (Employee employee : dep.getEmployees()) {
            System.out.println(employee.getDisplayName());
        }

        session.close();

        context.close();
    }

}
