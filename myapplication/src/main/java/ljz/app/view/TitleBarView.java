package ljz.app.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.ref.Reference;

import ljz.app.R;

/**
 * @ClassName TitleView
 * @Description TODO
 * @Author LJZ
 * @Date 2020/5/25 16:36
 * @Version 1.0
 */
public class TitleBarView extends RelativeLayout {
    private int leftTextColor, rightTextColor, titleTextColor;
    private Drawable leftBackGround, rightBackGround;
    private String titleText, leftText, rightText;
    private float titleTextSize;
    private Button leftButton, rightButton;
    private TextView tv_Title;
    private LayoutParams leftLayoutParams, rightLayoutParams, titleLayoutParams;

    public TitleBarView(Context context) {
        super(context);

    }

    public TitleBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFromAttributes(context, attrs);
    }

    public TitleBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initFromAttributes(context, attrs);
    }

    public TitleBarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initFromAttributes(context, attrs);
    }

    private void initFromAttributes(Context context, AttributeSet attributeSet) {
        if (null != attributeSet) {
            TypedArray typedValue = context.obtainStyledAttributes(attributeSet, R.styleable.TitleBarView);
            leftTextColor = typedValue.getColor(R.styleable.TitleBarView_leftTextColor, Color.BLACK);
            rightTextColor = typedValue.getColor(R.styleable.TitleBarView_rightTextColor, Color.BLACK);
            titleTextColor = typedValue.getColor(R.styleable.TitleBarView_titleTextColor, Color.BLACK);
            leftBackGround = typedValue.getDrawable(R.styleable.TitleBarView_leftBackGround);
            rightBackGround = typedValue.getDrawable(R.styleable.TitleBarView_rightBackGround);
            titleTextSize = typedValue.getDimension(R.styleable.TitleBarView_titleTextSize, 20f);
            titleText = typedValue.getString(R.styleable.TitleBarView_titleText);
            leftText = typedValue.getString(R.styleable.TitleBarView_leftText);
            rightText = typedValue.getString(R.styleable.TitleBarView_rightText);
            typedValue.recycle();
        }
        initView(context);
    }

    private void initView(Context context) {
        leftButton = new Button(context);
        rightButton = new Button(context);
        tv_Title = new TextView(context);
        leftButton.setTextColor(leftTextColor);
        leftButton.setBackground(leftBackGround);
        leftButton.setText(leftText);
        rightButton.setTextColor(rightTextColor);
        rightButton.setBackground(rightBackGround);
        rightButton.setText(rightText);
        tv_Title.setText(titleText);
        tv_Title.setTextColor(titleTextColor);
        tv_Title.setTextSize(titleTextSize);
        tv_Title.setGravity(Gravity.CENTER);
        leftLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        leftLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        addView(leftButton, leftLayoutParams);
        rightLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        rightLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(rightButton, rightLayoutParams);

        titleLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        titleLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(tv_Title, titleLayoutParams);



    }


    /**
     *
     */
    public void setLeftListener(View.OnClickListener onClickListener) {
        leftButton.setOnClickListener(onClickListener);
    }

    public void setRightListener(View.OnClickListener onClickListener) {
        rightButton.setOnClickListener(onClickListener);
    }

    /**
     *
     */
    public void setButtonVisible(int id, boolean flag) {

        switch (id) {
            case 0:
                if (flag) {
                    leftButton.setVisibility(VISIBLE);
                    rightButton.setVisibility(GONE);
                } else {
                    leftButton.setVisibility(GONE);
                    rightButton.setVisibility(VISIBLE);
                }
                break;
            case 1:
                if (flag) {
                    rightButton.setVisibility(VISIBLE);
                    leftButton.setVisibility(GONE);
                } else {
                    rightButton.setVisibility(GONE);
                    leftButton.setVisibility(VISIBLE);
                }
                break;
            default:
                leftButton.setVisibility(VISIBLE);
                rightButton.setVisibility(GONE);
                break;
        }


    }

}
