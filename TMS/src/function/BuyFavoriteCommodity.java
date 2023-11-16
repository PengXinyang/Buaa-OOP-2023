package function;

import commodity.Commodity;
import favorite.Favorite;
import order.Order;
import people.Customer;
import people.Merchant;
import shop.Shop;
import variable.GlobalClass;

public class BuyFavoriteCommodity {
    public static boolean buyFavoriteCommodity(String[]arc){
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
                if(!GlobalClass.Shop.get(f.getCommodity().getShopId()).isStatus()){
                    System.out.println("Shop id not exists");
                }
                else if(!f.getCommodity().isStatus()||!f.getCommodity().getCommodityUnit().isStatus()){
                    System.out.println("Commodity id not exists");
                }
                else{
                    int number=f.getNumber();
                    Commodity c0=f.getCommodity();
                    Shop s=GlobalClass.Shop.get(c0.getShopId());
                    if(!c0.isStatus()){
                        System.out.println("Commodity id not exists");
                    }
                    else if(number>c0.getCount()){
                        System.out.println("Illegal buy quantity");
                    }
                    else{
                        c0.setCount(c0.getCount()-number);
                        Order o=new Order(c0,number,s,customer,"pending");
                        customer.getOrderMap().put(o.getOrderId(),o);
                        Merchant m=s.getMerchant();
                        m.setOrders(o);
                        GlobalClass.orderMap.put(o.getOrderId(),o);
                        s.getOrderMap().put(o.getOrderId(),o);
                        o.printMessage();
                    }
                }
            }
            customer.getFavorites().clear();
            return true;
        }
    }
}
