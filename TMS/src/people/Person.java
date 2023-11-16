package people;

import commodity.CommodityUnit;
import shop.Shop;
import variable.GlobalClass;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Person implements Serializable {
    private String KakafeeCode;
    private String name;
    private String password;
    private String identify;
    private boolean registerStatus;
    public Person(){}
    public Person(String kakafeeCode, String name, String password, String identify, boolean registerStatus) {
        KakafeeCode = kakafeeCode;
        this.name = name;
        this.password = password;
        this.identify = identify;
        this.registerStatus = registerStatus;
    }

    public void setKakafeeCode(String KakafeecCode){
        this.KakafeeCode=KakafeecCode;
    }
    public String getKakafeeCode(){
        return KakafeeCode;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return password;
    }
    public void setIdentify(String identify){
        this.identify=identify;
    }
    public  String getIdentify(){
        return identify;
    }
    public void setRegisterStatus(boolean registerStatus){this.registerStatus=registerStatus;}
    public boolean getRegisterStatus(){return registerStatus;}
    public String toString(){
        return "Name: "+name+"\nKakafee number: "+KakafeeCode+"\nType: "+identify;
    }
    //打印商品信息，由于管理员和顾客打印出的信息相同
    public void listCommodity(){
        List<Map.Entry<String, Shop>> s1;
        int flag=0;
        s1=GlobalClass.setShopSort();
        for(Map.Entry<String, Shop>s: s1){
            if(s.getValue().isStatus()&&!s.getValue().getCommodities().isEmpty()){
                flag=1;
                s.getValue().viewCommodities();
            }
        }
        if(flag==0)System.out.println("Commodity not exists");
    }
    //查看某个商店的商品信息
    public boolean listShopCommodity(Shop s){
        if(s.getCommodities().isEmpty()){
            System.out.println("Commodity not exists");
            return false;
        }
        else {
            s.viewCommodities();
            return true;
        }
    }
    //查找所有店铺中含有某个名字的商品
    public boolean searchCommodity(String commodityName){
        List<Map.Entry<String, Shop>> s1=GlobalClass.setShopSort();
        boolean flag1,flag2=false;
        for(Map.Entry<String,Shop>s:s1){
            flag1=s.getValue().searchCommodity(commodityName);
            if(flag1) flag2=true;
        }
        if(!flag2){
            System.out.println("Commodity not exists");
        }
        return flag2;
    }
}