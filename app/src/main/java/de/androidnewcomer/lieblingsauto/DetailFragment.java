package de.androidnewcomer.lieblingsauto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import de.androidnewcomer.lieblingsauto.databinding.FragmentDetailBinding;
import io.paperdb.Paper;

public class DetailFragment extends Fragment {

    private Integer carResId;
    private String carName;

    public DetailFragment() {

    }

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    public static DetailFragment newInstance(Integer carResId, String carName) {
        DetailFragment f = newInstance();
        f.carResId = carResId;
        f.carName = carName;
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentDetailBinding binding = FragmentDetailBinding.inflate(inflater);
        if(carResId!=null) {
            binding.carpic.setImageResource(carResId);
            binding.name.setText(carName);
            binding.rating.setRating(Paper.book().read("rating_"+carResId, 0.0f));
            binding.rating.setOnRatingBarChangeListener(
                    (ratingBar, rating1, fromUser) -> {
                        Paper.book().write("rating_"+carResId,rating1);
                    });
        }

        return binding.getRoot();
    }
}