package com.plb.employeemgt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeatureBService {

    @Autowired
    private VinylService vinylService;

    public FeatureBService() {
    }

    public void featureBCode() {
        vinylService.initData();
    }

}
