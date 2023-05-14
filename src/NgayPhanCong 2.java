
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class NgayPhanCong {
    public  List<String> create7Days(LocalDate a) {
        // Set the start date
        LocalDate startDate = a;

        // Create a list to store the next 7 days
        List<String> next7Days = new ArrayList<>();

        // Format the date as "dd-MM-yyyy" and add it to the list
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        next7Days.add(startDate.format(formatter));

     //    Add the next 6 days to the list
        for (int i = 1; i < 7; i++) {
            startDate = startDate.plusDays(1);
            next7Days.add(startDate.format(formatter));
        }
//        
        // Print the next 7 days
//        System.out.println("Next 7 days:");
//        for (String day : next7Days) {
//            System.out.println(day);
//        }
        return next7Days;
    }
    

    
//    public static void main(String[] args) {
//        LocalDate b = LocalDate.of(2003, 2, 2);
//        List<String> days = create7Days(b);
//        for (String dayOfWeek : days) {
//            System.out.println(dayOfWeek);
//        }
//    }
}
//
//
//import java.util.Calendar;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import javax.swing.JTable;
//
//public class NgayPhanCong {
//
//   public static void main(String[] args) {
//      
//      // Create a calendar object and set it to November 1, 2022
//      Calendar calendar = Calendar.getInstance();
//      calendar.set(2022, Calendar.NOVEMBER, 1);
//      
//      // Create a DateFormat object to format the date strings
//      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//      
//      // Use a loop to create the date range of 7 days
//      String[] dates = new String[7];
//      for (int i = 0; i < 7; i++) {
//         dates[i] = dateFormat.format(calendar.getTime());
//         calendar.add(Calendar.DAY_OF_MONTH, 1);
//      }
//      for(String date23: dates ){
//          System.out.println(date23);
//      }
//      
//      // Set the dates as the header for the first 7 columns of the table
////      JTable table = new JTable();
////      for (int i = 0; i < 7; i++) {
////         table.getColumnModel().getColumn(i).setHeaderValue(dates[i]);
////      }
//   }
//}
