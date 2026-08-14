package dataProvider;

import org.testng.annotations.DataProvider;
import utilities.ExcelUtility;

public class DataProviderClass {
    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() {

        String filePath =
                "src/main/resources/testdata.xlsx";

        ExcelUtility excel =
                new ExcelUtility(
                        filePath,
                        "Sheet1"
                );

        int rowCount = excel.getRowCount();
        int columnCount = excel.getColumnCount();

        Object[][] data =
                new Object[rowCount][columnCount];

        System.out.println("========Excel data===========");

        for (int i = 0; i < rowCount; i++) {

            for (int j = 0; j < columnCount; j++) {

                data[i][j] =
                        excel.getCellData(i + 1, j);
                //adding print statment to validate whether the data is scrapped from excel for test execution
                System.out.println("Row: "+i+" | Column "+j+" | Value: "+data[i][j]);
            }
        }
        System.out.println("=========================================");

        excel.closeWorkbook();

        return data;
    }

}

