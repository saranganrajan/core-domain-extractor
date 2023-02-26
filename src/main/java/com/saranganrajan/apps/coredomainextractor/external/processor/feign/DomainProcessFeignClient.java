package com.saranganrajan.apps.coredomainextractor.external.processor.feign;

import com.saranganrajan.apps.coredomainextractor.model.PolicyTransaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="core-domain-processor", url="http://localhost:8080")
public interface DomainProcessFeignClient {
    @PostMapping(path = "/policy/premium/pay")
    ResponseEntity<String> processPolicyTransaction(@RequestBody List<PolicyTransaction> policies);
}
