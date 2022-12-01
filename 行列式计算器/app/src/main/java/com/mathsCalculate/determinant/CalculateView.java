package com.mathsCalculate.determinant;

import android.content.*;
import android.text.*;
import android.util.*;
import android.view.*;
import android.widget.*;

public class CalculateView extends GridLayout {
	
	//行列式的阶数
	private int rankShow;
	//用于存储行列式类
	private Determinant[][] determinant;
	//用于存储行列式具体的数字
	private double[][] a;
	private double[][] aBackup;
	private int cardWidth;
	//private GestureDetector gestureDetector;
	//构造器
	public CalculateView(Context context) {
		super(context);
		//gestureDetector = new GestureDetector(getContext(),this);
		initialView();
	}
	public CalculateView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		//gestureDetector = new GestureDetector(getContext(),this);
		initialView();
	}
	public CalculateView(Context context, AttributeSet attrs) {
		
		super(context, attrs);
		//gestureDetector = new GestureDetector(getContext(),this);
		initialView();
	}
	//初始化界面
	private void initialView(){
		rankShow = MainActivity.getMainActivity().getRankNum();
		setColumnCount(rankShow);
		setRowCount(rankShow);
		setBackgroundColor(0xffbbada0);
		determinant = new Determinant[rankShow][rankShow];
		//初始化行列式数组
		a = new double[rankShow][rankShow];
		aBackup = new double[rankShow][rankShow];
		//记录整个行列式的大小
		cardWidth = 1000/rankShow;
		//添加每个行列式的框架
		addNumView(cardWidth, cardWidth);
		//调用自己写的监听每个editText的方法
		setTextChangeListener();
		
	}
	//监听Determinant[][] 的每个对象的EditText
	private void setTextChangeListener(){
		for(int r=0;r<rankShow;r++){
			for(int c=0;c<rankShow;c++){
				final int col = c;
				final int row = r;
				determinant[r][c].getEditText().addTextChangedListener(new TextWatcher(){
						@Override
						public void onTextChanged(CharSequence s,int start,int count,int after){
						}
						@Override
						public void beforeTextChanged(CharSequence s,int start,int count,int after){
						}
						@Override
						public void afterTextChanged(Editable s){
							String inputNum = determinant[row][col].getEditText().getText().toString();
							if(!inputNum.equals("") && !inputNum.equals(".") && !inputNum.equals("-") && !inputNum.equals("+"))
							{
								a[row][col] = Double.valueOf(inputNum);
								aBackup[row][col] = a[row][col];
							}else if(inputNum.equals("")){
								a[row][col] = 0;
								aBackup[row][col] = 0;
							}
						}
				});
			}
		}
		
	}
	
	//把备份的a的数值归还给a
	public void returnBackupToA(){
		for(int r=0;r<rankShow;r++){
			for(int c=0;c<rankShow;c++){
				a[r][c] = aBackup[r][c];
			}
		}
	}
	//把每个行列式框架加入到整体框架中
	private void addNumView(int cardWidth,int cardHeight){
		for(int r=0;r<rankShow;r++){
			for(int c=0;c<rankShow;c++){
				determinant[r][c]=new Determinant(getContext());
				addView(determinant[r][c],cardWidth,cardHeight);
			}
		}
	}
	//计算行列式
	public double calculateDeterminant(int k,double result,int jieshu){
		
		for(int i=k;i<jieshu-1;i++){

			for(int j=k;j<jieshu-1;j++){

				if(a[k][k]==0){
					for(int n=k+1;n<jieshu;n++){
						for(int m=0;m<jieshu;m++){
							a[m][k]=a[m][k]+a[m][n];
						}
						if(a[k][k]!=0){
							break;
						}
					}
					if(a[k][k]==0){
						result=0;
						break;
					}
				}
				double beishu = -a[i+1][k]/a[k][k];
				a[i+1][j+1] = a[i+1][j+1]+beishu*a[k][j+1];
			}
			if(result!=0) {
				a[i+1][k]=0;
			}else {
				k=jieshu;
				break;
			}
		}
		if(k==jieshu){
			for(int i=0;i<jieshu;i++){
				result = result*a[i][i];
			}
		}else{
			k++;
			result=calculateDeterminant(k,result,jieshu);
		}
		return result;	
	}
	//清空行列式
	public void clearDeterminant(){
		for(int r=0;r<rankShow;r++){
			for(int c=0;c<rankShow;c++){
				a[r][c] = 0;
				determinant[r][c].getEditText().setText("");
			}
		}
	}
}
