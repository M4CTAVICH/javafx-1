package com.example.login;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class searchfunction {

    private TableView<House> table;
    private static TextField searchBar;
    private ObservableList<House> houseData;
    private static FilteredList<House> filteredData;

    public searchfunction(TableView<House> table, TextField searchBar, ObservableList<House> houseData) {
        this.table = table;
        this.searchBar = searchBar;
        this.houseData = houseData;

        initializeSearchFunction();
    }

    private void initializeSearchFunction() {
        filteredData = new FilteredList<>(houseData, p -> true);

        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData(newValue);
        });

        SortedList<House> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedData);
    }

    public static void filterData(String newValue) {
        filteredData.setPredicate(house -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            String lowerCaseFilter = newValue.toLowerCase();

            if (house.getAddress().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (house.getType().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (String.valueOf(house.getPrice()).contains(lowerCaseFilter)) {
                return true;
            }
            return false;
        });
    }

    public static void triggerSearch() {
        filterData(searchBar.getText());
    }
}
