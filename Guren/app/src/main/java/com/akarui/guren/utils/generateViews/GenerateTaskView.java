package com.akarui.guren.utils.generateViews;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class GenerateTaskView {
    public CardView generateTaskCard(Context context, String taskTitleValue, String taskTimeValue, String taskContentValue) {
        CardView card = new CardView(context);
        ConstraintLayout.LayoutParams cardLayoutParams = new ConstraintLayout.LayoutParams(-1, -2);

        card.setLayoutParams(cardLayoutParams);
        card.setContentPadding(20, 20, 20 ,20);
        card.setRadius(30);
        card.setCardBackgroundColor(Color.parseColor("#63FDF3"));

        ConstraintLayout cardLayout = new ConstraintLayout(context);
        cardLayout.setLayoutParams(new ConstraintLayout.LayoutParams(-1, -2));

        ConstraintSet cardLayoutSet = new ConstraintSet();

        TextView taskTitle = new TextView(context);
        TextView taskTime = new TextView(context);
        TextView taskContent = new TextView(context);

        taskTitle.setId(taskTitle.generateViewId());
        taskTime.setId(taskTime.generateViewId());
        taskContent.setId(taskContent.generateViewId());

        taskTitle.setLayoutParams(new ConstraintLayout.LayoutParams(-2, -2));
        taskTime.setLayoutParams(new ConstraintLayout.LayoutParams(-2, -2));
        taskContent.setLayoutParams(new ConstraintLayout.LayoutParams(-2, -2));

        taskTitle.setText(taskTitleValue);
        taskTime.setText(taskTimeValue);
        taskContent.setText(taskContentValue);

        cardLayout.addView(taskTitle);
        cardLayout.addView(taskTime);
        cardLayout.addView(taskContent);

        cardLayoutSet.clone(cardLayout);

        cardLayoutSet.connect(taskTitle.getId(), ConstraintSet.TOP, cardLayout.getId(), ConstraintSet.TOP);
        cardLayoutSet.connect(taskTitle.getId(), ConstraintSet.START, cardLayout.getId(), ConstraintSet.START);

        cardLayoutSet.connect(taskTime.getId(), ConstraintSet.TOP, taskTitle.getId(), ConstraintSet.BOTTOM, 20);
        cardLayoutSet.connect(taskTime.getId(), ConstraintSet.START, cardLayout.getId(), ConstraintSet.START);

        cardLayoutSet.connect(taskContent.getId(), ConstraintSet.TOP, taskTime.getId(), ConstraintSet.BOTTOM, 20);
        cardLayoutSet.connect(taskContent.getId(), ConstraintSet.START, cardLayout.getId(), ConstraintSet.START);

        cardLayoutSet.applyTo(cardLayout);
        card.addView(cardLayout);

        return card;

    }
}
