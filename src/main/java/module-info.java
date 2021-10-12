module com.example.towerdefence {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires junit;

    opens com.example.towerdefence;
    exports com.example.towerdefence;
    exports com.example.towerdefence.objects;
    exports com.example.towerdefence.tests;
    exports com.example.towerdefence.StartUpPage;
    exports com.example.towerdefence.ConfigScreenPage;
    opens com.example.towerdefence.ConfigScreenPage;
    opens com.example.towerdefence.StartUpPage;
}