package com.example.midtrans_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback;
import com.midtrans.sdk.corekit.core.MidtransSDK;
import com.midtrans.sdk.corekit.core.TransactionRequest;
import com.midtrans.sdk.corekit.core.themes.CustomColorTheme;
import com.midtrans.sdk.corekit.models.CustomerDetails;
import com.midtrans.sdk.corekit.models.snap.TransactionResult;
import com.midtrans.sdk.uikit.SdkUIFlowBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button payButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        payButton = (Button)findViewById(R.id.btnPay);
        payButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPay:
                System.out.println("Button pay telah diklik");
                setupSDK();
                MidtransSDK.getInstance().startPaymentUiFlow(MainActivity.this, "abdcab00-8c43-4f24-9777-abdafd64f3d4" );
        }
    }

    void setupSDK(){
        SdkUIFlowBuilder.init()
                .setClientKey("SB-Mid-client-PNsh99KUzG2488ln") // client_key is mandatory
                .setContext(this) // context is mandatory
                .setTransactionFinishedCallback(new TransactionFinishedCallback() {
                    @Override
                    public void onTransactionFinished(TransactionResult result) {
                        System.out.println(result);
                    }
                }) // set transaction finish callback (sdk callback)
                .setMerchantBaseUrl("https://binarstudpenfinalprojectbe-production.up.railway.app") //set merchant url (required)
                .enableLog(true) // enable sdk log (optional)
                .setColorTheme(new CustomColorTheme("#FFE51255", "#B61548", "#FFE51255")) // set theme. it will replace theme on snap theme on MAP ( optional)
                .setLanguage("en") //`en` for English and `id` for Bahasa
                .buildSDK();
        TransactionRequest transactionRequest = new TransactionRequest("TRANSACTION_ID", 20000);
        CustomerDetails customerDetails = new CustomerDetails();
        transactionRequest.setCustomerDetails(customerDetails);
        MidtransSDK.getInstance().setTransactionRequest(transactionRequest);
    }
}