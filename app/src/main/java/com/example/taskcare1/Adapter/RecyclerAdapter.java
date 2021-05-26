package com.example.taskcare1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskcare1.Model.Task;
import com.example.taskcare1.databinding.ItemTaskBinding;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Task> list;
    private RecyclerAdapterCallback mAdapterCallback;
    private ItemTaskBinding binding;

    public RecyclerAdapter(Context context, List<Task> list, RecyclerAdapterCallback adapterCallback) {
        this.context = context;
        this.list = list;
        this.mAdapterCallback = adapterCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task item = list.get(position);
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        int size = this.list.size();
        this.list.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void addData(List<Task> tasks) {
        this.list = tasks;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(@NonNull ItemTaskBinding itemView) {
            super(itemView.getRoot());

            itemView.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Task task = list.get(getAdapterPosition());
                    mAdapterCallback.onEdit(task);
                    return true;
                }
            });
            binding.bDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Task task = list.get(getAdapterPosition());
                    mAdapterCallback.onDelete(task);
                }
            });
        }
        void bindData(Task item) {
            String tugas = item.tugas;
            binding.tvTugas.setText(tugas);

            String deskripsi = item.deskripsi;
            binding.tvDeskripsi.setText(deskripsi);

            String tenggat = item.tenggat;
            binding.tvTenggat.setText(tenggat);

            String waktu = item.waktu;
            binding.tvWaktu.setText(waktu);
        }
    }
    public interface RecyclerAdapterCallback {
        void onEdit(Task task);
        void onDelete(Task task);
    }
}

