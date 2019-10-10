package kan10.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)

/**
 * Testing StatisticsService class
 * @author Suriya
 */
public class StatisticsServiceTest {

    @InjectMocks
    StatisticsService statisticsService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    // Init mockito annotations "manually"
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAverage() {
        float series [] = {303, 276, 239, 243, 198, 237, 250, 304, 430, 101, 117, 455};
        assertEquals( 	262.75, statisticsService.getAverage(series), 1);
    }

    @Test
    public void getVariance() {
        float series [] = {303, 276, 239, 243, 198, 237, 250, 304, 430, 101, 117, 455};
        assertEquals(10150.69, statisticsService.getVariance(series), 1);
    }

    @Test
    public void getStandardDeviation() {
        double series [] = {303, 276, 239, 243, 198, 237, 250, 304, 430, 101, 117, 455};
        assertEquals(100.75, statisticsService.getStandardDeviation(series), 1);
    }

    @Test
    public void getDataBiggerThanXSigma () {
        // We can't test because the function is not implemented yet
        // But the test should be working like this :

        //double series [] = {303, 276, 239, 243, 198, 237, 250, 304, 430, 101, 117, 455};
        //double result[] = {240};
        //assertArrayEquals(result,statisticsService.getDataBiggerThanXSigma(series,1),1);
    }

    @Test
    public void getDataSmallerThanXSigma () {
        // We can't test because the function is not implemented yet
        // But the test should be working like this :

        //double series [] = {303, 276, 239, 243, 198, 237, 250, 304, 430, 101, 117, 455};
        //double result[] = {21};
        //assertArrayEquals(result,statisticsService.getDataSmallerThanXSigma(series),1);
    }

    @Test
    public void getDataBiggerThan2Sigma () {
        // We can't test because the function is not implemented yet
        // But the test should be working like this :

        //double series [] = {303, 276, 239, 243, 198, 237, 250, 304, 430, 101, 117, 455};
        //double result[] = {};
        //assertArrayEquals(result,statisticsService.getDataBiggerThan2Sigma(series),1);
    }

    @Test
    public void getDataSmallerThan2Sigma () {
        // We can't test because the function is not implemented yet
        // But the test should be working like this :

        //double series [] = {303, 276, 239, 243, 198, 237, 250, 304, 430, 101, 117, 455};
        //double result[] = {};
        //assertArrayEquals(result,statisticsService.getDataSmallerThan2Sigma(series),1);
    }

    @Test
    public void getOutliers () {
        // We can't test because the function is not implemented yet
        // But the test should be working like this :

        //double series [] = {303, 198, 101, 117, 455};
        //double up = 101;
        //double down = 2;
        //assertEquals(series,statisticsService.getOutliers(up, down),1);
    }
}