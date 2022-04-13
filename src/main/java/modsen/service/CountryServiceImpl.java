package modsen.service;

import modsen.model.Country;
import modsen.repository.CountryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService{
    private CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Page<Country> list(int value) {
        Pageable firstPageWithTenElements = PageRequest.of(value, 10);
        return countryRepository.findAll(firstPageWithTenElements);
    }

    @Override
    public void create(Country country) {
        countryRepository.save(country);
    }

    @Override
    public boolean delete(String code) {
        if(!countryRepository.existsById(code)){
            return false;
        }
        countryRepository.deleteById(code);
        return true;
    }

    @Override
    public void update(Country country, String code) {
        Country countryNew = countryRepository.findById(code).get();

        if (country.getCode() != null && !countryNew.getCode().equals(country.getCode())) {
            countryNew.setCode(country.getCode());
        }
        if(country.getName()!= null){
            countryNew.setName(country.getName());
        }
        if(country.getLatitude()!=null){
            countryNew.setLatitude(country.getLatitude());
        }
        if(country.getLongitude()!=null){
            countryNew.setLongitude(country.getLongitude());
        }
        countryRepository.save(countryNew);
    }

    @Override
    public Country getByCode(String code) {
        return countryRepository.findById(code).get();
    }
}
