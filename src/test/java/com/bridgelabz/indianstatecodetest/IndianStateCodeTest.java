package com.bridgelabz.indianstatecodetest;

import com.bridgelabz.indianstatecodes.IndianStateCodeService;
import com.bridgelabz.indianstatecodes.StateCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IndianStateCodeTest
{
    @Test
    public void givenReadDataFromIndianCensus_ShouldReturnCountDataPresentInFile()
    {
        String fileName = "/StateCode.csv";
        IndianStateCodeService codeService = new IndianStateCodeService();
        List<StateCode> stateCencesList = codeService.readIndiaStatCode(fileName);
        Assertions.assertEquals(37, stateCencesList.size());
    }//
}
