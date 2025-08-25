package app;

public class DonneesStatistiques {
    public int populationTotale;
    public int nombreEnfants;
    public int nombreAdultes;
    public int nombreAges;
    public int nombreActifs;
    public int nombreChomeurs;

    public DonneesStatistiques(int total, int enfants, int adultes, int ages, int actifs, int chomeurs) {
        this.populationTotale = total;
        this.nombreEnfants = enfants;
        this.nombreAdultes = adultes;
        this.nombreAges = ages;
        this.nombreActifs = actifs;
        this.nombreChomeurs = chomeurs;
    }
}

