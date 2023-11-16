package function;
import people.*;
import shop.Shop;
import variable.GlobalClass;
import function.Operator;

import java.util.Map;

public class ShopRegister {
    public static boolean shopRegister(String []arc){//注册店铺指令
        if(arc.length!=2){
            System.out.println("Illegal argument count");
            return false;
        }
        else if(!GlobalClass.logStatus){
            System.out.println("Please log in first");
            return false;
        }
        else if(!GlobalClass.personLogged.getIdentify().equals("Merchant")){
            System.out.println("Permission denied");
            return false;
        }
        else{
            Merchant m=GlobalClass.MerchantMap.get(GlobalClass.personLogged.getKakafeeCode());
            if(m.getShopAmount()>=GlobalClass.maxShopAmount){
                System.out.println("Shop count reached limit");
                return false;
            }
            else if(!Operator.shopNameJudge(arc[1])){
                System.out.println("Illegal shop name");
                return false;
            }
            else {
                for(Map.Entry<String,Shop> p:m.getShop().entrySet()){
                    if(p.getValue().getShopName().equals(arc[1])){
                        System.out.println("Shop name already exists");
                        return false;
                    }
                }
                Shop s=m.registerShop(arc[1]);
                System.out.println("Register shop success (shopId: "+s.getShopId()+")");
                return true;
            }
        }
    }
}
