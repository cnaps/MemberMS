# 기반이 될 도커 이미지 선택 (여기서는 OpenJDK 11 사용)
FROM openjdk:15-jdk-alpine
# 빌드된 스프링 부트 JAR 파일을 복사
COPY target/*SNAPSHOT.jar app.jar
# 컨테이너가 실행될 포트 지정
#EXPOSE 8080
#ENV TZ=Asia/Seoul
#RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENTRYPOINT ["java","-Xmx400M","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.profiles.active=docker"]