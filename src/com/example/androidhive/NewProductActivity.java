package com.example.androidhive;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewProductActivity extends Activity {

	// Progress Dialog
	private ProgressDialog pDialog;

	JSONParser jsonParser = new JSONParser();
	EditText inputBarCode;
	EditText inputName;
	EditText inputProteins;
	EditText inputFats;
	EditText inputCarbs;
	EditText inputManufacture;
	EditText inputExx;

	// url to create new product
	private static String url_create_product = "http://exfood.zz.vc/db/create_product.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_product);

		// Edit Text

		inputBarCode = (EditText) findViewById(R.id.inputBarCode);
		inputName = (EditText) findViewById(R.id.inputName);
		inputProteins = (EditText) findViewById(R.id.inputeProteines);
		inputFats = (EditText) findViewById(R.id.inputeFats);
		inputCarbs = (EditText) findViewById(R.id.inputeCarbs);
		inputManufacture = (EditText) findViewById(R.id.inputManufacture);
		inputExx = (EditText) findViewById(R.id.inputExx);

		// Create button
		Button btnCreateProduct = (Button) findViewById(R.id.createButton);

		// button click event
		btnCreateProduct.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// creating new product in background thread
				new CreateNewProduct().execute();
			}
		});
	}

	/**
	 * Background Async Task to Create new product
	 * */
	class CreateNewProduct extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(NewProductActivity.this);
			pDialog.setMessage("Creating Product..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Creating product
		 * */
		protected String doInBackground(String... args) {
			
			String BarCode = inputBarCode.getText().toString();
			String Name =  inputName.getText().toString();
			String Proteins = inputProteins.getText().toString();
			String Fats = inputFats.getText().toString();
			String Carbs = inputCarbs.getText().toString();
			String Manufacture = inputManufacture.getText().toString();
			String Exx = inputExx.getText().toString();

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("bar_code", BarCode));
			params.add(new BasicNameValuePair("name", Name));
			params.add(new BasicNameValuePair("proteins", Proteins));
			params.add(new BasicNameValuePair("fats", Fats));
			params.add(new BasicNameValuePair("carbs", Carbs));
			params.add(new BasicNameValuePair("manufacturer", Manufacture));
			params.add(new BasicNameValuePair("product_additive", Exx));

			// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(url_create_product,"POST", params);
			
			// check log cat fro response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// successfully created product
					Intent i = new Intent(getApplicationContext(), AllProductsActivity.class);
					startActivity(i);
					
					// closing this screen
					finish();
				} else {
					// failed to create product
					Toast toast = Toast.makeText(getApplicationContext(),"Продукт не доданий!", Toast.LENGTH_SHORT);
					toast.show();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
		}

	}
}
