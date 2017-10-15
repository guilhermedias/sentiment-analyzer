package solutions.moot;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppIndex {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        AppLogic appLogic = applicationContext.getBean(AppLogic.class);
        appLogic.run();
    }
}
