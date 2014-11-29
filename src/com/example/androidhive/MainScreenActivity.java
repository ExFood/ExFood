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
		btnNewProduct.setOnClickListener(this);
		
	}
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
		if (scanningResult != null) {
			String scanContent = scanningResult.getContents();
			Intent i = new Intent(getApplicationContext(), AllProductsActivity.class);
			i.putExtra("scanContent", scanContent);
			startActivity(i);
		}
		else{
		    Toast toast = Toast.makeText(getApplicationContext(), 
		        "������ ���������� �� ��������!", Toast.LENGTH_SHORT);
		    toast.show();
		}
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btnViewProducts){
			IntentIntegrator scanIntegrator = new IntentIntegrator(this);
			scanIntegrator.initiateScan();
		}
		if(v.getId()==R.id.btnCreateProduct){
<<<<<<< HEAD
			IntentIntegrator scanIntegrator = new IntentIntegrator(this);
			scanIntegrator.initiateScan();
	
		Toast toast = Toast.makeText(getApplicationContext(), "OK c", Toast.LENGTH_SHORT);
	    toast.show();
=======
			Intent i = new Intent(getApplicationContext(), NewProductActivity.class);
			startActivity(i);
>>>>>>> origin/get-prodact-by-barcode
		}
	    
	}
	
}
