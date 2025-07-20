package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

    // DataProvider 1

    @DataProvider(name = "Data")
    public String[][] getAllData() throws Exception {
        String path = ".\\testData\\StudentDetails.xlsx"; // taking xl file from testData

        ExcelUtility xlutil = new ExcelUtility(path); // creating an object for XLUtility

        int totalrows = xlutil.getRowCount("sheet1");
        int totalcols = xlutil.getCellCount("sheet1", 1);

        String apiData[][] = new String[totalrows][totalcols]; // created for two dimension arrays that can store data

        for (int i = 1; i <= totalrows; i++) // 1 read the data from xl storing in a two-dimensional array
        {
            for (int j = 0; j < totalcols; j++) // 0 i is rows j is cols
            {

                apiData[i - 1][j] = xlutil.getCellData("sheet1", i, j); // 1,0

            }

        }

        return apiData; // returning two dimension arrays

    }

    // DataProvider 2

    @DataProvider(name = "StudentID")
    public String[] getStudentId() throws Exception {
        String path = ".\\testData\\StudentDetails.xlsx"; // taking xl file from testData

        ExcelUtility xlutil = new ExcelUtility(path); // creating an object for XLUtility

        int totalrows = xlutil.getRowCount("sheet1");

        String apiData[] = new String[totalrows];

        for (int i = 1; i <= totalrows; i++) // 1 read the data from xl storing in a two-dimensional array
        {


            apiData[i - 1] = xlutil.getCellData("sheet1", i, 0); // 1,0 Declare, which can be declared to dete by using that 0 or 1 in my case I want to delete data by I'd which can be placed in position at 0.

        }


        return apiData; // returning two dimension arrays

    }

    // DataProvider 3

    // DataProvider 4

}
