/**
 * 
 */
/**
 * @author Andreas Fernau
 *
 */
module tamClip
{
    requires opencsv;

    requires transitive javafx.graphics;

    requires javafx.controls;

    requires java.datatransfer;

    requires java.desktop;

    requires javafx.base;

    requires javafx.fxml;

    exports main.java.application;

    exports main.java.application.addItem;

    exports main.java.application.detailedItem;

    exports main.java.application.main;

    exports main.java.application.model;

    exports main.java.application.overview;

    exports main.java.application.settings;

    exports main.java.application.titlescreen;

    opens main.java.controllers;

}