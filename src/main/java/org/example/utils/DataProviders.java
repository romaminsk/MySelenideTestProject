package org.example.utils;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.example.annotations.TestData;
import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataProviders {

    @DataProvider
    public Object[][] getData(Method method) throws FileNotFoundException {
        String jsonData = new JsonParser()
                .parse(new FileReader(method.getAnnotation(TestData.class).path() + method.getName() + ".json")).toString();

        ArrayList<Object> list = null;
        try {
            list = new Gson().fromJson(jsonData, TypeToken.getParameterized(List.class,
                    Class.forName("org.example.models." + method.getAnnotation(TestData.class)
                            .modelType())).getType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return Objects.requireNonNull(list).stream()
                .map(data -> new Object[]{data})
                .toArray(Object[][]::new);
    }
}
