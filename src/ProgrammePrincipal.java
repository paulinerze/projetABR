import java.io.*;
import java.util.*;


public class ProgrammePrincipal {

    static TableauCaseABR tab;
    static ABR abr;

    public static void main(String[] args) {
        tab = new TableauCaseABR();
        Scanner sc = new Scanner(System.in);
        int reponse = -1;
        int choix = -1;

        System.out.println("-------------------------------------------------------------");
        System.out.println("Bonjour et bienvenue dans le programme de gestion d'ABR");
        System.out.println(" ");
        System.out.println("Ce programme inclut plusieurs fonctionnalités qui vous");
        System.out.println("permettrons de gérer plusieurs ABR.");
        System.out.println("-------------------------------------------------------------");

        while (true){
            System.out.println(" ");
            System.out.println("-------------------------------------------------------------");
            System.out.println("Merci de saisir le numéro correspondant à votre choix:");
            System.out.println(" ");
            System.out.println("1 : Importer un ABR depuis un fichier");
            System.out.println("2 : Exporter votre ABR dans un fichier");
            System.out.println("3 : Afficher votre ABR");
            System.out.println("4 : Vérifier mon tableau à importer");
            System.out.println("5 : Inserer un entier dans votre tableau d'ABR");
            System.out.println("6 : Supprimmer un entier dans votre tableau d'ABR");
            System.out.println("7 : Fusionner deux ABR");
            System.out.println("8 : Construire un ABR à partir du tableau d'ABR");
            System.out.println("-------------------------------------------------------------");
            System.out.println("0 : Quitter");
            System.out.println("-------------------------------------------------------------");
            System.out.println(" ");

            try {
                reponse = sc.nextInt();
                switch (reponse) {
                    case 0 :
                        System.out.println("Au revoir et à bientôt !");
                        sc.close();
                        System.out.println(" ");
                        return;
                    case 1 :
                        System.out.println("Merci de saisir le nom de votre fichier");
                        creerTableau(sc.next());
                        System.out.println(" ");
                        System.out.println("Voici contenu de votre tableau :");
                        System.out.println(tab.afficherTableauABR());
                        System.out.println(" ");
                        System.out.println("Tapez 1 pour continuer");
                        do{
                            choix = sc.nextInt();
                        } while (choix != 1);
                        System.out.println(" ");
                        break;
                    case 2 :
                        System.out.println("Merci de saisir le nom de votre fichier (si il n'existe pas il sera créé)");
                        exporter(sc.next());
                        System.out.println(" ");
                        System.out.println("Tapez 1 pour continuer");
                        do{
                            choix = sc.nextInt();
                        } while (choix != 1);
                        System.out.println(" ");
                        break;
                    case 3 :
                        System.out.println("Voici le contenu de votre tableau ");
                        System.out.println(" ");
                        System.out.println(tab.afficherTableauABR());
                        System.out.println(" ");
                        System.out.println("Tapez 1 pour continuer");
                        do{
                            choix = sc.nextInt();
                        } while (choix != 1);
                        System.out.println(" ");
                        break;
                    case 4 :
                        System.out.println("Nous allons lancer la vérification du tableau. Nous afficherons les erreurs si il y en a.");
                        System.out.println("Si rien ne s'affiche, cela signifie que votre tableau est prêt à être importé.");
                        System.out.println(" ");
                        System.out.println("Merci de saisir le nom de votre fichier");
                        verification(sc.next());
                        System.out.println(" ");
                        System.out.println("Tapez 1 pour continuer");
                        do{
                            choix = sc.nextInt();
                        } while (choix != 1);
                        System.out.println(" ");
                        break;
                    case 5 :
                        System.out.println("Merci de saisir l'entier à insérer dans votre tableau");
                        int elementAinserer = sc.nextInt();
                        ajouter(elementAinserer);
                        System.out.println(" ");
                        System.out.println("Tapez 1 pour continuer");
                        do{
                            choix = sc.nextInt();
                        } while (choix != 1);
                        System.out.println(" ");
                        break;
                    case 6 :
                        System.out.println("Merci de saisir l'entier à supprimer dans votre tableau");
                        System.out.println("Attention, si vous souhaitez supprimer un arbre entièrement, vous devez le fusionner puis supprimer une à une les valeurs.");
                        int elementAsupprimer = sc.nextInt();
                        supprimer(elementAsupprimer);
                        System.out.println(" ");
                        System.out.println("Tapez 1 pour continuer");
                        do{
                            choix = sc.nextInt();
                        } while (choix != 1);
                        System.out.println(" ");
                        break;
                    case 7 :
                        System.out.println("Vous allez fusionner deux cases de votre tableaux.");
                        System.out.println("Merci de saisir la position de la 1ere case à fusionner (nous la fusionnerons avec la 2e case)");
                        int indice = sc.nextInt();
                        fusion(indice-1);
                        System.out.println("Voici votre tableau d'ABR: ");
                        System.out.println(" ");
                        System.out.println(tab.afficherTableauABR());
                        System.out.println(" ");
                        System.out.println("Tapez 1 pour continuer");
                        do{
                            choix = sc.nextInt();
                        } while (choix != 1);
                        System.out.println(" ");
                        break;
                    case 8 :
                        if (tab.getTab().size() > 1){
                            TableauCaseABR t = new TableauCaseABR(tab);
                            abr = TABRenABR(t);
                            System.out.println("Voici votre ABR: ");
                            System.out.println(" ");
                            System.out.println(abr.afficherABR());
                            System.out.println(" ");

                        } else if (tab.getTab().size() == 1){
                            System.out.println("Voici votre ABR: ");
                            System.out.println(" ");
                            System.out.println(tab.getTab().get(0).getAbr().afficherABR());
                            System.out.println(" ");

                        } else {
                            System.out.println("Désolé mais votre tableau ne contient pas assez de cases (il vous en faut 2 au minimum");
                            System.out.println(" ");
                        }
                        System.out.println("Tapez 1 pour continuer");
                        do{
                            choix = sc.nextInt();
                        } while (choix != 1);
                        System.out.println(" ");
                        break;
                    default :
                        System.out.println("Ce choix n'est pas disponible");
                        System.out.println(" ");


                        break;
                }

            }catch (InputMismatchException ex){
                System.out.println("L'entrée n'est pas un numéro ");

            }
        }

    }

    //Methode qui permet de créer un tableau à partir d'un fichier
    // Complexitée: Ω(n) et O(n²)
    public static void creerTableau(String file) {
        tab = new TableauCaseABR();
        FileInputStream fis;
        try {
            fis = new FileInputStream(System.getProperty("user.dir") + "/"+file);

            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line = null;
            try {
                while ((line = br.readLine()) != null) {
                    tab.insererCase(new CaseABR(line));
                }
                br.close();
            } catch (IOException ex) {
            }


        } catch (FileNotFoundException ex) {
            System.out.println("Fichier "+System.getProperty("user.dir") + "/"+file+" introuvable");
            return;
        }

        System.out.println("Votre tableau d'ABR a bien été importé.");

    }

    //Méthode qui permet d'exporter un ABR dans un fichier
    // Complexitée: Θ(n)
    private static void exporter(String file) {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
            writer.write(tab.afficherTableauABR());
            System.out.println("Votre tableau d'ABR a bien été exporté.");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {writer.close();} catch (Exception ex) {

            }
        }
    }

    public static void verification(String file) {
        tab = new TableauCaseABR();
        FileInputStream fis;
        try {
            fis = new FileInputStream(System.getProperty("user.dir") + "/" + file);

            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line = null;
            try {
                while ((line = br.readLine()) != null) {
                    tab.verifierCases(new CaseABR(line, 1));
                }
                br.close();
            } catch (IOException ex) {
            } catch (NullPointerException npe) {
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Fichier " + System.getProperty("user.dir") + "/" + file + " introuvable");
            return;
        }


    }


    //Ajouter une valeur au TABR
    // Complexitée: Θ(n)
    public static void ajouter(int value){
        int res = tab.insererValeur(value);
        if (res == 0){
            System.out.println(" ");
            System.out.println("Ajout effectué");
        } else if (res == 1){
            System.out.println("Attention, la valeur que vous tentez d'insérer n'est pas comprise entre les intervalles");
        } else {
            System.out.println("Attention, la valeur que vous tentez d'insérer est déjà présente dans votre tableau");
        }
    }

    //Supprimer une valeur du TABR
    // Complexitée: Θ(n)
    public static void supprimer(int value){
        int res = tab.supprimerValeur(value);
        if (res == 0){
            System.out.println(" ");
            System.out.println("Supression effectuée");
        } else if (res == 1){
            System.out.println("Attention, la valeur que vous tentez de supprimer n'est pas comprise entre les intervalles");
        } else {
            System.out.println("Attention, vous tentez de supprimer une valeur qui n'est pas contenue dans votre tableau");
        }
    }

    //Fusionner les cases aux index i et i+1
    // Complexitée: Θ(n)
    public static void fusion(int index){
        if (index >= tab.getTab().size()-1){
            System.out.println(" ");
            System.out.println("Position invalide");
        }else {
            int res = tab.fusion(index);
            System.out.println(" ");
            System.out.println("Intervalles fusionnés.");
        }
    }

    //Transforme un TABR en  un ABR
    // Complexitée: Θ(n²)
    public static ABR TABRenABR(TableauCaseABR t) {
        ABR a = new ABR();
        while (t.getTab().size() > 1){
            t.fusion(0);
        }
        a = t.getTab().get(0).getAbr();
        return a;
    }

}
