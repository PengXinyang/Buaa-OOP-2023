import java.io.*;
import java.util.*;

import commodity.CommodityUnit;
import function.*;
import people.*;
import shop.Shop;
import variable.GlobalClass;

public class Test {
    public static void main(String []arc) throws IOException {
        File f=new File("data");
        if(!f.exists()||!f.isDirectory()){
            f.mkdirs();
        }
        Scanner in =new Scanner(System.in);
        PrintStream out=System.out;
        System.setOut(out);
        while(true){
            String command=in.nextLine();
            command=command.trim();
            String []Command=command.split("\\s+");
            switch (Command[0]) {
                case "quit" -> OpenClose.switchOn_Off(Command);
                case "register" -> {
                    Register.register(Command);
                }
                case "login" -> Login.login(Command);
                case "logout" -> Logout.logout(Command);
                case "printInfo" -> PrintInfo.printInfo(Command);
                case "registerShop"->ShopRegister.shopRegister(Command);
                case "putCommodity"->CommodityShelve.commodityShelve(Command);
                case "listShop"->ShopView.shopView(Command);
                case "listCommodity"->CommodityView.commodityView(Command);
                case "searchCommodity"->CommoditySearch.searchCommodity(Command);
                case "buyCommodity"->BuyCommodity.buyCommodity(Command);
                case "removeCommodity"->RemoveCommodity.removeCommodity(Command);
                case "cancelShop"->CancelShop.cancelShop(Command);
                case "putCommodityBatch"->PutCommodityBatch.putCommodityBatch(Command);
                case "cancelOrder"->CancelOrder.cancelOrder(Command);
                case "finishOrder"->FinishOrder.finishOrder(Command);
                case "listOrder"->ListOrder.listOrder(Command);
                case "exportMerchantOrder"->ExportMerchantOrder.exportMerchantOrder(Command);
                case "openFile"->OpenFile.openFile(Command);
                case "favoriteCommodity"->FavoriteCommodity.favoriteCommodity(Command);
                case "cancelFavoriteCommodity"->CancelFavoriteCommodity.cancelFavoriteCommodity(Command);
                case "listFavoriteCommodity"->ListFavoriteCommodity.listFavoriteCommodity(Command);
                case "uploadFavoriteCommodity"->UploadFavoriteCommodity.uploadFavoriteCommodity(Command);
                case "readFavoriteCommodity"->ReadFavoriteCommodity.readFavoriteCommodity(Command);
                case "buyFavoriteCommodity"->BuyFavoriteCommodity.buyFavoriteCommodity(Command);
                default -> System.out.println("Command '" + Command[0] + "' not found");
            }
        }
    }
}
