package order;

import commodity.Commodity;
import commodity.CommodityUnit;
import people.Customer;
import people.Merchant;
import shop.Shop;
import variable.GlobalClass;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class Order implements Serializable {
    private int id;
    private String orderId;
    private String status;
    private int number;//购买商品的数量
    private Customer customer;
    private Shop shop;
    private Merchant merchant;//保存商家信息
    private Commodity commodity;//保存商品信息
    public Order(Commodity commodity,int number,Shop shop,Customer customer,String status){
        setOrderId();
        this.commodity=commodity;
        this.number=number;
        this.shop=shop;
        this.customer=customer;
        this.status=status;
        this.merchant=shop.getMerchant();
    }
    public void setId() {
        GlobalClass.orderAmount++;
        this.id = GlobalClass.orderAmount;
    }

    public int getId() {
        return id;
    }

    public void setOrderId() {
        setId();
        this.orderId = "O-"+id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Shop getShop() {
        return shop;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }
    //打印订单信息
    public void printMessage(){
        CommodityUnit cu=commodity.getCommodityUnit();
        System.out.print(orderId+": "+commodity.getShopId()+" "+cu.getId()+" "+number+" "+String.format("%.2f",number*cu.getPrice())+"yuan "+status+"\n");
    }
    public void writeMessage(FileWriter f) throws IOException {
        CommodityUnit cu=commodity.getCommodityUnit();
        f.write(orderId+": "+commodity.getShopId()+" "+cu.getId()+" "+number+" "+String.format("%.2f",number*cu.getPrice())+"yuan "+status+"\n");
        f.flush();
    }
}
