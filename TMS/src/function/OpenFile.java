package function;

import file.OverridePath;
import variable.GlobalClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class OpenFile {
    public static boolean openFile(String []arc){
        if(arc.length!=2&&arc.length!=4&&arc.length!=3){
            System.out.println("Illegal argument count");
            return false;
        }
        else if(!GlobalClass.logStatus){
            System.out.println("Please log in first");
            return false;
        }
        else if(arc.length==2){
            if(arc[1].equals("<")){
                System.out.println("Please input the path to open the file");
                return false;
            }
            else if(!Operator.pathJudge(arc[1])){
                System.out.println("Illegal path");
                return false;
            }
            arc[1]= OverridePath.overridePath(arc[1]);
            File file=new File(arc[1]);
            if(!file.exists()||file.isDirectory()){
                System.out.println("File not exists");
                return false;
            }
            try{
                Scanner sc=new Scanner(file);
                while (sc.hasNextLine()) {
                    System.out.println(sc.nextLine());
                }
                sc.close();
            } catch (FileNotFoundException e) {
                System.out.println("File operation failed");
                return false;
            }
            return true;
        }
        else if(arc.length==3){
            if(!arc[1].equals("<")){
                System.out.println("Illegal redirector");
                return false;
            }
            else if(!Operator.pathJudge(arc[2])){
                System.out.println("Illegal redirect path");
                return false;
            }
            arc[2]= OverridePath.overridePath(arc[2]);
            File file=new File(arc[2]);
            if(!file.exists()||file.isDirectory()){
                System.out.println("File not exists");
                return false;
            }
            try{
                Scanner sc=new Scanner(file);
                while (sc.hasNextLine()) {
                    System.out.println(sc.nextLine());
                }
                sc.close();
            } catch (FileNotFoundException e) {
                System.out.println("File operation failed");
                return false;
            }
            return true;
        }
        else{
            arc[1]= OverridePath.overridePath(arc[1]);
            arc[3]= OverridePath.overridePath(arc[3]);
            if(!arc[2].equals("<")){
                System.out.println("Illegal redirector");
                return false;
            }
            int flag=1;
            if(!Operator.pathJudge(arc[3]))flag=0;
            else{//如果重定向路径都合法
                File pfile=new File(arc[3]);
                if(!pfile.exists()||pfile.isDirectory()){
                    flag=0;
                }
            }
            if(flag==0){
                if(!Operator.pathJudge(arc[1])){
                    System.out.println("Illegal path");
                    return false;
                }
                File file=new File(arc[1]);
                if(!file.exists()||file.isDirectory()){
                    System.out.println("File not exists");
                    return false;
                }
                try{
                    Scanner sc=new Scanner(file);
                    while (sc.hasNextLine()) {
                        System.out.println(sc.nextLine());
                    }
                    sc.close();
                } catch (FileNotFoundException e) {
                    System.out.println("File operation failed");
                    return false;
                }
                return true;
            }
            else{
                try{
                    File pfile=new File(arc[3]);
                    Scanner sc=new Scanner(pfile);
                    while (sc.hasNextLine()) {
                        System.out.println(sc.nextLine());
                    }
                    sc.close();
                } catch (FileNotFoundException e) {
                    System.out.println("File operation failed");
                    return false;
                }
                return true;
            }
        }
    }
}
