import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Ticker extends BorderPane {
    private ArrayList<TickIndicator> myTickIndicators;
    private Pane leftArrow;
    private Pane rightArrow;
    public Ticker(double width, double height) {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setSize(width, height);
        this.setBottom(makeTickIndicators());
        this.leftArrow = makeLeftArrow();
        this.rightArrow = makeRightArrow();
        this.setLeft(leftArrow);
        this.setRight(rightArrow);
        this.setCenter(makeCenter("MED"));
        this.addClickListeners();
    }
    private void setSize(double width, double height) {
        this.setPrefSize(width, height);
        this.setMaxSize(width, height);
        this.setMinSize(width, height);
    }
    private HBox makeTickIndicators() {
        HBox circlebox = new HBox();
        myTickIndicators = new ArrayList<>();
        TickIndicator c1 = new TickIndicator(5, Color.WHITE, false);
        TickIndicator c2 = new TickIndicator(10, Color.TRANSPARENT, true);
        TickIndicator c3 = new TickIndicator(5, Color.WHITE, false);
        myTickIndicators.add(c1);
        myTickIndicators.add(c2);
        myTickIndicators.add(c3);
        circlebox.getChildren().add(c1);
        circlebox.getChildren().add(c2);
        circlebox.getChildren().add(c3);
        circlebox.setSpacing(10);
        circlebox.setAlignment(Pos.CENTER);
        circlebox.setTranslateX(-6);
        circlebox.setPadding(new Insets(5, 5, 5, 5));
        return circlebox;
    }
    private Pane makeLeftArrow() {
        Line l1 = new Line(0, 50, -20, 30);
        Line l2 = new Line(0, 10, -20, 30);
        l1.setStroke(Color.WHITE);
        l2.setStroke(Color.WHITE);
        l1.setStrokeWidth(3);
        l2.setStrokeWidth(3);
        Pane arrow1 = new Pane();
        arrow1.getChildren().add(l1);
        arrow1.getChildren().add(l2);
        arrow1.setTranslateX(30);
        arrow1.setTranslateY(90);
        return arrow1;
    }
    private Pane makeRightArrow() {
        Line l3 = new Line(0, 50, 20, 30);
        Line l4 = new Line(0, 10, 20, 30);
        l3.setStroke(Color.WHITE);
        l4.setStroke(Color.WHITE);
        l3.setStrokeWidth(3);
        l4.setStrokeWidth(3);
        Pane arrow2 = new Pane();
        arrow2.getChildren().add(l3);
        arrow2.getChildren().add(l4);
        arrow2.setTranslateX(20);
        arrow2.setTranslateY(90);
        arrow2.setPadding(new Insets(10, 10, 10, 30));
        return arrow2;
    }
    private void addClickListeners() {
        this.getLeft().setOnMouseClicked(e -> scrollLeft());
        this.getLeft().setOnMousePressed(e -> leftArrowPress());
        this.getLeft().setOnMouseReleased(e -> leftArrowRelease());
        this.getRight().setOnMouseClicked(e -> scrollRight());
        this.getRight().setOnMousePressed(e -> rightArrowPress());
        this.getRight().setOnMouseReleased(e -> rightArrowRelease());
    }
    private void scrollLeft() {
        for (int i = 0; i < myTickIndicators.size(); i++) {
            if (myTickIndicators.get(i).checkSelected()) {
                if (i == 0) {
                    myTickIndicators.get(i).contract();
                    myTickIndicators.get(myTickIndicators.size()-1).expand();
                    this.setCenter(makeCenter("LOW"));
                    break;
                }
                else {
                    myTickIndicators.get(i).contract();
                    myTickIndicators.get(i-1).expand();
                    if (i == 1) {
                        this.setCenter(makeCenter("HIGH"));
                    }
                    else {
                        this.setCenter(makeCenter("MED"));
                    }
                    break;
                }
            }
        }
    }
    private void scrollRight() {
        for (int i = 0; i < myTickIndicators.size(); i++) {
            if (myTickIndicators.get(i).checkSelected()) {
                if (i == myTickIndicators.size()-1) {
                    myTickIndicators.get(i).contract();
                    myTickIndicators.get(0).expand();
                    this.setCenter(makeCenter("HIGH"));
                    break;
                }
                else {
                    myTickIndicators.get(i).contract();
                    myTickIndicators.get(i+1).expand();
                    if (i == 0) {
                        this.setCenter(makeCenter("MED"));
                    }
                    else {
                        this.setCenter(makeCenter("LOW"));
                    }
                    break;
                }
            }
        }
    }
    private StackPane makeCenter(String priority) {
        StackPane text = new StackPane();
        Text t = new Text();
        t.setUnderline(true);
        t.setFont(Font.font("Ariel", FontWeight.BOLD, 30));
        t.setFill(Color.web("0x48C4F2"));
        Text t2 = new Text();
        t2.setFont(Font.font("Ariel", FontWeight.SEMI_BOLD, 20));
        t2.setFill(Color.WHITE);
        t2.setWrappingWidth(this.getPrefWidth()*.6);
        switch (priority) {
            case ("MED"):
                t.setText("PRIORITY: MEDIUM");
                t2.setText("Identified CVE 2018-6692. Recommend switching network from high-priority assets.");
                break;
            case ("LOW"):
                t.setText("PRIORITY: LOW");
                t2.setText("Turn on \"Secure data back-up & restore mechanism to increase device hygiene\"");
                break;
            case ("HIGH"):
                t.setText("PRIORITY: HIGH");
                t2.setText("Unauthorized change request");
                break;
        }

        text.setTranslateX(30);
        text.setAlignment(Pos.CENTER);
        t.setTranslateY(-60);
        t.setTranslateX(-10);
        t2.setTranslateY(50);
//        text.setSpacing(25);
        text.getChildren().add(t);
        text.getChildren().add(t2);
        text.setTranslateY(-37);
        HBox h = new HBox();
        VBox v = new VBox();
        VBox v2 = new VBox();
        h.getChildren().add(v);
        h.getChildren().add(v2);
        this.setCenter(text);
        h.setPrefSize(this.getCenter().getBoundsInLocal().getWidth(), this.getCenter().getBoundsInLocal().getHeight());
        v.setPrefSize(h.getPrefWidth()/2, h.getPrefHeight());
        v2.setPrefSize(h.getPrefWidth()/2, h.getPrefHeight());
        v.setOnMouseClicked(e -> scrollLeft());
        v2.setOnMouseClicked(e -> scrollRight());
        v.setOnMousePressed(e -> leftArrowPress());
        v.setOnMouseReleased(e -> leftArrowRelease());
        v2.setOnMousePressed(e -> rightArrowPress());
        v2.setOnMouseReleased(e -> rightArrowRelease());
        text.getChildren().add(h);
        return text;
    }
    private void leftArrowPress() {
        Line l1 = (Line) leftArrow.getChildren().get(0);
        Line l2 = (Line) leftArrow.getChildren().get(1);
        l1.setStroke(Color.GRAY);
        l2.setStroke(Color.GRAY);
    }
    private void leftArrowRelease() {
        Line l1 = (Line) leftArrow.getChildren().get(0);
        Line l2 = (Line) leftArrow.getChildren().get(1);
        l1.setStroke(Color.WHITE);
        l2.setStroke(Color.WHITE);
    }
    private void rightArrowPress() {
        Line l1 = (Line) rightArrow.getChildren().get(0);
        Line l2 = (Line) rightArrow.getChildren().get(1);
        l1.setStroke(Color.GRAY);
        l2.setStroke(Color.GRAY);
    }
    private void rightArrowRelease() {
        Line l1 = (Line) rightArrow.getChildren().get(0);
        Line l2 = (Line) rightArrow.getChildren().get(1);
        l1.setStroke(Color.WHITE);
        l2.setStroke(Color.WHITE);
    }
}
