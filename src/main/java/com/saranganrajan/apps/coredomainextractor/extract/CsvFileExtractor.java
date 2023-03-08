package com.saranganrajan.apps.coredomainextractor.extract;

import com.saranganrajan.apps.coredomainextractor.external.processor.feign.CoreProcessFeignClient;
import com.saranganrajan.apps.coredomainextractor.model.PolicyTransaction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class CsvFileExtractor implements FileExtractor {

    @Autowired
    private CoreProcessFeignClient feignClient;

    public CsvFileExtractor(CoreProcessFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    @Override
    public void extractRawData() throws IOException {
        Pattern pattern = Pattern.compile(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        File sourceFolder = new File("C:\\Users\\saran\\OneDrive\\Documents\\Sarangapani\\Upgrad\\Thesis\\Implementation" +
                "\\Dataset\\to_process");
        File destinationFolder = new File("C:\\Users\\saran\\OneDrive\\Documents\\Sarangapani\\Upgrad\\Thesis\\Implementation" +
                "\\Dataset\\processed");
        if(sourceFolder.isDirectory()) {
            File[] processableFiles = sourceFolder.listFiles();
            if(processableFiles == null || processableFiles.length == 0) {
                log.info("No files to process");
                return;
            }
            for(File policyFile : processableFiles) {
                try (BufferedReader in = new BufferedReader(new FileReader(policyFile))) {
                    List<PolicyTransaction> policies = in.lines().skip(1).map(line -> {
                        String[] x = pattern.split(line);
                        return new PolicyTransaction(x[0],  Double.parseDouble(x[1]), LocalDate.parse(x[2], formatter), x[3]);
                    }).collect(Collectors.toList());
                    System.out.println(feignClient.processPolicyTransaction(policies));
                } catch (IOException e) {
                    log.error("Error while processing file {}", policyFile.getName());
                    e.printStackTrace();
                }
                FileUtils.moveFileToDirectory(policyFile, destinationFolder, false);
                log.info("Moved file {} to {}", policyFile.getName(), destinationFolder.getName());
            }
        }
    }
}
