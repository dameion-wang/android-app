package com.mathsCalculate.determinant;

import android.app.*;
import android.content.*;
import android.os.*;
import android.text.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity
{


	private static MainActivity mainActivity = null;

	private int rankNum;
	private String rankStr;
	private EditText editText;
	//构造器
	public MainActivity()
	{
		mainActivity = this;
	}

	public void setRankStr(String rankStr)
	{
		this.rankStr = rankStr;
	}

	public String getRankStr()
	{
		return rankStr;
	}
    @Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		editText = (EditText)findViewById(R.id.editView);
        //得到输入的行列式阶数
		editText.addTextChangedListener(new TextWatcher(){
				@Override
				public void onTextChanged(CharSequence s, int start, int count, int after)
				{

				}
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after)
				{
				}
				@Override
				public void afterTextChanged(Editable s)
				{
					rankStr = editText.getText().toString();
					if (!rankStr.equals(""))
					{
						if (Integer.valueOf(rankStr) >= 11)
						{
							Toast.makeText(MainActivity.this, "输入11阶以下的行列式！", Toast.LENGTH_SHORT).show();
						}
						rankNum = Integer.valueOf(rankStr);
					}
					else if (rankStr.equals(""))
					{
						rankNum = 0;
					}
				}
			});

    }
    //返回键事件
	@Override
	public boolean onKeyDown(int p1, KeyEvent p2)
	{
		//Toast.makeText(MainActivity.this,"退出中",Toast.LENGTH_SHORT).show();
		
		if (p1 == KeyEvent.KEYCODE_BACK)
		{
			AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
			dialog.setTitle("行列式计算器").setMessage("确定要退出？").setPositiveButton("退出", new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface p1, int p2)
					{
						//return super.onKeyDown(p1, p2);
						
						finish();
					}
				});
			dialog.setNegativeButton("取消", null);
			dialog.show();
		}
		
		return false;
		//
	}

	//Getter and setter
	//行列式的阶数
	public int getRankNum()
	{		
		return rankNum;
	}
	public void setRankNum(int rankNum)
	{
		this.rankNum = rankNum;
	}
	public static MainActivity getMainActivity()
	{
		return mainActivity;
	}	
	//输入行列式
	public void startInputButton(View v)
	{
		if (rankNum != 0 && rankNum < 11)	
		{
			Intent intent = new Intent(MainActivity.this, DeterminantActivity.class);
			startActivity(intent);
			finish();
		}
		else if (rankNum == 0)
		{
			Toast.makeText(this, "输入的行列式阶数不能为空或0！", Toast.LENGTH_SHORT).show();
		}



	}
}
