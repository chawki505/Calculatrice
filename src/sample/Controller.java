package sample;
/**
 * Created by chawki on 07/12/16.
 */


import javafx.event.ActionEvent;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {


    @FXML
    private Button num0;
    @FXML
    private Button num1;
    @FXML
    private Button num2;
    @FXML
    private Button num3;
    @FXML
    private Button num4;
    @FXML
    private Button num5;
    @FXML
    private Button num6;
    @FXML
    private Button num7;
    @FXML
    private Button num8;
    @FXML
    private Button num9;

    @FXML
    private Button add;
    @FXML
    private Button sub;
    @FXML
    private Button div;
    @FXML
    private Button mul;


    @FXML
    private Button reset;
    @FXML
    private Button egal;
    @FXML
    private Button point;

    @FXML
    private Label operation;
    @FXML
    private Label afficheEgale;
    @FXML
    private Label afficheResulta;


    private double number1;
    private double number2;
    private String operateur;
    private double resulta;
    private String nbs;


    public Controller() {
    }


    @FXML
    /*methode pour le bouton reset*/
    private void setReset() {
        this.afficheEgale.setVisible(false);
        this.afficheResulta.setText("");
        this.operation.setText("");
        this.number1 = 0;
        this.number2 = 0;
        this.resulta = 0;
        this.operateur = "";
        this.nbs = "";
    }


    @FXML
    /*methode pour capturer les chiffre des bouton */
    private void proccessNumber(ActionEvent event) {
        this.afficheEgale.setVisible(true);
        String val = ((Button) event.getSource()).getText();
        nbs = nbs + val;
        afficheResulta.setText(nbs);
        operation.setText(operation.getText() + val);
    }

    @FXML
    /*methode pour les bouton d'operation(*+-/) */
    private void proccessOperateur(ActionEvent event) {
        try {
            String val = ((Button) event.getSource()).getText();
            afficheResulta.setText("");

            /*test si c la premiere operation */
            if (resulta == 0 && number1 == 0 && number2 == 0) {
                operateur = val;
                number1 = Double.parseDouble(nbs);
                nbs = "";
                operation.setText(operation.getText() + operateur);

                /*le sinon pour la 3eme operation ou + */
            } else {
                number2 = Double.parseDouble(nbs);
                nbs = "";
                resulta = calcule(number1, number2, operateur);
                number1 = resulta;

                afficheResulta.setText(String.valueOf(resulta));

                operateur = val;
                operation.setText(operation.getText() + operateur);


//                }
            }
        } catch (Exception e) {
            messageAlert(event);
        }
    }

    @FXML
    /*methode pour calculer et afficher le resulta de la derniere operation*/
    private void prossesEgal(ActionEvent event) {
        try {

            if (number1 == 0) {
                number1 = Double.parseDouble(nbs);
                nbs = "";
                if ((number1 - (int) number1 != 0)) {
                    afficheResulta.setText(String.valueOf(number1));
                } else afficheResulta.setText(String.valueOf((int) number1));
            } else {
                number2 = Double.parseDouble(nbs);
                nbs = "";

                resulta = calcule(number1, number2, operateur);
                if ((resulta - (int) resulta != 0)) {
                    afficheResulta.setText(String.valueOf(resulta));
                } else afficheResulta.setText(String.valueOf((int) resulta));
            }
        } catch (Exception e) {
            messageAlert(event);
        }


    }

    /*methode pour afficher un message dalerte pour appuyer sur le bouton reste C*/
    private void messageAlert(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("ERROR");
        if (((Button) event.getSource()).getText().equals("=") || resulta == 0) {
            alert.setHeaderText("No calcule");
            alert.setContentText("Please set operation");
        } else {

            alert.setHeaderText("No reset");
            alert.setContentText("Please reset calculator");
        }
        alert.showAndWait();
    }


    /*methode pour le calcule*/
    private double calcule(double num1, double num2, String operateur) {

        switch (operateur) {
            case "+":
                return (num1 + num2);
            case "-":
                return (num1 - num2);
            case "*":
                return (num1 * num2);
            case "/":
                return (num1 / num2);
        }
        return 0;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.num1.setText("1");
        this.num2.setText("2");
        this.num3.setText("3");
        this.num4.setText("4");
        this.num5.setText("5");
        this.num6.setText("6");
        this.num7.setText("7");
        this.num8.setText("8");
        this.num9.setText("9");
        this.num0.setText("0");
        this.point.setText(".");

        this.egal.setText("=");
        this.mul.setText("*");
        this.sub.setText("-");
        this.div.setText("/");
        this.add.setText("+");

        this.reset.setText("C");

        this.afficheEgale.setText("=");

        this.setReset();
    }


}

