package tablePocket;

import javafx.geometry.Pos;
        import javafx.scene.control.*;
        import javafx.scene.control.Alert.AlertType;
        import javafx.scene.control.ButtonBar.ButtonData;
        import javafx.scene.layout.GridPane;
        import javafx.scene.text.Font;
        import javafx.scene.text.FontWeight;

public class PetEditDialog {

    private Pet pet;
    private Dialog<Pet> dialog;
    private TextField specEdit;
    private TextField nameEdit;
    private TextField ageEdit;
    private TextField ownEdit;
    private Font font;
    private GridPane root;

    public PetEditDialog(Pet pet, String title) {
        this.pet = pet  ;
        dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(null);
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        font = Font.font("Tahoma", FontWeight.NORMAL, 20);
        createSpecText();
        createNameText();
        createAgeText();
        createOwnerText();
        dialog.getDialogPane().setContent(root);
        createButtons();
    }

    private void createSpecText() {
        Label specPet = new Label("Spec:");
        specPet.setFont(font);
        root.add(specPet, 0, 0);
        specEdit = new TextField();
        specEdit.setFont(Font.font(24));
        specEdit.setPromptText(pet.getSpecies());
        specEdit.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("[a-zA-Zа-яА-Я ]*")) ? change : null));
        root.add(specEdit, 1, 0);
    }

    private void createNameText() {
        Label namePet = new Label("Name:");
        namePet.setFont(font);
        root.add(namePet, 0, 1);
        nameEdit = new TextField();
        nameEdit.setFont(Font.font(24));
        nameEdit.setPromptText(pet.getName());
        nameEdit.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("[a-zA-Zа-яА-Я ]*")) ? change : null));
        root.add(nameEdit, 1, 1);
    }

    private void createAgeText() {
        Label agePet = new Label("Age:");
        agePet.setFont(font);
        root.add(agePet, 0, 2);
        ageEdit = new TextField();
        ageEdit.setFont(Font.font(24));
        ageEdit.setPromptText(Integer.toString(pet.getAge()));
        ageEdit.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("[0-9]*")) ? change : null));
        root.add(ageEdit, 1, 2);
    }

    private void createOwnerText() {
        Label ownPet = new Label("Owner:");
        ownPet.setFont(font);
        root.add(ownPet, 0, 3);
        ownEdit = new TextField();
        ownEdit.setFont(Font.font(24));
        ownEdit.setPromptText(pet.getOwner());
        ownEdit.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("[a-zA-Zа-яА-Я ]*")) ? change : null));
        root.add(ownEdit, 1, 3);
    }

    private void createButtons() {
        ButtonType buttonTypeOk = new ButtonType("Ok", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeCancel);
        dialog.setResultConverter((ButtonType buttonType) -> {
            if (buttonType == buttonTypeOk) {
                if (specEdit.getText().isEmpty()) {
                    message();
                    return null;
                }
                if (nameEdit.getText().isEmpty()) {
                    message();
                    return null;
                }
                if (ageEdit.getText().isEmpty()) {
                    message();
                    return null;
                }
                if (ownEdit.getText().isEmpty()) {
                    message();
                    return null;
                }
                handleOk();
                return pet;
            }
            return null;
        });
    }

    private void message(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Data entry error");
        alert.setHeaderText("Text entry error");
        alert.setContentText("Ввод пустых строк недопустим.");
        alert.showAndWait();
    }

    private void handleOk() {
        pet.setSpecies(specEdit.getText());
        pet.setName(nameEdit.getText());
        pet.setAge(Integer.parseInt(ageEdit.getText()));
        pet.setOwner(ownEdit.getText()); }

    public Dialog<Pet> getDialog() {
        return dialog;
    }
}


