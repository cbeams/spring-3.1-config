
!SLIDE subsection bullets
# Agenda

* ..
* ..
* __Web MVC Configuration__

!SLIDE incremental
# What Does It Do?

	@@@ xml

          <mvc:annotation-driven />

* Enables @Controller processing
* Provides easy starting point
* Targets the 80% case

!SLIDE incremental
# What Does It Really Do?

	@@@ xml

          <mvc:annotation-driven />

* Transparency is key for MVC config
* So is flexibility
* Numerous underlying options exist
* Ease-of-use vs. control

.notes Many copy+paste AnnotationMethodHandlerAdapter config not seeing it overlaps with the config from <mvc:annotation-driven />

!SLIDE small
# Spring MVC Java Config
## (Spring 3.1)

	@@@ java


    @EnableWebMvc
    @Configuration
    public class WebConfig {

        // @EnableWebMvc causes import of
        // WebMvcConfigurationSupport.java

        // Equivalent to using
        // <mvc:annotation:driven />

    }

!SLIDE smaller
# Load MVC Java Config in `web.xml`

	@@@ xml

        <!-- Detect @Configuration classes in a package -->

        <context-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>
                org.example.somepackage
            </param-value>
        </context-param>

        <!-- Use ApplicationContext for Java config -->

        <context-param>
            <param-name>contextClass</param-name>
            <param-value>
                org.springframework.web.context.support.
                  AnnotationConfigWebApplicationContext
            </param-value>
        </context-param>

!SLIDE smaller
# Load MVC Java Config
## (Servlet 3.0)

	@@@ java

    public class MyWebAppInitializer 
                      implements WebApplicationInitializer {

      public void onStartup(ServletContext servletContext) 
            throws ServletException  {

        AnnotationConfigWebApplicationContext wac = 
                new AnnotationConfigWebApplicationContext();

        wac.scan("org.examples.somepackage");

        Servlet servlet = new DispatcherServlet(wac);
        Dynamic disp = servletContext.addServlet("mvc", servlet);
        disp.setLoadOnStartup(1);
        disp.addMapping("/");

      }
    }


!SLIDE incremental
# How to Customize the
# Provided Config?

* Implement <a href="http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurer.html">`WebMvcConfigurer`</a>
* Or extend <a href="http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurerAdapter.html">`WebMvcConfigurerAdapter`</a>
* Simple, discoverable configuration API
* Equivalent to MVC namespace

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

      // Override more methods as necessary...

    }

!SLIDE code
# Demo 

<a href="https://github.com/SpringSource/greenhouse">https://github.com/SpringSource/greenhouse</a>

__Package:__
<a href="https://github.com/SpringSource/greenhouse/tree/master/src/main/java/com/springsource/greenhouse/config">com.springsource.greenhouse.config</a>

__Also:__
<a href="https://github.com/SpringSource/greenhouse/blob/master/src/main/webapp/WEB-INF/web.xml">src/main/webapp/WEB-INF/web.xml</a>

!SLIDE incremental
# Simple (vs. Advanced)
# Customizations

* <a href="http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurer.html">`WebMvcConfigurer`</a> targets the 80% case
* A great starting point
* A higher-level configuration API
* Not a 1-for-1 with actual beans created

.notes Once again the ease-of-use vs. control issue exceptin this time not a dilemma

!SLIDE incremental
# How To See Actual
# Beans Created?

* Look in <a href="http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurationSupport.html">`WebMvcConfigurationSupport`</a>
* The config imported via <a href="http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/web/servlet/config/annotation/EnableWebMvc.html">`@EnableWebMvc`</a>
* Class javadoc provides a full listing
* Or just review `@Bean` methods

.notes Easier to read is relative to reading BeanDefinitionParser code with the MVC namespace

!SLIDE incremental
# How To Customize Actual
# Beans Created?

* Extend directly form <a href="http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurationSupport.html">`WebMvcConfigurationSupport`</a>
* Remove <a href="http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/web/servlet/config/annotation/EnableWebMvc.html">`@EnableWebMvc`</a>
* Override base class `@Bean` methods

!SLIDE smaller
# Advanced Customization Example

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

!SLIDE code
# Demo 

<a href="https://github.com/rstoyanchev/spring-mvc-31-demo">https://github.com/rstoyanchev/spring-mvc-31-demo</a>


__Package:__
<a href="https://github.com/rstoyanchev/spring-mvc-31-demo/tree/master/src/main/java/org/springframework/samples/mvc31/config">org.springframework.samples.mvc31.config</a>



