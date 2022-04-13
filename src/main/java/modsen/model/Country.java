package modsen.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "countries")
public class Country {
    private String name;
    @Id
    @JsonProperty("cca3")
    private String code;
    private Double longitude;
    private Double latitude;
    @JsonProperty("borders")
    private String[] borders;

    public Country() {
    }

    public Country(String name, String code, Double longitude, Double latitude, String[] borders) {
        this.name = name;
        this.code = code;
        this.longitude = longitude;
        this.latitude = latitude;
        this.borders = borders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String[] getBorders() {
        return borders;
    }

    public void setBorders(String[] borders) {
        this.borders = borders;
    }

    @JsonProperty("name")
    private void nameParser(Map<String,Object> map){
        this.name = map.get("official").toString();
    }

    @JsonProperty("latlng")
    private void latlngParser(List<Object> list){
        this.latitude = Double.parseDouble(list.get(0).toString());
        this.longitude = Double.parseDouble(list.get(1).toString());
    }

}
