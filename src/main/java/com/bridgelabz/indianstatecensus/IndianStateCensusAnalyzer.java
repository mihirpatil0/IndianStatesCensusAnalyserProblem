package com.bridgelabz.indianstatecensus;

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

public class IndianStateCensusAnalyzer
{
    private static final String FILE_PATH = "C:\\Users\\mihir\\IdeaProjects\\IndianStatesCensusAnalyser\\src\\resources";
    String[] headers = {"State", "Population", "AreaInSqKm", "DensityPerSqKm"};

    public List<StateCences> readInIndiaStateCensusData(String fileName)
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
                throw new CustomExceptionService(CustomExceptionService.ExceptionType.WRONG_HEADER,"enter proper extension");
            }
            Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH+fileName));   //reader to read contacts
            CsvToBean<StateCences> csvToBean = new CsvToBeanBuilder<StateCences>(reader)
                    .withType(StateCences.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();   //Converting them to list
        }
        catch (NoSuchFileException e)
        {
            throw new CustomExceptionService(CustomExceptionService.ExceptionType.FILE_NOT_FOUND,"File Not Found");
        }
        catch (FileNotFoundException e)
        {
            throw new CustomExceptionService(CustomExceptionService.ExceptionType.FILE_NOT_FOUND,"File Not Found");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
