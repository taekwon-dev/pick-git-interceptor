# [pick-git](https://github.com/woowacourse-teams/2021-pick-git) 팀 Handler Interceptor 분석

### | 분석 배경 

![image](https://user-images.githubusercontent.com/70354365/192001431-9da4ce39-3212-4244-8dd4-e6ae7ae69c4f.png)
[ 그림 1 ]

[ 그림 1 ] 과 같이 **같은 URL 주소**를 갖지만 **HTTP 메소드**에 따라 서로 다른 핸들러 인터셉터를 통과하도록 설정해야 하는 경우

HTTP 메소드 분기 로직을 전개하기 위해 각 핸들러 인터셉터에서 허용하는 HTTP 메소드를 명시적으로 선언해야 하는 문제가 있다.   

![image](https://user-images.githubusercontent.com/70354365/192002100-c029171a-9a09-4ee5-a285-ee91c9a7844b.png)
[ 그림 2 ]

이러한 문제 배경에는 핸들러 인터셉터를 통과할 API를 지정하는 방식이 [ 그림 2 ] 처럼 문자열 기반으로 API URL을 지정하는 것 외에 다른 방법이 없다는 점이 있었다.  

결과적으로 원하는 문제 해결 방향은 **API URL + HTTP 메소드 정보**를 기준으로 통과할 핸들러 인터셉터를 지정할 수 있도록 하는 것이었고, 

[pick-git](https://github.com/woowacourse-teams/2021-pick-git) 팀 프로젝트가 위 방향으로 구현되어 있어 분석하게 됐다.  


### | 핵심 아이디어 

분석 과정에서 문제 해결을 위해 사용된 핵심 아이디어를 다음과 같이 선정했다. 

● Java Annotation

● Java Reflection

● Design Pattern - Proxy Pattern 

핵심 아이디어 및 분석 내용 관련 자세한 내용은 아래 블로그 글을 통해서 확인할 수 있다. 

- [Spring MVC - 핸들러 인터셉터에서 유저 인증 여부 및 권한 검사하기 [1] - GET /api/post/{postId} vs POST /api/post/{postId}](https://medium.com/taekwon-v/spring-mvc-%ED%95%B8%EB%93%A4%EB%9F%AC-%EC%9D%B8%ED%84%B0%EC%85%89%ED%84%B0%EC%97%90%EC%84%9C-%EC%9C%A0%EC%A0%80-%EC%9D%B8%EC%A6%9D-%EC%97%AC%EB%B6%80-%EB%B0%8F-%EA%B6%8C%ED%95%9C-%EA%B2%80%EC%82%AC%ED%95%98%EA%B8%B0-1-2e736844d46b)
- [Spring MVC - 핸들러 인터셉터에서 유저 인증 여부 및 권한 검사하기 [2] - pick-git 팀 해결 아이디어 소개 (Java Annotation, Reflection, Proxy Pattern)](https://medium.com/taekwon-v/spring-mvc-%ED%95%B8%EB%93%A4%EB%9F%AC-%EC%9D%B8%ED%84%B0%EC%85%89%ED%84%B0%EC%97%90%EC%84%9C-%EC%9C%A0%EC%A0%80-%EC%9D%B8%EC%A6%9D-%EC%97%AC%EB%B6%80-%EA%B2%80%EC%82%AC%ED%95%98%EA%B8%B0-2-pick-git-%ED%8C%80-%ED%95%B4%EA%B2%B0-%EC%95%84%EC%9D%B4%EB%94%94%EC%96%B4-%EC%86%8C%EA%B0%9C-java-annotation-reflection-85d02cc20b32)
- [Spring MVC - 핸들러 인터셉터에서 유저 인증 여부 및 권한 검사하기 [3] - pick-git 팀 소스 코드 분석하기](https://medium.com/taekwon-v/spring-mvc-%ED%95%B8%EB%93%A4%EB%9F%AC-%EC%9D%B8%ED%84%B0%EC%85%89%ED%84%B0%EC%97%90%EC%84%9C-%EC%9C%A0%EC%A0%80-%EC%9D%B8%EC%A6%9D-%EC%97%AC%EB%B6%80-%EA%B2%80%EC%82%AC%ED%95%98%EA%B8%B0-3-pick-git-%ED%8C%80-%EC%86%8C%EC%8A%A4-%EC%BD%94%EB%93%9C-%EB%B6%84%EC%84%9D%ED%95%98%EA%B8%B0-7aad4ffc8297)