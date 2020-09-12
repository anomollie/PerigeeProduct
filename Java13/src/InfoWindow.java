import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.glyphfont.Glyph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class InfoWindow extends GridPane{
    private ImageView homeImage = new ImageView(new Image("/home.png"));

    public InfoWindow(double width, double height, boolean stroke) {
        this.setPrefSize(width, height);
        this.setMaxHeight(height);
        this.setBackground(new Background(new BackgroundFill(Color.web("0x262262"), new CornerRadii(5), Insets.EMPTY)));
        if (stroke) {
            this.setBorder(new Border(new BorderStroke(Color.web("0x48C4F2"), BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2, 2, 2, 2))));
        }
//        this.setFill(Color.BLACK);
//        this.setArcHeight(10);
//        this.setArcWidth(10);
//        this.setSt
//        if (stroke) {
//            this.setStroke(Color.web("0x48C4F2"));
//            this.setStrokeWidth(2);
//        }
    }

    public void addTextField() throws FileNotFoundException {
//        CustomTextField t = new CustomTextField();
//        Glyph g = new Glyph("FontAwesome", "SEARCH");
//        t.setLeft(g);
//
//        g.setColor(Color.WHITE);
//        g.setFontSize(35);
//        t.getLeft().setLayoutX(t.getLayoutX() - 20);
//        t.setFont(Font.font("Ariel", 20));
//        t.setId("ctf");
//        t.setStyle("-fx-text-fill: white");
//        t.setStyle("-fx-prompt-text-fill: white;");
        Button homeButton = new Button("", homeImage);
        HBox h = new HBox();
        HBox h2 = new HBox();
        HBox h3 = new HBox();
//        Text te = new Text("PERIGEE");
//        te.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 50));
//        te.setFill(Color.web("0x048194"));
        ImagePattern i = new ImagePattern(new Image(new FileInputStream("Java13\\src\\pp.png")));
        Ellipse r = new Ellipse(0, 0, 104.57, 53.86);
        r.setTranslateY(6.5);
        r.setFill(i);
//        h3.getChildren().add(te);
        h3.getChildren().add(r);
        Text text = new Text("HVAC FAN");
        text.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 50));
        text.setFill(Color.WHITE);
//        t.setBackground(new Background(new BackgroundFill(Color.web("0x262262"), CornerRadii.EMPTY, Insets.EMPTY)));
//        t.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 3, 0))));
//        t.setPromptText("Search for a device");
//        t.setPrefSize(350, 50);
        h.getChildren().add(text);
//        h2.getChildren().add(t);
//        h2.getChildren().add(homeButton);
//        h2.setAlignment(Pos.CENTER_RIGHT);
        h.setPadding(new Insets(0, 0, 0, 30));
        this.setHgap(630);
        this.setAlignment(Pos.BOTTOM_LEFT);
        HBox hh = new HBox();
        hh.getChildren().add(h3);
        hh.getChildren().add(h);
        hh.getChildren().add(h2);
        hh.setAlignment(Pos.CENTER);
        h.setAlignment(Pos.CENTER);
//        h2.setAlignment(Pos.CENTER_RIGHT);
        h3.setAlignment(Pos.CENTER_LEFT);
        h.setTranslateX(40);
        HBox.setMargin(h, new Insets(0, 100, 0, 100));
//        HBox.setMargin(h2, new Insets(0, 10, 0, 100));
        HBox.setMargin(h3, new Insets(0, 100, 0, 10));
        hh.setTranslateY(-10);

//        this.add(h, 0, 0);
//        this.add(h2, 1, 0);
        this.add(hh, 0, 0);
    }

    public void makeLeftMid() {
        VBox v = new VBox();
        Text t = new Text("DAYS SINCE INSTALL: ");
        Text t2 = new Text("MANUFACTURER: ");
        Text t3 = new Text("IP ADDR: ");
        Text t4 = new Text("MAC ADDR: ");
        Text t5 = new Text("LOCATION: ");
        t.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 20));
        t2.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 20));
        t3.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 20));
        t4.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 20));
        t5.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 20));
        t.setFill(Color.web("0x48C4F2"));
        t2.setFill(Color.web("0x48C4F2"));
        t3.setFill(Color.web("0x48C4F2"));
        t4.setFill(Color.web("0x48C4F2"));
        t5.setFill(Color.web("0x48C4F2"));
        Text tt = new Text("60");
        Text tt2 = new Text("ESPRESSIF");
        Text tt3 = new Text("192.168.68.42");
        Text tt4 = new Text("10:52:1C:CF:BB:74");
        Text tt5 = new Text("BEDROOM, 3RD FLOOR");
        tt.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 20));
        tt2.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 20));
        tt3.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 20));
        tt4.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 20));
        tt5.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 20));
        tt.setFill(Color.WHITE);
        tt2.setFill(Color.WHITE);
        tt3.setFill(Color.WHITE);
        tt4.setFill(Color.WHITE);
        tt5.setFill(Color.WHITE);
        TextFlow tf = new TextFlow(t, tt);
        TextFlow tf2 = new TextFlow(t2, tt2);
        TextFlow tf3 = new TextFlow(t3, tt3);
        TextFlow tf4 = new TextFlow(t4, tt4);
        TextFlow tf5 = new TextFlow(t5, tt5);
//        v.getChildren().add(t);
//        v.getChildren().add(t2);
//        v.getChildren().add(t3);
//        v.getChildren().add(t4);
//        v.getChildren().add(t5);
        v.getChildren().add(tf);
        v.getChildren().add(tf2);
        v.getChildren().add(tf3);
        v.getChildren().add(tf4);
        v.getChildren().add(tf5);
        this.setPadding(new Insets(0, 0, 0, 5));
        this.setHgap(5);
        this.setVgap(7);
        this.add(v, 0, 0);
//        this.add(t, 0, 0);
//        this.add(t2, 0, 1);
//        this.add(t3, 0, 2);
//        this.add(t4, 0, 3);
//        this.add(t5, 0, 4);
    }

    public void makeRightMid() {
        StackPane sp = new StackPane();
        Arc rarc = new Arc(0, 0, 120, 120, 90, -360);
        rarc.setStroke(Color.web("0xed1c23"));
        rarc.setStrokeWidth(12);
        rarc.setFill(Color.TRANSPARENT);
        //this.add(rarc, 0, 0);
        this.setAlignment(Pos.CENTER);
        Arc garc = new Arc(0, 0, 120, 120, 90, -328.68);
        garc.setStroke(Color.web("0x32cd32"));
        garc.setStrokeWidth(12);
        garc.setFill(Color.TRANSPARENT);
        Text n = new Text();
        DecimalFormat d = new DecimalFormat("0.0");
        String temp = d.format(Math.abs(garc.getLength()/360)*100) + "%";
        n.setText(temp);
        n.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 50));
        n.setFill(Color.web("0x48C4F2"));
        n.setX(garc.getCenterX() - garc.getRadiusX()/2);
        n.setY(garc.getCenterY() + garc.getRadiusY()/8);
        n.setManaged(false);
        Text n2 = new Text("DEVICE HYGIENE");
        n2.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 20));
        n2.setFill(Color.web("0x48c4f2"));
        n2.setTranslateX(garc.getCenterX() - garc.getRadiusX()/1.6);
        n2.setTranslateY(garc.getCenterY() + garc.getRadiusY()/3);
        n2.setManaged(false);
        n.setTranslateX(-5);
//        Text t = new Text("AVERAGE DEVICE HYGIENE SCORE");
//        t.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 15));
//        t.setFill(Color.web("0x48C4F2"));
//        t.setWrappingWidth(100);
//        t.setX(0);
//        t.setY(200);
//        this.add(garc, 0, 0);
////        this.add(t, 0, 0);
//        this.add(n, 0, 0);
        Transition transition = new Transition() {
            {
                setCycleDuration(Duration.INDEFINITE);
                setAutoReverse(true);
            }
            @Override
            protected void interpolate(double frac) {
                if (frac < 0.16 || (frac > 0.32 && frac < 0.48) || (frac > 0.64 && frac < 0.8)) {
                    getB();
                }
                else if ((frac > 0.16 && frac < 0.32) || (frac > 0.48 && frac < 0.64) || frac > 0.8){
                    getR();
                }
            }
        };
        this.setAlignment(Pos.CENTER);
        Pane p = new Pane();
        sp.getChildren().add(rarc);
        sp.getChildren().add(garc);
        sp.getChildren().add(n);
        sp.getChildren().add(n2);
        this.add(sp, 0, 0);
        garc.setManaged(false);
        rarc.setManaged(false);
        garc.setOnScroll(e -> {
            garc.setLength(garc.getLength() + 1);
            DecimalFormat df = new DecimalFormat("0.0");
            String temp2 = df.format(Math.abs(garc.getLength()/360)*100) + "%";
            if (Math.abs(garc.getLength()/360*100) < 5) {
                transition.play();
            }
            else {
                transition.stop();
            }
//            if (Math.abs(garc.getLeorg.controlsfx:controlsfx:11.0.2ngth()/360*100) < 50) {
//                n.setFill(Color.web("0xed1c23"));
//            }
//            else {
//                n.setFill(Color.web("0x48C4F2"));
//            }
            n.setText(temp2);
        });
    }
    int t2 = 0;
    ArrayList a2 = new ArrayList(Arrays.asList(100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0
            ));
    private double getValue2() {
        t2 += 1;
        if (t2 >= a2.size()) {
            return 100.0;
        }
        return (double) a2.get(t2);
    }
    public void makeMidMid() {
        VBox v = new VBox();
        HBox h = new HBox();
        Glyph g = new Glyph("FontAwesome", "EXPAND");
        g.setColor(Color.WHITE);
        g.setFontSize(15);
        final int size = 30;
        CategoryAxis x = new CategoryAxis();
        NumberAxis y = new NumberAxis();
        CategoryAxis fullx = new CategoryAxis();
        NumberAxis fully = new NumberAxis();

        y.setUpperBound(100.0);
        y.setLowerBound(0.0);
        y.setAutoRanging(false);
        x.setBorder(new Border(new BorderStroke(Color.web("0x48C4F2"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3, 0, 0, 0))));
        x.setTickLabelFill(Color.WHITE);
        y.setBorder(new Border(new BorderStroke(Color.web("0x48C4F2"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 3, 0, 0))));
        y.setTickLabelFill(Color.WHITE);
        x.setTickLabelFont(Font.font("Ariel", FontWeight.BOLD, 13));
        y.setTickLabelFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 13));
        y.setMinorTickCount(0);
        y.setTickUnit(1000);
        x.setAutoRanging(true);

        fully.setUpperBound(100.0);
        fully.setLowerBound(0.0);
        fully.setAutoRanging(false);
        fullx.setBorder(new Border(new BorderStroke(Color.web("0x48C4F2"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3, 0, 0, 0))));
        fullx.setTickLabelFill(Color.WHITE);
        fully.setBorder(new Border(new BorderStroke(Color.web("0x48C4F2"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 3, 0, 0))));
        fully.setTickLabelFill(Color.WHITE);
        fullx.setTickLabelFont(Font.font("Ariel", FontWeight.BOLD, 13));
        fully.setTickLabelFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 13));
        fully.setMinorTickCount(0);
        fully.setTickUnit(1000);
        fullx.setAutoRanging(true);

        LineChart c = new LineChart(x, y);
        LineChart fullchart = new LineChart(fullx, fully);
        fullchart.setAnimated(false);
        c.setAnimated(false);
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        XYChart.Series<String, Number> fullseries = new XYChart.Series<>();
        fullchart.getData().add(fullseries);
        c.getData().add(series);
        x.setLabel("Time: HH:MM:SS");
        y.setLabel("% Availability");
        fullx.setLabel("Time: HH:MM:SS");
        fully.setLabel("% Availability");
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> {
                double random = getValue2();
                Date now = new Date();
                series.getData().add(new XYChart.Data<String, Number>(sdf.format(now), random));
                fullseries.getData().add(new XYChart.Data<>(sdf.format(now), random));
                if (series.getData().size() > size) {
                    series.getData().remove(0);
                }
            });
        }, 0, 1, TimeUnit.SECONDS);
        Node line = series.getNode().lookup(".chart-series-line");
        Node fullline = fullseries.getNode().lookup(".chart-series-line");
        fullline.setStyle("-fx-stroke-width: 7;");
        line.setStyle("-fx-stroke-width: 7;");
        fullchart.setCreateSymbols(false);
        fullchart.setLegendVisible(false);
        fullchart.setTitle("DEVICE AVAILABILITY");
        fullchart.setTitleSide(Side.BOTTOM);
        fullchart.setStyle("-fx-text-fill: 0x48C4F2");
        fullchart.setPrefSize(600, 300);
        fullchart.setStyle("-fx-fill: transparent");
        fullchart.setStyle("-fx-stroke: tranparent");
        fullchart.setTranslateX(-10);
        fullchart.getXAxis().lookup(".axis-label").setStyle("-fx-label-padding: 0 0 -10 0");

        c.setCreateSymbols(false);
        c.setLegendVisible(false);
        c.setTitle("DEVICE AVAILABILITY");
        c.setTitleSide(Side.BOTTOM);
        c.setStyle("-fx-text-fill: 0x48C4F2");
        c.setPrefSize(600, 300);
        c.setStyle("-fx-fill: transparent");
        c.setStyle("-fx-stroke: tranparent");
        c.setTranslateX(-10);
        c.getXAxis().lookup(".axis-label").setStyle("-fx-label-padding: 0 0 -10 0");
        c.setPadding(new Insets(0, 0, -5, 0));
        Stage s = new Stage();
        HBox layout = new HBox(10);
        layout.setStyle("-fx-background-color: #262262; -fx-padding: 10;");
        layout.getChildren().add(fullchart);
        Scene ss = new Scene(layout);
        ss.getStylesheets().add("style.css");
        s.setScene(ss);
        g.setOnMouseClicked(e -> s.show());
        h.getChildren().add(g);
        h.setAlignment(Pos.CENTER_RIGHT);
        v.getChildren().add(h);
        v.getChildren().add(c);
        this.add(v, 0, 0);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(5, 5, 5, 5));
    }
    private Border getB() {
        this.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2, 2, 2, 2))));
        return this.getBorder();
    }
    private Border getR() {
        this.setBorder(new Border(new BorderStroke(Color.web("0x48C4F2"), BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2, 2, 2, 2))));
        return this.getBorder();
    }
    public void makeMidBot() {
        final int size = 30;
        VBox v = new VBox();
        HBox h = new HBox();
        Glyph g = new Glyph("FontAwesome", "EXPAND");
        g.setColor(Color.WHITE);
        g.setFontSize(15);
        Transition tra = new Transition() {
            {
                setCycleDuration(Duration.seconds(3));
                setAutoReverse(true);
            }
            @Override
            protected void interpolate(double frac) {
                if (frac < 0.16 || (frac > 0.32 && frac < 0.48) || (frac > 0.64 && frac < 0.8)) {
                    getB();
                }
                else if ((frac > 0.16 && frac < 0.32) || (frac > 0.48 && frac < 0.64) || frac > 0.8){
                    getR();
                }
            }
        };
        CategoryAxis x = new CategoryAxis();
        NumberAxis y = new NumberAxis();
        CategoryAxis fullx = new CategoryAxis();
        NumberAxis fully = new NumberAxis();
        x.setLabel("Time: HH:MM:SS");
        y.setLabel("Time Delta");
        x.setBorder(new Border(new BorderStroke(Color.web("0x48C4F2"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3, 0, 0, 0))));
        x.setTickLabelFill(Color.WHITE);
        y.setBorder(new Border(new BorderStroke(Color.web("0x48C4F2"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 3, 0, 0))));
        y.setTickLabelFill(Color.WHITE);
        x.setTickLabelFont(Font.font("Ariel", FontWeight.BOLD, 13));
        y.setTickLabelFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 13));
        y.setMinorTickCount(0);
        y.setTickUnit(1000);

        fullx.setBorder(new Border(new BorderStroke(Color.web("0x48C4F2"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3, 0, 0, 0))));
        fullx.setTickLabelFill(Color.WHITE);
        fully.setBorder(new Border(new BorderStroke(Color.web("0x48C4F2"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 3, 0, 0))));
        fully.setTickLabelFill(Color.WHITE);
        fullx.setTickLabelFont(Font.font("Ariel", FontWeight.BOLD, 13));
        fully.setTickLabelFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 13));
        fully.setMinorTickCount(0);
        fully.setTickUnit(1000);

        LineChart c = new LineChart(x, y);
        LineChart fullchart = new LineChart(fullx, fully);
        fullchart.setAnimated(false);
        c.setAnimated(false);
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        XYChart.Series<String, Number> fullseries = new XYChart.Series<>();
        fullchart.getData().add(fullseries);
        fullx.setLabel("Time: HH:MM:SS");
        fully.setLabel("Time Delta");
        c.getData().add(series);
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(() -> {

//            Integer random = ThreadLocalRandom.current().nextInt(100);
            Platform.runLater(() -> {
                double random = getValue();
                Date now = new Date();
                series.getData().add(new XYChart.Data<String, Number>(sdf.format(now), random));
                fullseries.getData().add(new XYChart.Data<>(sdf.format(now), random));
                if (series.getData().size() > size) {
                    series.getData().remove(0);
                }
                if (random > 200) {
                    tra.play();
                }
            });
        }, 0, 1, TimeUnit.SECONDS);

        fullchart.setCreateSymbols(false);
        fullchart.setLegendVisible(false);
        fullchart.setTitle("PACKET TRANSMISSION");
        fullchart.setTitleSide(Side.BOTTOM);
        fullchart.setStyle("-fx-text-fill: 0x48C4F2");
        fullchart.setPrefSize(600, 300);
        fullchart.setStyle("-fx-fill: transparent");
        fullchart.setStyle("-fx-stroke: tranparent");
        fullchart.setTranslateX(-10);
        fullchart.getXAxis().lookup(".axis-label").setStyle("-fx-label-padding: 0 0 -10 0");
        fullchart.setPrefSize(this.getPrefWidth()*2, this.getPrefHeight()*2);
        fullchart.setMaxSize(this.getPrefWidth()*2, this.getPrefHeight()*2);

        c.setCreateSymbols(false);
        c.setLegendVisible(false);
        c.setTitle("PACKET TRANSMISSION");
        c.setTitleSide(Side.BOTTOM);
        c.setStyle("-fx-text-fill: 0x48C4F2");
        c.setPrefSize(600, 300);
        c.setStyle("-fx-fill: transparent");
        c.setStyle("-fx-stroke: tranparent");
        c.setPrefSize(this.getPrefWidth()*2, this.getPrefHeight()*2);
        c.setMaxSize(this.getPrefWidth()*2, this.getPrefHeight()*2);
        c.setTranslateX(-10);
        c.getXAxis().lookup(".axis-label").setStyle("-fx-label-padding: 0 0 -10 0");
        c.setPadding(new Insets(0, 0, -5, 0));
        Stage s = new Stage();
        HBox layout = new HBox(10);
        layout.setStyle("-fx-background-color: #262262; -fx-padding: 10;");
        layout.getChildren().add(fullchart);
        Scene ss = new Scene(layout);
        ss.getStylesheets().add("style.css");
        s.setScene(ss);
        g.setOnMouseClicked(e -> s.show());
        h.getChildren().add(g);
        h.setAlignment(Pos.CENTER_RIGHT);
        v.getChildren().add(h);
        v.getChildren().add(c);
        this.add(v, 0, 0);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(5, 5, 5, 5));
    }
    int t = 0;
    ArrayList a = new ArrayList(Arrays.asList(1.5966475450960166E9, 0.1738753318786621, 0.003443479537963867, 3.2686221599578857, 5.150886297225952, 449.9326250553131, 5.120614051818848, 4.916818618774414, 4.913753509521484, 5.1202192306518555, 4.917128562927246, 10.033626556396484, 4.917613506317139, 5.118251800537109, 4.915096998214722, 5.1321210861206055, 4.903523683547974, 4.918833494186401, 5.117346286773682, 4.915283203125, 5.131742238998413, 4.904232025146484, 5.120259046554565, 4.91477632522583, 24.986648321151733, 4.915360689163208, 5.1203553676605225, 4.915220260620117, 5.120265007019043, 4.9152350425720215, 5.120348215103149, 4.915663480758667, 4.915099382400513, 5.120663404464722, 4.92737603187561, 5.108080625534058, 4.916277647018433, 4.914417743682861, 5.120149612426758, 4.915318012237549, 5.12033486366272, 4.915555715560913, 4.915556192398071, 5.120094299316406, 4.915449857711792, 5.119966506958008, 4.917240381240845, 5.121389389038086, 4.912420749664307, 4.918277025222778, 5.117472171783447, 4.915469169616699, 5.128185033798218, 4.907054424285889, 4.915736675262451, 10.056501626968384, 10.014536142349243, 5.125543832778931, 9.8293297290802, 5.11641263961792, 4.915839433670044, 10.035712242126465, 4.914858102798462, 5.120285749435425, 4.915057897567749, 5.120928525924683, 4.918881416320801, 4.9122960567474365, 5.119449853897095, 4.915390491485596, 5.121309757232666, 4.914024114608765, 5.12312650680542, 4.913605451583862, 4.918470859527588, 5.116919994354248, 5.556726932525635, 4.481313228607178, 4.9244513511657715, 4.903337240219116, 5.1234142780303955, 4.911837339401245, 5.121472358703613, 74.96355843544006, 4.9127373695373535, 5.120417594909668, 4.917529106140137, 5.117710590362549, 34.81702780723572, 5.120073318481445, 4.924755096435547, 5.110694169998169, 4.9151811599731445, 5.1200644969940186, 4.919834852218628, 4.911154270172119, 5.1202216148376465, 4.9151270389556885, 5.120330333709717, 49.97237730026245, 4.918036460876465, 5.118238210678101, 4.919233322143555, 4.920170783996582, 5.145399332046509, 4.882550239562988, 5.124528646469116, 4.912227630615234, 4.914403676986694, 5.121023178100586, 4.914744853973389, 5.122113466262817, 4.913600206375122, 5.12031364440918, 4.915197372436523, 4.92963171005249, 5.106889247894287, 4.912751197814941, 5.122018814086914, 4.915186643600464, 4.915541172027588, 5.120834589004517, 4.912616014480591, 5.122657060623169, 4.91589879989624, 10.03597354888916, 4.914064168930054, 5.120832920074463, 4.914904832839966, 5.120849847793579, 4.914953947067261, 4.9155638217926025, 5.13670539855957, 4.898572683334351, 5.12059211730957, 4.91610860824585, 4.9145729541778564, 5.119241952896118, 10.035582780838013, 4.9161131381988525, 5.119275808334351, 4.915567874908447, 4.917755126953125, 5.117562532424927, 4.91502571105957, 5.120274305343628, 4.915402889251709, 4.916484117507935, 5.118926286697388, 4.916507005691528, 5.1190345287323, 4.91730809211731, 5.119877815246582, 4.915627479553223, 4.913138389587402, 5.120585918426514, 4.915637969970703, 5.12106728553772, 4.914212942123413, 4.915961742401123, 5.121833324432373, 4.913519382476807, 5.119875431060791, 4.91544771194458, 4.918034076690674, 5.117725133895874, 4.915112495422363, 5.121089220046997, 4.914952278137207, 5.120236873626709, 4.915866136550903, 4.914574861526489, 5.12050986289978, 4.91524600982666, 5.122880697250366, 4.9131128787994385, 4.916950702667236, 5.118550062179565, 4.915140151977539, 40.14295792579651, 4.921944618225098, 4.908567190170288, 5.120467185974121, 4.915175914764404, 5.120445251464844, 4.915315628051758, 5.1203367710113525, 4.915371894836426, 4.915256023406982, 5.123517751693726, 4.912396669387817, 5.120194435119629, 4.91618275642395, 4.916956424713135, 5.1181862354278564, 4.915254354476929, 5.120148181915283, 4.91525673866272, 4.915864706039429, 5.357637405395508, 4.677665710449219, 5.120286464691162, 4.91496729850769, 5.120519399642944, 4.91530704498291, 4.919909954071045, 5.115952253341675, 19.866491556167603, 30.106738328933716, 35.025214433670044, 4.914769411087036, 5.118202209472656, 4.916946172714233, 4.915404558181763, 5.121689319610596, 4.913678407669067, 5.120024681091309, 4.916891574859619, 5.11832070350647, 4.915575265884399, 4.916236400604248, 5.119197368621826, 4.914822578430176, 5.119936227798462, 4.927062749862671, 4.904013633728027, 5.1201090812683105, 4.930330514907837, 5.110396862030029, 4.910319566726685, 5.119955539703369, 4.915493488311768, 4.915064573287964, 5.120144844055176, 4.915756702423096, 5.1208655834198, 4.9145472049713135, 4.9150731563568115, 5.120490550994873, 4.915351152420044, 5.120474815368652, 4.91621470451355, 4.926150560379028, 5.10861873626709, 4.917126655578613, 5.11844801902771, 4.920069694519043, 5.11554741859436, 4.917182683944702, 4.9339916706085205, 50.157703161239624, 164.88643050193787, 5.104398250579834, 4.915266513824463, 14.95128345489502, 5.119933366775513, 10.035850524902344, 4.916079759597778, 4.915972948074341, 5.119173526763916, 4.915910959243774, 5.119347810745239, 4.91592264175415, 4.914853811264038, 5.120319128036499, 4.915960788726807, 5.119511842727661, 4.915642976760864, 4.936186790466309, 5.099516868591309, 4.915177822113037, 5.120410442352295, 4.94026517868042, 5.09536600112915, 4.916879892349243, 4.914708614349365, 5.122333765029907, 4.915240287780762, 5.119262933731079, 4.915676116943359, 4.933428764343262, 5.100071907043457, 4.917083263397217, 5.118641138076782, 4.938495874404907, 5.097337484359741, 4.9162375926971436, 4.914438724517822, 5.120950698852539, 4.914702653884888, 5.119809150695801, 4.916240692138672, 4.915907621383667, 5.119184970855713, 14.951117992401123, 4.914977312088013, 5.1206889152526855, 4.915256023406982, 5.120198011398315, 4.915418386459351, 5.1199212074279785, 4.915705919265747, 4.915356397628784, 5.120283365249634, 4.915226221084595, 5.1204352378845215, 4.9154863357543945, 4.91514778137207, 5.120310306549072, 4.916076898574829, 5.11952018737793, 4.915953874588013, 5.12006139755249, 4.915754318237305, 4.915386199951172, 5.119541645050049, 4.915405988693237, 5.120360374450684, 4.915273189544678, 4.915744304656982, 5.1213579177856445, 4.913957595825195, 5.120624303817749, 4.91521143913269, 4.9150567054748535, 5.1204917430877686, 4.915513753890991, 5.324768543243408, 4.710886478424072, 5.120267391204834, 4.915317058563232, 4.915459871292114, 5.1205151081085205, 4.915413856506348, 5.120130300521851, 9.831886529922485, 5.118919134140015, 4.915319204330444, 5.120188474655151, 4.915493726730347, 5.119948863983154, 4.915652751922607, 4.9159464836120605, 5.119251012802124, 4.915766477584839, 5.120148420333862, 4.915502548217773, 4.91851806640625, 5.117098093032837, 4.915786981582642, 5.12024712562561, 4.9150495529174805, 4.915300607681274, 5.1203062534332275, 4.9171953201293945, 5.1184773445129395, 4.915457010269165, 5.120526075363159, 4.91543436050415, 4.915662050247192, 5.120084762573242, 4.9155614376068115, 5.119662761688232, 4.915663242340088, 4.915252685546875, 5.119943141937256, 4.915757179260254, 5.1202147006988525, 4.915493965148926, 5.120219469070435, 4.915194988250732, 4.915619134902954, 5.121797800064087, 4.9136059284210205, 5.120525598526001, 4.915207624435425, 4.915758371353149, 5.120367765426636, 10.035244703292847, 4.915606737136841, 4.914844512939453, 5.1205339431762695, 4.9189980030059814, 5.117714881896973, 4.917497873306274, 5.1181440353393555, 4.9156835079193115, 4.9144978523254395, 5.121409893035889, 4.9142608642578125, 5.120001792907715, 4.91527247428894, 4.925924301147461, 5.109589576721191, 4.918871164321899, 5.119110822677612, 4.912829399108887, 5.127248287200928, 4.911931753158569, 4.911930799484253, 5.120657920837402, 4.917973041534424, 5.116990327835083, 4.915783405303955, 4.914998769760132, 5.122381687164307, 4.916517496109009, 5.118141412734985, 4.914234161376953, 5.1200151443481445, 4.9159276485443115, 4.915140628814697, 5.122661113739014, 4.913603782653809, 5.121016502380371, 4.913794755935669, 4.917841911315918, 5.123371124267578, 4.913689136505127, 5.116578102111816, 4.917291164398193, 4.914740562438965, 5.118982553482056, 4.916481256484985, 5.118958473205566, 4.916808128356934, 5.118808269500732, 4.918144464492798, 4.923793315887451, 5.110568523406982, 4.918172836303711, 5.116080284118652, 4.916720628738403, 4.913976430892944, 5.120632648468018, 4.916009187698364, 5.119194269180298, 4.915383338928223, 5.120018243789673, 4.934690952301025, 4.896383762359619, 5.120853424072266, 4.918590545654297, 5.115983963012695, 4.915722608566284, 4.915245532989502, 5.120195388793945, 4.91562032699585, 5.1205267906188965, 9.831329345703125, 5.119425296783447, 10.036421775817871, 4.9180145263671875, 5.116242408752441, 4.915764093399048, 4.917126893997192, 5.118403673171997, 4.915596961975098, 5.119954824447632, 4.915447950363159, 4.915365934371948, 5.12027382850647, 4.927206516265869, 5.108335971832275, 4.919280052185059, 10.033271312713623, 4.937414884567261, 5.096786975860596, 4.916103839874268, 5.1191816329956055, 4.915652275085449, 5.530719757080078, 4.505070209503174, 4.915388822555542, 5.119813680648804, 4.9203503131866455, 4.9105753898620605, 5.120170831680298, 4.91544246673584, 10.03553056716919, 5.120208501815796, 4.91562819480896, 4.922638177871704, 5.112942934036255, 4.919979810714722, 5.115472078323364, 4.9193665981292725, 4.911726236343384, 5.119908809661865, 4.915473222732544, 5.120723724365234, 4.914774417877197, 4.918801784515381, 5.117352724075317, 4.914820909500122, 5.120293855667114, 4.916266441345215, 5.1193695068359375, 4.918910503387451, 4.912074089050293, 5.120926380157471, 4.914704084396362, 5.11985445022583, 4.915564775466919, 4.934532642364502, 5.104891061782837, 4.914632081985474, 5.120090007781982, 4.912513494491577, 5.1221630573272705, 4.9192304611206055, 4.912852048873901, 5.116891622543335, 4.918702125549316, 5.119889259338379, 4.916006088256836, 4.915514945983887, 5.120411396026611, 4.914138317108154, 5.120649337768555, 4.9148900508880615, 5.119871377944946, 4.920058488845825, 4.910828351974487, 5.120222091674805, 4.915643692016602, 5.119956970214844, 4.915986776351929, 4.9188714027404785, 5.116855144500732, 4.917968988418579
    ));
    private double getValue() {
        t = t + 1;
        if (t >= a.size()) {
            return 0;
        }
        return (double) a.get(t);
    }
    public void makeLeftBot() {
        VBox vv = new VBox();
        HBox h = new HBox();
        VBox v2 = new VBox();
        VBox v = new VBox();
        Text title = new Text("DEVICE ACTIVITY");
        Text t = new Text("1");
        Text tt = new Text(" TOTAL IN NETWORK");
        Text t2 = new Text("100%");
        Text tt2 = new Text(" CONFIGURATION DRIFT");
        Text t3 = new Text("22%");
        Text tt3 = new Text(" TOTAL TRAFFIC TO OUT OF\n NETWORK NODES");
        tt3.setTextAlignment(TextAlignment.JUSTIFY);
        Text t4 = new Text("4");
        Text tt4 = new Text(" OTHER DEVICES ON\n NETWORK IT TALKS TO");
        t.setFill(Color.WHITE);
        t2.setFill(Color.WHITE);
        t3.setFill(Color.WHITE);
        t4.setFill(Color.WHITE);
        tt.setFill(Color.web("0x48C4F2"));
        tt2.setFill(Color.web("0x48C4F2"));
        tt3.setFill(Color.web("0x48C4F2"));
        tt4.setFill(Color.web("0x48C4F2"));
        title.setFill(Color.web("0x48C4F2"));
        title.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 30));
        tt.setFont(Font.font("Ariel", FontWeight.BOLD, 20));
        tt2.setFont(Font.font("Ariel", FontWeight.BOLD, 20));
        tt3.setFont(Font.font("Ariel", FontWeight.BOLD, 20));
        tt4.setFont(Font.font("Ariel", FontWeight.BOLD, 20));
        t.setFont(Font.font("Ariel", FontWeight.BOLD, 20));
        t2.setFont(Font.font("Ariel", FontWeight.BOLD, 20));
        t3.setFont(Font.font("Ariel", FontWeight.BOLD, 20));
        t4.setFont(Font.font("Ariel", FontWeight.BOLD, 20));
        vv.getChildren().add(title);
        VBox v3 = new VBox();
        VBox v4 = new VBox();
        VBox v5 = new VBox();
        VBox v6 = new VBox();
        VBox v7 = new VBox();
        VBox v8 = new VBox();
        vv.getChildren().add(h);
        v.getChildren().add(t);
        v3.getChildren().add(t2);
        v5.getChildren().add(t3);
        v7.getChildren().add(t4);
        v2.getChildren().add(tt);
        v4.getChildren().add(tt2);
        v6.getChildren().add(tt3);
        v8.getChildren().add(tt4);
        v.setPrefWidth(this.getPrefWidth()*.3);
        v.setMaxWidth(this.getPrefWidth()*.3);
        v2.setPrefWidth(this.getPrefWidth()*.7);
        v2.setMaxWidth(this.getPrefWidth()*.7);
        v3.setPrefWidth(this.getPrefWidth()*.3);
        v3.setMaxWidth(this.getPrefWidth()*.3);
        v4.setPrefWidth(this.getPrefWidth()*.7);
        v4.setMaxWidth(this.getPrefWidth()*.7);
        v5.setPrefWidth(this.getPrefWidth()*.3);
        v5.setMaxWidth(this.getPrefWidth()*.3);
        v6.setPrefWidth(this.getPrefWidth()*.7);
        v6.setMaxWidth(this.getPrefWidth()*.7);
        v7.setPrefWidth(this.getPrefWidth()*.3);
        v7.setMaxWidth(this.getPrefWidth()*.3);
        v8.setPrefWidth(this.getPrefWidth()*.7);
        v8.setMaxWidth(this.getPrefWidth()*.7);
        tt.setWrappingWidth(this.getPrefWidth()*.75);
        tt2.setWrappingWidth(this.getPrefWidth()*.75);
        tt3.setWrappingWidth(this.getPrefWidth()*.75);
        tt4.setWrappingWidth(this.getPrefWidth()*.75);

        HBox h2 = new HBox();
        HBox h3 = new HBox();
        HBox h4 = new HBox();
        HBox h5 = new HBox();
        HBox h6 = new HBox();
        HBox h7 = new HBox();
        VBox vb = new VBox();
        VBox vbb = new VBox();
        VBox vb2 = new VBox();
        VBox vbb2 = new VBox();
        VBox vb3 = new VBox();
        VBox vbb3 = new VBox();
        vb3.getChildren().add(h7);
        vb.setSpacing(3);
        vb2.getChildren().add(h6);
        vb2.setSpacing(3);
        vb.getChildren().add(h5);
        vb.setSpacing(3);
        Text ttt = new Text("SEE PATCH HISTORY");
        Text ttt2 = new Text("SEE PORTS AND PROTOCOL DECODE");
        Text ttt3 = new Text("SEE PORTS AND PROTOCOL DECODE");
        ttt.setOnMousePressed(e -> ttt.setFill(Color.GRAY));
        ttt.setOnMouseReleased(e -> ttt.setFill(Color.WHITE));
        ttt2.setOnMousePressed(e -> ttt.setFill(Color.GRAY));
        ttt2.setOnMouseReleased(e -> ttt.setFill(Color.WHITE));
        ttt3.setOnMousePressed(e -> ttt.setFill(Color.GRAY));
        ttt3.setOnMouseReleased(e -> ttt.setFill(Color.WHITE));
        ttt3.setUnderline(true);
        ttt3.setFont(Font.font("Ariel", FontWeight.SEMI_BOLD, 15));
        ttt3.setFill(Color.WHITE);
        ttt2.setFill(Color.WHITE);
        ttt2.setUnderline(true);
        ttt2.setFont(Font.font("Ariel", FontWeight.SEMI_BOLD, 15));
        ttt.setFill(Color.WHITE);
        ttt.setUnderline(true);
        ttt.setFont(Font.font("Ariel", FontWeight.SEMI_BOLD, 15));
        Text t9 = new Text("02 SEPTEMBER 2020 | 06:01:39 | PASSWORD CHANGE");
        Text t10 = new Text("04 SEPTEMBER 2020 | 01:32:31 | NETWORK UPDATE");
        t9.setFill(Color.WHITE);
        t10.setTranslateX(-5);
        vbb.getChildren().add(t9);
        t10.setFill(Color.WHITE);
        vbb.getChildren().add(t10);
        Text t11 = new Text("SAMPLE TEXT");
        Text t12 = new Text("SAMPLE TEXT");
        t11.setFill(Color.WHITE);
        vbb2.getChildren().add(t11);
        t12.setFill(Color.WHITE);
        vbb2.getChildren().add(t12);
        Text t13 = new Text("SAMPLE TEXT");
        Text t14 = new Text("SAMPLE TEXT");
        t13.setFill(Color.WHITE);
        vbb3.getChildren().add(t13);
        t14.setFill(Color.WHITE);
        vbb3.getChildren().add(t14);
        ttt.setOnMouseClicked(e -> addOrRemoveInfo(vb, vbb));
        vbb.setAlignment(Pos.CENTER);
        h5.getChildren().add(ttt);
        h5.setAlignment(Pos.CENTER);
        h5.setTranslateX(-25);
        h6.getChildren().add(ttt2);
        h6.setAlignment(Pos.CENTER);
        h6.setTranslateX(29);
        ttt2.setOnMouseClicked(e -> addOrRemoveInfo2(vb2, vbb2));
        vbb2.setAlignment(Pos.CENTER);
        vbb3.setAlignment(Pos.CENTER);
        h7.setAlignment(Pos.CENTER);
        h7.setTranslateX(29);
        h7.getChildren().add(ttt3);
        ttt3.setOnMouseClicked(e -> addOrRemoveInfo3(vb3, vbb3));
        h.getChildren().add(v);
        h.getChildren().add(v2);
        h2.getChildren().add(v3);
        h2.getChildren().add(v4);
        h3.getChildren().add(v5);
        h3.getChildren().add(v6);
        h4.getChildren().add(v7);
        h4.getChildren().add(v8);
        vv.getChildren().add(h2);
        vv.getChildren().add(vb);
        vv.getChildren().add(h3);
        vv.getChildren().add(vb2);
        vv.getChildren().add(h4);
        vv.getChildren().add(vb3);


        vv.setSpacing(10);
        v.setSpacing(25);
        v2.setSpacing(25);
        vv.setAlignment(Pos.TOP_LEFT);
        this.setPadding(new Insets(0, 0, 0, 10));
        this.setHgap(5);
        this.setVgap(7);
        this.add(vv, 0, 0);

    }
    boolean open = false;
    private void addOrRemoveInfo(VBox vb, VBox hb) {
        if (!open) {
            vb.getChildren().add(hb);
            open = !open;
            return;
        }
        if (open) {
            vb.getChildren().remove(hb);
            open = !open;
            return;
        }
    }
    boolean open2 = false;
    private void addOrRemoveInfo2(VBox vb, VBox hb) {
        if (!open2) {
            vb.getChildren().add(hb);
            open2 = !open2;
            return;
        }
        if (open2) {
            vb.getChildren().remove(hb);
            open2 = !open2;
            return;
        }
    }
    boolean open3 = false;
    private void addOrRemoveInfo3(VBox vb, VBox hb) {
        if (!open3) {
            vb.getChildren().add(hb);
            open3 = !open3;
            return;
        }
        if (open3) {
            vb.getChildren().remove(hb);
            open3 = !open3;
            return;
        }
    }

    public void makeRightBot() {
//        Text t = new Text("PRIORITY");
//        Text t2 = new Text("LOW");
//        Text t3 = new Text("CVE released buffer overflow issue on Smiths Medical Medfusion 4000 Wireless Syringe Infusion Pump");
//        t.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 35));
//        t2.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 25));
//        t3.setFont(Font.font("Ariel", FontWeight.MEDIUM, 20));
//        t.setFill(Color.web("0x48C4F2"));
//        t2.setFill(Color.WHITE);
//        t3.setFill(Color.WHITE);
//        t3.setWrappingWidth(this.getPrefWidth()*.75);
//        VBox v = new VBox();
//        v.setAlignment(Pos.CENTER);
//        t.setTextAlignment(TextAlignment.CENTER);
//        t2.setTextAlignment(TextAlignment.CENTER);
//        t3.setTextAlignment(TextAlignment.CENTER);
//        v.setSpacing(15);
//        v.getChildren().add(t);
//        v.getChildren().add(t2);
//        v.getChildren().add(t3);
//        VBox text = new VBox();
//        Text t = new Text("PRIORITY: MEDIUM");
//        t.setUnderline(true);
//        t.setFont(Font.font("Ariel", FontWeight.BOLD, 30));
//        t.setFill(Color.web("0x48C4F2"));
//        Text t2 = new Text("Identified CVE 2018-6692. Recommend switching network from high-priority assets.");
//        t2.setFont(Font.font("Ariel", FontWeight.SEMI_BOLD, 20));
//        t2.setFill(Color.WHITE);
//        t2.setWrappingWidth(this.getPrefWidth()*.6);
//        text.setAlignment(Pos.CENTER);
//        text.setSpacing(25);
//        text.getChildren().add(t);
//        text.getChildren().add(t2);
//        text.setTranslateY(-37);
//        Line l1 = new Line(0, 50, -20, 30);
//        Line l2 = new Line(0, 10, -20, 30);
//        l1.setStroke(Color.WHITE);
//        l2.setStroke(Color.WHITE);
//        l1.setStrokeWidth(3);
//        l2.setStrokeWidth(3);
//        Pane arrow1 = new Pane();
//        arrow1.getChildren().add(l1);
//        arrow1.getChildren().add(l2);
//        Line l3 = new Line(0, 50, 20, 30);
//        Line l4 = new Line(0, 10, 20, 30);
//        l3.setStroke(Color.WHITE);
//        l4.setStroke(Color.WHITE);
//        l3.setStrokeWidth(3);
//        l4.setStrokeWidth(3);
//        Pane arrow2 = new Pane();
//        arrow2.getChildren().add(l3);
//        arrow2.getChildren().add(l4);
//        BorderPane p = new BorderPane();
//        p.setPrefSize(this.getPrefWidth(), this.getPrefHeight());
//        p.setMaxSize(this.getPrefWidth(), this.getPrefHeight());
//        arrow1.setTranslateX(30);
//        arrow1.setTranslateY(90);
//        arrow2.setTranslateX(-15);
//        arrow2.setTranslateY(90);
//        p.setLeft(arrow1);
//        p.setRight(arrow2);
//
//        p.setCenter(text);
//        HBox circlebox = new HBox();
//        p.getLeft().setOnMouseClicked(e -> scrollLeft(circlebox.getChildren().get(0), circlebox.getChildren().get(1), circlebox.getChildren().get(2), p));
//        //p.getRight().setOnMouseClicked(e -> scrollRight());
//        Circle c1 = new Circle(0, 0, 5, Color.WHITE);
//        Circle c2 = new Circle(0, 0, 10, Color.TRANSPARENT);
//        Circle c3 = new Circle(0, 0, 5, Color.WHITE);
//        c2.setStroke(Color.WHITE);
//        c1.setStroke(Color.WHITE);
//        c3.setStroke(Color.WHITE);
//        c2.setStrokeWidth(3);
//        c1.setStrokeWidth(3);
//        c3.setStrokeWidth(3);
//        circlebox.getChildren().add(c1);
//        circlebox.getChildren().add(c2);
//        circlebox.getChildren().add(c3);
//        circlebox.setSpacing(10);
//        circlebox.setAlignment(Pos.CENTER);
//        circlebox.setTranslateX(-6);
//        circlebox.setPadding(new Insets(5, 5, 5, 5));
////        p.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5, 5, 5, 5))));
//        p.setBottom(circlebox);
//        p.setPadding(new Insets(5, 5, 5, 5));
        Ticker t = new Ticker(this.getPrefWidth(), this.getPrefHeight());
//        circlebox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5, 5, 5, 5))));
        this.add(t, 0, 0);
        this.setPadding(new Insets(0, 0, 0, 10));
        this.setAlignment(Pos.BOTTOM_CENTER);
//        this.add(v, 0, 0);
    }

    private void scrollLeft(Node s1, Node s2, Node s3, BorderPane n) {
        Circle t = (Circle) s1;
        Circle t2 = (Circle) s2;
        Circle t3 = (Circle) s3;
        t.setFill(Color.TRANSPARENT);
        t.setRadius(10);
        t2.setFill(Color.WHITE);
        t2.setRadius(5);


    }

//    private void addHomeButtonFunction(Button homeButton, Stage applicationStage){
//        homeButton.setOnMouseClicked(e -> applicationStage.setScene(overviewScene));
//    }

}
