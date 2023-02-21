
package entités;

import com.mysql.cj.conf.StringProperty;
import java.sql.Date;
import java.util.Objects;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Test {
    private String Type ;
    private String Réference ;
    private Date DteTest ;
    private String Résultat ;

    public Test(String Type, String Réference, Date DteTest, String Résultat) {
        this.Type = Type;
        this.Réference = Réference;
        this.DteTest = DteTest;
        this.Résultat = Résultat;
    }

    public Test() {
    this.Type = "";
    this.Réference = "";
    this.DteTest = new Date(0);
    this.Résultat = "";

    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getRéference() {
        return Réference;
    }

    public void setRéference(String Réference) {
        this.Réference = Réference;
    }

    public Date getDteTest() {
        return DteTest;
    }

    public void setDteTest(Date DteTest) {
        this.DteTest = DteTest;
    }

    public String getRésultat() {
        return Résultat;
    }

    public void setRésultat(String Résultat) {
        this.Résultat = Résultat;
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
        final Test other = (Test) obj;
        if (!Objects.equals(this.Type, other.Type)) {
            return false;
        }
        if (!Objects.equals(this.Réference, other.Réference)) {
            return false;
        }
        if (!Objects.equals(this.Résultat, other.Résultat)) {
            return false;
        }
        return Objects.equals(this.DteTest, other.DteTest);
    }

    @Override
    public String toString() {
        return "Test{" + "Type=" + Type + ", R\u00e9ference=" + Réference + ", DteTest=" + DteTest + ", R\u00e9sultat=" + Résultat + '}';
    }

   public SimpleStringProperty typeProperty() {
    return new SimpleStringProperty(Type);
}

public SimpleStringProperty référenceProperty() {
    return new SimpleStringProperty(Réference);
}

public ObjectProperty<Date> dteTestProperty() {
    return new SimpleObjectProperty<>(DteTest);
}

public SimpleStringProperty resultatProperty() {
    return new SimpleStringProperty(Résultat);
}


   
}
