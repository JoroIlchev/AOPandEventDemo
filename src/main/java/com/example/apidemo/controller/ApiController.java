package com.example.apidemo.controller;

import com.example.apidemo.config.ApiConfiguration;
import com.example.apidemo.entity.Food;
import com.example.apidemo.entity.ListOfFoods;
import com.example.apidemo.events.ApiEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;

@RestController
public class ApiController {

    private final ApiConfiguration configuration;
    private final ApplicationEventPublisher publisher;


    public ApiController(ApiConfiguration configuration, ApplicationEventPublisher publisher) {
        this.configuration = configuration;
        this.publisher = publisher;
    }

    @GetMapping()
    public ResponseEntity<List<Food>> getAllFoods() {

        ApiEvent event = new ApiEvent(this, "ApiTest");
        publisher.publishEvent(event);

        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.btk.bg", 80));
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setProxy(proxy);

        RestTemplate restTemplate = new RestTemplate(requestFactory);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<ListOfFoods> request = new HttpEntity<>(headers);

        String key = this.configuration.getKey();
        String secret = this.configuration.getSecret();
        String url = UriComponentsBuilder.fromHttpUrl(this.configuration.getUrl())
                .queryParam(key, secret)
                .encode()
                .toUriString();

        ResponseEntity<ListOfFoods> responseEntity =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        request,
                        new ParameterizedTypeReference<ListOfFoods>() {
                        }
                );
        return ResponseEntity.ok().body(responseEntity.getBody().getResults());
    }
}
