package com.sharkawy.sittask.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.sharkawy.sittask.R;
import com.sharkawy.sittask.RecyclerItemClickListener;
import com.sharkawy.sittask.activities.DetailedItemActivity;
import com.sharkawy.sittask.adapters.CompanyAdapter;
import com.sharkawy.sittask.dataModel.CompanyModel;
import com.sharkawy.sittask.utilities.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompaniesFragment extends Fragment {
    private static String TAG = CompaniesFragment.class.getSimpleName();
    String URL ;
    RecyclerView recyclerView ;
    CompanyAdapter adapter ;
    RecyclerView.LayoutManager layoutManager ;
    ArrayList<CompanyModel> list = new ArrayList<>();
    SwipeRefreshLayout mSwipeRefreshLayout;
    Spinner filter ;
    String []array = {"All","VIP","Normal"};
    int Offset =11;
    int flag=0;
    String URL_VIP ="http://qbg-ws.smartinnovationtechnology.net/company.php?do=vip&limit=10&offset=";
    String URL_ALL="http://qbg-ws.smartinnovationtechnology.net/company.php?do=alllimit&limit=10&offset=";
    String URL_NORMAL ="http://qbg-ws.smartinnovationtechnology.net/company.php?do=normal&limit=10&offset=";
    public CompaniesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView;
        rootView =  inflater.inflate(R.layout.fragment_companies, container, false);

        filter = (Spinner) rootView.findViewById(R.id.filter);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_item, array);
        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        filter.setAdapter(dataAdapter);

        filter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(),"position "+position, Toast.LENGTH_SHORT).show();
                if(position==0){
                    Offset=1;
                    flag=0;
                    makeJsonObjectRequest(URL_ALL+0,true);

                }else if(position==1) {
                    Offset=1;
                    flag=1;
                    makeJsonObjectRequest(URL_VIP+0,true);

                }else if(position==2) {
                    Offset=1;
                    flag=2;
                    makeJsonObjectRequest(URL_NORMAL+0,true);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSwipeRefreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.company_swipe_refresh_layout);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.company_recyclerListview);
        layoutManager=new LinearLayoutManager(getActivity());
        adapter=new CompanyAdapter(getActivity(),list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                switch (flag){
                    case 0:
                        makeJsonObjectRequest(URL_ALL+Offset,false);
                        break;
                    case 1:
                        makeJsonObjectRequest(URL_VIP+Offset,false);
                        break;
                    case 2:
                        makeJsonObjectRequest(URL_NORMAL+Offset,false);
                        break;
                }
            }
        });
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Intent i = new Intent(getActivity(), DetailedItemActivity.class);
                        i.putExtra("Item",adapter.getItem(position).getObject().toString());
                        startActivity(i);
                    }

                })
        );
        return rootView;
    }

    private void makeJsonObjectRequest(String URL , final boolean clear) {

            mSwipeRefreshLayout.setRefreshing(true);

        JsonArrayRequest req = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        if(mSwipeRefreshLayout.isRefreshing()){
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                        if(clear){
                            list.clear();
                        }
                        for(int i =0 ;i<response.length();i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                list.add(0,new CompanyModel(object));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                        Offset+=10;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_SHORT).show();
                if(mSwipeRefreshLayout.isRefreshing()){
                    mSwipeRefreshLayout.setRefreshing(false);
                }

            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);

    }

}
