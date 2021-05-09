# deduplicate
#### A component used to prevent duplicated attacks
* [简体中文](README.md)

`deduplicate-cache: `
 - Provides the caching mechanism in this deduplicate component. 
 - Based on the SPI mechanism, it can be extended. 
 - Currently, the map caching mechanism is implemented, and other caching mechanisms such as redis can be implemented based on the api.
  
`deduplicate-common: `
 - Provide the common tools in this deduplicate component 

`deduplicate-core: `
 - Function implementation code in this deduplicate component 

`deduplicate-spring: `
- The Spring dependency version of this deduplicate component, which provides an annotation method to start and use the component

`deduplicate-spring-starter: `
- The Spring Starter dependency version of this deduplicate component provides an automatic injection method, automatically starts, and is used through annotations

`deduplicate-all: `
 - Provide a complete dependency package.
 - The project can rely on this artifactId, and choose the `deduplicate-cache` you want to use, then you can implement the introduction and use of the component in the project 

`deduplicate-bom: `
 - Provide a complete dependency version relationship. 
 - The project can also import the pom to realize the introduction of the dependency version without worrying about version conflicts. 

#### How to use
Can refer to  `<deduplicate-test>`

#### Version List
**v0.0.1**
- [X] Add custom replay exception information
- [X] Add unified log processing
***
**v0.0.2**
- [X] Add the unique token mechanism
- [X] Add global version control
***
**v0.0.3**
- [x] Add spring support
- [x] Add spring-starter support
***
**v0.0.4**
- [ ] Add optional redis caching mechanism