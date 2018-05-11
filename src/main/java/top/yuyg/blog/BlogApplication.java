package top.yuyg.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import top.yuyg.blog.datasources.DynamicDataSourceConfig;

@SpringBootApplication
@Import({ DynamicDataSourceConfig.class })
public class BlogApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BlogApplication.class);
	}
}
