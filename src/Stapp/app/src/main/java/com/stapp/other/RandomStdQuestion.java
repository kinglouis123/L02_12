package com.stapp.other;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by wenboma on 2017-11-25.
 */

public final class RandomStdQuestion implements MultipleChoice {

    NumberFormat formatter = new DecimalFormat("#0.0000");


    SimpleRandomSampling stds;

    public RandomStdQuestion(int samplesize){
        this.stds = new SimpleRandomSampling(samplesize);
    }

    @Override
    public String questiondescription() {
        return stds.questiondescription() + "\n" + stds.questionstandarderror();
    }

    @Override
    public String[] choices() {
        String options[] = {formatter.format(stds.optionsstandarderror()[0]),
                formatter.format(stds.optionsstandarderror()[1]),
                formatter.format(stds.optionsstandarderror()[2]),
                formatter.format(stds.optionsstandarderror()[3])};

        return options;
    }


    @Override
    public int correctanswerindex(){
        return stds.correctanswer()[2];
    }


}
