package function;

import commodity.Commodity;
import order.Order;
import shop.Shop;
import variable.GlobalClass;
import people.*;

import java.util.Map;

public class RemoveCommodity {
    public static boolean removeCommodity(String[]arc){
        if(!(arc.length==2||arc.length==3)){
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
        else if(!Operator.commodityIdJudge(arc[1])){
            System.out.println("Illegal commodity id");
            return false;
        }
        if(arc.length==2){
            if(!GlobalClass.CommodityUnits.containsKey(arc[1])||!GlobalClass.CommodityUnits.get(arc[1]).isStatus()){//判断商品编号是否存在
                System.out.println("Commodity id not exists");
                return false;
            }
            if(GlobalClass.personLogged.getIdentify().equals("Merchant")){//如果是商家登录，判断是否属于商家
                Merchant m=GlobalClass.MerchantMap.get(GlobalClass.personLogged.getKakafeeCode());
                if(!m.getCommodityUnits().containsKey(arc[1])){//商品不属于该商家
                    System.out.println("Commodity id not exists");
                    return false;
                }
            }
            //判断是否还有未处理的订单
            for(Map.Entry<String, Order>o:GlobalClass.orderMap.entrySet()){
                if(o.getValue().getCommodity().getCommodityUnit().getId().equals(arc[1])&&o.getValue().getStatus().equals("pending")){
                    System.out.println("Please process order for commodity");
                    return false;
                }
            }
            if(GlobalClass.personLogged.getIdentify().equals("Administrator")){
                Administrator a=GlobalClass.AdministratorMap.get(GlobalClass.personLogged.getKakafeeCode());
                a.commodityUnshelve(arc[1]);
                System.out.println("Remove commodity success");
                return true;
            }
            else{
                Merchant m=GlobalClass.MerchantMap.get(GlobalClass.personLogged.getKakafeeCode());
                m.commodityUnshelve(arc[1]);
                System.out.println("Remove commodity success");
                return true;
            }
        }
        else{//下架商品
            if(!Operator.shopIdJudge(arc[2])){//先判断商店状态
                System.out.println("Illegal shop id");
                return false;
            }
            else if(!GlobalClass.Shop.containsKey(arc[2])||!GlobalClass.Shop.get(arc[2]).isStatus()){
                System.out.println("Shop id not exists");
                return false;
            }
            if(GlobalClass.personLogged.getIdentify().equals("Merchant")){//如果是商家登录，判断商店是否属于商家
                Merchant m=GlobalClass.MerchantMap.get(GlobalClass.personLogged.getKakafeeCode());
                if(!m.getShop().containsKey(arc[2])){
                    System.out.println("Shop id not exists");
                    return false;
                }
            }
            Shop s=GlobalClass.Shop.get(arc[2]);//判断商品是否属于该店铺
            int flag=0;
            for(Commodity c:s.getCommodities()){
                if(c.getCommodityUnit().getId().equals(arc[1])&&c.isStatus()){flag=1;break;}
            }
            if(flag==0){
                System.out.println("Commodity id not exists");
                return false;
            }
            if(!GlobalClass.CommodityUnits.containsKey(arc[1])||!GlobalClass.CommodityUnits.get(arc[1]).isStatus()){//判断商品编号是否存在
                System.out.println("Commodity id not exists");
                return false;
            }
            if(GlobalClass.personLogged.getIdentify().equals("Merchant")){//如果是商家登录，判断是否属于商家
                Merchant m=GlobalClass.MerchantMap.get(GlobalClass.personLogged.getKakafeeCode());
                if(!m.getCommodityUnits().containsKey(arc[1])){//商品不属于该商家
                    System.out.println("Commodity id not exists");
                    return false;
                }
            }
            //判断是否还有未处理的订单
            for(Map.Entry<String, Order>o:GlobalClass.orderMap.entrySet()){
                if(o.getValue().getCommodity().getCommodityUnit().getId().equals(arc[1])&&o.getValue().getCommodity().getShopId().equals(arc[2])&&o.getValue().getStatus().equals("pending")){
                    System.out.println("Please process order for commodity");
                    return false;
                }
            }
            s.clearInventory(arc[1]);
            System.out.println("Remove commodity success");
            return true;
        }
    }
}
