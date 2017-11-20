package com.stapp.Other;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stapp.R;
import com.stapp.school.Assignment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richard on 2017-11-05.
 */

public class RVAssignmentAdapter extends RecyclerView.Adapter<RVAssignmentAdapter.AssignmentViewHolder> {

    public static class AssignmentViewHolder extends RecyclerView.ViewHolder{
        TextView assignmentTitle;
        TextView assignmentGrade;

        AssignmentViewHolder(View itemView){
            super(itemView);
            assignmentTitle = (TextView)itemView.findViewById(R.id.assignment_card_title);
            assignmentGrade = (TextView)itemView.findViewById(R.id.assignment_card_grade);

        }


    }

    List<Assignment> assignments = new ArrayList<>();

    public RVAssignmentAdapter(List<Assignment> assignments){
        this.assignments = assignments;
    }

    @Override
    public  int getItemCount(){
        return assignments.size();
    }

    @Override
    public AssignmentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.course_card, viewGroup, false);
        AssignmentViewHolder avh = new AssignmentViewHolder(v);
        return avh;
    }

    @Override
    public void onBindViewHolder(AssignmentViewHolder assignementViewHolder, int i){
        assignementViewHolder.assignmentTitle.setText(assignments.get(i).getAssignmentName());
        assignementViewHolder.assignmentGrade.setText("0/0");
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }
}