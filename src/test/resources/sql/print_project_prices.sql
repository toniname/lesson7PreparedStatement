WITH ProjectPrices AS (
    SELECT
        p.NAME AS PROJECT_NAME,
        SUM(w.SALARY * (DATEDIFF(p.FINISH_DATE, p.START_DATE) + 1)) AS PRICE
    FROM
        project p
            JOIN project_worker pw ON p.ID = pw.PROJECT_ID
            JOIN worker w ON pw.WORKER_ID = w.ID
    GROUP BY
        p.ID, p.NAME
)
SELECT
    PROJECT_NAME AS NAME,
    PRICE
FROM
    ProjectPrices
ORDER BY
    PRICE DESC;