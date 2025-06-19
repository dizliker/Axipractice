package com.example.requestservice.service;

import com.example.requestservice.dto.RequestInputDTO;
import com.example.requestservice.model.Header;
import com.example.requestservice.model.QueryParam;
import com.example.requestservice.model.Request;
import com.example.requestservice.repo.RequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RequestService {

    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    // Обработка JSON
    public Request processJsonInput(RequestInputDTO dto) {
        Request request = new Request(
                dto.getUrl(),
                dto.getPath(),
                dto.getBody(),
                LocalDateTime.now()
        );

        List<Header> headers = new ArrayList<>();
        if (dto.getHeaders() != null) {
            for (Map.Entry<String, String> entry : dto.getHeaders().entrySet()) {
                headers.add(new Header(entry.getKey(), entry.getValue(), request));
            }
        }

        List<QueryParam> queryParams = new ArrayList<>();
        if (dto.getVariable_params() != null) {
            for (Map.Entry<String, String> entry : dto.getVariable_params().entrySet()) {
                queryParams.add(new QueryParam(entry.getKey(), entry.getValue(), request));
            }
        }

        request.setHeaders(headers);
        request.setQueryParams(queryParams);

        return requestRepository.save(request);
    }

    // Обработка строки URL (простой вид)
    public Request processUrlInput(String urlString) {
        try {
            java.net.URL url = new java.net.URL(urlString);
            String host = url.getProtocol() + "://" + url.getHost();
            String path = url.getPath();

            Request request = new Request(host, path, null, LocalDateTime.now());
            return requestRepository.save(request);
        } catch (Exception e) {
            throw new RuntimeException("Invalid URL", e);
        }
    }
}
