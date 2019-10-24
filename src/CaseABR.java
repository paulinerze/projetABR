public class CaseABR {

    /*************
     * Attributs *
     *************/

    private ABR abr;
    private int borneinf;
    private int bornesup;



    /*****************
     * Constructeurs *
     *****************/


    //Permet de construire une case ABR à partir d'un fichier
    public CaseABR(String fileline) {
        String interv = fileline.substring(0, fileline.indexOf(";")); //stockage des valeurs de l'intervalle
        String values = fileline.substring(fileline.indexOf(";") + 1); //stockage des valeurs de l'ABR
        this.borneinf = Integer.parseInt(interv.substring(0, fileline.indexOf(":")));
        this.bornesup = Integer.parseInt(interv.substring(fileline.indexOf(":") + 1));

        if (this.borneinf > bornesup){
            System.out.println("Bornes incorrectes, création de l'ABR impossible");
            return;
        }

        String[] value = values.split(":");//on stock dans un tableau les valeurs de l'ABR séparées par ":"
        if (!(value.length == 1)){
            for (int i=value.length-1; i >= 0 ; i--){//parcourt du tableau par la fin
                int val = Integer.parseInt(value[i]);
                if (val >= this.borneinf && val <= bornesup) {//Test si la valeur est bien entre l'intervalle
                    if (i == value.length-1){
                        abr = new ABR(val);// si c'est la dernière valeur du tableau on créé un ABR avec comme racine "val"
                    }else {
                        abr.inserer(val);//sinon on insère "val" dans l'ABR
                    }
                } else {
                    System.out.println("La valeur : "+val+", n'est pas dans l'intervalle, elle sera ignorée");
                }
            }
        }

    }

    //Permet de construire une case ABR à partir d'un fichier en testant
    public CaseABR(String fileline, int verification) {
        String interv = fileline.substring(0, fileline.indexOf(";")); //stockage des valeurs de l'intervalle
        String values = fileline.substring(fileline.indexOf(";") + 1); //stockage des valeurs de l'ABR
        this.borneinf = Integer.parseInt(interv.substring(0, fileline.indexOf(":")));
        this.bornesup = Integer.parseInt(interv.substring(fileline.indexOf(":") + 1));

        if (this.borneinf > bornesup){
            System.out.println("Bornes incorrectes, création de l'ABR impossible");
            return;
        }

        String[] value = values.split(":");//on stock dans un tableau les valeurs de l'ABR séparées par ":"
        if (!(value.length == 1)){
            for (int i=value.length-1; i >= 0 ; i--){//parcourt du tableau par la fin
                int val = Integer.parseInt(value[i]);
                if (val >= this.borneinf && val <= bornesup) {//Test si la valeur est bien entre l'intervalle

                } else {
                    System.out.println("La valeur : "+val+", n'est pas dans l'intervalle.");
                }
            }
        }

    }







    //Permet de construire une case ABR à partir de 2 ABR
    //Prend en paramètres les bornes de la case
    public CaseABR(int borneinf, int bornesup, ABR abr1, ABR abr2){
        this.borneinf = borneinf;
        this.bornesup = bornesup;

        //On stocke dans un tableau les valeurs des deux arbres
        String[] values = (abr1.prefixe()+" "+abr2.prefixe()).split("\\s");

        abr = new ABR(Integer.parseInt(values[0])); // on crée un nouvel ABR

        for (int i = 1; i < values.length ; i ++){
            abr.inserer(Integer.parseInt(values[i])); //On insere les valeurs
        }
    }



    /*******************
     * Getter & setter *
     *******************/

    public ABR getAbr() {
        return abr;
    }

    public void setAbr(ABR abr) {
        this.abr = abr;
    }

    public int getBorneinf() {
        return borneinf;
    }

    public void setBorneinf(int borneinf) {
        this.borneinf = borneinf;
    }

    public int getBornesup() {
        return bornesup;
    }

    public void setBornesup(int bornesup) {
        this.bornesup = bornesup;
    }


    /************
     * Methodes *
     ************/

    //permet d'ajouter une valeur dans l'abr
    // Complexitée: au mieux O(1), au pire O(n)
    public int insererVal(int val){
        if (abr == null){
            abr = new ABR(val);
            return 0;
        }else {
            return abr.inserer(val);
        }
    }


    //permet de supprimer "val" dans l'abr
    // Complexitée: au mieux O(1), au pire O(n)

    public int supprimer(int val) {

        return abr.supprimer(val);
    }


    //permet de formater correctement la case pour l'exporter dans un fichier
    // Complexitée: Θ(n)
    public String afficherCaseABR() {
        return borneinf+":"+bornesup+";"+abr.afficherABR();
    }




}
