package com.saranganrajan.apps.coredomainextractor.extract;

import com.saranganrajan.apps.coredomainextractor.model.PolicyTransaction;

import java.io.IOException;
import java.util.List;

public interface FileExtractor {
    public void extractRawData() throws IOException;
}
