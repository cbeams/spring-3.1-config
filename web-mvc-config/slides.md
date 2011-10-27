!SLIDE incremental bullets
# Spring MVC Configuration
## (Quick Refresher)

!SLIDE incremental bullets
# (1) Default Configuration

* I.e. blank `<name>-servlet.xml`
* `DispatcherServlet.properties`
* `HandlerMapping`, `HandlerAdapter` .. and other types to instantiate by default
* Add own type .. turns off default types

!SLIDE incremental bullets
# Default Config Experience

* Intentionally minimalistic
* Fairly neutral
* Flexible
* Can be verbose for common tasks

!SLIDE incremental bullets
# (2) MVC Namespace

* Minimize boilerplate for @MVC
* Targets the 80% use case
* More opinionated
* Serves as starting point

!SLIDE incremental bullets
# MVC Namespace Experience

* Does a number of useful things
* Minimizes boilerplate
* Transparent .. ?
* Flexible .. ?

!SLIDE incremental bullets
# What's Behind...

	@@@ xml

          <mvc:annotation-driven />

* Find `BeanDefinitionParser`
* Read meta-data code
* How to customize?
* <a href="https://jira.springsource.org/browse/SPR-8648?focusedCommentId=70352&page=com.atlassian.jira.plugin.system.issuetabpanels:comment-tabpanel#comment-70352">`BeanPostProcessor works`</a> .. not obvious!

!SLIDE incremental bullets
# Ease-of-use vs. Control

* A good starting point is important
* But transparency is key in MVC config
* So is flexibility

!SLIDE incremental bullets
# (3) MVC Java Config
## (Spring 3.1)

* Designed with preivous experiences in mind
* Match MVC namespace capabilities
* More transparent
* More flexible

.notes Many copy+paste AnnotationMethodHandlerAdapter config not seeing it overlaps with the config from <mvc:annotation-driven />

!SLIDE small
# MVC Java Config Example
## (Spring 3.1)

	@@@ java

        // Equivalent to <mvc:annotation-driven/>

        @EnableWebMvc
        @Configuration
        public class WebConfig {

        }


!SLIDE small
# MVC Java Config Example
## (Spring 3.1)

	@@@ java

        // Equivalent to <mvc:annotation-driven/>

        @EnableWebMvc   // <-- What's behind ? 
        @Configuration
        public class WebConfig {

        }

!SLIDE small transition=scrollLeft
# `@EnableWebMvc`

    @@@ java

        @Retention(RetentionPolicy.RUNTIME)
        @Import(DelegatingWebMvcConfiguration.class)
        @Target(ElementType.TYPE)
        public @interface EnableWebMvc {

        }

!SLIDE small transition=fade
# `@EnableWebMvc`

    @@@ java


        @Import(DelegatingWebMvcConfiguration.class)

        public @interface EnableWebMvc {

        }

!SLIDE smaller
# Load MVC Java Config in `web.xml`

	@@@ xml

        <!-- 1. Change ApplicationContext type -->

        <context-param>
            <param-name>contextClass</param-name>
            <param-value>
                org.springframework.web.context.support.
                  AnnotationConfigWebApplicationContext
            </param-value>
        </context-param>

        <!-- 2. Point to package with @Configuration classes -->

        <context-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>
                org.example.somepackage
            </param-value>
        </context-param>

!SLIDE incremental bullets
# Built-in Customizations

* Implement <a href="http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurer.html">`WebMvcConfigurer`</a>
* Or extend <a href="http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurerAdapter.html">`WebMvcConfigurerAdapter`</a>
* Simple, discoverable config API
* Matches MVC namespace

!SLIDE smaller
# Customization Example

	@@@ java

    @EnableWebMvc
    @Configuration
    public class WebConfig extends WebMvcConfigurerAdapter {

      @Override
      protected void addFormatters(FormatterRegistry registry) {
        // ...
      }

      @Override
      public void addInterceptors(InterceptorRegistry reg){
        // Equivalent to <mvc:interceptors>
      }

      @Override
      public void addViewControllers(ViewControllerRegistry reg) {
        // Equivalent to <mvc:view-controller>
      }

    }

!SLIDE
# Demo 
<br>
__<a href="https://github.com/SpringSource/greenhouse">https://github.com/SpringSource/greenhouse</a>__
<br>
<a href="https://github.com/SpringSource/greenhouse/blob/master/src/main/webapp/WEB-INF/web.xml">`src/main/webapp/WEB-INF/web.xml`</a><br><br>
<a href="https://github.com/SpringSource/greenhouse/tree/master/src/main/java/com/springsource/greenhouse/config">`com.springsource.greenhouse.config`</a><br><br>
<a href="http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurer.html">`WebMvcConfigurer`</a>

!SLIDE incremental bullets
# Beyond The Common Case

* <a href="http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurer.html">`WebMvcConfigurer`</a> targets the 80%
* A good starting point
* Like the MVC namespace
* Transparency .. ?
* Flexibility .. ?

.notes Once again the ease-of-use vs. control issue exceptin this time not a dilemma

!SLIDE incremental bullets
# See Provided Config

* Inspect <a href="http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurationSupport.html">`WebMvcConfigurationSupport`</a>
* The config imported via <a href="http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/web/servlet/config/annotation/EnableWebMvc.html">`@EnableWebMvc`</a>
* Class javadoc lists created beans
* Or read `@Bean` methods

.notes Easier to read is relative to reading BeanDefinitionParser code with the MVC namespace

!SLIDE incremental bullets
# Switch To Advanced Config

* Remove <a href="http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/web/servlet/config/annotation/EnableWebMvc.html">`@EnableWebMvc`</a>
* Extend <a href="http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurationSupport.html">`WebMvcConfigurationSupport`</a>
* Override the same methods as in `WebMvcConfigurer` 
* Also override any `@Bean` methods!

!SLIDE smaller
# Advanced Config Example

	@@@ java

    @Configuration
    public class WebConfig extends WebMvcConfigurationSupport {

      @Override
      public void addInterceptors(InterceptorRegistry reg){
        // Equivalent to <mvc:interceptors>
      }

      @Override
      @Bean
      public RequestMappingHandlerAdapter 
                    requestMappingHandlerAdapter() {

          // Create or let "super" create and customize 
          // RequestMappingHandlerAdapter ...

      }
    }

!SLIDE
# Demo 
<br>
__<a href="https://github.com/rstoyanchev/spring-mvc-31-demo">https://github.com/rstoyanchev/spring-mvc-31-demo</a>__
<br>
<a href="https://github.com/rstoyanchev/spring-mvc-31-demo/tree/master/src/main/java/org/springframework/samples/mvc31/config">`org.springframework.samples.mvc31.config`</a><br><br>
<a href="http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurationSupport.html">`WebMvcConfigurationSupport`</a>


