/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Deathscythvi
 */
public class RideAnimals {

    public int id;
    private String nameAnimal;
    private String speciesAnimal;
    private int HaveLuggage;
    private float luggageMass;
    private float animalMass;
    private String SpecialNeeds;

    public RideAnimals() {
    }

    public File getRegpic() {
        return Regpic;
    }

    public void setRegpic(File Regpic) {
        this.Regpic = Regpic;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    private File Regpic;
    private String photo;
    
    public RideAnimals(String nameAnimal, String speciesAnimal, int HaveLuggage, float luggageMass, float animalMass, String SpecialNeeds) {
        this.nameAnimal = nameAnimal;
        this.speciesAnimal = speciesAnimal;
        this.HaveLuggage = HaveLuggage;
        this.luggageMass = luggageMass;
        this.animalMass = animalMass;
        this.SpecialNeeds = SpecialNeeds;
    }

    
    
    public RideAnimals(String nameAnimal, String speciesAnimal, float luggageMass, float animalMass, String SpecialNeeds) {
        this.nameAnimal = nameAnimal;
        this.speciesAnimal = speciesAnimal;
        this.luggageMass = luggageMass;
        this.animalMass = animalMass;
        this.SpecialNeeds = SpecialNeeds;
    }
    

    public RideAnimals(int id, String nameAnimal, String speciesAnimal, int HaveLuggage, float luggageMass, float animalMass, String SpecialNeeds) {
        this.id = id;
        this.nameAnimal = nameAnimal;
        this.speciesAnimal = speciesAnimal;
        this.HaveLuggage = HaveLuggage;
        this.luggageMass = luggageMass;
        this.animalMass = animalMass;
        this.SpecialNeeds = SpecialNeeds;
    }

    public RideAnimals(String nameAnimal, String speciesAnimal, float luggageMass, float animalMass, String SpecialNeeds, File Regpic, String photo) {
        this.nameAnimal = nameAnimal;
        this.speciesAnimal = speciesAnimal;
        this.luggageMass = luggageMass;
        this.animalMass = animalMass;
        this.SpecialNeeds = SpecialNeeds;
        this.Regpic = Regpic;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameAnimal() {
        return nameAnimal;
    }

    public void setNameAnimal(String nameAnimal) {
        this.nameAnimal = nameAnimal;
    }

    public String getSpeciesAnimal() {
        return speciesAnimal;
    }

    public void setSpeciesAnimal(String speciesAnimal) {
        this.speciesAnimal = speciesAnimal;
    }

    public int getHaveLuggage() {
        return HaveLuggage;
    }

    public void setHaveLuggage(byte HaveLuggage) {
        this.HaveLuggage = HaveLuggage;
    }

    public float getLuggageMass() {
        return luggageMass;
    }

    public void setLuggageMass(float luggageMass) {
        this.luggageMass = luggageMass;
    }

    public float getAnimalMass() {
        return animalMass;
    }

    public void setAnimalMass(float animalMass) {
        this.animalMass = animalMass;
    }

    public String getSpecialNeeds() {
        return SpecialNeeds;
    }

    public void setSpecialNeeds(String SpecialNeeds) {
        this.SpecialNeeds = SpecialNeeds;
    }

    @Override
    public String toString() {
        return "RideAnimals{" + "id=" + id + ", nameAnimal=" + nameAnimal + ", speciesAnimal=" + speciesAnimal + ", HaveLuggage=" + HaveLuggage + ", luggageMass=" + luggageMass + ", animalMass=" + animalMass + ", SpecialNeeds=" + SpecialNeeds + '}';
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
        final RideAnimals other = (RideAnimals) obj;
        if (this.HaveLuggage != other.HaveLuggage) {
            return false;
        }
        if (Float.floatToIntBits(this.luggageMass) != Float.floatToIntBits(other.luggageMass)) {
            return false;
        }
        if (Float.floatToIntBits(this.animalMass) != Float.floatToIntBits(other.animalMass)) {
            return false;
        }
        if (!Objects.equals(this.nameAnimal, other.nameAnimal)) {
            return false;
        }
        if (!Objects.equals(this.speciesAnimal, other.speciesAnimal)) {
            return false;
        }
        if (!Objects.equals(this.SpecialNeeds, other.SpecialNeeds)) {
            return false;
        }
        return true;
    }
    

    
}
