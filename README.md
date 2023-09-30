# RentalMS
- 프로젝트 생성
  - https://start.spring.io/

- 빌드
  - 어플리케이션 빌드
  
    ```
    mvn clean package 
    ```

 

- 도메인 모델 클래스도  
<img width="838" alt="image" src="https://github.com/cnaps/RentalMS/assets/15258916/ab18e473-3691-4624-8b27-b3fc8cc79569">

- RentalItem을 VO 로 선언했으나 rented등의 값이 변경됨으로 불변성을 잃음 , 엔티티로 다시 개념 정의 하던지? 아니면 overdueItem으로 분리할 필요성 있음.


