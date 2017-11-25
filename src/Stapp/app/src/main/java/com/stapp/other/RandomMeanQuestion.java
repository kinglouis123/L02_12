package com.stapp.other;

import java.text.NumberFormat;
import java.text.DecimalFormat;

/**
 * Created by wenboma on 2017-11-25.
 */

public final class RandomMeanQuestion implements MultipleChoice {

    SimpleRandomSampling means;
    NumberFormat formatter = new DecimalFormat("#000.0000");

    public RandomMeanQuestion(int samplesize){
        this.means = new SimpleRandomSampling(samplesize);
    }

    @Override
    public String questiondescription() {
        return means.questiondescription() + "\n" + means.questionmean();
    }

    @Override
    public String[] choices() {
        String options[] = {formatter.format(means.optionsmean()[0]),
                formatter.format(means.optionsmean()[1]),
                formatter.format(means.optionsmean()[2]),
                formatter.format(means.optionsmean()[3])};


        return options;
    }

    @Override
    public int correctanswerindex(){
        return means.correctanswer()[0];
    }
}
