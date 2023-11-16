package function;

import favorite.Favorite;
import file.OverridePath;
import people.Customer;
import variable.GlobalClass;

import java.io.*;

public class UploadFavoriteCommodity {
    public static boolean uploadFavoriteCommodity(String[] arc) throws IOException {
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
        if(customer.getFavorites().isEmpty()){
            System.out.println("Favorite not exists");
            return false;
        }
        else{
            arc[1]=OverridePath.overridePath(arc[1]);
            try{
                File file=new File(arc[1]);
                if(!file.getParentFile().exists())file.getParentFile().mkdirs();
                if(!file.exists())file.createNewFile();
                FileOutputStream fos=new FileOutputStream(file);
                ObjectOutputStream oos=new ObjectOutputStream(fos);
                for(Favorite f: customer.getFavorites()){
                    oos.writeObject(f);
                }
                oos.flush();
                oos.close();
                fos.close();
                System.out.println("Upload favorite commodity success");
                return true;
            }catch(Exception e){
                System.out.print("File operation failed");
                e.printStackTrace();;
                return false;
            }
        }
    }
}
