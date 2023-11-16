package function;

import order.Order;
import people.Administrator;
import people.Merchant;
import shop.Shop;
import variable.GlobalClass;

import java.util.Map;

public class CancelShop {
    public static boolean cancelShop(String[]arc){
        if(arc.length!=2){
            System.out.println("Illegal argument count");
            return false;
        }
        else if(!GlobalClass.logStatus){
            System.out.println("Please log in first");
            return false;
        }
        else if(!(GlobalClass.personLogged.getIdentify().equals("Administrator")||GlobalClass.personLogged.getIdentify().equals("Merchant"))){
            System.out.println("Permission denied");
            return false;
        }
        else if(!Operator.shopIdJudge(arc[1])){//先判断商店状态
            System.out.println("Illegal shop id");
            return false;
        }
        else{
            if(!GlobalClass.Shop.containsKey(arc[1])||!GlobalClass.Shop.get(arc[1]).isStatus()){
                System.out.println("Shop id not exists");
                return false;
            }
            else if(GlobalClass.personLogged.getIdentify().equals("Merchant")){
                Merchant m=GlobalClass.MerchantMap.get(GlobalClass.personLogged.getKakafeeCode());
                if(!m.getShop().containsKey(arc[1])){
                    System.out.println("Shop id not exists");
                    return false;
                }
                else {
                    Shop s=m.getShop().get(arc[1]);
                    for(Map.Entry<String, Order>o:s.getOrderMap().entrySet()){
                        if(o.getValue().getStatus().equals("pending")){
                            System.out.println("Please process order for shop");
                            return false;
                        }
                    }
                    m.cancelShop(arc[1]);
                    System.out.println("Cancel shop success");
                    return true;
                }
            }
            else{
                Shop s=GlobalClass.Shop.get(arc[1]);
                for(Map.Entry<String, Order>o:s.getOrderMap().entrySet()){
                    if(o.getValue().getStatus().equals("pending")){
                        System.out.println("Please process order for shop");
                        return false;
                    }
                }
                Administrator a=GlobalClass.AdministratorMap.get(GlobalClass.personLogged.getKakafeeCode());
                a.cancelShop(arc[1]);
                System.out.println("Cancel shop success");
                return true;
            }
        }
    }
}
