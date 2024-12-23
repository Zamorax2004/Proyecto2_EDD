
package proyecto2_edd;


public class Person {
    private String name; // Nombre completo
    private String numeral; // Numeral (ej. "Primero", "Segundo")
    private String mote; // Mote único
    private String title; // Título nobiliario
    private String father; // Nombre del padre
    private String mother; // Nombre de la madre
    private ListaEnlazada children; // Nombres de los hijos
    private String notes; // Notas sobre su vida
    private String fate; // Comentarios sobre su destino o muerte

    
    public Person(String name, String numeral, String mote, String title, 
                  String father, String mother, String notes, String fate) {
        this.name = name;
        this.numeral = numeral;
        this.mote = mote;
        this.title = title;
        this.father = father;
        this.mother = mother;
        this.children = new ListaEnlazada();
        this.notes = notes;
        this.fate = fate;
    }
    
    public Person(String name){
        this.name = name;
    }
    
    
    public void addChild(String childName) {
        this.children.add(childName);
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getNumeral() {
        return numeral;
    }

    public String getAlias() {
        return mote;
    }

    public String getTitle() {
        return title;
    }

    public String getParent() {
        return father;
    }

    public String getMother() {
        return mother;
    }

    public ListaEnlazada getChildren() {
        return children;
    }

    public String getNotes() {
        return notes;
    }

    public String getFate() {
        return fate;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setNumeral(String numeral) {
        this.numeral = numeral;
    }

    public void setAlias(String mote) {
        this.mote = mote;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setParent(String father) {
        this.father = father;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setFate(String fate) {
        this.fate = fate;
    }
}
