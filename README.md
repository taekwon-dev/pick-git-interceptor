# [pick-git](https://github.com/woowacourse-teams/2021-pick-git) 팀 Handler Interceptor 분석

### | 분석 배경

![image](https://user-images.githubusercontent.com/70354365/192001431-9da4ce39-3212-4244-8dd4-e6ae7ae69c4f.png)

[ 그림 1 ]

[ 그림 1 ] 과 같이 **같은 URL 주소**를 갖지만 **HTTP 메소드**에 따라 서로 다른 핸들러 인터셉터를 통과하도록 설정해야 하는 경우
HTTP 메소드 분기 로직을 전개하기 위해 각 핸들러 인터셉터에서 각 API URL 별로 허용하는 HTTP 메소드를 명시적으로 선언해야 한다.
이러한 구조는 인터셉터가 요청 URL, HTTP 메소드에 대해 의존하게 되고 결과적으로 결합도가 높아져 URL 또는 HTTP 메소드 수정 시 인터셉터도 수정 범위에 포함되는 문제가 있다.
또한, 각 인터셉터에 URL, HTTP 메소드 명시할 때 문자열 기반으로 처리하게 되는 부분 역시, 컴파일 단계에서 오타 등에 대한 검증을 미리할 수 없다는 점 역시 문제로 볼 수 있다.

![image](https://user-images.githubusercontent.com/70354365/192002100-c029171a-9a09-4ee5-a285-ee91c9a7844b.png)

[ 그림 2 ]

이러한 문제 배경에는 핸들러 인터셉터를 통과할 API를 지정하는 방식이 [ 그림 2 ] 처럼 문자열 기반으로 API URL을 지정하는 것 외에 다른 방법이 없다는 점이 있다.
따라서 위에서 정의한 문제를 해결하기 위해서는 `API URL + HTTP 메소드` 정보를 한 단위로해서 통과할 핸들러 인터셉터를 지정할 수 있어야 했고,
Java Annotation, Reflection 과 디자인 패턴 중 Proxy Pattern 을 활용해 이 문제를 해결한 [pick-git](https://github.com/woowacourse-teams/2021-pick-git) 팀을 알게 돼서 관련 내용을 정리하게 됐다.


### | 핵심 아이디어

● Java Annotation

● Java Reflection

● Design Pattern - Proxy Pattern 

핵심 아이디어 및 분석 내용 관련 자세한 내용은 아래 블로그 글을 통해서 확인할 수 있다. 

- [Spring MVC - 핸들러 인터셉터 & 프록시 패턴 [1] - GET /api/post/{postId} vs POST /api/post/{postId}](https://medium.com/taekwon-v/spring-mvc-%ED%95%B8%EB%93%A4%EB%9F%AC-%EC%9D%B8%ED%84%B0%EC%85%89%ED%84%B0%EC%97%90%EC%84%9C-%EC%9C%A0%EC%A0%80-%EC%9D%B8%EC%A6%9D-%EC%97%AC%EB%B6%80-%EB%B0%8F-%EA%B6%8C%ED%95%9C-%EA%B2%80%EC%82%AC%ED%95%98%EA%B8%B0-1-2e736844d46b)
- [Spring MVC - 핸들러 인터셉터 & 프록시 패턴 [2] - pick-git 팀 해결 아이디어 소개 (Java Annotation, Reflection, Proxy Pattern)](https://medium.com/taekwon-v/spring-mvc-%ED%95%B8%EB%93%A4%EB%9F%AC-%EC%9D%B8%ED%84%B0%EC%85%89%ED%84%B0%EC%97%90%EC%84%9C-%EC%9C%A0%EC%A0%80-%EC%9D%B8%EC%A6%9D-%EC%97%AC%EB%B6%80-%EA%B2%80%EC%82%AC%ED%95%98%EA%B8%B0-2-pick-git-%ED%8C%80-%ED%95%B4%EA%B2%B0-%EC%95%84%EC%9D%B4%EB%94%94%EC%96%B4-%EC%86%8C%EA%B0%9C-java-annotation-reflection-85d02cc20b32)
- [Spring MVC - 핸들러 인터셉터 & 프록시 패턴 [3] - pick-git 팀 소스 코드 분석하기](https://medium.com/taekwon-v/spring-mvc-%ED%95%B8%EB%93%A4%EB%9F%AC-%EC%9D%B8%ED%84%B0%EC%85%89%ED%84%B0%EC%97%90%EC%84%9C-%EC%9C%A0%EC%A0%80-%EC%9D%B8%EC%A6%9D-%EC%97%AC%EB%B6%80-%EA%B2%80%EC%82%AC%ED%95%98%EA%B8%B0-3-pick-git-%ED%8C%80-%EC%86%8C%EC%8A%A4-%EC%BD%94%EB%93%9C-%EB%B6%84%EC%84%9D%ED%95%98%EA%B8%B0-7aad4ffc8297)