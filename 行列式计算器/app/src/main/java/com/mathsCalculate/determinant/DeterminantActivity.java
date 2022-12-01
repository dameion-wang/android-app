package com.mathsCalculate.determinant;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.math.*;

public class DeterminantActivity extends Activity
{
	private CalculateView calculateView;
	private TextView tv;
	private Button myCalButton;
	private Button myReturnButton;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.det_show);
        calculateView = (CalculateView)findViewById(R.id.calculateView); 
		tv = (TextView)findViewById(R.id.result);
		myCalButton = (Button)findViewById(R.id.myCalButton);
		myReturnButton = (Button)findViewById(R.id.myReturnButton);
	}
	//计算按钮事件
	public void calculateDeterminantButton(View v)
	{
		int len = MainActivity.getMainActivity().getRankNum();
		double result=1;
		result = calculateView.calculateDeterminant(0, result, len);
		calculateView.returnBackupToA();
		/**
		 取小数点后8位输出
		 */
		BigDecimal big = new BigDecimal(result);
		//处理后的结果
		double result2 = big.setScale(8, BigDecimal.ROUND_HALF_UP).doubleValue();
		tv.setText(String.valueOf(result2));
		
		myReturnButton.setVisibility(View.VISIBLE);
	}
	//清空按钮事件
	public void clearDeterminantButton(View v){
		calculateView.clearDeterminant();
	}
	//返回键事件
	@Override
	public boolean onKeyDown(int p1, KeyEvent p2)
	{
		if (p1 == KeyEvent.KEYCODE_BACK)
		{
			Intent intent = new Intent(this, MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			finish();
		}
		return super.onKeyDown(p1, p2);
	}

	//跳转到MainActivity的按钮事件
	public void returnToMainButton(View v)
	{
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}

}
