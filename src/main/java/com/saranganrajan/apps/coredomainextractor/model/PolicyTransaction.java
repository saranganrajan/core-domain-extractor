package com.saranganrajan.apps.coredomainextractor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PolicyTransaction {
    private String policyNumber;
    private Double premiumPaid;
    private LocalDate paymentDate;
    private String paymentMode;
}
