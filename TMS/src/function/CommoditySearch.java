package function;

import variable.GlobalClass;

public class CommoditySearch {
    public static boolean searchCommodity(String[] arc){
        if(arc.length!=2){
            System.out.println("Illegal argument count");
            return false;
        }
        else if(!GlobalClass.logStatus){
            System.out.println("Please log in first");
            return false;
        }
        else if(!Operator.commodityNameJudge(arc[1])){
            System.out.println("Illegal commodity name");
            return false;
        }
        else if(GlobalClass.commodityUnitsAmount==0){
            System.out.println("Commodity not exists");
            return false;
        }
        else{
            if(GlobalClass.personLogged.getIdentify().equals("Merchant")){
                return GlobalClass.MerchantMap.get(GlobalClass.personLogged.getKakafeeCode()).searchCommodity(arc[1]);
            }
            else return GlobalClass.personLogged.searchCommodity(arc[1]);
        }
    }
}
