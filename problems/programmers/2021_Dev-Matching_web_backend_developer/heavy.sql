-- (210818) 헤비 유저가 소유한 장소
-- https://programmers.co.kr/learn/courses/30/lessons/77487

SELECT
 *
 FROM PLACES
 WHERE HOST_ID
 IN (SELECT HOST_ID
     FROM (SELECT HOST_ID, COUNT(HOST_ID) as HOST_COUNT
           FROM PLACES
           GROUP BY HOST_ID) as Heavy
     WHERE HOST_COUNT > 1);

