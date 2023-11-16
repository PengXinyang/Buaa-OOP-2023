package function;

import favorite.Favorite;
import people.Customer;
import variable.GlobalClass;

public class ListFavoriteCommodity {
    public static boolean listFavoriteCommodity(String[]arc){
        if(arc.length!=1){
            System.out.println("Illegal argument count");
            return false;
        }
        else if(!GlobalClass.logStatus){
            System.out.println("Please log in first");
            return false;
        }
        else if(!GlobalClass.personLogged.getIdentify().equals("Customer")){
            System.out.println("Permission denied");
            return false;
        }
        Customer customer=GlobalClass.CustomerMap.get(GlobalClass.personLogged.getKakafeeCode());
        if(customer.getFavorites().isEmpty()){
            System.out.println("Favorite not exists");
            return false;
        }
        else{
            for(Favorite f:customer.getFavorites()){
                f.printMessage();
            }
            return true;
        }
    }
}
