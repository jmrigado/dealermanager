package com.rivetry.dealermanager.custom;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.Button;

public class IconButton extends Button {
    private static final int LEFT = 0, TOP = 1, RIGHT = 2, BOTTOM = 3;

    // Pre-allocate objects for layout measuring
    private Rect textBounds = new Rect();
    private Rect drawableBounds = new Rect();

    public IconButton(Context context) {
        this(context, null);
    }

    public IconButton(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.buttonStyle);
    }

    public IconButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if (!changed) return;

        final CharSequence text = getText();
        if (!TextUtils.isEmpty(text)) {
            TextPaint textPaint = getPaint();
            textPaint.getTextBounds(text.toString(), 0, text.length(), textBounds);
        } else {
            textBounds.setEmpty();
        }

        final int height = getHeight() - (getPaddingTop() + getPaddingBottom());

        final Drawable[] drawables = getCompoundDrawables();

        if (drawables[TOP] != null) {
            drawables[TOP].copyBounds(drawableBounds);
            int topOffset = (height/2)-((textBounds.height())+drawableBounds.height()/2);
            drawableBounds.offsetTo(0, topOffset);
            drawables[TOP].setBounds(drawableBounds);
        }

        if (drawables[BOTTOM] != null) {
            drawables[BOTTOM].copyBounds(drawableBounds);
            int topOffset = (height/2)+((textBounds.height())+drawableBounds.height()/2);
            drawableBounds.offsetTo(0, topOffset);
            drawables[BOTTOM].setBounds(drawableBounds);
        }
    }
}