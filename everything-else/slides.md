!SLIDE
# EVERYTHING ELSE

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

