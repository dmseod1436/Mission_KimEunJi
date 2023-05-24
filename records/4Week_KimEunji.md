## Title: [4Week] 김은지

### 미션 요구사항 분석 & 체크리스트
- [x] 네이버클라우드플랫폼을 통한 배포, 도메인, HTTPS 까지 적용
- [x] 내가 받은 호감리스트에서 성별 필터링 구현
  - [x] @RequestParam를 통해 파라미터 값을 받을 수 있도록 함
  - [x] gender 값을 stream을 통해 필터링하여 반환함
- [x] 내가 받은 호감 리스트에서 호감사유 필터링 구현
  - [x] @RequestParam를 통해 파라미터 값을 받을 수 있도록 함
  - [x] attractiveTypeCode 값을 stream을 통해 필터링하여 반환함

---

### 4주차 미션 요약

---

- 필수 항목
  - 네이버클라우드플랫폼을 통한 배포, 도메인, HTTPS 까지 적용 
  - 내가 받은 호감리스트(/usr/likeablePerson/toList)에서 성별 필터링기능 구현

- 선택 항목
  - 젠킨스를 통해서 리포지터리의 main 브랜치에 커밋 이벤트가 발생하면 자동으로 배포가 진행되도록 
  - 내가 받은 호감리스트(/usr/likeablePerson/toList)에서 호감사유 필터링기능 구현 
  - 내가 받은 호감리스트(/usr/likeablePerson/toList)에서 정렬기능

**[접근 방법]**

- LikeablePersonController 클래스에서 @RequestParam을 통해 필터링을 위한 값을 받을 수 있도록 showToList()에 추가함
- 값이 없을 경우 gender = "", attractiveTypeCode = "0", sortCode = "1" 로 초기화함
- LikeableService에 listingContents를 생성하여 호감 목록을 필터링하고, 필터링한 목록이 반환됨

**[특이사항]**

-

**참고: [Refactoring]**