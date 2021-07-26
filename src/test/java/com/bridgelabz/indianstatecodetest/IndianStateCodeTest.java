package com.bridgelabz.indianstatecodetest;

import com.bridgelabz.customexception.CustomExceptionService;
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
    }

    @Test
    public void givenWrongFileExtention_ShouldReturnWrongFileType()
    {
        String fileName = "/StateCodeData.txt";
        IndianStateCodeService censusService = new IndianStateCodeService();
        try
        {
            List<StateCode> StateCodeList = censusService.readIndiaStatCode(fileName);
            Assertions.assertEquals(37, StateCodeList.size());
        }
        catch (CustomExceptionService e)
        {
            Assertions.assertEquals(CustomExceptionService.ExceptionType.WRONG_FILE_TYPE, e.type);
        }
    }

    @Test
    public void givenWrongFile_ShouldReturnFileNotFound()
    {
        String fileName = "/StateCodeing.csv";
        IndianStateCodeService censusService = new IndianStateCodeService();
        try
        {
            List<StateCode> StateCodeList = censusService.readIndiaStatCode(fileName);
            Assertions.assertEquals(37, StateCodeList.size());
        }
        catch (CustomExceptionService e)
        {
            Assertions.assertEquals(CustomExceptionService.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }
}
