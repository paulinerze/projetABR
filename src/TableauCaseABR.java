import java.util.ArrayList;

public class TableauCaseABR{

    /************
     * Attribut *
     ************/
    private ArrayList<CaseABR> tab;


    /*****************
     * Constructeurs *
     *****************/

    //Construit un TABR vide
    public TableauCaseABR() {
        super();
        tab = new ArrayList<CaseABR>();
    }

    //Construit un TABR à partir d'un autre TABR
    public TableauCaseABR(TableauCaseABR abr) {
        super();
        tab = (ArrayList<CaseABR>) abr.getTab().clone();
    }


    /*******************
     * Getter & setter *
     *******************/

    public ArrayList<CaseABR> getTab() {

        return tab;
    }

    /************
     * Methodes *
     ************/

    //Ajoute la case ABR "abr" au Tableau
    // Complexitée: au mieux O(1), au pire O(n)
    public void insererCase(CaseABR abr){
        int position = 0;
        boolean intersection = true; // On utilise un booléen pour tester si les bornes du Tableau d'ABR ne se chevauchent pas
        if (!tab.isEmpty()){
            for (int i = 0 ; i < tab.size(); i++){

                // Vérification de la non-intersection des bornes
                if ((abr.getBorneinf() <= tab.get(i).getBorneinf() && abr.getBornesup() >= tab.get(i).getBorneinf()) ||
                        abr.getBorneinf() <= tab.get(i).getBornesup() && abr.getBorneinf() >= tab.get(i).getBorneinf() ||
                        abr.getBorneinf()<= tab.get(i).getBorneinf() && abr.getBornesup() >= tab.get(i).getBornesup()){
                    intersection = false;
                }
            }
            //recherche de la bonne position pour y ajouter la case
            if(!(abr.getBornesup() < tab.get(0).getBorneinf())){
                for (int i = 0 ; i < tab.size(); i++){
                    if (i == tab.size()-1){ // si i est le dernier index de tab
                        if (abr.getBornesup() < tab.get(i).getBorneinf()){ // et si sa borne supp est inférieure à la borne inf de i, alors il faut le positionner avant i
                            position = i - 1;
                            break;
                        } else if (abr.getBorneinf() > tab.get(i).getBornesup()) { //et si sa borne inf est supérieure à la borne supp de i, alors il faut le positionner après i
                            position = i + 1;
                            break;
                        }
                    }else {
                        if (abr.getBorneinf() > tab.get(i).getBornesup() && abr.getBornesup() < tab.get(i+1).getBorneinf()){ //si sa borne inf est suppérieure à la borne supp de i et sa borne supp est inférieure à la borne inf de i+1
                            position = i + 1; // il se positionne après i
                            break;
                        }
                    }
                }
            }
        }
        if (position == -1){
            position = 0;
        }
        if (intersection){
            tab.add(position, abr); // on ajoute la case à la position "position" de tab
        } else {
            System.out.println("Bornes :"+abr.getBorneinf()+", "+abr.getBornesup()+" incorrectes, conflit avec un autre intervalle, annulation de l'import de cet intervalle");
        }
    }

    public void verifierCases(CaseABR abr){
        boolean intersection = true; // On utilise un booléen pour tester si les bornes du Tableau d'ABR ne se chevauchent pas
        if (!tab.isEmpty()){
            for (int i = 0 ; i < tab.size(); i++){

                // Vérification de la non-intersection des bornes
                if ((abr.getBorneinf() <= tab.get(i).getBorneinf() && abr.getBornesup() >= tab.get(i).getBorneinf()) ||
                        abr.getBorneinf() <= tab.get(i).getBornesup() && abr.getBorneinf() >= tab.get(i).getBorneinf() ||
                        abr.getBorneinf()<= tab.get(i).getBorneinf() && abr.getBornesup() >= tab.get(i).getBornesup()){
                    intersection = false;
                }
            }
        }
        if (intersection){
        } else {
            System.out.println("Bornes :"+abr.getBorneinf()+", "+abr.getBornesup()+" incorrectes, conflit avec un autre intervalle, annulation de l'import de cet intervalle");
        }
    }



    //Ajoute la valeur "val" dans la bonne case, retourne 1 si la valeur n'est pas correcte
    //L'entier retourné permet de retourner le bon cas d'erreur lors de son appel dans la classe main
    // Complexitée: Θ(n)
    public int insererValeur(int val){
        for (int i = 0 ;  i < tab.size() ; i++){
            if (val >= tab.get(i).getBorneinf() && val <= tab.get(i).getBornesup()){
                return tab.get(i).insererVal(val);
            }
        }
        return 1;
    }


    //Supprime la valeur "value" de la case qui la contient, retourne 1 si la valeur n'est pas présente
    //L'entier retourné permet de retourner le bon cas d'erreur lors de son appel dans la classe main
    // Complexitée: Θ(n)
    public int supprimerValeur(int val) {
        for (int i = 0 ;  i < tab.size() ; i++){ //recherche de la bonne case à l'aide des bornes inférieures et supérieures de ceux-ci
            if (val >= tab.get(i).getBorneinf() && val <= tab.get(i).getBornesup()){
                return tab.get(i).supprimer(val);
            }
        }
        return 1;
    }



    //Permet de fusionner la case à la position "index" du tableau avec la case suivante
    // Complexitée: Θ(n)
    public int fusion(int index) {
        if (tab.size() > index + 1){
            int borneinf = tab.get(index).getBorneinf(); //on définit la nouvelle borne inférieure avec la borne inf de i
            int bornesup = tab.get(index+1).getBornesup(); //on définit la nouvelle borne supérieure avec la borne supérieure de i+1
            CaseABR maCase = new CaseABR(borneinf, bornesup, tab.get(index).getAbr(), tab.get(index+1).getAbr()); //On créé une case "maCase"
            tab.remove(index + 1); //suppression des anciennes cases
            tab.remove(index);

            tab.add(index, maCase);//Ajout de la nouvelle case à la position "index"
        }
        return 0;
    }

    //Permet de formater le Tableau d'ABR pour l'exporter dans un fichier sous forme de plusieurs String (une par case)
    // Complexitée: Θ(n)
    public String afficherTableauABR(){
        String tmp = "";
        for (CaseABR abr : tab){
            tmp = tmp+abr.afficherCaseABR()+"\n";
        }
        return tmp;
    }






}
