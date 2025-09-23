package br.com.mbecker.drinkcount;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private final DrinkCategory[] categories = DrinkCategory.values();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        create();
    }

    private void create() {
        viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        viewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return DrinkFragment.newInstance(categories[position]);
            }

            @Override
            public int getItemCount() {
                return categories.length;
            }
        });

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) ->
                tab.setIcon(categories[position].getImageId())
        ).attach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }
    public void resetCountMenuClick(MenuItem item) {
        int temp = viewPager.getCurrentItem();
        DrinkUtil.saveCounter(0, categories[temp].name(), this);
        create();
        //Objects.requireNonNull(viewPager.getAdapter()).notifyItemChanged(temp);

        viewPager.setCurrentItem(temp);
    }

}
