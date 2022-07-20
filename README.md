# MyFootballLife

관심 있는 축구 클럽의 트윗, 유튜브 영상을 모아볼 수 있는 앱<br/>

## 개발 기간
2022.06.01 ~ 2022.07.18  <br/>
부스트캠프중...

## 주요 기술 스택
Kotlin<br/>

## 프로젝트 환경
Min sdk: 27
Target sdk: 32
Jvm target: 1.8  
Programming language: Kotlin  

## 프로젝트 기능

## 주요 라이브러리

## 주요 트러블 슈팅

<details>
  <summary>debug.keystore로 API 앱 제한사항 적용 시 403에러 발생</summary>
  
  * 증상: API Key의 보안 때문에 debug.keystore sha-1로 제한 했는데, 안드로이드 앱에서 API 사용 시 403 에러가 발생했다.<br/>
  
  * 원인: 정확한 원인을 파악 할 수 없었다.<br/>
  
  * 조치: 인터넷 검색 결과 뚜렷한 해결책이 없었기 때문에 개발 기간 동안은 이 문제를 회피하기로 했다. IP 주소 제한을 적용했다. 
  <br>    -> local.properties에 api key 숨기기 (22-06-08)
  
</details>
