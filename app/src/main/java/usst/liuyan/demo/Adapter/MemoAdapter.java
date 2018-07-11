package usst.liuyan.demo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import usst.liuyan.demo.Entity.Memo;
import usst.liuyan.demo.R;

public class MemoAdapter extends  RecyclerView.Adapter<MemoAdapter.ViewHolder> {
    private Context mContext;
    private List<Memo>  memoList;

    public MemoAdapter(List<Memo> memoList){
        memoList = memoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.memo_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Memo memo = memoList.get(position);
        memo.setFullDisplayed(false);
        holder.memoText.setText(memo.getShortText());
        holder.memoDate.setText(memo.getDate());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return  memoList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView memoText;
        TextView memoDate;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            memoText = view.findViewById(R.id.memo_text);
            memoDate = view.findViewById(R.id.memo_text);
        }
    }

}
