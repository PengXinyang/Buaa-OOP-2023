package function;

import people.*;
import variable.GlobalClass;

public class ShopView {
    public static boolean shopView(String []arc){
        if(arc.length>=3){
            System.out.println("Illegal argument count");
            return false;
        }
        else if(!GlobalClass.logStatus){
            System.out.println("Please log in first");
            return false;
        }
        else{
            if(arc.length==1){
                if(GlobalClass.shopAmount==0){
                    System.out.println("Shop not exists");
                    return false;
                }
                switch (GlobalClass.personLogged.getIdentify()) {
                    case "Merchant" -> {
                        Merchant m = GlobalClass.MerchantMap.get(GlobalClass.personLogged.getKakafeeCode());
                        if (m.getShopAmount() == 0) {
                            System.out.println("Shop not exists");
                            return false;
                        }
                        m.listShops();
                        return true;
                    }
                    case "Customer" -> {
                        Customer.listShops();
                        return true;
                    }
                    case "Administrator" -> {
                        Administrator.listShops();
                        return true;
                    }
                }
                return false;
            }
            else{
                if(!GlobalClass.personLogged.getIdentify().equals("Administrator")){
                    System.out.println("Permission denied");
                    return false;
                }
                else if(!Operator.kakafeeIdJudge(arc[1])){
                    System.out.println("Illegal Kakafee number");
                    return false;
                }
                else if(!GlobalClass.map.containsKey(arc[1])){
                    System.out.println("Kakafee number not exists");
                    return false;
                }
                else if(!GlobalClass.map.get(arc[1]).getIdentify().equals("Merchant")){
                    System.out.println("Kakafee number does not belong to a Merchant");
                    return false;
                }
                else{
                    if(GlobalClass.shopAmount==0){
                        System.out.println("Shop not exists");
                        return false;
                    }
                    Merchant m=GlobalClass.MerchantMap.get(arc[1]);
                    if(m.getShopAmount()==0){
                        System.out.println("Shop not exists");
                        return false;
                    }
                    else{
                        Administrator.listShops(arc[1]);
                        return true;
                    }
                }
            }
        }
    }
}
