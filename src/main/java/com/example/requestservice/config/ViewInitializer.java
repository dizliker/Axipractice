package com.example.requestservice.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ViewInitializer implements CommandLineRunner {

    private final JdbcTemplate jdbc;

    public ViewInitializer(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            jdbc.execute("DROP VIEW request_view");
        } catch (Exception ignored) {
            // Игнорируем, если представления нет
        }

        try {
            jdbc.execute(
                    "CREATE VIEW request_view AS " +
                            "SELECT r.id AS id, " +
                            "SUBSTRING(r.url, 1, LOCATE('/', r.url, 9) - 1) AS host, " +
                            "r.path AS path, " +
                            "r.timestamp AS timestamp, " +
                            "COALESCE(h.cnt, 0) AS avg_headers, " +
                            "COALESCE(q.cnt, 0) AS avg_params " +
                            "FROM request r " +
                            "LEFT JOIN (SELECT request_id, COUNT(*) AS cnt FROM header GROUP BY request_id) h ON r.id = h.request_id " +
                            "LEFT JOIN (SELECT request_id, COUNT(*) AS cnt FROM query_param GROUP BY request_id) q ON r.id = q.request_id"
            );
        } catch (Exception ignored) {
        }
    }
}