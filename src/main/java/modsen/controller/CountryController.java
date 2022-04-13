package modsen.controller;

import modsen.alghoritm.Routing;
import modsen.model.Country;
import modsen.service.CountryServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CountryController {
    private CountryServiceImpl countryService;

    public CountryController(CountryServiceImpl countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries/page={value}")
    public Page<Country> getForms(@PathVariable int value){
        return countryService.list(value);
    }

    @PostMapping(value = "/countries")
    public ResponseEntity<?> createCountry(@RequestBody Country country) {
        countryService.create(country);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/countries/{id}")
    public ResponseEntity<?> deleteCountry(@PathVariable(name = "id") String code) {
        final boolean deleted = countryService.delete(code);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @PostMapping(value = "/countries/edit/{id}")
    public ResponseEntity<?> updateCountry(@RequestBody Country country, @PathVariable(name = "id") String code){
        countryService.update(country, code);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/routing/alghoritm/{from}/{to}")
    public ResponseEntity<?> getRoute(@PathVariable String from, @PathVariable String to) {
        Country fromCountry = countryService.getByCode(from);
        Country toCountry = countryService.getByCode(to);

        Routing routing = new Routing();
        final boolean routeExists = routing.getRoute(fromCountry, toCountry);

        return routeExists
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
