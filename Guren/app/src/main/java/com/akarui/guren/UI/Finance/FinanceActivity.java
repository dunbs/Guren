package com.akarui.guren.UI.Finance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.akarui.guren.R;
import com.akarui.guren.database.entity.Transaction;
import com.akarui.guren.utils.generateViews.GenerateFinanceView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FinanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);

        insertChartData();
        insertTransactionData();
    }

    private void insertChartData() {
        BarChart financeChart = findViewById(R.id.finance_chart);
        List<BarEntry> entries = new ArrayList<BarEntry>();
        for(int i = 0; i < 12; ++i) {
            entries.add(new BarEntry(i, (float) (Math.random()*10)));
        }

        BarDataSet dataSet = new BarDataSet(entries, "Finance");
        dataSet.setColor(1000);
        BarData barData = new BarData(dataSet);

        financeChart.setData(barData);
        financeChart.invalidate();

    }



    private void insertTransactionData() {
        GenerateFinanceView generateFinanceView = new GenerateFinanceView();

        ConstraintLayout transactionLayout = findViewById(R.id.finance_transaction_list);
        ConstraintLayout cardLayout;

        ConstraintSet transactionLayoutSet = new ConstraintSet();
        cardLayout = generateFinanceView.generateTransactionCard(this, "Sample Title", "Sample Author", "$199");
        cardLayout.setId(cardLayout.generateViewId());
        transactionLayout.addView(cardLayout);

        transactionLayoutSet.clone(transactionLayout);
        transactionLayoutSet.connect(cardLayout.getId(), ConstraintSet.TOP, transactionLayout.getId(), ConstraintSet.TOP, 3);
        transactionLayoutSet.connect(cardLayout.getId(), ConstraintSet.START, transactionLayout.getId(), ConstraintSet.START, 2);
        transactionLayoutSet.applyTo(transactionLayout);

        for(int i = 0; i < 10; ++i) {
            String transactionTitle = "Title " + (i+1);
            String transactionAuthor = "Author " + (i+1);
            String transactionAmount = "$" + Math.ceil(Math.random()*1000);

            int prevId = cardLayout.getId();
            int currentId = cardLayout.generateViewId();

            cardLayout = generateFinanceView.generateTransactionCard(this, transactionTitle, transactionAuthor, transactionAmount);
            cardLayout.setId(currentId);
            transactionLayout.addView(cardLayout);

            transactionLayoutSet.clone(transactionLayout);
            transactionLayoutSet.connect(currentId, ConstraintSet.TOP, prevId, ConstraintSet.BOTTOM, 30);
            transactionLayoutSet.connect(currentId, ConstraintSet.START, transactionLayout.getId(), ConstraintSet.START, 2);
            transactionLayoutSet.applyTo(transactionLayout);

        }


    }
}