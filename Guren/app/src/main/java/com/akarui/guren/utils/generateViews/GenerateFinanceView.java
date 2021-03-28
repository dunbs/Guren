package com.akarui.guren.utils.generateViews;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class GenerateFinanceView {
    public ConstraintLayout generateTransactionCard(Context context, String transactionTitleValue, String transactionAuthorValue, String transactionAmountValue) {
        ConstraintLayout.LayoutParams commonParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );

        ConstraintLayout cardLayout = new ConstraintLayout(context);

        ConstraintSet cardLayoutSet = new ConstraintSet();

        TextView transactionTitle = new TextView(context);
        TextView transactionAuthor = new TextView(context);
        TextView transactionAmount = new TextView(context);

        transactionTitle.setId(transactionTitle.generateViewId());
        transactionAuthor.setId(transactionAuthor.generateViewId());
        transactionAmount.setId(transactionAmount.generateViewId());

        transactionTitle.setLayoutParams(commonParams);
        transactionAuthor.setLayoutParams(commonParams);
        transactionAmount.setLayoutParams(commonParams);

        transactionTitle.setText(transactionTitleValue);
        transactionAuthor.setText(transactionAuthorValue);
        transactionAmount.setText(transactionAmountValue);
        
        cardLayout.addView(transactionTitle);
        cardLayout.addView(transactionAuthor);
//        cardLayout.addView(transactionAmount);

        cardLayoutSet.clone(cardLayout);

        cardLayoutSet.connect(transactionTitle.getId(), ConstraintSet.START, cardLayout.getId(), ConstraintSet.START, 2);
        cardLayoutSet.connect(transactionTitle.getId(), ConstraintSet.TOP, cardLayout.getId(), ConstraintSet.TOP, 3);
//        cardLayoutSet.connect(transactionTitle.getId(), ConstraintSet.END, transactionAmount.getId(), ConstraintSet.START, 1);

        cardLayoutSet.connect(transactionAuthor.getId(), ConstraintSet.START, cardLayout.getId(), ConstraintSet.START, 2);
        cardLayoutSet.connect(transactionAuthor.getId(), ConstraintSet.TOP, transactionTitle.getId(), ConstraintSet.BOTTOM, 3);
//        cardLayoutSet.connect(transactionAuthor.getId(), ConstraintSet.END, transactionAmount.getId(), ConstraintSet.START, 1);

//        cardLayoutSet.connect(transactionAmount.getId(), ConstraintSet.TOP, cardLayout.getId(), ConstraintSet.TOP);
//        cardLayoutSet.connect(transactionAmount.getId(), ConstraintSet.BOTTOM, cardLayout.getId(), ConstraintSet.BOTTOM);
//        cardLayoutSet.connect(transactionAmount.getId(), ConstraintSet.END, cardLayout.getId(), ConstraintSet.END);

        cardLayoutSet.applyTo(cardLayout);

        return cardLayout;
    }
}
