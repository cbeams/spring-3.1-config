
!SLIDE incremental bullets
# Default MVC Config

* I.e. blank `<name>-servlet.xml`
* `DispatcherServlet.properties`
* `HandlerMapping`, `HandlerAdapter` .. other default types to use
* Add your own type .. turns off defaults

.notes This is intentionally minimal and neutral. Few assumptions about what application needs.

!SLIDE incremental bullets
# Spring MVC Namespace

	@@@ xml

* More opinionated
* Targets the 80%
* Minimize boilerplate
* Serve as starting point

!SLIDE incremental bullets
# What's Behind This?

	@@@ xml

          <mvc:annotation-driven />

* Find `BeanDefinitionParser`
* Read meta-data code
* How to customize the config?
* `BeanPostProcessor` ... works, not obvious!

!SLIDE incremental bullets
# Ease-of-use vs. Control

* Transparency is key for MVC config
* So is flexibility
* __MVC Java config__ designed with those experiences in mind
* Match, not mirror namespace

.notes Many copy+paste AnnotationMethodHandlerAdapter config not seeing it overlaps with the config from <mvc:annotation-driven />

!SLIDE small
# MVC Java Config Example
## (Spring 3.1)

	@@@ java

        // Equivalent to <mvc:annotation:driven/>

        @EnableWebMvc
        @Configuration
        public class WebConfig {

        }

!SLIDE small
# `@EnableWebMvc`

    @@@ java

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.TYPE)
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

!SLIDE smaller
# Load MVC Java Config
## (Servlet 3.0)

	@@@ java

    public class MyWebAppInitializer 
                   implements WebApplicationInitializer {

      public void onStartup(ServletContext sc) 
            throws ServletException  {

        AnnotationConfigWebApplicationContext wac = new ... ;
        wac.scan("org.examples.somepackage");

        Servlet servlet = new DispatcherServlet(wac);
        Dynamic dispatcher = sc.addServlet("mvc", servlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

      }
    }


!SLIDE incremental bullets
# To Customize ...

* Implement <a href="http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurer.html">`WebMvcConfigurer`</a>
* Simple, discoverable configuration API
* Matches MVC namespace
* Or extend <a href="http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurerAdapter.html">`WebMvcConfigurerAdapter`</a>

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
# Common To Advanced

* <a href="http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurer.html">`WebMvcConfigurer`</a> is the 80% case
* Like the MVC namespace
* A common starting point
* What actual beans are created?

.notes Once again the ease-of-use vs. control issue exceptin this time not a dilemma

!SLIDE incremental bullets
# Check Provided Config

* Inspect <a href="http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurationSupport.html">`WebMvcConfigurationSupport`</a>
* The config imported via <a href="http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/web/servlet/config/annotation/EnableWebMvc.html">`@EnableWebMvc`</a>
* Class javadoc lists created beans
* Or read `@Bean` methods

.notes Easier to read is relative to reading BeanDefinitionParser code with the MVC namespace

!SLIDE incremental bullets
# Customize Provided Config

* Remove <a href="http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/web/servlet/config/annotation/EnableWebMvc.html">`@EnableWebMvc`</a>
* Extend <a href="http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurationSupport.html">`WebMvcConfigurationSupport`</a>
* Override same methods as `WebMvcConfigurer` 
* Also override `@Bean` methods!

!SLIDE smaller
# Advanced Customization

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


