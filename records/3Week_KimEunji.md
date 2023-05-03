## Title: [3Week] 김은지

### 미션 요구사항 분석 & 체크리스트
- [x] 제한된 시간 동안 호감표시 수정 및 삭제가 불가능하도록 함
    - [x] LikeablePerson 클래스에 getModifyUnlockDateRemainStrHuman()를 구현함으로써 시간이 얼마나 남았는지 확인할 수 있도록 함
- [ ] 알람 기능 구현

---

### 3주차 미션 요약

---

- 필수 항목 : 일정 시간(3시간) 동안 호감표시 수정 및 삭제가 불가능하도록 구현
- 선택 항목 : 알람기능 구현
    어떠한 사람이 나를 좋아하는 일이 발생했을 경우
    어떠한 사람이 나에대한 호감 사유를 변경한 경우

**[접근 방법]**

- 먼저, LikeablePerson의 getModifyUnlockDateRemainStrHuman()에서 likeable_hour과 likeable_minute를 통해 시간을 세분화시켜, LikeablePersonService에 적용하기 쉽도록 구현했다.
- LikeablePersonService의 canCancel 메서드에서 호감삭제 가능한 시간을 likeablePerson.getModifyUnlockDateRemainStrHuman() 로 확인 시켜주었다.
- LikeablePersonService의 canModifyLike 메서드에서 호감수정 가능한 시간을 likeablePerson.getModifyUnlockDateRemainStrHuman() 로 확인 시켜주었다.

**[특이사항]**

- 제대로 작동하는지 확인해보지 못했고, 선택미션도 진행하지 못해서 아쉬웠다.

**참고: [Refactoring]**
