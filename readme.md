

--Spring Boot中使用Swagger2构建强大的RESTful API文档
–––––由于Spring Boot能够快速开发、便捷部署等特性，相信有很大一部分
Spring Boot的用户会用来构建RESTful API。而我们构建RESTful API的目的通常都是
由于多终端的原因，这些终端会共用很多底层业务逻辑，因此我们会抽象出这样一层来同时服务
于多个移动端或者Web前端。
这样一来，我们的RESTful API就有可能要面对多个开发人员或多个开发团队：IOS开发、
Android开发或是Web开发等。为了减少与其他团队平时开发期间的频繁沟通成本，
传统做法我们会创建一份RESTful API文档来记录所有接口细节，然而这样的做法有以下
几个问题：
由于接口众多，并且细节复杂（需要考虑不同的HTTP请求类型、HTTP头部信息、HTTP请求内容等），高质量地创建这份文档本身就是件非常吃力的事，
下游的抱怨声不绝于耳。
随着时间推移，不断修改接口实现的时候都必须同步修改接口文档，而文档与代码又处于两个不同的媒介，
除非有严格的管理机制，不然很容易导致不一致现象。
为了解决上面这样的问题，本文将介绍RESTful API的重磅好伙伴Swagger2，它可以轻松的整合到Spring
Boot中，并与Spring MVC程序配合组织出强大RESTful API文档。它既可以减少我们创建
文档的工作量，同时说明内容又整合入实现代码中，让维护文档和修改代码整合为一体，可以让
我们在修改代码逻辑的同时方便的修改文档说明。另外Swagger2也提供了强大的页面测试功能
来调试每个RESTful API
再通过createRestApi函数创建Docket的Bean之后，apiInfo()用来创建该Api的基本信息
（这些基本信息会展现在文档页面中）。select()函数返回一个ApiSelectorBuilder
实例用来控制哪些接口暴露给Swagger来展现，本例采用指定扫描的包路径来定义，
Swagger会扫描该包下所有Controller定义的API，并产生文档内容
（除了被@ApiIgnore指定的请求）

   Spring Boot中Web应用的统一异常处理
   --GlobalExceptionHandler
   创建全局异常处理类：通过使用@ControllerAdvice定义统一的异常处理类，
   而不是在每个Controller中逐个定义。@ExceptionHandler用来定义函数针对的
   异常类型，最后将Exception对象和请求URL映射到error.html中

   实现error.html页面展示：在templates目录下创建error.html，将请求的URL和Exception对象的message输出。
   通过实现上述内容之后，我们只需要在Controller中抛出Exception，当然我们可能会有多种不同的Exception。
   然后在@ControllerAdvice类中，根据抛出的具体Exception类型匹配@ExceptionHandler中配置的异常类型来匹配错误映射和处理。
   --返回JSON格式
     在上述例子中，通过@ControllerAdvice统一定义不同Exception映射到不同错误处理页面。而当我们要实现RESTful API时，返回的错误是JSON格式的数据，
     而不是HTML页面，这时候我们也能轻松支持。
     本质上，只需在@ExceptionHandler之后加入@ResponseBody，
     就能让处理函数return的内容转换为JSON格式。

问题：spring接入swagger后单元测试报错
   就是要测试用例上面加@WebAppConfiguration注解

   spring.jpa.properties.hibernate.hbm2ddl.auto
   是hibernate的配置属性，其主要作用是：自动创建、更新、验证数据库表结构。该参数的几种配置如下：
   create：每次加载hibernate时都会删除上一次的生成的表，然后根据你的model类再重新来生成新表，哪怕两次没有任何改变也要这样执行，这就是导致数据库表数据丢失的一个重要原因。
   create-drop：每次加载hibernate时根据model类生成表，但是sessionFactory一关闭,表就自动删除。
   update：最常用的属性，第一次加载hibernate时根据model类会自动建立起表的结构（
   前提是先建立好数据库），以后加载hibernate时根据model类自动更新表结构，
   即使表结构改变了但表中的行仍然存在不会删除以前的行。要注意的是当部署到服务器后，
   表结构是不会被马上建立起来的，是要等应用第一次运行起来后才会。自动创建表
   validate：每次加载hibernate时，验证创建数据库表结构，只会和数据库中的表进行比较，不会创建新表，但是会插入新值。


   它们分别实现了按name查询User实体和按name和age查询User实体
   ，可以看到我们这里没有任何类SQL语句就完成了两个条件查询方法。这就是
   Spring-data-jpa的一大特性：通过解析方法名创建查询。
   除了通过解析方法名来创建查询外，它也提供通过使用@Query 注解来创建查询，
   您只需要编写JPQL语句，并通过类似“:name”来映射@Param指定的参数，
   就像例子中的第三个findUser函数一样。

   spring boot多数据源配置与使用

   但是往往随着业务量发展，我们通常会进行数据库拆分或是引入其他数据库，
   从而我们需要配置多个数据源，下面基于之前的JdbcTemplate和Spring-data-jpa
   例子分别介绍两种多数据源的配置方式。

redis
   StringRedisTemplate对象进行Redis的读写操作，该对象从命名中就可注意到支持的是
   String类型。如果有使用过spring-data-redis的开发者一定熟悉RedisTemplate<K,
    V>接口，StringRedisTemplate就相当于RedisTemplate<String, String>的实现。
   其他类型处理Object to String
    除了String类型，实战中我们还经常会在Redis中存储对象，这时候我们就会想是否可以
    使用类似RedisTemplate<String, User>来初始化并进行操作。但是Spring Boot
    并支持直接使用，需要我们自己实现RedisSerializer<T>接口来对传入对象进行序列化
    和反序列化，下面我们通过一个实例来完成对象的读写操作。

mongodb
   较常见的，我们可以直接用MongoDB来存储键值对类型的数据，如：
   验证码、Session等；由于MongoDB的横向扩展能力，也可以用来存储数据规模
   会在未来变的非常巨大的数据，如：日志、评论等；由于MongoDB存储数据的弱类型，
   也可以用来存储一些多变json数据，如：与外系统交互时经常变化的JSON报文。
   而对于一些对数据有复杂的高事务性要求的操作，如：账户交易等就不适合使用MongoDB
   来存储。

   创建用户
   use test
   db.createUser({user:"mongo",pwd:"123123",roles:["readWrite","dbAdmin"]})
