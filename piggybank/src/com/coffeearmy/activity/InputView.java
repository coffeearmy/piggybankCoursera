package com.coffeearmy.activity;

import com.coffeearmy.piggybank.R;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class InputView  {

	private View inputView;
	private ImageButton addButton;
	private ImageButton minusButton;
	private EditText inputCuantity;
	private Button acceptTransation;
	private TextView signLabel;
	private OperationListener mEventListener;
	
	//With this we can comunicate with main Activity
	
	public interface OperationListener {
	    public void onOperationIsPerformed(boolean isAdd, double cuantity);
	}	

	public void setEventListener(OperationListener mEventListener) {
	    this.mEventListener = mEventListener;
	}

	public void InputViewSetup() {
		
		addButton=(ImageButton) inputView.findViewById(R.id.ibtnAddSaves);
		minusButton=(ImageButton) inputView.findViewById(R.id.ibtnMinusSaves);
		inputCuantity=(EditText) inputView.findViewById(R.id.edtCuantity);
		acceptTransation=(Button) inputView.findViewById(R.id.btnOKInputTransaction);
		signLabel = (TextView) inputView.findViewById(R.id.txtVsign);
		
		
		
		//By default will be a add operation
		signLabel.setText("+");
		addButton.setSelected(true);
		inputCuantity.setText("0");
		
		addButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				signLabel.setText("+");
			}
		});
		
		minusButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {				
					signLabel.setText("-");						
			}
		});
		acceptTransation.setTag(this);
		acceptTransation.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if(inputCuantity.length()!=0){
					((InputView) v.getTag()).Operate(signLabel.getText().toString(),inputCuantity.getText().toString());
					//disapear inputView
					((InputView) v.getTag()).setViewGone();
				}
				
			}
		});
	
		
	}
	public boolean isViewInflated(){
		return inputView!=null;
	}
	
	public void setInputView(View inputView) {
		this.inputView = inputView;
	}
	

	protected void Operate(String sign,String value){
		boolean isAddingOp=false;
		double cuantityFromStringInputEdit=0.0;
			if(sign.equals("+")){
				isAddingOp=true;
			}
		
		cuantityFromStringInputEdit=Double.parseDouble(value);	
		
		 if (mEventListener != null) {
		        mEventListener.onOperationIsPerformed(isAddingOp,cuantityFromStringInputEdit);
		 }
	}

	public void setViewVisible() {
		inputView.setVisibility(View.VISIBLE);		
	}

	protected void setViewGone() {
		inputView.setVisibility(View.GONE);		
	}

}
