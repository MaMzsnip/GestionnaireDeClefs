package fr.gestionnaire.gestionnairedeclefs.model;

import fr.gestionnaire.gestionnairedeclefs.MethodeDatabaseObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Clef implements MethodeDatabaseObject<Clef> {

    private SimpleIntegerProperty id;
    private SimpleIntegerProperty number;
    private SimpleStringProperty color;
    private SimpleStringProperty description;

    public Clef() {}
    public Clef(int id, int number, String color, String description) {
        this.id = new SimpleIntegerProperty(id);
        this.number = new SimpleIntegerProperty(number);
        this.color = new SimpleStringProperty(color);
        this.description = new SimpleStringProperty(description);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getNumber() {
        return number.get();
    }

    public SimpleIntegerProperty numberProperty() {
        return number;
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    public String getColor() {
        return color.get();
    }

    public SimpleStringProperty colorProperty() {
        return color;
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    @Override
    public String toString() {
        return "Clef{" +
                "id=" + id +
                ", number=" + number +
                ", color=" + color +
                ", description=" + description +
                '}';
    }

    @Override
    public List<Clef> getAll(Connection connection) throws SQLException {
        List<Clef> cache = new ArrayList<Clef>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM clefs");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int number = resultSet.getInt("numero");
            String color = resultSet.getString("couleur");
            String description = resultSet.getString("description");
            cache.add(new Clef(id, number, color, description));
        }
        return cache.isEmpty() ? null : cache;
    }
}
