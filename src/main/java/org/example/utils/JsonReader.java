package org.example.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.TestData;
import org.example.models.UserData;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class JsonReader {

    @DataProvider(name = "userData")
    public Object[][] getUserData() throws IOException {
        String filePath = "src/test/resources/test-data/userData.json";
        List<UserData> userDataList = readTestDataFromJson(filePath);

        Object[][] data = new Object[userDataList.size()][1];
        for (int i = 0; i < userDataList.size(); i++) {
            data[i][0] = userDataList.get(i);
        }

        return data;
    }

    private List<UserData> readTestDataFromJson(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);

        try (FileInputStream fis = new FileInputStream(file)) {
            return objectMapper.readValue(fis, new TypeReference<>() {
            });
        }
    }

    @DataProvider(name = "testData")
    public Object[][] getProductData() throws IOException {
        String filePath = "src/test/resources/test-data/testData.json";
        List<TestData> testDataList = readProductDataFromJson(filePath);

        Object[][] data = new Object[testDataList.size()][1];
        for (int i = 0; i < testDataList.size(); i++) {
            data[i][0] = testDataList.get(i);
        }

        return data;
    }

    private List<TestData> readProductDataFromJson(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);

        try (FileInputStream fis = new FileInputStream(file)) {
            return objectMapper.readValue(fis, new TypeReference<>() {
            });
        }
    }

}
