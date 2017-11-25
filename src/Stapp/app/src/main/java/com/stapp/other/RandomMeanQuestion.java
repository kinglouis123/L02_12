package com.stapp.other;



/**
 * Created by wenboma on 2017-11-25.
 */

public final class RandomMeanQuestion implements MultipleChoice {

    SimpleRandomSampling means;

    public RandomMeanQuestion(int samplesize){
        this.means = new SimpleRandomSampling(samplesize);
    }

    @Override
    public String questiondescription() {
        return means.questiondescription() + "\n" + means.questionmean();
    }

    @Override
    public String[] choices() {
        String options[] = {String.valueOf(means.optionsmean()[0]),
                String.valueOf(means.optionsmean()[1]),
                String.valueOf(means.optionsmean()[2]),
                String.valueOf(means.optionsmean()[3])};


        return options;
    }

    @Override
    public int correctanswerindex(){
        return means.correctanswer()[0];
    }
}
