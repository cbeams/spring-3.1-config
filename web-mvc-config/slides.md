
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
# Enable Java config in `web.xml`

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

!SLIDE incremental
# How to Customize the
# Provided Config?

* Implement `WebMvcConfigurer`
* Or extend `WebMvcConfigurerAdapter` 
* Simple, discoverable configuration API
* Equivalent to MVC namespace elements

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

https://github.com/SpringSource/greenhouse

__Package:__
com.springsource.greenhouse.config

__Also:__
src/main/webapp/WEB-INF/web.xml

!SLIDE incremental
# Simple (vs. Advanced)
# Customizations

* `WebMvcConfigurer` targets the 80% case
* A great starting point
* A higher-level configuration API
* Not a 1-for-1 with actual beans created

.notes Once again the ease-of-use vs. control issue exceptin this time not a dilemma

!SLIDE incremental
# How To See Actual
# Beans Created?

* Look in `WebMvcConfigurationSupport`
* The config imported via `@EnableWebMvc`
* Class javadoc provides a full listing
* Or just review `@Bean` methods

.notes Easier to read is relative to reading BeanDefinitionParser code with the MVC namespace

!SLIDE incremental
# How To Customize Actual
# Beans Created?

* Extend directly form `WebMvcConfigurationSupport`
* Remove `@EnableWebMvc`
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

https://github.com/rstoyanchev/spring-mvc-31-demo



