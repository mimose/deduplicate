# deduplicate
#### 防重放组件

`deduplicate-cache: 提供防重放组件中的缓存机制，基于SPI机制，可以进行扩展，目前实现了map的缓存机制，可以基于api实现redis等其他的缓存机制`

`deduplicate-gen: 提供防重放组件中的对象key生成机制，基于SPI机制，可以进行扩展，目前实现了Jackson的生成机制，可以基于api实现其他不同的序列化生成对象key的机制`

`deduplicate-common: 防重放组件中的公共信息`

`deduplicate-core: 防重放组件中的主要逻辑实现`

`deduplicate-all: 提供完整的依赖包，项目依赖该artifactId，并且选择自己想用的cache和gen，就可以实现组件在项目中的引入使用`

`deduplicate-bom: 提供完整的依赖版本关系，项目也可以通过import该pom，实现依赖版本的引入，不用担心版本冲突的问题`

#### Todo List
- [X] 增加自定义的重放异常信息
- [X] 增加统一日志处理