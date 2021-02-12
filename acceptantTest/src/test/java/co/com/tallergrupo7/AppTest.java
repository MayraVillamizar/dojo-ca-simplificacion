/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package co.com.tallergrupo7;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {
    @Test
    public void testParallel() {
        Results results = Runner.parallel(getClass(),2);
        generateReport(results.getReportDir());
        Assert.assertTrue(results.getErrorMessages(), results.getFailCount() == 0);
    }

    public static void generateReport(String karateOutputPath) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[]{"json"}, true);
        List<String> jsonPaths = new ArrayList<String>(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        Configuration config = new Configuration(new File("target"), "pruebas integracion");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }
}
