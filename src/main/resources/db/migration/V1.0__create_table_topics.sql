CREATE TABLE topics (
    id VARCHAR(36) NOT NULL,
    title VARCHAR(100) NOT NULL,
    message VARCHAR(256) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL,
    author VARCHAR(100) NOT NULL,
    course VARCHAR(100) NOT NULL,

    PRIMARY KEY (id)
);