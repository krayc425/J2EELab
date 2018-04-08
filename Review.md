# J2EE 复习

# 1. 概述

## 企业应用定义

企业应用程序提供了一个企业的业务逻辑。
他们是集中管理的，经常与其他企业软件交互；在信息技术领域的应用，企业必须设计，建造，并产生更少的钱，以更高的速度，和更少的资源。

## JavaEE 是按分布式多层应用模型设计的规范，包括哪些层，不同的层上运行什么组件

* 客户层：运行在客户端机器上的客户端组件
* Web 层：运行在 J2EE 服务器上的 Web 层组件 
* 业务层：运行在 J2EE 服务器上的业务逻辑层组件
* 企业信息系统层：运行在 EIS 服务器上的企业信息系统层软件

## JavaEE 组件

### 定义

采用 Java 语言编写，像 Java 类一样编译，将相关类和文件打包成的独立的功能单元，可以和其他组件交互，可以集成部署到服务器当中运行，JavaEE 应用是由组件构成。

### 和标准 Java 类有什么区别

* 组件按照 JavaEE 的规范被编译成 JavaEE 应用
* 可以发布部署到服务器中运行
* 可以提供安全，事务管理，JNDI 寻址，远程连接，生命周期管理，数据库连接操作等功能
* 普通 Java 类按 J2SE 的编译规范编译为`.class`文件，不能发布部署的服务器（容器）中运行

## 容器

### 定义

容器是支持组件和底层平台功能的接口。（JavaEE 组件要编译成模块，发布到容器中运行，通过配置一些文件就可以提供安全，事务管理，JDNI 查找和远程连接的服务）

### 提供的底层服务

* Container Services 
* Configurable Services 
* Nonconfigurable Services

#### 可配置的

* 安全性——配置用户的访问权限;
* 事务管理——配置由哪些操作来组成一个事务单元
* JNDI 查找——提供统一的接口让应用查找服务
* 远程连接——管理客户端和企业 Bean（EJB）之间的低层通信，一个 EJB 被创建，Client 可以 invoke 其中的方法就像 EJB 在 Client 的同一个 JVM 中一样，代理模式

#### 不可配置的

* EJB
* Servlet 生命周期 
* 数据库连接资源池 
* JavaEE 平台 API

## 打包和部署

### 四类 JavaEE 模块

1. Web 模块，`*.war` 包括 Servlet 类文件，JSP 页面文件，支持类文件，GIF 和 HTML 文件，web 应用部署描述符文件（XML 形式的配置文件）
2. EJB 模块，`*.jar` 包括 EJB 文件，EJB 配置文件（EJB 部署描述符）
3. Application Client 模块，`*.jar` 包括相关类文件，应用客户端配置文件（部署描述符）
4. Resource Adapter 模块，`*.rar` 包括所有的 Java 接口，类，本地库，文档，资源适配描述文件（资源部署描述符）

# 2. Servlet

## Web 应用

### 定义

Web 应用是对 Web 或应用服务器的动态扩展，有两种类型

* 面向表示：包括用于交互的 Web Pages（HTML，XHMTL，XML），用于响应用户的动态内容
* 面向服务：实现了 Web 的端点服务

### Web 应用和企业应用的不同

> 不同

### Web 应用的文件（目录）结构

![](https://ws2.sinaimg.cn/large/006tNc79ly1fq2qunmnznj30ow0jhn1p.jpg)

* 根目录 document root：存放 HTML，XHTML 页面，客户端类和文档，静态资源例如图片等的目录
* WEB-INF目录
    * 已经编译的 class 服务器端的类文件夹（Servlets, enterprise Bean class files, utility classes, and JavaBeans components）
    * tags 标签文件夹
    * lib 引用类库文件夹
    * 配置类文件（例如 web.xml，ejb-jar.xml）

### `web.xml` 包含哪些信息

1. 标题：DOCTYPE 声明，告诉服务器适用的 Servlet 规范的版本，指定 DTD 
2. `<web-app>`：主正文：根元素 web-app
3. `<servlet>`：应包括的 Servlet 类
4. `<servlet-mapping>`：指定 Servlet 可以映射到哪种 URL 模式
5. `<servlet-name>`：注册名
6. `<servlet-class>`：类名
7. `<url-pattern>`：URL模式

## Servlet

> 定义？
 
## Web 应用请求响应模型

1. 客户端向 Web 服务器发送一个 HTTP 请求
2. 实现了 Java Servlet 和 JavaServer Pages 技术的服务器将请求转换为一个 HttpServletRequest 对象
3. 这个对象被投递到一个 Web 组件，这个组件可以与 JavaBeans 组件或者一个数据库相互协作生成一个动态内容（dynamic content）
4. 这个组件可以生成一个 HttpServletResponse 或者将这个请求转交给其他 Web 组件
5. Web 组件最终生成一个 HttpServletResponse 对象
6. Web 服务器将这个对象转换成 HTTP 应答消息返回给客户端 

### 生命周期

Servlet 的生命周期由 Servlet 所部署的容器控制，当一个客户端请求发送到服务器时，容器开始执行以下步骤

* 如果 Servlet 实例不存在
    * 载入 Servlet 类
    * 创建一个 Servlet 的实例，一次只初始化一个 Servlet 实例
    * 调用 `init()` 方法初始化这个实例
* 调用 Service 的方法，传递 request 和 response 对象
* 如果容器需要移除这个 Servlet，那么他就会通过调用 Servlet 的 `destroy()` 方法来释放它

### URL 包含部分

`http://[host]:[port][request-path]?[query-string]` 

* request-path
    * 上下文路径：向前的斜线/和 Servlet 的 Web 应用的上下文根的拼接。
    * Servlet 路径：与激活该请求的组件别名相应的路径部分，由向前的斜线/开始。 
    * 路径信息：请求路径的部分，不是上下文路径或者 Servlet 路径的部分。
* 查询字符串：查询的参数

### URL 和 URI 的区别

URI 是从虚拟根路径开始的，URL 是整个链接

### Servlet 线程安全

1. Servlet 默认是多线程的，Server 创建一个实例，用它处理并发请求——编写线程安全的类，避免使用可以修改的类变量和实例变量
2. 实例变量是在队中分配的，不是线程安全的
3. Request，Response 是线程安全的
4. 局部变量是在栈中分配的，是线程安全的（局部变量可以作为参数传递）

## Tomcat

### 组成

![](https://ws1.sinaimg.cn/large/006tNc79ly1fq0p14lakuj30wg0ecnji.jpg)

### 组件的作用

1. Tomcat 是基于组件的服务器，构成组件可配置在 `<tomcat_home>\conf\server.xml`
2. Server 代表一个服务器，可以包含多个 Service
3. Service 代表服务，可以包含一个 Engine，多个 Connector
4. Connector 代表通信接口，在某一个指定端口监听用户请求，并且将获得的请求交给 Engine 来处理
5. Engine 可以包含多个 Host，将获得的请求匹配到某个虚拟主机上，并且把请求交给该 Host 来处理
6. Host 可包含多个 Context，代表虚拟主机，每一个都和某个网络域名相匹配，每一个都可部署多个 Web 应用
7. Context 对应一个 Web 应用，由一些 Servlet，HTML，Java 类，JSP 页面和一些其他的资源组成，在创建时根据`<Tomcat_home>\conf\web.xml`获得和`<Webapp_home>/WEB-INF/web.xml`载入 Servlet 类，在请求时查询映射表找到被请求 Servlet 类并且执行以获得请求回应

### 处理 HTTP 请求步骤

1. 请求被发送到本机端口 8080，被 Java HTTP Connector 获得
2. Connector 将该请求交给它所在的 Service 的 Engine 来处理，并等待 Engine 的回应
3. Engine 获得请求，匹配所有虚拟主机
4. Engine 匹配到名为 localhost 的主机
5. localhost 主机获得请求，匹配所拥有的所有 Context
6. localhost 主机匹配到路径为/HelloWorld 的 Context
7. 路径为 /HelloWorld 的 Context 获得请求，在映射表中寻找对应的 Servlet
8. Context 匹配到 URL Pattern 为/的 Servlet
9. 构造 HttpServletRequest 对象和 HttpServletResponse 对象，作为参数调用该 Servlet 的 Service 方法
10. Context 把执行完之后的 HttpServletResponse 对象返回给 localhost 主机
11. Host 把 HttpServletResponse 对象返回给 Engine
12. Engine 把 HttpServletResponse 对象返回给 Connector
13. Connector 把 HttpServletResponse 对象返回给客户 Browser

# 3. Session

## 两种跟踪机制

* Web 应用采用 Session 来跟踪应用的状态，因为 HTTP 是无状态的，有需求要维持状态
* Session 被表示为 HTTPSession 对象，可以将一个 object-valued 的属性关联到 Session 中，这样属性可以在同一个 Web 应用的不同地方使用
* 因为 Client 端不会发 signal 不需要一个 Session 了，所以 Session 有 timeout 机制，过时失效
* Web 容器在 Client 和 Server 端传递一个标识符（sessionid）来维护 Session 的状态，在 Client 端这个标识符可以实现为 Cookie 机制，或者在服务端采用 URL 重写机制来维护 Session，将 Session 信息写入 URL 中。一般选择 URL 重写机制，因为 cookie 在客户端可能不启用
* Session 实现两种机制：Cookie 和 URL 重写

### Cookie 过程

1. 当用户第一次访问站点→创建一个新的会话对象（HttpSession），**Server** 分配一个唯一的会话标识号（sessionID）
    * Servlet 容器自动处理 sessionID 的分配
    * 尽可能长，确保安全
    * 把 sessionID 信息放到 HttpSession 对象中 
2. **Server** 创建一个暂时的 HTTP Cookie
    * Cookie 存储这个 sessionID（名：jsessionid）
    * **Server** 将 Cookie 添加到 HTTP 响应中
    * Cookie 被放置到客户机浏览器中，存储到客户机硬盘
3. 客户浏览器发送包含 Cookie 的请求;
4. 根据客户机浏览器发送的 sessionID 信息（Cookie），Server 找到相应的 HttpSession 对象，跟踪会话
5. 在会话超时间隔期间，如果没有接收到新的请求，Server 将删除此会话对象
    * 用户又访问该站点，必须重新注册，确保安全

### URL 重写过程

* Cookie 被客户禁用时，采用URL重写机制
    * 调用 `reponse.encodeURL(URL)` 方法
    * `http://…;jsessionid=…`

1. 与 Cookie 机制相同
2. Server 将 sessionID 放在返回给客户端的 URL 中
3. 客户浏览器发送的请求将包含 sessionID
4. 根据包含请求的 sessionID 信息（URL），Server 找到相应的 HttpSession
5. 与 Cookie 机制相同

### 使用场景

#### Cookie 

1. 跟踪会话，也可以独立于 HTTP 会话使用 Cookie
2. 长期“记住用户信息”
3. 存储在客户机本地计算机硬盘上

> 示例：在购物车系统中，使用 cookie 记录用户 id，预填充; 使用会话，跟踪登录状态，跟踪应用程序的使用情况，cookie.txt 文件，记录用户对语言和颜色的选择之类的偏好

#### Session

1. 保存在服务器端内存中
2. 使用机制不同

> 示例：在购物车系统中，跟踪用户的购物车，导航信息，登录状态

## 四种作用域对象，从什么时候开始到什么时候结束

* 页面域（Page scope）：作用域为页面执行期
* 请求域（Request scope）：作用域为用户请求期，只要 Server 向客户端输出内容，就被销毁
* 会话域（Session scope）：作用域为会话期，从打开一个浏览器窗口开始，关闭窗口，会话关闭，当会话超时，被销毁
* 应用域（Application scope）：作用域为应用程序运行期，工程启动后存在，当容器关闭时被销毁

## 过滤器

### 定义

* 是一个 object，可以修改 request 或者 response 的 header 和 context
* 在不修改 Servlet 代码的情况下向 Servlet 添加功能，如：身份认证
* 可用于跨多个 Servlet 执行一些功能，创建可重复使用的功能
* 在 Servlet 处理请求之前，截获请求
    * 如：在调用 Servlet 之前，截获请求，验证用户身份，未经授权的用户遭到拒绝，而 Servlet 不知道曾经有过这样的请求
* 具体使用场景：代码重用；应用安全策略；日志；为特定目标浏览器传输 XML 输出；图像转换、加密；动态压缩输出；解决请求和响应中中文乱码的情况

### 跟其他 Web 组件的区别

Filters differ from web components in that filters usually **do not themselves create a response**. Instead, a filter provides functionality that can be “attached” to any kind of web resource.

## 监听器

### 定义

* 是一个对象，监听 Servlet 的生命周期，而做一些操作

### 应用场景

1. 监听系统关闭或者是开启
2. 监听用户访问次数
3. 监听用户是否登录
4. 监听 Session 是否改变

### 监听哪些生命周期事件

* 监听 Web context，在初始化时做一些操作
* 监听 Session
* 监听 Request

## 注解和 XML 文件的区别

* 注解往往是**类**级别的，因此，XML 配置则可以表现得更加**灵活**
* 在应用中，往往需要同时使用注解配置和 XML 配置
    * 对于**类级别**且**不会发生变动的配置**可以优先考虑注解配置
    * 而对于那些**第三方类**以及**容易发生调整的配置**则应优先考虑使用 XML 配置

# 4. JSP

## 文本信息类型

* 静态数据
    * HTML
    * SVG（可伸缩矢量图）
    * WML（无线标记语言）
    * XML
* 动态数据
    * JSP 元素（标准 JSP 语法，XML 语法）
    * Java 代码
    * 指令标签

## 以上信息如何被翻译解释

### 静态数据

转化为代码，排放到响应流中的数据。

### JSP 元素

* 指令标签：是用来控制 Web 容器转换并执行 JSP 页面
* 脚本元素：插入到 JSP 页面的 Servlet 类中
* 表达式语言：作为参数传递来调用 JSP 表达式解释器
* `jsp:[set|get]Property`：被转换成方法来调用 JavaBeans 组件
* `jsp:[include|forward]`：被转换成 JavaServletAPI 的调用，即 Servlet 采用 dispatcher 的方式 include 和 forward 的方式
* `jsp:plugin`：被转换成浏览器的特定标记来激活一个 applet
* Customtags 自定义标签：转化为调用标记处理程序，该程序实现自定义标签调用

## JSP 生命周期

JSP 的生命周期和许多 JSP 页面的能力（特别是动态方面）一样是由 Java Servlet 技术测定。 当一个请求映射到一个 JSP 页面，Web 容器首先检查是否 JSP 网页的 Servlet 比 JSP 页面的老。 如果 Servlet 是老年人，Web 容器将 JSP 页面到 Servlet 类和编辑类。在开发过程中，在 JSP 页面 Servlet 的一个优点是，生成过程是自动执行。

JSP 页面以 Servlet 的形式提供请求服务，所以，JSP 的生命周期和许多 JSP 功能都是由 Servlet 技术决定的（特别是动态的方面）

1. 请求被映射到 JSP 页面
2. 服务器端为 JSP 页面查找已有的实现类，如果没有找到则创建新的 JSP 页面的实现类，然后把这个类载入 JVM。在实现类装在完成后，JVM 将创建这个类的一个实例。
3. 初始化：初始化 JSP 页面对象
4. 处理请求
5. 生命周期终止：服务器不再把客户端的请求发给 JSP。在所有的请求处理完之后会释放掉这个类的所有实例。这种情况一般发生在服务器关闭的时候或者是服务器要保存资源，检测到有 JSP 文件更新等

## include 动作和 include 指令的区别

* include 指令：包括其他页面，编译时把其他页面的内容加进来，比 include 动作快
* include 标准动作：使用 RequestDispatcher，运行时把其他页面的内容加进来（包括到输出流中）

## forward 动作和 HTTP 重定向的区别

转发是服务器行为，重定向是客户端行为。

* HTTP 重定向（`response.sendRedirect(myNewURL)`）：发送的请求信息又回送给客户机，让客户机再转发到另一个资源上，新的 URL 出现在 Web 浏览器中，需要在服务器和客户机之间增加一次通信
* forward 标准动作：使用 RequestDispatcher，JSP 的转发功能是在服务器本身上实现的

## 创建实例变量和类变量、局部变量、作用域对象的属性的时机

* 类实例和类变量：在声明中创建，在脚本和表达式中被使用
* 局部变量：在脚本和表达式中被创建和使用
* 作用域对象的属性: 在脚本和表达式中被创建和使用

## JavaBean 设计规范

JavaBean 是 Java 的 class，是符合一定规范的 value object，是可以复用和组合使用的组件。所有 Java 的 class 只要符合 JavaBean 规范，就是一个 JavaBean，JSP 支持 JavaBean 的创建和 初始化，设置属性。

1. 属性 read/write, read-only, or write-only
2. 属性可以简单的单值也可以复合（数组）
3. 每一个属性有 get 和 set 方法，可以设值是可读，可写，或同时可读/可写 
4. 命名规则：属性第一个字母小写，Set/Get 第一个字母大写
5. 要位于一个 package 中
6. 要有一个无参数的构造器

## MVC

* View 层：Browser（HTML tags，JSP tags XML/XSL）用户界面
* Controller 层：Servlet，接受用户动作，并对数据做适当处理
* Model 层：Bean/JavaBeans（InventorManager, InventorItem, ShipmentReceived）封装应用数据

![](https://ws4.sinaimg.cn/large/006tNc79ly1fq0pgqylwkj30ok0c0gvy.jpg)

1. Client 端（Web 浏览器）发出请求
2. Servlets 获取客户请求
3. Servlets 决策由哪一个 Web 组件来处理请求（JavaBean，EJB，或者其他对象）
4. JavaBean 或者 EJB 处理来自 Servlets 的业务请求，封装结果
5. Servlet 选择一种表示模板（JSP），将内容返回给 Client
6. JSP 根据结果中的 JavaBean 产生具体的 JSP 页面，返回给 Client 端；JSP 页面不创建对象，只是从 JavaBean 中获取内容，对象都由 Servlet 创建

# 5. EJB

## 定义

An enterprise Bean is a server-side component that encapsulates the business logic of an application
企业 Bean 是一个服务器端组件，封装了应用程序的业务逻辑。

## 和自己写的 Service 的区别

> 区别

## 会话 Bean（给出远程方法调用）

### Stateful（有状态 SessionBean）

* 有状态的会话 Bean 在方法调用时可保持对话状态，维持实例变量的状态;
* 不同的 Client 有各自的 SessionBean，不共享。Client 端请求 SessionBean，系统就给该 Client 创建一个 SessionBean 实例，并维持组件的状态
* Client 端关闭，SessionBean 失效；要指定容器在某个方法完成后删除有状态的会话 Bean 实例，只要为该方法添加注释 `@Remove`
* 什么时候使用
    * Client 跨越多个方法调用需要维持相关信息
    * Client 需要和其他应用组件交互
    * 该 SessionBean 需要多个 EJB 交互才能实现，需要维护一个工作流
    * 例如：购物车

### Stateless（无状态会话 Bean）

* 不维持会话状态，不跟踪记录从一个方法调用传递到另一个方法调用的信息，每次调用无状态的业务方法都独立于前一次调用。
* 一个 Bean 可以被多个 Client 共享，Client 可以从容器的 stateless SessionBean 的实例池中获取实例
* 可以实现 Web Service，但是 stateful SessionBean 不行。
* 容器管理 Stateless SessionBean 更简单
* 指定 Java Bean 作为无状态的会话 Bean 加以部署及管理，只需要为该 Bean 添加注释`@Stateless`。
* 例如：发 Email

### Singleton（单例 Bean）

* 应用的生命周期中，只会被实例化一次。
* 可以被所有客户共享，并发访问
* 可以实现为 Web service 端
* 用途：应用的初始化工作，关闭时的清理工作，Singleton 在应用的整个生命周期存在

## 消息驱动 Bean（发送消息）

## 获得 Bean 实例的方式

### 依赖注入

* Client 端有 EJB 的实例引用，采用 Java 语言注解的方式；EJB 和 Client JVM 在同一个 JVM 中。最简单的获取 EJB 引用的方式。
* JSP，web application，JAX-RS web service, 其他的 EJB，JavaEE 应用客户端，都支持依赖注入，需要 javax.EJB.EJBannotation 包：`@EJB(“BeanName”)`

### JNDI 查找

EJB 和 Client 在不同的 JVM 或者同一个 JVM 中，通过 JNDI 来查找 EJB 实例。 Client 端可以是简单的 J2SE 应用，JNDI 支持识别 JavaEE 组件的全局语法，简化了显示查找的语法

## 目录结构

* Enterprise Bean class：实现业务方法的的 EJB，生命周期回调方法
* Business interface 定义业务接口方法：当 EJB 是本地，且 no-interface-view 访问时，就不需要了
* Helper class：EJB 的辅助类，例如:exception 或者 utility class

## Spring 管理 Bean 有两种常见的作用域，单例和多例的生命周期

* Singleton 表示该 Bean 全局只有一个实例，默认值。适用于 Service 层和 DAO 层。
* Prototype 表示该 Bean 在每次被注入的时候，都要重新创建一个实例，适用于有状态的 Bean。适用于 Action 层。

# 6. 持久化

## JPA

The Java Persistence API provides an object/relational mapping facility to Java developers for managing relational data in Java applications.

## 实体

* An entity is a lightweight persistence domain object. 
* Typically an entity represents a table in a relational database, and each entity instance corresponds to a row in that table. 
* 一个实体的实例是一个对应到数据库中的视图
* 一个实体的几个实例可能代表同一底层数据

## 实体的生命周期

* You manage entity instances by invoking operations on the entity by means of an EntityManager instance.
* Entity instances are in one of four states: **new, managed, detached, or removed**.

![](https://ws1.sinaimg.cn/large/006tNc79ly1fq2qcym10tj30wt0kv44m.jpg)
    
### New

* 通过`new`生成一个实体对象如：

```Java
User user=new User(“001”,“xyz”,……);
```

* `user`通过 JVM 获得了一块内存空间，但是并没有保存进数据库，还没有纳入 JPA EntityManager 管理中
* 在数据库中**不存在**一条与它对应的记录

### Managed

* 纳入 JPA EntityManager 管理中的对象
* new 状态，可通过 `persist()` 方法把 `user` 与数据库相关联，成为**持久化对象**
* 或使用`find()`方法，得到持久化对象
* 在数据库中存在一条与它对应的记录，并拥有一个持久化标识 (identifier)
* 对持久化对象的操作，**影响数据库**

### Detached

* 游离对象
    * 例如：`find()`方法调用后，可关闭 EntityManager，成为游离对象
        * 如: `em.clear();`
    * 对游离对象的操作，**不影响**数据库
    * 和 new 状态的区别
        * 在数据库中可能还存在一条与它对应的记录，只是现在这个游离对象脱离了 JPA EntityManager 的管理
* 游离对象转为持久对象
    * 调用`merge()`方法

### Removed

* `remove()`方法
* 删除数据库中的记录
* 在适当的时候被垃圾回收

## Hibernate 中实体的三种状态

![](https://ws4.sinaimg.cn/large/006tNc79ly1fq2qfje7ytj30kz0cgwgt.jpg)

* 瞬时/临时状态（Transient）
* 持久状态（Persistent）
* 脱管/游离状态（Detached）
* 三种状态主要取决于对象是否在 Session 缓存中
* 三种状态转化的方法都是通过 Session 方法来调用

### Transient

* 当通过`new`生成一个实体对象时，这个实体对象就处于自由状态，如：

```Java
User user=new User(“001”,“xyz”,……);
```

* 此时，user 通过 JVM 获得了一块内存空间，并没有通过 Session 对象的`save()`方法保存进数据库，还没有纳入 Hibernate 的缓存管理中，也就是说 user 对象现在还自由的游荡于 Hibernate 缓存管理之外
* 在数据库中**不存在**一条与它对应的记录

### Persistent

* 瞬时对象转为持久对象
    1. 通过 Session 的 `save()` 和 `saveOrUpdate()` 方法把一个瞬时对象与数据库相关联，这个瞬时对象就成为持久化对象
    2. 使用 `find()`,`get()`,`load()` 和 `iterator()` 等方法查询到的数据对象，将成为持久化对象
* 持久化对象,已经被保存进数据库的实体对象，处于 Hibernate 的**缓存管理之中**
    * 持久的实例在数据库中有对应的记录，并拥有一个**持久化标识**（identifier）
    * 对该实体对象的任何**修改**，都会在**清理缓存时同步**到数据库中
        * 持久对象与 Session 和 Transaction 相关联，在一个 Session 中，对持久对象的**改变不会马上**对数据库进行变更，而必须在 Transaction 终止，也就是执行 `commit()` 之后，才在数据库中真正运行 SQL 进行变更，持久对象的状态才会与数据库进行同步。在同步之前的持久对象称为脏 (dirty) 对象。

#### 示例

user 对象通过`save`方法保存进数据库后，成为持久化对象，然后通过`load`方法再次加载它（仍然是持久化对象，处于 Hibernate 缓存管理之中），当执行`tx.commit()`方法时，Hibernate 会自动清理缓存，并且自动将持久化对象的属性变化同步到到数据库中。

```Java
User user=new User(“001”,“xyz”,……); tx=session.beginTransaction();
session.save(user);//立即执行Sql insert
user=(User)session.load(User.class,“001”);//延迟加载
user.setName(“mary”);//不会立即执行Sql update
tx.commit(); //立即执行Sql update
```

### Detached

* 持久对象转为游离对象
    * 当执行 `close()` 或 `clear()`,`evict()` 之后，持久对象会变为游离对象
* 游离对象转为持久对象
    * 通过 Session 的 `update()`, `saveOrUpdate()` 和 `lock()` 等方法，把游离对象变为持久对象
* 游离对象和自由对象的区别
    * 游离对象在数据库中**可能还存在**一条与它对应的记录，只是现在这个游离对象脱离了 Hibernate 的缓存管理
    * 自由对象不会在数据库中出现与它对应的数据记录
* 当 Session 关闭（执行`session.close();`）后，user 对象就不处于 Hibernate 的缓存管理之中了，但是此时在数据库中还存在一条与 user 对象对应的数据记录，处于游离态

# 7. 安全

## 实现机制

### 声明安全

* 使用表单/浏览器/客户证书
* 把安全配置情况存储在部署描述文件中
    * 角色、访问控制、身份认证要求
    * web.xml
    * Jboss-web.xml
* 优先选择的模式

### 编程性安全（API）

* 细粒度的编程安全性
* 绑定在应用内部，用来做出安全决策
* 适用于当声明安全的实现形式不足以充分表达应用所需的安全模型的情况

## 包括内容

### 用户和组

* 用户——应用程序终端用户的账户名（ID）
* 组——命名的用户集合，包含 0 个和多个用户;通常用户表示具有类似系统资源访问权限的应用用户，例如：雇员（企业组），管理员（雇员组子集，可以 访问敏感的工资数据）

### 角色

* 角色（role）：一种抽象的逻辑用户分组，代表相同资源访问权限的用户组或者 特定用户
* 在部署时，角色被映射为授权的用户或组

### 策略

* 回答“特定角色能够访问什么样的服务”的问题

### 认证和授权
### 审计和日志记录
### 防火墙
### 数据保密和安全套接字

## 认证方式

* 基于浏览器的
* 基于表单的
* 基于 SSL 证书的

### 认证四个步骤

1. 应用开发者编写代码来提示用户输入用户名和密码
2. 开发者通过使用部署文件来为将要部署的应用设置安全
3. 服务器管理员为应用程序的用户或用户组在应用程序服务器端设置权限
4. 应用程序部署者为在服务器端已经定义的安全用户角色，规则来进行映射



