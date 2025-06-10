# SIMPLE WebRTC video/chat Example #

This README would normally document whatever steps are necessary to get your application up and running.

### sbwebrtcex ###

* Quick summary
* Version
* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)

### ## dependencies ###
```gradle
implementation 'org.springframework.boot:spring-boot-starter-websocket'
// implementation 'org.springframework.boot:spring-boot-starter-security'
implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
implementation 'nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect'
// implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity6'
implementation 'org.springframework.boot:spring-boot-starter-validation'
implementation 'org.springframework.boot:spring-boot-starter-web'

// JDBC, MySql
implementation 'org.bgee.log4jdbc-log4j2:log4jdbc-log4j2-jdbc4.1:1.16'  /* Log4JDBC */
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
implementation 'org.springframework.boot:spring-boot-starter-jdbc'
implementation 'org.springframework.boot:spring-boot-starter'
runtimeOnly 'com.mysql:mysql-connector-j'

// runtimeOnly 'com.h2database:h2'
compileOnly 'org.projectlombok:lombok'
annotationProcessor 'org.projectlombok:lombok'
developmentOnly 'org.springframework.boot:spring-boot-devtools'
compileOnly 'org.springframework.boot:spring-boot-devtools'		
testImplementation 'org.springframework.boot:spring-boot-starter-test'
// testImplementation 'org.springframework.security:spring-security-test'
testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
```

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact