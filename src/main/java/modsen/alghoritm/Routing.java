package modsen.alghoritm;

import modsen.model.Country;

import java.util.ArrayList;
import java.util.List;

public class Routing {

    public List<String> getRouting(Country from, Country to){
        List<String> fromBorders = new ArrayList<String>(List.of(from.getBorders()));
        List<String> toBorders = new ArrayList<String>(List.of(to.getBorders()));

        List<String> routing = new ArrayList<>();

        if(fromBorders.contains(to.getCode())){
            routing.add(from.getCode());
            routing.add(to.getCode());
            return routing;
        }
        if(!fromBorders.contains(to.getCode())){
            for(int i = 0; i < fromBorders.size(); i++){
                if(toBorders.contains(fromBorders.get(i))){
                    routing.add(from.getCode());
                    routing.add(fromBorders.get(i));
                    routing.add(to.getCode());
                }
            }
        }

        return routing;
    }
}
