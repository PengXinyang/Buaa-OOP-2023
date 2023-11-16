package function;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operator {
    public static boolean nameJudge(String name){//判断名字是否符合格式
        String name_pattern = "[A-Za-z][A-Za-z_]{3,15}";
        return name.matches(name_pattern);
    }
    public static boolean kakafeeIdJudge(String ID){//判断卡号
        if (ID.length()!=12) {
            return false;
        }
        else{
            char []iD=ID.toCharArray();
            int id1=(iD[0]-'0')*1000+(iD[1]-'0')*100+(iD[2]-'0')*10+(iD[3]-'0');
            int id2=(iD[4]-'0')*1000+(iD[5]-'0')*100+(iD[6]-'0')*10+(iD[7]-'0');
            int id3=(iD[8]-'0')*1000+(iD[9]-'0')*100+(iD[10]-'0')*10+(iD[11]-'0');
            return (id1 >= 1 && id1 <= 4500) && (id2 >= 1785 && id2 <= 1886) && (id3 >= 1000 && id3 <= 3000);
        }
    }
    public static boolean passwordJudge(String password){//判断密码
        if(!(password.length()<=16&&password.length()>=8)) return false;
        String password_pattern="^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[@_%$])[a-zA-Z0-9@_%$]{8,16}$";
        return password.matches(password_pattern);
    }
    public static boolean passwordConfirm(String passwordNow,String passwordOriginal){//确认密码
        return passwordOriginal.equals(passwordNow);
    }
    public static boolean identify(String identifyName){
        return identifyName.equals("Administrator")||identifyName.equals("Merchant")||identifyName.equals("Customer");
    }
    public static boolean shopNameJudge(String shopName){//判断店铺名称是否合法
        if(shopName.isEmpty() ||shopName.length()>50)return false;
        else{
            String shopName_pattern = "^[a-zA-Z][a-zA-Z-_]{0,49}$";
            return shopName.matches(shopName_pattern);
        }
    }
    public static boolean shopIdJudge(String id){//判断店铺编号是否合法
        String shopId="^S-[1-9]\\d*$";
        return id.matches(shopId);
    }
    public static boolean commodityNameJudge(String commodityName){//判断商品名称是否合法
        if(commodityName.isEmpty() ||commodityName.length()>50)return false;
        else{
            String commodityName_pattern = "^[a-zA-Z][a-zA-Z-_]{0,49}$";
            return commodityName.matches(commodityName_pattern);
        }
    }
    public static boolean priceJudge(String price){//判断商品单价是否符合格式
        char[]arr=price.toCharArray();
        if((arr.length==4||arr.length==3)&&arr[0]=='0'&&arr[1]=='.'&&!price.equals("0.0")&&!price.equals("0.00"))return true;
        String price_pattern="^(?!0{2,})[1-9]\\d{0,7}(\\.\\d{1,2})?$|99999999.99$";
        return price.matches(price_pattern);
    }
    public static boolean commodityNumberJudge(String number){//判断商品数量是否合法
        String number_pattern="^[1-9][0-9]*$";
        return number.matches(number_pattern);
    }
    public static boolean commodityIdJudge(String id){//判断商品编号是否合法
        String commodityId="^C-[1-9]\\d*$";
        return id.matches(commodityId);
    }
    public static boolean orderIdJudge(String id){//判断订单编号是否合法
        String commodityId="^O-[1-9]\\d*$";
        return id.matches(commodityId);
    }
    public static boolean pathJudge(String path) {
        // 定义正则表达式模式
        String regex = "^[^*?\"<>|]+$";

        // 使用正则表达式进行匹配
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(path);

        return matcher.matches();
    }
}
