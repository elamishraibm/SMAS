import ca.uwaterloo.iss4e.algorithm.PARX;
import ca.uwaterloo.iss4e.common.SMASException;
import ca.uwaterloo.iss4e.common.Utils;
import org.apache.commons.math.stat.regression.OLSMultipleLinearRegression;
import org.junit.Test;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by xiliu on 20/05/14.
 */
public class SampleTest {

    public void test() {
        String url = "jdbc:postgresql://localhost/essex";
        Properties props = new Properties();
        props.setProperty("user", "xiliu");
        props.setProperty("password", "Abcd1234");
        PrintStream out = new PrintStream(new FileOutputStream(FileDescriptor.out));
        try {
            Connection conn = DriverManager.getConnection(url, props);
            PreparedStatement pstmt = conn.prepareStatement("select readtime from smas_power_hourlyreading limit 10");

            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
                String hireDate = rs.getString(1);

                // System.out.println(" Hire Date: " + hireDate);
                System.out.println(" Hire Date: " + hireDate + "  " + Utils.getTime(hireDate, "UTC"));
            }
        } catch (Exception e) {
            out.println(e);
            e.printStackTrace();
        }
    }


    public void testMatrix(){
        double[] Y={0.27,0.28,0.28,0.4,0.35,0.04,0.04,0.04,0.37,0.37,0.39,0.25,0.04,0.04,0.26,0.34,0.26,0.21,0.13,0.37,0.32,0.38,0.19,0.2,0.2,0.32,0.31,0.37,0.39,0.33,0.31,0.29,0.26,0.19,0.26,0.3,0.23,0.32,0.24,0.35,0.05,0.05,0.31,0.35,0.05,0.16,0.35,0.36,0.34,0.28,0.11,0.31,0.23,0.4,0.35,0.35,0.4,0.34,0.4,0.35,0.14,0.34,0.05,0.05,0.26,0.29,0.3,0.35,0.05,0.05,0.26,0.1,0.24,0.3,0.16,0.29,0.3,0.15,0.23,0.32,0.15,0.33,0.3,0.34,0.39,0.33,0.23,0.39,0.23,0.19,0.06,0.35,0.12,0.18,0.37,0.2,0.05,0.05,0.34,0.36,0.34,0.19,0.38,0.17,0.4,0.38,0.31,0.26,0.34,0.41,0.16,0.15,0.16,0.38,0.32,0.39,0.25,0.07,0.21,0.17,0.33,0.26,0.24,0.41,0.44,0.05,0.56,0.06,0.5,0.09,0.39,0.53,0.45,0.48,0.5,0.38,0.32,0.36,0.12,0.39,0.31,0.49,0.43,0.19,0.1,0.2,0.24,0.46,0.41,0.65,0.06,0.06,0.06,0.06,0.06,0.16,0.51,0.46,0.44,0.14,0.12,0.48,0.45,0.38,0.21,0.29,0.41,0.53,0.22,0.06,0.06,0.06,0.06,0.06,0.05,0.28,0.53,0.32,0.54,0.55,0.52,0.31,0.47,0.4,0.58,0.57,0.51,0.06,0.06,0.56,0.7,0.33,0.47,0.39,0.06,0.1,0.06,0.32,0.27,0.28,0.19,0.53,0.06,0.55,0.52,0.48,0.48,0.39,0.05,0.06,0.15,0.46,0.4,0.35,0.52,0.1,0.37,0.18,0.22,0.24,0.14,0.23,0.2,0.35,0.34,0.36,0.44,0.06,0.23,0.06,0.04,0.45,0.28,0.18,0.32,0.14,0.16,0.22,0.18,0.16,0.47,0.38,0.35,0.51,0.33,0.43,0.12,0.43,0.52,0.37,0.43,0.19,0.44,0.06,0.05,0.15,0.51,0.06,0.06,0.39,0.52,0.24,0.06,0.42,0.43,0.42,0.06,0.37,0.36,0.52,0.19,0.53,0.02,0.04,0.25,0.23,0.5,0.34,0.42,0.46,0.5,0.53,0.05,0.36,0.44,0.32,0.55,0.45,0.5,0.37,0.43,0.54,0.05,0.05,0.3,0.38,0.49,0.36,0.5,0.3,0.41,0.51,0.59,0.26,0.3,0.45,0.56,0.47,0.59,0.39,0.26,0.49,0.43,0.51,0.24,0.2,0.4,0.31,0.25,0.14,0.63,0.48,0.32,0.05,0.43,0.29,0.27,0.05,0.05,0.55,0.36,0.43,0.19,0.24,0.5,0.39,0.05,0.05,0.43,0.4,0.41,0.42,0.29,0.46,0.21,0.42,0.35,0.21,0.12,0.05,0.26,0.3,0.22,0.26,0.52,0.35,0.24,0.49,0.51,0.4,0.39,0.44,0.4,0.48,0.52,0.15,0.42,0.2,0.42,0.46,0.52,0.08,0.3,0.53,0.55,0.44,0.3,0.07,0.12,0.33,0.26,0.44,0.43,0.1,0.49,0.34,0.4,0.52,0.52,0.46,0.39,0.52,0.12,0.49,0.42,0.4,0.43,0.51,0.25,0.42,0.29,0.5,0.36,0.53,0.22,0.43,0.38,0.23,0.55,0.42,0.55,0.53,0.53,0.53,0.47,0.51,0.43,0.42,0.52,0.52,0.34,0.15,0.16,0.25,0.18,0.22,0.18,0.19,0.1,0.52,0.44,0.09,0.1,0.1,0.5,0.35,0.42,0.34,0.27,0.38,0.14,0.25,0.14,0.33,0.27,0.53,0.52,0.39,0.51,0.48,0.52,0.61,0.32,0.05,0.07,0.46,0.4,0.56,0.59,0.33,0.43,0.51,0.17,0.29,0.39,0.44,0.26,0.07,0.07,0.56,0.51,0.27,0.54,0.51,0.34,0.26,0.2,0.39,0.18,0.53,0.5,0.46,0.08,0.46,0.49,0.59,0.43,0.33,0.43,0.06,0.06,0.16,0.46,0.19,0.09,0.07,0.1,0.56,0.45,0.48,0.41,0.39,0.17,0.27,0.38,0.39,0.1,0.3,0.24,0.09,0.17,0.19,0.37,0.2,0.49,0.07,0.1,0.08,0.37,0.2,0.38,0.34,0.51,0.27,0.51,0.08,0.38,0.09,0.07,0.06,0.19,0.38,0.12,0.14,0.12,0.36};
        double[][]X={
                {0.11,0.11,0.13,0.0,10.0,0.0},
                {0.31,0.27,0.27,0.0,10.0,0.0},
                {0.34,0.35,0.36,0.0,8.0,0.0},
                {0.4,0.36,0.32,0.0,8.0,0.0},
                {0.25,0.36,0.35,0.0,7.0,0.0},
                {0.04,0.02,0.04,0.0,10.0,0.0},
                {0.03,0.02,0.04,0.0,0.0,0.5},
                {0.03,0.04,0.04,0.0,0.0,12.5},
                {0.35,0.36,0.43,0.0,0.0,12.5},
                {0.44,0.4,0.39,0.0,0.0,9.0},
                {0.37,0.39,0.39,0.0,0.0,10.5},
                {0.42,0.34,0.35,0.0,0.0,5.0},
                {0.04,0.04,0.02,0.0,0.0,10.5},
                {0.04,0.03,0.03,0.0,0.0,9.0},
                {0.35,0.37,0.44,0.0,0.0,13.0},
                {0.16,0.3,0.38,0.0,0.0,5.0},
                {0.53,0.39,0.34,0.0,0.0,7.0},
                {0.37,0.32,0.3,0.0,11.0,0.0},
                {0.32,0.25,0.24,0.0,0.0,3.0},
                {0.44,0.44,0.39,0.0,0.0,2.0},
                {0.38,0.31,0.37,0.0,0.0,1.5},
                {0.4,0.42,0.43,0.0,0.0,6.0},
                {0.43,0.43,0.36,0.0,11.0,0.0},
                {0.27,0.26,0.19,0.0,0.0,2.0},
                {0.37,0.31,0.32,0.0,0.0,8.0},
                {0.38,0.35,0.34,0.0,0.0,10.0},
                {0.43,0.43,0.37,0.0,0.0,6.0},
                {0.44,0.44,0.43,0.0,0.0,8.0},
                {0.25,0.51,0.46,0.0,0.0,7.0},
                {0.33,0.39,0.42,0.0,0.0,3.0},
                {0.41,0.34,0.4,0.0,0.0,11.0},
                {0.33,0.32,0.35,0.0,0.0,5.0},
                {0.32,0.43,0.36,0.0,0.0,5.0},
                {0.34,0.34,0.35,0.0,0.0,5.0},
                {0.44,0.44,0.39,0.0,0.0,10.0},
                {0.38,0.37,0.31,0.0,0.0,6.5},
                {0.43,0.33,0.32,0.0,0.0,6.5},
                {0.28,0.26,0.31,0.0,0.0,3.0},
                {0.39,0.49,0.33,0.0,0.0,5.0},
                {0.4,0.43,0.39,0.0,0.0,2.0},
                {0.03,0.04,0.04,0.0,11.0,0.0},
                {0.04,0.05,0.05,0.0,0.0,2.5},
                {0.33,0.3,0.32,0.0,9.0,0.0},
                {0.53,0.42,0.4,0.0,9.5,0.0},
                {0.33,0.28,0.24,0.0,10.0,0.0},
                {0.33,0.32,0.35,0.0,7.0,0.0},
                {0.28,0.32,0.4,0.0,7.0,0.0},
                {0.41,0.43,0.4,0.0,0.0,1.0},
                {0.33,0.34,0.35,0.0,0.0,4.5},
                {0.44,0.49,0.36,0.0,11.0,0.0},
                {0.32,0.49,0.39,0.0,0.0,1.0},
                {0.37,0.48,0.36,0.0,7.0,0.0},
                {0.47,0.26,0.25,0.0,8.0,0.0},
                {0.69,0.5,0.38,0.0,0.0,1.5},
                {0.43,0.41,0.35,0.0,9.0,0.0},
                {0.47,0.37,0.34,0.0,10.5,0.0},
                {0.28,0.39,0.37,0.0,0.0,1.0},
                {0.36,0.55,0.34,0.0,8.0,0.0},
                {0.34,0.32,0.35,0.0,3.5,0.0},
                {0.35,0.51,0.35,0.0,1.5,0.0},
                {0.45,0.28,0.14,0.0,6.0,0.0},
                {0.44,0.42,0.35,0.0,5.0,0.0},
                {0.04,0.04,0.05,0.0,11.0,0.0},
                {0.04,0.04,0.05,0.0,10.0,0.0},
                {0.23,0.21,0.2,0.0,9.0,0.0},
                {0.47,0.34,0.32,0.0,0.0,0.0},
                {0.25,0.25,0.29,0.0,6.5,0.0},
                {0.55,0.48,0.42,0.0,2.0,0.0},
                {0.05,0.05,0.05,0.0,5.0,0.0},
                {0.05,0.04,0.04,0.0,0.0,6.0},
                {0.28,0.24,0.26,0.0,11.0,0.0},
                {0.27,0.27,0.27,0.0,8.0,0.0},
                {0.29,0.28,0.3,0.0,7.5,0.0},
                {0.28,0.38,0.29,0.0,9.0,0.0},
                {0.42,0.3,0.3,0.0,0.0,2.5},
                {0.26,0.3,0.3,0.0,8.0,0.0},
                {0.33,0.32,0.29,0.0,6.0,0.0},
                {0.24,0.16,0.14,0.0,0.0,0.5},
                {0.28,0.45,0.3,0.0,7.5,0.0},
                {0.31,0.31,0.32,0.0,7.0,0.0},
                {0.05,0.12,0.29,0.0,7.0,0.0},
                {0.42,0.43,0.37,0.0,7.0,0.0},
                {0.38,0.43,0.38,0.0,0.0,1.0},
                {0.33,0.34,0.34,0.0,5.0,0.0},
                {0.29,0.39,0.38,0.0,3.0,0.0},
                {0.27,0.35,0.37,0.0,1.5,0.0},
                {0.37,0.36,0.37,0.0,3.0,0.0},
                {0.31,0.31,0.32,0.0,5.0,0.0},
                {0.06,0.23,0.29,0.0,4.0,0.0},
                {0.42,0.33,0.33,0.0,4.0,0.0},
                {0.06,0.06,0.06,0.0,5.0,0.0},
                {0.32,0.37,0.4,0.0,4.5,0.0},
                {0.29,0.31,0.29,0.0,6.0,0.0},
                {0.33,0.34,0.34,0.0,3.0,0.0},
                {0.27,0.31,0.37,0.0,5.0,0.0},
                {0.29,0.23,0.2,0.0,3.0,0.0},
                {0.04,0.04,0.05,0.0,4.5,0.0},
                {0.08,0.05,0.05,0.0,7.0,0.0},
                {0.25,0.34,0.33,0.0,8.0,0.0},
                {0.35,0.38,0.36,0.0,0.0,1.0},
                {0.36,0.37,0.33,0.0,4.0,0.0},
                {0.23,0.14,0.14,0.0,2.0,0.0},
                {0.28,0.35,0.36,0.0,1.5,0.0},
                {0.25,0.12,0.1,0.0,3.0,0.0},
                {0.42,0.36,0.38,0.0,2.0,0.0},
                {0.22,0.28,0.38,0.0,2.5,0.0},
                {0.36,0.35,0.34,0.0,11.0,0.0},
                {0.32,0.38,0.38,0.0,6.5,0.0},
                {0.29,0.33,0.34,0.0,5.0,0.0},
                {0.37,0.38,0.4,0.0,6.0,0.0},
                {0.22,0.14,0.14,0.0,0.0,0.0},
                {0.24,0.17,0.15,0.0,3.0,0.0},
                {0.15,0.22,0.18,0.0,6.0,0.0},
                {0.31,0.35,0.37,0.0,0.0,0.0},
                {0.22,0.32,0.32,0.0,0.5,0.0},
                {0.26,0.33,0.39,0.0,3.0,0.0},
                {0.34,0.38,0.36,0.0,5.0,0.0},
                {0.06,0.06,0.06,0.0,2.0,0.0},
                {0.24,0.15,0.16,0.0,1.0,0.0},
                {0.29,0.12,0.12,0.0,1.0,0.0},
                {0.26,0.22,0.28,0.0,2.0,0.0},
                {0.29,0.5,0.42,0.0,6.5,0.0},
                {0.2,0.22,0.23,0.0,4.5,0.0},
                {0.24,0.15,0.15,0.0,5.5,0.0},
                {0.28,0.45,0.5,0.0,10.0,0.0},
                {0.04,0.05,0.05,0.0,11.0,0.0},
                {1.61,0.52,0.55,0.0,3.0,0.0},
                {0.06,0.06,0.06,0.0,7.0,0.0},
                {0.2,0.36,0.52,0.0,6.0,0.0},
                {0.23,0.43,0.45,0.0,5.5,0.0},
                {0.22,0.36,0.46,0.0,8.5,0.0},
                {0.27,0.35,0.34,0.0,5.0,0.0},
                {0.34,0.36,0.46,0.0,2.5,0.0},
                {0.19,0.3,0.4,0.0,2.5,0.0},
                {0.26,0.41,0.5,0.0,3.5,0.0},
                {0.21,0.15,0.16,0.0,0.0,0.0},
                {0.14,0.31,0.44,0.0,4.0,0.0},
                {0.34,0.3,0.29,0.0,2.5,0.0},
                {0.19,0.06,0.06,0.0,2.0,0.0},
                {0.19,0.11,0.11,0.0,1.0,0.0},
                {0.06,0.15,0.49,0.0,6.0,0.0},
                {0.27,0.51,0.5,0.0,6.0,0.0},
                {0.22,0.38,0.49,0.0,3.0,0.0},
                {0.24,0.15,0.15,0.0,4.0,0.0},
                {0.19,0.11,0.1,0.0,5.5,0.0},
                {0.24,0.15,0.14,0.0,4.0,0.0},
                {0.18,0.15,0.15,0.0,1.0,0.0},
                {0.21,0.32,0.35,0.0,3.0,0.0},
                {0.21,0.35,0.51,0.0,2.0,0.0},
                {3.02,2.03,1.34,0.0,2.0,0.0},
                {0.06,0.06,0.06,0.0,4.0,0.0},
                {0.06,0.06,0.06,0.0,5.0,0.0},
                {0.06,0.06,0.06,0.0,2.0,0.0},
                {0.05,0.05,0.05,0.0,3.0,0.0},
                {0.06,0.06,0.06,0.0,3.0,0.0},
                {0.2,0.17,0.15,0.0,1.5,0.0},
                {0.21,0.32,0.51,0.0,1.0,0.0},
                {0.25,0.21,0.45,0.0,1.5,0.0},
                {0.18,0.38,0.41,0.0,3.0,0.0},
                {0.19,0.06,0.06,0.0,0.0,0.0},
                {0.06,0.05,0.07,0.0,0.0,0.0},
                {0.18,0.4,0.31,0.0,2.0,0.0},
                {0.19,0.21,0.35,0.0,2.0,0.0},
                {0.19,0.2,0.39,0.0,3.0,0.0},
                {0.06,0.05,0.06,0.0,4.0,0.0},
                {0.18,0.2,0.24,0.0,2.0,0.0},
                {0.35,0.39,0.38,0.0,0.0,0.0},
                {0.2,0.26,0.44,0.0,0.0,0.0},
                {0.19,0.3,0.44,0.0,1.5,0.0},
                {0.06,0.06,0.06,0.0,4.0,0.0},
                {1.7,0.44,0.1,0.0,5.0,0.0},
                {0.06,0.06,0.06,0.0,2.0,0.0},
                {0.06,0.06,0.06,0.0,2.0,0.0},
                {0.09,0.06,0.06,0.0,4.0,0.0},
                {0.06,0.06,0.06,0.0,5.5,0.0},
                {0.35,0.35,0.46,0.0,0.0,0.0},
                {0.35,0.35,0.52,0.0,1.0,0.0},
                {0.19,0.13,0.36,0.0,3.0,0.0},
                {1.97,3.16,0.45,0.0,2.0,0.0},
                {0.06,0.16,0.46,0.0,0.0,0.0},
                {0.24,0.44,0.44,0.0,6.0,0.0},
                {0.15,0.16,0.15,0.0,3.0,0.0},
                {0.07,0.07,0.22,0.0,1.0,0.0},
                {0.38,0.28,0.34,0.0,3.0,0.0},
                {0.12,0.41,0.51,0.0,0.0,0.0},
                {0.15,0.56,0.48,0.0,0.0,0.0},
                {0.06,0.06,0.51,0.0,1.5,0.0},
                {0.06,0.06,0.06,0.0,0.0,0.0},
                {0.06,0.06,0.06,0.0,1.5,0.0},
                {0.11,0.12,0.35,0.0,1.0,0.0},
                {0.21,0.32,0.7,0.0,5.0,0.0},
                {0.2,0.24,0.4,0.0,4.0,0.0},
                {0.2,0.22,0.35,0.0,1.0,0.0},
                {0.68,0.48,0.31,0.0,1.0,0.0},
                {0.06,0.06,0.06,0.0,1.0,0.0},
                {0.06,0.06,0.11,0.0,3.0,0.0},
                {0.06,0.06,0.06,0.0,9.0,0.0},
                {0.24,0.44,0.44,0.0,6.0,0.0},
                {0.2,0.23,0.28,0.0,6.0,0.0},
                {0.34,0.4,0.27,0.0,0.0,0.0},
                {0.19,0.22,0.25,0.0,7.0,0.0},
                {0.2,0.25,0.4,0.0,8.0,0.0},
                {0.12,0.09,0.06,0.0,7.0,0.0},
                {0.19,0.2,0.47,0.0,5.0,0.0},
                {0.12,0.18,0.39,0.0,4.0,0.0},
                {0.24,0.33,0.35,0.0,9.0,0.0},
                {0.21,0.5,0.53,0.0,4.0,0.0},
                {0.21,0.25,0.28,0.0,3.0,0.0},
                {0.2,0.07,0.06,0.0,4.0,0.0},
                {0.06,0.06,0.06,0.0,5.0,0.0},
                {0.21,0.17,0.15,0.0,3.0,0.0},
                {0.13,0.1,0.28,0.0,0.0,0.0},
                {0.16,0.11,0.11,0.0,5.0,0.0},
                {0.2,0.22,0.37,0.0,7.0,0.0},
                {0.28,0.32,0.5,0.0,6.0,0.0},
                {0.26,0.18,0.11,0.0,7.0,0.0},
                {0.31,0.34,0.43,0.0,6.0,0.0},
                {0.19,0.22,0.18,0.0,6.5,0.0},
                {0.19,0.15,0.15,0.0,5.0,0.0},
                {0.11,0.1,0.1,0.0,8.0,0.0},
                {0.08,0.2,0.23,0.0,0.0,0.5},
                {0.19,0.2,0.23,0.0,10.0,0.0},
                {0.06,0.19,0.52,0.0,7.5,0.0},
                {0.08,0.06,0.46,0.0,2.0,0.0},
                {0.1,0.06,0.05,0.0,4.5,0.0},
                {0.13,0.06,0.06,0.0,6.5,0.0},
                {0.12,0.12,0.1,0.0,4.0,0.0},
                {0.34,0.39,0.46,0.0,0.0,0.0},
                {0.21,0.32,0.35,0.0,5.0,0.0},
                {0.19,0.27,0.36,0.0,4.0,0.0},
                {0.06,0.06,0.06,0.0,8.5,0.0},
                {0.05,0.05,0.35,0.0,7.0,0.0},
                {0.2,0.34,0.41,0.0,10.0,0.0},
                {0.34,0.36,0.41,0.0,7.0,0.0},
                {0.19,0.28,0.35,0.0,0.0,4.0},
                {0.26,0.33,0.46,0.0,11.0,0.0},
                {0.24,0.16,0.16,0.0,0.0,3.0},
                {0.19,0.22,0.21,0.0,8.5,0.0},
                {0.06,0.12,0.07,0.0,11.0,0.0},
                {0.18,0.14,0.16,0.0,8.5,0.0},
                {0.34,0.5,0.51,0.0,7.0,0.0},
                {0.23,0.32,0.38,0.0,4.0,0.0},
                {0.19,0.37,0.35,0.0,7.0,0.0},
                {0.21,0.52,0.49,0.0,0.0,1.5},
                {0.06,0.13,0.47,0.0,0.0,2.0},
                {0.06,0.05,0.14,0.0,8.0,0.0},
                {0.4,0.52,0.5,0.0,10.0,0.0},
                {0.2,0.39,0.45,0.0,6.5,0.0},
                {0.31,0.46,0.47,0.0,4.0,0.0},
                {0.11,0.27,0.37,0.0,6.0,0.0},
                {0.22,0.28,0.42,0.0,5.0,0.0},
                {0.24,0.33,0.4,0.0,5.5,0.0},
                {0.04,0.06,0.42,0.0,5.0,0.0},
                {0.24,0.44,0.39,0.0,10.0,0.0},
                {0.55,0.45,0.49,0.0,10.5,0.0},
                {0.12,0.44,0.41,0.0,0.0,5.0},
                {0.31,0.45,0.5,0.0,0.0,2.0},
                {0.06,0.04,0.06,0.0,0.0,4.0},
                {0.12,0.09,0.06,0.0,0.0,0.5},
                {0.3,0.39,0.46,0.0,0.0,0.5},
                {0.48,0.53,0.53,0.0,0.0,3.5},
                {0.38,0.37,0.35,0.0,0.0,2.5},
                {0.21,0.4,0.29,0.0,0.0,4.0},
                {0.24,0.56,0.45,0.0,10.0,0.0},
                {0.2,0.55,0.52,0.0,10.5,0.0},
                {0.04,0.33,0.42,0.0,9.5,0.0},
                {0.06,0.14,0.34,0.0,11.0,0.0},
                {0.06,0.06,0.18,0.0,0.0,1.0},
                {0.11,0.41,0.4,0.0,9.0,0.0},
                {0.54,0.54,0.55,0.0,10.0,0.0},
                {0.63,0.47,0.45,0.0,10.0,0.0},
                {0.51,0.56,0.62,0.0,6.0,0.0},
                {0.04,0.05,0.05,0.0,8.0,0.0},
                {0.03,0.05,0.05,0.0,0.0,2.5},
                {0.52,0.59,0.54,0.0,0.0,1.0},
                {0.06,0.53,0.48,0.0,0.0,4.5},
                {0.45,0.63,0.55,0.0,0.0,4.0},
                {0.37,0.49,0.57,0.0,0.0,4.0},
                {0.47,0.56,0.53,0.0,10.5,0.0},
                {0.47,0.47,0.48,0.0,0.0,2.5},
                {0.14,0.13,0.4,0.0,11.0,0.0},
                {0.52,0.52,0.53,0.0,8.5,0.0},
                {0.21,0.51,0.4,0.0,9.0,0.0},
                {0.56,0.56,0.53,0.0,10.0,0.0},
                {0.53,0.52,0.55,0.0,10.0,0.0},
                {0.38,0.37,0.36,0.0,0.0,1.0},
                {0.52,0.51,0.53,0.0,5.0,0.0},
                {0.61,0.47,0.48,0.0,0.0,4.0},
                {0.38,0.63,0.68,0.0,10.0,0.0},
                {0.48,0.55,0.52,0.0,6.0,0.0},
                {0.56,0.53,0.43,0.0,5.0,0.0},
                {0.42,0.52,0.57,0.0,9.0,0.0},
                {0.03,0.04,0.05,0.0,0.0,2.0},
                {0.05,0.05,0.05,0.0,0.0,1.0},
                {0.24,0.24,0.46,0.0,10.5,0.0},
                {0.53,0.54,0.5,0.0,0.0,3.5},
                {0.46,0.48,0.49,0.0,11.0,0.0},
                {0.44,0.54,0.56,0.0,0.0,8.0},
                {0.45,0.62,0.62,0.0,0.0,9.0},
                {0.56,0.57,0.5,0.0,0.0,6.5},
                {0.29,0.36,0.37,0.0,0.0,8.5},
                {0.05,0.45,0.6,0.0,0.0,4.5},
                {0.44,0.57,0.56,0.0,0.0,0.5},
                {0.57,0.53,0.44,0.0,0.0,9.0},
                {0.59,0.57,0.51,0.0,0.0,5.0},
                {0.45,0.43,0.49,0.0,0.0,1.5},
                {0.5,0.52,0.54,0.0,0.0,6.0},
                {0.6,0.58,0.56,0.0,0.0,6.0},
                {0.43,0.51,0.62,0.0,0.0,3.5},
                {0.43,0.52,0.59,0.0,0.0,3.0},
                {0.52,0.58,0.59,0.0,0.0,3.5},
                {0.45,0.48,0.49,0.0,0.0,3.0},
                {0.48,0.56,0.53,0.0,0.0,2.0},
                {0.58,0.58,0.58,0.0,0.0,2.0},
                {0.57,0.57,0.52,0.0,0.0,10.5},
                {0.38,0.61,0.57,0.0,0.0,5.0},
                {0.46,0.47,0.48,0.0,0.0,6.0},
                {0.37,0.39,0.36,0.0,0.0,5.5},
                {0.48,0.51,0.48,0.0,0.0,5.0},
                {0.36,0.41,0.39,0.0,0.0,4.0},
                {0.52,0.53,0.63,0.0,0.0,4.0},
                {0.9,0.73,0.51,0.0,0.0,1.0},
                {0.45,0.46,0.45,0.0,0.0,6.5},
                {0.44,0.46,0.31,0.0,0.0,2.0},
                {0.47,0.52,0.43,0.0,0.0,6.0},
                {0.32,0.43,0.45,0.0,0.0,5.0},
                {0.51,0.62,0.47,0.0,0.0,4.0},
                {0.05,0.04,0.04,0.0,0.0,3.0},
                {0.05,0.05,0.05,0.0,0.0,1.0},
                {0.46,0.52,0.57,0.0,0.0,2.5},
                {0.74,0.63,0.53,0.0,11.0,0.0},
                {0.51,0.47,0.44,0.0,9.5,0.0},
                {0.58,0.58,0.47,0.0,11.0,0.0},
                {0.41,0.41,0.42,0.0,0.0,2.5},
                {0.59,0.5,0.53,0.0,10.5,0.0},
                {0.53,0.48,0.43,0.0,0.0,5.0},
                {0.23,0.27,0.35,0.0,0.0,6.5},
                {0.62,0.63,0.6,0.0,0.0,5.0},
                {0.61,0.56,0.52,0.0,9.0,0.0},
                {0.13,0.5,0.45,0.0,11.0,0.0},
                {0.46,0.46,0.42,0.0,0.0,1.0},
                {0.33,0.51,0.48,0.0,0.0,1.0},
                {0.45,0.45,0.4,0.0,0.0,6.0},
                {0.24,0.19,0.21,0.0,0.0,5.0},
                {0.51,0.55,0.53,0.0,0.0,4.5},
                {0.52,0.54,0.47,0.0,7.0,0.0},
                {0.49,0.5,0.55,0.0,0.0,1.0},
                {0.58,0.64,0.55,0.0,10.0,0.0},
                {0.07,0.09,0.02,0.0,0.0,5.0},
                {0.07,0.07,0.09,0.0,8.0,0.0},
                {0.24,0.2,0.25,0.0,0.0,1.0},
                {0.57,0.64,0.53,0.0,7.0,0.0},
                {0.32,0.34,0.46,0.0,10.5,0.0},
                {0.46,0.87,0.55,0.0,0.0,2.5},
                {0.03,0.12,0.38,0.0,0.0,4.0},
                {0.36,0.29,0.28,0.0,0.0,2.0},
                {0.58,0.46,0.42,0.0,0.0,3.0},
                {0.43,0.53,0.5,0.0,0.0,6.0},
                {0.49,0.6,0.58,0.0,0.0,13.5},
                {0.46,0.44,0.38,0.0,0.0,12.0},
                {0.52,0.53,0.45,0.0,0.0,11.5},
                {0.62,0.56,0.51,0.0,0.0,6.0},
                {0.5,0.48,0.63,0.0,0.0,5.0},
                {0.65,0.39,0.48,0.0,0.0,7.0},
                {0.63,0.6,0.64,0.0,0.0,3.0},
                {0.16,0.14,0.2,0.0,0.0,8.0},
                {0.52,0.5,0.56,0.0,0.0,5.0},
                {0.39,0.47,0.52,0.0,0.0,1.5},
                {0.25,0.48,0.53,0.0,0.0,1.0},
                {0.48,0.43,0.41,0.0,0.0,2.0},
                {0.12,0.1,0.62,0.0,0.0,5.0},
                {0.11,0.05,0.07,0.0,0.0,2.0},
                {0.4,0.29,0.33,0.0,0.0,2.0},
                {0.31,0.45,0.48,0.0,0.0,7.0},
                {0.2,0.59,0.55,0.0,0.0,3.5},
                {0.46,0.47,0.51,0.0,0.0,5.0},
                {0.48,0.49,0.47,0.0,0.0,5.0},
                {0.03,0.12,0.02,0.0,0.0,5.0},
                {0.07,0.08,0.03,0.0,0.0,5.5},
                {0.26,0.29,0.3,0.0,0.0,6.0},
                {0.37,0.36,0.27,0.0,0.0,3.0},
                {0.44,0.43,0.38,0.0,9.0,0.0},
                {0.38,0.48,0.51,0.0,11.0,0.0},
                {0.2,0.52,0.51,0.0,9.5,0.0},
                {0.91,0.59,0.51,0.0,0.0,3.0},
                {0.24,0.36,0.35,0.0,0.0,2.0},
                {0.47,0.62,0.54,0.0,9.0,0.0},
                {0.14,0.55,0.57,0.0,9.0,0.0},
                {0.53,0.57,0.54,0.0,10.0,0.0},
                {0.53,0.47,0.42,0.0,7.0,0.0},
                {0.56,0.61,0.57,0.0,9.5,0.0},
                {0.42,0.45,0.47,0.0,9.0,0.0},
                {0.3,0.46,0.39,0.0,6.0,0.0},
                {0.63,0.59,0.53,0.0,7.5,0.0},
                {0.48,0.45,0.49,0.0,0.0,4.0},
                {0.4,0.35,0.37,0.0,10.0,0.0},
                {0.53,0.58,0.53,0.0,10.0,0.0},
                {0.49,0.53,0.5,0.0,0.0,4.5},
                {0.18,0.5,0.55,0.0,0.0,2.0},
                {0.47,0.47,0.44,0.0,10.0,0.0},
                {0.6,0.66,0.47,0.0,0.0,3.0},
                {0.49,0.59,0.52,0.0,0.0,1.0},
                {0.53,0.47,0.35,0.0,10.0,0.0},
                {0.55,0.47,0.51,0.0,10.0,0.0},
                {0.19,0.34,0.43,0.0,8.0,0.0},
                {0.27,0.24,0.24,0.0,8.0,0.0},
                {0.54,0.53,0.46,0.0,6.5,0.0},
                {0.55,0.55,0.53,0.0,8.0,0.0},
                {0.62,0.56,0.46,0.0,3.0,0.0},
                {0.3,0.63,0.56,0.0,2.0,0.0},
                {0.53,0.65,0.62,0.0,6.0,0.0},
                {0.53,0.55,0.58,0.0,8.0,0.0},
                {0.6,0.54,0.59,0.0,5.0,0.0},
                {0.64,0.53,0.4,0.0,11.0,0.0},
                {0.57,0.57,0.5,0.0,6.0,0.0},
                {0.59,0.6,0.53,0.0,7.0,0.0},
                {0.38,0.51,0.49,0.0,7.5,0.0},
                {0.51,0.4,0.52,0.0,0.0,4.0},
                {0.74,0.73,0.51,0.0,8.0,0.0},
                {0.46,0.47,0.52,0.0,0.0,3.0},
                {0.45,0.45,0.41,0.0,0.0,1.0},
                {0.18,0.12,0.22,0.0,0.0,0.5},
                {0.23,0.17,0.23,0.0,9.0,0.0},
                {0.16,0.25,0.18,0.0,8.5,0.0},
                {0.2,0.21,0.24,0.0,10.0,0.0},
                {0.22,0.19,0.25,0.0,6.0,0.0},
                {0.2,0.18,0.14,0.0,10.0,0.0},
                {0.19,0.16,0.15,0.0,3.0,0.0},
                {0.06,0.06,0.09,0.0,4.5,0.0},
                {0.53,0.54,0.52,0.0,1.0,0.0},
                {0.12,0.13,0.42,0.0,7.0,0.0},
                {0.1,0.04,0.08,0.0,2.0,0.0},
                {0.1,0.08,0.05,0.0,3.0,0.0},
                {0.07,0.06,0.09,0.0,6.0,0.0},
                {0.51,0.56,0.49,0.0,4.0,0.0},
                {0.33,0.31,0.39,0.0,4.0,0.0},
                {0.37,0.47,0.49,0.0,6.5,0.0},
                {0.45,0.33,0.42,0.0,3.5,0.0},
                {0.57,0.48,0.38,0.0,6.0,0.0},
                {0.35,0.39,0.41,0.0,5.0,0.0},
                {0.06,0.09,0.11,0.0,5.0,0.0},
                {0.45,0.32,0.26,0.0,9.0,0.0},
                {0.47,0.28,0.27,0.0,0.0,1.0},
                {0.48,0.34,0.25,0.0,5.0,0.0},
                {0.49,0.46,0.49,0.0,4.0,0.0},
                {0.56,0.5,0.51,0.0,3.0,0.0},
                {0.38,0.44,0.47,0.0,5.5,0.0},
                {0.46,0.44,0.44,0.0,9.0,0.0},
                {0.6,0.45,0.5,0.0,4.0,0.0},
                {0.53,0.8,0.56,0.0,4.5,0.0},
                {0.56,0.55,0.56,0.0,9.5,0.0},
                {0.42,0.45,0.52,0.0,8.0,0.0},
                {0.52,0.48,0.53,0.0,0.0,2.0},
                {0.05,0.1,0.09,0.0,8.0,0.0},
                {0.1,0.1,0.07,0.0,8.0,0.0},
                {0.13,0.16,0.18,0.0,7.5,0.0},
                {0.31,0.31,0.49,0.0,3.0,0.0},
                {0.39,0.51,0.51,0.0,6.5,0.0},
                {0.45,0.46,0.53,0.0,4.0,0.0},
                {0.52,0.49,0.33,0.0,2.0,0.0},
                {0.41,0.42,0.43,0.0,3.0,0.0},
                {0.14,0.27,0.53,0.0,3.0,0.0},
                {0.11,0.1,0.07,0.0,3.0,0.0},
                {0.28,0.27,0.29,0.0,3.0,0.0},
                {0.45,0.49,0.49,0.0,4.0,0.0},
                {0.32,0.41,0.41,0.0,7.0,0.0},
                {0.28,0.26,0.27,0.0,4.0,0.0},
                {0.05,0.06,0.1,0.0,2.0,0.0},
                {0.09,0.09,0.07,0.0,4.0,0.0},
                {0.24,0.46,0.57,0.0,0.0,1.0},
                {0.32,0.57,0.51,0.0,6.0,0.0},
                {0.27,0.34,0.22,0.0,7.0,0.0},
                {0.3,0.46,0.54,0.0,5.0,0.0},
                {0.3,0.3,0.42,0.0,6.0,0.0},
                {0.36,0.33,0.34,0.0,6.0,0.0},
                {0.4,0.43,0.25,0.0,5.0,0.0},
                {0.08,0.09,0.09,0.0,7.0,0.0},
                {0.28,0.4,0.47,0.0,9.0,0.0},
                {0.28,0.19,0.18,0.0,7.0,0.0},
                {0.35,0.49,0.52,0.0,9.0,0.0},
                {0.36,0.42,0.52,0.0,6.0,0.0},
                {0.23,0.33,0.43,0.0,0.0,0.0},
                {0.07,0.09,0.1,0.0,0.0,0.0},
                {0.28,0.54,0.51,0.0,3.5,0.0},
                {0.45,0.44,0.58,0.0,6.5,0.0},
                {0.15,0.15,0.63,0.0,7.0,0.0},
                {0.15,0.42,0.46,0.0,5.0,0.0},
                {0.35,0.37,0.35,0.0,5.0,0.0},
                {0.6,0.25,0.39,0.0,4.0,0.0},
                {0.06,0.1,0.09,0.0,0.0,0.0},
                {0.07,0.09,0.1,0.0,6.0,0.0},
                {0.18,0.24,0.18,0.0,6.0,0.0},
                {0.41,0.47,0.46,0.0,5.0,0.0},
                {0.38,0.18,0.2,0.0,4.0,0.0},
                {0.07,0.07,0.08,0.0,2.0,0.0},
                {0.09,0.08,0.07,0.0,4.0,0.0},
                {0.07,0.07,0.08,0.0,4.5,0.0},
                {0.2,0.33,0.53,0.0,0.0,0.0},
                {0.25,0.34,0.34,0.0,5.0,0.0},
                {0.08,0.08,0.09,0.0,3.0,0.0},
                {0.17,0.24,0.41,0.0,0.0,0.0},
                {0.17,0.34,0.39,0.0,0.0,0.0},
                {0.33,0.21,0.18,0.0,1.0,0.0},
                {0.38,0.34,0.27,0.0,2.0,0.0},
                {0.07,0.07,0.4,0.0,2.0,0.0},
                {0.14,0.35,0.37,0.0,0.0,0.0},
                {0.08,0.08,0.09,0.0,2.5,0.0},
                {0.09,0.2,0.29,0.0,4.0,0.0},
                {0.13,0.39,0.23,0.0,0.0,0.0},
                {0.11,0.1,0.07,0.0,3.0,0.0},
                {0.16,0.16,0.12,0.0,0.0,0.0},
                {0.16,0.18,0.2,0.0,0.0,0.0},
                {0.17,0.24,0.53,0.0,2.0,0.0},
                {0.22,0.26,0.25,0.0,3.0,0.0},
                {0.15,0.29,0.61,0.0,2.0,0.0},
                {0.09,0.1,0.08,0.0,2.0,0.0},
                {0.07,0.07,0.08,0.0,0.0,0.0},
                {0.09,0.07,0.07,0.0,2.0,0.0},
                {0.08,0.32,0.38,0.0,4.5,0.0},
                {0.1,0.26,0.21,0.0,1.0,0.0},
                {0.08,0.07,0.07,0.0,3.0,0.0},
                {0.14,0.22,0.38,0.0,5.0,0.0},
                {0.29,0.42,0.53,0.0,3.0,0.0},
                {0.15,0.2,0.28,0.0,3.0,0.0},
                {0.17,0.37,0.42,0.0,2.0,0.0},
                {0.09,0.1,0.1,0.0,2.0,0.0},
                {0.17,0.2,0.35,0.0,4.5,0.0},
                {0.06,0.09,0.09,0.0,4.0,0.0},
                {0.09,0.1,0.07,0.0,2.5,0.0},
                {0.09,0.09,0.06,0.0,3.0,0.0},
                {0.23,0.2,0.16,0.0,8.0,0.0},
                {0.21,0.26,0.38,0.0,4.0,0.0},
                {0.16,0.18,0.13,0.0,3.0,0.0},
                {0.13,0.27,0.12,0.0,5.0,0.0},
                {0.2,0.22,0.14,0.0,4.0,0.0},
                {0.1,0.19,0.28,0.0,3.0,0.0}
        };
        System.out.println("Y.length="+Y.length);
        System.out.println("X.length="+X.length);
        OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
        regression.setNoIntercept(true);
        regression.newSampleData(Y, X);

        double[] beta = regression.estimateRegressionParameters();

    }

    public static void main(String[] args) {
        try {
            SampleTest test = new SampleTest();
            test.testMatrix();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}