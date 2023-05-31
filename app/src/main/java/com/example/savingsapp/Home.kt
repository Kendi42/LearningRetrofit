package com.example.savingsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class Home : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var  viewPager2: ViewPager2
    private lateinit var  adapter: FreagmentPageAdapter

    //private var layoutManager:RecyclerView.LayoutManager?= null
    //private var rAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        tabLayout= findViewById(R.id.tabLayout)
        viewPager2= findViewById(R.id.viewPager)
        adapter = FreagmentPageAdapter(supportFragmentManager, lifecycle)

        tabLayout.addTab(tabLayout.newTab().setText("Savings"))
        tabLayout.addTab(tabLayout.newTab().setText("Transactions"))
        tabLayout.setSelectedTabIndicator(getDrawable(R.drawable.tab_indicator));

        viewPager2.adapter = adapter

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab !=null){
                    viewPager2.currentItem= tab.position
                    if (tab.position == 0) {
                        tabLayout.setSelectedTabIndicator(getDrawable(R.drawable.tab_indicator));
                        Log.i("TAG", "Tab Position inside first if:" + tab.position )

                    }
                    else if (tab.position == tabLayout.getTabCount() - 1) {
                        tabLayout.setSelectedTabIndicator(getDrawable(R.drawable.tab_indicator_right));
                        Log.i("TAG", "Tab Position inside else if:" + tab.position )
                }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })



    }
}