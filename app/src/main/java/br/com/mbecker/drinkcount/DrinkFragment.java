package br.com.mbecker.drinkcount;

import android.content.Context;
import android.content.SharedPreferences;
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
        counterText = view.findViewById(R.id.counterText);
        FloatingActionButton fab = view.findViewById(R.id.incrementButton);

        // Identifica a categoria e carrega contador salvo
        category = DrinkCategory.valueOf(getArguments().getString(ARG_CATEGORY));
        counter = loadCounter(); // Carrega contador salvo
        counterText.setText(String.valueOf(counter));

        // Ao clicar, incrementa e salva
        fab.setOnClickListener(v -> {
            counter++;
            counterText.setText(String.valueOf(counter));
            saveCounter(counter);
        });

        return view;
    }

    private void saveCounter(int value) {
        SharedPreferences prefs = requireActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("counter_" + category.name(), value);
        editor.apply();
    }

    private int loadCounter() {
        SharedPreferences prefs = requireActivity().getPreferences(Context.MODE_PRIVATE);
        return prefs.getInt("counter_" + category.name(), 0);
    }
}
