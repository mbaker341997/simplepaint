module org.baker.simplepaint {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.baker.simplepaint to javafx.fxml;
    exports org.baker.simplepaint;
    exports org.baker.simplepaint.tools;
    opens org.baker.simplepaint.tools to javafx.fxml;
}