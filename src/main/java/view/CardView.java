package main.java.view;

import javafx.event.ActionEvent;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;

public class CardView extends ListCell<Cardable> {

    CardCell cardCell;

    public CardView(String buttonText) {
        cardCell = new CardCell();
        cardCell.getAction().setText(buttonText);
    }

    public void setOnAction(OnActionEvent actionEvent) {
        cardCell.getAction().setOnAction(e -> {
            actionEvent.onClick(e);
        });
    }

    @Override
    protected void updateItem(Cardable card, boolean empty) {
        super.updateItem(card, empty);

        // Fix Graphical issues
        if (card == null || empty) {
            setGraphic(null);
            return;
        }

        cardCell.getHeading().setText(card.getHeader1());
        cardCell.getDetails().setText(card.getDetails());
        cardCell.getPic().setImage(new Image(card.getPicPath(), 150, 100, false, false));

        setGraphic(cardCell.getContainer());
    }

    public static interface OnActionEvent {
        void onClick(ActionEvent e);
    }
}