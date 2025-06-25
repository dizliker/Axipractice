-- liquibase formatted sql

-- changeset nikita:create_request_view runOnChange:true
CREATE OR REPLACE VIEW request_view AS
SELECT
    r.id AS id,
    CASE
        WHEN LOCATE('/', r.url, 9) > 0 THEN SUBSTRING(r.url, 1, LOCATE('/', r.url, 9) - 1)
        ELSE r.url
        END AS host,
    r.path AS path,
    r.timestamp AS timestamp,
    COALESCE(h.cnt, 0) AS avg_headers,
    COALESCE(q.cnt, 0) AS avg_params
FROM request r
         LEFT JOIN (SELECT request_id, COUNT(*) AS cnt FROM header GROUP BY request_id) h ON r.id = h.request_id
         LEFT JOIN (SELECT request_id, COUNT(*) AS cnt FROM query_param GROUP BY request_id) q ON r.id = q.request_id;
