package com.example.genesis.validator;

import com.example.genesis.service.CannotReadPersonIdListException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ValidPersonIdValidator implements ConstraintValidator<ValidPersonId, String> {
    private static final String DATA_PERSON_ID_RESOURCE = "/data/dataPersonId.txt";

    private List<String> personIdList = new ArrayList<>();

    @Override
    public void initialize(ValidPersonId constraintAnnotation) {
        try (InputStream is = Objects.requireNonNull(ValidPersonIdValidator.class.getResourceAsStream(DATA_PERSON_ID_RESOURCE));
             InputStreamReader isr = new InputStreamReader(is);
             BufferedReader br = new BufferedReader(isr)) {

            personIdList = new ArrayList<>();

            String line;

            while ((line = br.readLine()) != null) {
                personIdList.add(line);
            }
        } catch (IOException e) {
            throw new CannotReadPersonIdListException("Cannot read person ID list from resources", e);
        }
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return personIdList.contains(s);
    }
}
