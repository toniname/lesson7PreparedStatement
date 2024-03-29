SELECT
    p.NAME,
    DATEDIFF(MONTH, p.START_DATE, p.FINISH_DATE) AS MONTH_COUNT
FROM
    project p
WHERE
    DATEDIFF(MONTH, p.START_DATE, p.FINISH_DATE) = (
        SELECT
            MAX(DATEDIFF(MONTH, START_DATE, FINISH_DATE))
        FROM
            project
    );