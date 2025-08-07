package com.example.bmi_rechner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.bmi_rechner.databinding.ActivityMainBinding;
import android.view.Menu;
import android.view.MenuItem;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private static final String PREFS_NAME = "AppPreferences";
    private static final String LANGUAGE_KEY = "language";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // Sprache aus SharedPreferences laden
        String language = loadLanguage();
        setLocale(language);  // Setze die Sprache


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_english) {
            if (Locale.getDefault().getLanguage().equals("en")) {
                changeLanguage("de");
            } else {
                changeLanguage("en");
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Speichern der Sprache in SharedPreferences
     * @param language
     */
    private void saveLanguage(String language) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString(LANGUAGE_KEY, language);
        editor.apply();
    }

    /**
     * Sprache aus SharedPreferences laden
     * @return String
     */
    private String loadLanguage() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return prefs.getString(LANGUAGE_KEY, "de");  // Standardwert ist "de" (Deutsch)
    }

    /**
     * Wechesln der Sprache
     * @param language
     */
    private void changeLanguage(String language) {
        saveLanguage(language);  // Neue Sprache speichern
        setLocale(language);     // Sprache umschalten
        restartActivity();              // Activity neu laden, um die Änderungen anzuwenden
    }

    /**
     * Setzt Sprache fest
     * @param lang
     */
    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = getResources().getConfiguration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }

    /**
     * Neustart der Activity
     */
    private void restartActivity() {
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        finish();
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}