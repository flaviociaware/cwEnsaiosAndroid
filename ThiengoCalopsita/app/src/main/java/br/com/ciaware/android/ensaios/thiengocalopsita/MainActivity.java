package br.com.ciaware.android.ensaios.thiengocalopsita;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;
import java.util.List;

import br.com.ciaware.android.ensaios.thiengocalopsita.domain.Car;
import br.com.ciaware.android.ensaios.thiengocalopsita.domain.Person;
import br.com.ciaware.android.ensaios.thiengocalopsita.fragments.CarFragment;
import br.com.ciaware.android.ensaios.thiengocalopsita.fragments.LuxuryCarFragment;
import br.com.ciaware.android.ensaios.thiengocalopsita.fragments.OldCarFragment;
import br.com.ciaware.android.ensaios.thiengocalopsita.fragments.PopularCarFragment;
import br.com.ciaware.android.ensaios.thiengocalopsita.fragments.SportCarFragment;
import br.com.ciaware.android.ensaios.thiengocalopsita.model.ProfileDrawerItemCw;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "LOG";

    private Toolbar mToolbar;
//    private Toolbar mToolbarBottom;
    private Drawer navigationDrawerLeft;
    private AccountHeader headerNavigationLeft;
    private int mPositionClicked = 1;
    private FloatingActionMenu fab;

    private List<PrimaryDrawerItem> listCatefories;
    private List<Person> listProfile;
    private List<Car> listCars;

    private int mItemDrawerSelected;
    private int mProfileDrawerSelected;


//    private OnCheckedChangeListener mOnCheckedChangeListener = new OnCheckedChangeListener() {
//        @Override
//        public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
//            Toast.makeText(MainActivity.this, "onCheckedChanged: " + (isChecked ? "true" : "false "), Toast.LENGTH_SHORT).show();
//
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            mItemDrawerSelected = savedInstanceState.getInt("mItemDrawerSelected", 0);
            mProfileDrawerSelected = savedInstanceState.getInt("mProfileDrawerSelected", 0);
            listCars = savedInstanceState.getParcelableArrayList("listCars");
        }
        else{
            listCars = getSetCarList(50);
        }


        mToolbar = (Toolbar)findViewById(R.id.tb_main);
        mToolbar.setTitle("Todos os carros");
        //mToolbar.setSubtitle("Sub título");
        //mToolbar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(mToolbar);

        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*
        mToolbarBottom = (Toolbar)findViewById(R.id.inc_tb_bottom);

        mToolbarBottom.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent it = null;

                switch(item.getItemId()){
                    case R.id.action_facebook:
                        it = new Intent(Intent.ACTION_VIEW);
                        it.setData(Uri.parse("http://www.facebook.com"));
                        break;
                    case R.id.action_youtube:
                        it = new Intent(Intent.ACTION_VIEW);
                        it.setData(Uri.parse("http://www.youtube.com"));
                        break;
                    case R.id.action_google_plus:
                        it = new Intent(Intent.ACTION_VIEW);
                        it.setData(Uri.parse("http://plus.google.com"));
                        break;
                    case R.id.action_linkedin:
                        it = new Intent(Intent.ACTION_VIEW);
                        it.setData(Uri.parse("http://www.linkedin.com"));
                        break;
                    case R.id.action_whatsapp:
                        it = new Intent(Intent.ACTION_VIEW);
                        it.setData(Uri.parse("http://www.whatsapp.com"));
                        break;
                }

                startActivity(it);
                return true;
            }
        });

        mToolbarBottom.inflateMenu(R.menu.menu_bottom);

        mToolbarBottom.findViewById(R.id.iv_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Settings pressed", Toast.LENGTH_SHORT).show();
            }
        });
        */

        // FRAGMENT
        Log.v("fragment", "bucando mainFrag");
        Fragment frag = getSupportFragmentManager().findFragmentByTag("mainFrag");
        if(frag == null) {
            Log.v("fragment", "criando mainFrag");
            frag = new CarFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rl_fragment_container, frag, "mainFrag");
            ft.commit();
        }

        headerNavigationLeft = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .withThreeSmallProfileImages(false)
                .withHeaderBackground(R.drawable.ct6)
                //.withSelectionSecondLineShown(false)
                .addProfiles(
                        new ProfileDrawerItemCw().withBackground(R.drawable.gallardo).withName("Flavio Barbosa").withEmail("flavio@ciaware.com.br").withIcon(getResources().getDrawable(R.drawable.person_1)),
                        new ProfileDrawerItemCw().withBackground(R.drawable.camaro).withName("Gabriel Gomes Barbosa").withEmail("(Sem Email)").withIcon(getResources().getDrawable(R.drawable.person_2)),
                        new ProfileDrawerItemCw().withBackground(R.drawable.bmw_720).withName("Ricardo Mazer Barbosa").withEmail("(Sem Email)").withIcon(getResources().getDrawable(R.drawable.person_3)),
                        new ProfileDrawerItemCw().withBackground(R.drawable.corvette).withName("Leonardo Mazer Bueno da Silva").withEmail("(Sem Email)").withIcon(getResources().getDrawable(R.drawable.person_4))
                )
                                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                                    @Override
                                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                                        Log.v("events", "onProfileChanged");
                                        Log.v("events profileListOpen", Boolean.toString(ProfileDrawerItemCw.profileListOpen));

                                        Toast.makeText(MainActivity.this, "withOnAccountHeaderListener", Toast.LENGTH_SHORT).show();
                                        //headerNavigationLeft.setBackgroundRes(R.drawable.vyron);
                                        headerNavigationLeft.setBackgroundRes(((ProfileDrawerItemCw)profile).getBackground());

                                        ProfileDrawerItemCw.profileListOpen = false;


                                        Log.v("events onProfileChanged", "getIdentifier");

                                        //int idTvEmail = Resources.getSystem().getIdentifier("material_drawer_account_header_email", "id", "com.mikepenz.materialdrawer");
                                        int idTvEmail = view.getContext().getResources().getIdentifier("material_drawer_account_header_email", "id", view.getContext().getPackageName());


                                        if (idTvEmail > 0) {
                                            //((TextView) view.findViewById(R.id.material_drawer_account_header_email)).setText(profile.getEmail().toString());

                                            Log.v("events onProfileChanged", view.getClass() + "/" + view.getId() + ".findViewById(idTvEmail)");

                                            final TextView tvEmail = (TextView) ((View) view.getParent()).findViewById(idTvEmail);

                                            if (tvEmail != null) {
                                                tvEmail.setText(profile.getEmail().toString());

//                                if (!tvEmail.hasOnClickListeners()) {
//                                    tvEmail.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            Toast.makeText(MainActivity.this, "Alterar o email: " + ((TextView)v).getText(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    });
//                                }

                                            } else {
                                                Log.v("events onProfileChanged", "findViewById(idTvEmail) tvEmail1 == null");

                                                final TextView tvEmail2 = (TextView) ((View) view.getParent().getParent()).findViewById(idTvEmail);

                                                if (tvEmail2 != null) {
                                                    tvEmail2.setText(profile.getEmail().toString());

                                                } else {
                                                    Log.v("events onProfileChanged", "findViewById(idTvEmail) tvEmail2 == null");
                                                }

                                            }
                                        } else {
                                            Log.v("events onProfileChanged", "getIdentifier = 0");

                                        }

                                        //ProfileDrawerItemCw.setAccountHeader(headerNavigationLeft);

//                        if (profile instanceof ProfileDrawerItemCw) {
//
//                            Log.v("setIsShowInHeader", "withOnAccountHeaderListener > true");
//
//                            ((ProfileDrawerItemCw) profile).setIsShowInHeader(true);
//                        }

                                        return false;
                                    }


                                }).withOnAccountHeaderSelectionViewClickListener(new AccountHeader.OnAccountHeaderSelectionViewClickListener() {
                    @Override
                    public boolean onClick(View view, IProfile profile) {
                        Log.v("events", "onClick");
                        Log.v("events profileListOpen", Boolean.toString(ProfileDrawerItemCw.profileListOpen));

//                        for (IProfile profileSet :
//                                headerNavigationLeft.getProfiles()) {
//
//                                ((ProfileDrawerItemCw) profile).setIsShowInHeader(false);
//
//                                Log.v("setIsShowInHeader", "withOnAccountHeaderSelectionViewClickListener > false");
//                        }

/*
                        if (view.findViewById(R.id.material_drawer_account_header_email)!=null) {
                            //((TextView)view.findViewById(R.id.material_drawer_account_header_email)).setText(profile.getEmail().toString());
                        }
*/
                        ProfileDrawerItemCw.profileListOpen = !ProfileDrawerItemCw.profileListOpen;
                        return false;
                    }
                })
                                .build();

        ProfileDrawerItemCw.setAccountHeader(headerNavigationLeft);

        // NAVIGATION DRAWER [FB]
        navigationDrawerLeft = new DrawerBuilder(this)
                .withToolbar(mToolbar)
                //.withDisplayBelowStatusBar(true)
                .withDisplayBelowStatusBar(false)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.LEFT)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(0)
                .withActionBarDrawerToggle(true)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        // Position começa em 1 e não em 0.
                        Log.v("onItemClick", "position=" + Integer.toString(position));

                        if (drawerItem!=null) {
                            Log.v("onItemClick", "drawerItem.getType= " + drawerItem.getType());
                            Log.v("onItemClick", "drawerItem.getIdentifier()=" + Integer.toString(drawerItem.getIdentifier()));
                        } else {
                            Log.v("onItemClick", "drawerItem == null");
                        }

//                        for (int count = 1, tam = navigationDrawerLeft.getDrawerItems().size(); count <= tam; count++) {
//                            if ((count == mPositionClicked) && (navigationDrawerLeft.getDrawerItems().get(count - 1) instanceof PrimaryDrawerItem)) {
//                                PrimaryDrawerItem aux = (PrimaryDrawerItem) navigationDrawerLeft.getDrawerItems().get(count - 1);
//                                aux.withIcon(getResources().getDrawable(getCorrectDrawerIcon(count, false)));
//                                break;
//                            }
//                        }

                        //if (navigationDrawerLeft.getDrawerItems().get(position - 1) instanceof PrimaryDrawerItem) {
                        //    ((PrimaryDrawerItem) drawerItem).withIcon(getResources().getDrawable(getCorrectDrawerIcon(position, true)));
                        //}

                        mPositionClicked = position;
                        // Redesenha os icones [FB]
                        //navigationDrawerLeft.getAdapter().notifyDataSetChanged();

                        Fragment frag = null;
                        mItemDrawerSelected = position;

                        if(position == 1){ // ALL CARS
                            frag = new CarFragment();
                        }
                        else if(position == 2){ // LUXURY CAR
                            frag = new LuxuryCarFragment();
                        }
                        else if(position == 3){ // SPORT CAR
                            frag = new SportCarFragment();
                        }
                        else if(position == 4){ // OLD CAR
                            frag = new OldCarFragment();
                        }
                        else if(position == 5){ // POPULAR CAR
                            frag = new PopularCarFragment();
                        }



                        Log.v("fragment", "redefinindo mainFrag - criando ft");

                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        if (ft!=null) {

                            if (frag!=null) {
                                Log.v("fragment", "redefinindo mainFrag");
                                ft.replace(R.id.rl_fragment_container, frag, "mainFrag");
                                ft.commit();
                            } else {

                                Log.v("fragment", "criando frag porque estava nulo ");
                                frag = new CarFragment();
                                ft.replace(R.id.rl_fragment_container, frag, "mainFrag");
                                ft.commit();
                            }
                        }

                        if ((drawerItem!=null) && (drawerItem instanceof  PrimaryDrawerItem)) {
                            mToolbar.setTitle(((PrimaryDrawerItem) drawerItem).getName().toString());
                        }


                        return false;
                    }
                })
                .withAccountHeader(headerNavigationLeft)
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(View view, int position, IDrawerItem drawerItem) {
                        Toast.makeText(MainActivity.this, "onItemLongClick" + position, Toast.LENGTH_LONG).show();
                        return false;
                    }
                }).build();

        /*
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Carros Esportivos").withIcon(R.drawable.car_selected_1));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Carros de Luxo").withIcon(R.drawable.car_2));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Carros para Colecionadores").withIcon(R.drawable.car_3));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Carros Populares").withIcon(R.drawable.car_4));
        navigationDrawerLeft.addItem(new SectionDrawerItem().withName("Configurações"));
        navigationDrawerLeft.addItem(new SwitchDrawerItem().withName("Notificação").withChecked(false).withOnCheckedChangeListener(mOnCheckedChangeListener));
        navigationDrawerLeft.addItem(new ToggleDrawerItem().withName("News").withChecked(true).withOnCheckedChangeListener(mOnCheckedChangeListener));
        */

        listCatefories = getSetCategoryList();
        if(listCatefories != null && listCatefories.size() > 0){
            for( int i = 0; i < listCatefories.size(); i++ ){
                navigationDrawerLeft.addItem( listCatefories.get(i) );
            }
            navigationDrawerLeft.setSelection(mItemDrawerSelected);
        }

        // FLOATING ACTION BUTTON
        fab = (FloatingActionMenu) findViewById(R.id.fab);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_second_activity){
            startActivity(new Intent(this, SecondActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }


    /*private int getCorrectDrawerIcon(int position, boolean isSelecetd){

        // Position começa em 1 e não em 0.
        Log.v("getCorrectDrawerIcon", Integer.toString(position) + " = " + Boolean.toString(isSelecetd));

        switch(position){
            case 1:
                return( isSelecetd ? R.drawable.car_selected_1 : R.drawable.car_1 );
            case 2:
                return( isSelecetd ? R.drawable.car_selected_2 : R.drawable.car_2 );
            case 3:
                return( isSelecetd ? R.drawable.car_selected_3 : R.drawable.car_3 );
            case 4:
                return( isSelecetd ? R.drawable.car_selected_4 : R.drawable.car_4 );
        }
        return(0);
    }*/

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("mItemDrawerSelected", mItemDrawerSelected);
        outState.putInt("mProfileDrawerSelected", mProfileDrawerSelected);
        outState.putParcelableArrayList("listCars", (ArrayList<Car>) listCars);
        outState = navigationDrawerLeft.saveInstanceState(outState);
        outState = headerNavigationLeft.saveInstanceState(outState);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if(navigationDrawerLeft.isDrawerOpen()){
            navigationDrawerLeft.closeDrawer();
        }
        else if((fab!=null) && (fab.isOpened())){
            fab.close(true);
        }
        else{
            super.onBackPressed();
        }
    }

    // CAR
//    public List<Car> getSetCarList(int qtd){
//        String[] models = new String[]{"Gallardo", "Vyron", "Corvette", "Pagani Zonda", "Porsche 911 Carrera", "BMW 720i", "DB77", "Mustang", "Camaro", "CT6"};
//        String[] brands = new String[]{"Lamborghini", " bugatti", "Chevrolet", "Pagani", "Porsche", "BMW", "Aston Martin", "Ford", "Chevrolet", "Cadillac"};
//        int[] photos = new int[]{R.drawable.gallardo, R.drawable.vyron, R.drawable.corvette, R.drawable.paganni_zonda, R.drawable.porsche_911, R.drawable.bmw_720, R.drawable.db77, R.drawable.mustang, R.drawable.camaro, R.drawable.ct6};
//        List<Car> listAux = new ArrayList<>();
//
//        for(int i = 0; i < qtd; i++){
//            Car c = new Car( models[i % models.length], brands[ i % brands.length ], photos[i % models.length] );
//            listAux.add(c);
//        }
//        return(listAux);
//    }


    public List<Car> getSetCarList(int qtd){
        return(getSetCarList(qtd, 0));
    }

    public List<Car> getSetCarList(int qtd, int category){
        String[] models = new String[]{"Gallardo", "Vyron", "Corvette", "Pagani Zonda", "Porsche 911 Carrera", "BMW 720i", "DB77", "Mustang", "Camaro", "CT6"};
        String[] brands = new String[]{"Lamborghini", " bugatti", "Chevrolet", "Pagani", "Porsche", "BMW", "Aston Martin", "Ford", "Chevrolet", "Cadillac"};
        int[] categories = new int[]{2, 1, 2, 1, 1, 4, 3, 2, 4, 1};
        int[] photos = new int[]{R.drawable.gallardo, R.drawable.vyron, R.drawable.corvette, R.drawable.paganni_zonda, R.drawable.porsche_911, R.drawable.bmw_720, R.drawable.db77, R.drawable.mustang, R.drawable.camaro, R.drawable.ct6};
        String description = "Lorem Ipsum é simplesmente uma simulação de texto da indústria tipográfica e de impressos, e vem sendo utilizado desde o século XVI, quando um impressor desconhecido pegou uma bandeja de tipos e os embaralhou para fazer um livro de modelos de tipos. Lorem Ipsum sobreviveu não só a cinco séculos, como também ao salto para a editoração eletrônica, permanecendo essencialmente inalterado. Se popularizou na década de 60, quando a Letraset lançou decalques contendo passagens de Lorem Ipsum, e mais recentemente quando passou a ser integrado a softwares de editoração eletrônica como Aldus PageMaker.";
        List<Car> listAux = new ArrayList<>();

        for(int i = 0; i < qtd; i++){
            Car c = new Car( models[i % models.length], brands[ i % brands.length], photos[i % models.length] );
            c.setDescription( description );
            c.setCategory( categories[ i % brands.length ] );
            c.setTel("33221155");

            if(category != 0 && c.getCategory() != category){
                continue;
            }

            listAux.add(c);
        }
        return(listAux);
    }

    public List<Car> getCarsByCategory(int category){
        List<Car> listAux = new ArrayList<>();
        for(int i = 0; i < listCars.size() ; i++){
            if(category != 0 && listCars.get(i).getCategory() != category){
                continue;
            }

            listAux.add(listCars.get(i));
        }
        return(listAux);
    }

    public List<Car> getListCars(){
        return(listCars);
    }

    // CATEGORIES
    private List<PrimaryDrawerItem> getSetCategoryList(){
        String[] names = new String[]{"Todos os Carros", "Carros de Luxo", "Carros Esportivos", "Carros para Colecionadores", "Carros Populares"};
        int[] icons = new int[]{R.drawable.car_1, R.drawable.car_1, R.drawable.car_2, R.drawable.car_3, R.drawable.car_4};
        int[] iconsSelected = new int[]{R.drawable.car_selected_1, R.drawable.car_selected_1, R.drawable.car_selected_2, R.drawable.car_selected_3, R.drawable.car_selected_4};
        List<PrimaryDrawerItem> list = new ArrayList<>();

        for(int i = 0; i < names.length; i++){
            PrimaryDrawerItem aux = new PrimaryDrawerItem();


            aux.withName(names[i]);
            aux.withIcon(getResources().getDrawable(icons[i]));
            aux.withTextColor(getResources().getColor(R.color.colorPrimarytext));
            aux.withSelectedIcon(getResources().getDrawable(iconsSelected[i]));
            aux.withSelectedTextColor(getResources().getColor(R.color.colorPrimary));

            list.add( aux );
        }
        return(list);
    }


}
