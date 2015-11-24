package br.com.ciaware.android.ensaios.thiengocalopsita;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;
import java.util.List;

import br.com.ciaware.android.ensaios.thiengocalopsita.adapters.TabsAdapter;
import br.com.ciaware.android.ensaios.thiengocalopsita.domain.Car;
import br.com.ciaware.android.ensaios.thiengocalopsita.domain.Person;
import br.com.ciaware.android.ensaios.thiengocalopsita.extras.SlidingTabLayout;
import br.com.ciaware.android.ensaios.thiengocalopsita.model.ProfileDrawerItemCw;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "LOG";

    private Toolbar mToolbar;
    //    private Toolbar mToolbarBottom;
    private Drawer navigationDrawerLeft;
    private AccountHeader headerNavigationLeft;
    private int mPositionClicked = 1;
    //private FloatingActionMenu fab;

    private List<PrimaryDrawerItem> listCatefories;
    private List<Person> listProfile;
    private List<Car> listCars;

    private int mItemDrawerSelected;
    private int mProfileDrawerSelected;

    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;


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

        if (savedInstanceState != null) {
            mItemDrawerSelected = savedInstanceState.getInt("mItemDrawerSelected", 0);
            mProfileDrawerSelected = savedInstanceState.getInt("mProfileDrawerSelected", 0);
            listCars = savedInstanceState.getParcelableArrayList("listCars");
        } else {
            listCars = getSetCarList(50);
        }

        // TOOLBAR
        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("APP Carros");
        //mToolbar.setSubtitle("Sub título");
        //mToolbar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(mToolbar);

        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // TABS
        mViewPager = (ViewPager) findViewById(R.id.vp_tabs);
        mViewPager.setAdapter(new TabsAdapter(getSupportFragmentManager(), this));

        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.stl_tabs);
        //mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.colorFAB));
        //mSlidingTabLayout.setCustomTabView(R.layout.tab_view, R.id.tv_tab);
        mSlidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.v("events", "onPageSelected, position = " + position );

                navigationDrawerLeft.setSelection(position+1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        mSlidingTabLayout.setViewPager(mViewPager);


        // NAVIGATION DRAWER - HEADER
        headerNavigationLeft = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .withThreeSmallProfileImages(false)
                .withHeaderBackground(R.drawable.ct6)
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

                        //Toast.makeText(MainActivity.this, "withOnAccountHeaderListener", Toast.LENGTH_SHORT).show();
                        //headerNavigationLeft.setBackgroundRes(R.drawable.vyron);
                        headerNavigationLeft.setBackgroundRes(((ProfileDrawerItemCw) profile).getBackground());

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

                        return false;
                    }


                }).withOnAccountHeaderSelectionViewClickListener(new AccountHeader.OnAccountHeaderSelectionViewClickListener() {
                    @Override
                    public boolean onClick(View view, IProfile profile) {
                        Log.v("events", "onClick");
                        Log.v("events profileListOpen", Boolean.toString(ProfileDrawerItemCw.profileListOpen));

                        ProfileDrawerItemCw.profileListOpen = !ProfileDrawerItemCw.profileListOpen;
                        return false;
                    }
                })
                .build();

        ProfileDrawerItemCw.setAccountHeader(headerNavigationLeft);

        // NAVIGATION DRAWER - CORPO [FB]
        navigationDrawerLeft = new DrawerBuilder(this)
                .withToolbar(mToolbar)
                        //.withDisplayBelowStatusBar(true)
                .withDisplayBelowStatusBar(false)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.LEFT)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(1)
                .withActionBarDrawerToggle(true)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Log.v("events Drawer ItemClick", "position=" + Integer.toString(position));
                        if (position>0) {
                            mViewPager.setCurrentItem(position-1);
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


        listCatefories = getSetCategoryList();
        if (listCatefories != null && listCatefories.size() > 0) {
            for (int i = 0; i < listCatefories.size(); i++) {
                navigationDrawerLeft.addItem(listCatefories.get(i));
            }
            navigationDrawerLeft.setSelection(mItemDrawerSelected);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_second_activity) {
            startActivity(new Intent(this, SecondActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }




    public List<Car> getSetCarList(int qtd) {
        return (getSetCarList(qtd, 0));
    }

    public List<Car> getSetCarList(int qtd, int category) {
        String[] models = new String[]{"Gallardo", "Vyron", "Corvette", "Pagani Zonda", "Porsche 911 Carrera", "BMW 720i", "DB77", "Mustang", "Camaro", "CT6"};
        String[] brands = new String[]{"Lamborghini", " bugatti", "Chevrolet", "Pagani", "Porsche", "BMW", "Aston Martin", "Ford", "Chevrolet", "Cadillac"};
        int[] categories = new int[]{2, 1, 2, 1, 1, 4, 3, 2, 4, 1};
        int[] photos = new int[]{R.drawable.gallardo, R.drawable.vyron, R.drawable.corvette, R.drawable.paganni_zonda, R.drawable.porsche_911, R.drawable.bmw_720, R.drawable.db77, R.drawable.mustang, R.drawable.camaro, R.drawable.ct6};
        String description = "Lorem Ipsum é simplesmente uma simulação de texto da indústria tipográfica e de impressos, e vem sendo utilizado desde o século XVI, quando um impressor desconhecido pegou uma bandeja de tipos e os embaralhou para fazer um livro de modelos de tipos. Lorem Ipsum sobreviveu não só a cinco séculos, como também ao salto para a editoração eletrônica, permanecendo essencialmente inalterado. Se popularizou na década de 60, quando a Letraset lançou decalques contendo passagens de Lorem Ipsum, e mais recentemente quando passou a ser integrado a softwares de editoração eletrônica como Aldus PageMaker.";
        List<Car> listAux = new ArrayList<>();

        for (int i = 0; i < qtd; i++) {
            Car c = new Car(models[i % models.length], brands[i % brands.length], photos[i % models.length]);
            c.setDescription(description);
            c.setCategory(categories[i % brands.length]);
            c.setTel("33221155");

            if (category != 0 && c.getCategory() != category) {
                continue;
            }

            listAux.add(c);
        }
        return (listAux);
    }

    public List<Car> getCarsByCategory(int category) {
        List<Car> listAux = new ArrayList<>();
        for (int i = 0; i < listCars.size(); i++) {
            if (category != 0 && listCars.get(i).getCategory() != category) {
                continue;
            }

            listAux.add(listCars.get(i));
        }
        return (listAux);
    }

    public List<Car> getListCars() {
        return (listCars);
    }

    // CATEGORIES
    private List<PrimaryDrawerItem> getSetCategoryList() {
        String[] names = new String[]{"Todos os Carros", "Carros de Luxo", "Carros Esportivos", "Carros para Colecionadores", "Carros Populares"};
        int[] icons = new int[]{R.drawable.car_1, R.drawable.car_1, R.drawable.car_2, R.drawable.car_3, R.drawable.car_4};
        int[] iconsSelected = new int[]{R.drawable.car_selected_1, R.drawable.car_selected_1, R.drawable.car_selected_2, R.drawable.car_selected_3, R.drawable.car_selected_4};
        List<PrimaryDrawerItem> list = new ArrayList<>();

        for (int i = 0; i < names.length; i++) {
            PrimaryDrawerItem aux = new PrimaryDrawerItem();


            aux.withName(names[i]);
            aux.withIcon(getResources().getDrawable(icons[i]));
            aux.withTextColor(getResources().getColor(R.color.colorPrimarytext));
            aux.withSelectedIcon(getResources().getDrawable(iconsSelected[i]));
            aux.withSelectedTextColor(getResources().getColor(R.color.colorPrimary));

            list.add(aux);
        }
        return (list);
    }

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
        if (navigationDrawerLeft.isDrawerOpen()) {
            navigationDrawerLeft.closeDrawer();
        }
//        else if((fab!=null) && (fab.isOpened())){
//            fab.close(true);
//        }
        else {
            super.onBackPressed();
        }
    }

}
