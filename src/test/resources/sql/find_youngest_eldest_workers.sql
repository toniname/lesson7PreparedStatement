WITH YoungestOldestCTE AS (
    SELECT
        'YOUNGEST' AS TYPE,
        NAME,
        BIRTHDAY,
        ROW_NUMBER() OVER (ORDER BY BIRTHDAY ASC) AS RowNum
    FROM
        worker
    WHERE
        BIRTHDAY IS NOT NULL
    UNION
    SELECT
        'ELDEST' AS TYPE,
        NAME,
        BIRTHDAY,
        ROW_NUMBER() OVER (ORDER BY BIRTHDAY DESC) AS RowNum
    FROM
        worker
    WHERE
        BIRTHDAY IS NOT NULL
)
SELECT
    TYPE,
    NAME,
    BIRTHDAY
FROM
    YoungestOldestCTE
WHERE
    RowNum = 1;