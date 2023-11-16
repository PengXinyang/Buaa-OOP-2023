package function;

import commodity.Commodity;
import people.Merchant;
import shop.Shop;
import variable.GlobalClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class PutCommodityBatch {
    public static boolean putCommodityBatch(String []arc) throws FileNotFoundException {
        if(arc.length!=2){
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
        else {
            Merchant m = GlobalClass.MerchantMap.get(GlobalClass.personLogged.getKakafeeCode());
            int flag = 0;
            for (Map.Entry<String, Shop> s : m.getShop().entrySet()) {
                if (s.getValue().getShopId().equals(arc[1]) && s.getValue().isStatus()) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                System.out.println("Shop id not exists");
                return false;
            }
            else{
                Shop s=m.getShop().get(arc[1]);
                FileInputStream fis=new FileInputStream("./commodity.txt");
                System.setIn(fis);
                Scanner in=new Scanner(System.in);
                while(in.hasNextLine()){
                    String arss=in.nextLine();
                    String []ars=arss.split("\\s+");
                    double price=Double.parseDouble(ars[1]);
                    int count=Integer.parseInt(ars[2]);
                    Commodity c=m.shelveCommodity(arc[1],ars[0],price,count,m);
                    m.setCommodityUnits(c.getCommodityUnit());//将新添加的商品加入商家的数组中
                    s.setCommodities(c);//将新添加的商品加入对应店铺的数组中
                }
                System.out.println("Put commodity batch success");
                in.close();
                return true;
            }
        }
    }
}
