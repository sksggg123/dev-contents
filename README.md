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
```
>이렇게 application을 실행 후 정상기동이 되면 ssh로 접속한 터미널을 종료 시켜도 application은 죽지않고 살아 있다.  
