
package DAO;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.table.DefaultTableCellRenderer;


public class Currency extends DefaultTableCellRenderer{
    private NumberFormat currencyFormat;
    
    public Currency(){
        currencyFormat = DecimalFormat.getCurrencyInstance(new Locale("vi", "VN"));
    }
    
     @Override
    public void setValue(Object value) {
        if (value != null && value instanceof Number) {
            value = currencyFormat.format(value);
        }
        super.setValue(value);
    }
}
