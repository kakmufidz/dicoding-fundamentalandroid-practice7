package com.example.mufidzcustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

public class MyButton extends AppCompatButton {
    private Drawable enabledBackground, disabledBackground;
    private int textColor;

    public MyButton(Context context){
        super(context);
        init();
    }
    public MyButton(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }
    public MyButton(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackground(isEnabled() ? enabledBackground : disabledBackground);
        setTextColor(textColor);
        setTextSize(12.f);
        setGravity(Gravity.CENTER);
        setText(isEnabled() ? "Submit" : "Isi Dulu");
    }

    public void init(){
        textColor = ContextCompat.getColor(getContext(), android.R.color.background_light);
        enabledBackground = ContextCompat.getDrawable(getContext(), R.drawable.bg_button);
        disabledBackground = ContextCompat.getDrawable(getContext(), R.drawable.bg_button_disable);
    }
}
