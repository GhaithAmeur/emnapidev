package entités;

import java.util.Objects;

public class Laboratoire {
    private String Name;
    private String Adresse;

    public Laboratoire(String Name, String Adresse) {
        this.Name = Name;
        this.Adresse = Adresse;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Laboratoire other = (Laboratoire) obj;
        if (!Objects.equals(this.Name, other.Name)) {
            return false;
        }
        return Objects.equals(this.Adresse, other.Adresse);
    }

    @Override
    public String toString() {
        return "Laboratoire{" + "Name=" + Name + ", Adresse=" + Adresse + '}';
    }

    public String getNameProperty() {
        return Name;
    }

    public String getAdresseProperty() {
        return Adresse;
    }




    
}
