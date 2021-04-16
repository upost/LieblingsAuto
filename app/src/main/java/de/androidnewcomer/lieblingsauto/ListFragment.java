package de.androidnewcomer.lieblingsauto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.androidnewcomer.lieblingsauto.databinding.FragmentListBinding;

public class ListFragment extends Fragment {

    private final List<Integer> cars;

    public ListFragment() {
        cars = Arrays.asList(R.mipmap.car1, R.mipmap.car2,
                R.mipmap.car3, R.mipmap.car4, R.mipmap.car5, R.mipmap.car6);
    }

    public static ListFragment newInstance() {
        return new ListFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentListBinding binding = FragmentListBinding.inflate(inflater);


        RecyclerView recyclerView = binding.cars;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new CarsAdapter(cars, getResources().getStringArray(R.array.car_names)));
        return binding.getRoot();
    }
}