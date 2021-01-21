package com.example.lacuisine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setElevation(0);

        tabLayout = findViewById(R.id.tabmain);
        viewPager = findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabmain);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new PagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new FoodFragment(), "Food");
        adapter.AddFragment(new BeverageFragment(), "Drink");
        adapter.AddFragment(new DessertFragment(), "Dessert");
        adapter.AddFragment(new TambahFragment(), "Order");
        adapter.AddFragment(new HasilFragment(), "Final");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
