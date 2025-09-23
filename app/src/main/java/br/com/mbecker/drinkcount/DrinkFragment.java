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
    private DrinkCategory category;
    private TextView counterText;

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
        TextView nameText = view.findViewById(R.id.nameText);
        counterText = view.findViewById(R.id.counterText);

        FloatingActionButton fab = view.findViewById(R.id.incrementButton);

        category = DrinkCategory.valueOf(getArguments().getString(ARG_CATEGORY));
        counter = loadCounter();
        nameText.setText(String.valueOf(category));
        counterText.setText(String.valueOf(counter));

        fab.setOnClickListener(v -> {
            ++counter;
            counterText.setText(String.valueOf(counter));
            saveCounter(counter);
        });

        return view;
    }

    public void resetCounter() {
        counter = 0;
        counterText.setText(String.valueOf(counter));
    }

    private void saveCounter(int value) {
        DrinkUtil.saveCounter(value, category.name(), requireActivity());
    }

    private int loadCounter() {
        return DrinkUtil.loadCounter(category.name(), requireActivity());
    }
}
