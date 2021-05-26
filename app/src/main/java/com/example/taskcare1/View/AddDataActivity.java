package com.example.taskcare1.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.taskcare1.Model.Task;
import com.example.taskcare1.R;
import com.example.taskcare1.databinding.ActivityAddDataBinding;

public class AddDataActivity extends AppCompatActivity {

    private static String KEY_IS_EDIT = "key_is_edit";
    private static String KEY_DATA = "key_data";

    // Untuk kebutuhan data yang akan dipakai pada Activitu AddData
    public static void startActivity(Context context, boolean isEdit, Task task) {
        Intent intent = new Intent(new Intent(context, AddDataActivity.class));
        intent.putExtra(KEY_IS_EDIT, isEdit);
        intent.putExtra(KEY_DATA, task);
        context.startActivity(intent);
    }

    private ActivityAddDataBinding binding;
    private AddDataViewModel addDataViewModel;

    private boolean mIsEdit = false;
    private int mNmr = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addDataViewModel = ViewModelProviders.of(this).get(AddDataViewModel.class);

        loadData();
        initAction();
    }
    private void initAction() {
        binding.btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tugas = binding.eTugas.getText().toString();
                String deskripsi = binding.eDeskripsi.getText().toString();
                String tenggat = binding.eTenggat.getText().toString();
                String waktu = binding.eWaktu.getText().toString();

                if (tenggat.isEmpty() || waktu.isEmpty()) {
                    Toast.makeText(AddDataActivity.this, getString(R.string.error_message_form_empty),
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (mIsEdit) {
                        addDataViewModel.updateTugas(mNmr, tenggat, waktu);
                    } else {
                        addDataViewModel.addTugas(tugas, deskripsi,tenggat, waktu);
                    }
                    finish();
                }
            }
        });
    }
    private void loadData() {
        mIsEdit = getIntent().getBooleanExtra(KEY_IS_EDIT, false);
        if (mIsEdit) {
            Task task = getIntent().getParcelableExtra(KEY_DATA);
            if (task != null) {
                mNmr = task.nmr;
                String tugas = task.tugas;
                String deskripsi = task.deskripsi;
                String tenggat = task.tenggat;
                String waktu = task.waktu;

                binding.eTugas.setText(tugas);
                binding.eDeskripsi.setText(deskripsi);
                binding.eTenggat.setText(tenggat);
                binding.eWaktu.setText(waktu);
            }
        }
    }
}