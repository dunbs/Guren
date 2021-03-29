package com.akarui.guren.utils.generateViews;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class GenerateFinanceView {
    public CardView generateTransactionCard(Context context, String transactionTitleValue, String transactionAuthorValue, String transactionAmountValue) {

        CardView card = new CardView(context);
        card.setLayoutParams(new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        ));
        card.setContentPadding(20, 20, 20, 20);
        card.setRadius(20);
        card.setCardBackgroundColor(Color.parseColor("#33FFF1"));

        ConstraintLayout cardLayout = new ConstraintLayout(context);
        cardLayout.setLayoutParams(new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        ));


        ConstraintSet cardLayoutSet = new ConstraintSet();

        TextView transactionTitle = new TextView(context);
        TextView transactionAuthor = new TextView(context);
        TextView transactionAmount = new TextView(context);

        transactionTitle.setId(transactionTitle.generateViewId());
        transactionAuthor.setId(transactionAuthor.generateViewId());
        transactionAmount.setId(transactionAmount.generateViewId());

        transactionTitle.setLayoutParams(new ConstraintLayout.LayoutParams(-2, -2));
        transactionAuthor.setLayoutParams(new ConstraintLayout.LayoutParams(-2, -2));
        transactionAmount.setLayoutParams(new ConstraintLayout.LayoutParams(-2, -2));

        transactionTitle.setText(transactionTitleValue);
        transactionAuthor.setText(transactionAuthorValue);
        transactionAmount.setText(transactionAmountValue);
        
        cardLayout.addView(transactionTitle);
        cardLayout.addView(transactionAuthor);
        cardLayout.addView(transactionAmount);

        cardLayoutSet.clone(cardLayout);

        cardLayoutSet.connect(transactionTitle.getId(), ConstraintSet.START, cardLayout.getId(), ConstraintSet.START);
        cardLayoutSet.connect(transactionTitle.getId(), ConstraintSet.TOP, cardLayout.getId(), ConstraintSet.TOP);

        cardLayoutSet.connect(transactionAuthor.getId(), ConstraintSet.START, cardLayout.getId(), ConstraintSet.START);
        cardLayoutSet.connect(transactionAuthor.getId(), ConstraintSet.TOP, transactionTitle.getId(), ConstraintSet.BOTTOM);

//        cardLayoutSet.connect(transactionAmount.getId(), ConstraintSet.END, cardLayout.getId(), ConstraintSet.END);
        cardLayoutSet.connect(transactionAmount.getId(), ConstraintSet.TOP, transactionAuthor.getId(), ConstraintSet.BOTTOM);
        cardLayoutSet.connect(transactionAmount.getId(), ConstraintSet.START, cardLayout.getId(), ConstraintSet.START);

        cardLayoutSet.applyTo(cardLayout);

        card.addView(cardLayout);

        return card;
    }
}
