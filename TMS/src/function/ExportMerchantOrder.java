package function;

import file.OverridePath;
import order.Order;
import people.Merchant;
import variable.GlobalClass;
import file.MultiOutputStream;

import java.io.*;
import java.util.Map;

public class ExportMerchantOrder {
    public static boolean exportMerchantOrder(String []arc) throws IOException {
        if(arc.length==1){
            if(!GlobalClass.logStatus){
                System.out.println("Please log in first");
                return false;
            }
            else if(!GlobalClass.personLogged.getIdentify().equals("Merchant")){
                System.out.println("Permission denied");
                return false;
            }
            Merchant m=GlobalClass.MerchantMap.get(GlobalClass.personLogged.getKakafeeCode());
            if(m.getOrders().isEmpty()){
                System.out.println("Order not exists");
                return false;
            }
            else{
                String str="./data/order/"+m.getKakafeeCode()+".txt";
                File s=new File(str);
                if(!s.getParentFile().exists())s.getParentFile().mkdirs();
                if(!s.exists())s.createNewFile();
                try (FileWriter sWriter = new FileWriter(s)) {
                    for (Map.Entry<String, Order> o : m.getOrders().entrySet()) {
                        o.getValue().writeMessage(sWriter);
                    }
                    for (Map.Entry<String, Order> o : m.getOrders().entrySet()) {
                        o.getValue().printMessage();
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("File operation failed");
                    return false;
                }
                return true;
            }
        }
        else if(arc.length==2){
            if(arc[1].equals(">")||arc[1].equals(">>")){
                System.out.println("Please input the redirect path");
                return false;
            }
            else if(!GlobalClass.logStatus){
                System.out.println("Please log in first");
                return false;
            }
            else if(!GlobalClass.personLogged.getIdentify().equals("Administrator")){
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
                Merchant m=GlobalClass.MerchantMap.get(arc[1]);
                if(m.getOrders().isEmpty()){
                    System.out.println("Order not exists");
                    return false;
                }
                else{
                    String str="./data/order/"+arc[1]+".txt";
                    File s=new File(str);
                    if(!s.getParentFile().exists())s.getParentFile().mkdirs();
                    try (FileWriter sWriter = new FileWriter(s)) {
                        for (Map.Entry<String, Order> o : m.getOrders().entrySet()) {
                            o.getValue().writeMessage(sWriter);
                        }
                        for (Map.Entry<String, Order> o : m.getOrders().entrySet()) {
                            o.getValue().printMessage();
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("File operation failed");
                        return false;
                    }
                    return true;
                }
            }
        }
        else{
            if(!arc[1].equals(">")&&!arc[1].equals(">>")){
                System.out.println("Illegal argument count");
                return false;
            }
            else if(!Operator.pathJudge(arc[2])){
                System.out.println("Illegal redirect path");
                return false;
            }
            else{
                arc[2]= OverridePath.overridePath(arc[2]);
                if(arc.length>4){
                    FileWriter proFile2=fileWriter(arc);
                    proFile2.write("Illegal argument count\n");
                    proFile2.close();
                    return false;
                }
                else if(!GlobalClass.logStatus){
                    FileWriter proFile2=fileWriter(arc);
                    proFile2.write("Please log in first\n");
                    proFile2.close();
                    return false;
                }
                else if(GlobalClass.personLogged.getIdentify().equals("Customer")){
                    FileWriter proFile2=fileWriter(arc);
                    proFile2.write("Permission denied\n");
                    proFile2.close();
                    return false;
                }
                else if(GlobalClass.personLogged.getIdentify().equals("Merchant")){
                    if(arc.length==4){
                        FileWriter proFile2=fileWriter(arc);
                        proFile2.write("Permission denied\n");
                        proFile2.close();
                        return false;
                    }
                    Merchant m=GlobalClass.MerchantMap.get(GlobalClass.personLogged.getKakafeeCode());
                    if(arc[2].equals("./data/order/"+m.getKakafeeCode()+".txt")){
                        System.out.println("The save path is the same as the redirect path");
                        return false;
                    }
                    else{
                        if(m.getOrders().isEmpty()){
                            FileWriter proFile2=fileWriter(arc);
                            proFile2.write("Order not exists\n");
                            proFile2.close();
                            return false;
                        }
                        else{
                            FileWriter proFile2=fileWriter(arc);
                            String str="./data/order/"+m.getKakafeeCode()+".txt";
                            File s=new File(str);
                            if(!s.getParentFile().exists())s.getParentFile().mkdirs();
                            try(FileWriter proFile=new FileWriter(s);){
                                for(Map.Entry<String, Order>o:m.getOrders().entrySet()){
                                    o.getValue().writeMessage(proFile2);
                                }
                                for(Map.Entry<String, Order>o:m.getOrders().entrySet()){
                                    o.getValue().writeMessage(proFile);
                                }
                            } catch (FileNotFoundException e) {
                                System.out.println("File operation failed");
                                return false;
                            }finally{
                                proFile2.close();
                            }
                            return true;
                        }
                    }
                }
                else{
                    if(arc.length==3){
                        FileWriter proFile2=fileWriter(arc);
                        proFile2.write("Permission denied\n");
                        proFile2.close();
                        return false;
                    }
                    if(!Operator.kakafeeIdJudge(arc[3])){
                        FileWriter proFile2=fileWriter(arc);
                        proFile2.write("Illegal Kakafee number\n");
                        proFile2.close();
                        return false;
                    }
                    else if(!GlobalClass.map.containsKey(arc[3])){
                        FileWriter proFile2=fileWriter(arc);
                        proFile2.write("Kakafee number not exists\n");
                        proFile2.close();
                        return false;
                    }
                    else if(!GlobalClass.map.get(arc[3]).getIdentify().equals("Merchant")){
                        FileWriter proFile2=fileWriter(arc);
                        proFile2.write("Kakafee number does not belong to a Merchant\n");
                        proFile2.close();
                        return false;
                    }
                    else if(arc[2].equals("./data/order/"+arc[3]+".txt")){
                        System.out.println("The save path is the same as the redirect path");
                        return false;
                    }
                    Merchant m=GlobalClass.MerchantMap.get(arc[3]);
                    if(m.getOrders().isEmpty()){
                        FileWriter proFile2=fileWriter(arc);
                        proFile2.write("Order not exists\n");
                        proFile2.close();
                        return false;
                    }
                    else{
                        FileWriter proFile2=fileWriter(arc);
                        String str="./data/order/"+m.getKakafeeCode()+".txt";
                        File s=new File(str);
                        if(!s.getParentFile().exists())s.getParentFile().mkdirs();
                        try(FileWriter proFile=new FileWriter(s);){
                            for(Map.Entry<String, Order>o:m.getOrders().entrySet()){
                                o.getValue().writeMessage(proFile2);
                            }
                            for(Map.Entry<String, Order>o:m.getOrders().entrySet()){
                                o.getValue().writeMessage(proFile);
                            }
                        } catch (FileNotFoundException e) {
                            System.out.println("File operation failed");
                            proFile2.close();
                            return false;
                        }finally{
                            proFile2.close();
                        }
                        proFile2.close();
                        return true;
                    }
                }
            }
        }
    }
    public static FileWriter fileWriter(String[] arc) throws IOException {
        File f=new File(arc[2]);if(!f.getParentFile().exists())f.getParentFile().mkdirs();
        FileWriter proFile2;
        if(arc[1].equals(">")){
            proFile2=new FileWriter(f);
        }
        else{
            proFile2=new FileWriter(f,true);
        }
        return proFile2;
    }
}
