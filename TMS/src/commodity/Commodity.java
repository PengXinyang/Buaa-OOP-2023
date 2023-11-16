package commodity;

import java.io.Serializable;

public class Commodity implements Comparable<Commodity>, Serializable {
    private CommodityUnit commodityUnit;
    private int count;
    private String shopId;
    private boolean status;//代表物品在所在的商店是否被下架
    public Commodity() {
        commodityUnit=new CommodityUnit();
        count=0;
        shopId= "";
        status=true;
    }

    public Commodity(CommodityUnit commodityUnit, int count,String shopId,boolean status) {
        this.commodityUnit = commodityUnit;
        this.count = count;
        this.shopId=shopId;
        this.status=status;
    }

    public int getCount() {
        return count;
    }

    public CommodityUnit getCommodityUnit() {
        return commodityUnit;
    }

    public void setCommodityUnit(CommodityUnit commodityUnit) {
        this.commodityUnit = commodityUnit;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    //打印商品自己的信息
    public void printMessage(){
        System.out.print(shopId+": "+commodityUnit.getId()+" "+commodityUnit.getCommodityName()+" "+String.format("%.2f",commodityUnit.getPrice())+"yuan "+count+"\n");
    }
    @Override
    public int compareTo(Commodity o){
        return commodityUnit.getNumberId()-o.commodityUnit.getNumberId();
    }
}
