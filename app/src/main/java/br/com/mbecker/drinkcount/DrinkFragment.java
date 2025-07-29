package br.com.mbecker.drinkcount;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DrinkFragment extends Fragment {
    private static final String ARG_CATEGORY = "category";
    private int counter = 0;

    public static DrinkFragment newInstance(DrinkCategory category) {
        DrinkFragment fragment = new DrinkFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, category.name());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drink, container, false);
        TextView counterText = view.findViewById(R.id.counterText);
        FloatingActionButton fab = view.findViewById(R.id.incrementButton);

        fab.setOnClickListener(v -> {
            counter++;
            counterText.setText(String.valueOf(counter));
        });

        return view;
    }
}
