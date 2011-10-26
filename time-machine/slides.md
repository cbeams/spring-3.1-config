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
* now widely used
* especially for MVC `@Controllers`

!SLIDE smaller subsection
# But...

!SLIDE incremental bullets
# Annotation-Driven Injection
* can&apos;t wire up third-party code
* ambiguities can arise (enter `@Qualifier`)
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
# bean definition
    @@@ java
    @Import(OtherConfig.class) // =~ <import/>
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
    @Import(OtherConfig.class)
    @Configuration
    public class AppConfig {
        @Autowired QuoteSource quoteSource; // from OtherConfig
        @Bean
        public QuoteService quoteService() {
            RealTimeQuoteService quoteService = ...;

            return quoteService;
        }

    }

!SLIDE smaller
# bean definition
    @@@ java
    @Import(OtherConfig.class)
    @Configuration
    public class AppConfig {
        @Autowired QuoteSource quoteSource;
        @Bean
        public QuoteService quoteService() {
            RealTimeQuoteService quoteService = ...;
            quoteService.setQuoteSource(quoteSource); // inject
            return quoteService;
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

        System.out.println(quoteService.currentValue("AAPL"));

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

        System.out.println(quoteService.currentValue("AAPL"));

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

        System.out.println(quoteService.currentValue("AAPL"));

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
        System.out.println(quoteService.currentValue("AAPL"));

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

* no equivalent to `<namespace:*/>`
* xml still required for tx mgmt, aop, etc
* no support in _TestContext_ framework


