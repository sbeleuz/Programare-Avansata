package app;

import dao.ChartControllerDb;

import java.sql.Connection;

public class Task implements Runnable {
    private final String name;
    private final Connection connection;

    public Task(String name, Connection connection) {
        this.name = name;
        this.connection = connection;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        ChartControllerDb chartController = new ChartControllerDb(connection);
        chartController.getAllCharts();

        System.out.println(name + " done!");
    }
}
