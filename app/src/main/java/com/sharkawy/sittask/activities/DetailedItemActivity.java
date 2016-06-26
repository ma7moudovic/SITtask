package com.sharkawy.sittask.activities;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.linearlistview.LinearListView;
import com.sharkawy.sittask.R;
import com.sharkawy.sittask.adapters.GallaryAdapter;
import com.sharkawy.sittask.dataModel.CompanyModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class DetailedItemActivity extends AppCompatActivity {

    TextView company_name ;
    EditText company_address ,
            company_postalCode , company_telephone ,
            company_fax ,company_email,company_website,
            company_fb,company_twitter ,company_linkedIn ,company_google ;

    ImageView logo ;
    Button getDirection ;
    private LinearListView mTrailersView , mProducts;
    GallaryAdapter gallaryAdapter ,gallaryProductsAdapter;
    ArrayList<String> imagesURLs , productsURLs;

    double latitude , longitude ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        company_name = (TextView)findViewById(R.id.Detailed_company_name);
        company_address = (EditText) findViewById(R.id.company_address);
        company_email = (EditText) findViewById(R.id.company_email);
        company_fax = (EditText) findViewById(R.id.company_fax);
        company_postalCode = (EditText) findViewById(R.id.company_postalCode);
        company_telephone = (EditText) findViewById(R.id.company_telephone);
        company_website = (EditText) findViewById(R.id.company_website);
        company_fb = (EditText) findViewById(R.id.company_fb);
        company_twitter = (EditText) findViewById(R.id.company_twitter);
        company_linkedIn = (EditText) findViewById(R.id.company_linkedIn);
        company_google = (EditText) findViewById(R.id.company_google);

        logo = (ImageView) findViewById(R.id.Detailed_profile);
        getDirection = (Button) findViewById(R.id.company_getDirection);

        mTrailersView = (LinearListView) findViewById(R.id.detailed_images_gallary);
        imagesURLs = new ArrayList<String>() ;
        gallaryAdapter = new GallaryAdapter(this,imagesURLs );
        mTrailersView.setAdapter(gallaryAdapter);


        mProducts = (LinearListView) findViewById(R.id.detailed_product_gallary);
        productsURLs = new ArrayList<String>() ;
        gallaryProductsAdapter = new GallaryAdapter(this,productsURLs );
        mProducts.setAdapter(gallaryProductsAdapter);


        try {
            if(getIntent()!=null||getIntent().getExtras()!=null){
                JSONObject jsonObject = new JSONObject(getIntent().getExtras().getString("Item"));
                CompanyModel companyModel = new CompanyModel(jsonObject);

                company_name.setText(companyModel.getNameen());
                company_address.setText(companyModel.getAddressen());
                company_fb.setText(companyModel.getFacebook());
                company_twitter.setText(companyModel.getTwitter());
                company_linkedIn.setText(companyModel.getLinkedin());
                company_email.setText(companyModel.getEmail());
                company_website.setText(companyModel.getWebsite());
                company_telephone.setText(companyModel.getPhone());
                company_fax.setText(companyModel.getFax());
                company_google.setText(companyModel.getGoogelplus());
                company_postalCode.setText(companyModel.getPostalcode());

                Glide.with(this)
                        .load(companyModel.getImagename())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(logo);

                JSONArray jsonArray = companyModel.getImages();
                if(jsonArray.length()==0){
                    mTrailersView.setVisibility(View.GONE);
                }
                for(int i = 0 ; i <jsonArray.length();i++){
//                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    imagesURLs.add(jsonArray.getJSONObject(i).getString("name"));
                }
                gallaryAdapter.notifyDataSetChanged();

                //PRoducts
                JSONArray productsArray = companyModel.getProducts();
                if(productsArray.length()==0){
                    mProducts.setVisibility(View.GONE);
                }
                for(int i = 0 ; i <productsArray.length();i++){
//                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    productsURLs.add(productsArray.getJSONObject(i).getString("name"));
                }
                gallaryProductsAdapter.notifyDataSetChanged();


                if(companyModel.getLongitude()!=null||companyModel.getLatitude()!=null){
                    longitude = Double.parseDouble(companyModel.getLongitude());
                    latitude = Double.parseDouble(companyModel.getLatitude());
                }


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        getDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(latitude!=0&&longitude!=0){
                    //?q=<lat>,<long>(Label+Name)
                    String uri = String.format(Locale.ENGLISH, "geo:"+latitude+","+longitude+"?q="+latitude+","+longitude+"(Label+Name)");
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//                    intent.setPackage("com.google.android.apps.maps");
                    startActivity(intent);
                }
            }
        });
        company_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(newFacebookIntent(getPackageManager(),company_fb.getText().toString()));
            }
        });


    }
    public static Intent newFacebookIntent(PackageManager pm, String url) {
        Uri uri = Uri.parse(url);
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {
                // http://stackoverflow.com/a/24547437/1048340
                uri = Uri.parse("fb://facewebmodal/f?href=" + url);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }
}
