package modsen.service;

import modsen.model.Country;
import org.springframework.data.domain.Page;

public interface CountryService {
    Page<Country> list(int value);
    void create(Country country);
    boolean delete(String code);
    void update(Country country, String code);
    Country getByCode(String code);
}
