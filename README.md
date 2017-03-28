# Ajax-spring-boot-college-application-form
> **This project requires JVM version of at least 1.7**

> 
This web application is well suited for an Institution 

#Install
This application was build with Bootstrap and Jquery and Thymeleaf. All plugins have already been installed

# Usage

## Gradle >= 2.1

## Snapshot
```
repositories {
    mavenCentral()
}

configurations.all {
    exclude module: 'logback-classic'
}

dependencies {
    compile 'org.springframework.boot:spring-boot-starter-thymeleaf'
    compile 'org.springframework.boot:spring-boot-starter-data-jpa'
    compile 'org.apache.tomcat:tomcat-dbcp:8.0.30'
    compile 'org.springframework.boot:spring-boot-starter-security'
    compile 'org.thymeleaf.extras:thymeleaf-extras-springsecurity4:2.1.2.RELEASE'
    compile 'commons-fileupload:commons-fileupload:1.3.2'
    compile	'com.itextpdf:itextpdf:5.5.9'
    compile	'com.itextpdf:itext-asian:5.2.0'
    compile	'com.itextpdf.tool:xmlworker:5.5.9'
    compile 'commons-fileupload:commons-fileupload:1.3.2'
    compile 'commons-io:commons-io:2.5'
    compile 'org.apache.logging.log4j:log4j:2.8.1'
    compile 'org.slf4j:slf4j-api:1.7.25'
    compile 'org.apache.logging.log4j:log4j-slf4j-impl:2.8.1'
    compile 'org.apache.logging.log4j:log4j-api:2.8.1'
    compile 'org.apache.logging.log4j:log4j-core:2.8.1'
    compile 'org.apache.logging.log4j:log4j-jcl:2.8.1'
    //compile 'org.springframework.boot:spring-boot-starter-log4j:1.3.8.RELEASE'
    //compile	'javax.validation:validation-api:1.1.0.Final'
    //compile	'org.hibernate:hibernate-validator:5.0.1.Final'
    //compile 'org.codehaus.jackson:jackson-mapper-asl:1.9.13'


    runtime 'com.h2database:h2'
    runtime 'javax.transaction:jta:1.1'
    runtime 'org.aspectj:aspectjweaver:1.8.7'

    testCompile 'org.springframework.boot:spring-boot-starter-test'
}
```
