package org.springframework.data.rest.example;

import org.springframework.boot.autoconfigure.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JndiDataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.boot.autoconfigure.jersey.JerseyAutoConfiguration;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import net.daum.clix.springframework.data.rest.client.http.CommonsRestClient;
import net.daum.clix.springframework.data.rest.client.http.RestClient;
import javax.servlet.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.web.servlet.ViewRendererServlet;

@SpringBootApplication(exclude = { TransactionAutoConfiguration.class, DataSourceAutoConfiguration.class,
				JndiDataSourceAutoConfiguration.class, JtaAutoConfiguration.class,
				WebMvcAutoConfiguration.class, JerseyAutoConfiguration.class, FreeMarkerAutoConfiguration.class,
				JpaRepositoriesAutoConfiguration.class, RepositoryRestMvcAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class })
@Controller
@ImportResource({ "classpath*:application-context.xml" })
public class ApplicationConfig extends SpringBootServletInitializer {
	public static ApplicationConfig instance;
	
	public ApplicationConfig() {
			super();
			instance = this;
	}
		
	@Autowired
	private PersonRepository persons;
	@Autowired
	private AddressRepository addresses;
	@Autowired
	private ProfileRepository profiles;

	@Bean
	RestClient restClient() {
		return new CommonsRestClient("http://localhost:8180/spring-data-rest-webmvc");
	}

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		System.out.println("==========================================================================================");
		ServletRegistrationBean res = new ServletRegistrationBean(new ViewRendererServlet(), "/WEB-INF/servlet/view");
		res.setLoadOnStartup(1);
		return res;
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver res = new InternalResourceViewResolver("/WEB-INF/jsp/", ".jsp");
		res.setViewClass(InternalResourceView.class);
		return res;
	}

	@RequestMapping("/abc.j")
	public String findByRsql() {
		System.out.println("findByRsql");
		System.out.println("findByRsql" + persons + " " + addresses + " " + profiles);
		System.out.println(persons.count());
		return "";
	}
}
