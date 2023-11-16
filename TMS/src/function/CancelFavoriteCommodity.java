package function;

import favorite.Favorite;
import people.Customer;
import variable.GlobalClass;

public class CancelFavoriteCommodity {
    public static boolean cancelFavoriteCommodity(String[]arc){
        if(arc.length!=3){
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
        else if(!Operator.shopIdJudge(arc[1])){
            System.out.println("Illegal shop id");
            return false;
        }
        else if(!Operator.commodityIdJudge(arc[2])){
            System.out.println("Illegal commodity id");
            return false;
        }
        Customer customer=GlobalClass.CustomerMap.get(GlobalClass.personLogged.getKakafeeCode());
        if(customer.getFavorites().isEmpty()){
            System.out.println("Favorite not exists");
            return false;
        }
        else{
            int flag=0;Favorite f0 = null;
            for(Favorite f:customer.getFavorites()){
                if(f.getCommodity().getShopId().equals(arc[1])&&f.getCommodity().getCommodityUnit().getId().equals(arc[2])&&f.isStatus()){
                    flag=1;
                    f0=f;
                    break;
                }
            }
            if(flag==0){
                System.out.println("Favorite not exists");
                return false;
            }
            else{
                customer.getFavorites().remove(f0);
                System.out.println("Cancel favorite commodity success");
                return true;
            }
        }
    }
}
