import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadMail implements Runnable {
    Parse.Test obj=null;
    Parse.Trade trade=null;
    public ReadMail(Parse.Test obj, Parse.Trade trade){
        this.obj=obj;
        this.trade=trade;
    }

    @Override
    public void run() {
        while (Parse.work!=false) {

            try {
                 BufferedReader rd = new BufferedReader(new FileReader("readbox.txt"));
               String x = rd.readLine();
                System.out.println("Readbox "+x);
                rd.close();
                if (x.equalsIgnoreCase("screen")) {
                    obj.sendSignal("Ответ на запрос скрина", "");
                }
                if (x.equalsIgnoreCase("buy")) {
                    trade.Order_B();
                    obj.sendSignal("Покупка", "");
                }
                if (x.equalsIgnoreCase("sell")) {
                    trade.Order_S();
                    obj.sendSignal("Продажа", "");
                }
                Thread.sleep(20000);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

