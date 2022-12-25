package com.mathsCalculate.determinant;

import android.content.*;
import android.graphics.*;
import android.view.*;
import android.widget.*;
import android.text.*;
import android.renderscript.*;

//行列式类
public class Determinant extends FrameLayout{

	private EditText editText; 
	
	private int rank;
	public Determinant(Context context) {
		super(context);
		editText = new EditText(getContext());
		rank = MainActivity.getMainActivity().getRankNum();
		editText.setTextSize(100/rank);
		editText.setBackgroundColor(0x33ffffff);
		editText.setGravity(Gravity.CENTER);
		//editText.setInputType(8194);  //只能输入数字与小数
		//输入类型正负数和小数
		editText.setInputType(InputType.TYPE_CLASS_NUMBER 
							| InputType.TYPE_NUMBER_FLAG_SIGNED
							| InputType.TYPE_NUMBER_FLAG_DECIMAL);
		LayoutParams lp = new LayoutParams(-1,-1);
		lp.setMargins(20/rank, 20/rank, 0, 0);
		addView(editText,lp);
		editText.setBackgroundColor(0x33ffffff);
	}

	public void setEditText(EditText editText)
	{
		this.editText = editText;
	}

	public EditText getEditText()
	{
		return editText;
	}
}
