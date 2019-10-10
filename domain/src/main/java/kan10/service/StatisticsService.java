package kan10.service;

import kan10.interfaces.IStatisticsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Statistics Service for manage statistics
 * Contains many functions about statistics
 * @author suriya, mouad
 */
@Service
public class StatisticsService implements IStatisticsService {

    /**
     * Average on a serie
     * @param serie
     * @return average
     */
    @Override
    public float getAverage (float[] serie) {
        float total = 0 ;
        for(float s : serie)
        {
            total += s ;
        }
        return total/serie.length;
    }

    /**
     * Average for nb client connected and not connected
     * @param serie
     * @return average
     */
    @Override
    public float getAverage (int[] serie) {
        float total = 0 ;
        for(float s : serie)
        {
            total += s ;
        }
        return total/serie.length;
    }


    /**
     * Average for percent of client connected and not connected
     * @param serie
     * @return average
     */
    @Override
    public float getAverage (String[] serie) {
        float total = 0 ;
        for(String s : serie)
        {
            total += Float.parseFloat(s.replaceAll(",",".")) ;
        }
        return total/serie.length;
    }

    /**
     * Average for variance and standard deviation
     * @param serie
     * @return
     */
    @Override
    public double getAverage(double [] serie) {
        double total = 0 ;
        for(double s : serie)
        {
            total += s ;
        }
        return total / serie.length ;
    }

    /**
     * Variance
     * @param series
     * @return
     */
    @Override
    public double getVariance(double[] series) {
        double total = 0 ;
        double avg = this.getAverage(series);
        for(double s : series)
        {
            total += (s - avg)*(s - avg) ;
        }
        return total / series.length ;
    }

    /**
     * Variance
     * @param series
     * @return
     */
    @Override
    public double getVariance(float[] series) {
        double total = 0 ;
        double avg = this.getAverage(series);
        for(double s : series)
        {
            total += (s - avg)*(s - avg) ;
        }
        return total / series.length ;
    }

    /**
     * Variance for nb client connected, not connected and by month
     * @param series
     * @return
     */
    @Override
    public double getVariance(int[] series) {
        double total = 0 ;
        for(double s : series)
        {
            total += (s - this.getAverage(series))*(s - this.getAverage(series)) ;
        }
        return total / series.length ;
    }


    /**
     * Variance for percent of client connected and not connected
     * @param series
     * @return
     */
    @Override
    public double getVariance (String[] series) {
        double total = 0 ;
        double avg = this.getAverage(series) ;
        for(String s : series)
        {
            total += (Float.parseFloat(s.replace(',', '.')) - avg)*(Float.parseFloat(s.replace(',', '.')) - avg) ;
        }
        return total / series.length ;
    }

    /**
     * Standard Deviation
     * @param series
     * @return
     */
    @Override
    public double getStandardDeviation(double [] series)
    {
        return Math.sqrt(this.getVariance(series));
    }

    /**
     * Standard deviation for nb client connected, not connected and by month
     * @param series
     * @return
     */
    @Override
    public double getStandardDeviation(int [] series)
    {
        return Math.sqrt(this.getVariance(series));
    }

    /**
     * Standard deviation for percent of client connected and not connected
     * @param series
     * @return
     */
    @Override
    public double getStandardDeviation(String [] series)
    {
        return Math.sqrt(this.getVariance(series));
    }

    /**
     * @param serie
     * @return
     */
    //@Override
    public double getOutlier(double [] serie)
    {   double data = 0;
        for(double s : serie)
        {
            if(s>this.getAverage(serie)+(2*this.getStandardDeviation(serie)) ||
                    s<this.getAverage(serie)-(2*this.getStandardDeviation(serie))) {
                return data = s;
            }
        }
        return data;
    }

    /**
     * Outlier for nb client connected, not connected and by month
     * @param serie
     * @return
     */
    // @Override
    public int getOutlier(int [] serie)
    {   int data = 0;
        for(int s : serie)
        {
            if(s>this.getAverage(serie)+(2*this.getStandardDeviation(serie)) ||
                    s<this.getAverage(serie)-(2*this.getStandardDeviation(serie)))
            {
                return data = s;
            }
        }
        return data;
    }


    // @Override
   /* public ArrayList<Double> getOutlier(double [] serie)
    {   ArrayList<Double> data = new ArrayList<Double>();
        for(double s : serie)
        {
            if(s>this.getAverage(serie)+(2*this.getStandardDeviation(serie)) ||
                    s<this.getAverage(serie)-(2*this.getStandardDeviation(serie)))
            {
                data.add(s);
            }
        }
        return data;
    }*/

    /**
     * Outlier for percent of client connected and not connected
     * @param serie
     * @return
     */
    // @Override
    public double getOutlier(String [] serie)
    {   double data = 0;
        for(String s : serie)
        {
            if(Float.parseFloat(s.replaceAll(",","."))>this.getAverage(serie)+(2*this.getStandardDeviation(serie)) ||
                    Float.parseFloat(s.replaceAll(",","."))<this.getAverage(serie)-(2*this.getStandardDeviation(serie)))
            {
                 return data = Float.parseFloat(s.replaceAll(",","."));
            }
        }
        return data;
    }

}
