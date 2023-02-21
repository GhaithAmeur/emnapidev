
package entités;

import java.util.Objects;

public class Biologiste {
    private  String Name ;
    private  String Titre ;
    private int Phone ;
    private int Photo ;
    private String Email ;
    private  String Adresse ;

    public Biologiste(String Name, String Titre, int Phone, int Photo, String Email, String Adresse) {
        this.Name = Name;
        this.Titre = Titre;
        this.Phone = Phone;
        this.Photo = Photo;
        this.Email = Email;
        this.Adresse = Adresse;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public int getPhoto() {
        return Photo;
    }

    public void setPhoto(int Photo) {
        this.Photo = Photo;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
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
        final Biologiste other = (Biologiste) obj;
        if (this.Phone != other.Phone) {
            return false;
        }
        if (this.Photo != other.Photo) {
            return false;
        }
        if (!Objects.equals(this.Name, other.Name)) {
            return false;
        }
        if (!Objects.equals(this.Titre, other.Titre)) {
            return false;
        }
        if (!Objects.equals(this.Email, other.Email)) {
            return false;
        }
        return Objects.equals(this.Adresse, other.Adresse);
    }

    @Override
    public String toString() {
        return "Biologiste{" + "Name=" + Name + ", Titre=" + Titre + ", Phone=" + Phone + ", Photo=" + Photo + ", Email=" + Email + ", Adresse=" + Adresse + '}';
    }
    
    
    
    
}
    
    

    