import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main extends Application {

    private double offsetX = 0;
    private double offsetY = 0;
    private Group root = new Group();
    private TableView table = new TableView();
    private Map<String, ObservableList> networkMap = new HashMap<>();
    private ObservableList<Device> deviceList = FXCollections.observableArrayList(
            new Device("HVAC Fan 1 - Office", "216.58.216.163", "Room 1",99.0, 100.0,"09/10/2020"),
            new Device("HVAC Fan 3 - Cafe", "216.58.216.162","Room 3",52.0,94.0,   "09/06/2020"),
            new Device("Security Door", "216.58.216.162","North Wing Entrance",100.0,  86.0, "09/06/2020"),
            new Device("Cooling System 1", "216.58.216.162","Room 3",91.0,100.0,   "09/06/2020"),
            new Device("Key Card Reader", "216.58.216.162","Room 3",82.0,84.0,   "09/06/2020"),
            new Device("HVAC Fan 6", "216.58.216.162","Room 3",87.0,  74.0, "09/06/2020")
    );
    private ObservableList<Device> southWingDevices = FXCollections.observableArrayList();
    private ObservableList<String> networkList = FXCollections.observableArrayList(
            "Whole Hospital", "North Wing", "East Wing", "South Wing", "West Wing"
    );
    private SortedList<Device> wholeSortedList;


    private final TableColumn deviceNameCol = new TableColumn("Device Name");
    private final TableColumn ipAddressCol = new TableColumn("Device IP Address");
    private final TableColumn deviceLocationCol = new TableColumn("Device Location");
    private final TableColumn deviceAvailabilityDisplayCol = new TableColumn("Device Availability");
    private final TableColumn deviceHygieneDisplayCol = new TableColumn("Device Hygiene");
    private final TableColumn lastThreatDateCol = new TableColumn("Last Threat Blocked Date");
    private final ImageView exitImage = new ImageView(new Image("/exit.png"));
    private final ImageView minimizeImage = new ImageView(new Image("/minimize.png"));
    private final Button minimizeButton = new Button("", minimizeImage);
    private final Button exitButton = new Button("", exitImage);

    private ComboBox comboBox;
    private Scene deviceScene;
    private Scene overviewScene;

    private double xOffset = 0;
    private double yOffset = 0;
    private final String companyString = "Duke Hospital";

    private ScrollPane scrollPane = new ScrollPane();
    private GridPane outerGridPane = new GridPane();
    private GridPane innerGridPane = new GridPane();
    private BaseWindow deviceRoot;
    private Stage deviceStage;
    private Stage appStage;
    private GridPane topMenu;
    private HBox rightMenu;
    private HBox leftMenu;

    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 10000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private Arc dhArc1;
    private Arc dhArc2;
    private Arc daArc1;
    private Arc daArc2;
    private Text v;
    private Text n;
    private String lastThreatBlockedDate = "04/23/2020";
    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    private Text g2;



    @Override
    public void start(Stage primaryStage) throws Exception{
        appStage = primaryStage;
//        root.setOnMouseClicked(e -> {
//            offsetX = e.getSceneX();
//            offsetY = e.getSceneY();
//        });
//        root.setOnMouseDragged(e -> drag(primaryStage, e.getScreenX(), e.getScreenY()));
//        primaryStage.setWidth(1280);
//        primaryStage.setHeight(720);
//        primaryStage.setScene(primaryScene);
//        primaryStage.initStyle(StageStyle.UNDECORATED);
//        primaryScene.setOnKeyPressed(e -> close(e.getCode(), primaryStage));

        overviewScene = new Scene(root, 1280, 720, Color.BLACK);
        overviewScene.getStylesheets().add("/overviewStyling.css");
        primaryStage.setScene(overviewScene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("Table View Sample");
        deviceRoot = new BaseWindow(primaryStage, overviewScene);
        primaryStage.setTitle("IOT Example");
        deviceScene = new Scene(deviceRoot, 1280, 720);
        deviceScene.getStylesheets().add("style.css");

        //TableView Items
        Label tableLabel = new Label("Device List");
        tableLabel.setId("tableLabel");

        VBox tableVBox = new VBox();
        tableVBox.setId("tableVBox");

        Image i = new Image(new FileInputStream("Java13\\src\\Picture1.png"));
        ImageView iv = new ImageView(i);
        iv.setFitWidth(i.getWidth()*.7);
        iv.setFitHeight(i.getHeight()*.7);
        iv.setTranslateX(-8);
        iv.setTranslateY(-8);

        //TopMenu Items
        topMenu = new GridPane();
        leftMenu = new HBox();
        rightMenu = new HBox();
        leftMenu.getChildren().add(iv);
        Label companyName = new Label(companyString);
        topMenu.setId("topMenu");
        leftMenu.setId("leftMenu");
        rightMenu.setId("rightMenu");
        companyName.setId("companyName");
        companyName.setTranslateX(180);
//        companyName.setTranslateY(20);
        rightMenu.setTranslateX(-50);

        comboBox = new ComboBox(networkList);
//        comboBox.setTranslateX(500);
        comboBox.getSelectionModel().selectFirst();
        leftMenu.getChildren().add(companyName);
//        leftMenu.getChildren().add(comboBox);
        rightMenu.getChildren().add(comboBox);
        topMenu.add(leftMenu, 0, 0, 1, 1);
        topMenu.add(rightMenu, 1, 0, 1, 1);
        topMenu.setPadding(new Insets(10, 20, -20, 20));
        companyName.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 50));
        rightMenu.getChildren().addAll(minimizeButton, exitButton);
        tableVBox.getChildren().addAll(tableLabel, table);

        //Aggregate Items

        HBox aggregateAvailability = new HBox();
        HBox aggregateHygiene = new HBox();
        HBox lastThreatBlocked = new HBox();
        aggregateAvailability.setId("aggregateAvailability");
        aggregateHygiene.setId("aggregateHygiene");
        lastThreatBlocked.setId("lastThreatBlocked");
        addAggregateFigures(aggregateAvailability, aggregateHygiene, lastThreatBlocked);

        //inner grid
        innerGridPane.setId("innerGridPane");
        outerGridPane.setId("outerGridPane");
        scrollPane.setContent(innerGridPane);
        innerGridPane.add(aggregateAvailability, 0,0,1,1);
        innerGridPane.add(aggregateHygiene, 1,0,1,1);
        innerGridPane.add(lastThreatBlocked, 2,0,1,1);
        innerGridPane.add(tableVBox, 0,1,3,1);
        outerGridPane.add(topMenu, 0, 0, 1, 1);
        outerGridPane.add(scrollPane, 0, 1, 1, 1);
        root.getChildren().add(outerGridPane);

        makeScreenMovable(primaryStage);
        addButtonFunctions(minimizeButton, exitButton, primaryStage);
        overviewScene.setOnKeyPressed(e -> handleKeyInput(e.getCode(), primaryStage));
        deviceScene.setOnKeyPressed(e -> handleKeyInput(e.getCode(), primaryStage));
        switchNetworks();

        setUpTableColumns();
        populateTableColumns();
        setupTimeline();
        primaryStage.show();
    }

    private void setupTimeline(){
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e ->
        {
            try {
                step(); }
            catch (Exception ex) {
                ex.printStackTrace();} });
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }
    private void step() throws Exception {
//        randomAvailabilityChange();
        changeAggregateGraphs();
        updateLastThreatBlocked();
    }

    private void updateLastThreatBlocked() throws ParseException {
        for(int i = 0; i < deviceList.size(); i++){
            if(sdf.parse(deviceList.get(i).getLastThreatBlockedDate()).after(sdf.parse(lastThreatBlockedDate))){
                lastThreatBlockedDate = deviceList.get(i).getLastThreatBlockedDate();
            }
        }
        g2.setText(lastThreatBlockedDate);

    }

    private void changeAggregateGraphs(){
        double aggregateAvailability = calculateAggregateAvailability(wholeSortedList);
        double aggregateHygiene = calculateAggregateHygiene(wholeSortedList);
        daArc2.setLength(aggregateAvailability/100.0 * -360);
        dhArc2.setLength(aggregateHygiene/100.0 * -360);
        DecimalFormat d = new DecimalFormat("0.0");
        String temp = d.format(Math.abs(daArc2.getLength()/360)*100) + "%";
        n.setText(temp);
        n.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 50));
        n.setFill(Color.web("0x48C4F2"));
        n.setX(daArc2.getCenterX() - daArc2.getRadiusX()/1.8);
        n.setY(daArc2.getCenterY() - daArc2.getRadiusY()/4);
        n.setManaged(false);

        DecimalFormat d1 = new DecimalFormat("0.0");
        String temp1 = d1.format(Math.abs(dhArc2.getLength()/360)*100) + "%";
        v.setText(temp1);
        v.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 50));
        v.setFill(Color.web("0x48C4F2"));
        v.setX(dhArc2.getCenterX() - dhArc2.getRadiusX()/1.8);
        v.setY(dhArc2.getCenterY() - dhArc2.getRadiusY()/4);
        v.setManaged(false);
    }

    private void randomAvailabilityChange(){
        for(int i = 0; i < deviceList.size(); i++){
            double probability = Math.random();
            int increment;
            if(probability < 0.33){
                increment = -1;
            }
            else if(probability > 0.33 && probability <0.66){
                increment = 0;
            }
            else{
                increment = 1;
            }
            deviceList.get(i).increaseDeviceAvailability(increment);
        }
    }

    private void makeScreenMovable(Stage applicationStage){
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();

        });
        root.setOnMouseDragged(event -> {
            applicationStage.setX(event.getScreenX() - xOffset);
            applicationStage.setY(event.getScreenY() - yOffset);
        });
        deviceRoot.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();

        });
        deviceRoot.setOnMouseDragged(event -> {
            applicationStage.setX(event.getScreenX() - xOffset);
            applicationStage.setY(event.getScreenY() - yOffset);
        });
    }

    private void switchNetworks() {
        comboBox.valueProperty().addListener((ChangeListener<String>) (ov, oldText, newText) -> {
            table.setItems(null);
            table.setItems(new SortedList<Device>(networkMap.get(newText)));
        });
    }

    private void addButtonFunctions(Button minimize, Button exit, Stage applicationStage){
        minimize.setOnMouseClicked(e -> applicationStage.setIconified(true));
        exit.setOnMouseClicked(e -> deviceStage.close());
        exit.setOnMouseClicked(e -> applicationStage.close());

    }

    private void handleKeyInput(KeyCode code, Stage stage){
        if (code == KeyCode.D){
//            for(int i = 0; i < deviceList.size(); i++){
//                deviceList.get(i).increaseDeviceAvailability(1);
//            }
            deviceList.get(1).increaseDeviceAvailability(1);
            table.refresh();
        }
        if (code == KeyCode.Q){
            stage.setScene(deviceScene);
        }
        if (code == KeyCode.W){
            stage.setScene(overviewScene);
        }
        if (code == KeyCode.S){
            deviceList.get(0).increaseDeviceAvailability(1);
            table.refresh();
        }
        if (code == KeyCode.R){
            deviceList.get(0).increaseDeviceAvailability(-1);
            table.refresh();
        }
        if (code == KeyCode.T){
            deviceList.get(0).setLastThreatDate("09/12/2020");
            table.refresh();
        }
        if (code == KeyCode.ESCAPE) {
            stage.close();
        }


//        if (code == KeyCode.A){
//            wholeSortedList.add(new Device("HVAC North", "123:13:1424", "North Building", 75.0, 4.0,"01/12/20"));
//        }
    }

    private double calculateAggregateAvailability(List<Device> list){
        double totalAvailability = 0;
        for(int i = 0; i < list.size(); i++){
            totalAvailability += list.get(i).getDeviceAvailability();
        }
        return totalAvailability/list.size();
    }

    private double calculateAggregateHygiene(List<Device> list){
        double totalHygiene = 0;
        for(int i = 0; i < list.size(); i++){
            totalHygiene += list.get(i).getDeviceHygiene();
        }
        return totalHygiene/list.size();
    }

    private void setUpTableColumns(){
        deviceNameCol.setCellValueFactory(
                new PropertyValueFactory<Device, String>("deviceName"));
        ipAddressCol.setCellValueFactory(
                new PropertyValueFactory<Device, String>("ipAddress"));
        deviceLocationCol.setCellValueFactory(
                new PropertyValueFactory<Device, String>("deviceLocation"));
        deviceAvailabilityDisplayCol.setCellValueFactory(
                new PropertyValueFactory<Device, String>("deviceAvailabilityDisplay"));
        deviceHygieneDisplayCol.setCellValueFactory(
                new PropertyValueFactory<Device, String>("deviceHygieneDisplay"));
        lastThreatDateCol.setCellValueFactory(
                new PropertyValueFactory<Device, String>("lastThreatBlockedDate"));
        table.getColumns().addAll(deviceNameCol, ipAddressCol, deviceLocationCol, deviceAvailabilityDisplayCol, deviceHygieneDisplayCol, lastThreatDateCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void populateTableColumns(){
        Device southDevice = new Device("HVAC Fan 2", "216.58.216.164","Room 2" ,74.0,4.0, "8/03/2020");
        deviceList.add(southDevice);
        southWingDevices.add(southDevice);
        Callback<Device, Observable[]> cb =(Device device) -> new Observable[]{
                device.getDeviceAvailabilityProperty()
        };
        ObservableList<Device> test = FXCollections.observableArrayList(cb);
        test.addAll(deviceList);
        wholeSortedList = new SortedList<>( test,
                (Device stock1, Device stock2) -> {
                    if( stock1.getDeviceAvailability() < stock2.getDeviceAvailability() ) {
                        return -1;
                    } else if( stock1.getDeviceAvailability() > stock2.getDeviceAvailability() ) {
                        return 1;
                    } else {
                        return 0;
                    }
                });
        final ObservableList<Integer> highlightRows = FXCollections.observableArrayList();
        table.setRowFactory(new Callback<TableView<Device>, TableRow<Device>>() {
            @Override
            public TableRow<Device> call(TableView<Device> tableView) {
                final TableRow<Device> row = new TableRow<Device>() {
                    @Override
                    protected void updateItem(Device device, boolean empty){
                        super.updateItem(device, empty);
                        if(getItem() != null && getItem().getDeviceAvailability() < 20){
                            getStyleClass().add("highlightedRow");
                        }
                        else if(getItem() != null && getItem().getDeviceAvailability() >= 20) {
                            getStyleClass().removeAll(Collections.singleton("highlightedRow"));
                        }
                    }
                };
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                        appStage.setScene(deviceScene);
                    }
                });
                highlightRows.addListener((ListChangeListener<Integer>) change -> {
                    if(row.getItem() != null && row.getItem().getDeviceAvailability() < 20){
                        row.getStyleClass().add("highlightedRow");
                    }
                    else if(row.getItem() != null && row.getItem().getDeviceAvailability() >= 20) {
                        row.getStyleClass().removeAll(Collections.singleton("highlightedRow"));
                    }
                });
                return row;
            }
        });

        final Button btnHighlight = new Button("Highlight");
        btnHighlight.disableProperty().bind(Bindings.isEmpty(table.getSelectionModel().getSelectedIndices()));
        btnHighlight.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                highlightRows.setAll(table.getSelectionModel().getSelectedIndices());
            }
        });

        final Button btnClearHighlight = new Button("Clear Highlights");
        btnClearHighlight.disableProperty().bind(Bindings.isEmpty(highlightRows));
        btnClearHighlight.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                highlightRows.clear();
            }
        });
//        rightMenu.getChildren().addAll(btnHighlight, btnClearHighlight);
        SortedList<Device> southSortedList = new SortedList<>(southWingDevices);
        networkMap.put("Whole Hospital", wholeSortedList);
        networkMap.put("South Wing", southSortedList);
        networkMap.put("West Wing", southSortedList);
        networkMap.put("East Wing", southSortedList);
        networkMap.put("North Wing", southSortedList);
//        wholeSortedList.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(wholeSortedList);

    }

    private void addAggregateFigures(HBox aggregateAvailability, HBox aggregateHygiene, HBox lastThreatBlocked){

        daArc1 = new Arc(200, 150, 120, 120, 90, -360);
        daArc1.setStroke(Color.RED);
        daArc1.setStrokeWidth(9);
        daArc1.setFill(Color.TRANSPARENT);

        daArc2 = new Arc(200, 150, 120, 120, 90, -328.68);
        daArc2.setStroke(Color.LIMEGREEN);
        daArc2.setStrokeWidth(10);
        daArc2.setFill(Color.TRANSPARENT);
        daArc1.setManaged(false);
        daArc2.setManaged(false);

        n = new Text();
        DecimalFormat d = new DecimalFormat("0.0");
        String temp = d.format(Math.abs(daArc2.getLength()/360)*100) + "%";
        n.setText(temp);
        n.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 50));
        n.setFill(Color.WHITE);
        n.setX(daArc2.getCenterX() - daArc2.getRadiusX()/1.8);
        n.setY(daArc2.getCenterY() - daArc2.getRadiusY()/4);
        n.setManaged(false);
        Text n2 = new Text("AGGREGATE\nDEVICE AVAILABILITY");
        n2.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 20));
        n2.setFill(Color.web("0x48c4f2"));
        n2.setTextAlignment(TextAlignment.CENTER);
        n2.setTranslateX(daArc2.getCenterX() - daArc2.getRadiusX()/1.175);
        n2.setTranslateY(daArc2.getCenterY() + daArc2.getRadiusY()/8);
        n2.setManaged(false);

        aggregateAvailability.getChildren().addAll(daArc1, daArc2, n, n2);

        dhArc1 = new Arc(200, 150, 120, 120, 90, -360);
        dhArc1.setStroke(Color.RED);
        dhArc1.setStrokeWidth(9);
        dhArc1.setFill(Color.TRANSPARENT);

        dhArc2 = new Arc(200, 150, 120, 120, 90, -328.68);
        dhArc2.setStroke(Color.LIMEGREEN);
        dhArc2.setStrokeWidth(10);
        dhArc2.setFill(Color.TRANSPARENT);
        dhArc1.setManaged(false);
        dhArc2.setManaged(false);

        v = new Text();
        DecimalFormat s = new DecimalFormat("0.0");
        String temp1 = s.format(Math.abs(dhArc2.getLength()/360)*100) + "%";
        v.setText(temp1);
        v.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 50));
        v.setFill(Color.WHITE);
        v.setX(dhArc2.getCenterX() - dhArc2.getRadiusX()/1.8);
        v.setY(dhArc2.getCenterY() - dhArc2.getRadiusY()/4);
        v.setManaged(false);
        Text v2 = new Text("AGGREGATE\nDEVICE HYGIENE");
        v2.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 20));
        v2.setFill(Color.web("0x48c4f2"))
        ;
        v2.setTextAlignment(TextAlignment.CENTER);
        v2.setTranslateX(dhArc2.getCenterX() - dhArc2.getRadiusX()/1.5);
        v2.setTranslateY(dhArc2.getCenterY() + dhArc2.getRadiusY()/8);
        v2.setManaged(false);

        aggregateHygiene.getChildren().addAll(dhArc1, dhArc2, v, v2);
        VBox g = new VBox();
        Text g1 = new Text("LAST DATE THREAT BLOCKED:");
        g2 = new Text(lastThreatBlockedDate);
        g.getChildren().addAll(g1, g2);
        g1.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 30));
        g1.setFill(Color.web("0x48C4F2"));
        g1.setTextAlignment(TextAlignment.CENTER);
//        g1.setTranslateY(150);
//        g1.setTranslateX(10);
//        g1.setManaged(false);
        g2.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 30));
        g2.setFill(Color.WHITE);
        g2.setTextAlignment(TextAlignment.CENTER);
        g.setAlignment(Pos.CENTER);
        g.setPadding(new Insets(10, 10, 10, 10));
//        g2.setTranslateY(190);
//        g2.setTranslateX(10);
//        g2.setManaged(false);
        lastThreatBlocked.getChildren().addAll(g);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
       // ses.shutdownNow();
    }
}
