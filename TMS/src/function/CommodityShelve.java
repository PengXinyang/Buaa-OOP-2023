package function;

import commodity.*;
import people.Merchant;
import shop.Shop;
import variable.GlobalClass;

import java.util.Map;

public class CommodityShelve {
    public static boolean commodityShelve(String[] arc){
        if(!(arc.length==4||arc.length==5)){
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
        else if(!Operator.shopIdJudge(arc[1])){
            System.out.println("Illegal shop id");
            return false;
        }
        else{
            Merchant m=GlobalClass.MerchantMap.get(GlobalClass.personLogged.getKakafeeCode());
            int flag=0;
            for(Map.Entry<String,Shop> s:m.getShop().entrySet()){
                if(s.getValue().getShopId().equals(arc[1])&&s.getValue().isStatus()){flag=1;break;}
            }
            if(flag==0){
                System.out.println("Shop id not exists");
                return false;
            }
            else if(arc.length==5){//上架新商品
                return newCommodityShelve(m,arc);
            }
            else return oldCommodityShelve(m,arc);
        }
    }
    public static boolean newCommodityShelve(Merchant m,String[]arc){//上架新商品，5个字符串
        if(!Operator.commodityNameJudge(arc[2])){
            System.out.println("Illegal commodity name");
            return false;
        }
        else if(!Operator.priceJudge(arc[3])){
            System.out.println("Illegal commodity price");
            return false;
        }
        else if(!Operator.commodityNumberJudge(arc[4])){
            System.out.println("Illegal commodity quantity");
            return false;
        }
        else{
            double price=Double.parseDouble(arc[3]);
            int count=Integer.parseInt(arc[4]);
            Commodity c=m.shelveCommodity(arc[1],arc[2],price,count,m);
            m.setCommodityUnits(c.getCommodityUnit());//将新添加的商品加入商家的数组中
            m.getShop().get(arc[1]).setCommodities(c);//将新添加的商品加入对应店铺的数组中
            System.out.println("Put commodity success (commodityId: "+c.getCommodityUnit().getId()+")");
            return true;
        }
    }
    public static boolean oldCommodityShelve(Merchant m,String[] arc){//上架已有商品，4个字符串
        if(!Operator.commodityIdJudge(arc[2])){
            System.out.println("Illegal commodity id");
            return false;
        }
        else{
            if(!GlobalClass.CommodityUnits.containsKey(arc[2])){
                System.out.println("Commodity id not exists");
                return false;
            }
            else{
                CommodityUnit c=GlobalClass.CommodityUnits.get(arc[2]);
                Shop s=GlobalClass.Shop.get(arc[1]);
                int flag=0;
                for(Map.Entry<String,CommodityUnit> c0: m.getCommodityUnits().entrySet()){
                    if(c0.getValue().getId().equals(arc[2])){flag=1;break;}
                }
                if(flag==0){
                    System.out.println("Commodity id not exists");
                    return false;
                }
                else if(!c.isStatus()){
                    System.out.println("Commodity id not exists");
                    return false;
                }
                else if(!Operator.commodityNumberJudge(arc[3])){
                    System.out.println("Illegal commodity quantity");
                    return false;
                }
                else{
                    int count=Integer.parseInt(arc[3]);
                    s.shelveOldCommodity(arc[1],arc[2],count);
                    System.out.println("Put commodity success (commodityId: "+arc[2]+")");
                    return true;
                }
            }
        }
    }
}
