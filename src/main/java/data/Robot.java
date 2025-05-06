package data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "robot")
public class Robot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int speeda;
    private int speedb;

    public Robot() {}

    public Robot(int speeda, int speedb) {
        this.speeda = speeda;
        this.speedb = speedb;
    }

    public int getId() {
        return id;
    }

    public int getSpeeda() {
        return speeda;
    }

    public void setSpeeda(int speeda) {
        this.speeda = speeda;
    }

    public int getSpeedb() {
        return speedb;
    }

    public void setSpeedb(int speedb) {
        this.speedb = speedb;
    }
}
