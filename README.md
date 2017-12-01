# spring-example

参照[跟我学spring3](http://www.iteye.com/blogs/subjects/spring3)练习

# 第一章 Spring概述
## 1 为什么需要Spring及Spring的优点
- **非常轻量级的容器**：以集中的、自动化的方式进行应用程序对象创建和装配，负责对象创建和装配，管理对象生命周期，能组合成复杂的应用程序。Spring容器是非侵入式的（不需要依赖任何Spring特定类），而且完全采用POJOs进行开发，使应用程序更容易测试、更容易管理。而且核心JAR包非常小，Spring3.0.5不到1M，而且不需要依赖任何应用服务器，可以部署在任何环境（Java SE或Java EE）。
- **AOP**：AOP是Aspect Oriented Programming的缩写，意思是面向切面编程，提供从另一个角度来考虑程序结构以完善面向对象编程（相对于OOP），即可以通过在编译期间、装载期间或运行期间实现在不修改源代码的情况下给程序动态添加功能的一种技术。通俗点说就是把可重用的功能提取出来，然后将这些通用功能在合适的时候织入到应用程序中；比如安全，日记记录，这些都是通用的功能，我们可以把它们提取出来，然后在程序执行的合适地方织入这些代码并执行它们，从而完成需要的功能并复用了这些功能。
- **简单的数据库事务管理**：在使用数据库的应用程序当中，自己管理数据库事务是一项很让人头疼的事，而且很容易出现错误，Spring支持可插入的事务管理支持，而且无需JEE环境支持，通过Spring管理事务可以把我们从事务管理中解放出来来专注业务逻辑。
- **JDBC抽象及ORM框架支持**：Spring使JDBC更加容易使用；提供DAO（数据访问对象）支持，非常方便集成第三方ORM框架，比如Hibernate等；并且完全支持Spring事务和使用Spring提供的一致的异常体系。
- **灵活的Web层支持**：Spring本身提供一套非常强大的MVC框架，而且可以非常容易的与第三方MVC框架集成，比如Struts等。
- **简化各种技术集成**：提供对Java Mail、任务调度、JMX、JMS、JNDI、EJB、动态语言、远程访问、Web Service等的集成。
Spring能帮助我们简化应用程序开发，帮助我们创建和组装对象，为我们管理事务，简单的MVC框架，可以把Spring看作是一个超级粘合平台，能把很多技术整合在一起，形成一个整体，使系统结构更优良、性能更出众，从而加速我们程序开发，有如上优点，我们没有理由不考虑使用它。

## 2 Spring架构
**核心容器：包括Core、Beans、Context、EL模块。**

- **Core模块**：封装了框架依赖的最底层部分，包括资源访问、类型转换及一些常用工具类。
- **Beans模块**：提供了框架的基础部分，包括反转控制和依赖注入。其中Bean Factory是容器核心，本质是`工厂设计模式`的实现，而且无需编程实现`单例设计模式`，单例完全由容器控制，而且提倡面向接口编程，而非面向实现编程；所有应用程序对象及对象间关系由框架管理，从而真正把你从程序逻辑中把维护对象之间的依赖关系提取出来，所有这些依赖关系都由BeanFactory来维护。
- **Context模块**：以Core和Beans为基础，集成Beans模块功能并添加资源绑定、数据验证、国际化、Java EE支持、容器生命周期、事件传播等；核心接口是ApplicationContext。
- **EL模块**：提供强大的表达式语言支持，支持访问和修改属性值，方法调用，支持访问及修改数组、容器和索引器，命名变量，支持算数和逻辑运算，支持从Spring 容器获取Bean，它也支持列表投影、选择和一般的列表聚合等。
 
**AOP、Aspects模块**：

- **AOP模块**：Spring AOP模块提供了符合 AOP Alliance规范的面向方面的编程（aspect-oriented programming）实现，提供比如日志记录、权限控制、性能统计等通用功能和业务逻辑分离的技术，并且能动态的把这些功能添加到需要的代码中；这样各专其职，降低业务逻辑和通用功能的耦合。
- **Aspects模块**：提供了对AspectJ的集成，AspectJ提供了比Spring ASP更强大的功能。
 
**数据访问/集成模块：该模块包括了JDBC、ORM、OXM、JMS和事务管理。**

- **事务模块**：该模块用于Spring管理事务，只要是Spring管理对象都能得到Spring管理事务的好处，无需在代码中进行事务控制了，而且支持编程和声明性的事物管理。
- **JDBC模块**：提供了一个JBDC的样例模板，使用这些模板能消除传统冗长的JDBC编码还有必须的事务控制，而且能享受到Spring管理事务的好处。
- **ORM模块**：提供与流行的`对象-关系`映射框架的无缝集成，包括Hibernate、JPA、Ibatiss等。而且可以使用Spring事务管理，无需额外控制事务。
- **OXM模块**：提供了一个对Object/XML映射实现，将java对象映射成XML数据，或者将XML数据映射成java对象，Object/XML映射实现包括JAXB、Castor、XMLBeans和XStream。
- **JMS模块**：用于JMS(Java Messaging Service)，提供一套 `消息生产者、消息消费者`模板用于更加简单的使用JMS，JMS用于用于在两个应用程序之间，或分布式系统中发送消息，进行异步通信。
- **Web/Remoting模块**：Web/Remoting模块包含了Web、Web-Servlet、Web-Struts、Web-Porlet模块。
- **Web模块**：提供了基础的web功能。例如多文件上传、集成IoC容器、远程过程访问（RMI、Hessian、Burlap）以及Web Service支持，并提供一个RestTemplate类来提供方便的Restful services访问。
- **Web-Servlet模块**：提供了一个Spring MVC Web框架实现。Spring MVC框架提供了基于注解的请求资源注入、更简单的数据绑定、数据验证等及一套非常易用的JSP标签，完全无缝与Spring其他技术协作。
- **Web-Struts模块**：提供了与Struts无缝集成，Struts1.x 和Struts2.x都支持
 
**Test模块**： Spring支持Junit和TestNG测试框架，而且还额外提供了一些基于Spring的测试功能，比如在测试Web框架时，模拟Http请求的功能。

# 第二章 IoC 容器
## IoC容器的概念
IoC容器就是具有依赖注入功能的容器，IoC容器负责实例化、定位、配置应用程序中的对象及建立这些对象间的依赖。应用程序无需直接在代码中new相关的对象，应用程序由IoC容器进行组装。在Spring中BeanFactory是IoC容器的实际代表者。
Spring IoC容器如何知道哪些是它管理的对象呢？这就需要配置文件，Spring IoC容器通过读取配置文件中的配置元数据，通过元数据对应用中的各个对象进行实例化及装配。一般使用基于xml配置文件进行配置元数据，而且Spring与配置文件完全解耦的，可以使用其他任何可能的方式进行配置元数据，比如注解、基于java文件的、基于属性文件的配置都可以。
由IoC容器管理的那些组成你应用程序的对象我们就叫它Bean， Bean就是由Spring容器初始化、装配及管理的对象，除此之外，bean就与应用程序中的其他对象没有什么区别了。那IoC怎样确定如何实例化Bean、管理Bean之间的依赖关系以及管理Bean呢？这就需要配置元数据，在Spring中由BeanDefinition代表，后边会详细介绍，配置元数据指定如何实例化Bean、如何组装Bean等。
##详解IoC容器
在Spring Ioc容器的代表就是org.springframework.beans包中的`BeanFactory`接口，`BeanFactory`接口提供了IoC容器最基本功能；而org.springframework.context包下的`ApplicationContext`接口扩展了`BeanFactory`，还提供了与Spring AOP集成、国际化处理、事件传播及提供不同层次的context实现 (如针对web应用的`WebApplicationContext`)。简单说， BeanFactory提供了IoC容器最基本功能，而 ApplicationContext 则增加了更多支持企业级功能支持。ApplicationContext完全继承BeanFactory，因而BeanFactory所具有的语义也适用于ApplicationContext。
容器实现一览：

- **XmlBeanFactory**(Spring 3.1已弃用,使用DefaultListableBeanFactory和代替} )：BeanFactory实现，提供基本的IoC容器功能，可以从classpath或文件系统等获取资源；
```
（1） 
File file = new File("fileSystemConfig.xml");
Resource resource = new FileSystemResource(file);
BeanFactory beanFactory = new XmlBeanFactory(resource);
（2）
Resource resource = new ClassPathResource("classpath.xml");                 
BeanFactory beanFactory = new XmlBeanFactory(resource);
```
```
//spring3.1后写法
Resource res = new ClassPathResource("bean.xml");
DefaultListableBeanFactory factory= new DefaultListableBeanFactory ();
XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
reader.loadBeanDefinitions(resource)
```
- **ClassPathXmlApplicationContext**：ApplicationContext实现，从classpath获取配置文件；
```
 BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath.xml")
```
- **FileSystemXmlApplicationContext**：ApplicationContext实现，从文件系统获取配置文件。
```
BeanFactory beanFactory = new FileSystemXmlApplicationContext("fileSystemConfig.xml")
```
ApplicationContext接口获取Bean方法简介：

- `Object getBean(String name)` 根据名称返回一个Bean，客户端需要自己进行类型转换；
- `T getBean(String name, Class<T> requiredType)` 根据名称和指定的类型返回一个Bean，客户端无需自己进行类型转换，如果类型转换失败，容器抛出异常；
- `T getBean(Class<T> requiredType)` 根据指定的类型返回一个Bean，客户端无需自己进行类型转换，如果没有或有多于一个Bean存在容器将抛出异常；
- `Map<String, T> getBeansOfType(Class<T> type)` 根据指定的类型返回一个键值为名字和值为Bean对象的 Map，如果没有Bean对象存在则返回空的Map。

#  第三章 DI依赖注入
## 依赖和依赖注入
传统应用程序设计中所说的依赖一般指`类之间的关系`，那先让我们复习一下类之间的关系：
 **泛化**：表示类与类之间的继承关系、接口与接口之间的继承关系；
 **实现**：表示类对接口的实现；
 **依赖**：当类与类之间有使用关系时就属于依赖关系，不同于关联关系，依赖不具有`拥有关系`，而是一种`相识关系`，只在某个特定地方（比如某个方法体内）才有关系。
 **关联**：表示类与类或类与接口之间的依赖关系，表现为`拥有关系`；具体到代码可以用实例变量来表示；
 **聚合**：属于是关联的特殊情况，体现部分-整体关系，是一种弱拥有关系；整体和部分可以有不一样的生命周期；是一种弱关联；
 **组合**：属于是关联的特殊情况，也体现了体现部分-整体关系，是一种强`拥有关系`；整体与部分有相同的生命周期，是一种强关联；
 
 Spring IoC容器的依赖有两层含义：**Bean依赖容器和容器注入Bean的依赖资源**：
- **Bean依赖容器**：也就是说Bean要依赖于容器，这里的依赖是指容器负责创建Bean并管理Bean的生命周期，正是由于由容器来控制创建Bean并注入依赖，也就是控制权被反转了，这也正是IoC名字的由来，此处的有依赖是指Bean和容器之间的依赖关系。
- **容器注入Bean的依赖资源**：容器负责注入Bean的依赖资源，依赖资源可以是Bean、外部文件、常量数据等，在Java中都反映为对象，并且由容器负责组装Bean之间的依赖关系，此处的依赖是指Bean之间的依赖关系，可以认为是传统类与类之间的`关联`、`聚合`、`组合`关系。

为什么要应用依赖注入，应用依赖注入能给我们带来哪些好处呢？

- **动态替换Bean依赖对象**，程序更灵活：替换Bean依赖对象，无需修改源文件：应用依赖注入后，由于可以采用配置文件方式实现，从而能随时动态的替换Bean的依赖对象，无需修改java源文件；
- **更好实践面向接口编程**，代码更清晰：在Bean中只需指定依赖对象的接口，接口定义依赖对象完成的功能，通过容器注入依赖实现；
- **更好实践优先使用对象组合，而不是类继**承：因为IoC容器采用注入依赖，也就是组合对象，从而更好的实践对象组合。
    - 采用对象组合，Bean的功能可能由几个依赖Bean的功能组合而成，其Bean本身可能只提供少许功能或根本无任何功能，全部委托给依赖Bean，对象组合具有动态性，能更方便的替换掉依赖Bean，从而改变Bean功能；
    - 而如果采用类继承，Bean没有依赖Bean，而是采用继承方式添加新功能，，而且功能是在编译时就确定了，不具有动态性，而且采用类继承导致Bean与子Bean之间高度耦合，难以复用。
    
- **增加Bean可复用性**：依赖于对象组合，Bean更可复用且复用更简单；
- **降低Bean之间耦合**：由于我们完全采用面向接口编程，在代码中没有直接引用Bean依赖实现，全部引用接口，而且不会出现显示的创建依赖对象代码，而且这些依赖是由容器来注入，很容易替换依赖实现类，从而降低Bean与依赖之间耦合；
- **代码结构更清晰**：要应用依赖注入，代码结构要按照规约方式进行书写，从而更好的应用一些最佳实践，因此代码结构更清晰。
 
从以上我们可以看出，其实依赖注入只是一种装配对象的手段，设计的类结构才是基础，如果设计的类结构不支持依赖注入，Spring IoC容器也注入不了任何东西，从而从根本上说`如何设计好类结构才是关键，依赖注入只是一种装配对象手段`。
前边IoC一章我们已经了解了Bean依赖容器，那容器如何注入Bean的依赖资源，Spring IoC容器注入依赖资源主要有以下两种基本实现方式：

- **构造器注入**：就是容器实例化Bean时注入那些依赖，通过在在Bean定义中指定构造器参数进行注入依赖，包括实例工厂方法参数注入依赖，但静态工厂方法参数不允许注入依赖；
- **setter注入**：通过setter方法进行注入依赖；
- **方法注入**：能通过配置方式替换掉Bean方法，也就是通过配置改变Bean方法 功能。

> JavaBean：是本质就是一个POJO类，但具有一下限制：
 该类必须要有公共的无参构造器，如public HelloImpl4() {}；
 属性为private访问级别，不建议public，如private String message;
 属性必要时通过一组setter（修改器）和getter（访问器）方法来访问；
 setter方法，以`set` 开头，后跟首字母大写的属性名，如`setMesssage`,简单属性一般只有一个方法参数，方法返回值通常为`void`;
 getter方法，一般属性以`get`开头，对于boolean类型一般以`is`开头，后跟首字母大写的属性名，如`getMesssage`，`isOk`；
 还有一些其他特殊情况，比如属性有连续两个大写字母开头，如`URL`,则setter/getter方法为：`setURL`和`getURL`，其他一些特殊情况请参看`Java Bean`命名规范。

## 配置简写

让我们来总结一下依赖注入配置及简写形式，其实我们已经在以上部分穿插着进行简化配置了：
 
1. 构造器注入：
1）常量值
简写：`<constructor-arg index="0" value="常量"/>`
全写：`<constructor-arg index="0"><value>常量</value></constructor-arg>`
2）引用
简写：`<constructor-arg index="0" ref="引用"/>`
全写：`<constructor-arg index="0"><ref bean="引用"/></constructor-arg>`
 
2. setter注入：      
1）常量值
简写：`<property name="message" value="常量"/>`
全写：`<property name="message"><value>常量</value></ property>`
2）引用
简写：`<property name="message" ref="引用"/>`
全写：`<property name="message"><ref bean="引用"/></ property>`
3）数组：`<array>`没有简写形式
4）列表：`<list>`没有简写形式
5）集合：`<set>`没有简写形式
6）字典
  简写：`
        <map>
         <entry key="键常量" value="值常量"/>
         <entry key-ref="键引用" value-ref="值引用"/>
        </map>
        `
 全写：`
    <map>
     <entry><key><value>键常量</value></key><value>值常量</value></entry>
     <entry><key><ref bean="键引用"/></key><ref bean="值引用"/></entry>
   </map>
    `
7）Properties：没有简写形式
 
3. 使用p命名空间简化setter注入：
       使用p命名空间来简化setter注入，具体使用如下：
```
<bean id="bean1" class="java.lang.String">  
        <constructor-arg index="0" value="test"/>  
</bean>  
<bean id="idrefBean1" class="cn.javass.spring.chapter3.bean.IdRefTestBean"  p:id="value"/>  
<bean id="idrefBean2" class="cn.javass.spring.chapter3.bean.IdRefTestBean"  p:id-ref="bean1"/> 
</beans> 
```
##更多DI的知识
### 延迟初始化Bean
延迟初始化也叫做惰性初始化，指不提前初始化Bean，而是只有在真正使用时才创建及初始化Bean。
配置方式很简单只需在`<bean>`标签上指定 `lazy-init` 属性值为`true`即可延迟初始化Bean。
Spring容器会在创建容器时提前初始化`singleton`作用域的Bean，`singleton`就是单例的意思即整个容器每个Bean只有一个实例，后边会详细介绍。Spring容器预先初始化Bean通常能帮助我们提前发现配置错误，所以如果没有什么情况建议开启，除非有某个Bean可能需要加载很大资源，而且很可能在整个应用程序生命周期中很可能使用不到，可以设置为延迟初始化。
延迟初始化的Bean通常会在第一次使用时被初始化；或者在被非延迟初始化Bean作为依赖对象注入时在会随着初始化该Bean时被初始化，因为在这时使用了延迟初始化Bean。
容器管理初始化Bean消除了编程实现延迟初始化，完全由容器控制，只需在需要延迟初始化的Bean定义上配置即可，比编程方式更简单，而且是无侵入代码的。
```
<bean id="helloApi"  
class="cn.javass.spring.chapter2.helloworld.HelloImpl"  
lazy-init="true"/> 
```
### 使用depends-on
depends-on是指指定Bean初始化及销毁时的顺序，使用depends-on属性指定的Bean要先初始化完毕后才初始化当前Bean，由于只有`singleton`Bean能被Spring管理销毁，所以当指定的Bean都是`singleton`时，使用depends-on属性指定的Bean要在指定的Bean之后销毁。
```
<bean id="helloApi" class="cn.javass.spring.chapter2.helloworld.HelloImpl"/>  
<bean id="decorator"  
    class="cn.javass.spring.chapter3.bean.HelloApiDecorator"  
    depends-on="helloApi">  
    <property name="helloApi"><ref bean="helloApi"/></property>  
</bean
```
`decorator`指定了`depends-on`属性为`helloApi`，所以在`decorator`Bean初始化之前要先初始化`helloApi`，而在销毁`helloApi`之前先要销毁`decorator`，大家注意一下销毁顺序，与文档上的不符。
`depends-on`属性可以指定多个Bean，若指定多个Bean可以用`;`、`,`、空格分割。
### 自动装配
自动装配就是指由Spring来自动地注入依赖对象，无需人工参与。

目前Spring3.0支持`no`、`byName `、`byType`、`constructor`四种自动装配，默认是`no`指不支持自动装配的，其中Spring3.0已不推荐使用之前版本的`autodetect`自动装配，推荐使用Java 5+支持的（@Autowired）注解方式代替；如果想支持`autodetect`自动装配，请将schema改为`spring-beans-2.5.xsd`或去掉。

自动装配的好处是减少构造器注入和setter注入配置，减少配置文件的长度。自动装配通过配置<bean>标签的`autowire`属性来改变自动装配方式。接下来让我们挨着看下配置的含义。

1. default：表示使用默认的自动装配，默认的自动装配需要在<beans>标签中使用default-autowire属性指定，其支持`no`、`byName `、`byType`、`constructor`四种自动装配，如果需要覆盖默认自动装配，请继续往下看；

2. no：意思是不支持自动装配，必须明确指定依赖。

3. byName：通过设置Bean定义属性autowire="byName"，意思是根据名字进行自动装配，只能用于setter注入。比如我们有方法`setHelloApi`，则`byName`方式Spring容器将查找名字为helloApi的Bean并注入，如果找不到指定的Bean，将什么也不注入。
    ```
    <bean id="helloApi" class="cn.javass.spring.chapter2.helloworld.HelloImpl"/>  
    <bean id="bean" class="cn.javass.spring.chapter3.bean.HelloApiDecorator"  
         autowire="byName"/>  
    ```
4. `byType`：通过设置Bean定义属性autowire="byType"，意思是指根据类型注入，用于setter注入，比如如果指定自动装配方式为`byType`，而`setHelloApi`方法需要注入HelloApi类型数据，则Spring容器将查找HelloApi类型数据，如果找到一个则注入该Bean，如果找不到将什么也不注入，如果找到多个Bean将优先注入<bean>标签`primary`属性为true的Bean，否则抛出异常来表明有个多个Bean发现但不知道使用哪个。
*根据类型找到多个Bean时，对于集合类型（如List、Set）将注入所有匹配的候选者，而对于其他类型遇到这种情况可能需要使用`autowire-candidate`属性为false来让指定的Bean放弃作为自动装配的候选者，或使用`primary`属性为true来指定某个Bean为首选Bean*
5. `constructor`：通过设置Bean定义属性autowire="constructor"，功能和`byType`功能一样，根据类型注入构造器参数，只是用于构造器注入方式，直接看例子吧：
    ```
    <bean class="cn.javass.spring.chapter2.helloworld.HelloImpl"/>  
    <!-- 自动装配候选者中的首选Bean-->  
    <bean class="cn.javass.spring.chapter2.helloworld.HelloImpl" primary="true"/>  
    <bean id="bean"  
         class="cn.javass.spring.chapter3.bean.HelloApiDecorator"  
         autowire="constructor"/>  
    ```
6. autodetect：自动检测是使用`constructor`还是`byType`自动装配方式，已不推荐使用。如果Bean有空构造器那么将采用`byType`自动装配方式，否则使用`constructor`自动装配方式。此处要把3.0的xsd替换为2.5的xsd，否则会报错。

**可以采用在`<beans>`标签中通过`default-autowire`属性指定全局的自动装配方式，即如果default-autowire=`byName`，将对所有Bean进行根据名字进行自动装配。**

**不是所有类型都能自动装配：**

- 不能自动装配的数据类型：Object、基本数据类型（Date、CharSequence、Number、URI、URL、Class、int）等；
- 通过`<beans>`标签default-autowire-candidates属性指定的匹配模式，不匹配的将不能作为自动装配的候选者，例如指定`*Service，*Dao`，将只把匹配这些模式的Bean作为候选者，而不匹配的不会作为候选者；
- 通过将`<bean>`标签的autowire-candidate属性可被设为false，从而该Bean将不会作为依赖注入的候选者。

**数组、集合、字典类型的根据类型自动装配和普通类型的自动装配是有区别的：**

- 数组类型、集合（Set、Collection、List）接口类型：将根据泛型获取匹配的所有候选者并注入到数组或集合中，如`List<HelloApi> list`将选择所有的HelloApi类型Bean并注入到list中，而对于集合的具体类型将只选择一个候选者，`如 ArrayList<HelloApi> list`将选择一个类型为ArrayList的Bean注入，而不是选择所有的HelloApi类型Bean进行注入；
- 字典（Map）接口类型：同样根据泛型信息注入，键必须为String类型的Bean名字，值根据泛型信息获取，如`Map<String, HelloApi> map` 将选择所有的HelloApi类型Bean并注入到map中，而对于具体字典类型如`HashMap<String, HelloApi> map`将只选择类型为HashMap的Bean注入，而不是选择所有的HelloApi类型Bean进行注入。

什么是作用域呢？即`scope`，在面向对象程序设计中一般指对象或变量之间的可见范围。而在Spring容器中是指其创建的Bean对象相对于其他Bean对象的请求可见范围。
Spring提供`singleton`和`prototype`两种基本作用域，另外提供`request`、`session`、`global session`三种web作用域；Spring还允许用户定制自己的作用域。
### 依赖检查
用于检查Bean定义的属性都注入数据了，不管是自动装配的还是配置方式注入的都能检查，如果没有注入数据将报错，从而提前发现注入错误，只检查具有setter方法的属性。
Spring3+也不推荐配置方式依赖检查了，建议采用Java5+ @Required注解方式

## Bean的作用域
### 基本的作用域
1. **singleton**：指`singleton`作用域的Bean只会在每个Spring IoC容器中存在一个实例，而且其完整生命周期完全由Spring容器管理。对于所有获取该Bean的操作Spring容器将只返回同一个Bean。Spring是注册表单例设计模式的实现，消除了编程式单例，而且对代码是非入侵式。
接下来让我们看看在Spring中如何配置单例Bean吧，在Spring容器中如果没指定作用域默认就是`singleton`，配置方式通过scope属性配置，具体配置如下
```
<bean  class="cn.javass.spring.chapter3.bean.Printer" scope="singleton"/>
```
2. **prototype**：即原型，指每次向Spring容器请求获取Bean都返回一个全新的Bean，相对于`singleton`来说就是不缓存Bean，每次都是一个根据Bean定义创建的全新Bean。只需指定<bean>标签属性`scope`属性为`prototype`即可
```
<bean class="cn.javass.spring.chapter3.bean.Printer" scope="prototype"/>
```
### Web应用中的作用域
在Web应用中，我们可能需要将数据存储到request、session、global session。因此Spring提供了三种Web作用域：request、session、globalSession。
 
1. request作用域：表示每个请求需要容器创建一个全新Bean。比如提交表单的数据必须是对每次请求新建一个Bean来保持这些表单数据，请求结束释放这些数据。
2. session作用域：表示每个会话需要容器创建一个全新Bean。比如对于每个用户一般会有一个会话，该用户的用户信息需要存储到会话中，此时可以将该Bean配置为web作用域。
3. globalSession：类似于session作用域，只是其用于portlet环境的web应用。如果在非portlet环境将视为session作用域。
 
配置方式和基本的作用域相同，只是必须要有web环境支持，并配置相应的容器监听器或拦截器从而能应用这些作用域，我们会在集成web时讲解具体使用，大家只需要知道有这些作用域就可以了。

# 第四章 资源
Spring 提供一个Resource接口来统一这些底层资源一致的访问，而且提供了一些便利的接口，从而能提供我们的生产力。 Spring的Resource接口代表底层外部资源，提供了对底层外部资源的一致性访问接口。
##  内置Resource实现
1. **ByteArrayResource**
    ByteArrayResource代表byte[]数组资源，对于`getInputStream`操作将返回一个ByteArrayInputStream。 ByteArrayResource可多次读取数组资源，即isOpen ()永远返回false。
2. **InputStreamResource**
   InputStreamResource代表java.io.InputStream字节流，对于`getInputStream `操作将直接返回该字节流，因此只能读取一次该字节流，即`isOpen`永远返回true。
3. **FileSystemResource**
   FileSystemResource代表java.io.File资源，对于`getInputStream `操作将返回底层文件的字节流，`isOpen`将永远返回false，从而表示可多次读取底层文件的字节流。
4. **ClassPathResource**
   ClassPathResource代表classpath路径的资源，将使用ClassLoader进行加载资源。classpath 资源存在于类路径中的文件系统中或jar包里，且`isOpen`永远返回false，表示可多次读取资源。
   ClassPathResource加载资源替代了Class类和ClassLoader类的`getResource(String name)`和`getResourceAsStream(String name)`两个加载类路径资源方法，提供一致的访问方式。
5. **UrlResource**
   UrlResource代表URL资源，用于简化URL资源访问。`isOpen`永远返回false，表示可多次读取资源。
6. **ServletContextResource**
   ServletContextResource代表web应用资源，用于简化servlet容器的ServletContext接口的getResource操作和getResourceAsStream操作
7. **VfsResource**
   VfsResource代表Jboss 虚拟文件系统资源。
##  ResourceLoader接口
ResourceLoader接口用于返回Resource对象；其实现可以看作是一个生产Resource的工厂类。
 getResource接口用于根据提供的location参数返回相应的Resource对象；而getClassLoader则返回加载这些Resource的ClassLoader。
 
Spring提供了一个适用于所有环境的DefaultResourceLoader实现，可以返回ClassPathResource、UrlResource；还提供一个用于web环境的ServletContextResourceLoader，它继承了DefaultResourceLoader的所有功能，又额外提供了获取ServletContextResource的支持。

**ResourceLoader在进行加载资源时需要使用前缀来指定需要加载**：`classpath:path`表示返回ClasspathResource，`http://path`和`file:path`表示返回UrlResource资源，如果不加前缀则需要根据当前上下文来决定，DefaultResourceLoader默认实现可以加载classpath资源
对于目前所有ApplicationContext都实现了ResourceLoader，因此可以使用其来加载资源。
`ClassPathXmlApplicationContext`：不指定前缀将返回默认的ClassPathResource资源，否则将根据前缀来加载资源；
`FileSystemXmlApplicationContext`：不指定前缀将返回FileSystemResource，否则将根据前缀来加载资源；
`WebApplicationContext`：不指定前缀将返回ServletContextResource，否则将根据前缀来加载资源；
其他：不指定前缀根据当前上下文返回Resource实现，否则将根据前缀来加载资源
## ResourceLoaderAware接口
ResourceLoaderAware是一个标记接口，用于通过ApplicationContext上下文注入ResourceLoader。
##注入Resource
Spring提供了一个PropertyEditor `ResourceEditor`用于在注入的字符串和Resource之间进行转换。因此可以使用注入方式注入Resource。

ResourceEditor完全使用ApplicationContext根据注入的路径字符串获取相应的Resource，说白了还是自己做还是容器帮你做的问题。
```
<bean id="resourceBean1" class="cn.javass.spring.chapter4.bean.ResourceBean3">
   <property name="resource" value="cn/javass/spring/chapter4/test1.properties"/>
</bean>

<bean id="resourceBean2" class="cn.javass.spring.chapter4.bean.ResourceBean3">
   <property name="resource" value="classpath:cn/javass/spring/chapter4/test1.properties"/>
</bean>
```
## 使用路径通配符加载Resource
前面介绍的资源路径都是非常简单的一个路径匹配一个资源，Spring还提供了一种更强大的Ant模式通配符匹配，从能一个路径匹配一批资源。
 
Ant路径通配符支持`？`、`*`、`**`，**注意通配符匹配不包括目录分隔符`/`**：

> - `?`：匹配一个字符，如`config?.xml`将匹配`config1.xml`；
> - `*`：匹配零个或多个字符串，如`cn/*/config.xml`将匹配`cn/javass/config.xml`，但不匹配匹配`cn/config.xml`；而`cn/config-*.xml`将匹配`cn/config-dao.xml`；
> - `**`：匹配路径中的零个或多个目录，如`cn/**/config.xml`将匹配`cn /config.xml`，也匹配`cn/javass/spring/config.xml`；而`cn/javass/config-**.xml`将匹配`cn/javass/config-dao.xml`，即把`**`当做两个`*`处理。

Spring提供AntPathMatcher来进行Ant风格的路径匹配。
Spring在加载类路径资源时除了提供前缀`classpath:`的来支持加载一个Resource，还提供一个前缀`classpath*:`来支持加载所有匹配的类路径Resource。
 
Spring提供ResourcePatternResolver接口来加载多个Resource，该接口继承了ResourceLoader并添加了`Resource[] getResources(String locationPattern)`用来加载多个Resource：
```
public interface ResourcePatternResolver extends ResourceLoader {  
       String CLASSPATH_ALL_URL_PREFIX = "classpath*:";  
       Resource[] getResources(String locationPattern) throws IOException;  
}  
```
Spring提供了一个ResourcePatternResolver实现PathMatchingResourcePatternResolver，它是基于模式匹配的，默认使用AntPathMatcher进行路径匹配，它除了支持ResourceLoader支持的前缀外，还额外支持`classpath*:`用于加载所有匹配的类路径Resource，ResourceLoader不支持前缀`classpath*:`：

- `classpath`： 用于加载类路径（包括jar包）中的一个且仅一个资源；对于多个匹配的也只返回一个，所以如果需要多个匹配的请考虑`classpath*:`前缀；
- `classpath*`： 用于加载类路径（包括jar包）中的所有匹配的资源。带通配符的classpath使用`ClassLoader`的`Enumeration<URL> getResources(String name)`方法来查找通配符之前的资源，然后通过模式匹配来获取匹配的资源。如`classpath:META-INF/*.LIST`将首先加载通配符之前的目录`META-INF`，然后再遍历路径进行子路径匹配从而获取匹配的资源。
    ```
    @Test  
    public void testClasspathAsteriskPrefixLimit() throws IOException {  
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();      //将首先通过ClassLoader.getResources("")加载目录，  
        //将只返回文件系统的类路径不返回jar的跟路径  
        //然后进行遍历模式匹配  
        Resource[] resources = resolver.getResources("classpath*:asm-*.txt");  
        Assert.assertTrue(resources.length == 0);  
        //将通过ClassLoader.getResources("asm-license.txt")加载  
        //asm-license.txt存在于com.springsource.net.sf.cglib-2.2.0.jar  
        resources = resolver.getResources("classpath*:asm-license.txt");  
        Assert.assertTrue(resources.length > 0);       
        //将只加载文件系统类路径匹配的Resource  
        resources = resolver.getResources("classpath*:LICENS*");  
        Assert.assertTrue(resources.length == 1);  
    }  
    ```
    对于`resolver.getResources("classpath*:asm-*.txt");`，由于在项目`resources`目录下没有所以应该返回0个资源；`resolver.getResources("classpath*:asm-license.txt");`将返回jar包里的Resource；`resolver.getResources("classpath*:LICENS*");`，因为将只返回文件系统类路径资源，所以返回1个资源。
     
    因此加载通配符路径时（即路径中包含通配符），必须包含一个根目录才能保证加载的资源是所有的，而不是部分。
- `file`：加载一个或多个文件系统中的Resource。如`file:D:/*.txt`将返回D盘下的所有txt文件；      
- **无前缀**：通过ResourceLoader实现加载一个资源。
 
AppliacationContext提供的getResources方法将获取资源委托给ResourcePatternResolver实现，默认使用PathMatchingResourcePatternResolver。所有在此就无需介绍其使用方法了。

## 注入Resource数组
Spring还支持注入Resource数组，直接看配置如下：
```
<bean id="resourceBean1" class="cn.javass.spring.chapter4.bean.ResourceBean4">  
<property name="resources">  
        <array>  
            <value>cn/javass/spring/chapter4/test1.properties</value>  
            <value>log4j.xml</value>  
        </array>  
    </property>  
</bean>  
<bean id="resourceBean2" class="cn.javass.spring.chapter4.bean.ResourceBean4">  
<property name="resources" value="classpath*:META-INF/INDEX.LIST"/>  
</bean>  
<bean id="resourceBean3" class="cn.javass.spring.chapter4.bean.ResourceBean4">  
<property name="resources">  
        <array>  
            <value>cn/javass/spring/chapter4/test1.properties</value>  
            <value>classpath*:META-INF/INDEX.LIST</value>  
        </array>  
    </property>  
</bean>
```
 `resourceBean1`就不用多介绍了，传统实现方式；对于`resourceBean2`则使用前缀`classpath*`，看到这大家应该懂的，加载匹配多个资源；`resourceBean3`是混合使用的；测试代码在`cn.javass.spring.chapter4.ResourceInjectTest.testResourceArrayInject`。
Spring通过ResourceArrayPropertyEditor来进行类型转换的，而它又默认使用`PathMatchingResourcePatternResolver`来进行把路径解析为Resource对象。所有大家只要会使用`PathMatchingResourcePatternResolver`，其它一些实现都是委托给它的，比如AppliacationContext的`getResources`方法等。
## AppliacationContext实现对各种Resource的支持
1. ClassPathXmlApplicationContext：默认将通过classpath进行加载返回ClassPathResource，提供两类构造器方法：
    ```
    public class ClassPathXmlApplicationContext {  
        //1）通过ResourcePatternResolver实现根据configLocation获取资源  
           public ClassPathXmlApplicationContext(String configLocation);  
           public ClassPathXmlApplicationContext(String... configLocations)；  
           public ClassPathXmlApplicationContext(String[] configLocations, ……);  
            
        //2）通过直接根据path直接返回ClasspathResource  
           public ClassPathXmlApplicationContext(String path, Class clazz);  
           public ClassPathXmlApplicationContext(String[] paths, Class clazz);  
           public ClassPathXmlApplicationContext(String[] paths, Class clazz, ……);  
    }  
    ```

2. FileSystemXmlApplicationContext：将加载相对于当前工作目录的`configLocation`位置的资源，注意在linux系统上不管`configLocation`是否带`/`，都作为相对路径；而在window系统上如`D:/resourceInject.xml`是绝对路径。因此在除非很必要的情况下，不建议使用该ApplicationContext。
   
    ```
    //linux系统，以下全是相对于当前vm路径进行加载  
    new FileSystemXmlApplicationContext("chapter4/config.xml");  
    new FileSystemXmlApplicationContext("/chapter4/confg.xml");  
    
    ////windows系统，第一个将相对于当前vm路径进行加载；  
    //第二个则是绝对路径方式加载  
    new FileSystemXmlApplicationContext("chapter4/config.xml");  
    new FileSystemXmlApplicationContext("d:/chapter4/confg.xml");  
    ```
    
    此处还需要注意：在linux系统上，构造器使用的是相对路径，而ctx.getResource()方法如果以`/`开头则表示获取绝对路径资源，而不带前导`/`将返回相对路径资源。如下：
        
    ```
    //linux系统，第一个将相对于当前vm路径进行加载；  
    //第二个则是绝对路径方式加载  
    ctx.getResource ("chapter4/config.xml");  
    ctx.getResource ("/root/confg.xml");  
    //windows系统，第一个将相对于当前vm路径进行加载；  
    //第二个则是绝对路径方式加载  
    ctx.getResource ("chapter4/config.xml");  
    ctx.getResource ("d:/chapter4/confg.xml");  
    ```
    
因此如果需要加载绝对路径资源最好选择前缀`file`方式，将全部根据绝对路径加载。如在linux系统`ctx.getResource ("file:/root/confg.xml");`    


