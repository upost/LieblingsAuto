package de.androidnewcomer.lieblingsauto;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import de.androidnewcomer.lieblingsauto.databinding.ActivityMasterdetailBinding;
import io.paperdb.Paper;

public class MasterDetailActivity extends AppCompatActivity {



    public static Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Paper.init(this);

        ActivityMasterdetailBinding binding
                = ActivityMasterdetailBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        if( getSupportFragmentManager().getFragments().isEmpty()) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.master, ListFragment.newInstance() )
                    .commit();
            if(binding.detail !=null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.detail, DetailFragment.newInstance() )
                        .commit();
            }
        }

        handler = new Handler(getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                int target = R.id.master;
                if(binding.detail !=null) target = R.id.detail;
                getSupportFragmentManager().beginTransaction()
                        .replace(target, DetailFragment.newInstance( msg.arg1, (String) msg.obj))
                        .addToBackStack(null)
                        .commit();
            }
        };


    }
}