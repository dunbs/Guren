package UI.Finance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.akarui.guren.R;

public class FinanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);

        insertTransactionData();
    }

    private void insertTransactionData() {
    }
}