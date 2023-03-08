package com.saranganrajan.apps.coredomainextractor;

import com.saranganrajan.apps.coredomainextractor.external.processor.feign.CoreProcessFeignClient;
import com.saranganrajan.apps.coredomainextractor.extract.CsvFileExtractor;
import com.saranganrajan.apps.coredomainextractor.extract.FileExtractor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

@EnableFeignClients("com.saranganrajan.apps.coredomainextractor")
@SpringBootApplication
@EnableScheduling
@Slf4j
public class CoreDomainExtractorApplication implements CommandLineRunner {

    @Autowired
    CoreProcessFeignClient feignClient;

    public static void main(String[] args) {
        SpringApplication.run(CoreDomainExtractorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        processIncomingFiles();
    }

    @Scheduled(fixedDelay = 180000, initialDelay = 180000)
    public void processIncomingFiles() throws IOException {
        log.info("Processing incoming files");
        FileExtractor fileExtractor = new CsvFileExtractor(feignClient);
        fileExtractor.extractRawData();
    }

}
