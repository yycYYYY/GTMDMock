# GTMDMock
  一个Http/Https接口mock管理系统。
  提供mock信息保存修改、批量录制生成、转发透传等功能。

## 一、目标与场景
### 1、目标
- 满足异常场景测试（极端数据场景构造）

- 配合ui、接口自动化测试

- 通过mock提高测试环境接口可用性（服务挂了或接口未完全提测或特殊交易时间问题）

- mock数据统一管理

- 使测试过程中，尽可能的前后端分离

### 2、要解决的问题（http）
- 异常场景测试

- 客户端提测了，接口还没好，只给了一个调试用的假数据，各种数据场景无法测试

- 服务端接口测试环境挂了，服务不可用

- 自动化测试，数据污染，无法复用，需要每次重新初始化环境。

- 测试某需求，需要改包；后来出现问题或者需求更改，需要再测试这个模块，发现接口字段含义，怎么改全忘了，需要重新抓包查文档或者问研发。

### 3、场景
- 只对带有特定参数的某接口，进行mock，该接口的其他参数不进行mock

- 只对该域名下，某几个接口mock，其他的接口，正常访问真实服务，返回真实数据

- 配置默认返回的response，如果真实接口502了，返回默认的response。（这一块待实现）

- 自动生成mock（录制某个时间段，一整套大量的接口数据，保存至平台，需要使用时直接用）

## 二、项目介绍

### 1、项目结构
项目分为两个Module
mock-admin：springboot、mybatis、mysql

mock用例管理模块，用于组织mock用例，提供http接口，对mock信息进行管理。



mock-core：apache mockserver、okio、okHttp、netty

mock服务实现模块，对apache mockserver二次封装，并实现具体逻辑。



### 2、用例组织
mock信息管理分为三个层级：项目（project，包含多个期望集）、期望集（expectations，一组期望，包含多个期望，也就是包含多个接口）、期望（expectation，单个接口的mock，在项目中用request表示，对应一个接口的mock信息，包含request matcher和response template）

字段信息

#### ①项目project

isOpen	integer($int32)	开关，0为false，1为true
isSecure	integer($int32)	服务域名是否是https，0为false，1为true
port	integer($int32)	服务域名的端口，自定义
projectName	string	项目名，自定义
proxyAddress	string	真实服务地址，如果未命中匹配信息，会请求到真实服务
remark	string	备注
#### ②期望集expectations

expectationsName	string	期望集名称
isOpen	integer($int32)	开关，0为false，1为true
remark	string	备注
#### ③期望expectation（由于和request matcher的意义重合，在项目中用request表示）

body	string	请求体，json字符串
cookies	string	cookies，json字符串
headers	string	请求头，json字符串
isKeepAlive	integer($int32)	0为false，1为true
isOpen	integer($int32)	开关，0为false，1为true
isSecure	integer($int32)	服务域名是否是https，0为false，1为true
method	string	请求方法，get、post或者其他
path	string	接口地址
pathParams	string	path中的参数
queryParams	string	表单参数
remark	string	备注
responseType	string	响应类型：response、error、forward、overrideForward
response：我们自定义的正常响应

error：我们自定义的错误响应

forward：自定义转发，把原请求原封不动的转发至特定的域名：端口

overrideForward：自定义重写转发，把原请求转发至特定的域名：端口，同时可以修改原请求中的信息

### 3、数据流向

     客户端请求接口-》是否匹配mock--》匹配，返回mock

                              --》不匹配，请求真实server---》真实server返回正常--》客户端接收真实数据

                                                     ---》真实server返回502等异常状态码---》响应截获，返回mock默认响应--》客户端接收mock数据

三、使用流程
1、客户端接入
android或ios：

加入mock-server域名至白名单

客户端修改server域名配置为：https://mock域名：port。例如：https://mock.gtmd.com:1083



或者客户端在选择环境的时候，直接加一个mock环境（需要研发支持）：

线上

测试

测试（QA）

mock

2、服务端配置
普通手动mock
（1）进入管理页，先配置一个项目，并设置全局代理的域名并开启全局代理，例如hq-dev.tigerfintech.com

（2）客户端修改某server域名配置，dev.gtmd.com-->https://mock.gtmd.com:1083

（3）访问mockserver日志面板https://mock.gtmd.com:1083/mockserver/dashboard

（4）在客户端请求接口

（5）在日志面板的筛选器、筛选要mock的接口

（6）到筛选结果内，复制该接口的请求信息及响应

（7）在项目下新建一个期望集，新建一个期望，将复制的请求、响应信息添加到配置内，点击生效。

批量录制
（1）进入管理页，先配置一个项目，并设置全局代理的域名并开启全局代理，例如hq-dev.tigerfintech.com

（2）客户端修改行情server域名配置，dev.gtmd.com-->https://mock.gtmd.com:1083

（3）访问mockserver日志面板https://mock.gtmd.com:1083/mockserver/dashboard

（4）在客户端请求接口

（5）在项目页点击录制（可以使用通配符，录制指定路径下或某些特定的接口）

（6）自动生成一个名字为当前时间戳的期望集（包含刚刚请求的所有接口）

四、接口文档
localhost：port/swagger-ui.html