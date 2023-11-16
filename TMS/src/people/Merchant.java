package people;

import commodity.Commodity;
import commodity.CommodityUnit;
import order.Order;
import shop.*;
import variable.GlobalClass;

import java.io.Serializable;
import java.util.*;

public class Merchant extends Person implements Serializable {
    private int shopAmount;
    private Map<String,Shop> shop;//根据商店的id和商店实例组成map
    private Map<String,CommodityUnit>commodityUnits;//商家拥有商品，这个商品只需要记录是否存在于商家处
    private Map<String, Order>orders=new LinkedHashMap<>();//商家拥有的订单
    public Merchant(){
        super();
        shopAmount=0;
        shop=new HashMap<>();
        commodityUnits=new HashMap<>();
        orders=new LinkedHashMap<>();
    }
    public Merchant(String kakafeeCode, String name, String password){
        super(kakafeeCode, name, password, "Merchant", true);
        shopAmount=0;
        shop=new HashMap<>();
        commodityUnits=new HashMap<>();
        orders=new LinkedHashMap<>();
    }
    public int getShopAmount() {
        return shopAmount;
    }
    public void setShopAmount(int shopAmount) {
        this.shopAmount = shopAmount;
    }

    public Map<String,Shop> getShop() {
        return shop;
    }
    public void setShop(String id,Shop shop) {
        this.shop.put(id,shop);
    }
    public Map<String,CommodityUnit> getCommodityUnits() {
        return commodityUnits;
    }
    public void setCommodityUnits(CommodityUnit c){
        commodityUnits.put(c.getId(),c);
    }

    public Map<String, Order> getOrders() {
        return orders;
    }

    public void setOrders(Order order) {
        orders.put(order.getOrderId(),order);
    }

    //店铺集合排序
    public List<Map.Entry<String, Shop>> shopSort(){
        List<Map.Entry<String, Shop>> list2 = new ArrayList<>(shop.entrySet());
        Collections.sort(list2, Comparator.comparingInt(o -> o.getValue().getNumberId()));
        return list2;
    }
    //商品排序
    public List<Map.Entry<String, CommodityUnit>> commodityUnitsSort(){
        List<Map.Entry<String, CommodityUnit>> list2 = new ArrayList<>(commodityUnits.entrySet());
        Collections.sort(list2, Comparator.comparingInt(o -> o.getValue().getNumberId()));
        return list2;
    }
    //注册店铺
    public Shop registerShop(String shopName){
        shopAmount++;
        GlobalClass.shopAmount++;
        Shop s=new Shop(shopName,this);
        s.setShopId();
        s.setNumberId(GlobalClass.shopAmount);
        s.setStatus(true);
        this.setShop(s.getShopId(),s);
        GlobalClass.Shop.put(s.getShopId(),s);
        return s;
    }
    //上架新商品
    public Commodity shelveCommodity(String shopId,String commodityName,double price,int count,Merchant merchant){
        CommodityUnit cu=new CommodityUnit(commodityName,price,true,merchant);
        cu.setId();
        GlobalClass.CommodityUnits.put(cu.getId(),cu);
        GlobalClass.commodityUnitsAmount++;
        //setCommodityUnits(cu);
        Commodity c=new Commodity(cu,count,shopId,true);
        return c;
    }
    //查看自己的店铺
    public void listShops(){//查看商店
        List<Map.Entry<String, Shop>> l1=shopSort();;
        for(Map.Entry<String, Shop> s: l1){
            System.out.println(s.getKey()+" "+s.getValue().getShopName());
        }
    }
    //查看自己店铺的所有商品
    public void listCommodity(){
        List<Map.Entry<String, Shop>>l1 =shopSort();
        for(Map.Entry<String, Shop> s: l1){
            if(s.getValue().isStatus())s.getValue().viewCommodities();
        }
    }
    //查看自己店铺已知名称的商品
    public boolean searchCommodity(String commodityName){
        boolean flag1,flag2=false;
        List<Map.Entry<String, Shop>>l1 =shopSort();
        for(Map.Entry<String,Shop>s:l1){
            if(s.getValue().isStatus()){
                flag1=s.getValue().searchCommodity(commodityName);
                if(flag1) flag2=true;
            }
        }
        if(!flag2){
            System.out.println("Commodity not exists");
        }
        return flag2;
    }
    //下架自己店铺的商品
    public void commodityUnshelve(String commodityId){
        CommodityUnit cu=commodityUnits.get(commodityId);
        cu.setStatus(false);
        for(Map.Entry<String,Shop>s:shop.entrySet()){
            s.getValue().clearInventory(commodityId);
        }
        commodityUnits.remove(commodityId,cu);
        GlobalClass.commodityUnitsAmount--;
    }
    //注销自己店铺
    public void cancelShop(String shopId){
        Shop s=shop.get(shopId);
        s.setStatus(false);
        shopAmount--;
        GlobalClass.shopAmount--;
        for(Commodity c:s.getCommodities()){//清空这个商店的所有库存
            c.setCount(0);c.setStatus(false);
        }
        shop.remove(shopId);
    }
}
