package com.example.mufidzcustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;

public class MyEditText extends AppCompatEditText implements View.OnTouchListener {
    Drawable mClearButtonImage;
    public MyEditText(@NonNull Context context) {
        super(context);
        init();
    }

    public MyEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        mClearButtonImage = ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_close_24);
        setOnTouchListener(this);

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty()){
                    showClearButton();
                } else {
                    hideClearButton();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void showClearButton(){
        setCompoundDrawablesRelativeWithIntrinsicBounds(null,null, mClearButtonImage,null);
    }

    private void hideClearButton(){
        setCompoundDrawablesRelativeWithIntrinsicBounds(null,null, null,null);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if ((getCompoundDrawables()[2] != null)){
            float clearButtonStart;
            float clearButtonEnd;
            boolean isClearButtonClicked = false;
            if (getLayoutDirection() == LAYOUT_DIRECTION_RTL){
                clearButtonEnd = mClearButtonImage.getIntrinsicWidth()+getPaddingStart();
                if (event.getX() < clearButtonEnd){
                    isClearButtonClicked = true;
                }
            } else {
                clearButtonStart = (getWidth() - getPaddingEnd() - mClearButtonImage.getIntrinsicWidth());
                if (event.getX() > clearButtonStart){
                    isClearButtonClicked = true;
                }
            }
            if (isClearButtonClicked){
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    mClearButtonImage = ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_close_24);
                    showClearButton();
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP){
                    mClearButtonImage = ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_close_24);
                    if (getText() != null){
                        getText().clear();
                    }
                    hideClearButton();
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setHint("Masukkan Nama Anda");
        setTextAlignment(TEXT_ALIGNMENT_VIEW_START);
    }
}
