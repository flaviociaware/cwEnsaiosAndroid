package br.com.ciaware.docid;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.ciaware.docid.content.DocIdContent;

/**
 * A fragment representing a single Documento detail screen.
 * This fragment is either contained in a {@link DocumentoListActivity}
 * in two-pane mode (on tablets) or a {@link DocumentoDetailActivity}
 * on handsets.
 */
public class DocumentoDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DocIdContent.DocIdItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DocumentoDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DocIdContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            //CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            //Toolbar appBarLayout = (Toolbar) activity.findViewById(R.id.detail_toolbar);
            //TextView appBarLayout = (TextView) activity.findViewById(R.id.toolbar_title);


                Log.v("ToolBar","carregado");
                if (mItem.id
                        .length()<=4) {
                    activity.setTitle(mItem.id);
                } else {
                    activity.setTitle(mItem.content);

                }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_documento_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.documento_detail)).setText(mItem.content + ": " + mItem.details);



            Log.v("rootView", rootView.getClass().getName());

            ImageView imgDocId =((ImageView) rootView.findViewById(R.id.imgDocId));



            if (imgDocId!=null) {


                Log.v("imgDocId", "imgDocId!=null");

                if (mItem.id.toLowerCase()
                        .equals("cn")) {
                    imgDocId.setImageResource(R.drawable.docid_cn);

                } else if (mItem.id.toLowerCase().equals("cns")) {

                    imgDocId.setImageResource(R.drawable.docid_cns);
                    imgDocId.setMaxWidth(360);
                    imgDocId.setMaxHeight(360);
                    imgDocId.setAdjustViewBounds(true);
                } else if (mItem.id.toLowerCase().equals("cpf")) {

                    imgDocId.setImageResource(R.drawable.docid_cpf);
                    imgDocId.setMaxWidth(360);
                    imgDocId.setMaxHeight(360);
                    imgDocId.setAdjustViewBounds(true);

                } else if (mItem.id.toLowerCase().equals("dnv")) {
                    imgDocId.setImageResource(R.drawable.docid_dnv);
                } else if (mItem.id.toLowerCase().equals("passaporte")) {

                    imgDocId.setImageResource(R.drawable.docid_passaporte);

                }

                imgDocId.refreshDrawableState();

            } else {
                Log.v("imgDocId", "imgDocId==null");

            }


            //mItem.getImagePath();
        }

        return rootView;
    }
}
