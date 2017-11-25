package com.stapp.other;

/**
 * Created by wenboma on 2017-11-25.
 */

public final class RandomVarianceQuestion implements MultipleChoice {


    SimpleRandomSampling means;

    public RandomVarianceQuestion(int samplesize){
        this.means = new SimpleRandomSampling(samplesize);
    }

    @Override
    public String questiondescription() {
        return means.questiondescription() + "\n" + means.questionvariance();
    }

    @Override
    public String[] choices() {
        String options[] = {String.valueOf(means.optionsvariance()[0]),
                String.valueOf(means.optionsvariance()[1]),
                String.valueOf(means.optionsvariance()[2]),
                String.valueOf(means.optionsvariance()[3])};


        return options;
    }


    @Override
    public int correctanswerindex(){
        return means.correctanswer()[1];
    }

}
