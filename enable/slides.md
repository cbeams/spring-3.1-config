!SLIDE subsection
# back to the future

!SLIDE center
# Spring 3.1
<br/><br/>

!SLIDE center
# `@Enable*`

<br/><br/>
<br/><br/>

!SLIDE center
# `@Enable*`

 _(and friends)_

!SLIDE
# e.g.
    @@@ java
    @EnableTransactionManagement
    @EnableAspectJAutoProxy
    @EnableLoadTimeWeaving
    @EnableScheduling
    @EnableAsync
    @EnableWebMvc
    @ComponentScan

!SLIDE center
# Simple.

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

!SLIDE center
# Example
<br/><br/><br/><br/>
<br/><br/>

!SLIDE center
# Example
    @@@ java
    @EnableScheduling

!SLIDE center transition=fade
# (Trivial) Example
    @@@ java
    @EnableScheduling

!SLIDE smaller
# some component
    @@@ java
    package com.startup;

    public class ChatterBox {


        public void saySomething() {
            System.out.println(randomWord());
        }

    }

!SLIDE smaller
# some component
    @@@ java
    package com.startup;

    public class ChatterBox {

        @Scheduled(fixedRate=1000)
        public void saySomething() {
            System.out.println(randomWord());
        }

    }

!SLIDE smaller
# some component
    @@@ java
    package com.startup;

    public class ChatterBox {
                                   // (@Scheduled has been
        @Scheduled(fixedRate=1000) // around since Spring 3.0)
        public void saySomething() {
            System.out.println(randomWord());
        }

    }

!SLIDE smaller
# some component
    @@@ java
    package com.startup;

    public class ChatterBox {
                                   // "hey Spring, call this
        @Scheduled(fixedRate=1000) // method every second"
        public void saySomething() {
            System.out.println(randomWord());
        }

    }

!SLIDE smaller
# configuration
    @@@ java
    package com.startup.config;


    @Configuration
    public class Config {
        @Bean
        public ChatterBox chatterBox() {
            return new ChatterBox();
        }
    }

!SLIDE smaller transition=fade
# configuration
    @@@ java
    package com.startup.config;

    @Configuration
    @EnableScheduling
    public class Config {
        @Bean
        public ChatterBox chatterBox() {
            return new ChatterBox();
        }
    }

!SLIDE smaller
# configuration
    @@@ java
    package com.startup.config;

    @Configuration
    @EnableScheduling // look for and process @Scheduled
    public class Config {
        @Bean
        public ChatterBox chatterBox() {
            return new ChatterBox();
        }
    }

!SLIDE smaller
# configuration
    @@@ java
    package com.startup.config;

    @Configuration
    @EnableScheduling
    public class Config {
        @Bean
        public ChatterBox chatterBox() {
            return new ChatterBox(); // saySomething() method
        }                            // called once per second
    }

!SLIDE smaller
# configuration
    @@@ java
    package com.startup.config;

    @Configuration
    @EnableScheduling // ok, so what does this do?
    public class Config {
        @Bean
        public ChatterBox chatterBox() {
            return new ChatterBox();
        }
    }

!SLIDE smaller transition=scrollLeft
# EnableScheduling.java
    @@@ java
    package org.springframework.scheduling.annotation;

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Import(SchedulingConfiguration.class)
    @Documented
    public @interface EnableScheduling {

    }

!SLIDE smaller transition=fade
# EnableScheduling.java
    @@@ java
    package org.springframework.scheduling.annotation;



    @Import(SchedulingConfiguration.class) // this is the key =>

    public @interface EnableScheduling {

    }

!SLIDE smaller transition=scrollLeft
# SchedulingConfiguration.java
    @@@ java
    package org.springframework.scheduling.annotation;

    @Configuration
    public class SchedulingConfiguration {

        @Bean(name=SCHEDULED_ANNOTATION_PROCESSOR_BEAN_NAME)
        @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
        public ScheduledAnnotationBeanPostProcessor sabpp() {
            return new ScheduledAnnotationBeanPostProcessor();
        }

    }

!SLIDE smaller transition=fade
# SchedulingConfiguration.java
    @@@ java
    package org.springframework.scheduling.annotation;

    @Configuration
    public class SchedulingConfiguration {

        // it's just registering the @Scheduled bean
        // post processor on your behalf
        public ScheduledAnnotationBeanPostProcessor sabpp() {
            return new ScheduledAnnotationBeanPostProcessor();
        }

    }


!SLIDE incremental bullets
# moving parts
* @Import now supported at annotation level
* @Enable* is just a naming convention
* @Configuration classes shipped out of the box

!SLIDE incremental bullets
# design goal
* Build on existing concepts
* _"`@Configuration` all the way down"_

!SLIDE subsection incremental bullets
# but what about...
* transparency
* extensibility
* flexibility

!SLIDE subsection bullets
# but what about...
* transparency ... check
* extensibility
* flexibility

!SLIDE subsection bullets
# but what about...
* transparency ... check
* extensibility ... ?
* flexibility

!SLIDE subsection bullets
# but what about...
* transparency ... check
* extensibility ... ?
* flexibility ... ?

!SLIDE center
# (Not-so-trivial) Example
<br/><br/><br/>
<br/><br/><br/>

!SLIDE center
# (Not-so-trivial) Example
    @@@ java
    @EnableWebMvc
.notes throw to Rossen here

