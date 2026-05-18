package com.example.converttabsjava;

// Importation des classes nécessaires
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * ViewPagerAdapter - Adaptateur pour le ViewPager2.
 * Gère la création des fragments selon la position de l'onglet sélectionné.
 * Position 0 : TempFragment (conversion de température)
 * Position 1 : DistanceFragment (conversion de distance)
 */
public class ViewPagerAdapter extends FragmentStateAdapter {

    /**
     * Constructeur de l'adaptateur.
     *
     * @param fragmentActivity L'activité hôte contenant le ViewPager2
     */
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    /**
     * Crée le fragment correspondant à la position donnée.
     *
     * @param position La position de l'onglet (0 ou 1)
     * @return Le fragment à afficher pour cette position
     */
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Retourner le fragment correspondant à la position
        if (position == 0) {
            return new TempFragment();      // Onglet 0 : fragment température
        } else {
            return new DistanceFragment();  // Onglet 1 : fragment distance
        }
    }

    /**
     * Retourne le nombre total de fragments (onglets).
     *
     * @return 2 (température + distance)
     */
    @Override
    public int getItemCount() {
        return 2; // Deux onglets au total
    }
}
