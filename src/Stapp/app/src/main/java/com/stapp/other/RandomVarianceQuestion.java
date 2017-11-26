package com.stapp.other;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by wenboma on 2017-11-25.
 */

public final class RandomVarianceQuestion implements MultipleChoice {


    SimpleRandomSampling vari;
    NumberFormat formatter = new DecimalFormat("#0.0000");

    public RandomVarianceQuestion(int samplesize){
        this.vari = new SimpleRandomSampling(samplesize);
    }

    @Override
    public String questiondescription() {
        return vari.questiondescription() + "\n" + vari.questionvariance();
    }

    @Override
    public String[] choices() {
        String options[] = {formatter.format(vari.optionsvariance()[0]),
                formatter.format(vari.optionsvariance()[1]),
                formatter.format(vari.optionsvariance()[2]),
                formatter.format(vari.optionsvariance()[3])};


        return options;
    }


    @Override
    public int correctanswerindex(){
        return vari.correctanswer()[1];
    }

}
