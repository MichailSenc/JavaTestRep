package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public class Func {
    private double xmin, xmax;
    private int step;

    public Func(double min, double max, int numStep){
        xmin = min;
        xmax = max;
        step = numStep;
    }

    private double f(double x){
        return (3 - 4*x)/(x*x+1);
    }
    public String getName(){
        return "(3 - 4*x)/(x*x+1)";
    }

    public  XYChart.Series getSeries(){
        ObservableList<XYChart.Data> datas = FXCollections.observableArrayList();
        double x0 = xmin;
        double h = (xmax- x0) / step;
        for (int i = 0; i < step; i++) {
            datas.add(new XYChart.Data(x0 + h * i, f(x0 + h * i)));
        }
        XYChart.Series series = new XYChart.Series();
        series.setName(getName());
        series.setData(datas);
        return series;
    }
}
