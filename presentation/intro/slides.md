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

# Goals


!SLIDE commandline incremental

# __XML is Over!__
(if you want it)
<br/><br/>
<br/><br/>

    $ find myproject/ -name '*.xml' | wc -l
    0

!SLIDE subsection
# quickest. history. ever.
<br/><br/>

!SLIDE
# Spring 1.0
<br/><br/>

!SLIDE center
    @@@ xml
      <bean/>

!SLIDE center incremental bullets
    @@@ xml
      <bean/>
* simple
* general purpose
* flexible

!SLIDE smaller subsection
# But...

!SLIDE center incremental bullets
    @@@ xml
      <bean/>
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

!SLIDE center smaller
# "Spring XML namespaces"

!SLIDE center smaller incremental bullets
# Spring XML namespaces
* concise
* powerful
* easier to use

!SLIDE smaller subsection
# But...

!SLIDE center smaller incremental bullets
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

!SLIDE center smaller
# "Annotation-Driven Injection"

!SLIDE center smaller incremental bullets
# Annotation-Driven Injection
* _really_ concise, convenient

!SLIDE smaller subsection
# But...

!SLIDE smaller incremental bullets
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

!SLIDE center smaller
# _(read: a silly, naive example follows)_

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
* no special tooling required

!SLIDE smaller subsection
# But...

!SLIDE incremental bullets

# Java Configuration

* no equivalent to <namespace:*/>
* xml still required for tx mgmt, aop, etc
* no support in _TestContext_ framework

!SLIDE center incremental bullets
# @Bean

* pro: general purpose, flexible
* con: verbose, not type-safe
