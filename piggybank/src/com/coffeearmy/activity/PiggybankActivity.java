package com.coffeearmy.activity;

import java.util.ArrayList;

import com.coffeearmy.activity.InputView.OperationListener;
import com.coffeearmy.list.TransactionListAdapter;
import com.coffeearmy.piggybank.R;
import com.coffeearmy.storage.TransactionsAndTotalHandler;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;


public class PiggybankActivity extends ActionBarActivity {

  
	private ListView transactionList;
	private TextSwitcher txtSwitchSaves;
	
	
	protected InputView inputViewHandler;
	private TransactionsAndTotalHandler transactionsHandler;
	private LinearLayout inputViewLayout;

	/**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after 
     * previously being shut down then this Bundle contains the data it most 
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        transactionsHandler= new TransactionsAndTotalHandler(this);
        //Get list items
        ArrayList<String> transactionItems= transactionsHandler.readTransactions();
        //Get total saves
        String totalSaves = transactionsHandler.readTotal();
        
        //Setup List
        transactionList= (ListView) findViewById(R.id.lstTransaction);
        transactionList.setAdapter(new TransactionListAdapter(this, android.R.layout.simple_list_item_1, transactionItems));
        //SSet total saves
        txtSwitchSaves = (TextSwitcher) findViewById(R.id.txtSavesTotal);
        txtSwitchSaves.setTag(R.string.current_saves, Double.parseDouble(totalSaves));
        txtSwitchSaves.setFactory(new ViewFactory() {
			
			public View makeView() {
				TextView myText = new TextView(PiggybankActivity.this);
                myText.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                myText.setTextSize(40);
                myText.setTextColor(Color.WHITE);
                return myText;				
			}
		});
        // Declare the in and out animations and initialize them  
        Animation in = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right);
        
        // set the animation type of textSwitcher
        txtSwitchSaves.setInAnimation(in);
        txtSwitchSaves.setOutAnimation(out);
        
        //Set currect Saves
        txtSwitchSaves.setText(totalSaves);
        
        //Layout where the input will appear
        inputViewLayout = (LinearLayout) findViewById(R.id.inputLayout);
        inputViewHandler = new InputView(this);
        
               
        inputViewHandler.setEventListener(new OperationListener() {
			
			public void onOperationIsPerformed(boolean isAdd, double cuantity) {
				String itemList="";
				double savesValue= (Double) txtSwitchSaves.getTag(R.string.current_saves);
				//Do de operation
				if(isAdd){
					savesValue=savesValue+cuantity;
					itemList="+";
				}else{
					savesValue=savesValue-cuantity;
					itemList="-";
				}
				//Change currect Value
				txtSwitchSaves.setTag(R.string.current_saves, savesValue);
				txtSwitchSaves.setText(savesValue+"");
				//StoreValue
				transactionsHandler.writeTotal(savesValue+"");
				//Store operation
				transactionsHandler.writeTransactions(itemList+" "+cuantity);
				//RePopulate the list
				((TransactionListAdapter) transactionList.getAdapter()).notifyListChanged(transactionsHandler.readTransactions());
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	 switch (item.getItemId()) {
         case R.id.action_operation:
        	 	openAddOperationMenu();
             return true;
         default:
             return super.onOptionsItemSelected(item);
     }
    	
 }
    private void openAddOperationMenu(){
    	 if(!inputViewHandler.isViewInflated()){
				View inflated = getLayoutInflater().inflate(R.layout.input_transactions,null);
				inputViewHandler.setInputView(inflated);
				inputViewLayout.addView(inflated);
				}
				inputViewHandler.InputViewSetup();
				inputViewHandler.setViewVisible();
    }
    
    @Override
    public void onBackPressed() {
    	if(inputViewHandler.isViewVisible())
    		inputViewHandler.setViewGone();
    	else
    	super.onBackPressed();
    }

}

