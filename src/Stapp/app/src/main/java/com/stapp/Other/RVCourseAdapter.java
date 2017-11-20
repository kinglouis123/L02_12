package com.stapp.Other;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stapp.R;
import com.stapp.school.Course;

import java.util.List;

/**
 * Created by Richard on 2017-11-05.
 */

public class RVCourseAdapter extends RecyclerView.Adapter<RVCourseAdapter.CourseViewHolder> {

    public static class CourseViewHolder extends RecyclerView.ViewHolder{
        TextView courseCode;

        CourseViewHolder(View itemView){
            super(itemView);
            courseCode = (TextView)itemView.findViewById(R.id.course_code);

        }


    }

    List<Course> courses;

    public RVCourseAdapter(List<Course> courses){
        this.courses = courses;
    }

    @Override
    public  int getItemCount(){
        return courses.size();
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.course_card, viewGroup, false);
        CourseViewHolder cvh = new CourseViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(CourseViewHolder courseViewHolder, int i){
        courseViewHolder.courseCode.setText(courses.get(i).toString());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }
}