package edu.papolicy;

import edu.papolicy.daos.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean(name = "dataSource")
	public DataSource getDataSource(){
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://steve-rivera.com:3306/PAPolicy");
		dataSource.setUsername("capstone");
		dataSource.setPassword("letusin");

		return dataSource;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource){
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		sessionBuilder.scanPackages("edu.papolicy.models");
		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}

	@Autowired
	@Bean(name = "batchDao")
	public BatchDAO getBatchDao(SessionFactory sessionFactory) {
		return new BatchDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "codeDao")
	public CodeDAO getCodeDao(SessionFactory sessionFactory) {
		return new CodeDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "documentDao")
	public DocumentDAO getDocumentDao(SessionFactory sessionFactory) {
		return new DocumentDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "fileDao")
	public FileDAO getFileDao(SessionFactory sessionFactory) {
		return new FileDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "filterDao")
	public FilterDAO getFilterDao(SessionFactory sessionFactory) {
		return new FilterDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "newspaperDao")
	public NewspaperDAO getNewspaperDao(SessionFactory sessionFactory) {
		return new NewspaperDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "roleDao")
	public RoleDAO getRoleDao(SessionFactory sessionFactory) {
		return new RoleDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "userDao")
	public UserDAO getUserDao(SessionFactory sessionFactory) {
		return new UserDAOImpl(sessionFactory);
	}
}