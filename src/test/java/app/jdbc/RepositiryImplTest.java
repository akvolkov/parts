package app.jdbc;

import app.Utils.ConnectionUtill;
import app.models.Filter;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;


public class RepositiryImplTest {
    final Connection postgresConnection = ConnectionUtill.getPostgresConnection();

    @Test
    public void stringToSQLDateTest1() {
        Filter filter = new Filter(
                "1",
                "",
                "",
                "0",
                new Filter.TimeRange("Jan 01, 2019", "Dec 12, 2019"),
                new Filter.TimeRange("Jan 01, 2019", "Dec 12, 2019"));
        assertEquals(new RepositoryImpl(postgresConnection).getListOfParts(filter).size(), 4);
    }

    @Test
    public void stringToSQLDateTest2() {
        Filter filter = new Filter(
                "",
                "",
                "",
                "0",
                new Filter.TimeRange("", ""),
                new Filter.TimeRange("", ""));
        assertEquals(new RepositoryImpl(postgresConnection).getListOfParts(filter).size(), 14);
    }

    @Test
    public void stringToSQLDateTest3() {
        Filter filter = new Filter(
                "blade",
                "",
                "",
                "",
                new Filter.TimeRange("", ""),
                new Filter.TimeRange("", ""));
        assertEquals(new RepositoryImpl(postgresConnection).getListOfParts(filter).size(), 2);
    }

    @Test
    public void stringToSQLDateTest4() {
        Filter filter = new Filter(
                "",
                "1",
                "",
                "",
                new Filter.TimeRange("", ""),
                new Filter.TimeRange("", ""));
        assertEquals(new RepositoryImpl(postgresConnection).getListOfParts(filter).size(), 6);
    }

    @Test
    public void stringToSQLDateTest5() {
        Filter filter = new Filter(
                "",
                "",
                "steel",
                "",
                new Filter.TimeRange("", ""),
                new Filter.TimeRange("", ""));
        assertEquals(new RepositoryImpl(postgresConnection).getListOfParts(filter).size(), 7);
    }

    @Test
    public void stringToSQLDateTest6() {
        Filter filter = new Filter(
                "",
                "",
                "",
                "64",
                new Filter.TimeRange("", ""),
                new Filter.TimeRange("", ""));
        assertEquals(new RepositoryImpl(postgresConnection).getListOfParts(filter).size(), 6);
    }

    @Test
    public void stringToSQLDateTest7() {
        Filter filter = new Filter(
                "",
                "",
                "",
                "",
                new Filter.TimeRange("Jan 01, 2019", ""),
                new Filter.TimeRange("", ""));
        assertEquals(new RepositoryImpl(postgresConnection).getListOfParts(filter).size(), 8);
    }

    @Test
    public void stringToSQLDateTest8() {
        Filter filter = new Filter(
                "",
                "",
                "",
                "",
                new Filter.TimeRange("Jan 01, 2019", "Dec 12, 2019"),
                new Filter.TimeRange("Jan 01, 2019", "Dec 12, 2019"));
        assertEquals(new RepositoryImpl(postgresConnection).getListOfParts(filter).size(), 8);
    }
}
