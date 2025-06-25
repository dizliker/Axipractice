-- src/main/resources/db/changelog/002-create-view.sql
-- liquibase formatted sql

-- changeset nikita:create_request_view_grouped runOnChange:true
CREATE OR REPLACE VIEW request_view AS
SELECT
    MIN(r.id) AS id,
    r.url AS host,
    r.path AS path,
    MIN(r.timestamp) AS timestamp,
    AVG(COALESCE(h.cnt, 0)) AS avg_headers,
    AVG(COALESCE(q.cnt, 0)) AS avg_params
FROM request r
         LEFT JOIN (
    SELECT request_id, COUNT(*) AS cnt
    FROM header
    GROUP BY request_id
) h ON r.id = h.request_id
         LEFT JOIN (
    SELECT request_id, COUNT(*) AS cnt
    FROM query_param
    GROUP BY request_id
) q ON r.id = q.request_id
GROUP BY r.url, r.path;
