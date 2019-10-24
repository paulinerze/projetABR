public class ABR {

    /*************
     * Attributs *
     *************/

    private ABR sag;
    private ABR sad;
    private int val;


    /*****************
     * Constructeurs *
     *****************/

    //construit un abr vide
    public ABR() {

    }

    //construit un noeud
    public ABR(int val) {
        this.val = val;
        sag = null;
        sad = null;
    }


    /*******************
     * Getter & setter *
     *******************/

    public ABR getSag() {
        return sag;
    }

    public void setSag(ABR sag) {
        this.sag = sag;
    }

    public ABR getSad() {
        return sad;
    }

    public void setSad(ABR sad) {
        this.sad = sad;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }


    /************
     * Methodes *
     ************/

    //Retourne vrai ou faux si le noeud testé est une feuille
    // Complexitée: Θ(1)
    public boolean estFeuille() {
        if (sag == null && sad == null) {
            return true;
        }
        return false;
    }


    //retourne le sous abr avec la valeur max
    // Complexitée : Ω(log n) et O(n)
    public ABR maxVal() {
        if (sad != null) {//si sad n'est pas vide, appel recursif
            return sad.maxVal();
        } else {
            return this; //sinon on retourne le noeud courant
        }
    }



    //Insérer un élément dans un ABR
    // Complexitée: au mieux O(1), au pire O(n)
    public int inserer(int val) {
        if (val <= 0){ // On n'insère pas une valeur négative ou nulle
            return 1;
        }

        //On test où insérer le nouvel ABR
        if (this.val > val) { //Si plus petit
            if (sag == null) { //et que le sag est vide
                sag = new ABR(val); //on insère
                return 0;
            } else {
                return sag.inserer(val); // Sinon on rappelle récursivement la méthode
            }
        } else if (this.val < val) { //Si est plus grand
            if (sad == null) { //et que le sad est vide
                sad = new ABR(val); //on insère
                return 0;
            } else {
                return sad.inserer(val); // Sinon on rappelle récusivement la méthode
            }
        } else {
            return 2;
        }
    }


    //retire la valeur "value" de l'abr, l'entier retourn� permet de connaitre le bon cas d'erreur
    // Complexitée: au mieux O(1), au pire O(n)
    public int supprimer(int val) {
        if (this.val == val) { //si on tente de supprimer la racine de l'arbre
            if (sag == null) {
                this.val = sad.val; // on remonte sad
                this.sag = sad.sag; //le sag du sad devient le sag de la nouvelle racine
                this.sad = sad.sad; //même principe
            } else {		//Si sag non vide
                ABR tmp;    // On utilise un arbre temporaire pour rechercher la plus grande valeur dans sag
                tmp = this.sag.maxVal();
                this.val = tmp.val; //on remonte la + grande valeur
                if (sag.estFeuille()){ //si sag est une feuille, elle prend la valeur null
                    this.sag = null;
                } else {
                    return this.sag.supprimer(tmp.val); //sinon on appel la méthode sur sag (car on y retire la valeur max)
                }
            }
            return 0;
        } else if (this.val < val) { //sinon si val>abr.val (val est donc dans sad)
            if (sad != null) {
                if (sad.estFeuille() && sad.val == val) {// si sad est une feuille est que sa valeur est = à val
                    sad = null; //on supprime directement l'abr
                    return 0;
                } else {
                    return sad.supprimer(val); //Sinon appel récusrif
                }
            }
            return 2;
        } else { // sinon (value<abr.val)
            if (sag != null) { //si sag non null
                if (sag.estFeuille() && sag.val == val) {// si sag est une feuille est que sa valeur est = à val
                    sag = null; // on supprime directement l'abr
                    return 0;
                } else {
                    return sag.supprimer(val); //sinon appel recursif
                }
            }
            return 2;
        }
    }

    //retourne le parcours prefixe de l'abr
    // Complexitée: Θ(n)
    public String prefixe() {
        if (!estFeuille()) {
            String tmp;
            if (sad == null) {
                tmp = val + " " + sag.prefixe();
            } else if (sag == null) {
                tmp = val + " " + sad.prefixe();
            } else {
                tmp = val + " " + sag.prefixe() + " " + sad.prefixe();
            }
            return tmp;
        } else {
            return "" + val;
        }
    }

    //parcours suffixe de l'abr, retourne une cdc avec le bon format pour écrire l'abr dans un fichier
    // Complexitée: Θ(n)
    public String afficherABR() {
        if (!estFeuille()) {
            String tmp;
            if (sad == null) {
                tmp = sag.afficherABR() + ":" + val;
            } else if (sag == null) {
                tmp = sad.afficherABR() + ":" + val;
            } else {
                tmp = sag.afficherABR() + ":" + sad.afficherABR() + ":" + val;
            }
            return tmp;
        } else {
            return "" + val;
        }
    }

}
