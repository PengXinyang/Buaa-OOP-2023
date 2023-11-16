package function;

import favorite.Favorite;
import file.OverridePath;
import people.Customer;
import variable.GlobalClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadFavoriteCommodity {
    public static boolean readFavoriteCommodity(String[]arc) throws IOException {
        if(arc.length!=2){
            System.out.println("Illegal argument count");
            return false;
        }
        else if(!GlobalClass.logStatus){
            System.out.println("Please log in first");
            return false;
        }
        else if(!GlobalClass.personLogged.getIdentify().equals("Customer")){
            System.out.println("Permission denied");
            return false;
        }
        Customer customer=GlobalClass.CustomerMap.get(GlobalClass.personLogged.getKakafeeCode());
        arc[1]= OverridePath.overridePath(arc[1]);
        File file=new File(arc[1]);
        if(!file.exists()||file.isDirectory()){
            System.out.println("File not exists");
            return false;
        }
        else{
            try{
                FileInputStream fis=new FileInputStream(file);
                ObjectInputStream ois=new ObjectInputStream(fis);
                while(fis.available()>0){
                    Favorite f0=(Favorite) ois.readObject();
                    f0.setCommodity(GlobalClass.favorites.get(f0.getId()).getCommodity());
                    f0.setCustomer(customer);
                    int flag=0;
                    for(Favorite f: customer.getFavorites()){
                        if(f.getCommodity().getShopId().equals(f0.getCommodity().getShopId())&&f.getCommodity().getCommodityUnit().getId().equals(f0.getCommodity().getCommodityUnit().getId())){
                            f.setNumber(f.getNumber()+f0.getNumber());
                            flag=1;
                            break;
                        }
                    }
                    if(flag==0) customer.getFavorites().add(f0);
                }
                ois.close();
                fis.close();
                System.out.println("Read favorite commodity success");
                return true;
            }catch(Exception e) {
                System.out.println("File operation failed");
                return false;
            }
        }
    }
}
