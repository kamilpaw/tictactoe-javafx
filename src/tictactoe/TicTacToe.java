package tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

public class TicTacToe extends Application {


    private int turn;
    private Set<Integer> xset;
    private Set<Integer> oset;
    private Set<Integer> winindexes;

    public TicTacToe() {
        this.xset = new HashSet<>();
        this.oset = new HashSet<>();
        this.winindexes = new HashSet<>();
    }

    public void start(Stage stage) {
        BorderPane bp = new BorderPane();
        bp.setPrefSize(600, 600);

        TilePane tp = new TilePane();
        for (int i = 0; i < 9; i++) {
            Button button = new Button();
            button.setPrefSize(200, 200);
            button.setFont(new Font(60));

            button.setOnAction(event -> {
                if (turn < 9) {
                    if (button.getText().isEmpty()) {
                        if (isEven(turn)) {
                            button.setText("X");
                            xset.add(tp.getChildren().indexOf(button));
                            if (win(xset)) {
                                for (int j : winindexes) {
                                    Button winButton = new Button();
                                    winButton.setFont(new Font(60));
                                    winButton.setStyle("-fx-base: lightgreen");
                                    winButton.setText("X");
                                    tp.getChildren().remove(j);
                                    tp.getChildren().add(j, winButton);
                                }
                            }
                        } else {
                            button.setText("O");
                            oset.add(tp.getChildren().indexOf(button));
                            if (win(oset)) {
                                for (int j : winindexes) {
                                    tp.getChildren().remove(j);
                                    Button winButton = new Button();
                                    winButton.setFont(new Font(60));
                                    winButton.setStyle("-fx-base: lightgreen");
                                    winButton.setText("O");
                                    tp.getChildren().add(j, winButton);
                                }
                            }
                        }
                        turn++;
                    }
                }
            });
            tp.getChildren().add(button);
        }

        Button bt = new Button("RESTART");
        bp.setBottom(bt);


        bp.setCenter(tp);
        Scene scene = new Scene(bp);
        stage.setScene(scene);
        stage.show();
        bt.setOnMouseClicked((event -> {
            stage.close();
            this.xset = new HashSet<>();
            this.oset = new HashSet<>();
            this.winindexes = new HashSet<>();
            turn = 0;
            start(stage);
        }));
    }

    public boolean win(Set<Integer> set) {
        int[] i1 = {0, 1, 2};
        HashSet<Integer> set1 = arrayToHashSet(i1);
        int[] i2 = {3, 4, 5};
        HashSet<Integer> set2 = arrayToHashSet(i2);
        int[] i3 = {6, 7, 8};
        HashSet<Integer> set3 = arrayToHashSet(i3);
        int[] i4 = {0, 4, 8};
        HashSet<Integer> set4 = arrayToHashSet(i4);
        int[] i5 = {2, 5, 8};
        HashSet<Integer> set5 = arrayToHashSet(i5);
        int[] i6 = {1, 4, 7};
        HashSet<Integer> set6 = arrayToHashSet(i6);
        int[] i7 = {2, 4, 6};
        HashSet<Integer> set7 = arrayToHashSet(i7);
        int[] i8 = {0, 3, 6};
        HashSet<Integer> set8 = arrayToHashSet(i8);
        if (isequalto(set, set1) || isequalto(set, set2) || isequalto(set, set3) || isequalto(set, set4) || isequalto(set, set5) || isequalto(set, set6) || isequalto(set, set7) || isequalto(set, set8)) {
            turn = 9;
            return true;
        }
        return false;
    }

    public boolean isequalto(Set<Integer> set1, Set<Integer> set2) {
        for (int i : set2) {
            if (!set1.contains(i)) {
                return false;
            }
        }
        winindexes = set2;
        return true;
    }

    public HashSet<Integer> arrayToHashSet(int[] integers) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : integers) {
            set.add(i);
        }
        return set;
    }

    public boolean isEven(int i) {
        return i % 2 == 0;
    }
}
