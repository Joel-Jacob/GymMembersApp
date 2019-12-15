package com.example.gymmembersapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymmembersapp.Members.Members;
import com.example.gymmembersapp.R;

import java.util.List;

public class MemberRVAdapter extends RecyclerView.Adapter<MemberRVAdapter.CustomMemberHolder>{

    private List<Members> members;
    private MemberDelegate memberDelegate;

    @NonNull
    @Override
    public CustomMemberHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.member_item_layout, parent, false);

        return new CustomMemberHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomMemberHolder holder, final int position) {
        holder.memberName.setText(members.get(position).getMemberName());
        holder.memberPlan.setText(members.get(position).getPlan());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memberDelegate.memberSelected(members.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public interface MemberDelegate{

        void memberSelected(Members selectedMember);
    }

    public MemberRVAdapter(List<Members> members, MemberDelegate memberDelegate) {
        this.members = members;
        this.memberDelegate = memberDelegate;
    }

    class CustomMemberHolder extends RecyclerView.ViewHolder{
        public TextView memberName;
        public TextView memberPlan;

        public CustomMemberHolder(@NonNull View itemView) {
            super(itemView);

            memberName = itemView.findViewById(R.id.member_title_textview);
            memberPlan = itemView.findViewById(R.id.member_plan_textview);
        }
    }

}
