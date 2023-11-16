package function;

import order.Order;
import people.Customer;
import people.Merchant;
import shop.Shop;
import variable.GlobalClass;

import java.util.Map;

public class ListOrder {
    public static boolean listOrder(String[] arc){
        if(arc.length!=2&&arc.length!=1){
            System.out.println("Illegal argument count");
            return false;
        }
        else if(!GlobalClass.logStatus){
            System.out.println("Please log in first");
            return false;
        }
        else{
            if(arc.length==1){
                if(GlobalClass.orderMap.isEmpty()){
                    System.out.println("Order not exists");
                    return false;
                }
                if(GlobalClass.personLogged.getIdentify().equals("Administrator")){
                    for(Map.Entry<String,Order> o:GlobalClass.orderMap.entrySet()){
                        o.getValue().printMessage();
                    }
                    return true;
                }
                else if(GlobalClass.personLogged.getIdentify().equals("Merchant")){
                    Merchant m=GlobalClass.MerchantMap.get(GlobalClass.personLogged.getKakafeeCode());
                    if(m.getOrders().isEmpty()){
                        System.out.println("Order not exists");
                        return false;
                    }
                    else{
                        for(Map.Entry<String,Order> o:m.getOrders().entrySet()){
                            o.getValue().printMessage();
                        }
                        return true;
                    }
                }
                else{
                    Customer c=GlobalClass.CustomerMap.get(GlobalClass.personLogged.getKakafeeCode());
                    if(c.getOrderMap().isEmpty()){
                        System.out.println("Order not exists");
                        return false;
                    }
                    else{
                        for(Map.Entry<String,Order> o:c.getOrderMap().entrySet()){
                            o.getValue().printMessage();
                        }
                        return true;
                    }
                }
            }
            else{
                if(GlobalClass.personLogged.getIdentify().equals("Customer")){
                    System.out.println("Permission denied");
                    return false;
                }
                else if(!Operator.shopIdJudge(arc[1])){
                    System.out.println("Illegal shop id");
                    return false;
                }
                else if(!GlobalClass.Shop.containsKey(arc[1])){
                    System.out.println("Shop id not exists");
                    return false;
                }
                else if(GlobalClass.personLogged.getIdentify().equals("Merchant")){
                    Merchant m=GlobalClass.MerchantMap.get(GlobalClass.personLogged.getKakafeeCode());
                    if(!m.getShop().containsKey(arc[1])){
                        System.out.println("Shop id not exists");
                        return false;
                    }
                    Shop s=GlobalClass.Shop.get(arc[1]);
                    if(s.getOrderMap().isEmpty()){
                        System.out.println("Order not exists");
                        return false;
                    }
                    else{
                        for(Map.Entry<String,Order>o:s.getOrderMap().entrySet()){
                            o.getValue().printMessage();
                        }
                        return true;
                    }
                }
                Shop s=GlobalClass.Shop.get(arc[1]);
                if(s.getOrderMap().isEmpty()){
                    System.out.println("Order not exists");
                    return false;
                }
                else{
                    for(Map.Entry<String,Order>o:s.getOrderMap().entrySet()){
                        o.getValue().printMessage();
                    }
                    return true;
                }
            }
        }
    }
}
