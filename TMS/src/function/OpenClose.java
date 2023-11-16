package function;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class OpenClose{
    public static void delFile(File index){
        for (File file: Objects.requireNonNull(index.listFiles())) {
            if (file.isDirectory()) {
                delFile(file);
            }
            file.delete();
        }
        index.delete();
    }
    public static boolean switchOn_Off(String []Command) throws IOException {//判断开关机指令是否正确
        if(Command.length!=1){
            System.out.println("Illegal argument count");
            return false;
        }
        else{
            File file=new File("./data");
            File []files=file.listFiles();
            delFile(file);
            System.out.println("----- Good Bye! -----");
            System.exit(0);
        }
        return true;
    }
}