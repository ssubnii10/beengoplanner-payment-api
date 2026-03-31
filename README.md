# BeengoPlanner Payment API

## 프로젝트 개요
취준생 캘린더 앱 **BeengoPlanner**의 Pro 구독 결제를 처리하는 백엔드 API입니다.
Pro 전환 시 결제 요청에 대해 리스크를 판단하고 승인/거절을 처리합니다.

## 기술 스택
- Java 17
- Spring Boot 3.5
- Spring Data JPA
- H2 Database
- Gradle

## Pro 구독 플랜
| 플랜 | 금액 | 혜택 |
|------|------|------|
| MONTHLY | 9,900원/월 | 테마 변경, AI 파싱 무제한, 리포트 기능 |
| YEARLY | 99,000원/년 | 동일 혜택 + 연간 할인 |

## 주요 기능
- 결제 요청 처리 (POST /payments)
- 리스크 판단 — 비정상 금액 요청 자동 거절
- 결제 결과 저장 (APPROVED / REJECTED)

## 핵심 설계 포인트

### 트랜잭션 전략
결제 요청과 리스크 판단을 하나의 트랜잭션으로 처리하여
데이터 일관성을 보장합니다.

### 리스크 엔진 설계
리스크 판단 로직을 RiskRule 인터페이스 기반으로 분리하여
새로운 판단 기준 추가 시 기존 코드 수정 없이 확장 가능합니다.

## API 명세
### POST /payments
결제 요청을 처리하고 리스크 판단 후 결과를 반환합니다.

**Request**
```json
{
  "userId": 1,
  "planType": "MONTHLY",
  "amount": 9900
}
```

**Response**
```json
{
  "id": 1,
  "userId": 1,
  "planType": "MONTHLY",
  "amount": 9900,
  "riskScore": 0,
  "status": "APPROVED"
}
```

## 리스크 판단 기준
| 금액 | riskScore | 결과 |
|------|-----------|------|
| 9,900 (월 구독) | 0 | APPROVED |
| 99,000 (연 구독) | 0 | APPROVED |
| 그 외 금액 | 50 | REJECTED |

## 실행 방법
```bash
./gradlew bootRun
```

## 개선 가능한 부분
- Redis 기반 중복 결제 방지
- 구독 상태 관리 API 추가
- 실제 PG사 연동