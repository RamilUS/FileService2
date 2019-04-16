CREATE TABLE IF NOT EXISTS file(
    id SERIAL       NOT NULL PRIMARY KEY ,
    version         INTEGER ,
    file_name       VARCHAR(100) NOT NULL,
    file_data BYTEA NOT NULL,
    description     VARCHAR(150) NOT NULL,
    download_count  INTEGER
);
