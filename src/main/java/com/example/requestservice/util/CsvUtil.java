package com.example.requestservice.util;

import com.example.requestservice.model.RequestView;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvUtil {

    public static void writeCsv(String filePath, List<RequestView> data) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("id,host,path,timestamp,avgHeaders,avgParams\n");
            for (RequestView rv : data) {
                writer.append(rv.getId().toString()).append(",");
                writer.append(escape(rv.getHost())).append(",");
                writer.append(escape(rv.getPath())).append(",");
                writer.append(rv.getTimestamp().toString()).append(",");
                writer.append(rv.getAvgHeaders().toString()).append(",");
                writer.append(rv.getAvgParams().toString()).append("\n");
            }
        }
    }

    private static String escape(String s) {
        if (s == null) return "";
        String escaped = s.replace("\"", "\"\"");
        return s.contains(",") || s.contains("\n") ? "\"" + escaped + "\"" : s;
    }
}