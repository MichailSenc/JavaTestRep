package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.*;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Tab tabBarChart = new Tab("BarChart", createBarChart());
        Tab tabLineChart = new Tab("LineChart", createLineChart());
        Tab tabPieChart = new Tab("PieChart", createPieChart());
        Tab tabCanvas = new Tab("Canvas", createCanvas());
        TabPane root = new TabPane();
        root.getTabs().addAll(tabBarChart, tabLineChart, tabPieChart, tabCanvas);
        primaryStage.setTitle("Charts example");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private Group createBarChart() {

        Group groupChart = new Group();

        String austria = "Austria", brazil = "Brazil", france = "France",
                italy = "Italy", usa = "USA";
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc
                = new BarChart<String, Number>(xAxis, yAxis);
        bc.setTitle("Country Summary");
        xAxis.setLabel("Country");
        yAxis.setLabel("Value");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");
        series1.getData().add(new XYChart.Data(austria, 25601.34));
        series1.getData().add(new XYChart.Data(brazil, 20148.82));
        series1.getData().add(new XYChart.Data(france, 10000));
        series1.getData().add(new XYChart.Data(italy, 35407.15));
        series1.getData().add(new XYChart.Data(usa, 12000));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("2004");
        series2.getData().add(new XYChart.Data(austria, 57401.85));
        series2.getData().add(new XYChart.Data(brazil, 41941.19));
        series2.getData().add(new XYChart.Data(france, 45263.37));
        series2.getData().add(new XYChart.Data(italy, 117320.16));
        series2.getData().add(new XYChart.Data(usa, 14845.27));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("2005");
        series3.getData().add(new XYChart.Data(austria, 45000.65));
        series3.getData().add(new XYChart.Data(brazil, 44835.76));
        series3.getData().add(new XYChart.Data(france, 18722.18));
        series3.getData().add(new XYChart.Data(italy, 17557.31));
        series3.getData().add(new XYChart.Data(usa, 92633.68));

        bc.getData().addAll(series1, series2, series3);


        groupChart.getChildren().add(bc);
        return groupChart;

    }

    private Group createLineChart(){
        Group groupChart = new Group();

        Func f = new Func(-5, 5, 600);

        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis();

        LineChart<Number, Number> numberLineChart = new LineChart<>(x, y);
        numberLineChart.setCreateSymbols(false);
        numberLineChart.setLayoutX(400);
        numberLineChart.setLayoutY(60);
        numberLineChart.setMinHeight(600);
        numberLineChart.setMinWidth(600);
        numberLineChart.getData().add(f.getSeries());

        groupChart.getChildren().add(numberLineChart);
        return groupChart;
    }


    private Group createPieChart() {
        Group groupChart = new Group();

        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                new PieChart.Data("Grapefruit", 13),
                new PieChart.Data("Oranges", 25),
                new PieChart.Data("Plums", 10),
                new PieChart.Data("Pears", 22),
                new PieChart.Data("Apples", 30));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Imported Fruits");
        chart.setLegendSide(Side.LEFT);

        final Label caption = new Label("");
        caption.setTextFill(Color.DARKBLUE);
        caption.setStyle("-fx-font: 16 arial;");
        groupChart.getChildren().add(caption);

        for (final PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
                caption.setTranslateX(e.getSceneX());
                caption.setTranslateY(e.getSceneY());
                caption.setText(data.getPieValue() + "%");
                caption.toFront();
            });
        }
        groupChart.getChildren().add(chart);
        return groupChart;

    }

    private Group createCanvas() {
        Group groupCanvas = new Group();

        Canvas canvas = new Canvas(450, 700);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setLineWidth(2.0);
        gc.setFill(Color.RED);
        gc.strokeRoundRect(10, 10, 50, 50, 10, 10);
        gc.fillRect(100, 10, 50, 50);
        gc.setFill(Color.BLUE);
        gc.strokeOval(10, 70, 50, 30);
        gc.fillOval(100, 70, 50, 30);
        gc.strokeLine(200, 50, 300, 50);
        gc.strokeArc(320, 10, 50, 50, 40, 80, ArcType.ROUND);
        gc.fillArc(320, 70, 50, 50, 00, 120, ArcType.OPEN);

        gc.setFill(Color.GREEN);
        gc.beginPath();
        gc.moveTo(50, 150);
        gc.quadraticCurveTo(30, 250, 300, 300);
        gc.fill();
        gc.closePath();

        gc.setStroke(Color.OLIVE);
        gc.beginPath();
        gc.moveTo(350, 150);
        gc.quadraticCurveTo(330, 250, 450, 300);
        gc.stroke();
        gc.closePath();

        gc.setStroke(Color.MAGENTA);
        double[] xpoints = {10, 85, 110, 135, 210, 160,
                170, 110, 50, 60};
        double[] ypoints = {385, 375, 310, 375, 385, 425,
                490, 450, 490, 425};

        gc.strokePolygon(xpoints, ypoints, xpoints.length);

        Stop[] stops1 = new Stop[] { new Stop(0.2, Color.BLACK),
                new Stop(0.5, Color.RED), new Stop(0.8, Color.BLACK)};

        LinearGradient lg = new LinearGradient(0, 0, 1, 0, true,
                CycleMethod.NO_CYCLE, stops1);

        gc.setFill(lg);
        gc.fillRect(50, 530, 200, 120);

        Stop[] stops2 = new Stop[] { new Stop(0, Color.RED),
                new Stop(1, Color.BLACK)};

        RadialGradient rg = new RadialGradient(0, 0, 0.5, 0.5, 0.7, true,
                CycleMethod.NO_CYCLE, stops2);

        gc.setFill(rg);
        gc.fillOval(300, 530, 150, 150);

        groupCanvas.getChildren().add(canvas);

        return groupCanvas;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
