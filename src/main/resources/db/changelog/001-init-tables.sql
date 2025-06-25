-- src/main/resources/db/changelog/001-init-tables.sql

-- liquibase formatted sql

-- changeset nikita:create_request_view runOnChange:true
CREATE TABLE IF NOT EXISTS request (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       url VARCHAR(255),
                                       path VARCHAR(255),
                                       body TEXT,
                                       timestamp TIMESTAMP
);

CREATE TABLE IF NOT EXISTS header (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      header_key VARCHAR(255),
                                      header_value VARCHAR(255),
                                      request_id BIGINT,
                                      CONSTRAINT fk_header_request FOREIGN KEY (request_id) REFERENCES request(id)
);

CREATE TABLE IF NOT EXISTS query_param (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           query_key VARCHAR(255),
                                           query_value VARCHAR(255),
                                           request_id BIGINT,
                                           CONSTRAINT fk_param_request FOREIGN KEY (request_id) REFERENCES request(id)
);

CREATE TABLE IF NOT EXISTS report (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      host VARCHAR(255),
                                      path VARCHAR(255),
                                      start_date TIMESTAMP,
                                      end_date TIMESTAMP,
                                      min_headers INT,
                                      min_params INT,
                                      done BOOLEAN,
                                      created_at TIMESTAMP,
                                      csv_path VARCHAR(255),
                                      error_message VARCHAR(1024)
);
