package shop;

import commodity.*;
import order.Order;
import variable.GlobalClass;
import people.*;

import java.io.Serializable;
import java.util.*;

public class Shop implements Serializable {
    private String shopName;
    private String shopId;
    private boolean status;
    private int numberId;
    private ArrayList<Commodity>commodities;//店铺拥有商品，这个商品需要计数。
    private Merchant merchant;//每个店铺有自己的店主
    private Map<String, Order>orderMap=new LinkedHashMap<>();
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopName() {
        return shopName;
    }
    public Shop(String shopName,Merchant merchant){
        this.shopName=shopName;
        commodities=new ArrayList<>();
        this.merchant=merchant;
    }
    public void setShopId(){
        GlobalClass.currentShopId++;
        shopId="S-"+GlobalClass.currentShopId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setCommodities(Commodity commodity) {
        this.commodities.add(commodity);
    }

    public ArrayList<Commodity> getCommodities() {
        return commodities;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setNumberId(int numberId) {
        this.numberId = numberId;
    }

    public int getNumberId() {
        return numberId;
    }

    public Map<String, Order> getOrderMap() {
        return orderMap;
    }

    //上架已有的商品
    public Commodity shelveOldCommodity(String shopId,String commodityId,int count){
        for(Commodity c:this.commodities){
            if(c.getCommodityUnit().getId().equals(commodityId)){
                c.setCount(c.getCount()+count);c.setStatus(true);
                return c;
            }
        }
        Commodity c=new Commodity(GlobalClass.CommodityUnits.get(commodityId),count,shopId,true);
        setCommodities(c);
        return c;
    }

    //给商店货物按照商品编号排序
    public void commoditiesSort(){
        Collections.sort(commodities);
    }
    //打印本商店拥有的商品
    public void viewCommodities(){
        commoditiesSort();
        for(Commodity c:commodities){
            if(c.isStatus()) {
                c.printMessage();
            }
        }
    }
    //查找本商店是否拥有某个名字的商品
    public boolean searchCommodity(String commodityName){
        commoditiesSort();
        boolean flag=false;
        if(commodities.isEmpty())return flag;
        for(Commodity c:commodities){
            if(c.getCommodityUnit().getCommodityName().equals(commodityName)&&c.isStatus()&&c.getCount()!=0){
                flag=true;
                c.printMessage();
            }
        }
        return flag;
    }
    //清空库存
    public void clearInventory(String commodityId){
        for(Commodity c:commodities){
            if(c.getCommodityUnit().getId().equals(commodityId)){
                c.setCount(0);c.setStatus(false);
                break;
            }
        }
    }
}
