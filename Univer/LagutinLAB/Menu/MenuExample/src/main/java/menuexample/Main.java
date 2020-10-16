package menuexample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class Main extends Application {

    private class PageData {

        public String name;
        public String description;
        public Image image;

        public PageData(String name, String description) {
            this.name = name;
            this.description = description;
            image = new Image(getClass().getResourceAsStream("images/" + name + ".jpg"));
        }
    }

    private final PageData[] pages = new PageData[]{
            new PageData("Winter",
                    "When December is approaching there is a breath of winter in the autumn air. It is getting colder day by day. "
                            + "The cold makes the hands cold and stiff. People put on warm clothes.\n"
                            + "And then suddenly soft, white snowflakes are falling thick and fast covering the ground and roofs. "
                            + "When the branches of the fir trees and pines are covered with snow they look beautifully. "
                            + "The snow lies thick upon the ground and birds hop about looking for food.\n"
                            + "A lot of icicles all glittering in the sun hang from roofs of houses. Everything seems to be covered into a white blanket. "
                            + "Rivers and lakes are covered with a thick ice and you can skate on them.\n"
                            + "Winter is a jolly time for children. They make snowmen and play snowballs. "
                            + "When the weather is fine children and grown-ups go skiing, skating or sliding down the snow covered hills."
            ),
            new PageData("Spring",
                    "Springtime is a wonderful season. The warm sun shines and melts winter ice and snow. Nature begins to awake. "
                            + "All the trees and bushes are covered with small buds that get bigger and bigger and after some time turn into young green leaves. "
                            + "Then everything begin to blossom, flowers are everywhere. We can hear the birds singing and smell the sweet aroma of blooming trees.\n"
                            + "The weather is fine almost all the time. It’s not hot, that’s why we go for a walk with great pleasure. "
                            + "Sometimes it rains in spring and the air gets filled with the odour of the wet ground. Thуn the first springtime storm comes with bright flashes of lightning and thunder. "
                            + "It makes the ground fertile so that in summer and autumn we can enjoy fruits and vegetables from our gardens."
            ),
            new PageData("Summer",
                    "The bright sunshine warms the Earth. When the heat is oppressive, it is a treat to get out of town and have a walk in the woods.\n"
                            + "The fields are green. In summer nature surrounds us with all kinds of flowers: merry dandelions, beautiful roses, shy daisies.\n"
                            + "Sometimes the weather becomes too hot in summer and the air gets stuffy. Then a thunderstorm usually brings relief. "
                            + "The sky is suddenly covered with dark clouds and distant rolls of thunder are heard. Later bright flashes of lightning are followed by claps of thunder and it begins to rain. "
                            + "After the thunderstorm the air is remarkably fresh, and very often we can see a beautiful bridge across the sky which is called a rainbow."),
            new PageData("Autumn",
                    "In September summer yields to autumn. The days become shorter and the nights become longer. The sun rays are not so bright and warm. "
                            + "The fruit trees that not long ago bloomed with flowers are heavy with juicy apples and pears. Early autumn is the period of flaming colours, when the landscape is gold and brown. "
                            + "In the daytime the silver gossamer is flying in the air. But unfortunately this period of fine weather is very short and deep autumn follows it.\n"
                            + "October is the best month for planting trees. It is a season when the trees are fantastic — yellow, red, green and brown. The trees are loosing their leaves. "
                            + "Now the ground is like a carpet of many colours. It gets dark earlier and often heavy clouds cover the sky bringing rain. Birds don’t sing their songs. "
                            + "They begin to leave for warm countries.\n"
                            + "November is the month of fog, rain and snow. Animals put on their winter coats. The landscape becomes rather dull.Nature slowly falls asleep for winter.")
    };

    private final Font[] fonts = {
            new Font("Verdana Bold", 22),
            new Font("Arial Italic", 18),
            new Font("Tahoma", 16),
            new Font("Times New Roman", 20)
    };

    private ImageView pic;
    private Label name;
    private Label description;
    private VBox vbox;

    private int currentIndex = -1;

    private void createSceneElements() {

        name = new Label();
        name.setFont(fonts[0]);
        pic = new ImageView();
        pic.setFitHeight(200);
        pic.setPreserveRatio(true);
        description = new Label();
        description.setFont(fonts[3]);
        description.setWrapText(true);
        description.setTextAlignment(TextAlignment.JUSTIFY);

        vbox = new VBox();
        vbox.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0, 10, 0, 10));
        vbox.getChildren().addAll(name, pic, description);
        shuffle();
    }

    private void shuffle() {
        int i = currentIndex;
        while (i == currentIndex) {
            i = (int) (Math.random() * pages.length);
        }
        pic.setImage(pages[i].image);
        name.setText(pages[i].name);
        description.setText(pages[i].description);
        currentIndex = i;
    }

    private Menu createFileMenu() {
        Menu menuFile = new Menu("File");
        MenuItem shuffle = new MenuItem("Shuffle",
                new ImageView(new Image(getClass().getResourceAsStream("images/shutter.jpeg")))
        );
        shuffle.setOnAction((ActionEvent t) -> {
            shuffle();
            vbox.setVisible(true);
        });
        MenuItem clear = new MenuItem("Clear");
        clear.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        clear.setOnAction((ActionEvent t) -> {
            vbox.setVisible(false);
        });
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction((ActionEvent t) -> {
            Platform.exit();
        });

        menuFile.getItems().addAll(shuffle, clear, new SeparatorMenuItem(), exit);
        return menuFile;
    }

    private CheckMenuItem createMenuItem(String title, final Node node) {
        CheckMenuItem cmi = new CheckMenuItem(title);
        cmi.setSelected(true);
        cmi.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue ov, Boolean old_val, Boolean new_val) {
                node.setVisible(new_val);
            }
        });
        return cmi;
    }

    private Menu createViewMenu() {
        Menu menuView = new Menu("View");
        CheckMenuItem titleView = createMenuItem("Title", name);
        CheckMenuItem picView = createMenuItem("Picture", pic);
        CheckMenuItem descriptionView = createMenuItem("Description", description);
        menuView.getItems().addAll(titleView, picView, descriptionView);
        return menuView;
    }

    private Menu createEditMenu() {
        Menu menuEdit = new Menu("Edit");
        Menu menuFont = new Menu("Font selection");
        ToggleGroup groupFont = new ToggleGroup();
        for (int i = 0; i < fonts.length - 1; i++) {
            RadioMenuItem itemFont = new RadioMenuItem(fonts[i].getName());
            itemFont.setUserData(fonts[i]);
            itemFont.setToggleGroup(groupFont);
            menuFont.getItems().add(itemFont);
        }
        MenuItem defaultFont = new MenuItem("Default Font");
        defaultFont.setDisable(true);

        defaultFont.setOnAction((ActionEvent t) -> {
            description.setFont(fonts[3]);
            groupFont.getSelectedToggle().setSelected(false);
            defaultFont.setDisable(true);
        });

        groupFont.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (groupFont.getSelectedToggle() != null) {
                    Font font = (Font) groupFont.getSelectedToggle().getUserData();
                    description.setFont(font);
                    defaultFont.setDisable(false);
                } else {
                    defaultFont.setDisable(true);
                }
            }
        });

        menuEdit.getItems().addAll(menuFont, defaultFont);
        return menuEdit;
    }

    private SplitMenuButton createEditColorMenu() {
        MenuItem red = new MenuItem("Plum");
        red.setOnAction((ActionEvent event) -> {
            vbox.setBackground(new Background(new BackgroundFill(Color.PLUM, CornerRadii.EMPTY, Insets.EMPTY)));
        });
        MenuItem blue = new MenuItem("Tan");
        blue.setOnAction((ActionEvent event) -> {
            vbox.setBackground(new Background(new BackgroundFill(Color.TAN, CornerRadii.EMPTY, Insets.EMPTY)));
        });
        MenuItem green = new MenuItem("Khaki");
        green.setOnAction((ActionEvent event) -> {
            vbox.setBackground(new Background(new BackgroundFill(Color.KHAKI, CornerRadii.EMPTY, Insets.EMPTY)));
        });
        SplitMenuButton textColorMenu = new SplitMenuButton(red, blue, green);
        textColorMenu.setText("Select color");
        textColorMenu.setOnAction((ActionEvent event) -> {
            vbox.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
        });
        return textColorMenu;
    }

    private ContextMenu createCopyImgMenu() {
        ContextMenu cmCopyImg = new ContextMenu();
        MenuItem cmItem1 = new MenuItem("Copy Image");
        cmItem1.setOnAction((ActionEvent e) -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putImage(pic.getImage());
            clipboard.setContent(content);
        });

        cmCopyImg.getItems().add(cmItem1);
        pic.setOnContextMenuRequested((ContextMenuEvent e) -> {
            cmCopyImg.show(pic, e.getScreenX(), e.getScreenY());
        });
        return cmCopyImg;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Example menu");
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 600, 800);
        createSceneElements();

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(createFileMenu(), createViewMenu(), createEditMenu());

        SplitMenuButton menuColor = createEditColorMenu();
        createCopyImgMenu();

        root.setTop(menuBar);
        root.setCenter(vbox);
        root.setBottom(menuColor);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

