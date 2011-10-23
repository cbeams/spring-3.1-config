!SLIDE subsection
# __Configuration Enhancements in Spring 3.1__
<br><br>
## Chris Beams
### _SpringSource, VMware_
<br><br>
## Rossen Stoyanchev
### _SpringSource, VMware_

!SLIDE small
# __Chris Beams__

* Senior Technical Staff at SpringSource, VMware
* Spring Framework committer
* Train and consult around Spring
* Enterprise Integration with Spring course author
* Bay area

!SLIDE small
# __Rossen Stoyanchev__

* Staff Engineer SpringSource, VMware
* Spring MVC, Spring Web Flow committer
* Teach and consult, Spring Projects
* Spring Web course author
* NYC area

!SLIDE subsection
# time machine
<br/><br/>

!SLIDE
# Spring 1.0
<br/><br/>

!SLIDE center
    @@@ xml
    <beans/>

!SLIDE
    @@@ xml
    <beans>

    </beans>

!SLIDE
    @@@ xml
    <beans>
        <bean id="foo" class="com.foo.Foo"/>
    </beans>

!SLIDE
    @@@ xml
    <beans>
        <bean id="foo" class="com.foo.Foo"/>
        <bean id="bar" class="com.foo.Bar">
           <property name="foo" ref="foo"/>
        </bean>
    </beans>

!SLIDE
    @@@ xml
    <beans>
        <bean id="foo" class="com.foo.Foo"/>
        <bean id="bar" class="com.foo.Bar">
           <property name="foo" ref="foo"/>
        </bean>
        <bean .../>
    </beans>

!SLIDE
    @@@ xml
    <beans>
        <bean id="foo" class="com.foo.Foo"/>
        <bean id="bar" class="com.foo.Bar">
           <property name="foo" ref="foo"/>
        </bean>
        <bean .../>
        <bean .../>
    </beans>

!SLIDE
    @@@ xml
    <beans>
        <bean id="foo" class="com.foo.Foo"/>
        <bean id="bar" class="com.foo.Bar">
           <property name="foo" ref="foo"/>
        </bean>
        <bean .../>
        <bean .../>
        <bean .../>
        <bean .../>
    </beans>

!SLIDE
    @@@ xml
    <beans>
        <bean id="foo" class="com.foo.Foo"/>
        <bean id="bar" class="com.foo.Bar">
           <property name="foo" ref="foo"/>
        </bean>
        <bean .../>
        <bean .../>
        <bean .../>
        <bean .../>
        <bean .../>
        <bean .../>
    </beans>

!SLIDE
    @@@ xml
    <beans>
        <bean id="foo" class="com.foo.Foo"/>
        <bean id="bar" class="com.foo.Bar">
           <property name="foo" ref="foo"/>
        </bean>
        <bean .../>
        <bean .../>
        <bean .../>
        <bean .../>
        <bean .../>
        <bean .../>
        <bean .../>
        <bean .../>
        <bean .../>
        <bean .../>
    </beans>

!SLIDE
    @@@ xml
    <beans>
        <bean id="foo" class="com.foo.Foo"/>
        <bean id="bar" class="com.foo.Bar">
           <property name="foo" ref="foo"/>
        </bean>
        <bean .../>
        <bean .../>
        <bean .../>
        <bean .../>
        <bean .../>
        <bean .../>
        <bean .../>
        <bean .../>
        <bean .../>
        <bean .../>
        <bean .../>

    </beans>

!SLIDE
    @@@ xml
    <beans>
        <bean id="foo" class="com.foo.Foo"/>
        <bean id="bar" class="com.foo.Bar">
           <property name="foo" ref="foo"/>
        </bean>
        <bean .../>
        <bean .../>
        <bean .../>
        <bean .../>
        <bean .../>
        <bean .../>
        <bean .../>
        <bean .../>
        <bean .../>
        <bean .../>
        <bean .../>
        <import resource="more-beans.xml"/>
    </beans>

!SLIDE center
# "Spring beans XML"

!SLIDE center incremental bullets
# Spring beans XML
* simple
* general purpose
* flexible

!SLIDE smaller subsection
# But...

!SLIDE center incremental bullets
# Spring beans XML
* verbose
* not type-safe
* special tooling

!SLIDE
# Spring 2.0
<br/><br/>

!SLIDE center
    @@@ xml
    <namespace:*/>

!SLIDE center
# e.g.
    @@@ xml
    <tx:annotation-driven/>
    <aop:aspectj-autoproxy/>
    <jee:jndi-lookup/>
    <util:list/>

!SLIDE center
# "Spring XML namespaces"

!SLIDE center incremental bullets
# Spring XML namespaces
* concise
* powerful
* easier to use

!SLIDE smaller subsection
# But...

!SLIDE center incremental bullets
# Spring XML namespaces
* opaque
* non-extensible
* difficult to <a href="http://static.springsource.org/spring/docs/2.0.x/reference/extensible-xml.html">write your own</a>

!SLIDE center
# Spring 2.5
<br/><br/>

!SLIDE center smaller
# (Enter Java 5 and @Annotations)

!SLIDE center
    @@@ java
    @Autowired

!SLIDE center
    @@@ java
    @Component

!SLIDE center
    @@@ xml
    <context:component-scan/>

!SLIDE center
# "Annotation-Driven Injection"

!SLIDE center incremental bullets
# Annotation-Driven Injection
* _really_ concise, convenient
* TODO 2
* TODO 3

!SLIDE smaller subsection
# But...

!SLIDE incremental bullets
# Annotation-Driven Injection
* can&apos;t wire up third-party code
* ambiguities can arise (enter @Qualifier)
* still requires xml to bootstrap

!SLIDE smaller incremental bullets
# I mean, if you&apos;ve got this
    @@@ java
    package com.win;

    @Component
    public class TehAwesome {
        @Autowired
        public void setStuff(Stuff stuff) { ... }
    }
<br/><br/><br/>
<br/><br/><br/>
<br/><br/><br/>
<br/><br/>

!SLIDE smaller incremental bullets
# I mean, if you&apos;ve got this
    @@@ java
    package com.win;

    @Component
    public class TehAwesome {
        @Autowired
        public void setStuff(Stuff stuff) { ... }
    }

# ... then _this_ is kind of ironic, right?
    @@@ xml
    <context:component-scan base-package="com.win"/>

!SLIDE center smaller
# (we&apos;ll get back to that in a bit)

!SLIDE center
# Spring 3.0
<br/><br/>

!SLIDE center
    @@@ java
    @Configuration

!SLIDE center
    @@@ java
    @Bean

!SLIDE center smaller
# (quick refresher)

!SLIDE smaller
# bean definition
    @@@ java

    @Configuration
    public class AppConfig {

        @Bean
        public QuoteService quoteService() {
            RealTimeQuoteService quoteService = ...;

            return quoteService;
        }

    }

!SLIDE smaller
# bean definition
    @@@ java
    // @Configuration classes =~ <beans/> documents
    @Configuration
    public class AppConfig {

        @Bean
        public QuoteService quoteService() {
            RealTimeQuoteService quoteService = ...;

            return quoteService;
        }

    }

!SLIDE smaller
# bean definition
    @@@ java

    @Configuration
    public class AppConfig {
        // @Bean methods ~= <bean/> elements
        @Bean
        public QuoteService quoteService() {
            RealTimeQuoteService quoteService = ...;

            return quoteService;
        }

    }

!SLIDE smaller
# bean definition
    @@@ java

    @Configuration
    public class AppConfig {

        @Bean
        public QuoteService quoteService() {
            RealTimeQuoteService quoteService = // instantiate

            return quoteService;
        }

    }

!SLIDE smaller
# bean definition
    @@@ java

    @Configuration
    public class AppConfig {

        @Bean
        public QuoteService quoteService() {
            RealTimeQuoteService quoteService = ...;
            // configure
            return quoteService;
        }

    }

!SLIDE smaller
# bean definition
    @@@ java

    @Configuration
    public class AppConfig {

        @Bean
        public QuoteService quoteService() {
            RealTimeQuoteService quoteService = ...;

            return quoteService; // object managed by Spring
        }

    }

!SLIDE smaller
# bootstrap and use
    @@@ java

    public class Main {

      public static void main(String... args) {

        ApplicationContext ctx =
          new AnnotationConfigApplicationContext(AppConfig.class);

        QuoteService quoteService =
          ctx.getBean(QuoteService.class);

        System.out.println(quoteService.currentValue("VMW"));

      }
    }

!SLIDE smaller
# bootstrap and use
    @@@ java

    public class Main {

      public static void main(String... args) {
        // bootstrap the Spring container
        ApplicationContext ctx =
          new AnnotationConfigApplicationContext(AppConfig.class);

        QuoteService quoteService =
          ctx.getBean(QuoteService.class);

        System.out.println(quoteService.currentValue("VMW"));

      }
    }

!SLIDE smaller
# bootstrap and use
    @@@ java

    public class Main {

      public static void main(String... args) {

        ApplicationContext ctx =
          new AnnotationConfigApplicationContext(AppConfig.class);
        // retrieve the bean we want to use in type-safe fashion
        QuoteService quoteService =
          ctx.getBean(QuoteService.class);

        System.out.println(quoteService.currentValue("VMW"));

      }
    }

!SLIDE smaller
# bootstrap and use
    @@@ java

    public class Main {

      public static void main(String... args) {

        ApplicationContext ctx =
          new AnnotationConfigApplicationContext(AppConfig.class);

        QuoteService quoteService =
          ctx.getBean(QuoteService.class);
        // use the bean however desired
        System.out.println(quoteService.currentValue("VMW"));

      }
    }

!SLIDE

# "Java Configuration"

!SLIDE incremental bullets

# Java Configuration

* type-safe, object-oriented
* can configure any component
* complete programmatic control
* no special tooling required

!SLIDE smaller subsection
# But...

!SLIDE incremental bullets

# Java Configuration

* no equivalent to <namespace:*/>
* xml still required for tx mgmt, aop, etc
* no support in _TestContext_ framework


!SLIDE center
# Spring 3.1
<br/><br/>

!SLIDE center
    @@@ java
    @Enable*

<br/><br/>
<br/><br/>

!SLIDE center
    @@@ java
    @Enable*

 _(and friends)_

!SLIDE center
# e.g.
    @@@ java
    @EnableTransactionManagement
    @EnableAspectJAutoProxy
    @ComponentScan

!SLIDE smaller center
# "Java-Based Application Configuration"

!SLIDE smaller center incremental bullets
# "Java-Based Application Configuration"
* good
* better
* best

!SLIDE smaller subsection
# But...

!SLIDE smaller subsection
# But... ?

!SLIDE center incremental bullets
# No buts.

!SLIDE center incremental bullets
# No buts.
* 3.1 completes the vision
* can mix and match all styles
* which means...

!SLIDE center

![xml-is-over.jpg](xml-is-over.jpg)

!SLIDE smaller incremental bullets
# I mean, if you&apos;ve got this
    @@@ java
    package com.win;

    @Component
    public class TehAwesome {
        @Autowired
        public void setStuff(Stuff stuff) { ... }
    }

<br/><br/><br/><br/>
<br/><br/><br/><br/>
<br/><br/><br/>

!SLIDE smaller incremental bullets
# I mean, if you&apos;ve got this
    @@@ java
    package com.win;

    @Component
    public class TehAwesome {
        @Autowired
        public void setStuff(Stuff stuff) { ... }
    }

# ... then _this_ is kind of ironic ...
    @@@ xml
    <context:component-scan base-package="com.win"/>

!SLIDE smaller incremental bullets
# I mean, if you&apos;ve got this
    @@@ java
    package com.win;

    @Component
    public class TehAwesome {
        @Autowired
        public void setStuff(Stuff stuff) { ... }
    }

# ... but _this_ makes a lot more sense.
    @@@ java
    @ComponentScan("com.win")

!SLIDE smaller incremental bullets
# and if you&apos;ve got this
    @@@ java
    package com.win;

    @Repository
    public class WidgetRepo {
        @Transactional
        public void add(Widget widget) { ... }
    }

<br/><br/><br/><br/>
<br/><br/><br/><br/>
<br/><br/><br/>

!SLIDE smaller incremental bullets
# and if you&apos;ve got this
    @@@ java
    package com.win;

    @Repository
    public class WidgetRepo {
        @Transactional
        public void add(Widget widget) { ... }
    }

# ... then why do this ...
    @@@ xml
    <tx:annotation-driven/>

!SLIDE smaller incremental bullets
# and if you&apos;ve got this
    @@@ java
    package com.win;

    @Repository
    public class WidgetRepo {
        @Transactional
        public void add(Widget widget) { ... }
    }

# ... when you could do this?
    @@@ xml
    @EnableTransactionManagement


!SLIDE smaller subsection
# all together now

!SLIDE smaller subsection
# autowired component
    @@@ java
    @Repository
    public class JdbcWidgetRepo {
        private final JdbcTemplate;

        @Autowired
        public JdbcWidgetRepo(DataSource dataSource) {
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        }

        @Transactional
        public void add(Widget widget) { /* use jdbcTemplate */ }
    }

!SLIDE smaller subsection
# configuration class

    @@@ java
    @Configuration
    @ComponentScan("com.win")
    @EnableTransactionManagement
    public class Config {
        @Bean
        public DataSource dataSource() throws NamingException {
            Context jndi = new InitialContext();
            DataSource ds = (DataSource) jndi.lookup("java:...");
            return ds;
        }

        @Bean
        public PlatformTransactionManager txManager() ... {
            return
                new DataSourceTransactionManager(dataSource());
        }
    }

!SLIDE smaller
    @@@ java
    @Configuration
    @ComponentScan("com.win") // register @Component, @Service
    @EnableTransactionManagement
    public class Config {
        @Bean
        public DataSource dataSource() throws NamingException {
            Context jndi = new InitialContext();
            DataSource ds = (DataSource) jndi.lookup("java:...");
            return ds;
        }

        @Bean
        public PlatformTransactionManager txManager() ... {
            return
                new DataSourceTransactionManager(dataSource());
        }
    }

!SLIDE smaller
    @@@ java
    @Configuration
    @ComponentScan("com.win")
    @EnableTransactionManagement // advise @Transactional methods
    public class Config {
        @Bean
        public DataSource dataSource() throws NamingException {
            Context jndi = new InitialContext();
            DataSource ds = (DataSource) jndi.lookup("java:...");
            return ds;
        }

        @Bean
        public PlatformTransactionManager txManager() ... {
            return
                new DataSourceTransactionManager(dataSource());
        }
    }

!SLIDE smaller
    @@@ java
    @Configuration
    @ComponentScan("com.win")
    @EnableTransactionManagement
    public class Config { // wire up third-party stuff
        @Bean
        public DataSource dataSource() throws NamingException {
            Context jndi = new InitialContext();
            DataSource ds = (DataSource) jndi.lookup("java:...");
            return ds;
        }

        @Bean
        public PlatformTransactionManager txManager() ... {
            return
                new DataSourceTransactionManager(dataSource());
        }
    }

!SLIDE smaller
    @@@ java
    @Configuration
    @ComponentScan("com.win")
    @EnableTransactionManagement
    public class Config {
        @Bean
        public DataSource dataSource() throws NamingException {
            Context jndi = new InitialContext(); // native API!
            DataSource ds = (DataSource) jndi.lookup("java:...");
            return ds;
        }

        @Bean
        public PlatformTransactionManager txManager() ... {
            return
                new DataSourceTransactionManager(dataSource());
        }
    }

!SLIDE smaller subsection
# So..

!SLIDE center
    @@@ xml
    <context:component-scan/>
!SLIDE smaller
# becomes
<br/>
!SLIDE center
    @@@ xml
    @ComponentScan

!SLIDE center
    @@@ xml
    <tx:annotation-driven/>
!SLIDE smaller
# becomes
<br/>
!SLIDE center
    @@@ xml
    @EnableTransactionManagement

!SLIDE center
    @@@ xml
    <aop:aspectj-autoproxy/>
!SLIDE smaller
# becomes
<br/>
!SLIDE center
    @@@ xml
    @EnableAspectJAutoProxy

!SLIDE center
    @@@ xml
    <task:annotation-driven/>
!SLIDE smaller
# becomes
<br/>
!SLIDE center
    @@@ xml
    @EnableScheduling
    and
    @EnableAsync

!SLIDE center
    @@@ xml
    <mvc:annotation-driven/>
!SLIDE smaller
# becomes
<br/>
!SLIDE center
    @@@ xml
    @EnableWebMvc

!SLIDE smaller
# you get the idea
<br/>

!SLIDE smaller subsection
# But...

!SLIDE center smaller incremental bullets
# what about
* transparency?
* extensibility?
* flexibility?


!SLIDE smaller incremental bullets
    @@@ java
    @Configuration
    @EnableScheduling
    public class AppConfig {
        // various @Bean definitions
    }

!SLIDE
TODO: go into detail? show writing your own?

!SLIDE center
# __XML IS OVER!__
<br/><br/><br/><br/>

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
    @@@ java
    @Bean
    public SessionFactory sessionFactory() {
        return new LocalSessionFactoryBuilder(dataSource())
            .addAnnotatedClasses(Person.class, Account.class)
            .buildSessionFactory();
    }
<br/><br/><br/><br/>
<br/><br/><br/><br/>
<br/><br/>

!SLIDE smaller
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
and not just for Spring


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
        emf.setPersistenceXmlLocation( // no more!
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
and not just for Spring

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

!SLIDE smaller
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

!SLIDE incremental commandline
    $ find myproject/ -name '*.xml' | wc -l
    0
<br/><br/><br/>

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
if you want it.


!SLIDE
# Testing support

!SLIDE
# "TestContext Framework"

!SLIDE center incremental bullets
# TestContext Framework
* Since Spring 2.5
* Eases _integration testing_ with Spring
* Support for JUnit and TestNG

!SLIDE center
    @@@ java
    @RunWith(SpringJUnit4ClassRunner.class)

!SLIDE center
    @@@ java
    @ContextConfiguration

!SLIDE smaller
# 2.5
    @@@java
    @RunWith(SpringJUnit4ClassRunner.class)

    @ContextConfiguration("app-context.xml")
    public class MyTest {
        @Autowired MyBean bean;

        @Test
        public void testXyz() {

        }
    }

!SLIDE smaller
# 2.5
    @@@java
    @RunWith(SpringJUnit4ClassRunner.class)
    // ApplicationContext will be loaded from app-context.xml
    @ContextConfiguration("app-context.xml")
    public class MyTest {
        @Autowired MyBean bean;

        @Test
        public void testXyz() {

        }
    }

!SLIDE smaller
# 2.5
    @@@java
    @RunWith(SpringJUnit4ClassRunner.class)

    @ContextConfiguration("app-context.xml")
    public class MyTest {
        @Autowired MyBean bean; // injected from app-context.xml

        @Test
        public void testXyz() {

        }
    }

!SLIDE smaller
# 2.5
    @@@java
    @RunWith(SpringJUnit4ClassRunner.class)

    @ContextConfiguration("app-context.xml")
    public class MyTest {
        @Autowired MyBean bean;

        @Test
        public void testXyz() {
            // assertions against bean
        }
    }

!SLIDE smaller
# 3.1
    @@@java
    @RunWith(SpringJUnit4ClassRunner.class)

    @ContextConfiguration(classes=AppConfig.class)
    public class MyTest {
        @Autowired MyBean bean;

        @Test
        public void testXyz() {
            // assertions against bean
        }
    }

!SLIDE smaller
# 3.1
    @@@java
    @RunWith(SpringJUnit4ClassRunner.class)
    // ApplicationContext will be loaded from AppConfig
    @ContextConfiguration(classes=AppConfig.class)
    public class MyTest {
        @Autowired MyBean bean;

        @Test
        public void testXyz() {
            // assertions against bean
        }
    }

!SLIDE smaller
# 3.1
    @@@java
    @RunWith(SpringJUnit4ClassRunner.class)

    @ContextConfiguration(classes=AppConfig.class)
    public class MyTest {
        @Autowired MyBean bean; // injected from AppConfig

        @Test
        public void testXyz() {
            // assertions against bean
        }
    }

!SLIDE center incremental bullets
# TestContext Framework
* Fully updated for Spring 3.1
* Support for @Configuration, @Profile

!SLIDE center
Much more on testing at Sam&apos;s and Rossen&apos;s talk on
<a href="http://www.springone2gx.com/conference/chicago/2011/10/session?id=24020">Friday at 10:15</a>

!SLIDE center
# Environment API

!SLIDE center
# @Profile

!SLIDE center
# PropertySource API

!SLIDE center
# nested &lt;beans/&gt;

!SLIDE center
# c: namespace

!SLIDE center incremental bullets
# 3.1 RC1 is out
* Now is the time to test
* RC2 in a couple weeks
* GA by year end

!SLIDE subsection
# __Thanks__

!SLIDE center smaller
# _(read: a silly, naive example follows)_

!SLIDE commandline incremental
# __XML is Over!__

<br/><br/><br/><br/>
<br/><br/><br/><br/><br/>


!SLIDE commandline incremental

# __XML is Over!__
if you want it
<br/><br/>
<br/><br/><br/>


!SLIDE center

# __XML IS OVER!__
if you want it
<br/>
With Apologies to John & Yoko

!SLIDE center
![if-you-want-it.png](if-you-want-it.png)

!SLIDE commandline incremental
# __XML is Over!__

    $ find myproject/ -name '*.xml' | wc -l
    0

