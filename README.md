### prerequisites
The following items should be installed
IntelliJ IDEA and Gradle

##Usage
^build.gradle
```
buildscript {
    ext {
        springBootVersion = '1.5.2.RELEASE'
    }
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
    }
}

apply plugin: 'java'
apply plugin: 'idea'
apply plugin: 'org.springframework.boot'

jar {
    baseName = 'todotoday'
    version = '0.0.1-SNAPSHOT'
}

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
    compile     'com.itextpdf:itextpdf:5.5.9'
    compile     'com.itextpdf:itext-asian:5.2.0'
    compile     'com.itextpdf.tool:xmlworker:5.5.9'
    compile 'commons-fileupload:commons-fileupload:1.3.2'
    compile 'commons-io:commons-io:2.5'
    compile 'org.apache.logging.log4j:log4j:2.8.1'
    compile 'org.apache.logging.log4j:log4j-slf4j-impl:2.8.1'
    compile 'org.apache.logging.log4j:log4j-api:2.8.1'
    compile 'org.apache.logging.log4j:log4j-core:2.8.1'
    compile 'org.apache.logging.log4j:log4j-jcl:2.8.1'
  
    runtime 'com.h2database:h2'
    runtime 'javax.transaction:jta:1.1'
    runtime 'org.aspectj:aspectjweaver:1.8.7'

    testCompile 'org.springframework.boot:spring-boot-starter-test'
}



```
