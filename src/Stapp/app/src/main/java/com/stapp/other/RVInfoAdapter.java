package com.stapp.other;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stapp.R;
import com.stapp.school.StudentSubmission;

import java.util.List;

/** Created by Richard on 2017-11-05. */
public class RVInfoAdapter extends RecyclerView.Adapter<RVInfoAdapter.InfoViewHolder> {

  List<StudentSubmission> submissions;
  //
  private RecyclerViewClickListener mOnClickListener;

  public RVInfoAdapter(List<StudentSubmission> studentSubmissions) {
    this.submissions = studentSubmissions;
  }

  @Override
  public int getItemCount() {
    return submissions.size();
  }

  @Override
  public InfoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    View v =
        LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.info_card, viewGroup, false);
    InfoViewHolder ivh = new InfoViewHolder(v);
    return ivh;
  }

  @Override
  public void onBindViewHolder(InfoViewHolder infoViewHolder, int i) {
    infoViewHolder.assignment_name.setText(submissions.get(i).toString());
    infoViewHolder.assignment_grade.setText(submissions.get(i).getCurrentMark());
  }

  @Override
  public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    super.onAttachedToRecyclerView(recyclerView);
  }

  // onClick Listener code interface
  public interface RecyclerViewClickListener {
    void onListItemClick(int clickedPosition);
  }

  public class InfoViewHolder extends RecyclerView.ViewHolder {
    TextView assignment_name;
    TextView assignment_grade;

    InfoViewHolder(View itemView) {
      super(itemView);
      assignment_name = (TextView) itemView.findViewById(R.id.assignment_name);
      assignment_grade = (TextView) itemView.findViewById(R.id.assignment_grade);
    }

  }
}
