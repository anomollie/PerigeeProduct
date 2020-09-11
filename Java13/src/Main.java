import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
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

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends Application {

    private double offsetX = 0;
    private double offsetY = 0;
    private Group root = new Group();
    private TableView table = new TableView();
    private Map<String, ObservableList> networkMap = new HashMap<>();
    private ObservableList<Device> deviceList = FXCollections.observableArrayList(
            new Device("HVAC Fan 1 - Office", "216.58.216.163", "Room 1",1.0, 4.0,"09/17/2020"),
            new Device("HVAC Fan 3 - Cafe", "216.58.216.162","Room 3",2.0,4.0,   "09/06/2020"),
            new Device("Security Door", "216.58.216.162","North Wing Entrance",2.0,  4.0, "09/06/2020"),
            new Device("Cooling System 1", "216.58.216.162","Room 3",2.0,4.0,   "09/06/2020"),
            new Device("Key Card Reader", "216.58.216.162","Room 3",2.0,4.0,   "09/06/2020"),
            new Device("HVAC Fan 6", "216.58.216.162","Room 3",2.0,  4.0, "09/06/2020")
    );
    private ObservableList<Device> southWingDevices = FXCollections.observableArrayList();
    private ObservableList<String> networkList = FXCollections.observableArrayList(
            "Whole Hospital", "North Wing", "East Wing", "South Wing", "West Wing"
    );
    private SortedList<Device> wholeSortedList;

    private TableColumn deviceNameCol = new TableColumn("Device Name");
    private TableColumn ipAddressCol = new TableColumn("Device IP Address");
    private TableColumn deviceLocationCol = new TableColumn("Device Location");
    private TableColumn deviceAvailabilityDisplayCol = new TableColumn("Device Availability");
    private TableColumn deviceHygieneDisplayCol = new TableColumn("Device Hygiene");
    private TableColumn lastThreatDateCol = new TableColumn("Last Threat Blocked Date");
    private ImageView exitImage = new ImageView(new Image("/exit.png"));
    private ImageView minimizeImage = new ImageView(new Image("/minimize.png"));

    private ComboBox comboBox;
    private Scene deviceScene;
    private Scene overviewScene;

    private double xOffset = 0;
    private double yOffset = 0;
    private final String companyString = "Duke Hospital";

    private ScrollPane scrollPane = new ScrollPane();
    private GridPane outerGridPane = new GridPane();
    private GridPane innerGridPane = new GridPane();


    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Parent deviceRoot = new BaseWindow();
        primaryStage.setTitle("IOT Example");
        deviceScene = new Scene(deviceRoot, 1280, 720);
        deviceScene.getStylesheets().add("style.css");
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
        primaryStage.setScene(overviewScene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        overviewScene.getStylesheets().add("/overviewStyling.css");
        primaryStage.setTitle("Table View Sample");


        //TableView Items
        Label tableLabel = new Label("Device List");
        tableLabel.setId("tableLabel");

        VBox tableVBox = new VBox();
        tableVBox.setId("tableVBox");
        setUpTableColumns();
        populateTableColumns();


        //TopMenu Items
        GridPane topMenu = new GridPane();
        HBox leftMenu = new HBox();
        HBox rightMenu = new HBox();
        Label companyName = new Label(companyString);
        topMenu.setId("topMenu");
        leftMenu.setId("leftMenu");
        rightMenu.setId("rightMenu");
        companyName.setId("companyName");
        Button minimizeButton = new Button("", minimizeImage);
        Button exitButton = new Button("", exitImage);
        comboBox = new ComboBox(networkList);
        comboBox.getSelectionModel().selectFirst();
        leftMenu.getChildren().add(companyName);
        leftMenu.getChildren().add(comboBox);
        topMenu.add(leftMenu, 0, 0, 1, 1);
        topMenu.add(rightMenu, 1, 0, 1, 1);
        rightMenu.getChildren().addAll(minimizeButton, exitButton);
        tableVBox.getChildren().addAll(tableLabel, table);

        //Aggregate Items

        HBox aggregateAvailability = new HBox();
        HBox aggregateHygiene = new HBox();
        HBox lastThreatBlocked = new HBox();
        aggregateAvailability.setId("aggregateAvailability");
        aggregateHygiene.setId("aggregateHygiene");
        lastThreatBlocked.setId("lastThreatBlocked");

        Arc daArc1 = new Arc(200, 150, 120, 120, 90, -360);
        daArc1.setStroke(Color.web("0xFCBA03"));
        daArc1.setStrokeWidth(9);
        daArc1.setFill(Color.TRANSPARENT);

        Arc daArc2 = new Arc(200, 150, 120, 120, 90, -328.68);
        daArc2.setStroke(Color.web("0x48C4F2"));
        daArc2.setStrokeWidth(10);
        daArc2.setFill(Color.TRANSPARENT);
        daArc1.setManaged(false);
        daArc2.setManaged(false);

        Text n = new Text();
        DecimalFormat d = new DecimalFormat("0.0");
        String temp = d.format(Math.abs(daArc2.getLength()/360)*100) + "%";
        n.setText(temp);
        n.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 50));
        n.setFill(Color.web("0x48C4F2"));
        n.setX(daArc2.getCenterX() - daArc2.getRadiusX()/1.8);
        n.setY(daArc2.getCenterY() - daArc2.getRadiusY()/4);
        n.setManaged(false);
        Text n2 = new Text("AGGREGATE\nDEVICE AVAILABILITY");
        n2.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 20));
        n2.setFill(Color.SKYBLUE);
        n2.setTextAlignment(TextAlignment.CENTER);
        n2.setTranslateX(daArc2.getCenterX() - daArc2.getRadiusX()/1.175);
        n2.setTranslateY(daArc2.getCenterY() + daArc2.getRadiusY()/8);
        n2.setManaged(false);

        aggregateAvailability.getChildren().addAll(daArc1, daArc2, n, n2);

        Arc dhArc1 = new Arc(200, 150, 120, 120, 90, -360);
        dhArc1.setStroke(Color.web("0xFCBA03"));
        dhArc1.setStrokeWidth(9);
        dhArc1.setFill(Color.TRANSPARENT);

        Arc dhArc2 = new Arc(200, 150, 120, 120, 90, -328.68);
        dhArc2.setStroke(Color.web("0x48C4F2"));
        dhArc2.setStrokeWidth(10);
        dhArc2.setFill(Color.TRANSPARENT);
        dhArc1.setManaged(false);
        dhArc2.setManaged(false);

        Text v = new Text();
        DecimalFormat s = new DecimalFormat("0.0");
        String temp1 = s.format(Math.abs(daArc2.getLength()/360)*100) + "%";
        v.setText(temp1);
        v.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 50));
        v.setFill(Color.web("0x48C4F2"));
        v.setX(daArc2.getCenterX() - daArc2.getRadiusX()/1.8);
        v.setY(daArc2.getCenterY() - daArc2.getRadiusY()/4);
        v.setManaged(false);
        Text v2 = new Text("AGGREGATE\nDEVICE HYGIENE");
        v2.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 20));
        v2.setFill(Color.SKYBLUE);
        v2.setTextAlignment(TextAlignment.CENTER);
        v2.setTranslateX(daArc2.getCenterX() - daArc2.getRadiusX()/1.5);
        v2.setTranslateY(daArc2.getCenterY() + daArc2.getRadiusY()/8);
        v2.setManaged(false);

        aggregateHygiene.getChildren().addAll(dhArc1, dhArc2, v, v2);

        Text g1 = new Text("LAST DATE THREAT BLOCKED:\n09/10/2020");
        g1.setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD, 30));
        g1.setFill(Color.web("0x48C4F2"));
        g1.setTextAlignment(TextAlignment.CENTER);
        g1.setTranslateY(150);
        g1.setTranslateX(10);
        g1.setManaged(false);
        lastThreatBlocked.getChildren().addAll(g1);
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
        switchNetworks();
        primaryStage.show();
    }
    public void drag(Stage stage, double x, double y) {
        stage.setX(x - offsetX);
        stage.setY(y - offsetY);
    }
    public void close(KeyCode key, Stage primaryStage) {
        if (key == KeyCode.ESCAPE) {
            primaryStage.close();
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
    }

    private void switchNetworks() {
        comboBox.valueProperty().addListener((ChangeListener<String>) (ov, oldText, newText) -> {
            table.setItems(null);
            table.setItems(new SortedList<Device>(networkMap.get(newText)));
        });
    }

    private void addButtonFunctions(Button minimize, Button exit, Stage applicationStage){
        minimize.setOnMouseClicked(e -> applicationStage.setIconified(true));
        exit.setOnMouseClicked(e -> applicationStage.close());
    }


    private void handleKeyInput(KeyCode code, Stage stage){
        if (code == KeyCode.D){
//            for(int i = 0; i < deviceList.size(); i++){
//                deviceList.get(i).increaseDeviceAvailability(1);
//            }
            deviceList.get(0).increaseDeviceAvailability(1);
            table.refresh();
        }
        if (code == KeyCode.Q){
            stage.setScene(deviceScene);
        }
        if (code == KeyCode.W){
            stage.setScene(overviewScene);
        }
        if (code == KeyCode.S){
//            for(int i = 0; i < deviceList.size(); i++){
//                deviceList.get(i).increaseDeviceAvailability(1);
//            }
            deviceList.get(1).increaseDeviceAvailability(1);
            table.refresh();
        }
        if (code == KeyCode.A){
            wholeSortedList.add(new Device("HVAC North", "123:13:1424", "North Building", 2.0, 4.0,"01/12/20"));
        }
        for(int i = 0; i < deviceList.size(); i++){
            if(deviceList.get(i).getDeviceAvailability() > 100){
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setContentText(deviceList.get(i).getDeviceName() + " is under threat!");
                a.show();
            }
        }
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
            totalHygiene += list.get(i).getDeviceAvailability();
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
        Device southDevice = new Device("HVAC Fan 2", "216.58.216.164","Room 2" ,4.0,4.0, "8/03/2020");
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
                        return 1;
                    } else if( stock1.getDeviceAvailability() > stock2.getDeviceAvailability() ) {
                        return -1;
                    } else {
                        return 0;
                    }
                });
        SortedList<Device> southSortedList = new SortedList<>(southWingDevices);
        networkMap.put("Whole Hospital", wholeSortedList);
        networkMap.put("South Wing", southSortedList);
        networkMap.put("West Wing", southSortedList);
        networkMap.put("East Wing", southSortedList);
        networkMap.put("North Wing", southSortedList);
//        wholeSortedList.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(wholeSortedList);

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
