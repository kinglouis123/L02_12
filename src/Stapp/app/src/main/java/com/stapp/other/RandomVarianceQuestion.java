package com.stapp.other;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by wenboma on 2017-11-25.
 */

public final class RandomVarianceQuestion implements MultipleChoice {


    SimpleRandomSampling means;
    NumberFormat formatter = new DecimalFormat("#0.0000");

    public RandomVarianceQuestion(int samplesize){
        this.means = new SimpleRandomSampling(samplesize);
    }

    @Override
    public String questiondescription() {
        return means.questiondescription() + "\n" + means.questionvariance();
    }

    @Override
    public String[] choices() {
        String options[] = {formatter.format(means.optionsvariance()[0]),
                formatter.format(means.optionsvariance()[1]),
                formatter.format(means.optionsvariance()[2]),
                formatter.format(means.optionsvariance()[3])};


        return options;
    }


    @Override
    public int correctanswerindex(){
        return means.correctanswer()[1];
    }

}
