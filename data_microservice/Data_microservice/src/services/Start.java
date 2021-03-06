package services;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import databaseConnection.*;


public class Start {
    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {
        if (false) {
            test();
            return;
        }

        DBConnection connection = new DBConnection("127.0.0.1:3306/notifications", "root", "root");
        NotificationSaver sender = new NotificationSaver(connection);
        Aggregation aggregation = new Aggregation(sender);
        Deserialization des = new Deserialization("nokia", aggregation);
        Notification notification;
    
        while(true){
            try{
                Thread.sleep(10);
                notification = des.getNotifiObj();
                if(notification == null)
                    continue;
//                aggregation.run(notification);
                System.out.println(notification.getMessage());
            } catch (NullPointerException ex){
                ex.printStackTrace();
            }
        }
    }

    private static void test() throws InterruptedException, TimeoutException, IOException {
        int aggregationType = 1;

        DBConnection connection = new DBConnection("127.0.0.1:3306/notifications", "root", "root");
        NotificationSaver sender = new NotificationSaver(connection);
        Aggregation aggregation = new Aggregation(sender);
        Notification notification = new Notification(false, 1, 1, "tag_lol", 5, "test678", aggregationType);
        aggregation.run(notification);

        System.out.println(notification.getMessage());
    }
}
