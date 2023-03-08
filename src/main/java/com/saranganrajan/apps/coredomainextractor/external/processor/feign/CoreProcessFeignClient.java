package com.saranganrajan.apps.coredomainextractor.external.processor.feign;

import com.saranganrajan.apps.coredomainextractor.model.PolicyTransaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="core-domain-processor", url="http://20.120.52.99:8085")
public interface CoreProcessFeignClient {
    @PostMapping(path = "/policy/premium/pay")
    ResponseEntity<List<PolicyTransaction>> processPolicyTransaction(@RequestBody List<PolicyTransaction> policies);
}
