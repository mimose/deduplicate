# deduplicate
#### 防重放组件
* [English](README_en.md)

`deduplicate-cache: `
 - 提供防重放组件中的缓存机制.
 - 基于SPI机制，可以进行扩展.
 - 目前实现了map的缓存机制，可以基于api实现redis等其他的缓存机制
 - 目前实现了redis的缓存机制，可以基于api实现其他的缓存机制
 
`deduplicate-common: `
 - 提供防重放组件中的公用工具

`deduplicate-core: `
 - 防重放组件中的主要逻辑实现

`deduplicate-spring: `
 - 防重放组件的Spring依赖版本，提供通过注解的方式来启动和使用该组件

`deduplicate-spring-starter: `
 - 防重放组件的Spring Starter依赖版本，提供自动注入的方式，自动启动，并通过注解使用

`deduplicate-all: `
 - 提供完整的依赖包，项目依赖该artifactId，并且选择自己想用的`deduplicate-cache`，就可以实现组件在项目中的引入使用

`deduplicate-bom: `
 - 提供完整的依赖版本关系，项目也可以通过import该pom，实现依赖版本的引入，不用担心版本冲突的问题

#### How to use
可参考 `<deduplicate-test>`

#### Version List
**v0.0.1**
- [X] 增加自定义的重放异常信息
- [X] 增加统一日志处理
***
**v0.0.2**
- [X] 增加唯一token机制
- [X] 增加全局版本控制
***
**v0.0.3**
- [x] 增加Spring支持
- [x] 增加Spring-starter依赖
***
**v0.0.4**
- [x] 增加可选redis缓存机制
- [x] 增加启动器，通过DeduplicateStarter.start()在启动时完成一些工作
