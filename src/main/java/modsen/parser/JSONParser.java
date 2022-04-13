package modsen.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import modsen.model.Country;
import modsen.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
@Component
public class JSONParser {

    private final String JSON_PATH = "C:\\Users\\андрей\\Desktop\\modsen\\src\\main\\resources\\country.json";
    @Autowired
    private CountryRepository countryRepository;

    public static List<Country> getCountries(String path) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper.readValue(getFile(path), new TypeReference<List<Country>>() {});
        } catch (IOException e) {
            throw new IllegalArgumentException("Error", e);
        }
    }

    private static File getFile(String filePath) {
        File file = new File(filePath);
        return file;
    }

    public void load() {
        List<Country> countries = getCountries(JSON_PATH);
        countryRepository.saveAll(countries);
    }


}
