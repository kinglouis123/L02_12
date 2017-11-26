package com.stapp.other;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stapp.R;
//import com.stapp.school.String;

import java.util.ArrayList;
import java.util.List;

/** Created by Richard on 2017-11-05. */
public class RVStudentAdapter extends RecyclerView.Adapter<RVStudentAdapter.StudentViewHolder> {

  ArrayList<String> students;
  //
  private RecyclerViewClickListener mOnClickListener;

  public RVStudentAdapter(ArrayList<String> students, RecyclerViewClickListener listener) {
    this.students = students;
    mOnClickListener = listener;
  }

  @Override
  public int getItemCount() {
    return students.size();
  }

  @Override
  public StudentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    View v =
        LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.student_card, viewGroup, false);
    StudentViewHolder svh = new StudentViewHolder(v);
    return svh;
  }

  @Override
  public void onBindViewHolder(StudentViewHolder courseViewHolder, int i) {
    courseViewHolder.studentName.setText(students.get(i));
  }

  @Override
  public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    super.onAttachedToRecyclerView(recyclerView);
  }

  // onClick Listener code interface
  public interface RecyclerViewClickListener {
    void onListItemClick(int clickedPosition);
  }

  public class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView studentName;

    StudentViewHolder(View itemView) {
      super(itemView);
      studentName = (TextView) itemView.findViewById(R.id.student_card_name);

      itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      int clickedPosition = getAdapterPosition();
      mOnClickListener.onListItemClick(clickedPosition);
    }
  }
}
