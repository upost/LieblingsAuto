package de.androidnewcomer.lieblingsauto;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.androidnewcomer.lieblingsauto.databinding.ItemCarSmallBinding;
import io.paperdb.Paper;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarViewHolder> {
    private final List<Integer> cars;
    private final List<Integer> rating;
    private final String[] carNames;

    public CarsAdapter(List<Integer> cars, String[] carNames) {
        this.cars = cars;
        rating = new ArrayList<>( Collections.nCopies(cars.size(),0) );
        this.carNames = carNames;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemCarSmallBinding itemCarBinding = ItemCarSmallBinding.inflate(layoutInflater,parent,false);
        return new CarViewHolder(itemCarBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        holder.bind(cars.get(position),carNames[position]);
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public class CarViewHolder extends RecyclerView.ViewHolder {
        @NonNull
        private final ItemCarSmallBinding binding;

        public CarViewHolder(@NonNull ItemCarSmallBinding  binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Integer carResId, String name) {
            binding.ratingBar.setRating(
                    Paper.book().read("rating_"+carResId,0.0f)
            );

            binding.carpic.setImageResource(carResId);
            binding.name.setText(name);
            binding.getRoot().setOnClickListener(v->{
                Message m = new Message();
                m.obj=name;
                m.arg1=carResId;
                MasterDetailActivity.handler.sendMessage(m);
            });
        }
    }
}
