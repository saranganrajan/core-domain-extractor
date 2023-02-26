package com.saranganrajan.apps.coredomainextractor.extract;

import com.saranganrajan.apps.coredomainextractor.model.PolicyTransaction;

import java.util.List;

public interface FileExtractor {
    public List<PolicyTransaction> extractRawData();
}
