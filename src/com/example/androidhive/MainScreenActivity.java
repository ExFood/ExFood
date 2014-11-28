package com.example.androidhive;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainScreenActivity extends Activity implements OnClickListener{
	
	Button btnViewProducts;
	Button btnNewProduct;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);
		
		// Buttons
		btnViewProducts = (Button) findViewById(R.id.btnViewProducts);
		btnNewProduct = (Button) findViewById(R.id.btnCreateProduct);
		// view products click event
		
		btnViewProducts.setOnClickListener(this);
		
	}
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
		    Toast toast = Toast.makeText(getApplicationContext(), 
		        "ok dadw", Toast.LENGTH_SHORT);
		    toast.show();
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btnViewProducts){
			IntentIntegrator scanIntegrator = new IntentIntegrator(this);
			scanIntegrator.initiateScan();
	
		Toast toast = Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT);
	    toast.show();
		}
	    
	}
	
}
