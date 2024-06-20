# shopli

스프링 부트 JPA를 활용한 로그인, 회원가입, 조직, 게시글, 채팅등을 개발하는 프로젝트 입니다.
![Spring_Login](https://github.com/kjone8371/Project-Spring-Boot-Data-JPA-user-service/assets/103641429/08505529-c7a0-4104-adb1-f57542724b6e)


# 서비스 / 제품 개요
  + 일반 사용자(유저)
  + 사장
  + 개발자

# 기능요약
  + 조직 관리
  + 검색
  + 게시
  + 파일 관리 (클라우드 서비스)
  + 사용자
  + 상품 게시
  + 상품 등록
  + 장바구니 등록
    


# API
+ 1. 로그인(Post)
  2. 회원가입(Get)
  3. 적업 선택(Post)
  4. 조직 생성(Post)
  5. 조직 선택(Get)
  6. 상품 검색(Get)
  7. 조직 검색(Get)
  8. 조직 관리(Update)
  9. 상품 게시(Post)
  10. 상품 구매(Get)
  11. 상품 상세 보기(Get)

# Spring Boot implementation
+ **build.gradle**
    ```build
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-security'
    testImplementation 'org.springframework.security:spring-security-test'

    compileOnly group: 'io.jsonwebtoken', name: 'jjwt-api', version: '0.11.2'
    runtimeOnly group: 'io.jsonwebtoken', name: 'jjwt-impl', version: '0.11.2'
    runtimeOnly group: 'io.jsonwebtoken', name: 'jjwt-jackson', version: '0.11.2'
    ```


## Install Postman
포스트맨을 설치해서 localhost로 테스트 해보세요! 

https://www.postman.com/downloads

+ **Create MySQL database**
  ```properties
  spring.datasource.url= jdbc:mysql://localhost:3306/Study_JPA
  spring.datasource.username=유저이름
  spring.datasource.password=비빌번호
  spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
  
  ```

+ **Create Mail Config**
  ```properties
  spring.application.name=shopli
  server.port=8000

  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQLDialect
  spring.jpa.show-sql=true

  spring.datasource.hikari.maximum-pool-size=20
  spring.datasource.hikari.minimum-idle=10
  spring.datasource.hikari.idle-timeout=30000
  spring.datasource.hikari.max-lifetime=1800000
  spring.datasource.hikari.connection-timeout=30000

  jwt.secret.key = 키 만들기
  ```
# Jwt 시크릿 키 제작 명령어 (Mac)

```
  openssl rand -hex 32
```








