## spring-data-jpa-sample (Compsit Key Mapping)

> 이 리포는 우아한형제들 기술블로그의 [Legacy DB의 JPA Entity Mapping (복합키 매핑 편)](https://techblog.woowahan.com/2595/) 아티클의 예제 코드입니다.

Legacy System 이 JPA 를 사용하지 않는 상황에서 JPA 로 전환하려 할때, 복합키 매핑에 관한 예제소스입니다. 

첫번째 `@EmbededId`방식과 두번째 `@IdClass` 방식을 서로 비교해 볼 수 있는 예제입니다.

* `@EmbededId`: 객체지향적인 방식의 매핑방법이다.
* `@IdClass`: 컬럼의 중복이 발생하지만 RDB 친화적인 방식이다.
