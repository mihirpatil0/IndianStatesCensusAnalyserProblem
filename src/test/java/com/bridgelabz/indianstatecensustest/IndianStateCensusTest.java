package com.bridgelabz.indianstatecensustest;

import com.bridgelabz.indianstatecensus.CustomExceptionService;
import com.bridgelabz.indianstatecensus.IndianStateCensusAnalyzer;
import com.bridgelabz.indianstatecensus.StateCences;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/*********************************************
 * @author mihir
 * @since 22-July-2021
 *
 * This class holds all the test cases,
 * that validates methods present in a project.
 *********************************************/

public class IndianStateCensusTest
{
    @Test
    public void givenReadDataFromIndianCensus_ShouldReturnCountDataPresentInFile()
    {
        String fileName = "/IndiaStateCensusData.csv";
        IndianStateCensusAnalyzer censusService = new IndianStateCensusAnalyzer();
        List<StateCences> stateCencesList = censusService.readInIndiaStateCensusData(fileName);
        Assertions.assertEquals(29, stateCencesList.size());
    }

    @Test
    public void givenWrongFile_ShouldReturnFileNotFound()
    {
        String fileName = "/IndiaStateCensus.csv";
        IndianStateCensusAnalyzer censusService = new IndianStateCensusAnalyzer();
        try
        {
            List<StateCences> stateCencesList = censusService.readInIndiaStateCensusData(fileName);
            Assertions.assertEquals(29, stateCencesList.size());
        }
        catch (CustomExceptionService e)
        {
            Assertions.assertEquals(CustomExceptionService.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }
}
