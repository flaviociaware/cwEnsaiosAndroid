package br.com.ciaware.android.ensaios.thiengocalopsita.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

import br.com.ciaware.android.ensaios.thiengocalopsita.CarActivity;
import br.com.ciaware.android.ensaios.thiengocalopsita.MainActivity;
import br.com.ciaware.android.ensaios.thiengocalopsita.R;
import br.com.ciaware.android.ensaios.thiengocalopsita.adapters.CarAdapter;
import br.com.ciaware.android.ensaios.thiengocalopsita.domain.Car;
import br.com.ciaware.android.ensaios.thiengocalopsita.interfaces.RecyclerViewOnClickListenerHack;


public class CarFragment extends Fragment implements RecyclerViewOnClickListenerHack, View.OnClickListener {
    protected static final String TAG = "LOG";
    protected RecyclerView mRecyclerView;
    protected List<Car> mList;
    protected FloatingActionMenu fab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){
            mList = savedInstanceState.getParcelableArrayList("mList");
        }
        else{
            mList = ((MainActivity) getActivity()).getCarsByCategory(0);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);

        // Garante que o tamanho do RecyclerView não vai mudar [FB]
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy > 0){
                    fab.hideMenuButton(true);
                }
                else{
                    fab.showMenuButton(true);
                }

                LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                //GridLayoutManager llm = (GridLayoutManager) mRecyclerView.getLayoutManager();
//                StaggeredGridLayoutManager llm = (StaggeredGridLayoutManager) mRecyclerView.getLayoutManager();
//                int[] aux = llm.findLastCompletelyVisibleItemPositions(null);
//                int max = -1;
//                for (int i = 0; i < aux.length; i++) {
//                    max = aux[i] > max ? aux[i] : max;
//                }

                CarAdapter adapter = (CarAdapter) mRecyclerView.getAdapter();

                if (mList.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
//                if (mList.size() == max + 1) {
                    List<Car> listAux = ((MainActivity) getActivity()).getSetCarList(10);

                    for (int i = 0; i < listAux.size(); i++) {
                        adapter.addListItem(listAux.get(i), mList.size());
                    }
                }
            }
        });

        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getActivity(), mRecyclerView, this));


        // Definindo o tipo de layout [FB]

        // LinearLayoutManager
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        // Orientação vertical/horizontal
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        //Começa a lista de baixo para cima, interessante para chat [FB]
        //llm.setReverseLayout(true);

        // Setando o gerenciador de layout da lista (recyclerview)
        mRecyclerView.setLayoutManager(llm);

        /*
        GridLayoutManager llm = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(llm);
        */

        /*
        StaggeredGridLayoutManager llm = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        llm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        //llm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerView.setLayoutManager(llm);
         */


        mList = ((MainActivity) getActivity()).getSetCarList(10);
        CarAdapter adapter = new CarAdapter(getActivity(), mList);

        // adapter.setRecyclerViewOnClickListenerHack(this);
        mRecyclerView.setAdapter( adapter );
        setFloatingActionButton();

        /* Library: com.melnykov.fab.FloatingActionButton [FB]
        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.attachToRecyclerView(mRecyclerView, new ScrollDirectionListener() {
            @Override
            public void onScrollDown() {

            }

            @Override
            public void onScrollUp() {

            }
        }, new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();

                CarAdapter adapter = (CarAdapter) mRecyclerView.getAdapter();

                if (mList.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    List<Car> listAux = ((MainActivity) getActivity()).getSetCarList(10);

                    for (int i = 0; i < listAux.size(); i++) {
                        adapter.addListItem(listAux.get(i), mList.size());
                    }
                }
            }
        });

        fab.setOnClickListener(this);
        */



        return view;
    }
    public void setFloatingActionButton(){
        fab = (FloatingActionMenu) getActivity().findViewById(R.id.fab);
        fab.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean b) {
                Toast.makeText(getActivity(), "Is menu opened? "+(b ? "true" : "false"), Toast.LENGTH_SHORT).show();
            }
        });
        fab.showMenuButton(true);
        fab.setClosedOnTouchOutside(true);

        FloatingActionButton fab1 = (FloatingActionButton) getActivity().findViewById(R.id.fab1);
        FloatingActionButton fab2 = (FloatingActionButton) getActivity().findViewById(R.id.fab2);
        FloatingActionButton fab3 = (FloatingActionButton) getActivity().findViewById(R.id.fab3);
        FloatingActionButton fab4 = (FloatingActionButton) getActivity().findViewById(R.id.fab4);
        FloatingActionButton fab5 = (FloatingActionButton) getActivity().findViewById(R.id.fab5);

        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
        fab4.setOnClickListener(this);
        fab5.setOnClickListener(this);
    }



    @Override
    public void onClickListener(View view, int position) {
        Toast.makeText(getActivity(), "onClickListener: " + position, Toast.LENGTH_SHORT).show();

        //CarAdapter adapter = (CarAdapter) mRecyclerView.getAdapter();
        //adapter.removeListItem(position);

        try {
            YoYo.with(Techniques.Tada)
                    .duration(700)
                    .playOn(view);
        } catch (Exception e) {

        }

        Intent intent = new Intent(getActivity(), CarActivity.class);
        intent.putExtra("car", mList.get( position ));
        getActivity().startActivity(intent);

    }

    @Override
    public void onLongPressClickListener(View view, int position) {

        Toast.makeText(getActivity(), "onLongPressClickListener: " + position, Toast.LENGTH_SHORT).show();

    }


    private static class RecyclerViewTouchListener implements RecyclerView.OnItemTouchListener{

        private Context mContext;
        private GestureDetector mGestureDetector;
        private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;


        public RecyclerViewTouchListener(Context c, final RecyclerView rv, RecyclerViewOnClickListenerHack rvoclh) {
            mContext = c;
            mRecyclerViewOnClickListenerHack = rvoclh;
            mGestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener(){
                @Override
                public void onLongPress(MotionEvent e) {
                    super.onLongPress(e);
                    View cv = rv.findChildViewUnder(e.getX(), e.getY());

                    if (cv!=null&&mRecyclerViewOnClickListenerHack!=null) {
                        mRecyclerViewOnClickListenerHack.onLongPressClickListener(cv, rv.getChildPosition(cv));
                    }
                }

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    View cv = rv.findChildViewUnder(e.getX(), e.getY());

                    if (cv!=null&&mRecyclerViewOnClickListenerHack!=null) {
                        mRecyclerViewOnClickListenerHack.onClickListener(cv, rv.getChildPosition(cv));
                    }


                    //return super.onSingleTapUp(e);
                    return true;
                }
            }
            );

        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            mGestureDetector.onTouchEvent(e);
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    @Override
    public void onClick(View v) {
        String aux = "";

        switch( v.getId() ){
            case R.id.fab1:
                aux = "Fab 1 clicked";
                break;
            case R.id.fab2:
                aux = "Fab 2 clicked";
                break;
            case R.id.fab3:
                aux = "Fab 3 clicked";
                break;
            case R.id.fab4:
                aux = "Fab 4 clicked";
                break;
            case R.id.fab5:
                aux = "Fab 5 clicked";
                break;
        }

        Toast.makeText(getActivity(), aux, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("mList", (ArrayList<Car>) mList);
    }
}
