package modsen.alghoritm;

import modsen.model.Country;

import java.util.ArrayList;
import java.util.List;

public class Routing {

    public boolean getRoute(Country from, Country to){
        List<String> fromBorders = new ArrayList<String>(List.of(from.getBorders()));
        List<String> toBorders = new ArrayList<String>(List.of(to.getBorders()));

        if(fromBorders.contains(to.getCode())){
            return true;
        }
        if(!fromBorders.contains(to.getCode())){
            for(int i = 0; i < fromBorders.size(); i++){
                if(toBorders.contains(fromBorders.get(i))){
                    return true;
                }
            }
        }

        return false;
    }
}
