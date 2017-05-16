package udea.drai.model.dto;

public class SoftwareType {

    int codeSWT = 0;
    String nameSWT = "";

    public SoftwareType(int a, String b) {
        this.codeSWT = a;
        this.nameSWT = b;
    }

    public SoftwareType() {
    }

    public void setCodeSWT(int a) {
        this.codeSWT = a;
    }

    public void setNameSWT(String b) {
        this.nameSWT = b;
    }

    public int getCodeSWT() {
        return this.codeSWT;
    }

    public String getNameSWT() {
        return this.nameSWT;
    }
}
