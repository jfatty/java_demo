/**
 * @author <a href="mailto:wanggy6@asiainfo.com">wanggy6</a>
 * @version 1.0
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.WebApplicationContext;


@SpringBootApplication(scanBasePackages = { "com" })
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {

        final ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
    }

    /**
     * 打成war包供别的容器部署时用到，本地可以注释掉
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    /**
     * 打成war包供别的容器部署时用到,获取上下文，本地可以注释掉
     */
    @Override
    protected WebApplicationContext run(SpringApplication application) {
        WebApplicationContext context = super.run(application);
        return context;
    }

}

