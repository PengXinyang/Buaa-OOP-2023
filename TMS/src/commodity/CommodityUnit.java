package commodity;

import people.Merchant;
import variable.GlobalClass;

import java.io.Serializable;

public class CommodityUnit implements Serializable {
    private String commodityName;
    private String id;
    private int numberId;
    private double price;
    private boolean status;
    private Merchant merchant;
    public CommodityUnit(){
        merchant=new Merchant();
    }
    public CommodityUnit(String commodityName, double price, boolean status,Merchant merchant) {
        this.commodityName = commodityName;
        this.price = price;
        this.status = status;
        this.merchant=merchant;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        GlobalClass.currentCommodityId++;
        this.numberId=GlobalClass.currentCommodityId;
        this.id= "C-"+GlobalClass.currentCommodityId;
    }

    public int getNumberId() {
        return numberId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Merchant getMerchant() {
        return merchant;
    }
}
