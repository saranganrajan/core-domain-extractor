package com.saranganrajan.apps.coredomainextractor.extract;

import com.saranganrajan.apps.coredomainextractor.model.PolicyTransaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CsvFileExtractor implements FileExtractor {
    @Override
    public List<PolicyTransaction> extractRawData() {
        Pattern pattern = Pattern.compile(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String csvFile = "C:\\Users\\saran\\OneDrive\\Documents\\Sarangapani\\Upgrad\\Thesis\\Implementation\\Dataset\\to_process\\policy_batch_a.csv";
        try (BufferedReader in = new BufferedReader(new FileReader(csvFile))) {
            List<PolicyTransaction> policies = in.lines().skip(1).map(line -> {
                String[] x = pattern.split(line);
                return new PolicyTransaction(x[0],  Double.parseDouble(x[1]), LocalDate.parse(x[2], formatter), x[3]);
            }).collect(Collectors.toList());
            return policies;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
