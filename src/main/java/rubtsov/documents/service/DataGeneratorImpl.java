package rubtsov.documents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rubtsov.documents.data.model.entity.*;
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

    @Autowired
    CasesRepository casesRepository;

    @Autowired
    DocumentsRepository documentsRepository;

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
        addDocuments();
    }

    private void addDocuments() {
        addDocument(DocumentType.INCOMING, "NN111", "ФППЫПВ", new Date(), new Date(), "первый\nдокумент", null, null, "Иванов");
        addDocument(DocumentType.INCOMING, "NN112", "фывлоыжфл", new Date(), new Date(), "второй\nдокумент", null, null, "Петров");
        addDocument(DocumentType.OUTGOING, "NN113", null, new Date(), new Date(), "третий\nдокумент", null, null, null);
    }

    private void addDocument(DocumentType docType, String innerNumber, String outerNumber,
            Date innerDate, Date outerDate, String description, Correspondent correspondent,
            Person author, String outerAuthor) {
        Document doc = new Document();
        doc.setDocType(docType);

        doc.setInnerNumber(innerNumber);
        doc.setOuterNumber(outerNumber);

        doc.setInnerDate(innerDate);
        doc.setOuterDate(outerDate);

        doc.setOuterAuthor(outerAuthor);
        doc.setDescription(description);
        documentsRepository.saveAndFlush(doc);
    }

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

    private void addDepartments() {
        aho = departmentsRepository.saveAndFlush(new Department("01", "ахо", "административно-хозяйственный отдел"));
        admin = departmentsRepository.saveAndFlush(new Department("02", "администрация", "администрация"));
        oapr = departmentsRepository.saveAndFlush(new Department("03", "оапр", "оапр"));
        teh = departmentsRepository.saveAndFlush(new Department("04", "техотдел", "технический отдел"));
    }

    private void addEmployee(String firstName, String lastName, Post post, Department department) {
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

    private void addCorrespondents() {
        CaseFolder caseFolder = casesRepository.saveAndFlush(new CaseFolder("01", "дело1", ""));

        correspondentsRepository.save(new Correspondent("засядько", "шахта им. Засядько", caseFolder));
        correspondentsRepository.save(new Correspondent("минугля", "министерство угольной промышленности", caseFolder));

        caseFolder = casesRepository.saveAndFlush(new CaseFolder("02", "дело2", ""));
        correspondentsRepository.save(new Correspondent("облсовет", "донецкий областной совет", caseFolder));
        correspondentsRepository.save(new Correspondent("экон", "экон", caseFolder));
        correspondentsRepository.save(new Correspondent("епам", "епам", caseFolder));

        caseFolder = casesRepository.saveAndFlush(new CaseFolder("03", "дело3", ""));
        correspondentsRepository.save(new Correspondent("макеевуголь", "макеевуголь", caseFolder));
    }

}
