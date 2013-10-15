package rubtsov.documents;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import rubtsov.documents.service.DataGenerator;

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

        context.close();
    }

}
