package me.kupchenko.client;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "${client.service.name}", url = "${client.service.url}")
public interface ThirdPartyServiceClient {

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.GET, value = "/ok")
    void getJobById();

}
