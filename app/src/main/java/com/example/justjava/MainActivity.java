/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;



import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        CheckBox cappuccinoCheckBox = (CheckBox) findViewById(R.id.cappuccino_checkbox);
        boolean hasCappuccino = cappuccinoCheckBox.isChecked();
        EditText name = (EditText) findViewById(R.id.Name_EditText);
        String nameText = name.getText().toString();
        int price = calculateOrder(hasWhippedCream,hasChocolate,hasCappuccino);
        String priceMessage = orderSummary(nameText ,price, hasWhippedCream, hasChocolate, hasCappuccino);
        displayMessage(priceMessage);

    }

    public void increment(View view)       //increment
    {
        if(quantity==100)
        {   limit();
            return;
        }
        quantity++;
        displayQuantity(quantity);
    }

    public void decrement(View view)       //decrement
    {
        if(quantity==1)
        {   limit();
            return;
        }
        quantity--;
        displayQuantity(quantity);
    }
    private void limit()
    {
        Toast toast = Toast.makeText(getApplicationContext(), "limit : 1-100 ", Toast.LENGTH_SHORT);
        toast.show();
    }
    private String orderSummary(String name,int price, boolean hwc, boolean hc,boolean hca)  // has whipped cream :- hws , has chocolate :- hc , has cappucino :- hc
    {
        String temp;
        temp = "Name: "+name+"\nAdd Whipped Cream?"+hwc+"\nAdd Chocolate?"+hc+"\nAdd Cappuccino?"+hca+"\nQuantity: "+quantity+"\nTotal: $"+price+"\nThank you!";
        return temp;
    }
    private int calculateOrder(boolean hasWhippedCream,boolean hasChocolate,boolean hasCappucino)
    {   int basePrice = 5;
        if(hasWhippedCream) {basePrice += 1;}
        if(hasChocolate)    {basePrice += 2;}
        if(hasCappucino)    {basePrice += 3;}
        return quantity * basePrice;
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}