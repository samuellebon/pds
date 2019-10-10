package kan10.interfaces;

/**
 * Service interface class to manage statistics service
 * @author suriya
 */
public interface IStatisticsService {

    // avg
    public float getAverage (float[] serie);
    public float getAverage (int[] serie);
    public float getAverage (String[] serie);
    public double getAverage(double [] series);

    // variance
    public double getVariance (double[] serie);
    public double getVariance(int[] series);
    public double getVariance(String[] series);
    public double getVariance(float[] series);

    // standard deviation
    public double getStandardDeviation(double [] series);
    public double getStandardDeviation(int [] series);
    public double getStandardDeviation(String [] series);
}
