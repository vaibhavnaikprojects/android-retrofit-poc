package com.kushalnawlakhaprojects.buyit.activities;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.kushalnawlakhaprojects.buyit.adapters.ProductAdapter;
import com.kushalnawlakhaprojects.buyit.model.Product;
import com.kushalnawlakhaprojects.buyit.model.ProductResponse;
import com.kushalnawlakhaprojects.buyit.service.ProductsAPIService;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ProductActivity extends AppCompatActivity {
    private ProgressDialog pDialog;
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> products;
    private SearchView searchView;
    private ProductsAPIService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.zappos.com/Search")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ProductsAPIService.class);
        setView("");
    }

    public void setView(String query){
        showpDialog();
        Call<ProductResponse> productsCall = service.getProductResponse(query);
        productsCall.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Response<ProductResponse> response, Retrofit retrofit) {
                ProductResponse productResponse = response.body();
                products=productResponse.getResults();
                recyclerView=(RecyclerView) findViewById(R.id.product_card_view);
                StaggeredGridLayoutManager mStaggeredLayoutManager=new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                hidepDialog();
                productAdapter= new ProductAdapter(getBaseContext(), products);
                recyclerView.setLayoutManager(mStaggeredLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(productAdapter);
                productAdapter.setOnItemClickListener(onItemClickListener);
            }
            @Override
            public void onFailure(Throwable t) {
                Log.d("onFailure", t.toString());
                hidepDialog();
            }
        });
    }

    ProductAdapter.OnItemClickListener onItemClickListener=new ProductAdapter.OnItemClickListener(){
        @Override
        public void onItemClick(View view, int position) {
            Intent intent= new Intent(getBaseContext(),ProductDetailActivity.class);
            intent.putExtra("product",products.get(position));
            startActivity(intent);
        }
    };
    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        SearchManager searchManager =(SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =(SearchView) menu.findItem(R.id.product_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                setView(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setQueryHint("Search your product");
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                setView("");
                return false;
            }
        });
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.product_search:
                Toast.makeText(this, "Product Search", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}
