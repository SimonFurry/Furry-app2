package com.example.inventorymanagementapplication;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class Controller /*implements Initializable*/ {

    @FXML
    private TableView<Inventory> tableView;
    @FXML
    private TableColumn<Inventory, String> productName;
    @FXML
    private TableColumn<Inventory, String> productPrice;
    @FXML
    private TableColumn<Inventory, String> serialNumber;


    @FXML TextArea textDisplay;
    @FXML TextField itemDesc;
    @FXML TextField itemDate;
    @FXML TextField textField;
    @FXML TextField textField1;
    @FXML TextField textField2;
    @FXML TextField textFieldDelete;
    @FXML Button addButton;
    @FXML TextField saveName;
    @FXML TextField itemName, itemPrice, itemSerial;



    private Stage stage;
    /*
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        productName.setCellFactory(new PropertyValueFactory<Inventory, String>("Item Name"));
    }

     */
    public void init(Stage stage){
        this.stage = stage;
    }

    public void addNewList(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(inventoryApplication.class.getResource("NameList.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Inventory Manager");
        stage.setScene(scene);
        stage.show();
    }
        public void listName(MouseEvent mouseEvent) throws IOException {
            String fileName = textField.getText();
            fileName = fileName + ".txt";
            File file = new File("Lists/"+fileName);
            file.createNewFile();
            ((Stage)(((Button)mouseEvent.getSource()).getScene().getWindow())).close();
        }
    public void editExistingList(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(inventoryApplication.class.getResource("EditList.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Inventory Manager");
        stage.setScene(scene);
        stage.show();
    }
        public void replaceListName(MouseEvent mouseEvent) throws IOException {
            String fileName1 = textField1.getText();
            String fileName2 = textField2.getText();

            File file1 = new File("Lists/"+fileName1+".txt");
            File file2 = new File("Lists/"+fileName2+".txt");

            if(file1.renameTo(file2)){
                System.out.print("Success"); //Success and failure for testing.
            }else{
                System.out.print("failure");
            }
            ((Stage)(((Button)mouseEvent.getSource()).getScene().getWindow())).close();
        }

    public void removeExistingList(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(inventoryApplication.class.getResource("DeleteList.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Inventory Manager");
        stage.setScene(scene);
        stage.show();
    }
        public void deleteList(MouseEvent mouseEvent) {

            String fileNameDelete = textFieldDelete.getText();
            File fileToDelete = new File("Lists/"+fileNameDelete + ".txt");

            if(fileToDelete.delete()){
                System.out.println("File Deleted Successfully: " + fileToDelete.getName());
            }else{
                System.out.println("Failed to Delete");
            }

            ((Stage)(((Button)mouseEvent.getSource()).getScene().getWindow())).close();
        }

    public void addItemToList(MouseEvent mouseEvent) {
        Inventory inventory = new Inventory();
        inventory.setSerialNumber(itemSerial.getText());
        inventory.setProductName(itemName.getText());
        inventory.setProductPrice(itemPrice.getText());
        tableView.getItems().add(inventory);
        itemSerial.clear();
        itemName.clear();
        itemPrice.clear();
    }

    public void saveList(MouseEvent mouseEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if(file != null) {
            System.out.println("Chosen file: " + file);
        }
        FileWriter writer = new FileWriter(file);

        Inventory inventory = new Inventory();
        List<List<String>> arrList = new ArrayList<>();
        for(int i = 0; i < tableView.getItems().size(); i++){
            inventory = tableView.getItems().get(i);
            arrList.add(new ArrayList<>());
            arrList.get(i).add(inventory.serialNumber.get());
            arrList.get(i).add(""+inventory.productName.get());
            arrList.get(i).add(""+inventory.productPrice.get());
        }
        for(int i = 0; i < arrList.size(); i++){
            for(int j = 0; j <arrList.get(i).size(); j++){
                writer.write(arrList.get(i).get(j) + "\t");
            }
            writer.write("\n");
        }
        writer.close();



    }

    public void loadList(MouseEvent mouseEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if(file != null) {
            System.out.println("Chosen file: " + file);
        }

        Collection<Inventory> fileData = Files.readAllLines(new File(String.valueOf(file)).toPath()).stream().map(line -> {
                    String[] details = line.split("\t");
                    Inventory id = new Inventory();
                    id.setSerialNumber(details[0]);
                    id.setProductPrice(details[2]);
                    id.setProductName(details[1]);
                    return id;
                }).collect(Collectors.toList());
        ObservableList<Inventory> details = FXCollections.observableArrayList(fileData);
        tableView.setEditable(true);

        serialNumber.setCellValueFactory(data -> data.getValue().serialNumberProperty());
        productName.setCellValueFactory(data -> data.getValue().productNameProperty());
        productPrice.setCellValueFactory(data -> data.getValue().productPriceProperty());
        tableView.setItems(details);

    }

    public void exit(MouseEvent mouseEvent) {
        ((Stage)(((Button)mouseEvent.getSource()).getScene().getWindow())).close();
    }

    public void deleteItem(MouseEvent mouseEvent) {
        ObservableList<Inventory> itemSelected, allItems;
        allItems = tableView.getItems();
        itemSelected = tableView.getSelectionModel().getSelectedItems();

        itemSelected.forEach(allItems::remove);
    }
}