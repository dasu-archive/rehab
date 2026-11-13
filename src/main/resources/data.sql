INSERT INTO member (name, email)
SELECT * FROM (
    SELECT '홍길동' AS name, 'hong@test.com' AS email
    UNION ALL SELECT '김철수', 'kim@test.com'
    UNION ALL SELECT '이영희', 'lee@test.com'
    UNION ALL SELECT '박민수', 'park@test.com'
) AS seed
WHERE NOT EXISTS (SELECT 1 FROM member);

INSERT INTO event (name, capacity, applicant_count)
SELECT * FROM (
    SELECT 'Spring Boot 입문 스터디' AS name,
           30 AS capacity, 0 AS applicant_count
    UNION ALL SELECT 'JPA 실전 세미나', 50, 0
    UNION ALL SELECT '정원 마감 행사', 3, 3
    UNION ALL SELECT '마지막 한 자리 행사', 3, 2
    -- 나머지 행사도 같은 형식으로 UNION ALL SELECT ... 추가
) AS seed
WHERE NOT EXISTS (SELECT 1 FROM event);