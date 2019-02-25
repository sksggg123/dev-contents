># AWS EC2와 RDS 연동간 이슈사항 정리

>## application 기동했는데 휴대폰(외부에서)에서 접속이 안된상황
>분명히 아래의 명령어를 통해 jar를 실행시켰지만 프로세스가 죽어있었다.
```java
	java -jar *.jar
```
>위에 명령어로 실행을 하게되면 spring boot project는 실행이되고 접속이 된다.  
>하지만 리눅스에서 **ctrl + c**를 누르게 되면 명령어가 종료가 된다.  
>이렇게 해서 종료가 될 경우 프로세스도 종료가 된다.  
>그렇다면 터미널에서 jar 실행 후 터미널을 종료해도 application이 종료가 되지 않기 위해서는 아래의 명령어로 application을 실행시키면 된다.!  
```java
	nohup java -jar *.jar

	# AWS에 실제 등록한 shell
	REPOSITORY=/home/ec2-user/app/git
	JAR_NAME=$(ls $REPOSITORY/ |grep 'dev-contents' |tail -n 1)
	nohup java -jar -Dspring.profiles.active=real $REPOSITORY/$JAR_NAME &
```
>이렇게 application을 실행 후 정상기동이 되면 ssh로 접속한 터미널을 종료 시켜도 application은 죽지않고 살아 있다. 

># DB Connect 이슈
>##1. db 접속정보를 갖고 있는 yml file directory 이슈 
>EC2 Instance에 ssh로 접근하여 바로 /app/config/projectName/*application.yml 파일을 생성하여 db 접속정보를 넣었다.  
```yaml
	spring:
  profiles: real-db
  datasource:
        url: jdbc:mariadb://RDS접속주소:3306/databasesName
        username: db username
        password: db password
        driver-class-name: org.mariadb.jdbc.Driver
```
>java application.class file에서는 아래와 같이 properties를 읽어오게 소스 수정을 해두었다.  
```java
	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.yml,"
			+ "/app/git/config/goquality/real-application.yml";
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(DevContentsApplication.class).properties(APPLICATION_LOCATIONS).run(args);
		
	}
```
>jar파일을 실행시켜보아도 이상없이 application이 기동되고 프로젝트에도 접속이 된다.  
>근데.. DB에는 이상하게 insert가 되지가 않고.. 물론 select해도 데이터가 없다...  
>이렇게 하루가 지나고 다시보니.. 아주기본적인 directory 설정 문제였다.  
>EC2 Instance에 ssh로 접속하여 yml file이 있는 directory 접근하여 **pwd**명령어를 치는순간 ㅋㅋㅋ 아래와 같은 경로가 나왔다.  
```console
	/home/ec2-user/app/git/config/goquality
```
>기본적으로 /home/ec2-user/ directroy 하위에 생성이 되어 application.class에 선언한 path로는 찾지를 못했다.  
>수정 후 application을 재구동 해보고 테스트 결과 또 안됐다..  에러로그는 아래와 같이 출력됨.
```log
	java.sql.SQLSyntaxErrorException: (conn=674) Table 'sksggg123.hibernate_sequence' doesn't exist
	at org.mariadb.jdbc.internal.util.exceptions.ExceptionMapper.get(ExceptionMapper.java:236) ~[mariadb-java-client-2.3.0.jar!/:na]
	at org.mariadb.jdbc.internal.util.exceptions.ExceptionMapper.getException(ExceptionMapper.java:165) ~[mariadb-java-client-2.3.0.jar!/:na]
	at org.mariadb.jdbc.MariaDbStatement.executeExceptionEpilogue(MariaDbStatement.java:238) ~[mariadb-java-client-2.3.0.jar!/:na]
	at org.mariadb.jdbc.MariaDbPreparedStatementClient.executeInternal(MariaDbPreparedStatementClient.java:232) ~[mariadb-java-client-2.3.0.jar!/:na]
	at org.mariadb.jdbc.MariaDbPreparedStatementClient.execute(MariaDbPreparedStatementClient.java:159) ~[mariadb-java-client-2.3.0.jar!/:na]
	at org.mariadb.jdbc.MariaDbPreparedStatementClient.executeQuery(MariaDbPreparedStatementClient.java:174) ~[mariadb-java-client-2.3.0.jar!/:na]
	at com.zaxxer.hikari.pool.ProxyPreparedStatement.executeQuery(ProxyPreparedStatement.java:52) ~[HikariCP-3.2.0.jar!/:na]
	at com.zaxxer.hikari.pool.HikariProxyPreparedStatement.executeQuery(HikariProxyPreparedStatement.java) ~[HikariCP-3.2.0.jar!/:na]
''''''' 생략 '''''''''
```
>열심히 구글링 결과 아래와 같은 답변을 얻을 수 있었다.
>답변블로그는 [이분](http://ppost.tistory.com/entry/hibernate-%EC%8A%A4%ED%94%84%EB%A7%81HibernateMySql-%EC%97%B0%EB%8F%99-%EC%82%BD%EC%A7%88-%EB%A6%AC%EC%8A%A4%ED%8A%B8featjava-config)참고
```text
	com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table 'DB명.hibernate_sequence' doesn't exist

@GeneratedValue(strategy = GenerationType.AUTO)

이것이 문제다. 혼자서 계속 해메이다 사수에게 물어봣더니 하이버네이트4까지는 상관없었는데 5로 버전업이 되면서 생긴 문제라고 한다.

정확히는 이해를 못했지만, 하이버네이트5에서부터는 저걸 AUTO로 해놓을경우 지가 테이블을 만들어서 적용을 한단다. 

근데 우린 DB명.hibernate_sequence 테이블이 없잖아?! 그래서 안된거다.

정확한 원인은 하이버네이트를 Deep하게 파봐야 이해를 할 수 있을것같고, 현재수준에선 일단



@GeneratedValue(strategy = GenerationType.IDENTITY)

이렇게 바꾸면 된다.
```
>위의 설명대로 바꾸고 빌드하여 재기동을 해보았는데 결과는 마찬가지였다 ㅠㅠ  
>다시또 구글링! 이번에는 stack overflow로 검색을 해보았다.
>[여기](https://stackoverflow.com/questions/32968527/hibernate-sequence-doesnt-exist)에 나와같은 이슈가 있는것으로 확인되었고 project source에 application.yml에 공통영역에 소스를 추가하였다.
```yaml
	jpa:
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL5InnoDBDialect
        id:
          new_generator_mappings: false
```
>오늘 이거 안되면 안잔다는 마음으로 재빌드...  
>1차로 서버는 에러없이 기동 되었다.  
>2차로 EC2 Instance에 정상접속 되었다.  
>3차로 DB 로직수행시 ㅠㅠ 정상적으로 insert 되는 것을 확인하였다. ㅎㅎ  
>이렇게 해서 EC2 -> RDS 디비 연동은 해결되었다.
