package kan10.controller;

import kan10.interfaces.StoreService;
import kan10.service.ClientService;
import kan10.service.StatisticsService;
import kan10.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.stream.IntStream;

/**
 * Controller : manage statistics data row, outliers and adjusted data
 * @author suriya, mouad
 */
@Controller
public class StatisticsController {

    @Autowired
    VisitService visitService;

    @Autowired
    ClientService clientService;

    @Autowired
    StoreService storeService;

    @Autowired
    StatisticsService statisticsService;


    /**
     * Homepage for statistics
     * @return statistics-manage-view
     */
    @GetMapping(value = "/statisticsmanage")
    public String statistics(){
        return "statistics-manage-view";
    }


    /**
     * Attendance's data row
     * @param model
     * @return
     */
    @GetMapping(value = "/statisticsdatarow")
    public String statisticsDataRow (Model model) throws ParseException {

        // Some variables
        int [] nb_connected_by_month = new int[12];
        int [] nb_not_connected_by_month = new int[12];
        int [] nb_by_month = new int[12];
        String [] percent_connected_by_month = new String[12];
        String [] percent_not_connected_by_month = new String[12];
        String primarytitle = "Data row for attendance";
        String secondarytitle = "Data row for Mall (2018)";
        String subtitle = "In this page, you have the possibility to consult the statistics about attendance of mall and stores with many criteria at your disposal.";

        String[] month_names = {"January","February","March","April","May","June","July","August","September","October","November","December"};

        // Computing stat for each month
        for(int i = 0; i < 12; i++)
        {
            // Start Date
            Calendar calStart = GregorianCalendar.getInstance();
            calStart.set(Calendar.DAY_OF_MONTH, 1);// I might have the wrong Calendar constant...
            calStart.set(Calendar.MONTH, i);// -1 as month is zero-based
            calStart.set(Calendar.YEAR, 2018);
            calStart.set(Calendar.HOUR_OF_DAY, 0);            // set hour to midnight
            calStart.set(Calendar.MINUTE, 0);                 // set minute in hour
            calStart.set(Calendar.SECOND, 0);                 // set second in minute
            calStart.set(Calendar.MILLISECOND, 0);
            Timestamp tStartDate = new Timestamp(calStart.getTimeInMillis());

            // End Date
            Calendar calEnd = GregorianCalendar.getInstance();
            calEnd.set(Calendar.DAY_OF_MONTH, 31);// I might have the wrong Calendar constant...
            calEnd.set(Calendar.MONTH, i);// -1 as month is zero-based
            calEnd.set(Calendar.YEAR, 2018);
            calEnd.set(Calendar.HOUR_OF_DAY, 0);            // set hour to midnight
            calEnd.set(Calendar.MINUTE, 0);                 // set minute in hour
            calEnd.set(Calendar.SECOND, 0);                 // set second in minute
            calEnd.set(Calendar.MILLISECOND, 0);
            Timestamp tEndDate = new Timestamp(calEnd.getTimeInMillis());

            // Attendance counters
            nb_connected_by_month[i] = visitService.getNumberAttendanceConnectedClientWithPeriod(tStartDate, tEndDate);
            nb_not_connected_by_month[i] = visitService.getNumberAttendanceDiconnectedClientWithPeriod(tStartDate, tEndDate);
            nb_by_month[i] = visitService.getNumberAttendanceWithPeriod(tStartDate, tEndDate);

            // Percentages
            if(nb_by_month[i] != 0 )
            {
                percent_connected_by_month[i] = my_round((float)nb_connected_by_month[i]/nb_by_month[i] * 100);
                percent_not_connected_by_month[i] = my_round((float)nb_not_connected_by_month[i]/nb_by_month[i] * 100) ;
            }
            else
            {
                percent_connected_by_month[i] = my_round((float)0) ;
                percent_not_connected_by_month[i] = my_round((float)0) ;
            }
            System.out.println(tStartDate + " => " + tEndDate + " : " + nb_by_month[i]);
        }

        System.out.println(nb_connected_by_month[0]);

        // Sum months data to get full year data
        int nb_by_year_co = IntStream.of(nb_connected_by_month).sum();
        int nb_by_year_notco = IntStream.of(nb_not_connected_by_month).sum();
        int nb_by_year = IntStream.of(nb_by_month).sum();
        String percent_connected_by_year = my_round((float)nb_by_year_co/nb_by_year*100);
        String percent_not_connected_by_year = my_round((float)nb_by_year_notco/nb_by_year*100);

        // Average
        float avg_nb_co = statisticsService.getAverage(nb_connected_by_month);
        float avg_nb_notco = statisticsService.getAverage(nb_not_connected_by_month);
        float avg_nb_total = statisticsService.getAverage(nb_by_month);
        float avg_per_co = statisticsService.getAverage(percent_connected_by_month);
        float avg_per_notco = statisticsService.getAverage(percent_not_connected_by_month);

        // Variance
        double variance_nb_co = statisticsService.getVariance(nb_connected_by_month);
        double variance_nb_notco = statisticsService.getVariance(nb_not_connected_by_month);
        double variance_nb_total = statisticsService.getVariance(nb_by_month);
        double variance_per_co = statisticsService.getVariance(percent_connected_by_month);
        double variance_per_notco = statisticsService.getVariance(percent_not_connected_by_month);

        // Standard deviation
        double sigma_nb_co = statisticsService.getStandardDeviation(nb_connected_by_month);
        double sigma_nb_notco = statisticsService.getStandardDeviation(nb_not_connected_by_month);
        double sigma_nb_total = statisticsService.getStandardDeviation(nb_by_month);
        double sigma_per_co = statisticsService.getStandardDeviation(percent_connected_by_month);
        double sigma_per_notco = statisticsService.getStandardDeviation(percent_not_connected_by_month);


        // Passing data to view
        // title, subtitle
        model.addAttribute("primarytitle", primarytitle);
        model.addAttribute("secondarytitle", secondarytitle);
        model.addAttribute("subtitle", subtitle);
        // store dropdown
        model.addAttribute("allstores", storeService.getAllStores());
        // month names
        model.addAttribute("month_names", month_names);
        // count
        model.addAttribute("statsbymonth", nb_by_month);
        model.addAttribute("statsbymonth_co", nb_connected_by_month);
        model.addAttribute("statsbymonth_notco", nb_not_connected_by_month);
        model.addAttribute("statsbyyear", nb_by_year);
        model.addAttribute("statsbyyear_co", nb_by_year_co);
        model.addAttribute("statsbyyear_notco", nb_by_year_notco);
        // percent
        model.addAttribute("percentbymonth_co", percent_connected_by_month);
        model.addAttribute("percentbymonth_notco", percent_not_connected_by_month);
        model.addAttribute("percentbyyear_co", percent_connected_by_year);
        model.addAttribute("percentbyyear_notco", percent_not_connected_by_year);
        // avg
        model.addAttribute("avg_nb_co", my_round(avg_nb_co));
        model.addAttribute("avg_nb_notco", my_round(avg_nb_notco));
        model.addAttribute("avg_per_co", my_round(avg_per_co));
        model.addAttribute("avg_per_notco", my_round(avg_per_notco));
        model.addAttribute("avg_nb_total", my_round(avg_nb_total));
        // variance
        model.addAttribute("variance_nb_co", my_round(variance_nb_co));
        model.addAttribute("variance_nb_notco", my_round(variance_nb_notco));
        model.addAttribute("variance_per_co", my_round(variance_per_co));
        model.addAttribute("variance_per_notco", my_round(variance_per_notco));
        model.addAttribute("variance_nb_total", my_round(variance_nb_total));
        //standard deviation
        model.addAttribute("sigma_nb_co", my_round(sigma_nb_co));
        model.addAttribute("sigma_nb_notco", my_round(sigma_nb_notco));
        model.addAttribute("sigma_per_co", my_round(sigma_per_co));
        model.addAttribute("sigma_per_notco", my_round(sigma_per_notco));
        model.addAttribute("sigma_nb_total", my_round(sigma_nb_total));

        return "statistics-datarow-view";
    }

    /**
     * Attendance's outliers
     * @param model
     * @return
     */
    @GetMapping(value = "/statisticsoutliers")
    public String statisticsOutliers (Model model) throws ParseException {

        // Some variables
        int [] nb_connected_by_month = new int[12];
        int [] nb_not_connected_by_month = new int[12];
        int [] nb_by_month = new int[12];
        String [] percent_connected_by_month = new String[12];
        String [] percent_not_connected_by_month = new String[12];
        String primarytitle = "Data row for attendance";
        String secondarytitle = "Data row for Mall (2018)";
        String subtitle = "In this page, you have the possibility to consult the statistics about attendance of mall and stores with many criteria at your disposal.";

        String[] month_names = {"January","February","March","April","May","June","July","August","September","October","November","December"};

        // Computing stat for each month
        for(int i = 0; i < 12; i++)
        {
            // Start Date
            Calendar calStart = GregorianCalendar.getInstance();
            calStart.set(Calendar.DAY_OF_MONTH, 1);// I might have the wrong Calendar constant...
            calStart.set(Calendar.MONTH, i);// -1 as month is zero-based
            calStart.set(Calendar.YEAR, 2018);
            calStart.set(Calendar.HOUR_OF_DAY, 0);            // set hour to midnight
            calStart.set(Calendar.MINUTE, 0);                 // set minute in hour
            calStart.set(Calendar.SECOND, 0);                 // set second in minute
            calStart.set(Calendar.MILLISECOND, 0);
            Timestamp tStartDate = new Timestamp(calStart.getTimeInMillis());

            // End Date
            Calendar calEnd = GregorianCalendar.getInstance();
            calEnd.set(Calendar.DAY_OF_MONTH, 31);// I might have the wrong Calendar constant...
            calEnd.set(Calendar.MONTH, i);// -1 as month is zero-based
            calEnd.set(Calendar.YEAR, 2018);
            calEnd.set(Calendar.HOUR_OF_DAY, 0);            // set hour to midnight
            calEnd.set(Calendar.MINUTE, 0);                 // set minute in hour
            calEnd.set(Calendar.SECOND, 0);                 // set second in minute
            calEnd.set(Calendar.MILLISECOND, 0);
            Timestamp tEndDate = new Timestamp(calEnd.getTimeInMillis());

            // Attendance counters
            nb_connected_by_month[i] = visitService.getNumberAttendanceConnectedClientWithPeriod(tStartDate, tEndDate);
            nb_not_connected_by_month[i] = visitService.getNumberAttendanceDiconnectedClientWithPeriod(tStartDate, tEndDate);
            nb_by_month[i] = visitService.getNumberAttendanceWithPeriod(tStartDate, tEndDate);


            // Percentages
            if(nb_by_month[i] != 0 )
            {
                percent_connected_by_month[i] = my_round((float)nb_connected_by_month[i]/nb_by_month[i] * 100);
                percent_not_connected_by_month[i] = my_round((float)nb_not_connected_by_month[i]/nb_by_month[i] * 100) ;
            }
            else
            {
                percent_connected_by_month[i] = my_round((float)0) ;
                percent_not_connected_by_month[i] = my_round((float)0) ;
            }
            System.out.println(tStartDate + " => " + tEndDate + " : " + nb_by_month[i]);
        }

        System.out.println(nb_connected_by_month[0]);

        // Sum months data to get full year data
        int nb_by_year_co = IntStream.of(nb_connected_by_month).sum();
        int nb_by_year_notco = IntStream.of(nb_not_connected_by_month).sum();
        int nb_by_year = IntStream.of(nb_by_month).sum();
        String percent_connected_by_year = my_round((float)nb_by_year_co/nb_by_year*100);
        String percent_not_connected_by_year = my_round((float)nb_by_year_notco/nb_by_year*100);

        // Outlier

        int outlier_nb_connected_by_month = statisticsService.getOutlier(nb_connected_by_month);
        int outlier_nb_not_connected_by_month = statisticsService.getOutlier(nb_not_connected_by_month);
        int outlier_nb_by_month = statisticsService.getOutlier(nb_by_month);
        double outlier_percent_connected_by_month = statisticsService.getOutlier(percent_connected_by_month);
        double outlier_percent_not_connected_by_month = statisticsService.getOutlier(percent_not_connected_by_month);

        int outlier_nb_by_year_co = IntStream.of(outlier_nb_connected_by_month).sum();
        int outlier_nb_by_year_notco = IntStream.of(outlier_nb_not_connected_by_month).sum();
        int outlier_nb_by_year = IntStream.of(outlier_nb_by_month).sum();
        String outlier_percent_connected_by_year = my_round((float)outlier_nb_by_year_co/outlier_nb_by_year*100);
        String outlier_percent_not_connected_by_year = my_round((float)outlier_nb_by_year_notco/outlier_nb_by_year*100);

        // Average to put in comment
        float avg_nb_co = statisticsService.getAverage(nb_connected_by_month);
        float avg_nb_notco = statisticsService.getAverage(nb_not_connected_by_month);
        float avg_nb_total = statisticsService.getAverage(nb_by_month);
        float avg_per_co = statisticsService.getAverage(percent_connected_by_month);
        float avg_per_notco = statisticsService.getAverage(percent_not_connected_by_month);

        // Variance to put in comment
        double variance_nb_co = statisticsService.getVariance(nb_connected_by_month);
        double variance_nb_notco = statisticsService.getVariance(nb_not_connected_by_month);
        double variance_nb_total = statisticsService.getVariance(nb_by_month);
        double variance_per_co = statisticsService.getVariance(percent_connected_by_month);
        double variance_per_notco = statisticsService.getVariance(percent_not_connected_by_month);

        // Standard deviation to put in comment
        double sigma_nb_co = statisticsService.getStandardDeviation(nb_connected_by_month);
        double sigma_nb_notco = statisticsService.getStandardDeviation(nb_not_connected_by_month);
        double sigma_nb_total = statisticsService.getStandardDeviation(nb_by_month);
        double sigma_per_co = statisticsService.getStandardDeviation(percent_connected_by_month);
        double sigma_per_notco = statisticsService.getStandardDeviation(percent_not_connected_by_month);


        // Passing data to view
        // title, subtitle
        model.addAttribute("primarytitle", primarytitle);
        model.addAttribute("secondarytitle", secondarytitle);
        model.addAttribute("subtitle", subtitle);
        // store dropdown
        model.addAttribute("allstores", storeService.getAllStores());
        // month names
        model.addAttribute("month_names", month_names);
        // count outlier_
        model.addAttribute("statsbymonth", outlier_nb_by_month);
        model.addAttribute("statsbymonth_co", outlier_nb_connected_by_month);
        model.addAttribute("statsbymonth_notco", outlier_nb_connected_by_month);
        model.addAttribute("statsbyyear", outlier_nb_by_year);
        model.addAttribute("statsbyyear_co", outlier_nb_by_year_co);
        model.addAttribute("statsbyyear_notco", outlier_nb_by_year_notco);
        // percent outlier_
        model.addAttribute("percentbymonth_co", outlier_percent_connected_by_year);
        model.addAttribute("percentbymonth_notco", outlier_percent_not_connected_by_month);
        model.addAttribute("percentbyyear_co",percent_connected_by_month);
        model.addAttribute("percentbyyear_notco",percent_not_connected_by_year);

        // avg to put in comment
        model.addAttribute("avg_nb_co", my_round(avg_nb_co));
        model.addAttribute("avg_nb_notco", my_round(avg_nb_notco));
        model.addAttribute("avg_per_co", my_round(avg_per_co));
        model.addAttribute("avg_per_notco", my_round(avg_per_notco));
        model.addAttribute("avg_nb_total", my_round(avg_nb_total));
        // variance to put in comment
        model.addAttribute("variance_nb_co", my_round(variance_nb_co));
        model.addAttribute("variance_nb_notco", my_round(variance_nb_notco));
        model.addAttribute("variance_per_co", my_round(variance_per_co));
        model.addAttribute("variance_per_notco", my_round(variance_per_notco));
        model.addAttribute("variance_nb_total", my_round(variance_nb_total));
        //standard deviation to put in comment
        model.addAttribute("sigma_nb_co", my_round(sigma_nb_co));
        model.addAttribute("sigma_nb_notco", my_round(sigma_nb_notco));
        model.addAttribute("sigma_per_co", my_round(sigma_per_co));
        model.addAttribute("sigma_per_notco", my_round(sigma_per_notco));
        model.addAttribute("sigma_nb_total", my_round(sigma_nb_total));

        return "statistics-outliers-view";
    }

    /**
     *
     * @param model
     * @return
     */
    /*@PostMapping(value="/statisticsdatarowstore/{id}")
    public String statisticsDataRowStore (Model model, @PathVariable Integer id, HttpServletRequest request) {
        String selectedStore = request.getParameter("ddstore");
        model.addAttribute("storetest", storeService.getStoreById(id));
        System.out.println("test"+selectedStore);
        return "statistics-datarow-view";
    }*/

    /**
     * round percent which has a float valueI
     * @param per
     * @return
     */
    private String my_round(float per)
    {
        return String.format ("%.2f", per);
    }

    /**
     * round percent which has a double value
     * @param per
     * @return
     */
    private String my_round(double per)
    {
        return String.format ("%.2f", per);
    }

}
