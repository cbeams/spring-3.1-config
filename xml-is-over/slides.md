!SLIDE center
# `@Enable*`

!SLIDE incremental bullets
# benefits
* easy to see what's going on
* easy to write your own
* scales smoothly from simple to sophisticated

!SLIDE center incremental bullets
# "Java Configuration"
* (completed)

!SLIDE center incremental bullets
# Java Configuration (3.1)
* completes the vision
* can configure all major container features
* mix and match styles as desired

!SLIDE smaller subsection
# But...

!SLIDE smaller subsection
# But... ?

!SLIDE center incremental bullets
# No buts.

!SLIDE center incremental bullets
# No buts.
* can be adopted incrementally
* but can go _all the way_
* which means...

!SLIDE center

![xml-is-over.jpg](xml-is-over.jpg)

!SLIDE center
# __XML IS OVER!__
<br/><br/><br/><br/>

!SLIDE center
# __XML IS OVER!__
for dependency injection

!SLIDE center
# __XML IS OVER!__
for enabling features of the Spring container

!SLIDE center
# __XML IS OVER!__
for Hibernate integration, too

!SLIDE center bullets incremental
# Hibernate 4 support
* Spring 3.1 RC1 builds against Hib 4.0.0.CR4
* 3.1 GA will sync with 4.0 Final
* new orm.hibernate4 packaging

!SLIDE smaller
# Familiar?
    @@@ xml
    <bean id="sessionFactory"
        class="org.sfwk.orm.hibernate3.LocalSessionFactoryBean">
      <property name="dataSource" ref="myDataSource"/>
      <property name="mappingResources">
        <list>
          <value>Person.hbm.xml</value>
          <value>Account.hbm.xml</value>
        </list>
      </property>
      <property name="hibernateProperties">
        <value>
          hibernate.dialect=org.hibernate.dialect.HSQLDialect
        </value>
      </property>
    </bean>

!SLIDE smaller
# Or even
    @@@ xml
    <bean id="sessionFactory"
        class="org.sfwk.orm.hib3.AnnotationSessionFactoryBean">
      <property name="dataSource" ref="myDataSource"/>
      <property name="annotatedClasses">
        <list>
          <value>com.foo.Person</value>
          <value>com.foo.Account</value>
        </list>
      </property>
      <property name="hibernateProperties">
        <value>
          hibernate.dialect=org.hibernate.dialect.HSQLDialect
        </value>
      </property>
    </bean>

!SLIDE smaller
# Or even
    @@@ xml
    <bean id="sessionFactory"
        class="org.sfwk.orm.hib3.AnnotationSessionFactoryBean">
      <property name="dataSource" ref="myDataSource"/>
      <property name="annotatedClasses">
        <list>
          <value>com.foo.Person</value> <!-- again the irony -->
          <value>com.foo.Account</value>
        </list>
      </property>
      <property name="hibernateProperties">
        <value>
          hibernate.dialect=org.hibernate.dialect.HSQLDialect
        </value>
      </property>
    </bean>

!SLIDE smaller
# We can do better than that.

!SLIDE smaller
    @@@ java
    @Bean
    public SessionFactory sessionFactory() {
        return new LocalSessionFactoryBuilder(dataSource())
            .addAnnotatedClasses(Person.class, Account.class)
            .buildSessionFactory();
    }

!SLIDE incremental bullets
# LocalSessionFactoryBuilder
* Extends Hibernate&apos;s own Configuration class
* only in the new orm.hibernate4 package

!SLIDE smaller
# Also...

!SLIDE smaller
# standalone Hibernate exception translator
    @@@ java
    @Bean
    public SessionFactory sessionFactory() {
        return new LocalSessionFactoryBuilder(dataSource())
            .addAnnotatedClasses(Person.class, Account.class)
            .buildSessionFactory();
    }

    @Bean
    public PersistenceExceptionTranslator exTranslator() {
        return new HibernateExceptionTranslator();
    }

!SLIDE center
# __XML IS OVER!__
<br/><br/><br/><br/>


!SLIDE center
# __XML IS OVER!__
and not just _Spring_ XML


!SLIDE center incremental bullets
# JPA

!SLIDE smaller
# persistence.xml
    @@@ xml
    <persistence xmlns="http://java.sun.com/xml/ns/persistence"
                 xmlns:xsi="..."
                 xsi:schemaLocation="..."
                 version="2.0">
       <persistence-unit name="sample">
          <jta-data-source>java:/DefaultDS</jta-data-source>
          <properties>
             <property name="hibernate.dialect"
                     value="org.hibernate.dialect.HSQLDialect"/>
          </properties>
       </persistence-unit>
    </persistence>

!SLIDE
# say goodbye.

!SLIDE smaller
    @@@ java
    @Bean
    public LocalContainerEntityManagerFactoryBean emf() {
        LocalContainerEntityManagerFactoryBean emf =
            new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPersistenceXmlLocation(
            "classpath:META-INF/persistence.xml");
        return emf;
    }

!SLIDE smaller
    @@@ java
    @Bean
    public LocalContainerEntityManagerFactoryBean emf() {
        LocalContainerEntityManagerFactoryBean emf =
            new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPersistenceXmlLocation(
            "classpath:META-INF/persistence.xml"); // no more!
        return emf;
    }

!SLIDE smaller
    @@@ java
    @Bean
    public LocalContainerEntityManagerFactoryBean emf() {
        LocalContainerEntityManagerFactoryBean emf =
            new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());

        emf.setPackagesToScan("com.win.entity");
        return emf;
    }

!SLIDE smaller
    @@@ java
    @Bean
    public LocalContainerEntityManagerFactoryBean emf() {
        LocalContainerEntityManagerFactoryBean emf =
            new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        // scan classpath for JPA @Entity types
        emf.setPackagesToScan("com.win.entity");
        return emf;
    }


!SLIDE center
# __XML IS OVER!__
and not just _Spring_ XML

!SLIDE
# web.xml

!SLIDE smaller bullets incremental
# web.xml
    @@@ xml
    <web-app>
      <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>
          org.springframework.web.servlet.DispatcherServlet
        </servlet-class>
        <init-param>
          <param-name>contextConfigLocation</param-name>
          <param-value>
            /WEB-INF/spring/dispatcher-config.xml
          </param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
      </servlet>
      <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <url-pattern>/</url-pattern>
      </servlet-mapping>
    <web-app>

!SLIDE bullets incremental
# Gone.
* (if you&apos;ve got Servlet 3)

!SLIDE smaller
# equivalent
    @@@ java
    public class WebInit implements WebApplicationInitializer {

       @Override
       public void onStartup(ServletContext container) {
         XmlWebApplicationContext ctx =
           new XmlWebApplicationContext()

         ctx.setConfigLocation(
           "/WEB-INF/spring/dispatcher-config.xml");

         ServletRegistration.Dynamic dispatcher =
           container.addServlet(
            "dispatcher", new DispatcherServlet(ctx));

         dispatcher.setLoadOnStartup(1);
         dispatcher.addMapping("/");
       }

    }

!SLIDE smaller transition=fade
# even better
    @@@ java
    public class WebInit implements WebApplicationInitializer {

       @Override
       public void onStartup(ServletContext container) {
         AnnotationConfigWebApplicationContext ctx =
           new AnnotationConfigWebApplicationContext()

         ctx.register(DispatcherConfig.class);


         ServletRegistration.Dynamic dispatcher =
           container.addServlet(
            "dispatcher", new DispatcherServlet(ctx));

         dispatcher.setLoadOnStartup(1);
         dispatcher.addMapping("/");
       }

    }

!SLIDE incremental bullets
# WebApplicationInitializer
* Builds on Servlet 3&apos;s ServletContainerInitializer
* Auto-detected on Servlet container startup

!SLIDE incremental bullets
* <del>application-context.xml</del>
* <del>persistence.xml</del>
* <del>web.xml</del>

!SLIDE center
# __XML IS OVER.__
<br/><br/><br/><br/>

!SLIDE center transition=fade
![over.png](over.png)
<br/><br/><br/>

!SLIDE center
![if-you-want-it.png](if-you-want-it.png)

!SLIDE center transition=fade
# __XML IS OVER,__
if you want it*

!SLIDE small
# &#42;which means that you really shouldn't worry, because XML support is definitely not going anywhere. This is a concern that commonly comes up when we talk about annotations and such, but we've said it before and we'll say it again: XML was, is, and ever will be a first class citizen in Spring. The changes in 3.1 just mean that if you *don't* want XML, you don't have to use it. That's all. kthx, bye.
