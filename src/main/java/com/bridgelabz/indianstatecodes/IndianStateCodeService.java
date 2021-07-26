package com.bridgelabz.indianstatecodes;

import com.bridgelabz.customexception.CustomExceptionService;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class IndianStateCodeService
{
    private static final String FILE_PATH = "C:\\Users\\mihir\\IdeaProjects\\IndianStatesCensusAnalyser\\src\\resources";
    String[] headers = {"SrNo","State Name","TIN","StateCode"};

    public List<StateCode> readIndiaStatCode(String fileName)
    {
        try
        {
            if (!fileName.split("\\.")[1].equals("csv"))
            {
                throw new CustomExceptionService(CustomExceptionService.ExceptionType.WRONG_FILE_TYPE,"enter proper extension");
            }

            @SuppressWarnings("resource")
            String fileHeaders[] = new CSVReader(new FileReader(FILE_PATH+fileName)).readNext();
            if (!Arrays.toString(fileHeaders).equals(Arrays.toString(headers)))
            {
                throw new CustomExceptionService(CustomExceptionService.ExceptionType.WRONG_HEADER,"Header missmatch");
            }
            Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH+fileName));
            CsvToBean<StateCode> csvToBean = new CsvToBeanBuilder<StateCode>(reader)
                    .withType(StateCode.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();
        }
        catch (NoSuchFileException e)
        {
            throw new CustomExceptionService(CustomExceptionService.ExceptionType.FILE_NOT_FOUND,"File Not Found");
        }
        catch (FileNotFoundException e)
        {
            throw new CustomExceptionService(CustomExceptionService.ExceptionType.FILE_NOT_FOUND,"File Not Found");
        }
        catch (IOException | CsvValidationException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
