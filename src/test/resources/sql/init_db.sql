-- Створення таблиці "worker"
CREATE TABLE worker (
                        ID INT PRIMARY KEY,
                        NAME VARCHAR(1000) NOT NULL CHECK (LENGTH(NAME) >= 2 AND LENGTH(NAME) <= 1000),
                        BIRTHDAY DATE CHECK (YEAR(BIRTHDAY) > 1900),
    LEVEL VARCHAR(20) NOT NULL,
    SALARY INT CHECK (SALARY >= 100 AND SALARY <= 100000)
);

-- Створення таблиці "client"
CREATE TABLE client (
                        ID INT PRIMARY KEY,
                        NAME VARCHAR(1000) NOT NULL CHECK (LENGTH(NAME) >= 2 AND LENGTH(NAME) <= 1000)
);

-- Створення таблиці "project"
CREATE TABLE project (
                         ID INT PRIMARY KEY,
                         CLIENT_ID INT REFERENCES client(ID),
                         START_DATE DATE,
                         FINISH_DATE DATE
);

-- Створення таблиці "project_worker"
CREATE TABLE project_worker (
                                PROJECT_ID INT REFERENCES project(ID),
                                WORKER_ID INT REFERENCES worker(ID),
                                PRIMARY KEY (PROJECT_ID, WORKER_ID)
);