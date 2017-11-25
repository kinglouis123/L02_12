package com.stapp.other;

/**
 * Created by wenboma on 2017-11-25.
 */

public final class RandomStdQuestion implements MultipleChoice {



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
        String options[] = {String.valueOf(stds.optionsstandarderror()[0]),
                String.valueOf(stds.optionsstandarderror()[1]),
                String.valueOf(stds.optionsstandarderror()[2]),
                String.valueOf(stds.optionsstandarderror()[3])};


        return options;
    }


    @Override
    public int correctanswerindex(){
        return stds.correctanswer()[2];
    }


}
