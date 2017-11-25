/**
 *
 */
package com.stapp.other;
import java.lang.Math;

/**
 * @author wenboma
 *
 */
public class SimpleRandomSampling {


    private double[] data = new double[]{169.9758971,164.1584357,167.1129628,163.3224322,162.3884354,166.4317846,166.3842043,162.5836214
            ,159.493642	,163.5504291,173.7240735,160.6875767,160.5244963,149.422895	,172.7328164,158.7481936,155.4791659
            ,159.0024371,156.0867137,167.885874	,161.0408151,165.2005134,158.1530531,166.7273679,151.0324377,158.5716342
            ,153.0640377,159.5367283,159.1542691,166.8768323,157.4825881,166.0492519,159.146616,158.2862685,165.4316692
            ,162.8359586,158.5905949,168.3016235,152.1625573,170.0540654,173.5969491,162.8963379,160.198864,157.1028056
            ,166.1888196,158.2351521,170.0709883,169.5831076,156.6797415,166.6073285};

    private double sample[];
    private int correct[] = new int[3];

    public SimpleRandomSampling(int samplesize){
        this.sample = extractsample(samplesize);
    }

    public String questiondescription(){
        String des = "Registrar office want to estimate the height of all females on campus. The total number of female is " + this.data.length
                + "." + "The sample of student has been selected with the following sample points: ";
        for (double point : this.sample) {
            des += Double.toString(point) + "\n";
        }
        return des;
    }

    public double[] samplepicked(){
        return this.sample;
    }

    public String questionmean(){
        return "What is the sample mean?";
    }

    public double[] optionsmean(){
        this.correct[0] = (int)(Math.random()*4);
        double options[] = new double[4];
        for (int i = 0; i<options.length;i++){
            if (i == correct[0]){
                options[i] = round(samplemean(),4);
            }else{
                options[i] = round(samplemean() + Math.round(((Math.random()*10) - 5)),4);
            }
        }
        return options;
    }

    public String questionvariance(){
        return "What is the sample variance?";
    }

    public double[] optionsvariance(){
        this.correct[1] = (int)(Math.random()*4);
        double options[] = new double[4];
        for (int i = 0; i<options.length;i++){
            if (i == correct[1]){
                options[i] = round(samplevariance(),4);
            }else{
                options[i] = round( samplevariance() + Math.round(((Math.random()*10) - 5) ),4);
            }
        }
        return options;
    }

    public String questionstandarderror(){
        return "What is the sample standard error?";
    }

    public double[] optionsstandarderror(){
        this.correct[2] = (int)(Math.random()*4);
        double options[] = new double[4];
        for (int i = 0; i<options.length;i++){
            if (i == correct[2]){
                options[i] = round(samplestandarderror(),4);
            }else{
                options[i] = round(samplestandarderror() + Math.round(((Math.random() * 10) - 5)),4);
            }
        }
        return options;
    }

    public int[] correctanswer(){
        return this.correct;
    }

    private double samplemean(){
        double total = 0.0;
        for (int i = 0;i<sample.length;i++){
            total += sample[i];
        }
        return total/sample.length;
    }


    private double samplevariance(){
        double mean = samplemean();
        double variance = 0.0;
        for (int i = 0; i < sample.length; i++){
            variance += Math.pow((sample[i] - mean),2);
        }
        return variance/(sample.length-1);
    }

    private double samplestandarderror(){
        return Math.sqrt(((1 - sample.length/data.length)*(samplevariance()/sample.length)));
    }



    public boolean elementexist(int[] ex, int index){
        for (int i = 0; i<ex.length;i++)
            if (ex[i] == index)
                return true;
        return false;
    }

    private double[] extractsample(int samplesize){
        double sampler[] = new double[samplesize];
        int exclude[] = new int[samplesize];
        for (int i = 0; i < samplesize;i++){
            int select = (int) (Math.random() * this.data.length);
            if (elementexist(exclude,select)){
                i--;
            }else{
                sampler[i] = round(this.data[select],4);
                exclude[i] = select;
            }
        }
        return sampler;
    }

    public void printsample(){
        for (int i = 0;i<sample.length; i++){
            System.out.println(sample[i]);
        }
    }


    public static double round(double value, int decimalplace) {

        int fac = (int) Math.pow(10, decimalplace);

        value = value * fac;

        int tmp = (int)Math.round(value);

        return (double) tmp / fac;
    }


}
