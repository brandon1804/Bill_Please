package sg.edu.rp.c346.id18044455.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    TextView am;
    EditText amount;
    TextView p;
    EditText pax;
    ToggleButton svs;
    ToggleButton gst;
    TextView d;
    EditText discount;
    TextView tb;
    TextView ep;
    Button sb;
    Button rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        am= findViewById(R.id.am);
        amount = findViewById(R.id.amount);
        p = findViewById(R.id.p);
        pax = findViewById(R.id.pax);
        svs = findViewById(R.id.svs);
        gst = findViewById(R.id.gst);
        d= findViewById(R.id.d);
        discount = findViewById(R.id.discount);
        tb= findViewById(R.id.tb);
        ep = findViewById(R.id.ep);
        sb= findViewById(R.id.sb);
        rb= findViewById(R.id.rb);

        sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount.getText().toString().trim().length() != 0 && pax.getText().toString().trim().length() != 0){
                    double nA = 0.0;
                    if(! svs.isChecked() && ! gst.isChecked()){
                        nA = Double.parseDouble(amount.getText().toString());
                    }
                    else if(svs.isChecked() && ! gst.isChecked()){
                        nA = Double.parseDouble(amount.getText().toString()) * 1.1;
                    }
                    else if(! svs.isChecked() && gst.isChecked()){
                        nA = Double.parseDouble(amount.getText().toString()) * 1.07;
                    }
                    else {
                        nA = Double.parseDouble(amount.getText().toString()) * 1.17;
                    }

                    if(discount.getText().toString().trim().length() != 0){
                        nA *= 1 - Double.parseDouble(discount.getText().toString()) / 100;
                    }

                    tb.setText("Total Bill: $" + String.format("%.2f", nA));
                    int nP = Integer.parseInt(pax.getText().toString());
                    if(nP != 1){
                        ep.setText("Each Pays: $" + String.format("%.2f", nA / nP));
                    }
                    else{
                        ep.setText("Each Pays: $" + String.format("%.2f", nA));
                    }
                }//end of if


                if(amount.getText().toString().trim().length() != 0 && pax.getText().toString().trim().length() == 0){
                    pax.setError("Enter pax");
                }
                else if(amount.getText().toString().trim().length() == 0 && pax.getText().toString().trim().length() != 0){
                    amount.setError("Enter amount");
                }
                else if(amount.getText().toString().trim().length() == 0 && pax.getText().toString().trim().length() == 0) {
                    amount.setError("Enter amount");
                    pax.setError("Enter pax");
                }



            }

        });



    rb.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            amount.getText().clear();
            amount.setHint("Enter amount");
            pax.getText().clear();
            pax.setHint("Enter pax");
            discount.getText().clear();
            discount.setHint("Enter discount");
            tb.setText("Total Bill: $");
            ep.setText("Each Pays: $");
            svs.setChecked(false);
            gst.setChecked(false);
        }
    });


    }//end of method
}//end of class
