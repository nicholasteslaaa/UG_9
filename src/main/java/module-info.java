module com.ukdw.rplbo.soal_ug_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires io.github.cdimascio.dotenv.java;


    opens com.ukdw.rplbo.soal_ug_javafx to javafx.fxml;
    opens com.ukdw.rplbo.soal_ug_javafx.entity to javafx.base;
    exports com.ukdw.rplbo.soal_ug_javafx;
}