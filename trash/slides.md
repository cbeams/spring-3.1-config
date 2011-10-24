

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

!SLIDE smaller subsection
# all together now

!SLIDE smaller
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

!SLIDE smaller
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
# configuration class
    @@@ java
    @Configuration
    @ComponentScan("com.win") // register @Component, @Repository
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
# configuration class
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
# configuration class
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
# configuration class
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

!SLIDE incremental commandline
    $ find myproject/ -name '*.xml' | wc -l
    0
<br/><br/><br/>

