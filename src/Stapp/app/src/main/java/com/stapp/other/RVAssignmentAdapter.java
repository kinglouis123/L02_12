package com.stapp.other;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stapp.R;
import com.stapp.school.Assignment;
import com.stapp.school.StudentSubmission;
import com.stapp.terminals.StudentSubmissionTerminal;

import java.util.List;

/**
 * Created by Richard on 2017-11-05.
 */

public class RVAssignmentAdapter extends RecyclerView.Adapter<RVAssignmentAdapter.AssignmentViewHolder> {

    private RecyclerViewClickListener mOnClickListener;

    public interface RecyclerViewClickListener {
         void onListItemClick(int clickedPosition);
    }

    public class AssignmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView assignmentTitle;
        TextView assignmentGrade;

        AssignmentViewHolder(View itemView){
            super(itemView);
            assignmentTitle = (TextView)itemView.findViewById(R.id.assignment_card_title);
            assignmentGrade = (TextView)itemView.findViewById(R.id.assignment_card_grade);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }


    }

    private List<Assignment> assignments;
    private String username;

    public RVAssignmentAdapter(List<Assignment> assignments, RecyclerViewClickListener listener) {
        this.assignments = assignments;
        mOnClickListener = listener;
    }

    public RVAssignmentAdapter(List<Assignment> assignments, String username, RecyclerViewClickListener listener) {
        this.assignments = assignments;
        this.username = username;
        mOnClickListener = listener;
    }

    @Override
    public  int getItemCount(){
        return assignments.size();
    }

    @Override
    public AssignmentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.assignment_card, viewGroup, false);
        AssignmentViewHolder avh = new AssignmentViewHolder(v);
        return avh;
    }

    @Override
    public void onBindViewHolder(AssignmentViewHolder assignementViewHolder, int i){
        assignementViewHolder.assignmentTitle.setText(assignments.get(i).getAssignmentName());
        StudentSubmission temp = StudentSubmissionTerminal.startNewSubmission(username, assignments.get(i).getId());
        assignementViewHolder.assignmentGrade.setText(temp.getCurrentMark());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }
}