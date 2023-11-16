package people;

import commodity.Commodity;
import commodity.CommodityUnit;
import shop.Shop;
import variable.GlobalClass;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Administrator extends Person implements Serializable {
    public Administrator(){
        super();
    }
    public Administrator(String kakafeeCode, String name, String password){
        super(kakafeeCode, name, password, "Administrator", true);
    }
    public static void listShops(String kakafeCode){//查看指定商店
        Merchant m=GlobalClass.MerchantMap.get(kakafeCode);
        List<Map.Entry<String,Shop>> s1=m.shopSort();
        for(Map.Entry<String,Shop>s:s1){//对于单个商家，生成新店铺时输入的编号是有序的，输入进map时已经有序，不用再次排序。
            System.out.println(kakafeCode+" "+s.getValue().getShopId()+" "+s.getValue().getShopName());
        }
    }
    public static void listShops(){//查看商店
        List<Map.Entry<String,Shop>> s1=GlobalClass.setShopSort();
        for(Map.Entry<String, Shop> s: s1){//输入时应能保证全局商店按照id顺序输入，所以不用排序
            if(s.getValue().isStatus())System.out.println(s.getValue().getMerchant().getKakafeeCode()+" "+s.getKey()+" "+s.getValue().getShopName());
        }
    }
    //下架商品
    public void commodityUnshelve(String commodityId){
        CommodityUnit cu=GlobalClass.CommodityUnits.get(commodityId);
        cu.setStatus(false);
        Merchant m=cu.getMerchant();
        for(Map.Entry<String,Shop>s:m.getShop().entrySet()){
            s.getValue().clearInventory(commodityId);
        }
        m.getCommodityUnits().remove(commodityId,cu);
        GlobalClass.commodityUnitsAmount--;
    }
    //注销店铺
    public void cancelShop(String shopId){
        Shop s=GlobalClass.Shop.get(shopId);
        s.setStatus(false);
        Merchant m=s.getMerchant();
        m.setShopAmount(m.getShopAmount()-1);
        GlobalClass.shopAmount--;
        for(Commodity c:s.getCommodities()){//清空这个商店的所有库存
            c.setCount(0);c.setStatus(false);
        }
        m.getShop().remove(shopId);
    }
}
