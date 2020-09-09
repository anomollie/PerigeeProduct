import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;

public class BaseWindow extends GridPane {

    public BaseWindow() throws FileNotFoundException {
        this.setWidth(1280);
        this.setHeight(720);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(0, 10, 10, 10));
        this.setGridLinesVisible(true);
        RowConstraints rcon = new RowConstraints();
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        InfoWindow topBar = new InfoWindow(1278, 125, false);
        InfoWindow leftMid = new InfoWindow(368, 141, true);
        InfoWindow midMid = new InfoWindow(498, 280, true);
        InfoWindow rightMid = new InfoWindow(368, 280, true);
        InfoWindow leftBot = new InfoWindow(368, 421, true);
        InfoWindow midBot = new InfoWindow(498, 280, true);
        InfoWindow rightBot = new InfoWindow(368, 280, true);
//        leftMid.setMaxHeight(141);
//        leftBot.setMaxHeight(421);
        this.add(topBar, 0, 0, 3, 1);
        this.add(leftMid, 0, 1);
        this.add(midMid, 1, 1);
        this.add(rightMid, 2, 1);
        this.add(leftBot, 0, 1, 1, 2);
        this.add(midBot, 1, 2);
        this.add(rightBot,2, 2);
        GridPane.setMargin(leftMid, new Insets(0,0, 10, 0));
        GridPane.setHalignment(leftMid, HPos.LEFT);
        GridPane.setValignment(leftMid, VPos.TOP);
        GridPane.setValignment(leftBot, VPos.BOTTOM);
        topBar.addTextField();
        leftMid.makeLeftMid();
        rightMid.makeRightMid();
        midMid.makeMidMid();
        midBot.makeMidBot();
        leftBot.makeLeftBot();
        rightBot.makeRightBot();
    }

}
