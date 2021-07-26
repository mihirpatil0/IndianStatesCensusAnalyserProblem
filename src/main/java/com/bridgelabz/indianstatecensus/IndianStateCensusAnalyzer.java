package com.bridgelabz.indianstatecensus;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

public class IndianStateCensusAnalyzer
{
    private static final String FILE_PATH = "C:\\Users\\mihir\\IdeaProjects\\IndianStatesCensusAnalyser\\src\\resources";

    public List<StateCences> readInIndiaStateCensusData(String FileName)
    {
        try
        {
            if (!FileName.split("\\.")[1].equals("csv"))
            {
                throw new CustomExceptionService(CustomExceptionService.ExceptionType.WRONG_FILE_TYPE,"enter proper extension");
            }
            Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH+FileName));
            CsvToBean<StateCences> csvToBean = new CsvToBeanBuilder<StateCences>(reader)
                    .withType(StateCences.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();
        }
        catch (NoSuchFileException e)
        {
            throw new CustomExceptionService(CustomExceptionService.ExceptionType.FILE_NOT_FOUND,"File Not Found");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
