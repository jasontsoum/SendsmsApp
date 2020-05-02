package com.example.sendsms2;



import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.telephony.SmsMessage;
import android.view.View;
import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.gsm.SmsManager;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	EditText txtnumber, txtname, txtaddress;
	
	Button btnsend, btnsave, btnclear;
	
	SharedPreferences myprefs;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtnumber=(EditText)findViewById(R.id.editText2); //number
		btnsend=(Button)findViewById(R.id.button1);
		btnsave=(Button)findViewById(R.id.button2);
		btnclear=(Button)findViewById(R.id.button3);
		
	
		myprefs = getPreferences(MODE_PRIVATE);
		
		init();
		btnclear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {	
				SharedPreferences.Editor editor = myprefs.edit();
				editor.clear();
				editor.commit();
				readreferences();
				
			}
		});
		
		btnsave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
					
				SharedPreferences.Editor editor = myprefs.edit();
				String nameofperson=txtname.getText().toString();
				String addressperson=txtaddress.getText().toString();
				editor.putString("keyname", nameofperson);
				editor.putString("keyname2", addressperson);
				editor.commit();
				}
		});
		
		
		btnsend.setOnClickListener(new View.OnClickListener() {
				
				@Override
				 public void onClick(View v) 
	            {                
	                String phoneNo = "13033";
	               // String numberout = txtnumber.getText().toString(); 
	                String name_cl = txtname.getText().toString() + txtaddress.getText().toString();  
	                String address_cl=txtaddress.getText().toString();
	                
	                if (phoneNo.length()>0 && name_cl.length()>0 && address_cl.length()>0)                
	                    sendSMS(phoneNo, name_cl);                
	                else
	                    Toast.makeText(getBaseContext(), 
	                        "Παρακαλώ Συμπληρώστε όλα τα πεδία", 
	                        Toast.LENGTH_SHORT).show();
	            }
			});
		
		
		}
	private void sendSMS(String phoneNumber, String message)
    {        
        
		
		//PendingIntent pi = PendingIntent.getActivity(this, 0,
          //  new Intent(this, SMS.class), 0);
        try {  
        	
        	SmsManager sms = SmsManager.getDefault();
        	sms.sendTextMessage(phoneNumber, null, message, null, null);   
        	Toast.makeText(getApplicationContext(), "SMS Sent.", Toast.LENGTH_LONG).show();
       			
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "SMS FAIL Please try againn", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		       
		}
		
       
    } 

	public void readreferences(){	
		String st1=myprefs.getString("keyname", "");
		txtname.setText(st1);
		
		
		String st2=myprefs.getString("keyname2", "");
		txtaddress.setText(st2);
	}
	private void init(){
		txtname =(EditText)findViewById(R.id.editText1);
		txtaddress=(EditText)findViewById(R.id.editText3);
		readreferences();
	}
	
}
