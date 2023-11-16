package function;

import commodity.Commodity;
import people.Merchant;
import shop.Shop;
import variable.GlobalClass;

import java.util.Map;

public class CommodityView {
    public static boolean commodityView(String[] arc){
        if(arc.length>2){
            System.out.println("Illegal argument count");
            return false;
        }
        else if(!GlobalClass.logStatus){
            System.out.println("Please log in first");
            return false;
        }
        if(arc.length==1){
            if(GlobalClass.commodityUnitsAmount==0){
                System.out.println("Commodity not exists");
                return false;
            }
            if(GlobalClass.personLogged.getIdentify().equals("Merchant")){
                Merchant m=GlobalClass.MerchantMap.get(GlobalClass.personLogged.getKakafeeCode());
                int flag=0;
                for(Map.Entry<String,Shop> s:m.getShop().entrySet()){
                    if(!s.getValue().getCommodities().isEmpty()){
                        for(Commodity c:s.getValue().getCommodities()){
                            if(c.isStatus()){
                                flag=1;break;
                            }
                        }
                    }
                }
                if(flag==0){
                    System.out.println("Commodity not exists");
                    return false;
                }
                else{
                    m.listCommodity();
                    return true;
                }
            }
            else{
                GlobalClass.personLogged.listCommodity();
                return true;
            }
        }
        else{
            if(!Operator.shopIdJudge(arc[1])){
                System.out.println("Illegal shop id");
                return false;
            }
            if((!GlobalClass.Shop.containsKey(arc[1]))||(!GlobalClass.Shop.get(arc[1]).isStatus())){
                System.out.println("Shop id not exists");
                return false;
            }
            Shop s=GlobalClass.Shop.get(arc[1]);
            if(GlobalClass.personLogged.getIdentify().equals("Merchant")){
                Merchant m=GlobalClass.MerchantMap.get(GlobalClass.personLogged.getKakafeeCode());
                if(!s.getMerchant().equals(m)){
                    System.out.println("Shop id not exists");
                    return false;
                }
            }
            return GlobalClass.personLogged.listShopCommodity(s);
        }
    }
}