import documents.model.Department;
import documents.model.Employee;
import documents.model.Person;
import documents.service.DataGenerator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 12.09.13
 * Time: 16:41
 */
public class GeneratorApp {

    public static void main(String argv[]) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("development/testContext.xml");

        DataGenerator dataGenerator = context.getBean(DataGenerator.class);

        dataGenerator.generate();

        Department dep = dataGenerator.getDepartment(1);

        System.out.println(dep.getShortName());
        System.out.println(dep.getEmployees().getClass());
        System.out.println(dep.getEmployees().size());

        for (Employee employee : dep.getEmployees()) {
            System.out.println(employee.getDisplayName());
        }

        Person person  = dataGenerator.getPerson(1);

        System.out.println(person.getDisplayName());
        System.out.println(person.getUser().getName());
        System.out.println(person.getAssigments().getClass());
        System.out.println(person.getAssigments().size());

        for (Employee employee : person.getAssigments()) {
            System.out.println(employee.getDisplayName());
        }

        context.close();
    }

}
